package mc322.jogo.entidades;

import java.util.HashMap;

import mc322.jogo.Dados;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por representar os Heróis controlados pelo jogador.
 * Herda as características básicas de Entidade, mas adiciona mecânicas
 * exclusivas de gerenciamento de Energia e uma Mão de Cartas individual.
 */

public class Heroi extends Entidade {
    private int energia;
    private int energiaAtual;
    private MaoJogador maoJogador;
    private Baralho baralhoPessoal;

    public Heroi(String nome, int vida, int escudo, int energia, int vidaInicial, int velocidade, boolean turno,
            GameManager gm) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.maoJogador = new MaoJogador(baralhoPessoal);
        this.gm = gm;
        this.baralhoPessoal = Dados.carregarBaralhoGeral(); // tenho que mudar isso aqui para um baralho específico de
                                                            // cada jogador.
        this.inicializaMap();
    }

    public void inicializaMap() { // poderia estar em entidade
        this.mapEfeitos = new HashMap<>();

        for (TiposEfeitos tipo : TiposEfeitos.values())
            this.mapEfeitos.put(tipo, null);
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
        if (this.vida <= 0) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public int getEscudo() {
        return this.escudo;
    }

    public int getEnergia() {
        return this.energia;
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

    @Override
    public void verificaseAtacou(boolean status) {
        this.turno = status;
    }

    @Override
    public void aplicarEfeito(TiposEfeitos tipo, int acumulos) {
        Efeito valor = this.mapEfeitos.get(tipo);

        /* significa que ainda não existe esse efeito nessa entidade */
        if (valor == null) {
            Efeito novoEfeito = Efeito.criaEfeito(tipo, acumulos, this.gm);
            novoEfeito.setDono(this);
            this.mapEfeitos.put(tipo, novoEfeito);

            /* preciso inscrever cada efeito do modo correto */
            if (tipo == TiposEfeitos.VENENO) {
                this.gm.inscrever(novoEfeito, Estados.INICIO_DE_TURNO);

            } else if (tipo == TiposEfeitos.FRAQUEZA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);

            } else if (tipo == TiposEfeitos.FORCA) {
                // implementação do tipo força
            }

        } else {
            valor.aumentaAcumulos(acumulos);
        }
    }

    @Override
    public void terminaEfeito(TiposEfeitos tipo) {
        this.mapEfeitos.put(tipo, null);
    }

    public void setHasEfeitoFraqueza(boolean valor) {
        this.hasEfeitoFraqueza = valor;
    }

    public boolean getHasEfeitoFraqueza() {
        return this.hasEfeitoFraqueza;
    }

    public void jogarCarta(int indiceCartaMao) {
        /*validar se o heroi pode de fato usar a carta*/
        Carta cartaEscolhida = this.getMaoJogador().removeCartaMaoJogador(indiceCartaMao); //posso criar um método que engloba os dois passos (perguntar pro ped)

    }

    public boolean temOpcaoCartaMao(int i) {
        if (!this.temCartaDisponivel())
            return false;
        return this.getMaoJogador().isIndiceValido(i);
    }
}
