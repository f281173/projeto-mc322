
public class CartaDano extends Carta{
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

@Override
    public int getOpcaoCarta(){
        return this.opcaoCarta;
    }


}
