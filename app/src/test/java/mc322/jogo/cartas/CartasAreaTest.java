package mc322.jogo.cartas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;
import java.util.ArrayList;

/**
 * Classe para testar {@link CartaDanoArea}
 */
public class CartasAreaTest {

    @Test
    void testCartaDanoAreaRamos() {
        GameManager gm = new GameManager();
        Heroi dono = new Heroi("Dono", 100, 0, 5, 100, 10, true, gm, null);

        ArrayList<Inimigo> inimigos = new ArrayList<>();
        Inimigo vivo = new Inimigo("Vivo", 20, 0, 20, 5, false, gm, new ArrayList<>());
        Inimigo morto = new Inimigo("Morto", 0, 0, 20, 5, false, gm, new ArrayList<>());

        inimigos.add(vivo);
        inimigos.add(morto);

        CartaDanoArea area = new CartaDanoArea("Chuva de Flechas", "Dano em todos", 2, 10);

        area.usarEmArea(inimigos);
        assertEquals(10, vivo.getVida());
        assertEquals(0, morto.getVida()); 

        area.usar(dono, null, inimigos);
        assertEquals(0, vivo.getVida());
    }

    @Test
    void testMetodosAcessoCartas() {
        CartaDano dano = new CartaDano("Punhal", "Dano leve", 1, 5);

        assertEquals("Dano leve", dano.getDescricao());
        assertEquals(1, dano.getCusto());
        assertEquals(TiposCartas.DANO, dano.getTipoCarta());
        assertEquals(5, dano.acessoCartaDanoDano());
    }
}