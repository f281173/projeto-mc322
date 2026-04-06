package mc322.jogo.gerenciador;

import java.util.ArrayList;

import mc322.jogo.Musica;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

public class Prints {

    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    public void comeco() {
        System.out.println(NEGRITO + "===========================================================" + RESET);
        System.out.println(NEGRITO + CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            " + RESET);
        System.out.println(NEGRITO + "===========================================================" + RESET);

    }

    public void status_batalha(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos) {
        System.out.println("\n" + NEGRITO + "=================== STATUS DA BATALHA ===================" + RESET);

        imprimeHerois(herois, heroiAtual);
        System.out.println(NEGRITO + "                           vs" + RESET);
        imprimeInimigosVivos(inimigos);

        System.out.println(NEGRITO + "=========================================================" + RESET);
    }

    public void energia(int energia) {
        System.out.println(AMARELO + "⚡ Energia: " + RESET + energia + " disponíveis");
    }

    public void imprimeInimigosVivos(ArrayList<Inimigo> inimigos) {
        for (Inimigo enemy : inimigos) {
            if (enemy.estaVivo()) {
                System.out.println(VERMELHO + NEGRITO + enemy.getNome() + RESET + " "
                        + VERDE + "[VIVO] " + enemy.getVida() + "/" + enemy.getVidaInicial() + RESET + " de vida"
                        + "  | "
                        + AZUL + "🛡️  " + enemy.getEscudo() + RESET + " de escudo " + enemy.statusEfeitos());
            } else {
                System.out.println(VERMELHO + NEGRITO + enemy.getNome() + RESET + " "
                        + VERMELHO + "[MORTO] 💀" + RESET);
            }
        }
    }

    public void imprimeHerois(ArrayList<Heroi> herois, Heroi heroiAtual) {
        for (Heroi h : herois) {

            String destaque = (h == heroiAtual) ? AMARELO + " ⬅️ [SEU TURNO]" + RESET : "";

            if (h.estaVivo()) {
                System.out.println(CIANO + NEGRITO + h.getNome() + destaque + RESET + " "
                        + VERDE + "[VIVO] " + h.getVida() + "/" + h.getVidaInicial() + RESET + " de vida  | "
                        + AZUL + "🛡️  " + h.getEscudo() + RESET + " de escudo " + h.statusEfeitos());
            } else {

                System.out.println(CIANO + NEGRITO + h.getNome() + RESET + " "
                        + VERMELHO + "[MORTO] 💀" + RESET);
            }
        }
    }

    public void faseCompra(int limiteCompra, int cartasCompradas) {
        System.out.println(NEGRITO + "\n--- FASE DE COMPRA ---" + RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }

    public void faseBatalha() {
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }

    public void fimDeJogo(Jogador player, Musica dj) {
        if (!player.temHeroisVivos()) {
            dj.tocarMusica("../sons/I_need_sleep.wav");
            System.out.println(
                    Prints.NEGRITO + Prints.VERMELHO + "\n💀 BRUTAL!!! SUA EQUIPE FOI DERROTADA!" + Prints.RESET);
        } else {
            dj.tocarMusica("../sons/im_believer.wav");
            System.out.println(Prints.NEGRITO + Prints.VERDE + "\n🎉  PARABÉNS!!!   SUA EQUIPE VENCEU!" + Prints.RESET);
        }
    }

    public void dificuldade() {
        System.out.println("\n" + Prints.NEGRITO + "=================== CONFIGURAÇÃO DA PARTIDA ==================="
                + Prints.RESET);
        System.out.println("Escolha a dificuldade da batalha:");
        System.out.println(Prints.VERDE + "1 - Fácil (1 Inimigo)" + Prints.RESET);
        System.out.println(Prints.AMARELO + "2 - Médio (2 Inimigos)" + Prints.RESET);
        System.out.println(Prints.VERMELHO + "3 - Difícil (3 Inimigos)" + Prints.RESET);
        System.out.print(Prints.NEGRITO + "\nOpção: " + Prints.RESET);
    }

    public void jogarNovamente() {
        System.out.println("\n" + Prints.NEGRITO + "=======================================" + Prints.RESET);
        System.out.println(Prints.NEGRITO + "   Deseja jogar novamente? (1 - Sim / 2 - Não): " + Prints.RESET);
        System.out.println(Prints.NEGRITO + "=======================================" + Prints.RESET);
    }


    public static final String DIALOGO_N1 = 
        "Narrador: Era uma vez, num lugar tão tão distante, um ogro em seu pântano, que vivia sozinho, longe de tudo. Até que...\n" +
        "Shrek: O que diabos esse bando de contos de fadas está fazendo no meu quintal?!\n" +
        "Pinóquio: Lord Farquaad está atrás de todas as criaturas mágicas! Ele quer um reino perfeito, o caos está tomando conta!\n" +
        "Aldeões: Ali estão eles, se escondendo atrás do ogro!! Peguem todos!\n" +
        "Shrek: Haha. Na minha casa não.";


    public static final String DIALOGO_N10 = 
        "Criaturas mágicas: Você nos salvou, somos eternamente gratos.\\n" +
        "Shrek: Tá,tá, agora saiam do meu pântano.\n" +
        "Pinoquio: Não podemos, ele vai voltar a nos perseguir.\n"+
        "Shrek: GRRR. Eu vou falar com ele.\n" +
        "Narrador: Shrek então parte em uma viagem. Para ter seu pântano livre novamente. \n" +
        "Narrador: No meio do caminho, Shrek encontra uma carruagem. Ele vê criaturas mágicas presas!\n"+
        "Shrek: Esse lord é persistente mesmo\n"+
        "Narrador: Shrek solta as criaturas. Entretanto um anão chega..\n"+
        "Rumpelstiltskin: Eii! Estes caras eram meus. Meu poder, nãoooo!.\n" +
        "Rumpelstiltskin: Você vai pagar por isso! Vou te derrotar e te prender igual aos outros. Você vai render bastante poder mágico.\n" +
        "Shrek: Que? Pode tentar então!\n";


    public static final String DIALOGO_N21 = 
        "Narrador: No caminho Shrek vê um burro preso numa gaiola;\n"  +
        "Burro: Ei! Você, cara fortão! Me salva desses caras!\n" +
        "Shrek: Argh... lá se vai a minha paz.\n";



    public static final String DIALOGO_N22 = 
        "Narrador: No caminho Shrek vê um gato lutando contra um grupo de capangas\n"  +
        "Gato: Ei besta grotesca! Me ajude!\n" +
        "Shrek: Beleza, gatinho!\n ";


    
    public static final String DIALOGO_N5 = 
        "Narrador: Shrek arromba as pesadas portas do Castelo de Duloc.\n" +
        "Farquaad: Como um ogro imundo ousa interromper meu reino perfeito? Guardas, matem-no!\n" +
        "Shrek: Eu só quero o meu pântano de volta, seu meio-metro de tirano!\n";



    public static final String DIALOGO_N50 = 
        "Farquaad (tossindo): Espere! Um acordo! Você é forte...\n" +
        "Farquaad: Se descer até a masmorra vulcânica, derrotar o Dragão e me trazer a Princesa Fiona, devolvo seu pântano e prometo não deixar mais ninguém chegar lá!\n" +
        "Você:.....hum";

    public static final String DIALOGO_N61 = 
        "Narrador: Apoś passar por vários obstáculos, você finalmente chega ao quarto da princesa\n" +
        "Fiona: Finalmente, meu cavaleiro... Pera, você não tem um cavalo branco?\n" +
        "Você: Menos papo, mais fuga. A lagartixa gigante acordou!";

     public static final String DIALOGO_N7 = 
        "Narrador: Voltando ao pântano, você avista um cavaleiro bonitão" +
        "Príncipe Encantado: Aí está você. Você me roubou!\n" +
        "Você: Pode vir!";


    public static final String DIALOGO_N9 = 
        "Narrador: Furiosa, a fada madrinha chega ao local\n" +
        "Fada Madrinha: NINGUÉM toca no meu precioso!\n" +
        "Você: Senhora, a culpa não é minha se o seu filho é um engomadinho insuportável.\n";

    public static final String DIALOGO_N90 = 
        "Fada: Nãoooo!\n" +
        "Narrador: A Varinha da Fada quebra, mas você absorve parte do poder.\n";

    public static final String DIALOGO_N100 =
        "Narrador: Voltando ao pântano, cansados mas felizes, por que finalmente acabou.\n " +
        "Narrador: Agora viverão felizes para sempr...\n" +
        "Narrador: O ar fica pesado. A magia da Fada e a fumaça do Dragão começam a se juntar em um redemoinho...\n" +
        "Rumepel: Eai criaturas, hehehe. Eu voltei, depois de coletar toda a magia solta pelo seu rastro de caos!\n" +
        "Rumepel: Estou mais forte do que nunca. hahahaha\n" +
        "Narrador: E a batalha final se inicia...\n" ;
    




        public static void limparTela() {
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
    }
}
