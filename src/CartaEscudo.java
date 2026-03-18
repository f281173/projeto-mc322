public class CartaEscudo extends  Carta{
    private int escudoGanho;
    

    public CartaEscudo(String nome, String descricao,  int custo, int escudoGanho, int opcao_carta) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.escudoGanho = escudoGanho;
        this.opcao_carta = opcao_carta;
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
    public int acessopcaocarta(){
        return this.opcao_carta;
    }


}


