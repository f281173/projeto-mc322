package mc322.jogo.entidades;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.EfeitoForca;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por representar os Heróis controlados pelo jogador.
 * Herda as características básicas de Entidade, mas adiciona mecânicas
 * exclusivas de gerenciamento de Energia e uma Mão de Cartas individual.
 */

public class Heroi extends Entidade {
    private int energiaInicial;
    private int energiaAtual;
    private MaoJogador maoJogador;
    private Baralho baralhoPessoal;

    public Heroi(String nome, int vida, int escudo, int energia, int vidaInicial, int velocidade, boolean turno,
            GameManager gm, Baralho deck) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energiaInicial = energia;
        this.energiaAtual = energia;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.gm = gm;
        this.baralhoPessoal = deck;
        this.maoJogador = new MaoJogador(baralhoPessoal);
        this.listaEfeitos = new ArrayList<>();
    }

    @Override
    public void recebeDano(int dano) {

        if (this.escudo >= dano) {
            this.escudo -= dano;
        } else {

            int danoRestante = dano - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }
    }

    @Override
    public void recebeDanoEfeito(int dano) {
        if (this.vida >= dano) {
            this.vida -= dano;
        } else {
            this.vida = 0;
        }
    }

    @Override
    public void ganhaEscudo(int valorEscudo) {
        this.escudo += valorEscudo;
    }

    @Override
    public void zeraEscudo() {
        this.escudo = 0;
    }

    public int getEnergiaAtual() {
        return this.energiaAtual;
    }

    public void diminuiEnergia(int custo) {
        this.energiaAtual = this.energiaAtual - custo;
    }

    public void imprimeMaoJogador() {
        this.maoJogador.imprimeCartas();
    }

    public MaoJogador getMaoJogador() {
        return this.maoJogador;
    }

    public void recebeCarta(Carta cartaEscolhida) {
        this.getMaoJogador().ganhaCarta(cartaEscolhida);
    }

    public boolean temCartaDisponivel() {
        return !this.getMaoJogador().maoVazia();
    }

    public void resetaEnergia() {
        this.energiaAtual = this.energiaInicial;
    }

    public Baralho getBaralhoPessoal() {
        return this.baralhoPessoal;
    }

    /**
     * Método para receber a informação dos requisitos da carta que o Herói vai
     * jogar.
     * O Herói chama o método da própria Carta que está presente em seu Deck
     * 
     * @param indice é a posição da carta presente na mão do jogador.
     * @return retorna um requisito do jogo dentre as opções da classe
     *         {@link RequisitoJogo}
     */
    public RequisitoJogo temRequisito(int indice) {
        Carta carta = this.getMaoJogador().devolveCarta(indice);
        return carta.cartaRequisito();
    }

    @Override
    public void ataque(Entidade alvo, int valorDano) {
        /*
         * verifica quais os efeitos estão presente na lista de efeitos e se impactam no
         * ataque da Entidade
         */
        for (Efeito efeito : this.getListaEfeitos()) {
            if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                double fator = (100.0 - ((EfeitoFraqueza) efeito).getValorFraqueza()) / 100;
                valorDano = (int) (valorDano * fator); // aqui fiz o truncamento para baixo.
            }

            if (efeito.getTipo() == TiposEfeitos.FORCA) {
                double fator = (100.0 + ((EfeitoForca) efeito).getValorForca()) / 100;
                valorDano = (int) (valorDano * fator); // aqui fiz o truncamento para baixo
            }
        }
        /* publico que o heroi vai atacar */
        gm.notificar(this, Estados.ATAQUE);
        alvo.recebeDano(valorDano);
    }

    /**
     * Método para validar se a energia do Herói é suficiente para executar a ação
     * desejada por ele
     * 
     * @param custo inteiro que representa o Custo de uma Carta
     * @return retorna um booleano que indica se é possível ou não.
     */
    public boolean analisaEnergia(int custo) {
        if (this.getEnergiaAtual() >= custo) {
            this.diminuiEnergia(custo);
            return true;
        }
        return false;
    }

    /* versão de jogarCarta que atua no Heroi */
    public String jogarCarta(int indiceCartaMao) {
        /* validar se o heroi pode de fato usar a carta */
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao);

        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, this, null);
            return resposta; // significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET;

    }

    /* versão de jogarCarta para quando a ação depende de um inimigo alvo */
    public String jogarCarta(int indiceCartaMao, Inimigo alvo) {
        /* validar se o heroi pode de fato usar a carta */
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao);
        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, alvo, null);
            return resposta; // significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET;
    }

    public boolean temOpcaoCartaMao(int i) {
        if (!this.temCartaDisponivel())
            return false;
        return this.getMaoJogador().isIndiceValido(i);
    }

    public void limpaMao() {
        this.maoJogador.limpaMaoJogador(this.baralhoPessoal);
    }

    public String jogarCarta(int indiceCartaMao, ArrayList<Inimigo> inimigos) {
        /* validar se o heroi pode de fato usar a carta */
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao);
        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, this, inimigos);
            return resposta; // significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET;
    }
}
