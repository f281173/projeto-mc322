import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Heroi player1 = new Heroi("Shrek", 100, 20, 5, 10); //criei o atributo capacidade que é a quantidade de cartas
    Inimigo enemy1 = new Inimigo("Dragão", 80, 10, 40);                     // que o Heoroi pode ter.
    CartaDano card1 = new CartaDano("Bola de Fogo", 2, 15);
    CartaDano card2 = new CartaDano("Corte de Espada", 1, 10);
    CartaEscudo shield = new CartaEscudo("Proteção", 3, 20);

    player1.adiciona_card(card1); 
    player1.adiciona_card(card2); 
    System.out.println("\n-----------------------------------------------------------\n"); // teste para ver se fica melhor a visualização (?)


    int vida_inicial_p1 = player1.acessoVida(); 
    int vida_inicial_e1 = enemy1.acessoVida(); 

    while (player1.estaVivo() && enemy1.estaVivo()) {
  
      int energia = player1.acessoEnergia(); // energia inicial do personagem heroi.
      int opcao = 0;
      player1.resetarEscudo();

      
      while (opcao != 3 && enemy1.estaVivo()) { // eu posso matar o inimigo antes de passar de turno (adicionei um condicional a mais)
        // bloco com as opções
        System.out.println(player1.acessoNome() + " " + player1.acessoVida() + "/" + vida_inicial_p1 + " de vida  | "
            + player1.acessoEscudo() + " de escudo");
        System.out.println("vs");
        System.out.println(enemy1.acessoNome() + " " + enemy1.acessoVida() + "/" + vida_inicial_e1 + " de vida  | "
            + enemy1.acessoEscudo() + " de escudo ");

        System.out.println("Energia: " + energia + " de energia disponível"); // linha corrigida
        System.out.println("1 - Usar Carta de Dano");
        System.out.println("2 - Usar Carta de Escudo");
        System.out.println("3 - Encerrar Turno");
        System.out.println("Escolha:");

        opcao = sc.nextInt();

        if (opcao == 1) {
          System.out.println("Suas Cartas:");
          player1.imprimeCartasDano();

          System.out.println("Escolha a carta:");
          int i = sc.nextInt();
          CartaDano[] vetor = player1.acessoVetorCartaDano();
          CartaDano carta_escolhida = vetor[i];
          int custo = carta_escolhida.acessoCartaDano_custo();

          if (energia >= custo) {
            enemy1.ReceberDano(player1, carta_escolhida.acessoCartaDano_nome());
            energia -= custo;
            System.out.println("Você usou " + carta_escolhida.acessoCartaDano_nome() + "!");
          } else {
            System.out.println("VOCÊ  NÃO TEM MAIS ENERGIA!");
          }
        }

        else if (opcao == 2) {
          int custo = shield.acessoCusto();
          if (energia >= custo) {
            player1.ganhaEscudo(shield);
            energia -= custo;
            System.out.println("Você ganhou escudo!");
          } else {
            System.out.println("VOCÊ  NÃO TEM MAIS ENERGIA!");
          }

        }

        else {
          break; // ja tem a condição no while (?)
        }
        System.out.println("\n-----------------------------------------------------------\n"); // teste para ver se melhora a visualização (?)
      }

      /* ataque do inimigo */
      if (enemy1.estaVivo()) { // o inimigo só vai atacar se estiver vivo (caso a gente mate ele antes de passar de turno)
        System.out.println("\nTurno do Inimigo:");
        enemy1.atacar(player1);
        System.out.println(enemy1.acessoNome() + " executou o ataque");
        System.out.println("\n-----------------------------------------------------------\n"); // teste para ver se melhora a visualização (?)
      }

    }

    /* vamos verificar quem ganhou o jogo */
    if (player1.estaVivo()) {
      System.out.println("Parabéns, você ganhou !!");
    } else {
      System.out.println("Você foi derrotado !" + "\uD83D\uDE35"); // testei se fica legal a carinha aqui no derrotado
    }
  }

}


