package mc322.jogo.mapa;

import mc322.jogo.cartas.Carta;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;

public class EventoRecompensa extends Evento {
    private Carta cartaRecompensa;

    public EventoRecompensa(String nomeFase, String dialogo, Carta carta) {
        super(nomeFase, dialogo);
        this.cartaRecompensa = carta;
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, InterfaceUsuario ui, int dificuldade) {
        if (cartaRecompensa != null) {
            ui.mostrarRecompensaCarta(cartaRecompensa);
            jogador.getHeroisEscolhidos().get(0).ganhaCarta(cartaRecompensa);
        }
        return true;
    }
}