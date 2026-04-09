package mc322.jogo.gerenciador;

import java.util.ArrayList;
import java.util.Random;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;
import mc322.jogo.observer.Estados;

/**
 * Classe responsável por executar o turno do inimigo
 * Avalia o campo de batalha, escolhe um alvo e executa a ação da carta do topo.
 */
public class TurnoVilao {
    private GameManager gm;

    public TurnoVilao(GameManager gm) {
        this.gm = gm;
    }

    /**
     * Executa o Turno do vilão com base em um vetor de ações, em que
     * uma ação é escolhida aleatoriamente para ser executada. Essa classe
     * é quem faz a interação com o terminal que será impresso para o usuário
     * durante o turno do vilão.
     * 
     * @param enemy  Entidade da Classe Inimigo responsável por realizar o ataque
     * @param herois Um Vetor com todos os heróis no campo de batalha.
     */
    public void jogar(Inimigo enemy, ArrayList<Heroi> herois) {

        System.out
                .println("\n" + Cores.VERMELHO + Cores.NEGRITO
                        + "================== TURNO DO INIMIGO ==================" + Cores.RESET);

        /* notificar a entidade que é inicio do turno */
        gm.notificar(enemy, Estados.INICIO_DE_TURNO);

        System.out.println(Cores.VERMELHO + "O " + enemy.getNome() + " está atacando..." + Cores.RESET);

        ArrayList<Heroi> heroisVivos = new ArrayList<>();
        for (Heroi h : herois) { // poderia deixar isso aqui como responsabilidade da classe jogador [CORRECAO]
            if (h.estaVivo()) {
                heroisVivos.add(h);
            }
        }

        if (!heroisVivos.isEmpty()) {

            /* sorteia um heroi pra bater */
            Random gerador = new Random();
            Heroi alvo = heroisVivos.get(gerador.nextInt(heroisVivos.size()));;

            /* sorteia uma ação para ser executada */
            AcaoInimigo acao = enemy.getSistemaAcoes().get(gerador.nextInt(enemy.getTamanhoSistemaAcoes()));

            String resposta = acao.executar(enemy, alvo);
            System.out.println(resposta);
            enemy.imprimeEfeitos();

        }
        /* tenho que publicar que o turno do inimigo terminou */
        gm.notificar(enemy, Estados.FIM_DE_TURNO);

        System.out.println(Cores.VERMELHO + Cores.NEGRITO + "======================================================\n"
                + Cores.RESET);
    }
}
