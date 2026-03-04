public class Carta{

    private String nome;
    private int custo;
    private int dano;

    private Carta(String nome, int custo){
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;
    }


    public int Causa_dano(int vida_inimigo){
        vida_inimigo -= this.dano;
        return vida_inimigo;
    }
    