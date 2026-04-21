package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoEventoTest {
    @Test
    void testEnumValues() {
        // Percorrer os valores do enum gera cobertura para a classe gerada pelo Java
        for (TipoEvento tipo : TipoEvento.values()) {
            assertNotNull(tipo);
        }
        assertEquals(TipoEvento.BATALHA, TipoEvento.valueOf("BATALHA"));
    }
}