import java.util.ArrayList;
import java.util.Random;

/**
 * Classe responsável por executar o turno do inimigo
 * Avalia o campo de batalha, escolhe um alvo  e executa a ação da carta do topo.
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


//Executa o turno do personagem e ataca um herói aleatoriamente
    public void jogar(Inimigo enemy, ArrayList<Heroi> herois){
        
        System.out.println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);

        /*notificar a entidade que é inicio do turno*/
        gm.notificar(enemy, Estados.INICIO_DE_TURNO);

        System.out.println(VERMELHO + "O " + enemy.acessoNome() + " está atacando..." + RESET);
        
        ArrayList<Heroi> heroisVivos = new ArrayList<>();
        for (Heroi h : herois) {
            if (h.estaVivo()) {
                heroisVivos.add(h);
            }
        }

        if (!heroisVivos.isEmpty()) {
            
            //sorteia um heroi pra bater
            Random gerador = new Random();
            Heroi alvo = heroisVivos.get(gerador.nextInt(heroisVivos.size()));


            enemy.atacar(alvo);

            if (enemy.acessoTipoCarta() == 0) {

                System.out.println(enemy.acessoNome() + " usou '" + enemy.acessoNome_Carta() + "' e causou " + VERMELHO + enemy.acessoDano() + RESET + " de dano no " + alvo.acessoNome() + "!");
            
            } else if (enemy.acessoTipoCarta() == 1) {
                System.out.println(enemy.acessoNome() + " usou '" + enemy.acessoNome_Carta() + "' e ganhou " + AZUL + enemy.acessoEscudo() + RESET + " de escudo!");
            
            } else if (enemy.acessoTipoCarta() == 2) {
                System.out.println(enemy.acessoNome() + " usou '" + enemy.acessoNome_Carta() + "' e ganhou " + AZUL + "ativou o efeito");
            }
        }

        System.out.println(VERMELHO + NEGRITO + "======================================================\n" + RESET);
    }
}

