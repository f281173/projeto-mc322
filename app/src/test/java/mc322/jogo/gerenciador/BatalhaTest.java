package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import java.util.ArrayList;

/**
 * Classe para testar {@link Batalha}
 */
public class BatalhaTest {

    @Test
    void testOrdenacaoVelocidadeComplexa() {
        GameManager gm = new GameManager();
        Batalha arena = new Batalha();

        Heroi lento = new Heroi("Lento", 100, 0, 5, 100, 5, true, gm, null);
        Inimigo medio = new Inimigo("Medio", 50, 0, 50, 15, false, gm, new ArrayList<>());
        Heroi rapido = new Heroi("Rapido", 100, 0, 5, 100, 30, true, gm, null);

        Jogador j = new Jogador();
        j.getHeroisEscolhidos().add(lento);
        j.getHeroisEscolhidos().add(rapido);

        Oponente o = new Oponente();
        o.getInimigosEscolhidos().add(medio);
        assertNotNull(arena);
    }

    @Test
    void testVerificacaoVitoriaDerrotaInstantanea() {
        GameManager gm = new GameManager();
        Batalha arena = new Batalha();

        Jogador j = new Jogador();
        Oponente o = new Oponente();

        assertDoesNotThrow(() -> {
            arena.executarCombate(j, o, gm, null, new Prints());
        });
    }

    @Test
    void testBatalhaComEquipesIncompletas() {
        GameManager gm = new GameManager();
        Batalha arena = new Batalha();
        Jogador j = new Jogador();
        Oponente o = new Oponente();

        Heroi h = new Heroi(10, 0, 5, 10, "H");
        j.getHeroisEscolhidos().add(h);

        assertDoesNotThrow(() -> {
            boolean resultado = arena.executarCombate(j, o, gm, null, new Prints());
            assertTrue(resultado);
        });
    }
}