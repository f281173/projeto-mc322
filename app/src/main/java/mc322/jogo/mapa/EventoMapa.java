package mc322.jogo.mapa;

import mc322.jogo.cartas.Carta;
import mc322.jogo.gerenciador.Oponente;

public class EventoMapa {
    private String nomeFase;
    private String dialogo;
    private TipoEvento tipo;
    private Oponente oponenteDaFase; 
    private Carta cartaRecompensa;

    public EventoMapa(String nomeFase, String dialogo, TipoEvento tipo, Oponente oponenteDaFase) {
        this.nomeFase = nomeFase;
        this.dialogo = dialogo;
        this.tipo = tipo;
        this.oponenteDaFase = oponenteDaFase;
        this.cartaRecompensa = null;

    }

    public String getNomeFase(){ 
        return nomeFase; 
    }

    public String getDialogo(){ 
        return dialogo; 
    }

    public TipoEvento getTipo(){ 
        return tipo; 
    }

    
    public Oponente getOponente(){ 
        return oponenteDaFase; 
    } 
    
    
    public void setCartaRecompensa(Carta carta){ 
        this.cartaRecompensa = carta; 
    }


    public Carta getCartaRecompensa(){ 
        return this.cartaRecompensa;
    }
}