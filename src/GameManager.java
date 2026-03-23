import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe  do controle do jogo
 * Responsável por controlar a preparação da partida, carregar os dados,
 * gerenciar os turnos e manter o loop principal da batalha funcionando 
 * até que um dos lados seja completamente derrotado.
 */

public class GameManager {


//Prepara a partida, escolhendo a dificuldade e equipe
    public void prepararPartida(Scanner sc, Prints tela) {

        tela.comeco();
        Jogador jogador = new Jogador();
        Oponente oponente = new Oponente();

        for (Heroi h : Dados.carregarHerois()) {
            jogador.adicionarHeroiTodos(h);
        }

        for (Inimigo i : Dados.carregarInimigos()) {
            oponente.adicionarInimigoTodos(i);
        }

        //---------------------------Dificuldade-----------------
        tela.dificuldade();
        int dificuldade = sc.nextInt();
        oponente.gerarInimigos(dificuldade);

        //------------------------------Equipe--------------------------
        System.out.println("\n\n\n" + Prints.NEGRITO + "====================== SELEÇÃO DE EQUIPE ======================" + Prints.RESET);
        jogador.escolherHerois(sc);

        Baralho deckGeral = Dados.carregarBaralhoGeral();
        for (Inimigo inimigoSorteado : oponente.getInimigosEscolhidos()) {
            inimigoSorteado.embaralhaBaralho();
        }

        System.out.println("\n" + Prints.AMARELO + Prints.NEGRITO + "Preparando...  A BATALHA VAI COMEÇAR!" + Prints.RESET + "\n\n");
        //colocar musica?
        iniciarBatalha(jogador, oponente, sc, tela, deckGeral);
    }




//Partida em si, gerencia os turnos
    private void iniciarBatalha(Jogador jogador, Oponente oponente, Scanner sc, Prints tela, Baralho deckGeral) {

        //Junta os escolhidos dos heróis e vilões para decidir a ordem do turno
        ArrayList<Entidade> ordemTurno = new ArrayList<>(); 
        ordemTurno.addAll(jogador.getHeroisEscolhidos());
        ordemTurno.addAll(oponente.getInimigosEscolhidos());

        //Olha a velocidade
        for (int i = 0; i < ordemTurno.size(); i++) {
            for (int j = 0; j < ordemTurno.size() - 1 - i; j++) {
                
                Entidade atual = ordemTurno.get(j);
                Entidade proximo = ordemTurno.get(j + 1);

                if (atual.acessoVelocidade() < proximo.acessoVelocidade()) {
                    ordemTurno.set(j, proximo);
                    ordemTurno.set(j + 1, atual);
                }
            }
        }

        TurnoHeroi turnoHeroi = new TurnoHeroi();
        TurnoVilao turnoVilao = new TurnoVilao();


//--------------------BATALHA--------------------------------------------------

        while (jogador.temHeroisVivos() && oponente.temInimigosVivos()) {
            
            //Olha quem já jogou
            for (Entidade entidades : ordemTurno) { 
                entidades.verificaseAtacou(false); 
            }

            for (Entidade entidadeAtual : ordemTurno) {
                
                // Só joga se estiver vivo e não tiver atacado nesta rodada
                if (entidadeAtual.estaVivo() && !entidadeAtual.acessoturno()) {
                    
                    if (entidadeAtual instanceof Heroi) {
                        Heroi heroiAtual = (Heroi) entidadeAtual;
                        System.out.println(Prints.CIANO + "\n>>> Turno de " + heroiAtual.acessoNome() + " <<<" + Prints.RESET);
                        
                        // O herói recebe o deckGeral e a lista de inimigos disponíveis
                        turnoHeroi.jogar(heroiAtual, jogador.getHeroisEscolhidos(),  oponente.getInimigosEscolhidos(), tela, deckGeral, sc);
                    } 
                    else if (entidadeAtual instanceof Inimigo) {
                        Inimigo inimigoAtual = (Inimigo) entidadeAtual;
                        // O inimigo ataca um herói aleatório da lista
                        turnoVilao.jogar(inimigoAtual, jogador.getHeroisEscolhidos());
                    }
                    
                    // Marca que já jogou neste turno
                    entidadeAtual.verificaseAtacou(true);
                }

                //Vê se algum grupo já foi de base
                if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos()) { 
                    break; 
                }
            }
        }
        
 

        tela.fim_de_jogo(jogador);
        
    }
}
