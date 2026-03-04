public class Heroi{
    private String nome;
    private int vida;
    private int escudo;

    public Heroi(String nome, int vida, int escudo){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }


    public int Recebe_dano(int dano_sofrido){
        if (this.escudo >=  dano_sofrido) {
            this.escudo -= dano_sofrido;
        } else {

        int dano_restante = dano_sofrido - this.escudo;
        this.escudo = 0;
        this.vida -= dano_restante;}
    
    
        return this.vida;
    }
    

    public int Ganha_escudo(int carta_escudo){
        this.escudo = carta_escudo;
        return this.vida
    }

    public int Estar_vivo(){
        if (this.vida <=0){
            return 0; 
        } else {
            return 1;
        }
    }

}





