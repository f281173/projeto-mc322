
public class CartaDano {

    private String nome;
    private int custo;
    private int dano_carta;

    public CartaDano(String nome, int custo, int dano) {
        this.nome = nome;
        this.custo = custo;
        this.dano_carta = dano;
    }

    public int valida_dano() {
        return this.dano_carta;
    }

    public int Causa_dano(int vida_inimigo) {
        vida_inimigo -= this.dano_carta;
        return vida_inimigo;
    }
    

    /* */
    public String acessoCartaDano_nome() {
        return this.nome;
    }

    public int acessoCartaDano_dano() {
        return this.dano_carta;
    }

    public int acessoCartaDano_custo() {
        return this.custo;
    }
}
