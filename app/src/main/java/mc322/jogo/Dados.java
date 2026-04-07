package mc322.jogo;

import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaDanoArea;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.efeitos.EfeitoForca;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.EfeitoVeneno;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoAtacar;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoEfeito;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoEscudo;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;

import java.util.ArrayList;

/**
 * Classe que tem como única função armazenar os dados de Inimigos, Herois,
 * Cartas, Ações.
 */
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

                /* todas as castas de dano */
                deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Custa 2 de energia e causa 15 de dano", 2, 15));
                deck.adicionaBaralho(
                                new CartaDano("Corte de Espada", "Custa 1 de energia e causa 10 de dano", 1, 10));
                deck.adicionaBaralho(new CartaDano("Soco do ogro", "Custa 3 de energia e causa 30 de dano", 3, 30));
                deck.adicionaBaralho(
                                new CartaDano("Pântano tenebroso", "Custa 5 de energia e causa 40 de dano", 5, 40));
                deck.adicionaBaralho(new CartaDano("Olhar Fofinho", "Custa 1 de energia e causa 8 de dano", 1, 8));
                deck.adicionaBaralho(
                                new CartaDano("Cebola Explosiva", "Custa 1 de energia e causa 12 de dano", 1, 12));
                deck.adicionaBaralho(
                                new CartaDano("Canto da Princesa", "Custa 1 de energia e causa 10 de dano", 1, 10));
                deck.adicionaBaralho(
                                new CartaDano("Coice do Burro", "Custa 2 de energia e causa 18 de dano", 2, 18));
                deck.adicionaBaralho(
                                new CartaDano("Voadora da Fiona", "Custa 2 de energia e causa 22 de dano", 2, 22));
                deck.adicionaBaralho(
                                new CartaDano("Arroto de Pântano", "Custa 3 de energia e causa 28 de dano", 3, 28));
                deck.adicionaBaralho(
                                new CartaDano("Ataque de Cócegas", "Custa 2 de energia e causa 15 de dano", 2, 15));
                deck.adicionaBaralho(new CartaDano("Fúria de Ogro", "Custa 4 de energia e causa 38 de dano", 4, 38));
                deck.adicionaBaralho(
                                new CartaDano("Invocação do Dragão Aliado", "Custa 5 de energia e causa 50 de dano", 5,
                                                50));

                /* Cartas de dano em áera */
                deck.adicionaBaralho(new CartaDanoArea("TESTE AREA", "TÁ BALA ARTHUR ?", 4, 50));
                deck.adicionaBaralho(new CartaDanoArea("TESTE AREA", "TÁ BALA ARTHUR ?", 4, 50));
                deck.adicionaBaralho(new CartaDanoArea("TESTE AREA", "TÁ BALA ARTHUR ?", 4, 50));

                /* todas as cartas de escudo */
                deck.adicionaBaralho(new CartaEscudo("Proteção", "Custa 3 de energia e recebe 20 de escudo", 3, 20));
                deck.adicionaBaralho(
                                new CartaEscudo("Beijo de amor verdadeiro", "Custa 4 de energia e recebe 50 de escudo",
                                                4, 50));
                deck.adicionaBaralho(new CartaEscudo("Botas de Couro", "Custa 1 de energia e recebe 10 de escudo", 1,
                                10));
                deck.adicionaBaralho(
                                new CartaEscudo("Camadas de Cebola", "Custa 1 de energia e recebe 12 de escudo", 1,
                                                12));
                deck.adicionaBaralho(
                                new CartaEscudo("Waffles Quentinhos", "Custa 2 de energia e recebe 18 de escudo", 2,
                                                18));
                deck.adicionaBaralho(
                                new CartaEscudo("Pele Verde e Grossa", "Custa 3 de energia e recebe 25 de escudo", 3,
                                                25));
                deck.adicionaBaralho(new CartaEscudo("Panela de Lama", "Custa 2 de energia e recebe 20 de escudo", 2,
                                20));
                deck.adicionaBaralho(
                                new CartaEscudo("Muralha do Castelo do Dragão",
                                                "Custa 5 de energia e recebe 45 de escudo", 5, 45));

                /* todas as cartas de efeito */
                deck.adicionaBaralho(new CartaEfeito("Hálito Matinal de Ogro",
                                "Aplica 5 de Veneno. É pior que cebola podre", 3, (new EfeitoVeneno(2, gm))));
                deck.adicionaBaralho(new CartaEfeito("Ensopado de Olho de Peixe",
                                "Aplica 12 de Veneno. Custa 3 de energia.", 3, (new EfeitoVeneno(12, gm))));

                deck.adicionaBaralho(new CartaEfeito("Arroto de Cebola",
                                "Custa 4 de energia e Aplica 25% de Fraqueza. Um cheiro de desmotivar qualquer cavaleiro.",
                                4, (new EfeitoFraqueza(5, gm, 25))));
                deck.adicionaBaralho(new CartaEfeito("Choro do Biscoito",
                                "Custa 3 de energia. Aplica 25% de Fraqueza. 'Não os meus botões de goma!' Corta o coração do inimigo.",
                                3, (new EfeitoFraqueza(5, gm, 25))));
                deck.adicionaBaralho(new CartaEfeito("Banho de Lama",
                                "Custa 2 de energia. Aplica 50% de Fraqueza. Deixa as armas do inimigo escorregadias.",
                                2, (new EfeitoFraqueza(5, gm, 50))));
                deck.adicionaBaralho(new CartaEfeito("TESTE CARTA FORCA", "TESTE AUMENTA 25% FORCA", 3,
                                (new EfeitoForca(5, gm, 25))));
                deck.adicionaBaralho(new CartaEfeito("TESTE CARTA FORCA", "TESTE AUMENTA 25% FORCA", 3,
                                (new EfeitoForca(5, gm, 25))));
                deck.adicionaBaralho(new CartaEfeito("TESTE CARTA FORCA", "TESTE AUMENTA 25% FORCA", 3,
                                (new EfeitoForca(5, gm, 25))));
                deck.adicionaBaralho(new CartaEfeito("TESTE CARTA FORCA", "TESTE AUMENTA 25% FORCA", 3,
                                (new EfeitoForca(5, gm, 25))));

                return deck;
        }

        public static ArrayList<Inimigo> carregarInimigos() {
                ArrayList<Inimigo> inimigos = new ArrayList<>();

                ArrayList<AcaoInimigo> acoesDragao = new ArrayList<>();
                acoesDragao.add(new AcaoAtacar("Baforada de Fogo", 45));
                acoesDragao.add(new AcaoAtacar("Mordida Feroz", 15));
                acoesDragao.add(new AcaoEfeito("Gargalhada suprema", (new EfeitoVeneno(8, gm)))); // teste
                acoesDragao.add(new AcaoEscudo("Escamas Duras", 15));
                acoesDragao.add(new AcaoEfeito("Choro do Biscoito", (new EfeitoFraqueza(5, gm, 25))));
                acoesDragao.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));
                acoesDragao.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));
                Inimigo dragao = new Inimigo("Dragão", 100, 40, 100, 10, true, gm, acoesDragao);

                inimigos.add(dragao);

                ArrayList<AcaoInimigo> acoesFarquaad = new ArrayList<>();
                acoesFarquaad.add(new AcaoAtacar("Ordem de Execução", 20));
                acoesFarquaad.add(new AcaoAtacar("Golpe Baixo", 30));
                acoesFarquaad.add(new AcaoEscudo("Esconder atrás dos guardas", 30));
                acoesFarquaad.add(new AcaoEfeito("Gargalhada suprema", (new EfeitoVeneno(9, gm))));
                acoesFarquaad.add(new AcaoEfeito("Choro do Biscoito", (new EfeitoFraqueza(6, gm, 50))));
                acoesFarquaad.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));
                acoesFarquaad.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));

                Inimigo farquaad = new Inimigo("Lord Farquaad", 60, 30, 60, 30, true, gm, acoesFarquaad);

                inimigos.add(farquaad);

                ArrayList<AcaoInimigo> acoesFada = new ArrayList<>();
                acoesFada.add(new AcaoAtacar("Raio Mágico", 30));
                acoesFada.add(new AcaoAtacar("Poção Explosiva", 10));
                acoesFada.add(new AcaoEscudo("Bolha de Sabão", 25));
                acoesFada.add(new AcaoEfeito("Feitiço da fada", (new EfeitoVeneno(12, gm))));
                acoesFada.add(new AcaoEfeito("Choro do Biscoito", (new EfeitoFraqueza(5, gm, 25))));
                acoesFada.add(new AcaoEfeito("Banho de Lama", (new EfeitoFraqueza(5, gm, 25))));
                acoesFada.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));
                acoesFada.add(new AcaoEfeito("ACAO TESTE FORCA", (new EfeitoForca(5, gm, 25))));

                Inimigo fada = new Inimigo("Fada Madrinha", 90, 20, 90, 60, true, gm, acoesFada);

                inimigos.add(fada);

                return inimigos;
        }

        public static void setGm(GameManager gm) {
                Dados.gm = gm;
        }

}