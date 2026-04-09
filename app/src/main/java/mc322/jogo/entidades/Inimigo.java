package mc322.jogo.entidades;

import java.util.ArrayList;

import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.EfeitoForca;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por representar os adversários do jogo.
 * Herda de Entidade e possui uma mecânica de combate automática baseada
 * em um sistema de ações pré-definido.
 * 
 * @author Arthur Nascimento
 * @author Felipe Garcia
 */

public class Inimigo extends Entidade {

    /** Vetor com todas as ações possíveis para cada Inimigo. */
    private ArrayList<AcaoInimigo> sistemaAcoes;

    public Inimigo(String nome, int vida, int escudo, int vidaInicial, int velocidade, boolean turno, GameManager gm,
            ArrayList<AcaoInimigo> sistemaAcoes) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.sistemaAcoes = sistemaAcoes;
        this.gm = gm;
        this.listaEfeitos = new ArrayList<>();
    }

    /**
     * Método para adicionar uma nova ação ao atributo sistemadeAcoes.
     * 
     * @param acao uma nova ação entre as implementadas na interface
     *             {@link AcaoInimigo}
     */
    public void adicionaAcao(AcaoInimigo acao) {
        this.sistemaAcoes.add(acao);
    }

    /**
     * Método para disponibilizar o tamanho do vetor de ações de cada inimigo
     * 
     * @return tamanho do vetor
     */
    public int getTamanhoSistemaAcoes() {
        return this.sistemaAcoes.size();
    }

    public ArrayList<AcaoInimigo> getSistemaAcoes() {
        return this.sistemaAcoes;
    }

    @Override
    public void recebeDano(int dano) {
        if (this.escudo >= dano) {
            this.escudo -= dano;
        } else {

            int danoRestante = dano - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }
    }

    @Override
    public void recebeDanoEfeito(int dano) {
        if (this.vida >= dano) {
            this.vida -= dano;
        } else {
            this.vida = 0;
        }
    }

    @Override
    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    @Override
    public void ataque(Entidade alvo, int valorDano) {
        /*
         * vamos ver quais são os efeitos na lista de efeitos que alterar o valor do
         * dano
         */
        for (Efeito efeito : this.listaEfeitos) {
            if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                double fator = (100.0 - ((EfeitoFraqueza) efeito).getValorFraqueza()) / 100;
                valorDano = (int) (valorDano * fator); // aqui fiz o truncamento para baixo.
            }

            if (efeito.getTipo() == TiposEfeitos.FORCA) {
                double fator = (100.0 + ((EfeitoForca) efeito).getValorForca()) / 100;
                valorDano = (int) (valorDano * fator); // aqui fiz o truncamento para baixo

            }
        }
        /* publico que o inimigo vai atacar */
        gm.notificar(this, Estados.ATAQUE);
        alvo.recebeDano(valorDano);
    }
}


