package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import mc322.jogo.observer.Estados;
import mc322.jogo.observer.Subscriber;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;

/**
 * Classe que testa {@link GameManager}
 */
public class GameManagerTest {

    private GameManager gm;

    @BeforeEach
    void setUp() {
        gm = new GameManager();
    }

    @Test
    void testInicializacaoEInscricao() {
        Subscriber sub = new Subscriber() {
            @Override
            public void serNotificado(Estados state) {
            }

            @Override
            public Entidade getDono() {
                return null;
            }
        };

        assertDoesNotThrow(() -> {
            gm.inscrever(sub, Estados.ATAQUE);
            gm.desinscrever(sub, Estados.ATAQUE);
        });
    }

    @Test
    void testNotificarSemInscritos() {
        assertDoesNotThrow(() -> {
            gm.notificar(null, Estados.INICIO_DE_TURNO);
        });
    }

    @Test
    void testNotificacaoMultiplosInscritos() {
        Heroi shrek = new Heroi(100, 0, 5, 10, "Shrek");

        class ContadorSub implements Subscriber {
            int chamadas = 0;

            @Override
            public void serNotificado(Estados state) {
                chamadas++;
            }

            @Override
            public Entidade getDono() {
                return shrek;
            }
        }

        ContadorSub sub1 = new ContadorSub();
        ContadorSub sub2 = new ContadorSub();

        gm.inscrever(sub1, Estados.ATAQUE);
        gm.inscrever(sub2, Estados.ATAQUE);

        gm.notificar(shrek, Estados.ATAQUE);

        assertEquals(1, sub1.chamadas, "Sub1 deve ser notificado");
        assertEquals(1, sub2.chamadas, "Sub2 deve ser notificado");
    }

    @Test
    void testFluxoNotificacaoReal() {
        Heroi h = new Heroi(100, 0, 5, 10, "Heroi");

        class MockSub implements mc322.jogo.observer.Subscriber {
            boolean foiNotificado = false;

            @Override
            public void serNotificado(mc322.jogo.observer.Estados s) {
                foiNotificado = true;
            }

            @Override
            public mc322.jogo.entidades.Entidade getDono() {
                return h;
            }
        }

        MockSub sub = new MockSub();
        gm.inscrever(sub, mc322.jogo.observer.Estados.ATAQUE);

        gm.notificar(h, mc322.jogo.observer.Estados.ATAQUE);
        assertTrue(sub.foiNotificado);
    }

    @Test
    void testNotificacaoSeletiva() {
        Heroi shrek = new Heroi("Shrek", 100, 0, 5, 100, 10, true, gm, null);
        Heroi burro = new Heroi("Burro", 80, 0, 3, 80, 15, false, gm, null);

        class ContadorSub implements mc322.jogo.observer.Subscriber {
            int cont = 0;
            Entidade dono;

            ContadorSub(Entidade d) {
                this.dono = d;
            }

            @Override
            public void serNotificado(mc322.jogo.observer.Estados s) {
                cont++;
            }

            @Override
            public Entidade getDono() {
                return dono;
            }
        }

        ContadorSub subShrek = new ContadorSub(shrek);
        ContadorSub subBurro = new ContadorSub(burro);

        gm.inscrever(subShrek, mc322.jogo.observer.Estados.ATAQUE);
        gm.inscrever(subBurro, mc322.jogo.observer.Estados.ATAQUE);

        gm.notificar(shrek, mc322.jogo.observer.Estados.ATAQUE);

        assertEquals(1, subShrek.cont);
        assertEquals(0, subBurro.cont);
    }

}