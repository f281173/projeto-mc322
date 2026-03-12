public class CartaEscudo {

    private String nome;
    private int custo;
    private int escudo_ganho;

    public void usaEscudo(Heroi personagem) {
        personagem.ganhaEscudo(this);
    }

    public CartaEscudo(String nome, int custo, int escudo_ganho) {
        this.nome = nome;
        this.custo = custo;
        this.escudo_ganho = escudo_ganho;
    }


    public String acessoNome() {
        return this.nome;
    }

    public int acessoEscudoGanho() {
        return this.escudo_ganho;
    }

    public int acessoCusto() {
        return  this.custo;
    }

    
}

