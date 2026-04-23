package mc322.jogo.gerenciador;

import java.util.ArrayList;

import mc322.jogo.Cores;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;

/**
 * Classe responsável por gerenciar as principais impressões durante o jogo.
 * Essa classe não manipula nenhuma entidade.
 */
public class Prints {

    /**
     * Método responsável pela tela de início do jogo.
     */
    public void comeco() {
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + Cores.CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            "
                + Cores.RESET);
        System.out.println(Cores.NEGRITO + "===========================================================" + Cores.RESET);

    }

    /**
     * Método responsável pela tela do momento da batalha no Turno do Heroi.
     * 
     * @param heroiAtual Heroi que vai executar o ataque naquele momento.
     * @param herois     vetor com todos os Heróis escolhidos naquela batalha
     * @param inimigos   vetor com todo os inmigos escolhidos para aquela Batalha
     */
    public void status_batalha(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos) {
        heroiAtual.imprimeEfeitos();
        System.out.println();
        System.out.println(
                "\n" + Cores.NEGRITO + "=================== STATUS DA BATALHA ===================" + Cores.RESET);

        imprimeHerois(herois, heroiAtual);
        System.out.println(Cores.NEGRITO + "                           vs" + Cores.RESET);
        imprimeInimigosVivos(inimigos);

        System.out.println(Cores.NEGRITO + "=========================================================" + Cores.RESET);
    }

    /**
     * Método responsável por imprimir a energia do Heroi na tela
     * 
     * @param energia inteiro que representa o atritbuto energia do herói
     */
    public void energia(int energia) {
        System.out.println(Cores.AMARELO + "⚡ Energia: " + Cores.RESET + energia + " disponíveis");
    }

    /**
     * Método responsável por imprimir todas as informações do Inimigo no status de
     * batalha.
     * 
     * @param inimigos vetor com todos os inimigos escolhidos naquela Batalha.
     */
    public void imprimeInimigosVivos(ArrayList<Inimigo> inimigos) {
        for (Inimigo enemy : inimigos) {
            if (enemy.estaVivo()) {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + enemy.getVida() + "/" + enemy.getVidaInicial() + Cores.RESET
                        + " de vida"
                        + "  | "
                        + Cores.AZUL + "🛡️  " + enemy.getEscudo() + Cores.RESET + " de escudo"
                        + enemy.statusEfeitoForca() + " " + enemy.statusEfeitoFraqueza());
            } else {
                System.out.println(Cores.VERMELHO + Cores.NEGRITO + enemy.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    /**
     * Imprime as informações do Heroi no momento do seu turno.
     * 
     * @param herois     vetor com todos os herois escolhidos
     * @param heroiAtual héroi que vai executar a ação
     */
    public void imprimeHerois(ArrayList<Heroi> herois, Heroi heroiAtual) {
        for (Heroi h : herois) {

            String destaque = (h == heroiAtual) ? Cores.AMARELO + " ⬅️ [SEU TURNO]" + Cores.RESET : "";

            if (h.estaVivo()) {
                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + destaque + Cores.RESET + " "
                        + Cores.VERDE + "[VIVO] " + h.getVida() + "/" + h.getVidaInicial() + Cores.RESET
                        + " de vida  | "
                        + Cores.AZUL + "🛡️  " + h.getEscudo() + Cores.RESET + " de escudo"
                        + h.statusEfeitoForca() + " " + h.statusEfeitoFraqueza());
            } else {

                System.out.println(Cores.CIANO + Cores.NEGRITO + h.getNome() + Cores.RESET + " "
                        + Cores.VERMELHO + "[MORTO] 💀" + Cores.RESET);
            }
        }
    }

    /**
     * Método responsável por gerenciar a tela do momento de compra de cartas de
     * cada Herói em seu turno.
     * 
     * @param limiteCompra    Limite de compras de cartas em um único turno
     * @param cartasCompradas quantidade de cartas que já foram compradas.
     */
    public void faseCompra(int limiteCompra, int cartasCompradas) {
        System.out.println(Cores.NEGRITO + "\n--- FASE DE COMPRA ---" + Cores.RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

    /**
     * Método responsável pela tela no ínicio da batalha, logo após a fase de
     * compra.
     */
    public void faseBatalha() {
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(Cores.NEGRITO + "Escolha: " + Cores.RESET);
    }

    /**
     * Método que imprime a tela de fim de jogo.
     * 
     * @param player objeto que gerencia os heróis (necessário para verificar qual
     *               está vivo)
     * @param dj     objeto musica para trocar para uma outra música.
     */
    public void fimDeJogo(Jogador player, Musica dj) {
        if (!player.temHeroisVivos()) {
            dj.tocarMusica("../sons/I_need_sleep.wav");
            System.out.println(
                    Cores.NEGRITO + Cores.VERMELHO + "\n💀 BRUTAL!!! SUA EQUIPE FOI DERROTADA!" + Cores.RESET);
        } else {
            dj.tocarMusica("../sons/im_believer.wav");
            System.out.println(Cores.NEGRITO + Cores.VERDE + "\n🎉  PARABÉNS!!!   SUA EQUIPE VENCEU!" + Cores.RESET);
        }
    }

    public void dificuldade() {
        System.out.println("\n" + Cores.NEGRITO + "=================== CONFIGURAÇÃO DA PARTIDA ==================="
                + Cores.RESET);
        System.out.println("Escolha a dificuldade da batalha:");
        System.out.println(Cores.VERDE + "1 - Fácil (1 Inimigo)" + Cores.RESET);
        System.out.println(Cores.AMARELO + "2 - Médio (2 Inimigos)" + Cores.RESET);
        System.out.println(Cores.VERMELHO + "3 - Difícil (3 Inimigos)" + Cores.RESET);
        System.out.print(Cores.NEGRITO + "\nOpção: " + Cores.RESET);
    }

    public void jogarNovamente() {
        System.out.println("\n" + Cores.NEGRITO + "=======================================" + Cores.RESET);
        System.out.println(Cores.NEGRITO + "   Deseja jogar novamente? (1 - Sim / 2 - Não): " + Cores.RESET);
        System.out.println(Cores.NEGRITO + "=======================================" + Cores.RESET);
    }


    public static void limparTela() {
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
    }


    public static final String DIALOGO_N1 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Era uma vez, num lugar tão tão distante, um ogro em seu pântano, que vivia sozinho, longe de tudo. Até que...\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "O que diabos esse bando de contos de fadas está fazendo no meu quintal?!\n" +
        Cores.AZUL + Cores.NEGRITO + "Porco: " + Cores.RESET + "Lord Farquaad está atrás de todas as criaturas mágicas! Ele quer um reino perfeito, o caos está tomando conta!\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Aldeões: " + Cores.RESET + "Ali estão eles, se escondendo atrás do ogro!! Peguem todos!\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Haha. Na minha casa não.\n";


    public static final String DIALOGO_N10 = 
        Cores.AZUL + Cores.NEGRITO + "Criaturas mágicas: " + Cores.RESET + "Você nos salvou, somos eternamente gratos.\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Tá, tá, agora saiam do meu pântano.\n" +
        Cores.AZUL + Cores.NEGRITO + "Porco: " + Cores.RESET + "Não podemos, ele vai voltar a nos perseguir.\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "GRRR. Eu vou falar com ele.\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Shrek então parte em uma viagem. Para ter seu pântano livre novamente.\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "No meio do caminho, Shrek encontra uma carruagem. Ele vê criaturas mágicas presas!\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Esse lord é persistente mesmo.\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Shrek solta as criaturas. Entretanto um caçador chega...\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Caçador: " + Cores.RESET + "Eii! Estes caras eram meus. Meu dinheiro, nãoooo!\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Caçador: " + Cores.RESET + "Você vai pagar por isso! Vou te derrotar e te prender igual aos outros. Você deve valer bastante.\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Que? Pode tentar então!\n";



    public static final String DIALOGO_N21 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "No caminho Shrek vê um burro preso numa gaiola.\n" +
        Cores.AZUL + Cores.NEGRITO + "Burro: " + Cores.RESET + "Ei! Você, cara fortão! Me salva desses caras!\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Argh... lá se vai a minha paz.\n";




    public static final String DIALOGO_N22 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "No caminho Shrek vê um boneco de madeira lutando contra um grupo de capangas.\n" +
        Cores.AZUL + Cores.NEGRITO + "Pinóquio: " + Cores.RESET + "Ei besta grotesca! Me ajude!\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Beleza, brinquedinho!\n";




    
    public static final String DIALOGO_N5 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Shrek arromba as pesadas portas do Castelo de Duloc.\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Farquaad: " + Cores.RESET + "Como um ogro imundo ousa interromper meu reino perfeito? Guardas, matem-no!\n" +
        Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Eu só quero o meu pântano de volta, seu meio-metro de tirano!\n";





    public static final String DIALOGO_N50 = 
        Cores.VERMELHO + Cores.NEGRITO + "Farquaad (tossindo): " + Cores.RESET + "Espere! Um acordo! Você é forte...\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Farquaad: " + Cores.RESET + "Se descer até a torre perdida, derrotar a bruxa velha e me trazer a Princesa Fiona, devolvo seu pântano e prometo não deixar mais ninguém chegar lá!\n" +
        Cores.AZUL + Cores.NEGRITO + "Você: " + Cores.RESET + ".....hum\n";

    public static final String DIALOGO_N61 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Após passar por vários obstáculos, você finalmente chega ao quarto da princesa.\n" +
        Cores.AZUL + Cores.NEGRITO + "Fiona: " + Cores.RESET + "Finalmente, meu cavaleiro... Pera, você não tem um cavalo branco?\n" +
        Cores.AZUL + Cores.NEGRITO + "Você: " + Cores.RESET + "Menos papo, mais fuga. A bruxa velha  pode acordar!\n" +
        Cores.AZUL + Cores.NEGRITO + "Bruxa velha: " + Cores.RESET + "Já acordei, hihihi!\n";;



     public static final String DIALOGO_N7 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Voltando ao pântano, você avista um cavaleiro com uma marreta.\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Cavaleiro da Marreta: " + Cores.RESET + "Aí está você. Você me roubou!\n" +
        Cores.AZUL + Cores.NEGRITO + "Você: " + Cores.RESET + "Pode vir!\n";



    public static final String DIALOGO_N9 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Furioso, Thelonius chega ao local.\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Thelonius: " + Cores.RESET + "Meu amigo marreta, você o destruiu!\n" +
        Cores.AZUL + Cores.NEGRITO + "Você: " + Cores.RESET + "Colega, a culpa não é minha se o seu amigo é fraco.\n";

    public static final String DIALOGO_N90 = 
        Cores.VERMELHO + Cores.NEGRITO + "Thelonius: " + Cores.RESET + "Nãoooo! Não vai ficar assim. \n" + 
        "Narrador: Thelonius pega o apito e..... PIIIIIIIIIIIIII, Thelonius cai no chão." +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "A capa mágica de Thelonius rasga, mas você absorve parte do poder.\n";


    public static final String DIALOGO_N100 = 
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Não muito tempo depois do apito, vocês escutam um rugido.\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "Um dragão surge dos céus...\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "O ar fica pesado. O tempo escurece, fumaças saem das narinas do Dragão...\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Dragão: " + Cores.RESET + "ROARRRRR!\n" +
        Cores.VERMELHO + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Tá de brincadeira\n" +
        Cores.NEGRITO + "Narrador: " + Cores.RESET + "E a batalha final se inicia...\n";
    

    
    public static void PrintaMapa(String faseAtual) {
        String idAtual = "";
        
        switch (faseAtual) {
            case "Inicio": idAtual = "01"; break;
            case "Batalha": idAtual = "02"; break;
            case "Acampamento do Caçador": idAtual = "03"; break;
            case "Encruzilhada": idAtual = "04"; break;
            case "Flor azul com espinhos vermelhos": idAtual = "05"; break;
            case "Flor vermelha com espinhos azuis": idAtual = "06"; break;
            case "Caminho bonito": idAtual = "07"; break;
            case "Caminho tortuoso": idAtual = "08"; break;
            case "Tempo na fogueira": idAtual = "09"; break;
            case "Floresta à noite": idAtual = "10"; break;
            case "Floresta densa": idAtual = "11"; break;
            case "Seguir pelo rio": idAtual = "12"; break;
            case "Passar pela ponte": idAtual = "13"; break;
            case "Cabana": idAtual = "14"; break;
            case "Quintal de uma casa": idAtual = "30"; break;
            case "Seguindo pela floresta": idAtual = "15"; break;
            case "Castelo de Duloc": idAtual = "16"; break;
            case "Proposta": idAtual = "17"; break;
            case "Vamos lá": idAtual = "18"; break;
            case "Quero não!": idAtual = "19"; break;
            case "Torre da Bruxa Velha": idAtual = "20"; break;
            case "Volta para o pântano": idAtual = "21"; break;
            case "Na floresta": idAtual = "22"; break;
            case "Voltar agora": idAtual = "23"; break;
            case "Descansar um pouco e voltar depois": idAtual = "24"; break;
            case "Seguir jornada": idAtual = "25"; break;
            case "Continuar": idAtual = "26"; break;
            case "I need a hero": idAtual = "27"; break;
            case "Mercado de Duloc": idAtual = "28"; break;
            case "Tenda Misteriosa": idAtual = "29"; break;
        }


        String mapa = 
        "                             [01]\n" +
        "                              |\n" +
        "                             [02]\n" +
        "                              |\n" +
        "                             [03]\n" +
        "                              |\n" +
        "                             [04]\n" +
        "                           /       \\\n" +
        "                          /         \\\n" +
        "                       [05]          [06]\n" +
        "                      /    \\       /     \\\n" +
        "                   [07]  [08]     [09]     [10]\n" +
        "                  /   |    |      /  \\      |\n" +
        "                [11] [12] [13]  [14] [30]   [15]\n" +
        "                  \\   |    /     |   |       /\n" +
        "                   \\__|___/______|___|______/\n" +
        "                              |\n" +
        "                            [16]\n" +
        "                              |\n" +
        "                            [17]\n" +
        "                              |\n" +
        "                            [28]\n" +
        "                           /    \\\n" +
        "                        [18]    [19]\n" +
        "                          |      |\n" +
        "                        [20]    [21]\n" +
        "                           \\    /\n" +
        "                            [22]\n" +
        "                              |\n" +
        "                            [29]\n" +
        "                           /    \\\n" +
        "                        [23]    [24]\n" +
        "                          \\    /\n" +
        "                            [25]\n" +
        "                              |\n" +
        "                            [26]\n" +
        "                              |\n" +
        "                            [27]\n";

  
        if (!idAtual.isEmpty()) {
            mapa = mapa.replace("[" + idAtual + "]", Cores.VERDE + "[📍]" + Cores.RESET);
        }

        mapa = mapa.replace("[01]", "[🏠]").replace("[02]", "[⚔️ ]").replace("[03]", "[👹]")
                      .replace("[04]", "[🔀]").replace("[05]", "[⚔️ ]").replace("[06]", "[⚔️ ]")
                    .replace("[07]", "[❓]").replace("[08]", "[⚔️ ]").replace("[09]", "[❓]")
                    .replace("[10]", "[❓]") .replace("[11]", "[⚔️ ]").replace("[12]", "[🍺]")
                   .replace("[13]", "[⚔️ ]").replace("[14]", "[⚔️ ]") .replace("[15]", "[❓]")
                    .replace("[16]", "[👹]") .replace("[17]", "[💬]") .replace("[18]", "[⚔️ ]")
                    .replace("[19]", "[⚔️ ]").replace("[20]", "[👹]")  .replace("[21]", "[❓]")
                    .replace("[22]", "[👹]") .replace("[23]", "[⚔️ ]").replace("[24]", "[❓]")
                   .replace("[25]", "[👹]") .replace("[26]", "[❓]").replace("[27]", "[👹]")
                   .replace("[28]", "[💰]").replace("[29]", "[💰]");

        System.out.println(Cores.NEGRITO + "LEGENDA DO MAPA:" + Cores.RESET);
        System.out.println("📍 Você está aqui  |  ⚔️ Batalha   |  👹 Boss");
        System.out.println("❓ Surpresa        |  🍺 Bar ");
        System.out.println("🔀 Caminho         |  💬 Acordo\n");

        System.out.println(mapa);
    }



    public static void imprimirLetraPorLetra(String texto) {
    
        String[] linhas = texto.split("\n");
        for (String linha : linhas) {
            for (char letra : linha.toCharArray()) {
                System.out.print(letra);
                
                try {
                    Thread.sleep(20); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(); 
        }
    }



}

