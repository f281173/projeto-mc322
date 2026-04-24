package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.SistemaAcoes.ResultadoAcao.TipoAcao;

/**
 * Classe que implementa açãoInimigo e tem a função de criar
 * uma ação de ataque para o inimigo.
 */
public class AcaoAtacar implements AcaoInimigo {
    private String nomeAcao;
    private int valorDano;

    public AcaoAtacar(String nomeAcao, int valorDano) {
        this.valorDano = valorDano;
        this.nomeAcao = nomeAcao;
    }

    public ResultadoAcao executar(Inimigo dono, Heroi alvo) {
        dono.ataque(alvo, valorDano);
        /*
         * Isso aqui não resolve a questão de imprimir certo quando usa fraqueza
         * [CORRECAO]
         */
        ResultadoAcao resultado = new ResultadoAcao(TipoAcao.ATAQUE, this.nomeAcao, dono, alvo, valorDano);
        return resultado;
    }

    public String getnomeAcao() {
        return this.nomeAcao;
    }

}
