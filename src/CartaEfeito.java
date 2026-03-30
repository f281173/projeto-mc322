public class CartaEfeito extends Carta{
    protected TiposEfeitos tipo;
    protected int acumulos;

    public CartaEfeito(String nome, String descricao, int custo, int opcaoCarta, TiposEfeitos tipo, int acumulos) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.opcaoCarta = opcaoCarta;
        this.tipo = tipo;
        this.acumulos = acumulos;
    }

    @Override
    public String acessoNome() {
    return this.nome;
    }

    @Override
    public  int acessoCusto() {
        return this.custo;
    }

    @Override
    public void  usar(Entidade personagem, Baralho baralho) {
        personagem.aplicarEfeito(this.tipo, this.acumulos);
        baralho.adicionaPilhaDescarte(this);
    }

    @Override
    public String acessoDescricao() {
        return this.descricao;
    }

    @Override
    public int getOpcaoCarta() {
        return this.opcaoCarta;
    }

    public TiposEfeitos getTipoEfeito() {
        return this.tipo;
    }

    public int getAcumulo() {
        return this.acumulos;
    }

}
