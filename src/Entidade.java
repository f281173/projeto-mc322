public abstract  class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int vida_inicial;
    protected int velocidade;
    protected boolean turno;
    
    public  abstract void recebeDano(Entidade personagem, CartaDano carta);

    public  abstract boolean estaVivo ();

    public abstract int acessoEscudo();

    public  abstract void ganhaEscudo(CartaEscudo cartaEscudo);

    public  abstract String acessoNome();

    public  abstract int acesso_vida();

    public  abstract int acesso_vidainicial();

    public abstract  int acessoVelocidade();
        
    public abstract boolean acessoturno();  
      
    public abstract  void verificaseAtacou(boolean status);  
        

}   
