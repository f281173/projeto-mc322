package mc322.jogo.observer;

import mc322.jogo.entidades.Entidade;

public interface Publisher {
    public void inscrever(Subscriber observador, Estados state);

    public void desinscrever(Subscriber observador, Estados state);

    public void notificar(Entidade observador, Estados state); // uma entdade é quem será notificado no final.
}
