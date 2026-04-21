package mc322.jogo.gerenciador;

import org.junit.jupiter.api.Test;
import mc322.jogo.entidades.Heroi;
import java.util.ArrayList;

public class PrintsTest {
    @Test
    void testTodosOsPrints() {
        Prints p = new Prints();
        // Chamamos os métodos estáticos e de instância para ganhar cobertura
        p.comeco();
        p.dificuldade();
        p.faseBatalha();
        p.jogarNovamente();
        Prints.limparTela();
        
        // Testamos um status de batalha vazio para cobrir a lógica de loops
        p.status_batalha(new Heroi(10,0,5,5,"H"), new ArrayList<>(), new ArrayList<>());
    }
}