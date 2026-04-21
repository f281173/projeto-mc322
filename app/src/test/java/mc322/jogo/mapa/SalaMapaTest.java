package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.cartas.CartaDano;

/**
 * Classe para testar o grafo do jogo.
 */
public class SalaMapaTest {

    @Test
    void testEstruturaNoEEvento() {
        EventoMapa evento = new EventoMapa("Floresta", "Cuidado com o Ogro!", TipoEvento.BATALHA, null);
        assertEquals("Floresta", evento.getNomeFase());
        assertEquals(TipoEvento.BATALHA, evento.getTipo());

        NoMapa no = new NoMapa(evento);
        assertTrue(no.FimDeJogo());

        NoMapa proximo = new NoMapa(new EventoMapa("Fim", "Fim", TipoEvento.ESCOLHA_HISTORIA, null));
        no.adicionarCaminho(proximo);

        assertFalse(no.FimDeJogo());
        assertEquals(1, no.getProximos().size());
    }

    @Test
    void testRecompensaNoEvento() {
        EventoMapa evento = new EventoMapa("Tesouro", "Achou uma carta!", TipoEvento.RECOMPENSA_CARTA, null);
        CartaDano carta = new CartaDano("Golpe", "Dano", 1, 10);

        evento.setCartaRecompensa(carta);
        assertEquals(carta, evento.getCartaRecompensa());
    }
}