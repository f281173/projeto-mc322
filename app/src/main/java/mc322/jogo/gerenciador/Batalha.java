package mc322.jogo.gerenciador;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.jogo.Cores;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.mapa.Evento;



/**
 * Classe responsável por instanciar e gerenciar um combate isolado em um Nó do Mapa.
 */
public class Batalha extends Evento {
    private Oponente oponente;
    private boolean isBoss;

    public Batalha(String nomeFase, String dialogo, Oponente oponente, boolean isBoss) {
        super(nomeFase, dialogo);
        this.oponente = oponente;
        this.isBoss = isBoss;
    }

    public void darRecompensaExtra(Jogador jogador, GameManager gm) { } //---------------------------------------------------
    

  @Override
    public boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade) {
        Prints.imprimirLetraPorLetra(getDialogo());
        System.out.println(Cores.AMARELO + Cores.NEGRITO + "\n=== A BATALHA COMEÇOU! ===" + Cores.RESET);

        ArrayList<Entidade> ordemTurno = new ArrayList<>();
        ordemTurno.addAll(jogador.getHeroisEscolhidos());
        ordemTurno.addAll(oponente.getInimigosEscolhidos());

        // Ordena por velocidade
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

        while (jogador.temHeroisVivos() && oponente.temInimigosVivos()) {
            for (Entidade entidades : ordemTurno) entidades.verificaseAtacou(false);

            for (Entidade entidadeAtual : ordemTurno) {
                if (entidadeAtual.estaVivo() && !entidadeAtual.getTurno()) {
                    if (entidadeAtual instanceof Heroi) {
                        Heroi heroiAtual = (Heroi) entidadeAtual;
                        System.out.println(Cores.CIANO + "\n>>> Turno de " + heroiAtual.getNome() + " <<<" + Cores.RESET);
                        turnoHeroi.jogar(heroiAtual, jogador, oponente, tela, sc);
                        
                        if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos()) break;

                    } else if (entidadeAtual instanceof Inimigo) {
                        Inimigo inimigoAtual = (Inimigo) entidadeAtual;
                        turnoVilao.jogar(inimigoAtual, jogador.getHeroisEscolhidos());
                        if (!jogador.temHeroisVivos() || !oponente.temInimigosVivos()) break;
                    }
                    entidadeAtual.verificaseAtacou(true);
                }
            }
        }

        if (jogador.temHeroisVivos()) {
            System.out.println(Cores.VERDE + "\n🎉 VITÓRIA! A área foi limpa." + Cores.RESET);
            
            
            int moedasGanhas = isBoss ? 20 : (10 * dificuldade);
            jogador.adicionarMoedas(moedasGanhas);
            System.out.println(Cores.AMARELO + "💰 Você ganhou " + moedasGanhas + " moedas!" + Cores.RESET);
            System.out.println("Saldo atual: 💰 " + jogador.getMoedas() + "\n");
            
            darRecompensaExtra(jogador, gm); 
            return true; 
        } else {
            System.out.println(Cores.VERMELHO + "\n☠️ DERROTA... Sua jornada termina aqui." + Cores.RESET);
            return false; 
        }
    }
}




