package mc322.jogo;

import java.util.Scanner;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Prints;

/** Classe que serve como Ponto de Entrada para executar o Código. */
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

            if (escolha != 1) {  //colocar as musicas aqui eu acho
                jogarNovamente = false;
                System.out.println(Cores.VERDE + "\nObrigado! Até a próxima aventura." + Cores.RESET);
            } else {
                System.out.println(Cores.CIANO + "\nReiniciando... Vamos lá!\n" + Cores.RESET);

            }

        }
    }
}
