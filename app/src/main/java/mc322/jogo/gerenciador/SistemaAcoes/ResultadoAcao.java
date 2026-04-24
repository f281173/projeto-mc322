package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.entidades.Entidade;

/**
 * Classe que serve para implementar o padrão DTO para
 * que seja possível transferir os dados sem estar vinculado
 * diretamente a uma interface de usuário.
 */
public class ResultadoAcao {
    public enum TipoAcao { ATAQUE, ESCUDO, EFEITO }

    private TipoAcao tipo;
    private String nomeHabilidade;
    private Entidade dono;
    private Entidade alvo;
    private int valor; // Pode ser o dano, o escudo, etc.

    public ResultadoAcao(TipoAcao tipo, String nomeHabilidade, Entidade dono, Entidade alvo, int valor) {
        this.tipo = tipo;
        this.nomeHabilidade = nomeHabilidade;
        this.dono = dono;
        this.alvo = alvo;
        this.valor = valor;
    }

    public TipoAcao getTipo() {
        return this.tipo;
    }

    public String getNomeHabilidade() {
        return this.nomeHabilidade;
    }

    public Entidade getDono() {
        return this.dono;
    }

    public Entidade getAlvo() {
        return this.alvo;
    }

    public int getValor() {
        return this.valor;
    }

}