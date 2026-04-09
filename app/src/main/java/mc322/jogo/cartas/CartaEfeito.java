package mc322.jogo.cartas;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe concreta que constrói Cartas do tipo efeito. Essas Cartas são responsáveis,
 * por aplicar diversos efeitos no jogo, os quais podem ser vistos todos implementados ate
 * aqui em {@link TiposEfeitos}
 */
public class CartaEfeito extends Carta {
    protected Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, Efeito efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.tipo = TiposCartas.EFEITO;
        this.efeito = efeito;
    }

    public Efeito getEfeito() {
        return this.efeito;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getCusto() {
        return this.custo;
    }

    @Override
    public String usar(Entidade dono, Entidade alvo, ArrayList<Inimigo> inimigos) {
        alvo.aplicarEfeito(this.efeito);
        return Cores.AZUL + "\n Você ativou " + this.getNome() + " e aplicou o " + this.getNome() + " : " + this.getDescricao() + " em " + alvo.getNome()+ Cores.RESET;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public TiposCartas getTipoCarta() {
        return this.tipo;
    }

    @Override
    public RequisitoJogo cartaRequisito() {
        return this.getEfeito().requisitoEfeito();
    }
}
