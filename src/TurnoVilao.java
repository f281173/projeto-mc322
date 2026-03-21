public class TurnoVilao {
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public void jogar(Heroi player, Inimigo enemy){
        
         // o inimigo só vai atacar se estiver vivo (caso a gente mate ele antes de passar de turno)
          
        System.out.println("\n" + VERMELHO + NEGRITO + "================== TURNO DO INIMIGO ==================" + RESET);
        System.out.println(VERMELHO + "O " + enemy.acessoNome() + " está atacando..." + RESET);
        enemy.atacar(player);
        System.out.println(enemy.acessoNome() + " executou o ataque e causou " + VERMELHO + enemy.acessoDano() + RESET + " de dano!");
        System.out.println(VERMELHO + NEGRITO + "======================================================\n" + RESET);
    

     
    }
}


