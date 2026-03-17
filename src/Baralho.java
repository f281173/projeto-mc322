import java.util.ArrayList;

public class Baralho {
    private static ArrayList<Carta> baralho; //quero ter um baralho só na memória
    private ArrayList<Carta> pilhaCompra;
    private ArrayList<Carta> pilhaDescarte;

    public Baralho() {
        this.pilhaCompra = new ArrayList<>();
        this.pilhaDescarte = new ArrayList<>();
    }

    public void adicionaBaralho(Carta carta) {
        baralho.add(carta);
    }

    public void resetaPilhaCompra() {
        this.pilhaCompra = new ArrayList<>(baralho);
    }

    public Carta compraCarta(Heroi personagem, int i) {
        personagem.getMaoJogador().adiciona_card(this.pilhaCompra.get(i));
        


    }



    
    //preciso de imprime carta 3 ultimas
    
}
