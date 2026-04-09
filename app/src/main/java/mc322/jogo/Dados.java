package mc322.jogo;

import java.util.ArrayList;

import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaDanoArea;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.efeitos.EfeitoForca;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.EfeitoVeneno;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoAtacar;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoEfeito;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoEscudo;
import mc322.jogo.gerenciador.SistemaAcoes.AcaoInimigo;

/**
 * Classe que tem como única função armazenar os dados de Inimigos, Herois,
 * Cartas, Ações. Futuramente devemos trocar por um JSON.
 */
public class Dados {
        /**
         * Atributo gm é o Objeto que gerencia todo o jogo e é alimentado pelos dados
         */
        private static GameManager gm;

        public static ArrayList<Heroi> carregarHerois() {
                ArrayList<Heroi> herois = new ArrayList<>();

                /** Baralho do Personagem Shrek */
                Baralho deckShrek = new Baralho();

                deckShrek.adicionaBaralho(
                                new CartaDano("Soco do Ogro", "Dano: [Custo: 3 | Dano: 30] - Um jab de direita.", 3,
                                                30));
                deckShrek.adicionaBaralho(
                                new CartaDano("Pântano tenebroso", "Dano: [Custo: 5 | Dano: 50] - É como o nome diz.. ",
                                                5, 40));
                deckShrek.adicionaBaralho(new CartaDano("Arroto do Ogro",
                                "Dano: [Custo: 3 | Dano: 28] - Depois de uma garrafa de coca. ", 3, 28));
                deckShrek.adicionaBaralho(new CartaDano("Fúria de Ogro", "Dano: [Custo: 2 | Dano: 18] - GRRRR", 2, 18));
                deckShrek.adicionaBaralho(new CartaDanoArea("TESTE AREA shrek", "TÁ BALA ARTHUR ?", 4, 50));

                deckShrek.adicionaBaralho(new CartaEscudo("Beijo de amor verdadeiro",
                                "Escudo: [Custo: 3 | Escudo: 30] - Aquece o coração.", 3, 30));
                deckShrek.adicionaBaralho(new CartaEscudo("Pele Verde e Grossa",
                                "Escudo: [Custo: 2 | Escudo: 25] - Muito verde, muito grossa.", 2, 25));
                deckShrek.adicionaBaralho(
                                new CartaEscudo("Panela de Lama", "Escudo: [Custo: 1 | Escudo: 5] - Sabor escudo.", 1,
                                                5));

                deckShrek.adicionaBaralho(new CartaEfeito("Hálito Matinal de Ogro",
                                "Efeito: [Custo: 3 | Turnos: 2 |Veneno] - Depois de escovar os dentes", 3,
                                (new EfeitoVeneno(2, gm))));
                deckShrek.adicionaBaralho(new CartaEfeito("Arroto de Cebola",
                                "Efeito: [Custo: 4 | Turnos: 5 |Fraqueza: 25%] - É de fazer chorar.", 4,
                                (new EfeitoFraqueza(5, gm, 25))));
                deckShrek.adicionaBaralho(new CartaEfeito("Banho de Lama",
                                "Efeito: [Custo: 2 | Turnos: 5 |Força: +50%] - Renovado.", 2,
                                (new EfeitoForca(5, gm, 50))));

                herois.add(new Heroi("Shrek", 100, 20, 6, 100, 20, true, gm, deckShrek));

                /** Baralho do Personagem Burro */
                Baralho deckBurro = new Baralho();

                deckBurro.adicionaBaralho(
                                new CartaDano("Coice do Burro",
                                                "Dano: [Custo: 1 | Dano: 10] - Um coice rápido e inesperado.", 1, 10));
                deckBurro.adicionaBaralho(new CartaDano("Falatório Infinito",
                                "Dano: [Custo: 3 | Dano: 32] - Confunde o inimigo enquanto causa dano leve.", 3, 32));
                deckBurro.adicionaBaralho(new CartaDano("Empurrão Desesperado",
                                "Dano: [Custo: 4 | Dano: 40] - Burro avança sem pensar e causa dano médio.", 4, 40));
                deckBurro.adicionaBaralho(new CartaDano("Toma essa", "Dano: [Custo: 2 | Dano: 20] - Toma.", 2, 20));
                deckBurro.adicionaBaralho(new CartaDanoArea("burro ataque de surto", "SAI DA FRENTE QUE É COICE EM TODO MUNDO", 4, 50));

                deckBurro.adicionaBaralho(new CartaEscudo("Proteção Improvisada",
                                "Escudo: [Custo: 3 | Escudo: 20] - Usa qualquer coisa como defesa.", 3, 20));
                deckBurro.adicionaBaralho(new CartaEscudo("Amizade Verdadeira",
                                "Escudo: [Custo: 4 | Escudo: 35] - O poder da amizade protege.", 4, 35));
                deckBurro.adicionaBaralho(
                                new CartaEscudo("Botas Velhas",
                                                "Escudo: [Custo: 1 | Escudo: 10] - Defesa simples, mas útil.", 1, 10));

                deckBurro.adicionaBaralho(new CartaEfeito("A gente já chegou?",
                                "Efeito: [Custo: 4 | Turnos: 8 | Veneno] - A gente já chegou?", 4,
                                (new EfeitoVeneno(8, gm))));
                deckBurro.adicionaBaralho(new CartaEfeito("Motivação do Burro",
                                "Efeito: [Custo: 3 | Turnos: 5| Força: +25%] - Um garoto diferenciado", 3,
                                (new EfeitoForca(5, gm, 25))));

                herois.add(new Heroi("Burro", 80, 10, 7, 80, 50, true, gm, deckBurro));

                /** Baralho do Personagem Gato */
                Baralho deckGato = new Baralho();

                deckGato.adicionaBaralho(
                                new CartaDano("Corte Preciso",
                                                "Dano: [Custo: 2 | Dano: 20] - Um ataque rápido e certeiro.", 2, 20));
                deckGato.adicionaBaralho(new CartaDano("Dança das Espadas",
                                "Dano: [Custo: 4 | Dano: 42] - Uma sequência elegante de golpes.", 4, 42));
                deckGato.adicionaBaralho(new CartaDano("Golpe Crítico Felino",
                                "Dano: [Custo: 5 | Dano: 55] - Ataque mortal com precisão felina.", 5, 55));
                deckGato.adicionaBaralho(new CartaDano("Miauuu", "Dano: [Custo: 1 | Dano: 15] - Miauu.", 1, 15));
                deckGato.adicionaBaralho(new CartaDanoArea("gato e o El Bigodon Federal", "SAI DA FRENTE", 4, 50));

                deckGato.adicionaBaralho(new CartaEscudo("Olhar Fofo",
                                "Escudo: [Custo: 2 | Escudo: 15] - Desarma o inimigo emocionalmente.", 2, 15));
                deckGato.adicionaBaralho(new CartaEscudo("Esquiva Ágil",
                                "Escudo: [Custo: 1 | Escudo: 30] - Movimentos rápidos evitam dano.", 1, 10));
                deckGato.adicionaBaralho(new CartaEscudo("Capa do Espadachim",
                                "Escudo: [Custo: 3 | Escudo: 32] - Defesa leve e estilosa.", 3, 32));

                deckGato.adicionaBaralho(new CartaEfeito("7 vidas",
                                "Efeito: [Custo: 4 | Turnos: 7| Força: +30%] - Até a Morte teme", 4,
                                (new EfeitoForca(7, gm, 30))));
                deckGato.adicionaBaralho(new CartaEfeito("Marca do Caçador",
                                "Efeito: [Custo: 2 | Turnos: 4 |Fraqueza: 30%] - Identifica o ponto fraco do inimigo.",
                                2,
                                (new EfeitoFraqueza(4, gm, 30))));

                herois.add(new Heroi("Gato de Botas", 70, 15, 5, 60, 80, true, gm, deckGato));

                /** Baralho da personagem Fiona */
                Baralho deckFiona = new Baralho();

                deckFiona.adicionaBaralho(
                                new CartaDano("Golpe de Princesa", "Dano: [Custo: 3 | Dano: 26] - Elegância com força.",
                                                3, 26));
                deckFiona.adicionaBaralho(
                                new CartaDano("Fúria de Ogra",
                                                "Dano: [Custo: 1 | Dano: 14] - Mostra sua verdadeira força.", 1, 14));
                deckFiona.adicionaBaralho(
                                new CartaDano("Combo Real",
                                                "Dano: [Custo: 4 | Dano: 42] - Sequência poderosa de ataques.", 4, 42));

                deckFiona.adicionaBaralho(new CartaEscudo("Postura de Combate",
                                "Escudo: [Custo: 1 | Escudo: 18] - Defesa firme e treinada.", 1, 18));
                deckFiona.adicionaBaralho(new CartaEscudo("Amor Verdadeiro",
                                "Escudo: [Custo: 4 | Escudo: 45] - Proteção fortalecida pelo amor.", 4, 45));
                deckFiona.adicionaBaralho(new CartaEscudo("Resistência Ogra",
                                "Escudo: [Custo: 2 | Escudo: 22] - Aguenta mais que parece.", 2, 22));

                // curar
                deckFiona.adicionaBaralho(new CartaEfeito("Determinação",
                                "Efeito: [Custo: 3 | Turnos: 5| Força: +20%] - Aumenta o poder de ataque.", 3,
                                (new EfeitoForca(5, gm, 20))));
                deckFiona.adicionaBaralho(new CartaEfeito("Pressão Real",
                                "Efeito: [Custo: 3 | Turnos: 4| Fraqueza: 20%] - Sente a pressão", 3,
                                (new EfeitoFraqueza(4, gm, 20))));

                herois.add(new Heroi("Fiona", 90, 25, 5, 90, 40, true, gm, deckFiona));

                return herois;
        }

        /**
         * Método para carregar nosso vetor com todos os inimigos do Jogo. A partir
         * desse vetor que criamos
         * um subVetor com os inimigos escolhidos em cada Batalha
         * 
         * @return vetor de Inimigos do jogo
         */
        public static ArrayList<Inimigo> carregarInimigos() {
                ArrayList<Inimigo> inimigos = new ArrayList<>();

                /** Inimigo Dragão com o seu sistema de ações do jogo */
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

                /** Inimigo Farquaad com o seu sistema de ações do jogo */
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

                /** Inimigo Fada com o seu sistema de ações do jogo */
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