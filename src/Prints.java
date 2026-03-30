
import java.util.ArrayList;

public class Prints {
    
    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";


    public void  comeco(){
        System.out.println(NEGRITO  + "===========================================================" + RESET);
        System.out.println(NEGRITO + CIANO + "           BEM-VINDO AO JOGO DE RPG DO SHREK !!            " + RESET);
        System.out.println(NEGRITO + "===========================================================" + RESET);
        
    }



    public void status_batalha(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos){
        System.out.println("\n" + NEGRITO + "=================== STATUS DA BATALHA ===================" + RESET);
      
        imprimeHerois(herois, heroiAtual);
        System.out.println(NEGRITO + "                           vs" + RESET);
        imprimeInimigosVivos(inimigos);

        System.out.println(NEGRITO + "=========================================================" + RESET);
    }


    public void energia( int energia){
        System.out.println(AMARELO + "⚡ Energia: " + RESET  + energia + " disponíveis");
    }
    
    
    public void imprimeInimigosVivos(ArrayList<Inimigo> inimigos) {
        for (Inimigo enemy : inimigos) {
            if (enemy.estaVivo()) {
                System.out.println(VERMELHO + NEGRITO + enemy.acessoNome() + RESET + " " 
                    + VERDE + "[VIVO] " + enemy.acesso_vida() + "/" + enemy.getVidaInicial() + RESET + " de vida" + "  | "
                    + AZUL + "🛡️  " + enemy.acessoEscudo()  + RESET + " de escudo");
            } else {
                System.out.println(VERMELHO + NEGRITO + enemy.acessoNome() + RESET + " " 
                    + VERMELHO + "[MORTO] 💀" + RESET);
            }
        }
    }


    public void imprimeHerois(ArrayList<Heroi> herois, Heroi heroiAtual) {
        for (Heroi h : herois) {
            
            String destaque = (h == heroiAtual) ? AMARELO + " ⬅️ [SEU TURNO]" + RESET : "";
            
            if (h.estaVivo()) {
                System.out.println(CIANO + NEGRITO + h.acessoNome() + destaque + RESET + " " 
                    + VERDE + "[VIVO] " + h.acesso_vida() + "/" + h.getVidaInicial() + RESET + " de vida  | "
                    + AZUL + "🛡️  " + h.acessoEscudo() +  RESET + " de escudo");
            } else {
            
                System.out.println(CIANO + NEGRITO + h.acessoNome() + RESET + " " 
                    + VERMELHO + "[MORTO] 💀" + RESET);
            }
        }
    }

    public void faseCompra(int limiteCompra, int cartasCompradas){
        System.out.println(NEGRITO + "\n--- FASE DE COMPRA ---" + RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }


    public void faseBatalha(){
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }


    public void fimDeJogo(Jogador player, Musica dj){
        if (!player.temHeroisVivos()) {
            dj.tocarMusica("../sons/I_need_sleep.wav");
            System.out.println(Prints.NEGRITO + Prints.VERMELHO + "\n💀 BRUTAL!!! SUA EQUIPE FOI DERROTADA!" + Prints.RESET);
        } else {
            dj.tocarMusica("../sons/im_believer.wav");
            System.out.println(Prints.NEGRITO + Prints.VERDE + "\n🎉  PARABÉNS!!!   SUA EQUIPE VENCEU!" + Prints.RESET);
        }
      }
    

      
      public void dificuldade(){
        System.out.println("\n" + Prints.NEGRITO + "=================== CONFIGURAÇÃO DA PARTIDA ===================" + Prints.RESET);
        System.out.println("Escolha a dificuldade da batalha:");
        System.out.println(Prints.VERDE + "1 - Fácil (1 Inimigo)" + Prints.RESET);
        System.out.println(Prints.AMARELO + "2 - Médio (2 Inimigos)" + Prints.RESET);
        System.out.println(Prints.VERMELHO + "3 - Difícil (3 Inimigos)" + Prints.RESET);
        System.out.print(Prints.NEGRITO + "\nOpção: " + Prints.RESET);
      }



      public void jogarNovamente(){
        System.out.println("\n"  + Prints.NEGRITO + "=======================================" + Prints.RESET);
            System.out.println(  Prints.NEGRITO + "   Deseja jogar novamente? (1 - Sim / 2 - Não): " + Prints.RESET);
            System.out.println( Prints.NEGRITO + "=======================================" + Prints.RESET);
      }
      
}
