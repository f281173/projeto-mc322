package mc322.jogo.mapa;
import java.util.Scanner;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;


public class EventoEscolha extends Evento {
    public EventoEscolha(String nomeFase, String dialogo) {
        super(nomeFase, dialogo);
    }
    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        return true;
    }
}