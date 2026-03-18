import java.util.ArrayList;
import java.util.Scanner;

public class App {

  public static final String RESET = "\u001B[0m";
  public static final String NEGRITO = "\u001B[1m";
  public static final String VERMELHO = "\u001B[31m";
  public static final String VERDE = "\u001B[32m";
  public static final String AMARELO = "\u001B[33m";
  public static final String AZUL = "\u001B[34m";
  public static final String CIANO = "\u001B[36m";

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Heroi player1 = new Heroi("Shrek", 100, 20, 5); 
    Inimigo enemy1 = new Inimigo("Dragão", 80, 10);  
    Baralho deck = new Baralho();                
    CartaDano card1 = new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15,0);
    CartaDano card2 = new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano",  1, 10,0);
    CartaDano card3 = new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano",  3, 30,0);
    CartaDano card4 = new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano",  5, 40,0);
    CartaDano card5 = new CartaDano("Like a boss", "Custa 5 de energia e causa 60 de dano",  5, 60,0);
    CartaDano card6 = new CartaDano("Golpe da princesa", "Custa 2 de energia e causa 10 de dano",  2, 10,0);
    CartaDano card7 = new CartaDano("I need a hero", "Custa 3 de energia e causa 25 de dano",  3, 25,0);
    CartaDano card8 = new CartaDano("Biscoito de gengibre gigante", "Custa 4 de energia e causa 35 de dano",  4, 35,0);
    CartaDano card9 = new CartaDano("Golpe flamejante", "Custa 2 de energia e causa 10 de dano",  2, 10,0);

    CartaEscudo shield1 = new CartaEscudo("Proteção","Custa 3 de energia e recebe 20 de escudo", 3, 20,1);
    CartaEscudo shield2 = new CartaEscudo("Beijo de amor verdadeiro","Custa 4 de energia e recebe 50 de escudo", 4, 50,1);



