import java.util.ArrayList;
import java.util.Collections;

public class Inimigo extends  Entidade {

    private ArrayList<Carta> deck_inimigo; 
    private Carta ultimaCartaUsada;

    public Inimigo(String nome, int vida, int escudo, int vida_inicial, int velocidade, boolean turno) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vida_inicial = vida_inicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.deck_inimigo = new ArrayList<>();
    }



    public void adicionaCard(Carta carta) {
       this.deck_inimigo.add(carta);
    }

    public void embaralhaBaralho() {
        Collections.shuffle(this.deck_inimigo);
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
        if (this.deck_inimigo.size() > 0) {
            this.ultimaCartaUsada = this.deck_inimigo.remove(0);

             if (this.ultimaCartaUsada.acessopcaocarta() == 0) {
                CartaDano cartadano = (CartaDano) this.ultimaCartaUsada;
                personagem.recebeDano(this, cartadano);
            } else {
                CartaEscudo cartaescudo = (CartaEscudo) this.ultimaCartaUsada;
                this.ganhaEscudo(cartaescudo);
            }
            this.deck_inimigo.add(this.ultimaCartaUsada);
        }
        
    }



    public int acessoDano() {
         if (this.ultimaCartaUsada != null && this.ultimaCartaUsada.acessopcaocarta() == 0) {
            CartaDano cartadano = (CartaDano) this.ultimaCartaUsada;
            return cartadano.acessoCartaDanoDano();
        }

        return 0;
    }


    public String acessoNome_Carta() {
        return this.ultimaCartaUsada.acessoNome();
    }


    public int acessotipo_carta() {
        return this.ultimaCartaUsada.acessopcaocarta();
    } 
        

@Override
    public void ganhaEscudo(CartaEscudo cartaEscudo) {
        this.escudo = cartaEscudo.acessoEscudoGanho(); 
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


    public void transforma_Deck(ArrayList<Carta> cartas) {
        this.deck_inimigo = cartas;
    }


@Override
    public int acessoVelocidade() { 
        return this.velocidade; }

@Override
    public boolean acessoturno() { 
    return this.turno; }


@Override
    public void verificaseAtacou(boolean status){
            this.turno = status; }
}



