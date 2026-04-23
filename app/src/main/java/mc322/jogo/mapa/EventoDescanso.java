package mc322.jogo.mapa;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;


public class EventoDescanso extends Evento {
    public EventoDescanso(String nomeFase, String dialogo) { super(nomeFase, dialogo); }
    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        System.out.println(Cores.VERDE + "🍺 Você bebeu uma poção de lama no bar! Recuperou 30 de vida." + Cores.RESET);
        for (Heroi heroi : jogador.getHeroisEscolhidos()) {
            if (heroi.estaVivo()) heroi.curar(30);
        }
        return true;
    }
}