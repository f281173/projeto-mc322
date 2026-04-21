package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import mc322.jogo.entidades.Heroi;
import java.util.ArrayList;

/**
 * Classe para testar {@link Prints}
 */
public class PrintsTest {
    @Test
    void testTodosOsPrints() {
        Prints p = new Prints();
        p.comeco();
        p.dificuldade();
        p.faseBatalha();
        p.jogarNovamente();
        Prints.limparTela();

        p.status_batalha(new Heroi(10, 0, 5, 5, "H"), new ArrayList<>(), new ArrayList<>());
    }
}