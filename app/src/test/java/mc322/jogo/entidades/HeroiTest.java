package mc322.jogo.entidades;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {

    @Test
    public void danoAbsorvidoPorEscudo() {
    Heroi h = new Heroi(10, 5, 6, 5,"HEROI");
    h.recebeDano(3);
    assertEquals(10, h.getVida());
    assertEquals(2, h.getEscudo());
    }

    @Test
    public void danoParcialEscudo() {
    Heroi h = new Heroi(10, 5, 6, 5,"HEROI");
    h.recebeDano(8);
    assertEquals(7, h.getVida());
    assertEquals(0, h.getEscudo());
    }

    @Test
    public void vidaNegativaEfeito() {
    Heroi h = new Heroi(10, 1, 6, 5,"HEROI");
    h.recebeDanoEfeito(15);
    assertEquals(0, h.getVida());
    assertEquals(1, h.getEscudo());
    }

    @Test
    public void vidaNegativa() {
    Heroi h = new Heroi(10, 1, 6, 5,"HEROI");
    h.recebeDano(15);
    assertEquals(0, h.getVida());
    assertEquals(0, h.getEscudo());
    }

    @Test
    public void TestaZeraEscudo() {
    Heroi h = new Heroi(10, 12, 6, 6,"HEROI");
    h.zeraEscudo();
    assertEquals(0, h.getEscudo());
    }

    @Test
    public void TestaGanhaEscudo() {
    Heroi h = new Heroi(10, 5, 6, 5,"HEROI");
    h.ganhaEscudo(-10);
    assertEquals(5, h.getEscudo());
    h.ganhaEscudo(12);
    assertEquals(17, h.getEscudo());
    }

    @Test
    public void TestaAnalisaEnergia() {
    Heroi h = new Heroi(10, 12, 6, 9,"HEROI");
    assertEquals(false, h.analisaEnergia(10));
    }

    @Test
    public void TestaEstaVivo() {
    Heroi h = new Heroi(10, 12, 6, 5,"HEROI");
    assertEquals(true, h.estaVivo());
    Heroi heroi = new Heroi(0,0,10, 9,"HEROI");
    assertEquals(false, heroi.estaVivo());
    }

    @Test
    public void TestaGetters() {
    Heroi h = new Heroi(10, 12, 6, 5,"HEROI");
    assertEquals("HEROI", h.getNome());
    assertEquals(10, h.getVidaInicial());
    assertEquals(5, h.getVelocidade());
    }
}