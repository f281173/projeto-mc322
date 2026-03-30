import java.util.ArrayList;

public class Dados {
        private static GameManager gm;

        public static ArrayList<Heroi> carregarHerois() {
                ArrayList<Heroi> herois = new ArrayList<>();
                herois.add(new Heroi("Shrek", 100, 20, 6, 100, 20, true, gm));
                herois.add(new Heroi("Burro", 80, 10, 7, 80, 50, true, gm));
                herois.add(new Heroi("Gato de Botas", 70, 15, 5, 60, 80, true, gm));
                herois.add(new Heroi("Fiona", 90, 25, 5, 90, 40, true, gm));
                return herois;
        }


        // Retorna o baralho oficial da loja para os Heróis comprarem
        public static Baralho carregarBaralhoGeral() {
                Baralho deck = new Baralho();


                /* todas as cartas de dano */
                deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
                deck.adicionaBaralho(new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano", 1, 10, 0));
                deck.adicionaBaralho(new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano", 3, 30, 0));
                deck.adicionaBaralho(new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano", 5, 40, 0));
                deck.adicionaBaralho(new CartaDano("Olhar Fofinho", "Custa 1 de energia e causa 8 de dano", 1, 8, 0));
                deck.adicionaBaralho(new CartaDano("Cebola Explosiva", "Custa 1 de energia e causa 12 de dano", 1, 12, 0));
                deck.adicionaBaralho(new CartaDano("Canto da Princesa", "Custa 1 de energia e causa 10 de dano", 1, 10, 0));
                deck.adicionaBaralho( new CartaDano("Coice do Burro", "Custa 2 de energia e causa 18 de dano", 2, 18, 0));
                deck.adicionaBaralho(new CartaDano("Voadora da Fiona", "Custa 2 de energia e causa 22 de dano", 2, 22, 0));
                deck.adicionaBaralho(new CartaDano("Arroto de Pântano", "Custa 3 de energia e causa 28 de dano", 3, 28, 0));
                deck.adicionaBaralho(new CartaDano("Ataque de Cócegas", "Custa 2 de energia e causa 15 de dano", 2, 15, 0));
                deck.adicionaBaralho(new CartaDano("Fúria de Ogro", "Custa 4 de energia e causa 38 de dano", 4, 38, 0));
                deck.adicionaBaralho(new CartaDano("Invocação do Dragão Aliado", "Custa 5 de energia e causa 50 de dano", 5, 50, 0));

                /* todas as cartas de escudo */
                deck.adicionaBaralho(new CartaEscudo("Proteção", "Custa 3 de energia e recebe 20 de escudo", 3, 20, 1));
                deck.adicionaBaralho(new CartaEscudo("Beijo de amor verdadeiro", "Custa 4 de energia e recebe 50 de escudo",4, 50, 1));
                deck.adicionaBaralho(new CartaEscudo("Botas de Couro", "Custa 1 de energia e recebe 10 de escudo", 1,10, 1));
                deck.adicionaBaralho( new CartaEscudo("Camadas de Cebola", "Custa 1 de energia e recebe 12 de escudo", 1, 12, 1));
                deck.adicionaBaralho(new CartaEscudo("Waffles Quentinhos", "Custa 2 de energia e recebe 18 de escudo", 2, 18,1));
                deck.adicionaBaralho(new CartaEscudo("Pele Verde e Grossa", "Custa 3 de energia e recebe 25 de escudo", 3, 25, 1));
                deck.adicionaBaralho(new CartaEscudo("Panela de Lama", "Custa 2 de energia e recebe 20 de escudo", 2, 20, 1));
                deck.adicionaBaralho(new CartaEscudo("Muralha do Castelo do Dragão", "Custa 5 de energia e recebe 60 de escudo", 5, 45, 1));

                /* todas as cartas de efeito */
                deck.adicionaBaralho(new CartaEfeito("Hálito Matinal de Ogro",
                                "Aplica 5 de Veneno. É pior que cebola podre.",
                                3, 2, TiposEfeitos.VENENO, 10));

                deck.adicionaBaralho(new CartaEfeito("Ensopado de Olho de Peixe",
                                "Aplica 12 de Veneno. Custa 3 de energia.", 3,
                                2, TiposEfeitos.VENENO, 12));

                deck.adicionaBaralho(new CartaEfeito("Arroto de Cebola",
                                "Custa 4 de energia e Aplica 25% de Fraqueza. Um cheiro de desmotivar qualquer cavaleiro.",
                                4, 2, TiposEfeitos.FRAQUEZA, 5));
                deck.adicionaBaralho(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, 2, TiposEfeitos.FRAQUEZA, 4));
                deck.adicionaBaralho(new CartaEfeito("Banho de Lama",
                                "Custa 2 de energia. Aplica 25% de Fraqueza. Deixa as armas do inimigo escorregadias.",
                                2, 2, TiposEfeitos.FRAQUEZA, 3));

                return deck;
        }


        

        public static ArrayList<Inimigo> carregarInimigos() {
                ArrayList<Inimigo> inimigos = new ArrayList<>();

                Inimigo dragao = new Inimigo("Dragão", 100, 40, 100, 10, true, gm);
                ArrayList<Carta> deckDragao = new ArrayList<>();
                deckDragao.add(new CartaDano("Baforada de Fogo", "Causa 45 de dano", 0, 45, 0));
                deckDragao.add(new CartaDano("Mordida Feroz", "Causa 15 de dano", 0, 15, 0));
                deckDragao.add(new CartaEfeito("Gargalhada suprema",
                                "O Lord Gargalha durante uma série de turnos e causa dano", 3, 2, TiposEfeitos.VENENO,
                                10)); // teste
                deckDragao.add(new CartaEscudo("Escamas Duras", "Ganha 15 de escudo", 0, 15, 1));
                deckDragao.add(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, 2, TiposEfeitos.FRAQUEZA, 4));
                deckDragao.add(new CartaEfeito("Banho de Lama",
                                "Custa 2 de energia. Aplica 25% de Fraqueza. Deixa as armas do inimigo escorregadias.",
                                2, 2, TiposEfeitos.FRAQUEZA, 3));
                dragao.transformaDeck(deckDragao);
                inimigos.add(dragao);



                Inimigo farquaad = new Inimigo("Lord Farquaad", 60, 30, 60, 30, true, gm);
                ArrayList<Carta> deckFarquaad = new ArrayList<>();
                deckFarquaad.add(new CartaDano("Ordem de Execução", "Causa 20 de dano", 0, 20, 0));
                deckFarquaad.add(new CartaDano("Golpe Baixo", "Causa 30 de dano", 0, 30, 0));
                deckFarquaad.add(new CartaEscudo("Esconder atrás dos guardas", "Ganha 30 de escudo", 0, 30, 1));
                deckFarquaad.add(new CartaEfeito("Gargalhada suprema",
                                "O Lord Gargalha durante uma série de turnos e causa dano", 3, 2, TiposEfeitos.VENENO,
                                10));
                deckFarquaad.add(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, 2, TiposEfeitos.FRAQUEZA, 4));
                deckFarquaad.add(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, 2, TiposEfeitos.FRAQUEZA, 4));
                farquaad.transformaDeck(deckFarquaad);
                inimigos.add(farquaad);


