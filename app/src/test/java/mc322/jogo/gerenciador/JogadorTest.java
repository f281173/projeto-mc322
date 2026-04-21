package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;

/**
 * Classe que testa {@link Jogador}
 */
public class JogadorTest {
    @Test
    void testGestaoHerois() {
        Jogador jogador = new Jogador();
        Heroi h = new Heroi(100, 0, 5, 10, "Heroi");

        jogador.adicionarHeroiTodos(h);
        jogador.getHeroisEscolhidos().add(h);

        assertTrue(jogador.temHeroisVivos());
        assertTrue(jogador.validaEscolhaHeroi(0));
        assertFalse(jogador.validaEscolhaHeroi(1));
    }
}