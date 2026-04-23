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

        public static Heroi criarShrek(GameManager gm) {
            /** Baralho do Personagem Shrek */
            Baralho deckShrek = new Baralho();

            deckShrek.adicionaBaralho(
                            new CartaDano("Soco do Ogro", "Dano: [Custo: 3 | Dano: 30] - Um jab de direita.", 3,
                                            30));
            deckShrek.adicionaBaralho(
                            new CartaDano("Pântano Tenebroso", "Dano: [Custo: 5 | Dano: 50] - É como o nome diz.. ",
                                            5, 40));
            deckShrek.adicionaBaralho(new CartaDano("Arroto do Ogro",
                            "Dano: [Custo: 3 | Dano: 28] - Depois de uma garrafa de coca. ", 3, 28));
            deckShrek.adicionaBaralho(new CartaDano("Fúria de Ogro", "Dano: [Custo: 2 | Dano: 18] - GRRRR", 2, 18));

            deckShrek.adicionaBaralho(new CartaDanoArea("Faz o Urro!", "[Custo: 4 | Dano: 30 ÁREA] UUUAAAAAAAAAAHHHHHHHH", 4, 30)); 

            deckShrek.adicionaBaralho(new CartaEscudo("Beijo de Amor Verdadeiro",
                            "Escudo: [Custo: 3 | Escudo: 30] - Aquece o oração.", 3, 30));
            deckShrek.adicionaBaralho(new CartaEscudo("Pele Verde e Grossa",
                            "Escudo: [Custo: 2 | Escudo: 25] - Muito verde, muito grossa.", 2, 25));

            deckShrek.adicionaBaralho(new CartaEfeito("Hálito Matinal de Ogro",
                            "Efeito: [Custo: 3 | Turnos: 2 |Veneno] - Depois de escovar os dentes", 3,
                            (new EfeitoVeneno(2, gm))));
            deckShrek.adicionaBaralho(new CartaEfeito("Arroto de Cebola",
                            "Efeito: [Custo: 4 | Turnos: 5 |Fraqueza: 25%] - É de fazer chorar.", 4,
                            (new EfeitoFraqueza(5, gm, 25))));
            deckShrek.adicionaBaralho(new CartaEfeito("Banho de Lama",
                            "Efeito: [Custo: 2 | Turnos: 5 |Força: +50%] - Renovado.", 2,
                            (new EfeitoForca(5, gm, 50))));

            return new Heroi("Shrek", 300, 20, 6, 300, 20, true, gm, deckShrek) ;
        }


        public static Heroi criarBurro(GameManager gm) {
            /** Baralho do Personagem Burro */
            Baralho deckBurro = new Baralho();

            deckBurro.adicionaBaralho(
                            new CartaDano("Coice do Burro",
                                            "Dano: [Custo: 1 | Dano: 10] - Um coice rápido e inesperado.", 1, 10));
            deckBurro.adicionaBaralho(new CartaDano("Falatório Infinito",
                            "Dano: [Custo: 3 | Dano: 32] - Confunde o inimigo enquanto causa dano leve.", 3, 32));
            deckBurro.adicionaBaralho(new CartaDano("Empurrão Desesperado",
                            "Dano: [Custo: 4 | Dano: 40] - Burro avança sem pensar e causa dano médio.", 4, 40));
            deckBurro.adicionaBaralho(new CartaDano("Toma Essa", "Dano: [Custo: 2 | Dano: 20] - Toma.", 2, 20));
            deckBurro.adicionaBaralho(new CartaDanoArea("Burro Ataque de Surto", "[Custo: 4 | Dano: 50 ÁREA] SAI DA FRENTE QUE É COICE EM TODO MUNDO", 4, 50));  

            deckBurro.adicionaBaralho(new CartaEscudo("Proteção Improvisada",
                            "Escudo: [Custo: 3 | Escudo: 20] - Usa qualquer coisa como defesa.", 3, 20));
            deckBurro.adicionaBaralho(
                            new CartaEscudo("Botas Velhas",
                                            "Escudo: [Custo: 1 | Escudo: 10] - Defesa simples, mas útil.", 1, 10));

            deckBurro.adicionaBaralho(new CartaEfeito("A Gente Já Chegou?",
                            "Efeito: [Custo: 4 | Turnos: 8 | Veneno] - A gente já chegou?", 4,
                            (new EfeitoVeneno(8, gm))));
            deckBurro.adicionaBaralho(new CartaEfeito("Motivação do Burro",
                            "Efeito: [Custo: 3 | Turnos: 5| Força: +25%] - Um garoto diferenciado", 3,
                            (new EfeitoForca(5, gm, 25))));

            return new Heroi("Burro", 120, 10, 7, 120, 50, true, gm, deckBurro);
        }


        public static Heroi criarPinoquio(GameManager gm) {
                /** Baralho do Personagem Pinóquio */
                Baralho deckPinoquio = new Baralho();

                deckPinoquio.adicionaBaralho(
                                new CartaDano("Madeirada",
                                                "Dano: [Custo: 2 | Dano: 20] - Um ataque rápido com braço de madeira.", 2, 20));
                deckPinoquio.adicionaBaralho(new CartaDano("Sequência de golpes",
                                "Dano: [Custo: 4 | Dano: 42] - O Gepeto lhe ensinou.", 4, 42));
                deckPinoquio.adicionaBaralho(new CartaDano("Estocada Nasal",
                                "Dano: [Custo: 5 | Dano: 55] - Ataque mortal com o nariz esticado.", 5, 55));
                deckPinoquio.adicionaBaralho(new CartaDano("Eu não estou mentindo!", 
                                "Dano: [Custo: 1 | Dano: 15] - *Crec*. O nariz cresceu e bateu no inimigo.", 1, 15));
                deckPinoquio.adicionaBaralho(new CartaDanoArea("Dança sem Cordas", 
                                "[Custo: 4 | Dano: 50 ÁREA] SAI DA FRENTE, O FANTOCHE TÁ SOLTO!", 4, 50));

                deckPinoquio.adicionaBaralho(new CartaEscudo("Corpo de Carvalho",
                                "Escudo: [Custo: 2 | Escudo: 15] - A madeira dura absorve o impacto.", 2, 15));
                deckPinoquio.adicionaBaralho(new CartaEscudo("Fio Invisível",
                                "Escudo: [Custo: 1 | Escudo: 10] - Puxado por cordas mágicas para evitar o dano.", 1, 10));

                deckPinoquio.adicionaBaralho(new CartaEfeito("Menino de Verdade",
                                "Efeito: [Custo: 4 | Turnos: 7| Força: +30%] - A magia da Fada Azul te enche de determinação.", 4,
                                (new EfeitoForca(7, gm, 30))));
                deckPinoquio.adicionaBaralho(new CartaEfeito("Mentira Descarada",
                                "Efeito: [Custo: 2 | Turnos: 4 | Fraqueza: 30%] - As mentiras deixam o inimigo confuso.",
                                2,
                                (new EfeitoFraqueza(4, gm, 30))));

                return new Heroi("Pinóquio", 120, 15, 5, 120, 80, true, gm, deckPinoquio);
        }


        public static Heroi criarFiona(GameManager gm) {
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

                return new Heroi("Fiona", 130, 25, 5, 130, 40, true, gm, deckFiona);

                
        }


        public static Heroi criarLobinho(GameManager gm) {
            Baralho deckLobinho = new Baralho();
            deckLobinho.adicionaBaralho(new CartaDano("Rugido", "Dano: [Custo: 3 | Dano: 67]", 3, 67));
            deckLobinho.adicionaBaralho(new CartaDano("Garras", "Dano: [Custo: 2 | Dano: 27]", 2, 27));
            deckLobinho.adicionaBaralho(new CartaEfeito("Lobinho neles", "[Custo: 1 | Turnos: 1 | Força: +100%] - Simplesmente dobra sua força", 1, new EfeitoForca(1, gm, 100))); 

            
            return new Heroi("Biscoito", 50, 10, 3, 50, 30, true, gm, deckLobinho);
        }





        // ---------------------- INIMIGOS GENERICOS --------------------------------------------

        public static Inimigo criarOgreHunter(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Tochada", 12));
            acoes.add(new AcaoAtacar("Golpe com graveto", 15));
            acoes.add(new AcaoEscudo("Recuar com Medo", 10));
            
            return new Inimigo("Aldeão Furioso", 40, 0, 40, 15, true, gm, acoes);
        }

        public static Inimigo criarGuards(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Lave bem o seu .. pé", 20));
            acoes.add(new AcaoAtacar("Chute alto", 15));
            acoes.add(new AcaoEscudo("Atrás do Lord", 10));
            
            return new Inimigo("Soldados do Lord", 50, 0, 50, 25, true, gm, acoes);
        }

        public static Inimigo criarWolf(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Mordida", 20));
            acoes.add(new AcaoAtacar("Líder da alcateia", 25));
            acoes.add(new AcaoEscudo("Lua cheia", 10));
            
            return new Inimigo("Lobo", 50, 10, 50, 95, true, gm, acoes);
        }

        public static Inimigo criarPig(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Jab de esquerda", 20));
            acoes.add(new AcaoAtacar("Porcada", 25));
            acoes.add(new AcaoEscudo("Casa de Tijolo", 20));
            
            return new Inimigo("Capanga do Encantado", 60, 20, 60, 15, true, gm, acoes);
        }

        public static Inimigo criarMonsieurHood(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Flechada", 10));
            acoes.add(new AcaoAtacar("Tiro Certo", 25));
            acoes.add(new AcaoEscudo("Poção do caçador", 20));
            
            return new Inimigo("Caçador", 80, 20, 80, 45, true, gm, acoes);
        }


        public static Inimigo criarCrow(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Asas do corvo", 10));
            acoes.add(new AcaoAtacar("Bicada da morte", 25));
            acoes.add(new AcaoEscudo("Voou pra longe", 20));
            
            return new Inimigo("Corvo", 50, 10, 50, 65, true, gm, acoes);
        }

      
        public static Inimigo criarWitch(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Risada fantasmagórica", 10));
            acoes.add(new AcaoAtacar("Bombas de abóbora", 30));
            acoes.add(new AcaoEscudo("Fuga com a vassoura", 20));
            
            return new Inimigo("Bruxa", 90, 20, 90, 85, true, gm, acoes);
        }


        public static Inimigo criarFarquadMascot(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Pedágio da Ponte", 10));
            acoes.add(new AcaoAtacar("Cópia do Lord", 30));
            acoes.add(new AcaoEscudo("Máscara do Lord", 10));
            
            return new Inimigo("Cara da Ponte", 50, 20, 50, 85, true, gm, acoes);
        }



        // ------------------------------- CHEFOES --------------------------------------------------

        public static Inimigo criarFarquaad(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Ordem de Execução", 20));
            acoes.add(new AcaoAtacar("Golpe Baixo", 30));
            acoes.add(new AcaoEscudo("Muralha de Guardas", 30));
            acoes.add(new AcaoEfeito("Gargalhada Suprema", new EfeitoVeneno(3, gm))); 
            acoes.add(new AcaoEfeito("Ameaça de Prisão", new EfeitoFraqueza(4, gm, 25)));
            acoes.add(new AcaoEfeito("Choro do Biscoito", new EfeitoFraqueza(4, gm, 25)));
            
            return new Inimigo("Lord Farquaad", 80, 30, 80, 30, true, gm, acoes);
        }

        public static Inimigo criarDragao(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Baforada de Fogo", 45));
            acoes.add(new AcaoAtacar("Mordida Feroz", 25));
            acoes.add(new AcaoEscudo("Escamas Duras", 25));
            acoes.add(new AcaoEfeito("Fumaça Tóxica", new EfeitoVeneno(3, gm)));
            acoes.add(new AcaoEfeito("Rugido Aterrorizante", new EfeitoFraqueza(4, gm, 55)));
            acoes.add(new AcaoEfeito("Cauda Esmagadora", new EfeitoFraqueza(3, gm, 75)));
            
            // acoes.add(new AcaoAtacarArea("Sopro de Fogo em Área", 20)); 

            return new Inimigo("Dragão", 200, 60, 200, 10, true, gm, acoes);
        }


        public static Inimigo criarMerryMen(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("No Alvo", 30));
            acoes.add(new AcaoAtacar("Arqueiro Verde", 15));
            acoes.add(new AcaoEscudo("O Arco é meu Escudo!", 20));
            
            return new Inimigo("Robin Hood", 70, 20, 70, 55, true, gm, acoes);
        }


        public static Inimigo criarHammerSoldier(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("MARRETA!!!", 25));
            acoes.add(new AcaoEscudo("Armadura Pesada", 30));
            acoes.add(new AcaoEfeito("Força Bruta", new EfeitoForca(3, gm, 25))); 
            
            return new Inimigo("Soldado da Marreta", 100, 40, 100, 55, true, gm, acoes);
        }

        public static Inimigo CriarOldLady(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Raio Mágico", 20));
            acoes.add(new AcaoAtacar("Poção Explosiva", 10));
            acoes.add(new AcaoEscudo("Bolha de Sabão", 25));
            acoes.add(new AcaoEfeito("Feitiço da Fada", new EfeitoVeneno(10, gm)));
            acoes.add(new AcaoEfeito("Poeira Cegante", new EfeitoFraqueza(3, gm, 25)));

            return new Inimigo("Velha Bruxa", 90, 20, 90, 60, true, gm, acoes);
        }

        public static Inimigo criarThelonius(GameManager gm) {
            ArrayList<AcaoInimigo> acoes = new ArrayList<>();
            acoes.add(new AcaoAtacar("Super Soco", 55));
            acoes.add(new AcaoEscudo("Capa Resistente", 40));
            acoes.add(new AcaoEfeito("Capa da fraqueza", new EfeitoFraqueza(5, gm, 45)));
            acoes.add(new AcaoEfeito("Eu sou muito forte!", new EfeitoForca(4, gm, 40)));

            return new Inimigo("Thelonius", 100, 90, 100, 55, true, gm, acoes);
        }
 

        public static void setGm(GameManager gm){
            Dados.gm = gm;}
 
}






