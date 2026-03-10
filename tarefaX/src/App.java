

public class App {
    public static void main(String[] args) throws Exception {

      Heroi player1 = new Heroi("Shrek", 100, 10);
      Inimigo enemy1 = new Inimigo("Dragão", 80, 10);
      CartaDano card1 = new CartaDano("Bola de Fogo", 2, 10);
      CartaDano card2 = new CartaDano("Corte de Espada", 1, 15);  


      player1.adiciona_card(card1, 0);
      player1.adiciona_card(card2, 1);
      
     // player1.setDano(inventario);

     
    
    while (player1.Estar_vivo() == 1 && enemy1.estaVivo()) {
      int energiaTurno = 3;
      boolean turnoHeroi = true;

    }
  } 

} 
         







