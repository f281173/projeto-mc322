package mc322.jogo.observer;

import mc322.jogo.entidades.Entidade;

public interface Subscriber {
    public void serNotificado(Estados state);

    public Entidade getDono();
}
