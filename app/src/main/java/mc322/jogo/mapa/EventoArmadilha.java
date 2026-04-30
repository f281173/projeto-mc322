package mc322.jogo.mapa;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;

public class EventoArmadilha extends Evento {
    public EventoArmadilha(String nomeFase, String dialogo) {
        super(nomeFase, dialogo);
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, InterfaceUsuario ui, int dificuldade) {
        ui.mostrarEventoArmadilha();

        for (Heroi heroi : jogador.getHeroisEscolhidos()) {
            if (heroi.estaVivo())
                heroi.recebeDanoEfeito(15);
        }
        return jogador.temHeroisVivos(); // Se todos morrerem na queda, retorna false
    }
}