package mc322.jogo.cartas;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;

public abstract class Carta {
    protected String nome;
    protected String descricao;
    protected int custo;
    protected int opcaoCarta; //coloquei isso aqui para quebrar o encapsulamento
    protected Entidade personagem = null; // agregação em que tenho uma referência para quem possui a carta no momento

    public abstract String getNome();

    public abstract int getCusto();

    public abstract void usar(Entidade dono, Entidade alvo);

    public abstract String getDescricao();

    public abstract int getOpcaoCarta();

    public abstract RequisitoJogo cartaRequisito();

    public void setPersonagem(Entidade personagem) {
        this.personagem = personagem;
    }

}
