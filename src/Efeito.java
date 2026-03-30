public abstract class Efeito implements Subscriber {
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected GameManager gm;

    /*
     * método para informar sobre a carta de efeito e a quantidade de acumulos
     * inicial
     */
    public String getString() {
        return "Efeito - " + this.nome + "com - " + this.acumulos + "de acúmulo";
    }

    public void imprimeDescricao(String descricao) {
        System.out.println("Efeito: " + this.nome + " descrição: " + descricao);
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

    public Efeito(int acumulos, GameManager gm) {
        this.acumulosInicial = acumulos;
        this.acumulos = acumulos;
        this.gm = gm;
    }

    public abstract void acaoEfeito();

    public abstract Estados tipoDeEstado();

        /*Nosso padrão para criar qualquer tipo de efeito */
    public static Efeito criaEfeito(TiposEfeitos tipo, int acumulos, GameManager gm) {
        if (tipo == TiposEfeitos.VENENO) {
            return new EfeitoVeneno(acumulos, gm);

        } else if (tipo == TiposEfeitos.FORCA) {
            return null; // ainda não implementei esse tipo
        }
        return null; // ainda não implementei esse tipo
    }
}
