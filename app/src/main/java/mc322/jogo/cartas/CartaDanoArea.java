package mc322.jogo.cartas;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Inimigo;

/**
 * Carta herdada de Carta Dano para implementar a versão em área.
 * Essa carta recebe um vetor de inimigos para executar danos em todos
 * os inimigos presentes na batalha.
 */
public class CartaDanoArea extends CartaDano {

    public CartaDanoArea(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo, dano);
    }

    // Método exclusivo para bater em todos os inimigos vivos de uma vez
    public void usarEmArea(ArrayList<Inimigo> inimigos) {
        for (Inimigo alvo : inimigos) {
            if (alvo.estaVivo()) {
                alvo.recebeDano(this.acessoCartaDanoDano());
            }
        }
    }

    @Override
    public String usar(Entidade dono, Entidade alvo, ArrayList<Inimigo> inimigos) {
    /* vamos atacar inimigo por inimigo usando a lógica pronta de ataque que já temos efeitos de força e fraqueza */
        for (Inimigo inimigo : inimigos) {
            if (inimigo.estaVivo()) { // seria melhor ter um método em oponentes que entrega um vetor filtrado
                dono.ataque(inimigo, super.acessoCartaDanoDano());
            }

        }
        return Cores.VERMELHO + "\n⚔️ Você usou " + this.getNome() + " em " + " todos os inimigos "
                + " e causou dano! " + Cores.RESET;
    }

    @Override
    public RequisitoJogo cartaRequisito() {
        return RequisitoJogo.TODOS_INIMIGOS;
    }

}
