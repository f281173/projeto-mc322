package mc322.jogo.mapa;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;

public class EventoDescanso extends Evento {
    public EventoDescanso(String nomeFase, String dialogo) {
        super(nomeFase, dialogo);
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, InterfaceUsuario ui, int dificuldade) {
        ui.mostrarEventoBar();

        for (Heroi heroi : jogador.getHeroisEscolhidos()) {
            if (heroi.estaVivo())
                heroi.curar(30);
        }
        return true;
    }
}