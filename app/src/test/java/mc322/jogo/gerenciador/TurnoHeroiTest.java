package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.cartas.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Classe para testar {@link TurnoHeroi}
 */
public class TurnoHeroiTest {

    private GameManager gm;
    private TurnoHeroi turnoHeroi;

    @BeforeEach
    void setUp() {
        gm = new GameManager();
        turnoHeroi = new TurnoHeroi(gm);
    }

    @Test
    void testFluxoHeroiCompleto() {
        String input = "1\n0\n2\n2\n0\n0\n3\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Baralho deck = new Baralho();
        deck.adicionaBaralho(new CartaDano("Golpe", "Dano 10", 1, 10));

        Heroi shrek = new Heroi("Shrek", 100, 10, 5, 100, 10, true, gm, deck);

        Jogador jogador = new Jogador();
        jogador.getHeroisEscolhidos().add(shrek);

        Oponente oponente = new Oponente();
        oponente.adicionarInimigoTodos(new Inimigo("Vilao", 50, 0, 50, 5, false, gm, new ArrayList<>()));
        oponente.gerarInimigos(1);

        assertDoesNotThrow(() -> {
            turnoHeroi.jogar(shrek, jogador, oponente, new Prints(), sc);
        });
    }

    @Test
    void testRequisitosDiferentes() {
        String input = "2\n2\n0\n3\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Baralho deck = new Baralho();
        deck.adicionaBaralho(new CartaEscudo("Escudo", "Defesa", 1, 10));

        Heroi shrek = new Heroi("Shrek", 100, 0, 5, 100, 10, true, gm, deck);
        shrek.ganhaCarta(new CartaEscudo("Defesa", "E", 1, 5)); // Garante carta na mão

        Jogador jogador = new Jogador();
        jogador.getHeroisEscolhidos().add(shrek);
        Oponente oponente = new Oponente();
        oponente.adicionarInimigoTodos(new Inimigo("V", 10, 0, 10, 5, false, gm, new ArrayList<>()));
        oponente.gerarInimigos(1);

        assertDoesNotThrow(() -> {
            turnoHeroi.jogar(shrek, jogador, oponente, new Prints(), sc);
        });
    }
}