package mc322.jogo.mapa;

import java.util.Scanner;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Prints;

public abstract class Evento {
    protected String nomeFase;
    protected String dialogo;

    public Evento(String nomeFase, String dialogo) {
        this.nomeFase = nomeFase;
        this.dialogo = dialogo;
    }

    public String getNomeFase() {
         return nomeFase;
        }

    public String getDialogo() { 
        return dialogo; 
    }


    public abstract boolean iniciar(Jogador jogador, GameManager gm, Scanner sc, Prints tela, int dificuldade);
}