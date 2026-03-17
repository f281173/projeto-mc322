public abstract class Carta {
    protected  String nome;
    protected String descricao;
    protected  int custo;

    public  abstract String acessoNome();

    public abstract  int acessoCusto();

    public abstract void  usar(Entidade personagem);

    public abstract String acessoDescricao();
}
