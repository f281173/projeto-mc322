import java.util.ArrayList;
import java.util.HashMap;
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
        this.deckInimigo = new ArrayList<>();
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

            if (this.ultimaCartaUsada.getOpcaoCarta() == 0) {
                CartaDano cartaDano = (CartaDano) this.ultimaCartaUsada;
                personagem.recebeDano(cartaDano.acessoCartaDanoDano());

            } else if (this.ultimaCartaUsada.getOpcaoCarta() == 1) {
                CartaEscudo cartaescudo = (CartaEscudo) this.ultimaCartaUsada;
                this.ganhaEscudo(cartaescudo);

            } else if (this.ultimaCartaUsada.getOpcaoCarta() == 2) {
                CartaEfeito cartaEfeito = (CartaEfeito) this.ultimaCartaUsada;
                personagem.aplicarEfeito(cartaEfeito.getTipoEfeito(), cartaEfeito.getAcumulo());
            }

            this.deckInimigo.add(this.ultimaCartaUsada);
        }

    }

    // Pega o valor da última carta usada, se for do tipo dano.
    public int acessoDano() {
        if (this.ultimaCartaUsada != null && this.ultimaCartaUsada.getOpcaoCarta() == 0) {
            CartaDano cartadano = (CartaDano) this.ultimaCartaUsada;
            return cartadano.acessoCartaDanoDano();
        }

        return 0;
    }

    public String acessoNome_Carta() {
        return this.ultimaCartaUsada.acessoNome();
    }

    public int acessoTipoCarta() {
        return this.ultimaCartaUsada.getOpcaoCarta();
    }

    @Override
    public void ganhaEscudo(CartaEscudo cartaEscudo) {
        this.escudo = cartaEscudo.acessoEscudoGanho();
    }

    @Override
    public int acessoEscudo() {
        return this.escudo;
    }

    @Override
    public String acessoNome() {
        return this.nome;
    }

    @Override
    public int acesso_vida() {
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
    public int acessoVelocidade() {
        return this.velocidade;
    }

    @Override
    public boolean acessoturno() {
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
            this.gm.inscrever(novoEfeito, novoEfeito.tipoDeEstado());
        } else {
            valor.aumentaAcumulos(acumulos);
        }
    }

    @Override
    public void terminaEfeito(TiposEfeitos tipo) {
        this.mapEfeitos.put(tipo, null);
    }
}
