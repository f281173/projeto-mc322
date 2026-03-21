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
        System.out.println("Use suas cartas para destruir o " + VERMELHO + NEGRITO + "Dragão!" + RESET + "\n");
    }



    public void status_batalha(Entidade player, Entidade enemy){
        System.out.println("\n" + NEGRITO + "=================== STATUS DA BATALHA ===================" + RESET);
      
        System.out.println(CIANO + NEGRITO + player.acessoNome() + RESET + " " 
            + VERDE + "[VIVO] " + player.acesso_vida() + "/" + player.acesso_vidainicial() + RESET + " de vida" + "  | "
            + AZUL + "🛡️  " + player.acessoEscudo() +  RESET + " de escudo");

        System.out.println(NEGRITO + "                           vs" + RESET);

        System.out.println(VERMELHO + NEGRITO + enemy.acessoNome() + RESET + " " 
            + VERDE + "[VIVO] " + enemy.acesso_vida() + "/" + enemy.acesso_vidainicial() + RESET + " de vida" + "  | "
            + AZUL + "🛡️  " + enemy.acessoEscudo()  + RESET + " de escudo");
        System.out.println(NEGRITO + "=========================================================" + RESET);
    }


    public void energia( int energia){
        System.out.println(AMARELO + "⚡ Energia: " + RESET  + energia + " disponíveis");
    }
    
    



    public void fase_compra(int limiteCompra, int cartasCompradas){
        System.out.println(NEGRITO + "\n--- FASE DE COMPRA ---" + RESET);
        System.out.println("Você pode comprar mais " + (limiteCompra - cartasCompradas) + " carta(s).");
        System.out.println("1 - Comprar carta");
        System.out.println("2 - Terminar de comprar (Ir para a Batalha)\n");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }


    public void fase_batalha(){
        System.out.println("1 - Ver mão");
        System.out.println("2 - Usar Cartas");
        System.out.println("3 - Encerrar Turno");
        System.out.print(NEGRITO + "Escolha: " + RESET);
    }


    public void fim_de_jogo(Heroi player){
        System.out.println("\n" + NEGRITO + "==================== FIM DE JOGO ====================" + RESET);
        if (player.estaVivo()) {
          System.out.println(NEGRITO + VERDE + "🎉 Parabéns, você ganhou !! \uD83D\uDE0A" + RESET);
        } else {
          System.out.println(NEGRITO + VERMELHO + "💀 Você foi derrotado ! \uD83D\uDE35" + RESET);
        }
        System.out.println(NEGRITO + "=====================================================" + RESET);
      }
    


}
