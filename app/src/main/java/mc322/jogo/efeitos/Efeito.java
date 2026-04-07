package mc322.jogo.efeitos;

import mc322.jogo.observer.Subscriber;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.gerenciador.GameManager;

/**
 * Classe abstrada para implementar a base de diversos efeitos do Jogo.
 * O efeito é quem é responsável por receber a notificação
 * de uma determinada ação do jogo, que pode ser vista em {@link Estados} para a ação do efeito.
 * (Efeito é o Subscriber do padrão Observer).
 */
public abstract class Efeito implements Subscriber {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected GameManager gm;
    protected TiposEfeitos tipo;

    public Efeito(int acumulos, GameManager gm) {
        this.acumulosInicial = acumulos;
        this.acumulos = acumulos;
        this.gm = gm;
    }

    /*
     * método para informar sobre a carta de efeito e a quantidade de acumulos
     * inicial
     */
    public String getString() {
        return "Efeito - " + this.nome + " com - " + this.acumulos + " de acúmulo";
    }

    public int getAcumulos() {
        return this.acumulos;
    }

    public void diminuiAcumulos() {
        this.acumulos = this.acumulos - 1;
    }

    public void aumentaAcumulos(int x) {
        this.acumulos = this.acumulos + x;
    }

    public int getAcumulosInicial() {
        return this.acumulosInicial;
    }

    public Entidade getDono() {
        return this.dono;
    }

    public void setDono(Entidade dono) {
        this.dono = dono;
    }

    public GameManager getGm() {
        return this.gm;
    }

    public TiposEfeitos getTipo() {
        return this.tipo;
    }

    /**
     * Método para indicar para a classe que interage com o usuário quais são os 
     * requisitos necessários para a ação desse efeito.
     * 
     * @return retorna uma constante disponíveis em  {@link RequisitoJogo}
     */
    public abstract RequisitoJogo requisitoEfeito();

    public abstract void acaoEfeito();

    /**
     * Um método responsável por ser uma fábrica de criar objetos  do tipo
     * Efeito, dai podemos usar um único método para criar ou fazer uma cópia de um Efeito
     * já existente 
     *  
     * @param efeito O efeito que será copiado 
     * @return retorna um novo tipo efeito.
     */
    public static Efeito criaEfeito(Efeito efeito) {
        if (efeito.getTipo() == TiposEfeitos.VENENO) {
            return new EfeitoVeneno((EfeitoVeneno) efeito);

        } else if (efeito.getTipo() == TiposEfeitos.FORCA) {
            return new EfeitoForca((EfeitoForca) efeito);

        } else if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) { // garanto que ele é do tipo fraqueza
            return new EfeitoFraqueza((EfeitoFraqueza) efeito);
        }
        return null;
    }
}
