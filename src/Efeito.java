public abstract class Efeito implements Subscriber{
    protected String nome;
    protected Entidade dono;
    protected int acumulos;
    protected int acumulosInicial;
    protected String descricao;
    protected GameManager gm;

    /* método para informar sobre a carta de efeito e a quantidade de acumulos inicial*/
    public void getString() {
        System.out.println("Efeito - " + this.nome + "com - " + this.acumulos + "de acúmulo");
    }

    public void getDescricao() {
        System.out.println("Descrição do efeito: " + this.nome + " descrição: " + this.descricao + "\nacúmulos: " + this.acumulos );
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

    public Efeito(String nome, int acumulos, String descricao, GameManager gm) {
        this.nome = nome;
        this.acumulosInicial = acumulos;
        this.acumulos = acumulos;
        this.descricao = descricao;
        this.gm = gm;
    }

    public abstract void acaoEfeito();
    
    public abstract Estados tipoDeEstado();

}   

