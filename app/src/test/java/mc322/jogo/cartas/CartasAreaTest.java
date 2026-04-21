package mc322.jogo.cartas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;
import java.util.ArrayList;

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
        
        // 1. Testa o método usarEmArea (cobre o loop e o 'if (alvo.estaVivo())')
        area.usarEmArea(inimigos);
        assertEquals(10, vivo.getVida());
        assertEquals(0, morto.getVida()); // Não deve ter sofrido dano adicional por já estar morto

        // 2. Testa o método usar padrão (cobre a chamada super e o loop de ataque)
        area.usar(dono, null, inimigos);
        assertEquals(0, vivo.getVida());
    }

    @Test
    void testMetodosAcessoCartas() {
        CartaDano dano = new CartaDano("Punhal", "Dano leve", 1, 5);
        
        // Cobre os métodos de retorno de descrição e custo que as vezes ficam em cinza
        assertEquals("Dano leve", dano.getDescricao());
        assertEquals(1, dano.getCusto());
        assertEquals(TiposCartas.DANO, dano.getTipoCarta());
        assertEquals(5, dano.acessoCartaDanoDano());
    }
}