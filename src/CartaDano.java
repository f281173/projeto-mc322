
public class CartaDano extends Carta {
    private int danoCarta;

    public CartaDano(String nome, String descricao, int custo, int dano, int opcaoCarta) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.danoCarta = dano;
        this.opcaoCarta = opcaoCarta;
    }

    @Override
    public void usar(Entidade vilao, Baralho baralho) {
        vilao.recebeDano(this.acessoCartaDanoDano());
        baralho.adicionaPilhaDescarte(this);
        this.personagem = null;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public int acessoCartaDanoDano() {
        if (this.personagem.getHasEfeitoFraqueza()) {
            this.personagem.setHasEfeitoFraqueza(false);
            return (int) (this.danoCarta * 0.75);

        } else {
            return this.danoCarta;
        }
    }

    @Override
    public int getCusto() {
        return this.custo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public int getOpcaoCarta() {
        return this.opcaoCarta;
    }

}
