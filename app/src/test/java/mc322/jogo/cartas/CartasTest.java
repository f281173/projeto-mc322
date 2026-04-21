package mc322.jogo.cartas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.efeitos.EfeitoVeneno;
import mc322.jogo.RequisitoJogo;
import java.util.ArrayList;

public class CartasTest {

    @Test
    void testCartaDanoArea() {
        GameManager gm = new GameManager();
        Heroi shrek = new Heroi("Shrek", 100, 0, 5, 100, 10, true, gm, null);
        
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        Inimigo i1 = new Inimigo("Inimigo 1", 20, 0, 20, 5, false, gm, new ArrayList<>());
        Inimigo i2 = new Inimigo("Inimigo 2", 20, 0, 20, 5, false, gm, new ArrayList<>());
        inimigos.add(i1);
        inimigos.add(i2);

        CartaDanoArea area = new CartaDanoArea("Explosão", "10 de dano em área", 2, 10);
        
        // Testa o requisito e o uso
        assertEquals(RequisitoJogo.TODOS_INIMIGOS, area.cartaRequisito());
        area.usar(shrek, null, inimigos);

        assertEquals(10, i1.getVida());
        assertEquals(10, i2.getVida());
    }

    @Test
    void testCartaEscudo() {
        GameManager gm = new GameManager();
        Heroi h = new Heroi("H", 50, 0, 5, 50, 10, true, gm, null);
        CartaEscudo carta = new CartaEscudo("Escudo Fiel", "Ganha 15 de escudo", 1, 15);

        carta.usar(h, h, null);
        
        assertEquals(15, h.getEscudo());
        assertEquals(TiposCartas.ESCUDO, carta.getTipoCarta());
    }

    @Test
    void testCartaEfeitoVeneno() {
        GameManager gm = new GameManager();
        Inimigo vilao = new Inimigo("Vilão", 50, 0, 50, 5, false, gm, new ArrayList<>());
        EfeitoVeneno veneno = new EfeitoVeneno(5, gm);
        
        CartaEfeito cartaVeneno = new CartaEfeito("Poção", "Aplica 5 de veneno", 1, veneno);
        
        cartaVeneno.usar(null, vilao, null);
        
        // Verifica se o efeito foi aplicado na lista de efeitos da entidade
        assertFalse(vilao.getListaEfeitos().isEmpty());
        assertEquals(RequisitoJogo.INIMIGO, cartaVeneno.cartaRequisito());
    }
}