package mc322.jogo.gerenciador;

import mc322.jogo.Dados;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;
import mc322.jogo.mapa.Campanha;
import mc322.jogo.mapa.Evento;
import mc322.jogo.mapa.NoMapa;
import mc322.jogo.observer.Estados;
import mc322.jogo.observer.Publisher;
import mc322.jogo.observer.Subscriber;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe do controle do jogo
 * Responsável por controlar a preparação da partida, carregar os dados,
 * gerenciar os turnos e manter o loop principal da batalha funcionando
 * até que um dos lados seja completamente derrotado.
 */

public class GameManager implements Publisher {
    /*
     * Um hashMap com as chaves sendo os estados
     * possíveis no jogo e os valores a lista
     * de subscribers
     */
    private HashMap<Estados, ArrayList<Subscriber>> efeitosAtivos;
    private InterfaceUsuario ui;
    Musica dj = new Musica();

    public GameManager(InterfaceUsuario ui) {
        this.inicializaEfeitosAtivos();
        this.ui = ui;
    }

    public InterfaceUsuario getUi() {
        return this.ui;
    }

    /**
     * Método para inicializar o hashMap com os observadores
     * interessados em cada estado do jogo
     */
    public void inicializaEfeitosAtivos() {
        this.efeitosAtivos = new HashMap<>();

        for (Estados state : Estados.values())
            this.efeitosAtivos.put(state, new ArrayList<Subscriber>());
    }

    /* implementação da interface do Publish */
    @Override
    public void inscrever(Subscriber observador, Estados state) {
        this.efeitosAtivos.get(state).add(observador);
    }

    @Override
    public void desinscrever(Subscriber observador, Estados state) {
        this.efeitosAtivos.get(state).remove(observador);
    }

    @Override
    public void notificar(Entidade observador, Estados state) {
        ArrayList<Subscriber> inscritos = this.efeitosAtivos.get(state);
        ArrayList<Subscriber> copiaInscritos = new ArrayList<>(inscritos);

        for (Subscriber efeito : copiaInscritos) {
            if (efeito.getDono() == observador) {
                efeito.serNotificado(state);
            }
        }
    }

    /**
     * Método para construir a partida escolhendo os herois
     * para formar a equipe de batalha
     * 
     * @param sc   Scanner de leitura de dados do teclado
     * @param tela para impressão no terminal.
     */
    public void prepararPartida() {

        this.ui.telaInicial();
        dj.tocarMusica("../sons/Funkytown.wav");

        int dificuldade = ui.escolheDificuldade();

        Jogador jogador = new Jogador();
        Heroi shrek = Dados.criarShrek(this);
        jogador.adicionarHeroiTodos(shrek);
        jogador.getHeroisEscolhidos().add(shrek);

        // criar um mapa aqui eu acho
        NoMapa inicioDoMapa = Campanha.criarMapa(this, dificuldade, this.ui);

        viajarPeloGrafo(inicioDoMapa, jogador, shrek, dificuldade);

    }

    public void viajarPeloGrafo(NoMapa nodoAtual, Jogador jogador, Heroi Shrek, int dificuldade) {

        Evento evento = nodoAtual.getEvento();
        this.ui.mostrarDialogoEvento(evento.getNomeFase(), evento.getDialogo());

        boolean sobreviveu = evento.iniciar(jogador, this, this.ui, dificuldade);

        if (!sobreviveu)
            return;

        // navegação
        ArrayList<NoMapa> proximos = nodoAtual.getProximos();

        if (proximos.isEmpty() || nodoAtual.FimDeJogo()) {
            this.ui.fimDeJogo();
            return;
        }

        this.ui.mostrarMapa(evento.getNomeFase());
        int escolha = this.ui.escolherCaminhoMapa(proximos);
        viajarPeloGrafo(proximos.get(escolha), jogador, Shrek, dificuldade); // deve continuar aqui

    }

}
