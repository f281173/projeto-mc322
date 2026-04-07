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

    public Heroi(String nome, int vida, int escudo, int energia, int vidaInicial, int velocidade, boolean turno,GameManager gm,  Baralho deck) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energiaInicial = energia;
        this.energiaAtual = energia;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.gm = gm;
        this.baralhoPessoal = deck; // tenho que mudar isso aqui para um baralho específico de cada jogador.
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

    @Override
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }


    @Override
    public int getEscudo() {
        return this.escudo;
    }

    public int getEnergiaAtual() {
        return this.energiaAtual;
    }

    public void diminuiEnergia(int custo) {
        this.energiaAtual = this.energiaAtual - custo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getVida() {
        return this.vida;
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

    @Override
    public int getVidaInicial() {
        return this.vidaInicial;
    }

    @Override
    public int getVelocidade() {
        return this.velocidade;
    }

    // Pra ver se já jogou ou não naquela rodada
    @Override
    public boolean getTurno() {
        return this.turno;
    }


    public  Baralho getBaralhoPessoal() {
        return this.baralhoPessoal;
    }

    @Override
    public void verificaseAtacou(boolean status) {
        this.turno = status;
    }

    public void resetaEnergia() {
        this.energiaAtual = this.energiaInicial;
    }

    /*vale questionar se isso deve estar aqui ou não*/
    private int buscaEfeito(TiposEfeitos tipoAlvo) {
        for (int i = 0; i < this.listaEfeitos.size(); i ++) {
            Efeito efeito = listaEfeitos.get(i);

            if (efeito.getTipo() == tipoAlvo)
                return i;
        }
        return -1; // não existe ainda esse efeito agindo no Heroi
    }

    @Override 
    public void aplicarEfeito(Efeito efeito) {
        int valor = this.buscaEfeito(efeito.getTipo());

        /* significa que ainda não existe esse efeito nessa entidade */
        if (valor == -1) {
            Efeito novoEfeito = Efeito.criaEfeito(efeito);
            novoEfeito.setDono(this);
            this.listaEfeitos.add(novoEfeito);

            /* preciso inscrever cada efeito do modo correto */
            if (novoEfeito.getTipo() == TiposEfeitos.VENENO) {
                this.gm.inscrever(novoEfeito, Estados.INICIO_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FORCA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);
            }

        } else {
            /*decido como cada tipo de efeito vai se comportar*/
            if (efeito.getTipo() == TiposEfeitos.VENENO) {
                this.listaEfeitos.get(valor).aumentaAcumulos(efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                ((EfeitoFraqueza) this.listaEfeitos.get(valor)).alteraFraqueza(((EfeitoFraqueza)efeito).getValorFraqueza(), efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FORCA) {
                ((EfeitoForca) this.listaEfeitos.get(valor)).alteraForca(((EfeitoForca)efeito).getValorForca(), efeito.getAcumulosInicial());
            }
        }
    }

    @Override
    public void terminaEfeito(TiposEfeitos tipo) {
        int indice = this.buscaEfeito(tipo);
        this.listaEfeitos.remove(indice);
    }

    public RequisitoJogo temRequisito(int indice) {
        Carta carta = this.getMaoJogador().devolveCarta(indice); // tenho que ver se devo criar outro método em heroi para acessar de uma vez.
        return carta.cartaRequisito();
    }


    public void ataque(Entidade alvo, int valorDano) {
        /*vamos ver quais são os efeitos na lista de efeitos que alterar o valor do dano */
        for (Efeito efeito: this.listaEfeitos) {
            if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                double fator = (100.0 - ((EfeitoFraqueza) efeito).getValorFraqueza()) / 100;
                valorDano = (int)(valorDano * fator); // aqui fiz o truncamento para baixo.
            }

            if (efeito.getTipo() == TiposEfeitos.FORCA) {
                double fator = (100.0 + ((EfeitoForca) efeito).getValorForca()) / 100;
                valorDano = (int)(valorDano * fator); // aqui fiz o truncamento para baixo
            }
        }
        /*publico que o heroi vai atacar */
        gm.notificar(this, Estados.ATAQUE);
        alvo.recebeDano(valorDano);
    }

    /*método para validar a energia da entidade*/
    public boolean analisaEnergia(int custo) {
        if (this.getEnergiaAtual() >= custo) {
            this.diminuiEnergia(custo);
            return true;
        }
        return false;
    }

    /*versão de jogarCarta que atua no Heroi */
    public String jogarCarta(int indiceCartaMao) {
        /*validar se o heroi pode de fato usar a carta*/
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao); //posso criar um método que engloba os dois passos (perguntar pro ped)
        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, this, null); // perguntar sobre a questão do null
            return resposta; //significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET; // carta descartada sem o jogador conseguir usar.
    }

    /*versão de jogarCarta para quando a ação depende de um inimigo alvo */
    public String jogarCarta(int indiceCartaMao, Inimigo alvo) {
        /*validar se o heroi pode de fato usar a carta*/
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao); //posso criar um método que engloba os dois passos (perguntar pro ped)
        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, alvo, null); //perguntar sobre a questão do null
            return resposta; //significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET; // carta descartada sem o jogador conseguir usar.
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
        /*validar se o heroi pode de fato usar a carta*/
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao); //posso criar um método que engloba os dois passos (perguntar pro ped)
        int custo = cartaEscolhida.getCusto();

        if (this.analisaEnergia(custo)) {
            String resposta = cartaEscolhida.usar(this, this, inimigos);
            return resposta; //significa que a carta foi usada com sucesso
        }
        return Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET; // carta descartada sem o jogador conseguir usar.
    }

    public void imprimeEfeitos() {
        System.out.println("=================================================================");
        System.out.println("EFEITOS QUE ESTÃO EM AÇÃO EM: " + this.getNome());
        for (Efeito efeito : this.listaEfeitos) {
            System.out.println(this.getNome() + "está sob "+ efeito.getString());
        }
        System.out.println("=================================================================");
    }
}
