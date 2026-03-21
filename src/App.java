
public class App {

  public static final String RESET = "\u001B[0m";
  public static final String NEGRITO = "\u001B[1m";
  public static final String VERMELHO = "\u001B[31m";
  public static final String VERDE = "\u001B[32m";
  public static final String AMARELO = "\u001B[33m";
  public static final String AZUL = "\u001B[34m";
  public static final String CIANO = "\u001B[36m";

  public static void main(String[] args) {
    
    GameManager game_manager = new GameManager();
    game_manager.partida();

  }

}
    
    
    