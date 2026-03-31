public class CartaEscudo extends Carta {
    private int escudoGanho;

    public CartaEscudo(String nome, String descricao, int custo, int escudoGanho, int opcaoCarta) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
        this.opcaoCarta = opcaoCarta;
    }

    @Override
    public void usar(Entidade personagem, Baralho baralho) {
        personagem.ganhaEscudo(this.getEscudoGanho());
        this.personagem = null;
        baralho.adicionaPilhaDescarte(this);
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    public int getEscudoGanho() {
        return this.escudoGanho;
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
