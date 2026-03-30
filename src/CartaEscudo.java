public class CartaEscudo extends  Carta{
    private int escudoGanho;
    

    public CartaEscudo(String nome, String descricao,  int custo, int escudoGanho, int opcaoCarta) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
        this.opcaoCarta = opcaoCarta;
    }

    



@Override  
    public void usar(Entidade personagem, Baralho baralho) {
        personagem.ganhaEscudo(this);
        baralho.adicionaPilhaDescarte(this);
    }

@Override
    public String acessoNome() {
        return this.nome;
    }


    public int acessoEscudoGanho() {
        return this.escudoGanho;
    }

@Override
    public int acessoCusto() {
        return  this.custo;
    }

@Override
    public String acessoDescricao(){
        return this.descricao;}

@Override
    public int getOpcaoCarta(){
        return this.opcaoCarta;
    }


}


