package mc322.jogo.entidades;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;

/**
 * Classe responsável por gerenciar as cartas na mão do jogador
 * durante um turno. Entre seus deveres é de mostrar as cartas na mão do jogador
 * jogar as cartas não usadas para a pilha de descarte e de comprar cartas.
 */
public class MaoJogador {
    private ArrayList<Carta> mao;
    private Baralho baralhoMaoJogador;



    public MaoJogador(Baralho baralho) {
        this.baralhoMaoJogador = baralho; // passo a referencia do baralho do jogador para ser manipulado.
        this.mao = new ArrayList<>();
    }

    /**
     * Método para colocar uma carta no vetor que representa a mão do jogador.
     * 
     * @param cartaEscolhida objeto Carta que vai para a mão do jogador.
     */
    public void ganhaCarta(Carta cartaEscolhida) {
        this.getMaoJogador().add(cartaEscolhida);
    }

    public void imprimeCartas() {
        if (this.maoVazia()) {
            System.out.println("A sua mão está Vazia !!");
        }

        /*como a mão não está vazia faz sentido imprimir as cartas */
        for (int i = 0; i < this.getMaoJogador().size(); i++) {
            System.out.println(Cores.NEGRITO + i + Cores.RESET + "-" + Cores.AZUL + this.getMaoJogador().get(i).getNome() + Cores.RESET + " -  "
                    + this.getMaoJogador().get(i).getDescricao());
        }
    }    

    /**
     * Método para jogar as cartas da mão do jogador para a pilha de descarte do Baralho.
     * 
     * @param 
     * @return
     */
    public Carta removeCartaMaoJogador(int i) {
        if (i >= this.mao.size()) {
            System.out.println("Voce escolheu uma carta inválida !! Tente novamente");
            return null; //isso nunca vai acontecer, mas ainda deixo aqui por segurança (por enquanto)
        }
        /*escolheu uma opção válida ! */
        Carta carta = this.getMaoJogador().remove(i);
        this.getBaralhoMaoJogador().adicionaPilhaDescarte(carta);
        return carta;
    }

    /**
     * Método executado todo final de turno para devolver todas as cartas não jogadas
     * da mão do jogador para o seu Baralho.
     * 
     * @param baralho baralho pessoal do Heroi, que tem o atributo MãoJogador.
     */
   public void limpaMaoJogador(Baralho baralho) {
        int tamanho = this.getMaoJogador().size();

        while (tamanho > 0) {
            tamanho--;
            baralho.adicionaPilhaDescarte(this.getMaoJogador().remove(tamanho));
        }
    }

    public boolean maoVazia() {
        if (this.getMaoJogador().size() == 0)
            return true;
        return false;
    }

    public ArrayList<Carta> getMaoJogador() {
        return this.mao;
    }

    public Carta devolveCarta(int indice) {
        return this.mao.get(indice);
    }

    public Baralho getBaralhoMaoJogador() {
        return this.baralhoMaoJogador;
    }

    /**
     * Método para verificar se o índice escolhido entre as opções
     * do usuário é um índice valido que de fato escolhe uma Carta.
     * 
     * @param i indice escolhido pelo usuário.
     * @return booleano para dar a resposta se foi validado ou não.
     */
    public boolean isIndiceValido(int i) {
        if (i < this.mao.size())
            return true;
        return false;
    }
}
