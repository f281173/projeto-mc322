public abstract class Efeito implements Subscriber {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected GameManager gm;
    protected int dano;

    /*
     * método para informar sobre a carta de efeito e a quantidade de acumulos
     * inicial
     */
    public String getString() {
        return "Efeito - " + this.nome + "com - " + this.acumulos + "de acúmulo";
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

    @Override
    public Entidade getDono() {
        return this.dono;
    }

    public void setDono(Entidade dono) {
        this.dono = dono;
    }

    public GameManager getGm() {
        return this.gm;
    }

    public Efeito(int acumulos, GameManager gm, int dano) {
        this.acumulosInicial = acumulos;
        this.acumulos = acumulos;
        this.gm = gm;
        this.dano = dano;
    }

    public abstract void acaoEfeito();

    /* Nosso padrão para criar qualquer tipo de efeito */
    public static Efeito criaEfeito(TiposEfeitos tipo, int acumulos, GameManager gm, int dano) {
        if (tipo == TiposEfeitos.VENENO) {
            return new EfeitoVeneno(acumulos, gm, dano);

        } else if (tipo == TiposEfeitos.FORCA) {
            return new EfeitoForca(acumulos, gm, dano);

        } else if (tipo == TiposEfeitos.FRAQUEZA) {
            return new EfeitoFraqueza(acumulos, gm, dano);
        }
        return null;
    }
}
