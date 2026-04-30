package mc322.jogo.mapa;

import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.Dados;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;

public class EventoLoja extends Evento {
    private int numeroLoja;

    public EventoLoja(String nomeFase, String dialogo, int numeroLoja) {
        super(nomeFase, dialogo);
        this.numeroLoja = numeroLoja;
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        System.out.println("\n" + Cores.AMARELO + "=== 💰 BEM-VINDO À LOJA DO PÂNTANO (Loja " + numeroLoja + ") ===" + Cores.RESET);
        
        while (true) {
            System.out.println("\nO que deseja comprar?");
            System.out.println("1 - Poção de Cura (Recupera 30 HP) - 💰 20");
            System.out.println("2 - Treinamento de Resiliência (+20 HP Máx) - 💰 50");
            System.out.println("3 - Elixir de Café (+1 Energia Máx) - 💰 70");
            
            if (numeroLoja == 2) System.out.println("4 - [EASTER EGG] Recrutar o Lobinho! - 💰 100");
            
            System.out.println("0 - Sair da Loja");
            System.out.print("Escolha: ");
            
            int escolha = sc.nextInt(); sc.nextLine(); 
            
            if (escolha == 1 && jogador.getMoedas() >= 20) {
                jogador.removerMoedas(20);
                jogador.getHeroisEscolhidos().get(0).curar(30);
                System.out.println("❤️ HP recuperado!");
            } 
            else if (escolha == 2 && jogador.getMoedas() >= 50) {
                jogador.removerMoedas(50);
                jogador.getHeroisEscolhidos().get(0).aumentarVidaInicial(20);
                System.out.println("💪 Vida máxima aumentada!");
            } 
            else if (escolha == 3 && jogador.getMoedas() >= 70) {
                jogador.removerMoedas(70);
                jogador.getHeroisEscolhidos().get(0).aumentarEnergia(1);
                System.out.println("⚡ Energia máxima aumentada!");
            } 
            else if (escolha == 4 && numeroLoja == 2 && jogador.getMoedas() >= 100) {
                jogador.removerMoedas(100);
                jogador.getHeroisEscolhidos().add(Dados.criarLobinho(gm));
                System.out.println("VOCÊ É UM MONSTRO! - O Lobinho entrou no time!");
            } 
            else if (escolha == 0) {
                System.out.println("Saindo da loja...");
                break; 
            } else {
                System.out.println("Dinheiro insuficiente ou opção inválida!");
            }
            System.out.println("Saldo atual: 💰 " + jogador.getMoedas());
        }
        return true;
    }
}