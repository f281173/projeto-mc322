package mc322.jogo.interfaceUsuario;

import java.util.ArrayList;
import java.util.Scanner;
import mc322.jogo.Cores;
import mc322.jogo.cartas.Carta;
import mc322.jogo.gerenciador.Prints;
import mc322.jogo.mapa.NoMapa;

public class Terminal implements InterfaceUsuario {
    Scanner sc = new Scanner(System.in);

    public void telaInicial() {
        /* lógica identica ao comeco() em Prints inicio do jogo */
        Prints.comeco();
    }

    public int escolheDificuldade() {
        /* lógica identica ao dificuldade() em Prints */
        Prints.dificuldade();

        /* vamos fazer a leitura do teclado com a dificuldade */
        int dificuldade = this.validaDificuldade();
        sc.nextLine(); // verificar a necessidade dessa linha !

        System.out.println("\nA JORNADA VAI COMEÇAR!\n\n");

        return dificuldade;
    }

    /**
     * Método para forçar uma entrada correta do usuário.
     * Para o futuro seria interessante a gente impedir que o usuário digite
     * uma String ou Enter.
     * 
     * @return inteiro entre 1 a 3
     */
    private int validaDificuldade() {
        int dificuldade = sc.nextInt();

        while (dificuldade < 1 || dificuldade > 3) {
            System.out.println(
                    Cores.VERMELHO + "ESCOLHA UM VALOR QUE CORRESPONDA AO NÍVEL DE DIFICULDADE !!" + Cores.RESET);
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

    public void mostrarMapa(String nomeFaseAtual) {
        Prints.PrintaMapa(nomeFaseAtual);
    }

    public void mostrarEventoBar() {
        System.out.println(Cores.VERDE + "🍺 Você bebeu uma poção de lama no bar! Recuperou 30 de vida." + Cores.RESET);
    }

    public void mostrarEventoArmadilha() {
        System.out.println(Cores.VERMELHO + "🕳️ Você caiu num buraco com espinhos! Perdeu 15 de vida." + Cores.RESET);
    }

    public void mostrarRecompensaCarta(Carta carta) {
        System.out.println(Cores.AZUL + "📜 Você encontrou uma nova carta: " + carta.getNome() + "!" + Cores.RESET);
    }

    public void mostrarNovoCompanheiro(String nomeCompanheiro) {
        System.out.println(Cores.AZUL + " Princesa " + nomeCompanheiro + " se juntou à sua equipe!" + Cores.RESET);
    }

    public void fimDeJogo() {
        System.out.println(Cores.VERDE + Cores.NEGRITO + "🏆 FIM DE JOGO! VOCÊ ZEROU A CAMPANHA!" + Cores.RESET);
    }

    public int escolherCaminhoMapa(ArrayList<NoMapa> caminhos) {
        System.out.println("\nPara onde você quer ir agora?");
        for (int i = 0; i < caminhos.size(); i++) {
            System.out.println(i + " - " + caminhos.get(i).getEvento().getNomeFase());
        }

        System.out.print(Cores.NEGRITO + "Sua escolha: " + Cores.RESET);
        int escolha = sc.nextInt();

        while (escolha < 0 || escolha >= caminhos.size()) {
            System.out.println(Cores.VERMELHO + "Opção inválida! Digite um número do menu." + Cores.RESET);
            escolha = sc.nextInt();
        }

        return escolha;

    }

}
