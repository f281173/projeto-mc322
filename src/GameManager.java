import java.util.Scanner;
public class GameManager {

    private Scanner sc;
    private Prints tela;
    private TurnoHeroi turnoHeroi;
    private TurnoVilao turnoVilao;

    public GameManager() {
        this.sc = new Scanner(System.in);
        this.tela = new Prints();
        this.turnoHeroi = new TurnoHeroi();
        this.turnoVilao = new TurnoVilao();
    }



    public void partida(){

        tela.comeco();
        Heroi player1 = new Heroi("Shrek", 100, 20, 5, 100); 
        Inimigo enemy1 = new Inimigo("Dragão", 80, 10, 80); 

        Baralho deck = montarBaralho_player();
       // Baralho deck_inimigo = montarBaralho_enemy();
        CartaDano card3 = new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano",  3, 30, 0);
        enemy1.adicionaCard(card3);

        while (player1.estaVivo() && enemy1.estaVivo()){

            turnoHeroi.jogar(player1, enemy1, tela, deck, sc);
            
            if (enemy1.estaVivo()) {
                turnoVilao.jogar(player1, enemy1);
            }
    
        }

        tela.fim_de_jogo(player1);




    }





    private Baralho montarBaralho_player() {
        Baralho deck = new Baralho();
        deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano",  1, 10, 0));
        deck.adicionaBaralho(new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano",  3, 30, 0));
        deck.adicionaBaralho(new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano",  5, 40, 0));
        deck.adicionaBaralho(new CartaDano("Like a boss", "Custa 5 de energia e causa 60 de dano",  5, 60, 0));
        deck.adicionaBaralho(new CartaDano("Golpe da princesa", "Custa 2 de energia e causa 10 de dano",  2, 10, 0));
        deck.adicionaBaralho(new CartaDano("I need a hero", "Custa 3 de energia e causa 25 de dano",  3, 25, 0));
        deck.adicionaBaralho(new CartaDano("Biscoito de gengibre gigante", "Custa 4 de energia e causa 35 de dano",  4, 35, 0));
        deck.adicionaBaralho(new CartaDano("Golpe flamejante", "Custa 2 de energia e causa 10 de dano",  2, 10, 0));

        deck.adicionaBaralho(new CartaEscudo("Proteção","Custa 3 de energia e recebe 20 de escudo", 3, 20, 1));
        deck.adicionaBaralho(new CartaEscudo("Beijo de amor verdadeiro","Custa 4 de energia e recebe 50 de escudo", 4, 50, 1));
        return deck;
    }

//fazer pro vilao
    private Baralho montarBaralho_enemy(){
        Baralho deck = new Baralho();
        deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano",  1, 10, 0));
        return deck;
    }
}