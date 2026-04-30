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
    /** atributo que serve como um molde para saber qual a energia padrão do Heroi no inicio do turno */
    private int energiaInicial;
    /** atributo com a energia em qualquer instante do turno para esse heroi */
    private int energiaAtual;
    /** atributo que faz a composição com um objeto {@link MaoJogador}*/
    private MaoJogador maoJogador;
    /** Atributo com o Objeto {@link Baralho} para o heroi poder manipular*/
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

    /**
     * Construtor auxiliar para construir os testes automatizados
     * 
     * @param vida
     * @param escudo
     * @param energia
     */
    public Heroi(int vida, int escudo, int energia, int velocidade, String nome) {
        this.vida = vida;
        this.vidaInicial = vida;
        this.escudo = escudo;
        this.energiaAtual = energia;
        this.energiaInicial = energia;
        this.velocidade = velocidade;
        this.nome = nome;
        this.listaEfeitos = new java.util.ArrayList<>();
        this.maoJogador = new MaoJogador(null);
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

        if (this.vida < 0)
            this.vida = 0;
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
        if (valorEscudo > 0) {
            this.escudo += valorEscudo;
        }
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
        this.maoJogador.imprimeCartas(); // foi implementado em terminal
    }

    public MaoJogador getMaoJogador() {
        return this.maoJogador;
    }

    public ArrayList<Carta> getMao() {
        return this.getMaoJogador().getMaoJogador();
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

    public void ganhaCarta(Carta carta) {
            this.baralhoPessoal.adicionaBaralho(carta);
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

    /**
     * jogar carta quando precisamos usar cartas que tem um efeito em qualquer
     * aliado heroi escolhido pelo usuário.
     * 
     * @param indiceCartaMao indice validado que representa a carta na mão do jogador
     * @param heroi heroi que será o alvo da ação.
     * @return string com a informação da ação daquela carta em específico.
     */
    public String jogarCarta(int indiceCartaMao, Heroi heroi) {
        /* validar se o heroi pode de fato usar a carta */
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao);
        int custo = cartaEscolhida.getCusto();
        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, heroi, null);
            return resposta; // significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET;

    }

    /**
     * Versão de jogar carta quando precisamos atingir apenas um inimigo em específico.
     * 
     * @param indiceCartaMao indice validado que representa a carta na mão do jogador
     * @param alvo inimigo alvo do ataque
     * @return string com a informação da ação daquela carta em específico.
     */
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

    /**
     * Método para chamar o limpa mão do Objeto MaoHeroi
     */
    public void limpaMao() {
        this.maoJogador.limpaMaoJogador(this.baralhoPessoal);
    }

    /**
     * Versão jogar carta quando estamos falando de cartas que 
     * tem a sua função em área e precisam atingir todos os inimigos
     * 
     * @param indiceCartaMao indice validado que representa a posição da carta na mão do heroi
     * @param inimigos vetor com todos os inimigos da batalha
     * @return informa o resultado da carta (muda a depender da carta)
     */
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

    

    public void aumentarVidaInicial(int bonus) {
        this.vidaInicial += bonus;
        this.vida += bonus; // Cura o valor que aumentou
    }

    public void aumentarEnergia(int bonus) {
        this.energiaInicial += bonus;
    }


}
