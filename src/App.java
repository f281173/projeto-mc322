import java.util.Scanner;


public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Prints tela = new Prints();
        
        GameManager gm = new GameManager();
        gm.prepararPartida(sc, tela);
    }
} 
    