package mc322.jogo.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InimigoTest {

    @Test
    public void danoAbsorvidoPorEscudo() {
    Inimigo h = new Inimigo(10, 5);
    h.recebeDano(3);
    assertEquals(10, h.getVida());
    assertEquals(2, h.getEscudo());
    }

    @Test
    public void danoParcialEscudo() {
    Inimigo h = new Inimigo(10, 5);
    h.recebeDano(8);
    assertEquals(7, h.getVida());
    assertEquals(0, h.getEscudo());
    }

    @Test
    public void vidaNegativaEfeito() {
    Inimigo h = new Inimigo(10, 1);
    h.recebeDanoEfeito(15);
    assertEquals(0, h.getVida());
    assertEquals(1, h.getEscudo());
    }

    @Test
    public void vidaNegativa() {
    Inimigo h = new Inimigo(10, 1);
    h.recebeDano(15);
    assertEquals(0, h.getVida());
    assertEquals(0, h.getEscudo());
    } 

    @Test
    public void TestaEstaVivo() {
    Inimigo h = new Inimigo(10, 12);
    assertEquals(true, h.estaVivo());
    Inimigo inimigo = new Inimigo(0,0);
    assertEquals(false, inimigo.estaVivo());
    }
}