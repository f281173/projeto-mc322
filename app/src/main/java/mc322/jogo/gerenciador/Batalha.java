package mc322.jogo.gerenciador;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.jogo.Dados;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;




/**
 * Classe responsável por instanciar e gerenciar um combate isolado em um Nó do Mapa.
 */
public class Batalha {

    // Recebe as classes encapsuladas: Jogador e Oponente
    public boolean executarCombate(Jogador jogador, Oponente oponente, GameManager gm, Scanner sc, Prints tela) {
        
        System.out.println(TurnoHeroi.AMARELO + TurnoHeroi.NEGRITO + "\n=== A BATALHA COMEÇOU! ===" + TurnoHeroi.RESET);

        Baralho deckGeral = Dados.carregarBaralhoGeral(); 

        // 1. Prepara os Inimigos encapsulados no Oponente
        for (Inimigo ini : oponente.getInimigosEscolhidos()) {
            ini.embaralhaBaralho();
        }

        // Junta os escolhidos dos heróis e vilões para decidir a ordem do turno
        ArrayList<Entidade> ordemTurno = new ArrayList<>();
        ordemTurno.addAll(jogador.getHeroisEscolhidos());
        ordemTurno.addAll(oponente.getInimigosEscolhidos());

        // Olha a velocidade
        for (int i = 0; i < ordemTurno.size(); i++) {
            for (int j = 0; j < ordemTurno.size() - 1 - i; j++) {

                Entidade atual = ordemTurno.get(j);
                Entidade proximo = ordemTurno.get(j + 1);

                if (atual.getVelocidade() < proximo.getVelocidade()) {
                    ordemTurno.set(j, proximo);
                    ordemTurno.set(j + 1, atual);
                }
            }
        }

        TurnoHeroi turnoHeroi = new TurnoHeroi(gm);
        TurnoVilao turnoVilao = new TurnoVilao(gm);

        // --------------------BATALHA--------------------------------------------------

        while (jogador.temHeroisVivos() && oponente.temInimigosVivos()) {

            // Olha quem já jogou
            //Prints.limparTela(); //---------------olhar se deu errado
            for (Entidade entidades : ordemTurno) {
                entidades.verificaseAtacou(false);
            }

            for (Entidade entidadeAtual : ordemTurno) {

                // Só joga se estiver vivo e não tiver atacado nesta rodada
                if (entidadeAtual.estaVivo() && !entidadeAtual.getTurno()) {

                    if (entidadeAtual instanceof Heroi) {
                        Heroi heroiAtual = (Heroi) entidadeAtual;
                        System.out.println(
                                Prints.CIANO + "\n>>> Turno de " + heroiAtual.getNome() + " <<<" + Prints.RESET);

                        // O herói recebe o deckGeral e a lista de inimigos disponíveis
                        turnoHeroi.jogar(heroiAtual, jogador.getHeroisEscolhidos(), oponente.getInimigosEscolhidos(),
                                tela, deckGeral, sc);
                    } else if (entidadeAtual instanceof Inimigo) {
                        Inimigo inimigoAtual = (Inimigo) entidadeAtual;
                        // O inimigo ataca um herói aleatório da lista
                        turnoVilao.jogar(inimigoAtual, jogador.getHeroisEscolhidos());
                    }

                    // Marca que já jogou neste turno
                    entidadeAtual.verificaseAtacou(true);
                }

                // Vê se algum grupo já foi de base
                if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos()) {
                    break;
                }
            }
        }

   


        
        if (jogador.temHeroisVivos()) {
            System.out.println(TurnoHeroi.VERDE + "\n🎉 VITÓRIA! A área foi limpa." + TurnoHeroi.RESET);
            
            for (Heroi h : jogador.getHeroisEscolhidos()) {
                h.resetarEscudo();
                h.terminaEfeito(TiposEfeitos.VENENO);
                h.terminaEfeito(TiposEfeitos.FRAQUEZA);
                h.terminaEfeito(TiposEfeitos.FORCA);
                h.setHasEfeitoFraqueza(false);
            }
            return true; 
        } else {
            System.out.println(TurnoHeroi.VERMELHO + "\n☠️ DERROTA... Sua jornada termina aqui." + TurnoHeroi.RESET);
            return false; 
        }
    }
}


