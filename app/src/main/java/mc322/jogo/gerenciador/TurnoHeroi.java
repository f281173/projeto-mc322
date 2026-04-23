package mc322.jogo.gerenciador;

    import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.entidades.Heroi;
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
    public void jogar(Heroi player, Jogador herois, Oponente oponente, Prints tela, Scanner sc) {

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
            
            tela.statusBatalha(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos()); // [TERMINAL]
            tela.energia(player.getEnergiaAtual()); // [TERMINAL]
            tela.faseCompra(limiteCompra, cartasCompradas); // [TERMINAL]

            opcaoCompra = sc.nextInt(); // [TERMINAL]

            if (opcaoCompra == COMPRA_CARTA) {
                player.getBaralhoPessoal().imprimePilhaCompra();
                int i = sc.nextInt(); // [TERMINAL]

                if (i < 0 || player.getBaralhoPessoal().tamanhoPilha() <= i) {
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET); // [TERMINAL] vamos usar mostrarMensagem()

                } else {
                    player.getBaralhoPessoal().compraCarta(player, i);
                    Prints.limparTela();   // se possivel tirar ou criar mais um método em terminal.
                    System.out.println("\n"); //se possivel tirar
                    System.out.println(Cores.VERDE + "Você comprou uma carta!" + Cores.RESET); // [TERMINAL] vamos usar mostrarMensagem()
                    cartasCompradas++;
                }

            } else if (opcaoCompra != ENCERRAR_FASE_COMPRA) {
                System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET); // [TERMINAL] vamos usar mostrarMensagem()
            }
        }
        Prints.limparTela();  // criar o método de limparTela no terminal.
        player.getBaralhoPessoal().devolverCartasNaoCompradas();

        // ---------------------------FASE DE
        // AÇÃO-----------------------------------------

        int opcao = 0;
        while (opcao != OPCAO_ENCERRA_TURNO && oponente.temInimigosVivos() && player.estaVivo()) {
           
            tela.statusBatalha(player, herois.getHeroisEscolhidos(), oponente.getInimigosEscolhidos()); // [TERMINAL]
            tela.energia(player.getEnergiaAtual()); // [TERMINAL]

            tela.faseBatalha(); // [TERMINAL]
            opcao = sc.nextInt(); // [TERMINAL]

            if (opcao == OPCAO_VER_CARTAS) {
                Prints.limparTela();  // criar o método de limparTela no terminal.
                System.out.println("\nSuas Cartas na Mão:"); // [TERMINAL]
                player.imprimeMaoJogador(); // [TERMINAL] já foi implementado em terminal
            }

            else if (opcao == OPCAO_USAR_CARTAS) {
                
                System.out.println(Cores.CIANO + "\nSuas Cartas na Mão:" + Cores.RESET);
                player.imprimeMaoJogador(); // [TERMINAL] já foi implementado em terminal

                if (player.temCartaDisponivel()) { // [TERMINAL]
                    System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET); // [TERMINAL]

                    /* a lógica de escolher um índice válido de carta */
                    int i = sc.nextInt(); // [TERMINAL]
                    while (!player.temOpcaoCartaMao(i)) { // [TERMINAL]
                        System.out.println(Cores.VERMELHO + "Carta inválida!" + Cores.RESET); // [TERMINAL]
                        System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET); // [TERMINAL]
                        i = sc.nextInt(); // [TERMINAL]
                    }

                    // A ideia desse switch é pedir a carta o que ela precisa para ser
                    // executada,assim não perco o encapsulamento
                    RequisitoJogo requisito = player.temRequisito(i); // pergunto o que preciso para a carta escolhida
                    String resposta;

                    /* para gerenciar as telas */
                    switch (requisito) {
                        // por enquanto aqui vão entrar carta de dano e carta de efeito direto no
                        // inimigo
                        case RequisitoJogo.INIMIGO:

                            /*
                             * vamos gerenciar a tela para ele escolher um inimigo necessário para a carta
                             * que ele escolheu
                             */
                            System.out.println(Cores.VERMELHO + "\nEscolha o seu alvo: " + Cores.RESET);
                            oponente.imprimeInimigosVivos(); // isso vamos ter que mudar para poder funcionar até mesmo alguns prints

                            System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
                            int alvoEscolhido = sc.nextInt();

                            /* valida a entrada do usuário */
                            while (!oponente.validaEscolhaInimigo(alvoEscolhido)) { // [TERMINAL]
                                System.out.println(Cores.VERMELHO + "Alvo inválido!" + Cores.RESET);// [TERMINAL]
                                System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);// [TERMINAL]
                                alvoEscolhido = sc.nextInt();// [TERMINAL]
                            }
                            Prints.limparTela(); // [TERMINAL] vamos implementar
                            resposta = player.jogarCarta(i, oponente.getInimigo(alvoEscolhido));
                            System.out.println(resposta); // [TERMINAL] vamos usar mostrarMensagem por enquanto
                            break;

                        /* cartas de escudo e cartas de regeneração */
                        case RequisitoJogo.HEROI:
                            resposta = player.jogarCarta(i, player);
                            System.out.println(resposta); // [TERMINAL] vamos usar mostrarMensagem por enquanto
                            break;

                        /* cartas como efeito em área vão entrar aqui */
                        case RequisitoJogo.TODOS_INIMIGOS:
                            Prints.limparTela(); 
                            resposta = player.jogarCarta(i, oponente.getInimigosEscolhidos());
                            System.out.println(resposta); // [TERMINAL] vamos usar mostrarMensagem por enquanto
                            break;

                        /* Aqui é quando o usuário pode escolher um novo companheiro Herói */
                        case RequisitoJogo.TODOS_HEROIS:
                            System.out.println(Cores.VERDE + "\nEscolha o seu Herói: " + Cores.RESET); // [TERMINAL]
                            herois.imprimeHeroisVivos(); // [TERMINAL] podekmos modificar o imprimeHerois vivos e iniimigos vivos

                            System.out.print(Cores.NEGRITO + "Herói: " + Cores.RESET); // [TERMINAL]
                            int heroiEscolhido = sc.nextInt(); // [TERMINAL]

                            /* valida entrada do usuário */
                            while (!herois.validaEscolhaHeroi(heroiEscolhido)) {
                                System.out.println(Cores.VERMELHO + "Heroi inválido!" + Cores.RESET); // [TERMINAL]
                                System.out.print(Cores.NEGRITO + "Heroi: " + Cores.RESET); // [TERMINAL]
                                heroiEscolhido = sc.nextInt(); // [TERMINAL]
                            }

                            Prints.limparTela(); // [TERMINAL] temos que implementar ainda

                            resposta = player.jogarCarta(i, herois.getHeroisEscolhidos().get(heroiEscolhido));
                            System.out.println(resposta); // [TERMINAL] vamos usar mostrarMensagem()
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Você não tem mais cartas disponíveis !!!"); // [TERMINAL] vamos usar mostrarMensagem por enquanto
                }
            }

        }
        Prints.limparTela();  // [TERMINAL] vamos talvez criar um novo método para colocar aqui
        player.limpaMao();
        gm.notificar(player, Estados.FIM_DE_TURNO);
    }

}

