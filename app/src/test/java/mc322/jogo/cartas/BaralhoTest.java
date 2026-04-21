package mc322.jogo.cartas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BaralhoTest {
    private Baralho baralho;

    @BeforeEach
    void setUp() {
        baralho = new Baralho();
    }

    @Test
    void testCriarPilhaCompraEResetar() {
        // Adiciona cartas para garantir que o baralho não está vazio
        Carta c1 = new CartaDano("Ataque 1", "Dano", 1, 10);
        Carta c2 = new CartaEscudo("Defesa 1", "Escudo", 1, 10);
        baralho.adicionaBaralho(c1);
        baralho.adicionaBaralho(c2);

        // Testa a criação da pilha com tamanho maior que o baralho atual
        // Isso forçará a chamada interna de resetaBaralho() quando a pilha esvaziar
        baralho.criarPilhaCompra(3);

        // Verifica se as cartas foram movidas para a pilha de compra
        assertTrue(baralho.tamanhoPilha() > 0);
    }

    @Test
    void testDevolverCartasNaoCompradas() {
        Carta c = new CartaDano("Teste", "D", 1, 5);
        baralho.adicionaPilhaCompra(c);

        baralho.devolverCartasNaoCompradas();

        assertEquals(0, baralho.tamanhoPilha());
        assertTrue(baralho.getBaralho().contains(c));
    }

    @Test
    void testResetaBaralhoCompleto() {
        Carta c1 = new CartaDano("C1", "D", 1, 5);
        Carta c2 = new CartaDano("C2", "D", 1, 5);

        baralho.adicionaPilhaCompra(c1);
        baralho.adicionaPilhaDescarte(c2);

        baralho.resetaBaralho();

        assertEquals(2, baralho.getBaralho().size());
        assertEquals(0, baralho.tamanhoPilha());
    }

    @Test
    void testConstrutorECopiaBaralho() {
        Baralho original = new Baralho();
        original.adicionaBaralho(new CartaDano("C1", "D", 1, 5));

        Baralho copia = new Baralho(original);
        assertEquals(original.getBaralho().size(), copia.getBaralho().size());

        // Cobre o método de impressão (importante para as linhas de visualização)
        copia.adicionaPilhaCompra(new CartaDano("C2", "D", 1, 5));
        assertDoesNotThrow(() -> copia.imprimePilhaCompra());
    }
}