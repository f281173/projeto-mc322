import java.util.ArrayList;
import java.util.Random;

/**
 * Classe responsável por executar o turno do inimigo
 * Avalia o campo de batalha, escolhe um alvo e executa a ação da carta do topo.
 */

public class TurnoVilao {
    private GameManager gm;

    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";

    public TurnoVilao(GameManager gm) {
        this.gm = gm;
    }

    // Executa o turno do personagem e ataca um herói aleatoriamente
    public void jogar(Inimigo enemy, ArrayList<Heroi> herois) {

        System.out
                .println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);

        /* notificar a entidade que é inicio do turno */
        gm.notificar(enemy, Estados.INICIO_DE_TURNO);

        System.out.println(VERMELHO + "O " + enemy.getNome() + " está atacando..." + RESET);

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

            enemy.atacar(alvo, heroisVivos);
            

            if (enemy.getTipoCarta() == 0) {
                if (enemy.getUltimaCarta() instanceof CartaDanoArea) {
                 System.out.println(VERMELHO + "💥 É UM ATAQUE EM ÁREA! Toda a sua equipe tomou " + enemy.getDano() + " de dano!" + RESET);
            } else{
                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() + "' e causou " + VERMELHO
                        + enemy.getDano() + RESET + " de dano no " + alvo.getNome() + "!");}

            } else if (enemy.getTipoCarta() == 1) {
                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() + "' e ganhou " + AZUL
                        + enemy.getEscudo() + RESET + " de escudo!");

            } else if (enemy.getTipoCarta() == 2) {
                Carta ultima = enemy.getUltimaCarta();
                CartaEfeito carta_efeito = (CartaEfeito) ultima;

                TiposEfeitos tipo = carta_efeito.getTipoEfeito();
                int turnos = carta_efeito.getAcumulo();

                System.out.println(enemy.getNome() + " usou '" + enemy.getNomeCarta() +  " e aplicou um efeito de " + tipo + " em " + alvo.getNome() +  " que durará " + turnos + " turnos!" + RESET);
        }

        /* tenho que publicar que o turno do inimigo terminou */
        gm.notificar(enemy, Estados.FIM_DE_TURNO);

        System.out.println(VERMELHO + NEGRITO + "======================================================\n" + RESET);
    }
}

}