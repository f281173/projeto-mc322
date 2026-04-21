package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoAtacar;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;
import java.util.ArrayList;

public class TurnoVilaoTest {

    @Test
    void testFluxoTurnoVilaoComSucesso() {
        GameManager gm = new GameManager();
        TurnoVilao turnoVilao = new TurnoVilao(gm);
        
        // 1. Criar Heróis (devem estar vivos para entrar no if do heroisVivos)
        Heroi h1 = new Heroi("Heroi 1", 100, 0, 5, 100, 10, true, gm, null);
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(h1);
        
        // 2. Criar Ações (precisa de pelo menos uma para o Random não falhar)
        ArrayList<AcaoInimigo> acoes = new ArrayList<>();
        acoes.add(new AcaoAtacar("Mordida", 10));
        
        // 3. Criar Inimigo com o sistema de ações populado
        Inimigo vilao = new Inimigo("Vilao", 50, 0, 50, 10, false, gm, acoes);

        assertDoesNotThrow(() -> {
            // Cobre: Notificações de início/fim, loop de heróis vivos, sorteio e execução da ação
            turnoVilao.jogar(vilao, herois);
        });
    }

    @Test
    void testTurnoVilaoSemHeroisVivos() {
        GameManager gm = new GameManager();
        TurnoVilao turnoVilao = new TurnoVilao(gm);
        
        // Herói morto (estaVivo() será false)
        Heroi morto = new Heroi("Defunto", 0, 0, 5, 100, 10, true, gm, null);
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(morto);
        
        ArrayList<AcaoInimigo> acoes = new ArrayList<>();
        acoes.add(new AcaoAtacar("Ataque", 5));
        Inimigo vilao = new Inimigo("Vilao", 50, 0, 50, 10, false, gm, acoes);

        assertDoesNotThrow(() -> {
            // Cobre o ramo 'if (!heroisVivos.isEmpty())' como falso
            turnoVilao.jogar(vilao, herois);
        });
    }
}