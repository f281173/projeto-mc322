
public class CartaDano extends Carta{
    private int danoCarta;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.danoCarta = dano;

    }

@Override
    public void usar(Entidade vilao) {
        vilao.vida -= this.danoCarta;
    }
 
@Override
    public String acessoNome() {
        return this.nome;
    }


    public int acessoCartaDanoDano() {
        return this.danoCarta;
    }

@Override
    public int acessoCusto() {
        return this.custo;
    }

    
@Override
    public String acessoDescricao(){
        return this.descricao;}

}