                Inimigo fada = new Inimigo("Fada Madrinha", 90, 20, 90, 60, true, gm);
                ArrayList<Carta> deckFada = new ArrayList<>();
                deckFada.add(new CartaDano("Raio Mágico", "Causa 30 de dano", 0, 30, 0));
                deckFada.add(new CartaDano("Poção Explosiva", "Causa 10 de dano", 0, 10, 0));
                deckFada.add(new CartaEscudo("Bolha de Sabão", "Ganha 25 de escudo", 0, 25, 1));
                deckFada.add(new CartaEfeito("Feitiço da fada",
                                "A fada usa o seu feitiço durante uma série de turnos e causa dano", 3, 2, TiposEfeitos.VENENO,
                                10));
                deckFada.add(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, 2, TiposEfeitos.FRAQUEZA, 4));
                deckFada.add(new CartaEfeito("Banho de Lama",
                                "Custa 2 de energia. Aplica 25% de Fraqueza. Deixa as armas do inimigo escorregadias.",
                                2, 2, TiposEfeitos.FRAQUEZA, 3));
                fada.transformaDeck(deckFada);
                inimigos.add(fada);


                Inimigo encantado = new Inimigo("Príncipe Encantado", 80, 40, 80, 55, true, gm);
                ArrayList<Carta> deckEncantado = new ArrayList<>();
                deckEncantado.add(new CartaDano("Sorriso colgate", "Causa 10 de dano", 0, 10, 0));
                deckEncantado.add(new CartaDano("O Herói destinado", "Causa 40 de dano", 0, 40, 0));
                deckEncantado.add(new CartaEscudo("Cabelos brilhantes", "Ganha 30 de escudo", 0, 30, 1));
                encantado.transformaDeck(deckEncantado);
                inimigos.add(encantado);


                Inimigo rumpel = new Inimigo("Rumpelstiltskin", 60, 40, 60, 25, true, gm);
                ArrayList<Carta> deckRumpel = new ArrayList<>();
                deckRumpel.add(new CartaDano("Pena em chamas", "Causa 10 de dano", 0, 10, 0));
                deckRumpel.add(new CartaDano("Assina aqui-->", "Causa 50 de dano", 0, 50, 0));
                deckRumpel.add(new CartaEscudo("Chama o pato", "Ganha 40 de escudo", 0, 40, 1));
                rumpel.transformaDeck(deckRumpel);
                inimigos.add(rumpel);

        return inimigos;
    }

    
        public static void setGm(GameManager gm) {
                Dados.gm = gm;
        }

}