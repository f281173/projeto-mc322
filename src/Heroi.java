import java.util.ArrayList;

public class Heroi extends Entidade {
    private int energia;
    private ArrayList<CartaDano> maoJogador; 

    public Heroi(String nome, int vida, int escudo, int energia) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.maoJogador = new ArrayList<>();
    }

    public int encontraNome(String nomeCarta) {
        int i = 0;

        while (i < this.maoJogador.size()) {  
            if (this.maoJogador.get(i).acessoNome().equals(nomeCarta))
                return i;
            i++;
        }
        return -1;
    }

@Override
    public Carta encontraCarta(String nomeCarta) {
        int i = encontraNome(nomeCarta);
        Carta carta = this.maoJogador.get(i);
        return carta;
    }

@Override    
    public void recebeDano(Entidade personagem, CartaDano carta) {

        if (this.escudo >= carta.acessoCartaDanoDano()) {
            this.escudo -= carta.acessoCartaDanoDano();
        } else {

            int danoRestante = carta.acessoCartaDanoDano() - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }

    }

@Override
    public void ganhaEscudo(CartaEscudo cartaEscudo) {
        this.escudo = cartaEscudo.acessoEscudoGanho();
    }

@Override    
    public boolean estaVivo() {
        if (this.vida <= 0) {
            return false;
        } else {
            return true;
        }
    }

@Override
    public void adiciona_card(CartaDano carta) {
        this.maoJogador.add(carta);
}

@Override 
    public int acessoEscudo() {
        return this.escudo;
    }

    public int acessoEnergia() {
        return this.energia;
    }

    public void resetarEscudo() {
        this.escudo = 0;
    }

@Override
    public String acessoNome() {
        return this.nome;
    }

    public int acessoVida() {
        return this.vida;
    }

    public void imprimeCartas() {
        for (int i = 0; i < this.maoJogador.size(); i++) {
            System.out.println(i + "-" + this.maoJogador.get(i).acessoNome() + " -  " + this.maoJogador.get(i).acessoDescricao());
        }
    }

    public ArrayList<CartaDano> getMaoJogador() {
        return this.maoJogador;
    }

    
    public void resetaMaoJogador() {
        this.maoJogador = new ArrayList<>();
    }
}
