package mc322.jogo.mapa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;

public class CampanhaIntegracaoTest {

    @Test
    void testGeracaoMapaCompleto() {
        GameManager gm = new GameManager();
        int dificuldade = 1;

        // O método criarMapa percorre centenas de linhas configurando a campanha
        NoMapa inicio = Campanha.criarMapa(gm, dificuldade);

        assertNotNull(inicio);
        assertEquals("Inicio", inicio.getEvento().getNomeFase());
        
        // Verifica se os caminhos iniciais foram criados corretamente
        assertFalse(inicio.getProximos().isEmpty());
        
        // Navega um pouco no grafo para garantir a integridade
        NoMapa proximo = inicio.getProximos().get(0);
        assertNotNull(proximo.getEvento().getNomeFase());
    }
}