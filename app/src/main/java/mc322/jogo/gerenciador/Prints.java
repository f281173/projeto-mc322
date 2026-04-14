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
        NEGRITO + "Narrador: " + RESET + "Era uma vez, num lugar tão tão distante, um ogro em seu pântano, que vivia sozinho, longe de tudo. Até que...\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "O que diabos esse bando de contos de fadas está fazendo no meu quintal?!\n" +
        AZUL + NEGRITO + "Pinóquio: " + RESET + "Lord Farquaad está atrás de todas as criaturas mágicas! Ele quer um reino perfeito, o caos está tomando conta!\n" +
        VERMELHO + NEGRITO + "Aldeões: " + RESET + "Ali estão eles, se escondendo atrás do ogro!! Peguem todos!\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Haha. Na minha casa não.\n";


    public static final String DIALOGO_N10 = 
        AZUL + NEGRITO + "Criaturas mágicas: " + RESET + "Você nos salvou, somos eternamente gratos.\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Tá, tá, agora saiam do meu pântano.\n" +
        AZUL + NEGRITO + "Porco: " + RESET + "Não podemos, ele vai voltar a nos perseguir.\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "GRRR. Eu vou falar com ele.\n" +
        NEGRITO + "Narrador: " + RESET + "Shrek então parte em uma viagem. Para ter seu pântano livre novamente.\n" +
        NEGRITO + "Narrador: " + RESET + "No meio do caminho, Shrek encontra uma carruagem. Ele vê criaturas mágicas presas!\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Esse lord é persistente mesmo.\n" +
        NEGRITO + "Narrador: " + RESET + "Shrek solta as criaturas. Entretanto um anão chega...\n" +
        VERMELHO + NEGRITO + "Caçador: " + RESET + "Eii! Estes caras eram meus. Meu dinheiro, nãoooo!\n" +
        VERMELHO + NEGRITO + "Caçador: " + RESET + "Você vai pagar por isso! Vou te derrotar e te prender igual aos outros. Você deve valer bastante.\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Que? Pode tentar então!\n";



    public static final String DIALOGO_N21 = 
        NEGRITO + "Narrador: " + RESET + "No caminho Shrek vê um burro preso numa gaiola.\n" +
        AZUL + NEGRITO + "Burro: " + RESET + "Ei! Você, cara fortão! Me salva desses caras!\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Argh... lá se vai a minha paz.\n";




    public static final String DIALOGO_N22 = 
        NEGRITO + "Narrador: " + RESET + "No caminho Shrek vê um gato lutando contra um grupo de capangas.\n" +
        AZUL + NEGRITO + "Gato: " + RESET + "Ei besta grotesca! Me ajude!\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Beleza, gatinho!\n";




    
    public static final String DIALOGO_N5 = 
        NEGRITO + "Narrador: " + RESET + "Shrek arromba as pesadas portas do Castelo de Duloc.\n" +
        VERMELHO + NEGRITO + "Farquaad: " + RESET + "Como um ogro imundo ousa interromper meu reino perfeito? Guardas, matem-no!\n" +
        AZUL + NEGRITO + "Shrek: " + RESET + "Eu só quero o meu pântano de volta, seu meio-metro de tirano!\n";





    public static final String DIALOGO_N50 = 
        VERMELHO + NEGRITO + "Farquaad (tossindo): " + RESET + "Espere! Um acordo! Você é forte...\n" +
        VERMELHO + NEGRITO + "Farquaad: " + RESET + "Se descer até a torre perdida, derrotar a bruxa velha e me trazer a Princesa Fiona, devolvo seu pântano e prometo não deixar mais ninguém chegar lá!\n" +
        AZUL + NEGRITO + "Você: " + RESET + ".....hum\n";

    public static final String DIALOGO_N61 = 
        NEGRITO + "Narrador: " + RESET + "Após passar por vários obstáculos, você finalmente chega ao quarto da princesa.\n" +
        AZUL + NEGRITO + "Fiona: " + RESET + "Finalmente, meu cavaleiro... Pera, você não tem um cavalo branco?\n" +
        AZUL + NEGRITO + "Você: " + RESET + "Menos papo, mais fuga. A bruxa velha  pode acordar!\n" +
        AZUL + NEGRITO + "Bruxa velha: " + RESET + "Já acordei, hihihi!\n";;



     public static final String DIALOGO_N7 = 
        NEGRITO + "Narrador: " + RESET + "Voltando ao pântano, você avista um cavaleiro com uma marreta.\n" +
        VERMELHO + NEGRITO + "Cavaleiro da Marreta: " + RESET + "Aí está você. Você me roubou!\n" +
        AZUL + NEGRITO + "Você: " + RESET + "Pode vir!\n";



    public static final String DIALOGO_N9 = 
        NEGRITO + "Narrador: " + RESET + "Furioso, Thelonius chega ao local.\n" +
        VERMELHO + NEGRITO + "Thelonius: " + RESET + "Meu amigo marreta, você o destruiu!\n" +
        AZUL + NEGRITO + "Você: " + RESET + "Colega, a culpa não é minha se o seu amigo é fraco.\n";

    public static final String DIALOGO_N90 = 
        VERMELHO + NEGRITO + "Thelonius: " + RESET + "Nãoooo! Não vai ficar assim. \n" + 
        "Narrador: Thelonius pega o apito e..... PIIIIIIIIIIIIII, Thelonius cai no chão." +
        NEGRITO + "Narrador: " + RESET + "A capa mágica de Thelonius rasga, mas você absorve parte do poder.\n";


    public static final String DIALOGO_N100 = 
        NEGRITO + "Narrador: " + RESET + "Não muito tempo depois do apito, vocês escutam um rugido.\n" +
        NEGRITO + "Narrador: " + RESET + "Um dragão surge dos céus...\n" +
        NEGRITO + "Narrador: " + RESET + "O ar fica pesado. O tempo escurece, fumaças saem das narinas do Dragão...\n" +
        VERMELHO + NEGRITO + "Dragão: " + RESET + "ROARRRRR!\n" +
        VERMELHO + NEGRITO + "Shrek: " + RESET + "Tá de brincadeira\n" +
        NEGRITO + "Narrador: " + RESET + "E a batalha final se inicia...\n";
    

    public static void limparTela() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); }



    public static void PrintaMapa(String faseAtual) {
        String mapa = 
        "                                [Inicio]\n" +
        "                                   |\n" +
        "                               [Batalha]\n" +
        "                                   |\n" +
        "                        [Acampamento do Caçador]\n" +
        "                                   |\n" +
        "                                [Encruzilhada]\n" +
        "                             /                         \\\n" +
        "      [Flor azul com espinhos vermelhos]              [Flor vermelha com espinhos azuis]\n" +
        "               /             \\                                     /               \\\n" +
        " [Caminho bonito]      [Caminho tortuoso]              [Tempo na fogueira]       [Floresta à noite]\n" +
        "         |               /         \\                              |                   |\n" +
        " [Floresta densa] [Seguir pelo rio] [Passar pela ponte]    [Cabana do caçador] [Seguindo pela floresta]\n" +
        "         \\               |         /                |              /                   /\n" +
        "          \\______________\\________/________________/_____________/____________________/\n" +
        "                                  |\n" +
        "                            [Castelo de Duloc]\n" +
        "                                  |\n" +
        "                                [Proposta]\n" +
        "                             /          \\\n" +
        "                      [Vamos lá]   [Quero não!]\n" +
        "                         |                |\n" +
        "              [Torre da Bruxa Velha] [Volta para o pântano]\n" +
        "                         \\                /\n" +
        "                          \\              /\n" +
        "                            [Na floresta]\n" +
        "                           /             \\\n" +
        "                 [Voltar agora]   [Descansar um pouco e voltar depois]\n" +
        "                           \\             /\n" +
        "                           [Seguir jornada]\n" +
        "                                  |\n" +
        "                              [Continuar]\n" +
        "                                  |\n" +
        "                          [I need a hero]\n";

        mapa = mapa.replace("[" + faseAtual + "]", VERDE + NEGRITO + "[" + faseAtual + " 📍]" + RESET);

        System.out.println(mapa);
    }

}

