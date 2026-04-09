package mc322.jogo.gerenciador;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe responsável por gerenciar as principais impressões durante o jogo.
 * Essa classe não manipula nenhuma entidade.
 */
public class Prints {

    /**
     * Método responsável pela tela de início do jogo.
     */
    public void comeco() {
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + Cores.CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            "
                + Cores.RESET);
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);

    }

    /**
     * Método responsável pela tela do momento da batalha no Turno do Heroi.
     * 
     * @param heroiAtual Heroi que vai executar o ataque naquele momento.
     * @param herois     vetor com todos os Heróis escolhidos naquela batalha
     * @param inimigos   vetor com todo os inmigos escolhidos para aquela Batalha
     */
    public void status_batalha(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos) {
        heroiAtual.imprimeEfeitos();
        System.out.println();
        System.out.println(
                "\n" + Cores.NEGRITO + "=================== STATUS DA BATALHA ===================" + Cores.RESET);

        imprimeHerois(herois, heroiAtual);
        System.out.println(Cores.NEGRITO + "                           vs" + Cores.RESET);
        imprimeInimigosVivos(inimigos);

        System.out.println(Cores.NEGRITO + "=========================================================" + Cores.RESET);
    }

    /**
     * Método responsável por imprimir a energia do Heroi na tela
     * 
     * @param energia inteiro que representa o atritbuto energia do herói
     */
    public void energia(int energia) {
        System.out.println(Cores.AMARELO + "⚡ Energia: " + Cores.RESET + energia + " disponíveis");
    }

    /**
     * Método responsável por imprimir todas as informações do Inimigo no status de
     * batalha.
     * 
     * @param inimigos vetor com todos os inimigos escolhidos naquela Batalha.
     */
    public void imprimeInimigosVivos(ArrayList<Inimigo> inimigos) {
        for (Inimigo enemy : inimigos) {
            if (enemy.estaVivo()) {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + enemy.getVida() + "/" + enemy.getVidaInicial() + Cores.RESET
                        + " de vida"
                        + "  | "
                        + Cores.AZUL + "🛡️  " + enemy.getEscudo() + Cores.RESET + " de escudo"
                        + enemy.statusEfeitoForca() + " " + enemy.statusEfeitoFraqueza());
            } else {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    /**
     * Imprime as informações do Heroi no momento do seu turno.
     * 
     * @param herois     vetor com todos os herois escolhidos
     * @param heroiAtual héroi que vai executar a ação
     */
    public void imprimeHerois(ArrayList<Heroi> herois, Heroi heroiAtual) {
        for (Heroi h : herois) {

            String destaque = (h == heroiAtual) ? Cores.AMARELO + " ⬅️ [SEU TURNO]" + Cores.RESET : "";

            if (h.estaVivo()) {
                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + destaque + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + h.getVida() + "/" + h.getVidaInicial() + Cores.RESET
                        + " de vida  | "
                        + Cores.AZUL + "🛡️  " + h.getEscudo() + Cores.RESET + " de escudo"
                        + h.statusEfeitoForca() + " " + h.statusEfeitoFraqueza());
            } else {

                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    /**
     * Método responsável por gerenciar a tela do momento de compra de cartas de
     * cada Herói em seu turno.
     * 
     * @param limiteCompra    Limite de compras de cartas em um único turno
     * @param cartasCompradas quantidade de cartas que já foram compradas.
     */
    public void faseCompra(int limiteCompra, int cartasCompradas) {
        System.out.println(Cores.NEGRITO + "\n--- FASE DE COMPRA ---" + Cores.RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

    /**
     * Método responsável pela tela no ínicio da batalha, logo após a fase de
     * compra.
     */
    public void faseBatalha() {
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

    /**
     * Método que imprime a tela de fim de jogo.
     * 
     * @param player objeto que gerencia os heróis (necessário para verificar qual
     *               está vivo)
     * @param dj     objeto musica para trocar para uma outra música.
     */
    public void fimDeJogo(Jogador player, Musica dj) {
        if (!player.temHeroisVivos()) {
            dj.tocarMusica("../sons/I_need_sleep.wav");
            System.out.println(
                    Cores.NEGRITO + Cores.VERMELHO + "\n💀 BRUTAL!!! SUA EQUIPE FOI DERROTADA!" + Cores.RESET);
        } else {
            dj.tocarMusica("../sons/im_believer.wav");
            System.out.println(Cores.NEGRITO + Cores.VERDE + "\n🎉  PARABÉNS!!!   SUA EQUIPE VENCEU!" + Cores.RESET);
        }
    }

    public void dificuldade() {
        System.out.println("\n" + Cores.NEGRITO + "=================== CONFIGURAÇÃO DA PARTIDA ==================="
                + Cores.RESET);
        System.out.println("Escolha a dificuldade da batalha:");
        System.out.println(Cores.VERDE + "1 - Fácil (1 Inimigo)" + Cores.RESET);
        System.out.println(Cores.AMARELO + "2 - Médio (2 Inimigos)" + Cores.RESET);
        System.out.println(Cores.VERMELHO + "3 - Difícil (3 Inimigos)" + Cores.RESET);
        System.out.print(Cores.NEGRITO + "\nOpção: " + Cores.RESET);
    }

    public void jogarNovamente() {
        System.out.println("\n" + Cores.NEGRITO + "=======================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + "   Deseja jogar novamente? (1 - Sim / 2 - Não): " + Cores.RESET);
        System.out.println(Cores.NEGRITO + "=======================================" + Cores.RESET);
    }

}
