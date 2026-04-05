package mc322.jogo.gerenciador;
import java.util.ArrayList;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.RequisitoJogo;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.observer.Estados;
import mc322.jogo.RequisitoJogo;

/**
 * Classe responsável por gerenciar a interação do jogador durante o turno de um
 * Herói.
 * Divide-se em duas etapas principais: Fase de Compra (Loja) e Fase de Ação
 * (Combate).
 */
public class TurnoHeroi {
    private GameManager gm;

    /*criar constantes para melhorar a legibilidade*/
    private static final int OPCAO_ENCERRA_TURNO = 3;
    private static final int OPCAO_VER_CARTAS = 1;
    private static final int OPCAO_USAR_CARTAS = 2;
    private static final int ENCERRAR_FASE_COMPRA = 2;
    private static final int COMPRA_CARTA = 1;

    public TurnoHeroi(GameManager gm) {
        this.gm = gm;
    }

    // Turno de um herói específico
    public void jogar(Heroi player, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos, Prints tela, Baralho deck,
            Scanner sc) {

        int energia = player.getEnergia();
        player.zeraEscudo();
        int opcaoCompra = 0;
        int cartasCompradas = 0;
        int limiteCompra = 3;
        deck.criarPilhaCompra(6);

        /* vamos notificar que iniciou o turno para a entidade */
        gm.notificar(player, Estados.INICIO_DE_TURNO);

        boolean temInimigoVivo = false;
        for (Inimigo ini : inimigos) {
            if (ini.estaVivo()) {
                temInimigoVivo = true;
                break;
            }
        }

        // ----------------------------FASE DE COMPRA ----------------------------------

        while (opcaoCompra != ENCERRAR_FASE_COMPRA && temInimigoVivo && cartasCompradas < limiteCompra) {

            tela.status_batalha(player, herois, inimigos);
            tela.energia(energia);
            tela.faseCompra(limiteCompra, cartasCompradas);

            opcaoCompra = sc.nextInt();

            if (opcaoCompra == COMPRA_CARTA) {
                deck.imprimePilhaCompra();
                int i = sc.nextInt();

                if (i < 0 || deck.tamanhoPilha() <= i) {
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);

                } else {
                    deck.compraCarta(player, i);
                    System.out.println(Cores.VERDE + "Você comprou uma carta!" + Cores.RESET);
                    cartasCompradas++;
                }

            } else if (opcaoCompra != ENCERRAR_FASE_COMPRA) {
                System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }
        }

        deck.devolverCartasNaoCompradas();

        // ---------------------------FASE DE AÇÃO
        // -----------------------------------------

