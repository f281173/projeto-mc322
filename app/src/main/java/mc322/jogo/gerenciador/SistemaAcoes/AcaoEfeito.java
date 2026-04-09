package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.Cores;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe que implementa a AçãoInimigo e tem a função
 * de criar um ação de efeito para o inimigo.
 */
public class AcaoEfeito implements AcaoInimigo {
    private String nomeAcao;
    private Efeito efeito;

    public AcaoEfeito(String nomeAcao, Efeito efeito) {
        this.efeito = efeito;
        this.nomeAcao = nomeAcao;
    }

    public String executar(Inimigo dono, Heroi alvo) {
        if (this.efeito.getTipo() == TiposEfeitos.FORCA)
            dono.aplicarEfeito(efeito);

        else if (this.efeito.getTipo() == TiposEfeitos.FRAQUEZA)
            alvo.aplicarEfeito(efeito);

        else if (this.efeito.getTipo() == TiposEfeitos.VENENO)
            alvo.aplicarEfeito(efeito);
        return dono.getNome() + " usou '" + this.nomeAcao + Cores.AZUL + " ativou " + this.efeito.getString() + " em " + alvo.getNome();
    }

    public String getnomeAcao() {
        return this.nomeAcao;
    }
}