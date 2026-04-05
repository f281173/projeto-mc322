package mc322.jogo.entidades;

import java.util.ArrayList;
import java.util.HashMap;

import mc322.jogo.cartas.Carta;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;
import java.util.Collections;

/**
 * Classe responsável por representar os adversários do jogo.
 * Herda de Entidade e possui uma mecânica de combate automática baseada
 * em um baralho pré-definido.
 */

public class Inimigo extends Entidade {

    private ArrayList<Carta> deckInimigo;
    private Carta ultimaCartaUsada;

    public Inimigo(String nome, int vida, int escudo, int vidaInicial, int velocidade, boolean turno, GameManager gm) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.deckInimigo = new ArrayList<>(); // isso aqui deveria ser na verdade um sistema de ações !!
        this.gm = gm;
        this.inicializaMap();
    }

    public void inicializaMap() { // poderia estar em entidade
        this.mapEfeitos = new HashMap<>();

        for (TiposEfeitos tipo : TiposEfeitos.values())
            this.mapEfeitos.put(tipo, null);
    }

    public void adicionaCard(Carta carta) {
        this.deckInimigo.add(carta);
    }

    public void embaralhaBaralho() {
        Collections.shuffle(this.deckInimigo);
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
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    // Pega a última carta, analisa e usa. Depois volta pro deck
    public void atacar(Heroi personagem) {
        if (this.deckInimigo.size() > 0) {
            this.ultimaCartaUsada = this.deckInimigo.remove(0);
            this.ultimaCartaUsada.setPersonagem(this);

            if (this.ultimaCartaUsada.getOpcaoCarta() == 0) {
                /*tenho que publicar que o inimigo vai atacar */
                gm.notificar(this, Estados.ATAQUE);

                CartaDano cartaDano = (CartaDano) this.ultimaCartaUsada;
                personagem.recebeDano(cartaDano.acessoCartaDanoDano());

            } else if (this.ultimaCartaUsada.getOpcaoCarta() == 1) {
                CartaEscudo cartaescudo = (CartaEscudo) this.ultimaCartaUsada;
                this.ganhaEscudo(cartaescudo.getEscudoGanho());

            } else if (this.ultimaCartaUsada.getOpcaoCarta() == 2) {
                CartaEfeito cartaEfeito = (CartaEfeito) this.ultimaCartaUsada;
                personagem.aplicarEfeito(cartaEfeito.getTipoEfeito(), cartaEfeito.getAcumulo());
            }

            this.deckInimigo.add(this.ultimaCartaUsada);
        }

    }

    // Pega o valor da última carta usada, se for do tipo dano.
    public int getDano() {
        if (this.ultimaCartaUsada != null && this.ultimaCartaUsada.getOpcaoCarta() == 0) {
            CartaDano cartadano = (CartaDano) this.ultimaCartaUsada;
            return cartadano.acessoCartaDanoDano();
        }

        return 0;
    }

    public String getNomeCarta() {
        return this.ultimaCartaUsada.getNome();
    }

    public int getTipoCarta() {
        return this.ultimaCartaUsada.getOpcaoCarta();
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
    public int getEscudo() {
        return this.escudo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    @Override
    public int getVidaInicial() {
        return this.vidaInicial;
    }

    // Subsitui o deck atual do inimigo por uma lista de cartas definidas, que estão
    // em Dados.
    public void transformaDeck(ArrayList<Carta> cartas) {
        this.deckInimigo = cartas;
    }

    @Override
    public int getVelocidade() {
        return this.velocidade;
    }

    @Override
    public boolean getTurno() {
        return this.turno;
    }

    // Olha se já atacou
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

            /*preciso inscrever cada efeito do modo correto */
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
}