        int opcao = 0;
        while (opcao != OPCAO_ENCERRA_TURNO) {

            tela.status_batalha(player, herois, inimigos);
            tela.energia(energia);

            tela.faseBatalha();
            opcao = sc.nextInt();

            if (opcao == OPCAO_VER_CARTAS) {
                System.out.println("\nSuas Cartas na Mão:");
                player.imprimeMaoJogador();
            }

            else if (opcao == OPCAO_USAR_CARTAS) {
                System.out.println(Cores.CIANO + "\nSuas Cartas na Mão:" + Cores.RESET);
                player.imprimeMaoJogador(); //pode imprimir que a mão está vazia também

                if (player.temCartaDisponivel()) { //verifica se a mão não está vazia
                    System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET);

                    /*a lógica de escolher um índice válido de carta */
                    int i = sc.nextInt();
                    while (!player.temOpcaoCartaMao(i)) {
                        System.out.println("Escolha uma carta válida !!");
                        i = sc.nextInt();
                    }
                    switch (RequisitoJogo) {
                        case value:
                            
                            break;
                    
                        default:
                            break;
                    }

                    ArrayList<Carta> vetor = player.getMaoJogador(); //quebra totalmente o encapsulamento
                    
                    if (i >= 0 && i < vetor.size()) {
                        Carta cartaEscolhida = vetor.get(i);
                        int custo = cartaEscolhida.getCusto();
    
                        if (energia >= custo) {
    
                            // Verifica o tipo de carta
                            if (cartaEscolhida.getOpcaoCarta() == 0) {
                                /* tenho que publicar que heroi vai atacar */
                                gm.notificar(player, Estados.ATAQUE);
    
                                System.out.println(Cores.VERMELHO + "\nEscolha o alvo do seu ataque: " + Cores.RESET);
                                for (int j = 0; j < inimigos.size(); j++) {
                                    if (inimigos.get(j).estaVivo()) {
                                        System.out.println(j + " - " + inimigos.get(j).getNome() + " (Vida: "
                                                + inimigos.get(j).getVida() + ")");
                                    }
                                }
    
                                System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
                                int alvoEscolhido = sc.nextInt();
    
                                if (alvoEscolhido >= 0 && alvoEscolhido < inimigos.size()
                                        && inimigos.get(alvoEscolhido).estaVivo()) {
                                    Inimigo alvo = inimigos.get(alvoEscolhido);
                                    cartaEscolhida.usar(alvo, deck);
    
                                    player.removeCartaMaoJogador(deck, i);
                                    energia -= custo;
                                    System.out.println(Cores.VERMELHO + "\n⚔️ Você usou " + cartaEscolhida.getNome() + " no "
                                            + alvo.getNome() + " e causou dano! " + Cores.RESET);
                                } else {
                                    System.out.println(Cores.VERMELHO + "Alvo inválido!" + Cores.RESET);
                                }
    
                            } else if (cartaEscolhida.getOpcaoCarta() == 1) {
                                cartaEscolhida.usar(player, deck);
                                player.removeCartaMaoJogador(deck, i);
                                energia -= custo;
                                System.out.println(Cores.AZUL + "\n🛡️ Você ativou " + cartaEscolhida.getNome()
                                        + " e ganhou escudo!" + Cores.RESET);
    
                            } else if (cartaEscolhida.getOpcaoCarta() == 2) {
                                CartaEfeito carta = (CartaEfeito) cartaEscolhida;
                                System.out.println(Cores.VERMELHO + "\nEscolha o alvo para aplicar o efeito: " + Cores.RESET);
                                for (int j = 0; j < inimigos.size(); j++) {
                                    if (inimigos.get(j).estaVivo()) {
                                        System.out.println(j + " - " + inimigos.get(j).getNome() + " (Vida: "
                                                + inimigos.get(j).getVida() + ")");
                                    }
                                }
    
                                System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
                                int alvoEscolhido = sc.nextInt();
    
                                if (alvoEscolhido >= 0 && alvoEscolhido < inimigos.size()
                                        && inimigos.get(alvoEscolhido).estaVivo()) {
                                    Inimigo alvo = inimigos.get(alvoEscolhido);
                                    cartaEscolhida.usar(alvo, deck);
                                    player.removeCartaMaoJogador(deck, i);
                                    energia -= custo;
                                    System.out.println(Cores.AZUL + "\n Você ativou " + carta.getNome()
                                            + " e aplicou o " + carta.getNome() + " : " + carta.getDescricao() + Cores.RESET);
                                    carta.explicaEfeito(carta.getTipo());
                                } else {
                                    System.out.println(Cores.VERMELHO + "Alvo inválido!" + Cores.RESET);
                                }
    
                            } else {
                                System.out.println(Cores.NEGRITO + Cores.VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + Cores.RESET);
                            }
    
                        } else {
                            System.out.println(Cores.VERMELHO + "\nOpção de carta inválida!" + Cores.RESET);
                        }
                    }
                }

            } else if (opcao != OPCAO_ENCERRA_TURNO) {
                System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }

        }
        /* aqui acaba o turno do jogador */
        player.resetaMaoJogador(deck);
        gm.notificar(player, Estados.FIM_DE_TURNO);
    }
}
