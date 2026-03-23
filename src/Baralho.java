import java.util.ArrayList;
import java.util.Collections;



public class Baralho {
    private ArrayList<Carta> baralho; 
    private ArrayList<Carta> pilhaCompra;
    private ArrayList<Carta> pilhaDescarte;
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String NEGRITO = "\u001B[1m";

    public Baralho() {
        this.baralho = new ArrayList<>();
        this.pilhaCompra = new ArrayList<>();
        this.pilhaDescarte = new ArrayList<>();
    }

    public void embaralhaBaralho() {
        Collections.shuffle(this.baralho);
    }

    
    public void criarPilhaCompra(int tamanho) {
        embaralhaBaralho();
        for(int i = 0; i < tamanho; i++) {
            if (this.baralho.isEmpty()) {
              this.resetaBaralho(); 
            }
            
            Carta carta = this.baralho.remove(this.baralho.size() - 1);
            adicionaPilhaCompra(carta);
        }

    }

    public int tamanhoPilha() {
        return this.pilhaCompra.size();
    }

    /* adiciona uma por uma cada carta do baralho com o json*/
    public void adicionaBaralho(Carta carta) {
        this.baralho.add(carta);
    }

    public void resetaBaralho() {
        int tamanho = this.pilhaCompra.size();

        while (tamanho > 0) {
            tamanho--;
            this.adicionaBaralho(this.pilhaCompra.remove(tamanho));
        }

        tamanho = this.pilhaDescarte.size();

        while (tamanho > 0) {
            tamanho--;
            this.adicionaBaralho(this.pilhaDescarte.remove(tamanho));
        }
    }

    public void compraCarta(Heroi personagem, int i) {
        Carta carta = this.pilhaCompra.remove(i);
        personagem.adiciona_card(carta);
    }

    public void imprimePilhaCompra() {
        for(int i = 0; i < this.pilhaCompra.size(); i++) {
            System.out.println(NEGRITO + i + RESET + "-" + AZUL + this.pilhaCompra.get(i).acessoNome() + RESET + " -  " + this.pilhaCompra.get(i).acessoDescricao());
        }
    }

    public void adicionaPilhaDescarte(Carta carta) {
        this.pilhaDescarte.add(carta);
    }

    public void adicionaPilhaCompra(Carta carta) {
        this.pilhaCompra.add(carta);
    }
    

    
    public void devolverCartasNaoCompradas() {
        
        this.baralho.addAll(this.pilhaCompra);
        this.pilhaCompra.clear();
    }
    
}
