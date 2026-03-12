public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int dano;


    public Inimigo(String nome, int vida, int escudo, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.dano = dano;
    }

    
    public void ReceberDano(Heroi personagem, String nome_carta) {
        
        // encontra nome i .. acesosocartadano
        CartaDano carta = personagem.encontraCarta(nome_carta);
        int valorDano = carta.acessoCartaDano_dano();

        if (this.escudo >=  valorDano) {
            this.escudo -= valorDano;
        } else {

        int dano_restante = valorDano - this.escudo;
        this.escudo = 0;
        this.vida -= dano_restante;}
    }
    

    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    
    public void atacar(Heroi personagem) {
        personagem.RecebeDano(this);
        
    }

    public int acessoDano() {
        return this.dano;
    }

    public int acessoEscudo() {
        return this.escudo;
    }
    
    public String acessoNome() {
        return this.nome;
    }

    public int acessoVida() {
        return this.vida;
    }
    
}



