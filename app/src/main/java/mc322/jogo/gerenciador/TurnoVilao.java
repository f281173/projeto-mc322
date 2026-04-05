package mc322.jogo.gerenciador;

import java.util.ArrayList;
import java.util.Random;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por executar o turno do inimigo
 * Avalia o campo de batalha, escolhe um alvo e executa a ação da carta do topo.
 */

public class TurnoVilao {
    private GameManager gm;

    public TurnoVilao(GameManager gm) {
        this.gm = gm;
    }

    // Executa o turno do personagem e ataca um herói aleatoriamente
    public void jogar(Inimigo enemy, ArrayList<Heroi> herois) {

        System.out
                .println("\n" + Cores.VERMELHO + Cores.NEGRITO + "================== TURNO DO INIMIGO ==================" + Cores.RESET);

        /* notificar a entidade que é inicio do turno */
        gm.notificar(enemy, Estados.INICIO_DE_TURNO);

        System.out.println(Cores.VERMELHO + "O " + enemy.getNome() + " está atacando..." + Cores.RESET);

        ArrayList<Heroi> heroisVivos = new ArrayList<>();
        for (Heroi h : herois) {
            if (h.estaVivo()) {
                heroisVivos.add(h);
            }
        }

        if (!heroisVivos.isEmpty()) {

            // sorteia um heroi pra bater
            Random gerador = new Random();
            Heroi alvo = heroisVivos.get(gerador.nextInt(heroisVivos.size()));

            enemy.atacar(alvo);

            if (enemy.getTipoCarta() == 0) {

                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() + "' e causou " + Cores.VERMELHO
                        + enemy.getDano() + Cores.RESET + " de dano no " + alvo.getNome() + "!");

            } else if (enemy.getTipoCarta() == 1) {
                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() + "' e ganhou " + Cores.AZUL
                        + enemy.getEscudo() + Cores.RESET + " de escudo!");

            } else if (enemy.getTipoCarta() == 2) {
                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() + Cores.AZUL + " ativou o efeito");
            }
        }
        /* tenho que publicar que o turno do inimigo terminou */
        gm.notificar(enemy, Estados.FIM_DE_TURNO);

        System.out.println(Cores.VERMELHO + Cores.NEGRITO + "======================================================\n" + Cores.RESET);
    }
}
