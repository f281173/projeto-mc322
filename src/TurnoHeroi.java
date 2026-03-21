import java.util.ArrayList;
import java.util.Scanner;

public class TurnoHeroi {
    
    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    public void jogar(Heroi player, Inimigo enemy, Prints tela, Baralho deck, Scanner sc){
        
        int energia = player.acessoEnergia(); // energia inicial do personagem heroi.
        player.resetarEscudo();
        int opcaoCompra = 0;
        int cartasCompradas = 0;
        int limiteCompra = 4;
        deck.criarPilhaCompra(8);

        while (opcaoCompra != 2 && enemy.estaVivo() && cartasCompradas < limiteCompra) {
            
            tela.status_batalha(player, enemy);
            tela.energia(energia);
            tela.fase_compra(limiteCompra, cartasCompradas);

            opcaoCompra = sc.nextInt();
            
            if (opcaoCompra == 1) {
            deck.imprimePilhaCompra();
            int i = sc.nextInt();
            if (deck.tamanhoPilha() < i ){
                System.out.println(VERMELHO + "Opção inválida!" + RESET);

            } else{
            deck.compraCarta(player, i);
            System.out.println(VERDE + "Você comprou uma carta!" + RESET); 
            cartasCompradas++;}

            } else if (opcaoCompra != 2) {
                System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        }



        int opcao = 0;
        while (opcao != 3 && enemy.estaVivo()) {

            tela.status_batalha(player, enemy);
            tela.energia(energia);

            tela.fase_batalha();
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

                if (i>=0 && player.maoVazia() == false){
                Carta carta_escolhida = vetor.get(i);
                int custo = carta_escolhida.acessoCusto();
        
                if (energia >= custo) {
                    
                    if (carta_escolhida.acessopcaocarta() == 0){
                    carta_escolhida.usar(enemy, deck);
                    player.removeCartaMaoJogador(deck, i);
                    energia -= custo;
                    System.out.println(VERMELHO + "\n⚔️  Você usou " + carta_escolhida.acessoNome() + " e causou dano!" + RESET);

                    } else {
                    custo = carta_escolhida.acessoCusto();
                    if (energia >= custo) {
                        carta_escolhida.usar(player, deck);
                        player.removeCartaMaoJogador(deck, i);
                        energia -= custo;
                        System.out.println(AZUL + "\n🛡️  Você ativou " + carta_escolhida.acessoNome() + " e ganhou escudo!" + RESET);
                    } else {
                        System.out.println(NEGRITO + VERMELHO + "\n⚠️  VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
                    }
                    }
                    

                } else {
                    System.out.println(NEGRITO + VERMELHO + "\n⚠️  VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
                }
                } else{
                    System.out.println(VERMELHO + "\nOpção de carta inválida!" + RESET);
                }
            }
            
            else if (opcao == 3) {
                player.resetaMaoJogador(deck);
                deck.resetaBaralho();
                deck.embaralhaBaralho();
                break; 
            }

            else {
                System.out.println(VERMELHO + "Opção inválida!" + RESET);
            }
        }



        }
    }





        



    
