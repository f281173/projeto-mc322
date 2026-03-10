public class Heroi{
    private String nome;
    private int vida;
    private int escudo;
    private CartaDano[] dano;

    public Heroi(String nome, int vida, int escudo){
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.dano = new CartaDano[10];
    }
    
    public int acessoEscudo() {
        return this.escudo;
    }

    public  int encontraNome(String nome_carta) {
        int i = 0;

        while (i < this.dano.length) {
            if (this.dano[i].acessoCartaDano_nome().equals(nome_carta))
                return i;
            i++;
        }
        return -1; 
        }

    public CartaDano encontraCarta(String nomeCarta) {
        int i = encontraNome(nomeCarta);
        CartaDano carta = this.dano[i];
        return carta;
    }


    public void RecebeDano(Inimigo personagem){

        if (this.escudo >=  personagem.acessoDano()) {
            this.escudo -= personagem.acessoDano();
        } else {

        int dano_restante = personagem.acessoDano() - this.escudo;
        this.escudo = 0;
        this.vida -= dano_restante;}
    
    }
    

    public void GanhaEscudo(CartaEscudo carta_escudo){
        this.escudo = carta_escudo.acessoEscudoGanho();
    }


    public int Estar_vivo(){
        if (this.vida <=0){
            return 0; 
        } else {
            return 1;
        }
    }


    public void adiciona_card(CartaDano carta, int i) {
        this.dano[i] = carta;
}

}





