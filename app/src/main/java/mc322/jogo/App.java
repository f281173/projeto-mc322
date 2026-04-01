package mc322.jogo;

import mc322.jogo.gerenciador.Prints;
import mc322.jogo.gerenciador.GameManager;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Prints tela = new Prints();
        GameManager gm = new GameManager();
        Dados.setGm(gm);
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            gm.prepararPartida(sc, tela);

            tela.jogarNovamente();
            int escolha = sc.nextInt();

            if (escolha != 1) {
                jogarNovamente = false;
                System.out.println(Prints.VERDE + "\nObrigado! Até a próxima aventura." + Prints.RESET);
            } else {
                System.out.println(Prints.CIANO + "\nReiniciando... Vamos lá!\n" + Prints.RESET);

            }

        }
    }
}
