package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoAtacar;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;
import java.util.ArrayList;

/**
 * Classe para testar {@link TurnoVilao}
 */
public class TurnoVilaoTest {

    @Test
    void testFluxoTurnoVilaoComSucesso() {
        GameManager gm = new GameManager();
        TurnoVilao turnoVilao = new TurnoVilao(gm);
        
        Heroi h1 = new Heroi("Heroi 1", 100, 0, 5, 100, 10, true, gm, null);
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(h1);
        
        ArrayList<AcaoInimigo> acoes = new ArrayList<>();
        acoes.add(new AcaoAtacar("Mordida", 10));
        
        Inimigo vilao = new Inimigo("Vilao", 50, 0, 50, 10, false, gm, acoes);

        assertDoesNotThrow(() -> {
            turnoVilao.jogar(vilao, herois);
        });
    }

    @Test
    void testTurnoVilaoSemHeroisVivos() {
        GameManager gm = new GameManager();
        TurnoVilao turnoVilao = new TurnoVilao(gm);
        
        Heroi morto = new Heroi("Defunto", 0, 0, 5, 100, 10, true, gm, null);
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(morto);
        
        ArrayList<AcaoInimigo> acoes = new ArrayList<>();
        acoes.add(new AcaoAtacar("Ataque", 5));
        Inimigo vilao = new Inimigo("Vilao", 50, 0, 50, 10, false, gm, acoes);

        assertDoesNotThrow(() -> {
            turnoVilao.jogar(vilao, herois);
        });
    }
}