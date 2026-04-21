package mc322.jogo.efeitos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.observer.Estados;

public class EfeitoForcaTest {
    @Test
    void testLogicaAcumuloForca() {
        GameManager gm = new GameManager();
        EfeitoForca ef = new EfeitoForca(2, gm, 25);
        
        // Se aplicar uma força maior (50%), deve substituir o valor e somar acúmulos
        ef.alteraForca(50, 3);
        assertEquals(50, ef.getValorForca());
        assertEquals(5, ef.getAcumulos());
    }

    @Test
    void testTerminoEfeitoForca() {
        GameManager gm = new GameManager();
        Heroi h = new Heroi(100, 0, 5, 10, "Heroi");
        EfeitoForca ef = new EfeitoForca(1, gm, 25);
        ef.setDono(h);
        h.getListaEfeitos().add(ef);

        // Notifica fim de turno: deve reduzir acúmulo e remover o efeito por ser o último
        ef.serNotificado(Estados.FIM_DE_TURNO);
        assertEquals(0, ef.getAcumulos());
        assertTrue(h.getListaEfeitos().isEmpty());
    }
}