package mc322.jogo.gerenciador;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;
import mc322.jogo.observer.Estados;


/**
 * Classe responsável por gerenciar a interação do jogador durante o turno de um
 * Herói.
 * Divide-se em duas etapas principais: Fase de Compra (Loja) e Fase de Ação
 * (Combate).
 */
public class TurnoHeroi {
    private GameManager gm;

    /* criar constantes para melhorar a legibilidade */
    private static final int OPCAO_ENCERRA_TURNO = 3;
    private static final int OPCAO_VER_CARTAS = 1;
    private static final int OPCAO_USAR_CARTAS = 2;
    private static final int ENCERRAR_FASE_COMPRA = 2;
    private static final int COMPRA_CARTA = 1;

    public TurnoHeroi(GameManager gm) {
        this.gm = gm;
    }

    /**
     * Método responsável por gerenciar um Turno do Heroi com respeito a interação
     * com o terminal.
     * Responsável por gerenciar as entradas do usuário durante o turno do heroi, ou
     * seja, as cartas
     * escolhidas e as ações de ataacar, aplicar escudo ou aplicar efeito.
     * 
     * @param player   Heroi que vai atacar nesse turno
     * @param herois   Objeto Jogador que serve para gerenciar a lista com todos os
     *                 herois da batalha.
     * @param oponente lista com todos os inimigos escolhidos nessa batalha
     * @param tela     Classe com todos os prints de telas do jogo {@link Prints}
     * @param sc       Scanner do teclado.
     */
    public void jogar(Heroi player, Jogador herois, Oponente oponente, InterfaceUsuario ui) {

        player.zeraEscudo();
        int opcaoCompra = 0;
        int cartasCompradas = 0;
        int limiteCompra = 3;
        player.getBaralhoPessoal().criarPilhaCompra(4);

        /* vamos notificar que iniciou o turno para a entidade */
        gm.notificar(player, Estados.INICIO_DE_TURNO);

        /*
         * é necessário resetar a energia do player para ele jogar no turno (posso
         * trocar por inicia turno no futuro)
         */
        player.resetaEnergia();

        // ----------------------------FASE DE COMPRA ----------------------------------

        while (opcaoCompra != ENCERRAR_FASE_COMPRA && oponente.temInimigosVivos() && cartasCompradas < limiteCompra
                && player.estaVivo()) {
            
            ui.mostrarStatusTurno(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos());
            opcaoCompra = ui.escolherAcaoCompra(limiteCompra, cartasCompradas);

            if (opcaoCompra == COMPRA_CARTA) {
                int i = ui.escolherCartaParaComprar(player.getBaralhoPessoal().getPilhaCompra());

                if (i < 0 || player.getBaralhoPessoal().tamanhoPilha() <= i) {
                    ui.mostrarMensagem(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);

                } else {
                    player.getBaralhoPessoal().compraCarta(player, i);
                    ui.limparTela(); // talvez não faça sentido existir isso aqui.
                    ui.mostrarMensagem(Cores.VERDE + "Você comprou uma carta!" + Cores.RESET);
                    cartasCompradas++;
                }

            } else if (opcaoCompra != ENCERRAR_FASE_COMPRA) {
                ui.mostrarMensagem(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }
        }
        ui.limparTela();
        player.getBaralhoPessoal().devolverCartasNaoCompradas();

        // ---------------------------FASE DE
        // AÇÃO-----------------------------------------

        int opcao = 0;
        while (opcao != OPCAO_ENCERRA_TURNO && oponente.temInimigosVivos() && player.estaVivo()) {
           
            ui.mostrarStatusTurno(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos());
            opcao = ui.escolherAcaoBatalha();

            if (opcao == OPCAO_VER_CARTAS) {
                ui.limparTela();
                ui.mostrarCartasMao(player.getMao());
            }

            else if (opcao == OPCAO_USAR_CARTAS) {
                ui.mostrarCartasMao(player.getMao());

                if (player.temCartaDisponivel()) {
                    /* a lógica de escolher um índice válido de carta */
                    int i = ui.escolherCartaParaUsar(player.getMao());

                    // A ideia desse switch é pedir a carta o que ela precisa para ser
                    // executada,assim não perco o encapsulamento
                    RequisitoJogo requisito = player.temRequisito(i); // pergunto o que preciso para a carta escolhida
                    String resposta;

                    /* para gerenciar as telas */
                    switch (requisito) {
                        // por enquanto aqui vão entrar carta de dano e carta de efeito direto no
                        // inimigo
                        case RequisitoJogo.INIMIGO:
                            int alvoEscolhido = ui.escolherAlvoInimigo(oponente.getInimigosEscolhidos());

                            ui.limparTela();
                            resposta = player.jogarCarta(i, oponente.getInimigo(alvoEscolhido));
                            ui.mostrarMensagem(resposta);
                            break;

                        /* cartas de escudo e cartas de regeneração */
                        case RequisitoJogo.HEROI:
                            resposta = player.jogarCarta(i, player);
                            ui.mostrarMensagem(resposta);
                            break;

                        /* cartas como efeito em área vão entrar aqui */
                        case RequisitoJogo.TODOS_INIMIGOS:
                            ui.limparTela();
                            resposta = player.jogarCarta(i, oponente.getInimigosEscolhidos());
                            ui.mostrarMensagem(resposta);
                            break;

                        /* Aqui é quando o usuário pode escolher um novo companheiro Herói */
                        case RequisitoJogo.TODOS_HEROIS:
                            int heroiEscolhido = ui.escolherAlvoHeroi(herois.getHeroisEscolhidos());


                            ui.limparTela();
                            resposta = player.jogarCarta(i, herois.getHeroisEscolhidos().get(heroiEscolhido));
                            ui.mostrarMensagem(resposta);
                            break;
                        default:
                            break;
                    }
                } else {
                        ui.mostrarMensagem("Você não tem mais cartas disponíveis !!!");
                }
            }

        }
        ui.limparTela();
        player.limpaMao();
        gm.notificar(player, Estados.FIM_DE_TURNO);
    }

}

