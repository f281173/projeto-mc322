package mc322.jogo.cartas;

import mc322.jogo.entidades.Heroi;
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

    public Baralho() {
        this.baralho = new ArrayList<>();
        this.pilhaCompra = new ArrayList<>();
        this.pilhaDescarte = new ArrayList<>();
    }

    public Baralho(Baralho copiaBaralho)  {
        this.baralho = new ArrayList<>(copiaBaralho.baralho); //temos que ver essa cópia é de fato uma cópia profunda (novos objetos em cada posição do vetor)
        this.pilhaCompra = new ArrayList<>();
        this.pilhaDescarte = new ArrayList<>();
    }

    public void embaralhaBaralho() {
        Collections.shuffle(this.baralho);
    }

    /**
     *  Método para gerenciar as cartas que o Herói pode escolher para usar.
     * 
     * @param tamanho inteiro com o tamanho de cartas que podem ser compradas
     */
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

    /**
     * Método para acessar o tamanho da pilha de compra disponível para o usuário comprar
     * de cartas.
     * 
     * @return tamanho da pilha.
     */
    public int tamanhoPilha() {
        return this.pilhaCompra.size();
    }

    /**
     * Método para inserir novas Cartas no Baralho pessoal do personagem.
     * 
     * @param carta objeto da Classe {@link Carta}
     */
    public void adicionaBaralho(Carta carta) {
        this.baralho.add(carta);
    }

    /**
     * Método responsável por pegar todas as cartas usadas ou apenas compradas ao final do turno e colocar
     * de volta ao baralho inicial.
     */
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
        personagem.recebeCarta(carta);;
    }

    public ArrayList<Carta> getPilhaCompra() {
        return this.pilhaCompra;
    }

    public void adicionaPilhaDescarte(Carta carta) {
        this.pilhaDescarte.add(carta);
    }

    public void adicionaPilhaCompra(Carta carta) {
        this.pilhaCompra.add(carta);
    }

    /**
     * Método responsável por pegar todas as cartas que não foram usadas mas foram
     * compradas para voltar  para o baralho.
     */
    public void devolverCartasNaoCompradas() {

        this.baralho.addAll(this.pilhaCompra);
        this.pilhaCompra.clear();
    }

    public ArrayList<Carta> getBaralho() {
        return this.baralho;
    }

}
