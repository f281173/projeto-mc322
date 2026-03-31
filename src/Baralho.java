import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe responsável por gerenciar a economia de cartas do jogo.
 * Controla o baralho principal de onde as cartas são puxadas, a "loja",
 * apresentada ao jogador, e a pilha de descarte para onde as cartas usadas vão.
 */

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

    // Prepara a 'lista de compra', caso o baralho esteja vazio, reseta e faz de
    // novo.
    public void criarPilhaCompra(int tamanho) {
        embaralhaBaralho();
        for (int i = 0; i < tamanho; i++) {
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

    // Insere uma nova carta no baralho
    public void adicionaBaralho(Carta carta) {
        this.baralho.add(carta);
    }

    // Pega as cartas do descarte e devolve ao baralho principal
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
        carta.setPersonagem(personagem);
        personagem.adicionaCard(carta);
    }

    public void imprimePilhaCompra() {
        for (int i = 0; i < this.pilhaCompra.size(); i++) {
            System.out.println(NEGRITO + i + RESET + "-" + AZUL + this.pilhaCompra.get(i).getNome() + RESET + " -  "
                    + this.pilhaCompra.get(i).getDescricao());
        }
    }

    public void adicionaPilhaDescarte(Carta carta) {
        this.pilhaDescarte.add(carta);
    }

    public void adicionaPilhaCompra(Carta carta) {
        this.pilhaCompra.add(carta);
    }

    // Pega as as cartas não compradas e volta ao baralho geral
    public void devolverCartasNaoCompradas() {

        this.baralho.addAll(this.pilhaCompra);
        this.pilhaCompra.clear();
    }

    public ArrayList<Carta> getBaralho() {
        return this.baralho;
    }

}
