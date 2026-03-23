import java.util.ArrayList;

/**
 * Classe responsável por representar os Heróis controlados pelo jogador.
 * Herda as características básicas de Entidade, mas adiciona mecânicas 
 * exclusivas de gerenciamento de Energia e uma Mão de Cartas individual.
 */

public class Heroi extends Entidade {
    private int energia;
    private ArrayList<Carta> maoJogador; 
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String NEGRITO = "\u001B[1m";


    public Heroi(String nome, int vida, int escudo, int energia, int vida_inicial, int velocidade, boolean turno) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.vida_inicial = vida_inicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.maoJogador = new ArrayList<>();
    }


    
//Busca uma carta na mão do jogador pelo nome e retorna o seu índice (posição).
    public int encontraNome(String nomeCarta) {
        int i = 0;

        while (i < this.maoJogador.size()) {  
            if (this.maoJogador.get(i).acessoNome().equals(nomeCarta))
                return i;
            i++;
        }
        return -1;
    }

 //Utiliza o método encontraNome para buscar e retornar o objeto Carta exato que está na mão.
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


//Adiciona uma carta recém-comprada da loja pra mão do herói.
    public void adiciona_card(Carta carta) {
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

@Override
    public int acesso_vida() {
        return this.vida;
    }

    public void imprimeCartas() {
        for (int i = 0; i < this.maoJogador.size(); i++) {
            System.out.println( NEGRITO + i + RESET +  "-" + AZUL + this.maoJogador.get(i).acessoNome() + RESET + " -  " + this.maoJogador.get(i).acessoDescricao());
        }
    }

    public ArrayList<Carta> getMaoJogador() {
        return this.maoJogador;
    }

//Remove da mão e bota numa pilha de descarte
    public void removeCartaMaoJogador(Baralho baralho, int i) {
        Carta carta = this.maoJogador.remove(i);
        baralho.adicionaPilhaDescarte(carta);
    }

//Limpa a mão do herói, com aquelas q ele n usou. Manda pra pilha de descarte
    public void resetaMaoJogador(Baralho baralho) {
        int tamanho = this.maoJogador.size();

        while (tamanho > 0) {
            tamanho--;
            baralho.adicionaPilhaDescarte(this.maoJogador.remove(tamanho));
        } 
    }


    public boolean  maoVazia() {
        if (this.maoJogador.size() == 0)
            return true;
        return false;
    }

@Override
    public int acesso_vidainicial(){
        return this.vida_inicial;
    }


@Override
    public int acessoVelocidade() { 
        return this.velocidade; }

//Pra ver se já jogou ou não naquela rodada     
@Override
    public boolean acessoturno() { 
    return this.turno; }


@Override
    public void verificaseAtacou(boolean status){
            this.turno = status; }

}
