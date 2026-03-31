import java.util.HashMap;

public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int vidaInicial;
    protected boolean hasEfeitoFraqueza = false;
    protected boolean turno;
    protected HashMap<TiposEfeitos, Efeito> mapEfeitos;
    protected GameManager gm;
    protected int velocidade;

    /* método para gerar o dano partindo de uma carta de dano */
    public abstract void recebeDano(int dano);

    /* método para gerar o dano partindo de uma carta de Efeito */
    public abstract void recebeDanoEfeito(int dano);

    public abstract boolean estaVivo();

    public abstract int getEscudo();

    public abstract void ganhaEscudo(int valorEscudo);

    public abstract void zeraEscudo(); 

    public abstract String getNome();

    public abstract int getVida();

    public abstract int getVidaInicial();

    public abstract int getVelocidade();

    public abstract boolean getTurno();

    public abstract void verificaseAtacou(boolean status);

    public abstract void aplicarEfeito(TiposEfeitos tipo, int acumulos);

    /* retira o efeito da lista de efeitos daquela entidade */
    public abstract void terminaEfeito(TiposEfeitos tipo);

    public abstract void setHasEfeitoFraqueza(boolean valor);

    public abstract boolean getHasEfeitoFraqueza();

}
