import java.util.ArrayList;

public class Dados {


    public static ArrayList<Heroi> carregarHerois() {
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(new Heroi("Shrek", 100, 20, 5, 100, 10, true)); 
        herois.add(new Heroi("Burro", 80, 10, 8, 80, 50, true)); 
        herois.add(new Heroi("Gato de Botas", 60, 15, 5, 60, 70, true)); 
        herois.add(new Heroi("Fiona", 90, 25, 4, 90, 40, true));
        return herois;
    }



    // Retorna o baralho oficial da loja para os Heróis comprarem
    public static Baralho carregarBaralhoGeral() {
        Baralho deck = new Baralho();
        deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano",  1, 10, 0));
        deck.adicionaBaralho(new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano",  3, 30, 0));
        deck.adicionaBaralho(new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano",  5, 40, 0));
        deck.adicionaBaralho(new CartaDano("Olhar Fofinho", "Custa 1 de energia e causa 8 de dano", 1, 8, 0));
        deck.adicionaBaralho(new CartaDano("Cebola Explosiva", "Custa 1 de energia e causa 12 de dano", 1, 12, 0));
        deck.adicionaBaralho(new CartaDano("Canto da Princesa", "Custa 1 de energia e causa 10 de dano", 1, 10, 0));
        deck.adicionaBaralho(new CartaDano("Coice do Burro", "Custa 2 de energia e causa 18 de dano", 2, 18, 0));
        deck.adicionaBaralho(new CartaDano("Voadora da Fiona", "Custa 2 de energia e causa 22 de dano", 2, 22, 0));
        deck.adicionaBaralho(new CartaDano("Arroto de Pântano", "Custa 3 de energia e causa 28 de dano", 3, 28, 0));
        deck.adicionaBaralho(new CartaDano("Ataque de Cócegas", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Fúria de Ogro", "Custa 4 de energia e causa 38 de dano", 4, 38, 0));
        deck.adicionaBaralho(new CartaDano("Invocação do Dragão Aliado", "Custa 5 de energia e causa 50 de dano", 5, 50, 0));       
        
        deck.adicionaBaralho(new CartaEscudo("Proteção","Custa 3 de energia e recebe 20 de escudo", 3, 20, 1));
        deck.adicionaBaralho(new CartaEscudo("Beijo de amor verdadeiro","Custa 4 de energia e recebe 50 de escudo", 4, 50, 1));
        deck.adicionaBaralho(new CartaEscudo("Botas de Couro", "Custa 1 de energia e recebe 10 de escudo", 1, 10, 1));
        deck.adicionaBaralho(new CartaEscudo("Camadas de Cebola", "Custa 1 de energia e recebe 12 de escudo", 1, 12, 1));
        deck.adicionaBaralho(new CartaEscudo("Waffles Quentinhos", "Custa 2 de energia e recebe 18 de escudo", 2, 18, 1));
        deck.adicionaBaralho(new CartaEscudo("Pele Verde e Grossa", "Custa 3 de energia e recebe 25 de escudo", 3, 25, 1));
        deck.adicionaBaralho(new CartaEscudo("Panela de Lama", "Custa 2 de energia e recebe 20 de escudo", 2, 20, 1));
        deck.adicionaBaralho(new CartaEscudo("Muralha do Castelo do Dragão", "Custa 5 de energia e recebe 45 de escudo", 5, 45, 1));

        return deck;
    }

    
   public static ArrayList<Inimigo> carregarInimigos() {
        ArrayList<Inimigo> inimigos = new ArrayList<>();

        
        Inimigo dragao = new Inimigo("Dragão", 110, 40, 110, 10, true); 
        ArrayList<Carta> deckDragao = new ArrayList<>();
        deckDragao.add(new CartaDano("Baforada de Fogo", "Causa 45 de dano", 0, 45, 0));
        deckDragao.add(new CartaDano("Mordida Feroz", "Causa 15 de dano", 0, 15, 0));
        deckDragao.add(new CartaEscudo("Escamas Duras", "Ganha 15 de escudo", 0, 15, 1));
        dragao.transforma_Deck(deckDragao); 
        inimigos.add(dragao);

        
        Inimigo farquaad = new Inimigo("Lord Farquaad", 60, 20, 60, 30, true);
        ArrayList<Carta> deckFarquaad = new ArrayList<>();
        deckFarquaad.add(new CartaDano("Ordem de Execução", "Causa 20 de dano", 0, 20, 0));
        deckFarquaad.add(new CartaDano("Golpe Baixo", "Causa 30 de dano", 0, 30, 0));
        deckFarquaad.add(new CartaEscudo("Esconder atrás dos guardas", "Ganha 20 de escudo", 0, 20, 1));
        farquaad.transforma_Deck(deckFarquaad);
        inimigos.add(farquaad);

        
        Inimigo fada = new Inimigo("Fada Madrinha", 90, 20, 90, 60, true);
        ArrayList<Carta> deckFada = new ArrayList<>();
        deckFada.add(new CartaDano("Raio Mágico", "Causa 30 de dano", 0, 30, 0));
        deckFada.add(new CartaDano("Poção Explosiva", "Causa 20 de dano", 0, 20, 0));
        deckFada.add(new CartaEscudo("Bolha de Sabão", "Ganha 25 de escudo", 0, 25, 1));
        fada.transforma_Deck(deckFada);
        inimigos.add(fada);

        return inimigos;
    }




}