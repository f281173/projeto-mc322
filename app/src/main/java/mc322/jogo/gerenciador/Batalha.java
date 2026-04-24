package mc322.jogo.gerenciador;

import java.util.ArrayList;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;




/**
 * Classe responsável por instanciar e gerenciar um combate isolado em um Nó do Mapa.
 */
public class Batalha {

    // Recebe as classes encapsuladas: Jogador e Oponente
    public boolean executarCombate(Jogador jogador, Oponente oponente, GameManager gm, InterfaceUsuario ui) {
        
        ui.mostrarInicioBatalha();

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
            for (Entidade entidades : ordemTurno) {
                entidades.verificaseAtacou(false);
            }

            for (Entidade entidadeAtual : ordemTurno) {

                // Só joga se estiver vivo e não tiver atacado nesta rodada
                if (entidadeAtual.estaVivo() && !entidadeAtual.getTurno()) {

                    if (entidadeAtual instanceof Heroi) {
                        Heroi heroiAtual = (Heroi) entidadeAtual;
                        ui.mostrarNovaRodada(heroiAtual.getNome());

                        // O herói recebe o deckGeral e a lista de inimigos disponíveis
                        turnoHeroi.jogar(heroiAtual, jogador, oponente, ui);

                        /* após um turno do heroi, podemos ter a morte de todos os inimigos */
                        if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos())
                            break;

                    } else if (entidadeAtual instanceof Inimigo) {
                        Inimigo inimigoAtual = (Inimigo) entidadeAtual;
                        // O inimigo ataca um herói aleatório da lista
                        turnoVilao.jogar(inimigoAtual, jogador.getHeroisEscolhidos(), ui);

                        /* verificar se ainda temos herois e inimigos vivos */
                        if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos())
                            break;

                    }

                    // Marca que já jogou neste turno
                    entidadeAtual.verificaseAtacou(true); // aqui que ele marca que foi atacada
                }
            }
        }

        if (jogador.temHeroisVivos()) {
            ui.mostrarVitoriaBatalha();
            
            return true; 
        } else {
            ui.mostrarDerrotaBatalha();
            return false; 
        }
    }
}


