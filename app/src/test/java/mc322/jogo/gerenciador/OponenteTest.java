package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe que testa {@link Oponente}
 */
public class OponenteTest {
    private Oponente oponente;

    @BeforeEach
    void setUp() {
        oponente = new Oponente();
    }

    @Test
    void testGerarEValidarInimigos() {
        Inimigo i1 = new Inimigo(50, 0);
        i1.setNome("Inimigo 1");

        oponente.adicionarInimigoTodos(i1);
        oponente.gerarInimigos(1);

        assertTrue(oponente.validaEscolhaInimigo(0), "Índice 0 deve ser válido");
        assertFalse(oponente.validaEscolhaInimigo(-1), "Índice negativo deve ser inválido");
        assertFalse(oponente.validaEscolhaInimigo(99), "Índice inexistente deve ser inválido");

        assertTrue(oponente.temInimigosVivos());
    }

    @Test
    void testTemInimigosVivosVazio() {
        assertFalse(oponente.temInimigosVivos());
    }
}