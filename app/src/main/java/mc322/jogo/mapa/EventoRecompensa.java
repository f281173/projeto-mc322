package mc322.jogo.mapa;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.cartas.Carta;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;


public class EventoRecompensa extends Evento {
    private Carta cartaRecompensa;
    public EventoRecompensa(String nomeFase, String dialogo, Carta carta) {
        super(nomeFase, dialogo);
        this.cartaRecompensa = carta;
    }
    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        if (cartaRecompensa != null) {
            System.out.println(Cores.AZUL + "📜 Você encontrou uma nova carta: " + cartaRecompensa.getNome() + "!" + Cores.RESET);
            jogador.getHeroisEscolhidos().get(0).ganhaCarta(cartaRecompensa); 
        }
        return true;
    }
}