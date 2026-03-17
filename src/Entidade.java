public abstract  class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    
    public  abstract void recebeDano(Entidade personagem, CartaDano carta);

    public  abstract boolean estaVivo ();

    public abstract int acessoEscudo();

    public  abstract void ganhaEscudo(CartaEscudo cartaEscudo);

    public  abstract String acessoNome();

    public  abstract Carta encontraCarta(String nomeCarta);
    
    public abstract void adiciona_card(CartaDano carta);

}   