//-------------------------------------------------------------------------------------------------------------------------

    System.out.println(NEGRITO  + "===========================================================" + RESET);
    System.out.println(NEGRITO + CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            " + RESET);
    System.out.println(NEGRITO + "===========================================================" + RESET);
    System.out.println("Use suas cartas para destruir o " + VERMELHO + NEGRITO + "Dragão!" + RESET + "\n");


    enemy1.adiciona_card(card3);
    deck.adicionaBaralho(card1); 
    deck.adicionaBaralho(card2);
    deck.adicionaBaralho(card3); 
    deck.adicionaBaralho(card4); 
    deck.adicionaBaralho(card5); 
    deck.adicionaBaralho(card6);
    deck.adicionaBaralho(card7);
    deck.adicionaBaralho(card8);
    deck.adicionaBaralho(card9);
    deck.adicionaBaralho(shield1);
    deck.adicionaBaralho(shield2);

  
    
    int vida_inicial_p1 = player1.acessoVida(); 
    int vida_inicial_e1 = enemy1.acessoVida(); 

    while (player1.estaVivo() && enemy1.estaVivo()) {
  
      int energia = player1.acessoEnergia(); // energia inicial do personagem heroi.
      player1.resetarEscudo();
      int opcaoCompra = 0;
      int cartasCompradas = 0;
      int limiteCompra = 4;
      deck.criarPilhaCompra(8);

      while (opcaoCompra != 2 && enemy1.estaVivo() && cartasCompradas < limiteCompra) {
        
        System.out.println("\n" + NEGRITO + "=================== STATUS DA BATALHA ===================" + RESET);
      
        System.out.println(CIANO + NEGRITO + player1.acessoNome() + RESET + " " 
            + VERDE + "[VIVO] " + player1.acessoVida() + "/" + vida_inicial_p1 + RESET + " de vida" + "  | "
            + AZUL + "🛡️  " + player1.acessoEscudo() +  RESET + " de escudo");

        System.out.println(NEGRITO + "                           vs" + RESET);

        System.out.println(VERMELHO + NEGRITO + enemy1.acessoNome() + RESET + " " 
            + VERDE + "[VIVO] " + enemy1.acessoVida() + "/" + vida_inicial_e1 + RESET + " de vida" + "  | "
            + AZUL + "🛡️  " + enemy1.acessoEscudo()  + RESET + " de escudo");
        System.out.println(NEGRITO + "=========================================================" + RESET);

        System.out.println(AMARELO + "⚡ Energia: " + RESET  + energia + " disponíveis");
        System.out.println(NEGRITO + "\n--- FASE DE COMPRA ---" + RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(NEGRITO + "Escolha: " + RESET);
        opcaoCompra = sc.nextInt();
        
        if (opcaoCompra == 1) {
          deck.imprimePilhaCompra();
          int i = sc.nextInt();
          if (deck.tamanhoPilha() < i ){
            System.out.println(VERMELHO + "Opção inválida!" + RESET);

          } else{
          deck.compraCarta(player1, i);
          System.out.println(VERDE + "Você comprou uma carta!" + RESET); 
          cartasCompradas++;}

        } else if (opcaoCompra != 2) {
            System.out.println(VERMELHO + "Opção inválida!" + RESET);
        }
      }



//-------------------------------------------------------------------------------------

        int opcao = 0;
        while (opcao != 3 && enemy1.estaVivo()) {

          System.out.println("\n" + NEGRITO + "=================== STATUS DA BATALHA ===================" + RESET);
          
          System.out.println(CIANO + NEGRITO + player1.acessoNome() + RESET + " " 
              + VERDE + "[VIVO] " + player1.acessoVida() + "/" + vida_inicial_p1 + RESET + " de vida" + "  | "
              + AZUL + "🛡️  " + player1.acessoEscudo() +  RESET + " de escudo");

          System.out.println(NEGRITO + "                           vs" + RESET);

          System.out.println(VERMELHO + NEGRITO + enemy1.acessoNome() + RESET + " " 
              + VERDE + "[VIVO] " + enemy1.acessoVida() + "/" + vida_inicial_e1 + RESET + " de vida" + "  | "
              + AZUL + "🛡️  " + enemy1.acessoEscudo()  + RESET + " de escudo");
          System.out.println(NEGRITO + "=========================================================" + RESET);

          System.out.println(AMARELO + "⚡ Energia: " + RESET  + energia + " disponíveis");


          System.out.println("1 - Ver mão");
          System.out.println("2 - Usar Cartas");
          System.out.println("3 - Encerrar Turno");
          System.out.print(NEGRITO + "Escolha: " + RESET);

          opcao = sc.nextInt();

          if (opcao == 1) {
            System.out.println("\nSuas Cartas na Mão:");
            player1.imprimeCartas();
          }


          else if (opcao == 2) {
            System.out.println(CIANO + "\nSuas Cartas na Mão:" + RESET);
            player1.imprimeCartas();
            
            System.out.print(NEGRITO + "Escolha o número da carta para usar: " + RESET);
            int i = sc.nextInt();
            ArrayList<Carta> vetor = player1.getMaoJogador();

            if (i>=0 && player1.maoVazia() == false){
              Carta carta_escolhida = vetor.get(i);
              int custo = carta_escolhida.acessoCusto();
    
              if (energia >= custo) {
                
                if (carta_escolhida.acessopcaocarta() == 0){
                  carta_escolhida.usar(enemy1, deck);
                  player1.removeCartaMaoJogador(deck, i);
                  energia -= custo;
                  System.out.println(VERMELHO + "\n⚔️  Você usou " + carta_escolhida.acessoNome() + " e causou dano!" + RESET);

                } else {
                  custo = carta_escolhida.acessoCusto();
                  if (energia >= custo) {
                    carta_escolhida.usar(player1, deck);
                    player1.removeCartaMaoJogador(deck, i);
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
          player1.resetaMaoJogador(deck);
          deck.resetaBaralho();
          deck.embaralhaBaralho();
          break; 
        }

        else {
            System.out.println(VERMELHO + "Opção inválida!" + RESET);
        }
      }



          
        /* ataque do inimigo */
        if (enemy1.estaVivo()) { // o inimigo só vai atacar se estiver vivo (caso a gente mate ele antes de passar de turno)
          System.out.println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);
          System.out.println(VERMELHO + "O " + enemy1.acessoNome() + " está atacando..." + RESET);
          enemy1.atacar(player1);
          System.out.println(enemy1.acessoNome() + " executou o ataque e causou " + VERMELHO + enemy1.acessoDano() + RESET + " de dano!");
          System.out.println(VERMELHO + NEGRITO + "======================================================\n" + RESET);
        }

      }
    

        /* vamos verificar quem ganhou o jogo */
        System.out.println("\n" + NEGRITO + "==================== FIM DE JOGO ====================" + RESET);
        if (player1.estaVivo()) {
          System.out.println(NEGRITO + VERDE + "🎉 Parabéns, você ganhou !! \uD83D\uDE0A" + RESET);
        } else {
          System.out.println(NEGRITO + VERMELHO + "💀 Você foi derrotado ! \uD83D\uDE35" + RESET);
        }
        System.out.println(NEGRITO + "=====================================================" + RESET);
      }

    }


