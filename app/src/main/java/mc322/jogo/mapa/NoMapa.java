package mc322.jogo.mapa;

import java.util.ArrayList;

public class NoMapa {
    private Evento evento;
    private ArrayList<NoMapa> proximosCaminhos; // A lista de para onde eu posso ir

    public NoMapa(Evento evento) {
        this.evento = evento;
        this.proximosCaminhos = new ArrayList<>();
    }

    // Método para ligar um nó ao outro!
    public void adicionarCaminho(NoMapa proximoNodo) {
        this.proximosCaminhos.add(proximoNodo);
    }

    public Evento getEvento() {
        return this.evento;
    }

    public ArrayList<NoMapa> getProximos() {
        return this.proximosCaminhos;
    }

    public boolean FimDeJogo() {
        return this.proximosCaminhos.isEmpty();
    }
}