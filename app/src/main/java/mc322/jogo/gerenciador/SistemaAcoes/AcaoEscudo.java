package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe que implementa AçãoInimigo e tem a função de
 * criar uma ação de dar escudo para o inmigo em um turno.
 */
public class AcaoEscudo implements AcaoInimigo {
    private String nomeAcao;
    private int valorEscudo;

    public AcaoEscudo(String nomeAcao, int valorEscudo) {
        this.valorEscudo = valorEscudo;
        this.nomeAcao = nomeAcao;
    }

    public String executar(Inimigo dono, Heroi alvo) {
        dono.ganhaEscudo(valorEscudo);
        return dono.getNome() + " usou '" + this.nomeAcao + "' e ganhou " + Cores.AZUL + this.valorEscudo + Cores.RESET + " de escudo!";
    }

    public String getnomeAcao() {
        return this.nomeAcao;
    }
    
}
