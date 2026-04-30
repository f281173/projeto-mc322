package mc322.jogo.mapa;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;

public class EventoEscolha extends Evento {
    public EventoEscolha(String nomeFase, String dialogo) {
        super(nomeFase, dialogo);
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, InterfaceUsuario ui, int dificuldade) {
        return true;
    }
}