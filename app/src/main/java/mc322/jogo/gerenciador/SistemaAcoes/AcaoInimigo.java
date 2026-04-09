package mc322.jogo.gerenciador.SistemaAcoes;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * interface que tem como objetivo definir a regra de ação para o Inimigo.
 * Entre as ações temos:
 * 
 * ATACAR: tem uma habilidade que gera dano no oponente
 * ESCUDO: pode usar o seu escudo durante um turno
 * EFEITOS: tem efeitos que ele pode aplicar em um oponente
 */
public interface AcaoInimigo {
    /**
     * Método para executar uma ação genérica (será implementado
     * por cada ação específica) pelo inimigo.
     * 
     * @param dono a qual Inimigo paritu aquela ação
     * @param alvo Quem o inimigo quer de fato atingir com a ação.
     * @return
     */
    public String executar(Inimigo dono, Heroi alvo);

}
