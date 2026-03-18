
public class CartaDano extends Carta{
    private int danoCarta;

    public CartaDano(String nome, String descricao, int custo, int dano, int opcao_carta) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.danoCarta = dano;
        this.opcao_carta = opcao_carta;

    }

@Override
    public void usar(Entidade vilao, Baralho baralho) {
        vilao.recebeDano(vilao, this);
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
    public int acessopcaocarta(){
        return this.opcao_carta;
    }


}
