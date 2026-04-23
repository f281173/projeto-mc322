package mc322.jogo.mapa;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;

public class EventoArmadilha extends Evento {
    public EventoArmadilha(String nomeFase, String dialogo) { super(nomeFase, dialogo); }
    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        System.out.println(Cores.VERMELHO + "🕳️ Você caiu num buraco com espinhos! Perdeu 15 de vida." + Cores.RESET);
        for (Heroi heroi : jogador.getHeroisEscolhidos()) {
            if (heroi.estaVivo()) heroi.recebeDanoEfeito(15);
        }
        return jogador.temHeroisVivos(); // Se todos morrerem na queda, retorna false
    }
}