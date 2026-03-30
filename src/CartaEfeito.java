public class CartaEfeito extends Carta{
    protected Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, int opcaoCarta, Efeito efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.opcaoCarta = opcaoCarta;
        this.efeito = efeito;
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
        this.efeito.setDono(personagem);
        personagem.aplicarEfeito(efeito, efeito.getAcumulos());
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

    public Efeito getEfeito() {
        return this.efeito;
    }
}
