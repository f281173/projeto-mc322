package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TipoEventoTest {
    @Test
    void testEnumValues() {
        for (TipoEvento tipo : TipoEvento.values()) {
            assertNotNull(tipo);
        }
        assertEquals(TipoEvento.BATALHA, TipoEvento.valueOf("BATALHA"));
    }
}