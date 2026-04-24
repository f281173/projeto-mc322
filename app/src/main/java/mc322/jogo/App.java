package mc322.jogo;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;
import mc322.jogo.interfaceUsuario.Terminal;

/** Classe que serve como Ponto de Entrada para executar o Código. */
public class App {

    public static void main(String[] args) {
        InterfaceUsuario ui = new Terminal(); // por enquanto temos somente essa interface
        GameManager gm = new GameManager(ui);
        Dados.setGm(gm);
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            gm.prepararPartida();

            int escolha = ui.escolheJogarNovamente();

            if (escolha != 1) { // colocar as musicas aqui eu acho
                jogarNovamente = false;
                ui.mostrarMensagem(Cores.VERDE + "\nObrigado! Até a próxima aventura." + Cores.RESET);
            } else {
                ui.mostrarMensagem(Cores.CIANO + "\nReiniciando... Vamos lá!\n" + Cores.RESET);
            }

        }
    }
}
