package mc322.jogo.gerenciador;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;

/**
 * Classe responsável por gerenciar a equipe de Heróis do usuário.
 * Armazena o catálogo de personagens disponíveis e gerencia a interface
 * de seleção de equipe no terminal.
 */

public class Jogador {

    private ArrayList<Heroi> todosHerois = new ArrayList<>();
    private ArrayList<Heroi> heroisEscolhidos = new ArrayList<>();

   /**
    * Método para carregar um Heroi presente em dados para dentro 
    * do objeto Jogador.
    * 
    * @param heroi Objeto Heroi que foi adicionado na batalha.
    */
    public void adicionarHeroiTodos(Heroi heroi) {
        this.todosHerois.add(heroi);
    }

    /**
     * Método para que o usuário possa escolher quais herois  já
     * criados em {@link Dados} vão fazer parte da batalha.
     * 
     * @param sc Objeto Scanner para leitura de dados do telado.
     */
    public void escolherHerois(Scanner sc) {
        System.out.println("Heróis Disponíveis:");
        for (int i = 0; i < todosHerois.size(); i++) {
            System.out.println("[" + i + "] " + Cores.CIANO + todosHerois.get(i).getNome() + Cores.RESET
                    + " - Vida: " + todosHerois.get(i).getVidaInicial()
                    + " - Energia: " + todosHerois.get(i).getEnergiaAtual()
                    + " - Escudo: " + todosHerois.get(i).getEscudo()
                    + " - Velocidade: " + todosHerois.get(i).getVelocidade());
        }

        System.out.print("\nQuantos heróis farão parte da sua equipe? (Máximo " + todosHerois.size() + "): ");
        int quant = sc.nextInt();
        quant = Math.min(quant, todosHerois.size());

        for (int i = 0; i < quant; i++) {
            boolean escolhaValida = false;
            while (!escolhaValida) {

                System.out.print("Digite o número do Herói " + (i + 1) + ": ");
                int escolha = sc.nextInt();

                if (escolha < 0 || escolha >= todosHerois.size()) {
                    System.out.println(Cores.VERMELHO + "Opção inválida! Escolha um número do catálogo." + Cores.RESET);
                } else {
                    Heroi selecionado = todosHerois.get(escolha); // olha se o herói já está na equipe
                    if (this.heroisEscolhidos.contains(selecionado)) {
                        System.out.println(
                                Cores.VERMELHO + "Esse herói já está na sua equipe! Escolha outro." + Cores.RESET);
                    } else {
                        this.heroisEscolhidos.add(selecionado);
                        System.out
                                .println(Cores.VERDE + selecionado.getNome() + " entrou para a equipe!" + Cores.RESET);
                        escolhaValida = true;

                    }
                }
            }

        }
    }

    public ArrayList<Heroi> getHeroisEscolhidos() {
        return this.heroisEscolhidos;
    }

    /**
     * Método para verificar se ainda existe algum heroi da batalha vivo.
     * @return booleano true para se existem e false caso contrário.
     */
    public boolean temHeroisVivos() {
        for (Heroi h : heroisEscolhidos) {
            if (h.estaVivo()) {
                return true;
            }
        }
        return false;
    }

}
