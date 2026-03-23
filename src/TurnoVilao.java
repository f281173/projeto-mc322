import java.util.ArrayList;
import java.util.Random;

public class TurnoVilao {
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";



    public void jogar(Inimigo enemy, ArrayList<Heroi> herois){
        
        System.out.println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);
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

            if (enemy.acessotipo_carta() == 0) {

                System.out.println(enemy.acessoNome() + " usou '" + enemy.acessoNome_Carta() + "' e causou " + VERMELHO + enemy.acessoDano() + RESET + " de dano no " + alvo.acessoNome() + "!");
            } else {
                System.out.println(enemy.acessoNome() + " usou '" + enemy.acessoNome_Carta() + "' e ganhou " + AZUL + enemy.acessoEscudo() + RESET + " de escudo!");
            }
        }

        System.out.println(VERMELHO + NEGRITO + "======================================================\n" + RESET);
    }
}

