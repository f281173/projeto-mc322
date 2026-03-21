public class Inimigo extends  Entidade {
    private CartaDano carta;


    public Inimigo(String nome, int vida, int escudo, int vida_inicial) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vida_inicial = vida_inicial;
    }

@Override
    public Carta encontraCarta(String nomeCarta) {
        return this.carta;
    }

    public void adicionaCard(CartaDano carta) {
       this.carta = carta;
    }

@Override
    public void recebeDano(Entidade  personagem, CartaDano carta) {
        
        int valorDano = carta.acessoCartaDanoDano();

        if (this.escudo >=  valorDano) {
            this.escudo -= valorDano;
        } else {

        int dano_restante = valorDano - this.escudo;
        this.escudo = 0;
        this.vida -= dano_restante;}
    }

@Override 
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    public void atacar(Heroi personagem) {
        personagem.recebeDano(this, this.carta);
        
    }

    public int acessoDano() {
        return this.carta.acessoCartaDanoDano();
    }

@Override
    public void ganhaEscudo(CartaEscudo cartaEscudo) {
        this.escudo += 0;
    }


@Override     
    public int acessoEscudo() {
        return this.escudo;
    }

@Override
    public String acessoNome() {
        return this.nome;
    }

@Override
    public int acesso_vida() {
        return this.vida;
    }
    
    @Override
    public int acesso_vidainicial(){
        return this.vida_inicial;
    }
}



