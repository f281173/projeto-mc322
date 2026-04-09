package mc322.jogo.gerenciador;

import mc322.jogo.entidades.Inimigo;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe responsável por gerenciar a equipe de Inimigos.
 * Funciona como o "Técnico" do time inimigo, armazenando o catálogo
 * completo de monstros e sorteando a equipe que entrará na batalha atual.
 */
public class Oponente {
    private ArrayList<Inimigo> todosInimigos = new ArrayList<>();
    private ArrayList<Inimigo> inimigosEscolhidos = new ArrayList<>();

    /**
     * Método para adicionar um Vilão nono no nosso jogo
     * @param enemy novo vilão para o jogo.
     */
    public void adicionarInimigoTodos(Inimigo enemy) {
        this.todosInimigos.add(enemy);
    }

   /**
    * Método para gerar os inimigos para a batalha, baseado em 
    * dificuldade.
    * 
    * @param dificuldade escolha do usuário, que pode ser
    * 1 - fácil (apenas 1 inimigo)
    * 2 - médio (2 inimigos)
    * 3 - difícil (3 inimigos)
    */
    public void gerarInimigos(int dificuldade) {
        this.inimigosEscolhidos.clear();

        Collections.shuffle(this.todosInimigos);

        int quantidade = Math.min(dificuldade, todosInimigos.size());

        for (int i = 0; i < quantidade; i++) {
            this.inimigosEscolhidos.add(this.todosInimigos.get(i));
        }
    }

    /** método para imprimir os inimigos vivos que foram escolhidos naquele jogo */
    public void imprimeInimigosVivos() {
        for (int j = 0; j < this.inimigosEscolhidos.size(); j++) {
            if (this.inimigosEscolhidos.get(j).estaVivo()) {
                System.out.println(j + " - " + this.inimigosEscolhidos.get(j).getNome() + " (Vida: "
                        + this.inimigosEscolhidos.get(j).getVida() + ")");
            }
        }
    }

    /**
     * método para validar escolha do usuário
     * 
     * @param alvoEscolhido inteiro que é escolhido pelo usuário (representa um índice do vetor de inimigos)
     * @return um booleano para dizer se a escolha é valida ou não
     */
    public boolean validaEscolhaInimigo(int alvoEscolhido) {
        if (alvoEscolhido >= 0 && alvoEscolhido < this.inimigosEscolhidos.size() && this.inimigosEscolhidos.get(alvoEscolhido).estaVivo())
            return true;
        return false;
    }

    public ArrayList<Inimigo> getInimigosEscolhidos() {
        return this.inimigosEscolhidos;
    }

    /**
     * Método para verificar se ainda existem inimigos vivos no combate
     * 
     * @return booleano true se existir e false caso contrário.
     */
    public boolean temInimigosVivos() {
        for (Inimigo i : inimigosEscolhidos) {
            if (i.estaVivo()) {
                return true;
            }
        }
        return false;
    }

    public Inimigo getInimigo(int indice) {
        return this.getInimigosEscolhidos().get(indice);
    }

}
