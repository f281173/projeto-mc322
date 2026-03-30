public interface Publisher {
    public void inscrever(Subscriber observador, Estados state);

    public void desinscrever(Subscriber observador, Estados state);

    public void notificar(Entidade observador, Estados state); // uma entdade é quem será notificado no final.
}
