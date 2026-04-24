package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.SistemaAcoes.ResultadoAcao.TipoAcao;

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

    public ResultadoAcao executar(Inimigo dono, Heroi alvo) {
        dono.ganhaEscudo(valorEscudo);
        ResultadoAcao resultado = new ResultadoAcao(TipoAcao.ESCUDO, this.nomeAcao, dono, alvo, valorEscudo);
        return resultado;
    }

    public String getnomeAcao() {
        return this.nomeAcao;
    }
    
}
