import java.util.ArrayList;

public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int vidaInicial;
    protected int velocidade;
    protected boolean turno;
    protected ArrayList<Efeito> listaEfeitos;
    protected GameManager gm;

    public abstract void recebeDano(int dano);

    public abstract boolean estaVivo();

    public abstract int acessoEscudo();

    public abstract void ganhaEscudo(CartaEscudo cartaEscudo);

    public abstract String acessoNome();

    public abstract int acesso_vida();

    public abstract int getVidaInicial();

    public abstract int acessoVelocidade();

    public abstract boolean acessoturno();

    public abstract void verificaseAtacou(boolean status);

    public abstract void aplicarEfeito(Efeito efeito, int acumulos);

    /*retira o efeito da lista de efeitos daquela entidade*/
    public abstract void terminaEfeito(Efeito efeito);

}
