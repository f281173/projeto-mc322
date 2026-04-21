package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;

public class CampanhaIntegracaoTest {

    @Test
    void testGeracaoMapaCompleto() {
        GameManager gm = new GameManager();
        int dificuldade = 1;

        NoMapa inicio = Campanha.criarMapa(gm, dificuldade);

        assertNotNull(inicio);
        assertEquals("Inicio", inicio.getEvento().getNomeFase());
        
        assertFalse(inicio.getProximos().isEmpty());

        NoMapa proximo = inicio.getProximos().get(0);
        assertNotNull(proximo.getEvento().getNomeFase());
    }
}