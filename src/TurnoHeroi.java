import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar a interação do jogador durante o turno de um
 * Herói.
 * Divide-se em duas etapas principais: Fase de Compra (Loja) e Fase de Ação
 * (Combate).
 */

public class TurnoHeroi {
    private GameManager gm;

    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    public TurnoHeroi(GameManager gm) {
        this.gm = gm;
    }

    // Turno de um herói específico
    public void jogar(Heroi player, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos, Prints tela, Baralho deck,
            Scanner sc) {

        int energia = player.getEnergia();
        player.resetarEscudo();
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

        while (opcaoCompra != 2 && temInimigoVivo && cartasCompradas < limiteCompra) {

            tela.status_batalha(player, herois, inimigos);
            tela.energia(energia);
            tela.faseCompra(limiteCompra, cartasCompradas);

            opcaoCompra = sc.nextInt();

            if (opcaoCompra == 1) {
                deck.imprimePilhaCompra();
                int i = sc.nextInt();

                if (i < 0 || deck.tamanhoPilha() <= i) {
                    System.out.println(VERMELHO + "Opção inválida!" + RESET);

                } else {
                    deck.compraCarta(player, i);
                    System.out.println(VERDE + "Você comprou uma carta!" + RESET);
                    cartasCompradas++;
                }

            } else if (opcaoCompra != 2) {
                System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        }

        deck.devolverCartasNaoCompradas();

        // ---------------------------FASE DE AÇÃO
        // -----------------------------------------

        int opcao = 0;
        while (opcao != 3) {

            tela.status_batalha(player, herois, inimigos);
            tela.energia(energia);

            tela.faseBatalha();
            opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.println("\nSuas Cartas na Mão:");
                player.imprimeCartas();
            }

            else if (opcao == 2) {
                System.out.println(CIANO + "\nSuas Cartas na Mão:" + RESET);
                player.imprimeCartas();

                System.out.print(NEGRITO + "Escolha o número da carta para usar: " + RESET);
                int i = sc.nextInt();
                ArrayList<Carta> vetor = player.getMaoJogador();

                if (i >= 0 && i < vetor.size() && player.maoVazia() == false) {
                    Carta cartaEscolhida = vetor.get(i);
                    int custo = cartaEscolhida.getCusto();

                    if (energia >= custo) {

                     if (cartaEscolhida.getOpcaoCarta() == 0) {
                            /* tenho que publicar que heroi vai atacar */
                            gm.notificar(player, Estados.ATAQUE);

                            if (cartaEscolhida instanceof CartaDanoArea) {
                                CartaDanoArea cartaArea = (CartaDanoArea) cartaEscolhida;
                                cartaArea.usarEmArea(inimigos, deck);
                                player.removeCartaMaoJogador(deck, i);
                                energia -= custo;
                                System.out.println(VERMELHO + "\n TOMA!!! Você usou " + cartaEscolhida.getNome() + " e atingiu TODOS os inimigos!" + RESET);

                            } else {
                                System.out.println(VERMELHO + "\nEscolha o alvo do seu ataque: " + RESET);
                                for (int j = 0; j < inimigos.size(); j++) {
                                    if (inimigos.get(j).estaVivo()) {
                                        System.out.println(j + " - " + inimigos.get(j).getNome() + " (Vida: "
                                                + inimigos.get(j).getVida() + ")");
                                    }
                                }

                                System.out.print(NEGRITO + "Alvo: " + RESET);
                                int alvoEscolhido = sc.nextInt();

                                if (alvoEscolhido >= 0 && alvoEscolhido < inimigos.size()
                                        && inimigos.get(alvoEscolhido).estaVivo()) {
                                    Inimigo alvo = inimigos.get(alvoEscolhido);
                                    cartaEscolhida.usar(alvo, deck);

                                    player.removeCartaMaoJogador(deck, i);
                                    energia -= custo;
                                    System.out.println(VERMELHO + "\n⚔️ Você usou " + cartaEscolhida.getNome() + " no "
                                            + alvo.getNome() + " e causou dano! " + RESET);
                                } else {
                                    System.out.println(VERMELHO + "Alvo inválido!" + RESET);
                                }
                            }

                        } else if (cartaEscolhida.getOpcaoCarta() == 1) {
                            cartaEscolhida.usar(player, deck);
                            player.removeCartaMaoJogador(deck, i);
                            energia -= custo;
                            System.out.println(AZUL + "\n🛡️ Você ativou " + cartaEscolhida.getNome()
                                    + " e ganhou escudo!" + RESET);

                        }else if (cartaEscolhida.getOpcaoCarta() == 2) {
                            CartaEfeito carta = (CartaEfeito) cartaEscolhida;
                            
                            if (carta.getTipoEfeito() == TiposEfeitos.FORCA) {
                                System.out.println(VERDE + "\nEscolha o aliado para receber o Buff de Força:" + RESET);
                                for (int j = 0; j < herois.size(); j++) {
                                    if (herois.get(j).estaVivo()) {
                                        System.out.println(j + " - " + herois.get(j).getNome() + " (Vida: " + herois.get(j).getVida() + ")");
                                    }
                                }

                                System.out.print(NEGRITO + "Alvo Aliado: " + RESET);
                                int alvoIndex = sc.nextInt();

                                if (alvoIndex >= 0 && alvoIndex < herois.size() && herois.get(alvoIndex).estaVivo()) {
                                    Heroi aliado = herois.get(alvoIndex);
                                    carta.usar(aliado, deck); 
                                    
                                    player.removeCartaMaoJogador(deck, i);
                                    energia -= custo;
                                    System.out.println(VERDE + "\n💪 " + aliado.getNome() + " usou " + carta.getNome() + " e ficou mais forte!" + RESET);
                                } else {
                                    System.out.println(VERMELHO + "Alvo inválido!" + RESET);
                                }

                            } else {
                                System.out.println(VERMELHO + "\nEscolha o alvo para aplicar o efeito: " + RESET);
                                for (int j = 0; j < inimigos.size(); j++) {
                                    if (inimigos.get(j).estaVivo()) {
                                        System.out.println(j + " - " + inimigos.get(j).getNome() + " (Vida: "
                                                + inimigos.get(j).getVida() + ")");
                                    }
                                }

                                System.out.print(NEGRITO + "Alvo: " + RESET);
                                int alvoEscolhido = sc.nextInt();

                                if (alvoEscolhido >= 0 && alvoEscolhido < inimigos.size()
                                        && inimigos.get(alvoEscolhido).estaVivo()) {
                                    Inimigo alvo = inimigos.get(alvoEscolhido);
                                    cartaEscolhida.usar(alvo, deck);
                                    player.removeCartaMaoJogador(deck, i);
                                    energia -= custo;
                                    System.out.println("Você usou '" + carta.getNome() +  "' e aplicou um efeito de " 
                                            + carta.getTipoEfeito() + " que durará por " + carta.getAcumulo() + " turnos!" + RESET);
                                    
                                    carta.explicaEfeito(carta.tipo); 
                                    
                                } else {
                                    System.out.println(VERMELHO + "Alvo inválido!" + RESET);
                                }
                            }

                        } 
                        
                        
                    } else {
                        System.out.println(NEGRITO + VERMELHO + "\n⚠️ VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
                    }

                } else {
                    System.out.println(VERMELHO + "\nOpção de carta inválida!" + RESET);
                }

            } else if (opcao != 3) {
                System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }

        }
        
        player.resetaMaoJogador(deck);
        gm.notificar(player, Estados.FIM_DE_TURNO);
    }
}
