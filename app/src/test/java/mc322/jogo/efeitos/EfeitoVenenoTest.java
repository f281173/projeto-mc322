package mc322.jogo.efeitos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.observer.Estados;
import java.util.ArrayList;

public class EfeitoVenenoTest {
    @Test
    void testDanoVenenoNoInicioDoTurno() {
        GameManager gm = new GameManager();
        Inimigo i = new Inimigo("Vilao", 100, 50, 100, 10, false, gm, new ArrayList<>());
        EfeitoVeneno ev = new EfeitoVeneno(10, gm);
        ev.setDono(i);
        i.getListaEfeitos().add(ev);

        // Notifica início de turno: deve causar dano igual aos acúmulos (10)
        ev.serNotificado(Estados.INICIO_DE_TURNO);
        
        // Vida deve cair para 90, mas o escudo (50) deve permanecer intacto
        assertEquals(90, i.getVida());
        assertEquals(50, i.getEscudo());
        assertEquals(9, ev.getAcumulos());
    }
}