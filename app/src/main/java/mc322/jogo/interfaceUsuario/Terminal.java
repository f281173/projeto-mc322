package mc322.jogo.interfaceUsuario;

import java.util.Scanner;
import mc322.jogo.Cores;
import mc322.jogo.gerenciador.Prints;

public class Terminal  {
    Scanner sc = new Scanner(System.in);
    
    public void telaInicial() {
        /*lógica identica ao comeco() em Prints inicio do jogo */
        Prints.comeco();
    }

    public int escolheDificuldade() {
        /*lógica identica ao dificuldade() em Prints */
        Prints.dificuldade();

        /*vamos fazer a leitura do teclado com a dificuldade*/
        int dificuldade = this.validaDificuldade();
        sc.nextLine(); // verificar a necessidade dessa linha !

        System.out.println("\nA JORNADA VAI COMEÇAR!\n\n");

        return dificuldade;
    }

    /**
     * Método para forçar uma entrada correta do usuário.
     * Para o futuro seria interessante a gente impedir que o usuário digite
     * uma String ou Enter.
     * @return inteiro entre 1 a 3
     */
    private int validaDificuldade() {
        int dificuldade = sc.nextInt();

        while(dificuldade < 1 || dificuldade > 3) {
            System.out.println(Cores.VERMELHO + "ESCOLHA UM VALOR QUE CORRESPONDA AO NÍVEL DE DIFICULDADE !!" + Cores.RESET);
            System.out.print(Cores.NEGRITO + "Opção: " + Cores.RESET);
            dificuldade = sc.nextInt();
        }
        return dificuldade;
    }

    public void mostrarDialogoEvento(String nomeFase, String dialogo) {
        Prints.limparTela();

        System.out.println(nomeFase);
        Prints.imprimirLetraPorLetra(dialogo);
    }

}
