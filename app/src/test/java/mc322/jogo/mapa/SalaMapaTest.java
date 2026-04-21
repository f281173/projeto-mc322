package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.cartas.CartaDano;

public class SalaMapaTest {

    @Test
    void testEstruturaNoEEvento() {
        // Testa a criação de um evento base
        EventoMapa evento = new EventoMapa("Floresta", "Cuidado com o Ogro!", TipoEvento.BATALHA, null);
        assertEquals("Floresta", evento.getNomeFase());
        assertEquals(TipoEvento.BATALHA, evento.getTipo());

        // Testa o Nó do Mapa
        NoMapa no = new NoMapa(evento);
        assertTrue(no.FimDeJogo()); // Inicialmente sem caminhos, é fim de jogo

        // Adiciona um caminho e verifica se deixou de ser fim de jogo
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