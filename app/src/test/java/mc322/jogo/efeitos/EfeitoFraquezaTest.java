package mc322.jogo.efeitos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.RequisitoJogo;

public class EfeitoFraquezaTest {
    @Test
    void testAlteraFraqueza() {
        GameManager gm = new GameManager();
        EfeitoFraqueza ef = new EfeitoFraqueza(2, gm, 20);
        
        // Valor menor não deve substituir o atual, mas soma acúmulos
        ef.alteraFraqueza(10, 2);
        assertEquals(20, ef.getValorFraqueza());
        assertEquals(4, ef.getAcumulos());
    }

    @Test
    void testRequisito() {
        EfeitoFraqueza ef = new EfeitoFraqueza(1, null, 10);
        assertEquals(RequisitoJogo.INIMIGO, ef.requisitoEfeito());
    }
}