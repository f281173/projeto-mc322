package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

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

    public String executar(Inimigo dono, Heroi alvo) {
        dono.ataque(alvo, valorDano);
        /*
         * Isso aqui não resolve a questão de imprimir certo quando usa fraqueza
         * [CORRECAO]
         */
        return dono.getNome() + " usou '" + this.nomeAcao + "' e causou " + Cores.VERMELHO + this.valorDano + " de Dano"
                + Cores.RESET + " de dano no " + alvo.getNome() + "!";
    }

    public String getnomeAcao() {
        return this.nomeAcao;
    }

}
