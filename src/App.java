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
    CartaDano card1 = new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15);
    CartaDano card2 = new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano",  1, 10);
    CartaDano card3 = new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano",  3, 30);
    CartaDano card4 = new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano",  5, 40);

    CartaEscudo shield = new CartaEscudo("Proteção","Custa 3 de energia e recebe 20 de escudo", 3, 20);
    
    System.out.println(NEGRITO  + "===========================================================" + RESET);
    System.out.println(NEGRITO + CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            " + RESET);
    System.out.println(NEGRITO + "===========================================================" + RESET);
    System.out.println("Use suas cartas para destruir o " + VERMELHO + NEGRITO + "Dragão!" + RESET + "\n");


    enemy1.adiciona_card(card2);
    deck.adicionaBaralho(card1); 
    deck.adicionaBaralho(card2);
    deck.adicionaBaralho(card3); 
    deck.adicionaBaralho(card4);  
    
    int vida_inicial_p1 = player1.acessoVida(); 
    int vida_inicial_e1 = enemy1.acessoVida(); 

    while (player1.estaVivo() && enemy1.estaVivo()) {
  
      int energia = player1.acessoEnergia(); // energia inicial do personagem heroi.
      int opcao = 0;
      player1.resetarEscudo();

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
        System.out.println(" - Comprar Cartas");
        System.out.println("Você deve comprar 4 cartas das 8 disponíveis:");
        //imprime x cartas do baralho
        System.out.println("Você tem mais x cartas para comprar");

        System.out.println("1 - Comprar carta");
        System.out.println("2 -  Usar Cartas");
        







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
          player1.imprimeCartas();
        }



        else if (opcao == 2) {
          //------------ver mao------------------
            player1.imprimeCartas();
            System.out.print(NEGRITO + "Escolha: " + RESET);

            int i = sc.nextInt();
          }

            /*            
             ArrayList<CartaDano> vetor = player1.getMaoJogador();
            CartaDano carta_escolhida = vetor.get(i);
            int custo = carta_escolhida.acessoCusto();

            if (energia >= custo) {
              enemy1.recebeDano(player1, carta_escolhida);
              energia -= custo;
              System.out.println(VERMELHO + "\n⚔️  Você usou " + carta_escolhida.acessoNome() + " e causou dano!" + RESET);
            } else {
              System.out.println(NEGRITO + VERMELHO + "\n⚠️  VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
      

            int i = sc.nextInt();
            ArrayList<CartaDano> vetor = player1.getMaoJogador();
            CartaDano carta_escolhida = vetor.get(i);
            int custo = carta_escolhida.acessoCusto();


            int custo = shield.acessoCusto();
            if (energia >= custo) {
              player1.ganhaEscudo(shield);
              energia -= custo;
              System.out.println(AZUL + "\n🛡️  Você ativou " + shield.acessoNome() + " e ganhou escudo!" + RESET);
            } else {
              System.out.println(NEGRITO + VERMELHO + "\n⚠️  VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
            }
          

            int custo = shield.acessoCusto();
            if (energia >= custo) {
              player1.ganhaEscudo(shield);
              energia -= custo;
              System.out.println(AZUL + "\n🛡️  Você ativou " + shield.acessoNome() + " e ganhou escudo!" + RESET);
            } else {
              System.out.println(NEGRITO + VERMELHO + "\n⚠️  VOCÊ NÃO TEM MAIS ENERGIA!" + RESET);
            }

          }
          */


        else {
          player1.resetaMaoJogador();
          break; 
        }
        
      }


      /* ataque do inimigo */
      if (enemy1.estaVivo()) { // o inimigo só vai atacar se estiver vivo (caso a gente mate ele antes de passar de turno)
        System.out.println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);
        System.out.println(VERMELHO + "O " + enemy1.acessoNome() + " está atacando..." + RESET);
        enemy1.atacar(player1);
        System.out.println(enemy1.acessoNome() + " executou o ataque");
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


