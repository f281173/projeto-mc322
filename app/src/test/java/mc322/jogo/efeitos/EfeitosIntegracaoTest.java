package mc322.jogo.efeitos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;

public class EfeitosIntegracaoTest {

    @Test
    void testFabricaDeEfeitos() {
        GameManager gm = new GameManager();
        
        // Testa a criação via fábrica (criaEfeito) para cada tipo
        Efeito v = new EfeitoVeneno(3, gm);
        Efeito f = new EfeitoForca(2, gm, 25);
        Efeito fr = new EfeitoFraqueza(1, gm, 50);
        
        assertTrue(Efeito.criaEfeito(v) instanceof EfeitoVeneno);
        assertTrue(Efeito.criaEfeito(f) instanceof EfeitoForca);
        assertTrue(Efeito.criaEfeito(fr) instanceof EfeitoFraqueza);
    }

    @Test
    void testGestaoDeAcumulosBase() {
        GameManager gm = new GameManager();
        Efeito v = new EfeitoVeneno(5, gm);
        
        assertEquals(5, v.getAcumulos());
        v.aumentaAcumulos(2);
        assertEquals(7, v.getAcumulos());
        v.diminuiAcumulos();
        assertEquals(6, v.getAcumulos());
    }


}