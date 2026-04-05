package mc322.jogo.gerenciador;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

public class Prints {

;

    public void comeco() {
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + Cores.CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            " + Cores.RESET);
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);

    }

    public void status_batalha(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos) {
        System.out.println("\n" + Cores.NEGRITO + "=================== STATUS DA BATALHA ===================" + Cores.RESET);

        imprimeHerois(herois, heroiAtual);
        System.out.println(Cores.NEGRITO + "                           vs" + Cores.RESET);
        imprimeInimigosVivos(inimigos);

        System.out.println(Cores.NEGRITO + "=========================================================" + Cores.RESET);
    }

    public void energia(int energia) {
        System.out.println(Cores.AMARELO + "⚡ Energia: " + Cores.RESET + energia + " disponíveis");
    }

    public void imprimeInimigosVivos(ArrayList<Inimigo> inimigos) {
        for (Inimigo enemy : inimigos) {
            if (enemy.estaVivo()) {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + enemy.getVida() + "/" + enemy.getVidaInicial() + Cores.RESET + " de vida"
                        + "  | "
                        + Cores.AZUL + "🛡️  " + enemy.getEscudo() + Cores.RESET + " de escudo");
            } else {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    public void imprimeHerois(ArrayList<Heroi> herois, Heroi heroiAtual) {
        for (Heroi h : herois) {

            String destaque = (h == heroiAtual) ? Cores.AMARELO + " ⬅️ [SEU TURNO]" + Cores.RESET : "";

            if (h.estaVivo()) {
                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + destaque + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + h.getVida() + "/" + h.getVidaInicial() + Cores.RESET + " de vida  | "
                        + Cores.AZUL + "🛡️  " + h.getEscudo() + Cores.RESET + " de escudo");
            } else {

                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    public void faseCompra(int limiteCompra, int cartasCompradas) {
        System.out.println(Cores.NEGRITO + "\n--- FASE DE COMPRA ---" + Cores.RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

    public void faseBatalha() {
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

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
