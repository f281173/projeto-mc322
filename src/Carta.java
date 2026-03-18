public abstract class Carta {
    protected  String nome;
    protected String descricao;
    protected  int custo;
    protected int opcao_carta;

    public  abstract String acessoNome();

    public abstract  int acessoCusto();

    public abstract void  usar(Entidade personagem, Baralho baralho);

    public abstract String acessoDescricao();

    public abstract int acessopcaocarta();
    
    
}
