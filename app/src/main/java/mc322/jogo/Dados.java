package mc322.jogo;

import java.util.ArrayList;

import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaDanoArea;
import mc322.jogo.cartas.CartaEfeito;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;

public class Dados {
    private static GameManager gm;

    

    public static ArrayList<Heroi> carregarHerois() {
        ArrayList<Heroi> herois = new ArrayList<>();
        herois.add(new Heroi("Shrek", 1000, 20, 6, 1000, 20, true, gm));
        herois.add(new Heroi("Burro", 80, 10, 7, 80, 50, true, gm));
        herois.add(new Heroi("Pinoquio", 70, 15, 5, 70, 80, true, gm));
        herois.add(new Heroi("Fiona", 90, 25, 5, 90, 40, true, gm));
        return herois;
    }

    public static Baralho carregarBaralhoGeral() {
        Baralho deck = new Baralho();

        /* todas as cartas de dano */
        deck.adicionaBaralho(new CartaDano("Bola de Fogo", "Dano: [Custo: 2 | Dano: 15] - Lança uma esfera flamejante.", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Corte de Espada", "Dano: [Custo: 1 | Dano: 10] - Um golpe rápido e preciso.", 1, 10, 0));
        deck.adicionaBaralho(new CartaDano("Soco do ogro", "Dano: [Custo: 3 | Dano: 30] - Um impacto devastador com a força do pântano.", 3, 30, 0));
        deck.adicionaBaralho(new CartaDano("Pântano tenebroso", "Dano: [Custo: 5 | Dano: 40] - Invoca as profundezas lamacentas.", 5, 40, 0));
        deck.adicionaBaralho(new CartaDano("Olhar Fofinho", "Dano: [Custo: 1 | Dano: 8] - Quebra a guarda com fofura.", 1, 8, 0));
        deck.adicionaBaralho(new CartaDano("Cebola Explosiva", "Dano: [Custo: 1 | Dano: 12] - Explode em camadas de dor.", 1, 12, 0));
        deck.adicionaBaralho(new CartaDano("Canto da Princesa", "Dano: [Custo: 1 | Dano: 10] - Atinge os tímpanos dos inimigos.", 1, 10, 0));
        deck.adicionaBaralho(new CartaDano("Coice do Burro", "Dano: [Custo: 2 | Dano: 18] - Um golpe traseiro inesperado.", 2, 18, 0));
        deck.adicionaBaralho(new CartaDano("Voadora da Fiona", "Dano: [Custo: 2 | Dano: 22] - Técnica impecável de combate.", 2, 22, 0));
        deck.adicionaBaralho(new CartaDano("Arroto de Pântano", "Dano: [Custo: 3 | Dano: 28] - Dano sônico e olfativo.", 3, 28, 0));
        deck.adicionaBaralho(new CartaDano("Ataque de Cócegas", "Dano: [Custo: 2 | Dano: 15] - Desconcerta o oponente.", 2, 15, 0));
        deck.adicionaBaralho(new CartaDano("Fúria de Ogro", "Dano: [Custo: 4 | Dano: 38] - O poder bruto de um ogro irritado.", 4, 38, 0));
        deck.adicionaBaralho(new CartaDano("Invocação do Dragão", "Dano: [Custo: 5 | Dano: 50] - Fogo e destruição aliados.", 5, 50, 0));

        /* Cartas de dano em área */
        deck.adicionaBaralho(new CartaDanoArea("Terremoto do Pântano", "Dano em área: [Custo: 4 | Dano: 15 (ÁREA)] - Atinge TODOS os inimigos!", 4, 15, 0));
        deck.adicionaBaralho(new CartaDanoArea("Giro do Gato", "Dano em área: [Custo: 3 | Dano: 10 (ÁREA)] - Ataque giratório veloz em TODOS os inimigos!", 3, 10, 0));

        /* todas as cartas de escudo */
        deck.adicionaBaralho(new CartaEscudo("Proteção", "Escudo: [Custo: 3 | Escudo: 20] - Prepara uma defesa sólida.", 3, 20, 1));
        deck.adicionaBaralho(new CartaEscudo("Beijo de amor", "Escudo: [Custo: 4 | Escudo: 50] - Magia poderosa de proteção.", 4, 50, 1));
        deck.adicionaBaralho(new CartaEscudo("Botas de Couro", "Escudo: [Custo: 1 | Escudo: 10] - Aumenta a agilidade defensiva.", 1, 10, 1));
        deck.adicionaBaralho(new CartaEscudo("Camadas de Cebola", "Escudo: [Custo: 1 | Escudo: 12] - Proteção em camadas.", 1, 12, 1));
        deck.adicionaBaralho(new CartaEscudo("Waffles Quentinhos", "Escudo: [Custo: 2 | Escudo: 18] - Conforto que fortalece a guarda.", 2, 18, 1));
        deck.adicionaBaralho(new CartaEscudo("Pele Verde e Grossa", "Escudo: [Custo: 3 | Escudo: 25] - Resistência natural de ogro.", 3, 25, 1));
        deck.adicionaBaralho(new CartaEscudo("Panela de Lama", "Escudo: [Custo: 2 | Escudo: 20] - Absorve os impactos.", 2, 20, 1));
        deck.adicionaBaralho(new CartaEscudo("Muralha do Dragão", "Escudo: [Custo: 5 | Escudo: 60] - Defesa impenetrável do castelo.", 5, 45, 1));

        /* todas as cartas de efeito */
        deck.adicionaBaralho(new CartaEfeito("Hálito de Ogro", "Efeito Veneno: [Custo: 3 | Turnos: 5] - Veneno constante e fedorento.", 3, 2, TiposEfeitos.VENENO,  5));
        deck.adicionaBaralho(new CartaEfeito("Ensopado de Olho", "Efeito Veneno: [Custo: 3 | Turnos: 3] - Veneno potente de longa duração.", 3, 2, TiposEfeitos.VENENO,  3));
        deck.adicionaBaralho(new CartaEfeito("Arroto de Cebola", "Efeito Fraqueza: [Custo: 4 | Fraqueza: 25% | Turnos: 4] - Desmotiva o ataque inimigo.", 4, 2, TiposEfeitos.FRAQUEZA,  4));
        deck.adicionaBaralho(new CartaEfeito("Choro do Biscoito", "Efeito Fraqueza: [Custo: 3 | Fraqueza: 25% | Turnos: 4] - Corta o coração do oponente.", 3, 2, TiposEfeitos.FRAQUEZA,  4));
        deck.adicionaBaralho(new CartaEfeito("Banho de Lama", "Efeito Fraqueza: [Custo: 2 | Fraqueza: 25% | Turnos: 3] - Deixa as armas do inimigo escorregadias.", 2, 2, TiposEfeitos.FRAQUEZA,  3));
        deck.adicionaBaralho(new CartaEfeito("Poção de Força", "Efeito Força:[Custo: 2 | Bônus: 25% | Turnos: 3] - Aumenta a potência de todos os ataques.",  2, 2, TiposEfeitos.FORCA,  3));
        deck.adicionaBaralho(new CartaEfeito("Rugido de Guerra",  "Efeito Força: [Custo: 4 | Bônus: 25% | Turnos: 2] - Dobra o dano dos golpes por um curto período.", 4, 2, TiposEfeitos.FORCA,  2));

        return deck;
    }




//----------------------INIMIGOS GENERICOS --------------------------------------------

    public static Inimigo criarAldeao(GameManager gm) { //ogre hunter
        Inimigo aldeao = new Inimigo("Aldeão Furioso", 40, 0, 40, 15, true, gm);
        ArrayList<Carta> deckAldeao = new ArrayList<>();
        deckAldeao.add(new CartaDano("Tochada", "[Dano: 12] - Uma tocha quente no seu rosto.", 0, 12, 0));
        deckAldeao.add(new CartaDano("Golpe com graveto", "[Dano: 15] - Uma espetada pontiaguda.", 0, 15, 0));
        deckAldeao.add(new CartaEscudo("Recuar com Medo", "[Escudo: 10] - Cobre o rosto com as mãos e recua.", 0, 10, 1));
        aldeao.transformaDeck(deckAldeao);
        return aldeao;
    }


    public static Inimigo criarSoldadoFarquad(GameManager gm) {  //guards
        Inimigo soldadoLord = new Inimigo("Soldados do Lord", 60, 0, 60, 25, true, gm);
        ArrayList<Carta> decksoldado = new ArrayList<>();
        decksoldado.add(new CartaDano("Lave bem o seu .. pé", "[Dano: 20] - Na cabeça o shampoo.", 0, 20, 0));
        decksoldado.add(new CartaDano("Chute alto", "[Dano: 15] - Um chute alto.", 0, 15, 0));
        decksoldado.add(new CartaEscudo("Atrás do Lord", "[Escudo: 10] - Recua para atrás do lord.", 0, 10, 1));
        soldadoLord.transformaDeck(decksoldado);
        return soldadoLord;
    }


    public static Inimigo criarLobo(GameManager gm) {  //wolf
        Inimigo lobo = new Inimigo("Lobo", 50, 10, 50, 95, true, gm);
        ArrayList<Carta> decklobo = new ArrayList<>();
        decklobo.add(new CartaDano("Mordida", "[Dano: 20] - Raawwh.", 0, 20, 0));
        decklobo.add(new CartaDano("Líder da alcateia", "[Dano: 35] - Mostra quem é o alfa.", 0, 35, 0));
        decklobo.add(new CartaEscudo("Lua cheia", "[Escudo: 10] - O lobo se cura em lua cheia.", 0, 10, 1));
        lobo.transformaDeck(decklobo);
        return lobo;
    }


    public static Inimigo criarCapanga(GameManager gm) {  //merry men
        Inimigo capanga = new Inimigo("Capanga do Encantado", 80, 20, 80, 65, true, gm);
        ArrayList<Carta> deckcapanga = new ArrayList<>();
        deckcapanga.add(new CartaDano("Jab de esquerda", "[Dano: 20] - Ele é canhoto.", 0, 20, 0));
        deckcapanga.add(new CartaDano("Golpe de espada", "[Dano: 25] - Está afiada.", 0, 25, 0));
        deckcapanga.add(new CartaEscudo("Cabeleira meio esvoaçante", "[Escudo: 20] - Não é igual a original.", 0, 20, 1));
        capanga.transformaDeck(deckcapanga);
        return capanga;
    }


    public static Inimigo criarBruxa(GameManager gm) {  //witch
        Inimigo bruxa = new Inimigo("Bruxa", 90, 20, 90, 85, true, gm);
        ArrayList<Carta> deckBruxa = new ArrayList<>();
        deckBruxa.add(new CartaDano("Risada fantasmagórica", "[Dano: 20] - Não é engraçado.", 0, 20, 0));
        deckBruxa.add(new CartaDano("Bombas de abóbora", "[Dano: 40] - Kabum.", 0, 40, 0));
        deckBruxa.add(new CartaEscudo("Fuga com a vassoura", "[Escudo: 30] - Fugiu e se recuperou.", 0, 30, 1));
        bruxa.transformaDeck(deckBruxa);
        return bruxa;
    }



    //farquad mascote
    //big bad wolf




// -------------------------------CHEFOES--------------------------------------------------

    public static Inimigo criarFarquaad(GameManager gm) { //lord farquaad
        Inimigo farquaad = new Inimigo("Lord Farquaad", 60, 30, 60, 30, true, gm);
        ArrayList<Carta> deckFarquaad = new ArrayList<>();
        deckFarquaad.add(new CartaDano("Ordem de Execução", "[Dano: 20] - Os guardas atacam sem piedade.", 0, 20, 0));
        deckFarquaad.add(new CartaDano("Golpe Baixo", "[Dano: 30] - Um ataque covarde, mas efetivo.", 0, 30, 0));
        deckFarquaad.add(new CartaEscudo("Muralha de Guardas", "[Escudo: 30] - Usa seus soldados como barreira.", 0, 30, 1));
        deckFarquaad.add(new CartaEfeito("Gargalhada Suprema", "[Turnos: 3] - O Lord ri de você impiedosamente, ferindo seu orgulho.", 0, 2, TiposEfeitos.VENENO,  3));
        deckFarquaad.add(new CartaEfeito("Ameaça de Prisão", "[Fraqueza: 25% | Turnos: 4] - O medo das masmorras tira sua vontade de lutar.", 0, 2, TiposEfeitos.FRAQUEZA,  4));
        deckFarquaad.add(new CartaEfeito("Choro do Biscoito", "[Fraqueza: 25% | Turnos: 4] - Tortura o homem de gengibre, te deixando mal.", 0, 2, TiposEfeitos.FRAQUEZA,  4));
        farquaad.transformaDeck(deckFarquaad);
        return farquaad;
    }

    
    public static Inimigo criarDragao(GameManager gm) {  //dragon
        Inimigo dragao = new Inimigo("Dragão", 100, 40, 100, 10, true, gm);
        ArrayList<Carta> deckDragao = new ArrayList<>();
        deckDragao.add(new CartaDano("Baforada de Fogo", "[Dano: 45] - Um sopro ardente e mortal.", 0, 45, 0));
        deckDragao.add(new CartaDano("Mordida Feroz", "[Dano: 15] - Dentes afiados como espadas.", 0, 15, 0));
        deckDragao.add(new CartaEfeito("Fumaça Tóxica", "[Dano Veneno: 10 | Turnos: 3] - A fumaça queima os pulmões rodada a rodada.", 0, 2, TiposEfeitos.VENENO,  3));
        deckDragao.add(new CartaEscudo("Escamas Duras", "[Escudo: 15] - Pele impenetrável.", 0, 15, 1));
        deckDragao.add(new CartaEfeito("Rugido Aterrorizante", "[Fraqueza: 25% | Turnos: 4] - O medo reduz sua força de ataque.", 0, 2, TiposEfeitos.FRAQUEZA, 4));
        deckDragao.add(new CartaEfeito("Cauda Esmagadora", "[Fraqueza: 25% | Turnos: 3] - Um golpe que te deixa desnorteado e fraco.", 0, 2, TiposEfeitos.FRAQUEZA,  3));
        deckDragao.add(new CartaDanoArea("Sopro de Fogo em Área", "[Dano Área: 20] - O Dragão cospe fogo na arena inteira!", 0, 20, 0));
        dragao.transformaDeck(deckDragao);
        return dragao;
    }


    public static Inimigo criarEncantado(GameManager gm) {  //thelonius
        Inimigo encantado = new Inimigo("Príncipe Encantado", 80, 40, 80, 55, true, gm);
        ArrayList<Carta> deckEncantado = new ArrayList<>();
        deckEncantado.add(new CartaDano("O Herói destinado", "[Dano: 25] - Um golpe com pose heroica.", 0, 25, 0));
        deckEncantado.add(new CartaEscudo("Cabelos brilhantes", "[Escudo: 20] - O reflexo protege contra ataques.", 0, 20, 1));
        deckEncantado.add(new CartaEfeito("Pose no Espelho", "[Buff Força | Turnos: 3] - O Príncipe se admira e ganha Força!", 0, 2, TiposEfeitos.FORCA,  3));
        encantado.transformaDeck(deckEncantado);
        return encantado;
    }


    public static Inimigo criarFada(GameManager gm) {  //hammer soldier
        Inimigo fada = new Inimigo("Fada Madrinha", 90, 20, 90, 60, true, gm);
        ArrayList<Carta> deckFada = new ArrayList<>();
        deckFada.add(new CartaDano("Raio Mágico", "[Dano: 30] - Um feitiço direto da varinha.", 0, 30, 0));
        deckFada.add(new CartaDano("Poção Explosiva", "[Dano: 10] - Arremessa um frasco instável.", 0, 10, 0));
        deckFada.add(new CartaEscudo("Bolha de Sabão", "[Escudo: 25] - Uma barreira mágica reluzente.", 0, 25, 1));
        deckFada.add(new CartaEfeito("Feitiço da Fada", "[Turnos: 10] - Uma maldição longa e persistente.", 0, 2, TiposEfeitos.VENENO,  10));
        deckFada.add(new CartaEfeito("Poeira Cegante", "[Turnos: 3] - Fada joga brilho nos seus olhos.", 0, 2, TiposEfeitos.FRAQUEZA,  3));
        deckFada.add(new CartaDanoArea("Chuva de Poções", "[Dano Área: 15] - A Fada joga poções em todos os heróis!", 0, 15, 0));
        fada.transformaDeck(deckFada);
        return fada;
    }


    public static Inimigo criarRumpelFraco(GameManager gm) {  
        Inimigo rumpel = new Inimigo("Rumpelstiltskin", 60, 0, 60, 25, true, gm);
        ArrayList<Carta> deckRumpel = new ArrayList<>();
        deckRumpel.add(new CartaDano("Pena em chamas", "[Dano: 15] - Escreve no ar um ataque mágico.", 0, 15, 0));
        deckRumpel.add(new CartaEfeito("Tinta Tóxica", "[Turnos: 3] - Joga tinta de contrato na sua cara!", 0, 2, TiposEfeitos.VENENO,  3));
        deckRumpel.add(new CartaEscudo("Esconder na Carruagem", "[Escudo: 30] - Foge para um local seguro.", 0, 30, 1));
        rumpel.transformaDeck(deckRumpel);
        return rumpel;
    }


    public static Inimigo criarRumpel(GameManager gm) { 
        Inimigo rumpel = new Inimigo("Rumpelstiltskin", 100, 70, 100, 55, true, gm);
        ArrayList<Carta> deckRumpel = new ArrayList<>();
        deckRumpel.add(new CartaDano("Pena em chamas", "[Dano: 55] - Escreve no ar um ataque mágico.", 0, 55, 0));
        deckRumpel.add(new CartaEfeito("Contrato Mágico", "[Fraqueza: 25% | Turnos: 5] - Te obriga a assinar um contrato abusivo.", 0, 2, TiposEfeitos.FRAQUEZA,  5));
        deckRumpel.add(new CartaEfeito("Tinta Tóxica", "[Turnos: 3] - Joga tinta de contrato na sua cara!", 0, 2, TiposEfeitos.VENENO,  3));
        deckRumpel.add(new CartaEscudo("Esconder na Carruagem", "[Escudo: 40] - Foge para um local seguro.", 0, 40, 1));
        //botar mais carta

        rumpel.transformaDeck(deckRumpel);
        return rumpel;
    }



    public static void setGm(GameManager gm) {
        Dados.gm = gm;
    }
}