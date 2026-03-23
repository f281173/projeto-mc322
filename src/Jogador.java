import java.util.ArrayList;
import java.util.Scanner;



public class Jogador {
    private ArrayList<Heroi> todosHerois = new ArrayList<>();
    private ArrayList<Heroi> heroisEscolhidos = new ArrayList<>();
    public static final String RESET = "\u001B[0m";
    public static final String NEGRITO = "\u001B[1m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    public void adicionarHeroiTodos(Heroi heroi) {
        this.todosHerois.add(heroi);
    }


   public void escolherHerois(Scanner sc) {
        System.out.println("Heróis Disponíveis:");
        for (int i = 0; i < todosHerois.size(); i++) {
            System.out.println("[" + i + "] " + CIANO + todosHerois.get(i).acessoNome() + RESET + " - Vida: " + todosHerois.get(i).acesso_vidainicial()+ " - Energia: " + todosHerois.get(i).acessoEnergia() + " - Escudo: "  + todosHerois.get(i).acessoEscudo() + "- Velocidade: " + todosHerois.get(i).acessoVelocidade()  );
        }

        System.out.print("\nQuantos heróis farão parte da sua equipe? (Máximo " + todosHerois.size() + "): ");
        int quant = sc.nextInt();
        quant = Math.min(quant, todosHerois.size());

        

        for (int i = 0; i < quant; i++) {
            boolean escolhaValida = false;
            while (!escolhaValida) {

                System.out.print("Digite o número do Herói " + (i + 1) + ": ");
                int escolha = sc.nextInt();

                if (escolha < 0 || escolha >= todosHerois.size()) {
                    System.out.println(VERMELHO + "Opção inválida! Escolha um número do catálogo." + RESET);
                } else {
                    Heroi selecionado = todosHerois.get(escolha);
                    if (this.heroisEscolhidos.contains(selecionado)) {
                        System.out.println(VERMELHO + "Esse herói já está na sua equipe! Escolha outro." + RESET);
                    } else {
                        this.heroisEscolhidos.add(selecionado);
                        System.out.println(VERDE + selecionado.acessoNome() + " entrou para a equipe!" + RESET);
                        escolhaValida = true;

                    }
                }
            }
                
        }
    }


    public ArrayList<Heroi> getHeroisEscolhidos() {
        return this.heroisEscolhidos;
    }

    public boolean temHeroisVivos() {
        for (Heroi h : heroisEscolhidos) {
            if (h.estaVivo()) {
                return true; 
            }
        }
        return false; 
    }



}
