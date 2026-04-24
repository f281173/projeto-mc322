package mc322.jogo.gerenciador;

import mc322.jogo.Dados;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;
import mc322.jogo.mapa.Campanha;
import mc322.jogo.mapa.EventoMapa;
import mc322.jogo.mapa.NoMapa;
import mc322.jogo.mapa.TipoEvento;
import mc322.jogo.observer.Estados;
import mc322.jogo.cartas.Carta;
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
        NoMapa inicioDoMapa = Campanha.criarMapa(this, dificuldade);
        viajarPeloGrafo(inicioDoMapa, jogador, shrek);

    }

    public void viajarPeloGrafo(NoMapa nodoAtual, Jogador jogador, Heroi Shrek) {

        EventoMapa evento = nodoAtual.getEvento();
        this.ui.mostrarDialogoEvento(evento.getNomeFase(), evento.getDialogo());


        if (evento.getTipo() == TipoEvento.BATALHA || evento.getTipo() == TipoEvento.BOSS) {
            Batalha arena = new Batalha();
            boolean sobreviveu = arena.executarCombate(jogador, evento.getOponente(), this, ui);
            if (!sobreviveu)
                return;
        }

        else if (evento.getTipo() == TipoEvento.DESCANSO_BAR) {
            this.ui.mostrarEventoBar();

            for (Heroi heroi : jogador.getHeroisEscolhidos()) {
                if (heroi.estaVivo()) {
                    heroi.curar(30);
                }
            }

        }

        else if (evento.getTipo() == TipoEvento.ARMADILHA) {
            this.ui.mostrarEventoArmadilha();
            for (Heroi heroi : jogador.getHeroisEscolhidos()) {
                if (heroi.estaVivo()) {
                    heroi.recebeDanoEfeito(15);
                }
            }
        }

        else if (evento.getTipo() == TipoEvento.RECOMPENSA_CARTA) {
            Carta novaCarta = evento.getCartaRecompensa();
            if (novaCarta != null) {
                this.ui.mostrarRecompensaCarta(novaCarta);
                Shrek.ganhaCarta(novaCarta);

            }
        }

        // companheiros
        if (evento.getNomeFase().equals("Flor azul com espinhos vermelhos")) {
            this.ui.mostrarNovoCompanheiro("Burro");
            Heroi burro = Dados.criarBurro(this);
            jogador.adicionarHeroiTodos(burro);
            jogador.getHeroisEscolhidos().add(burro);
        }

        else if (evento.getNomeFase().equals("Flor vermelha com espinhos azuis")) {
            this.ui.mostrarNovoCompanheiro("Pinoquio");
            Heroi pinoquio = Dados.criarPinoquio(this);
            jogador.adicionarHeroiTodos(pinoquio);
            jogador.getHeroisEscolhidos().add(pinoquio);
        }

        else if (evento.getNomeFase().equals("Torre da Bruxa Velha")) {
            this.ui.mostrarNovoCompanheiro("Fiona");
            Heroi fiona = Dados.criarFiona(this);
            jogador.adicionarHeroiTodos(fiona);
            jogador.getHeroisEscolhidos().add(fiona);
        }

        // navegação
        ArrayList<NoMapa> proximos = nodoAtual.getProximos();

        if (proximos.isEmpty() || nodoAtual.FimDeJogo()) {
            this.ui.fimDeJogo();
            return;
        }

        this.ui.mostrarMapa(evento.getNomeFase());
        int escolha = this.ui.escolherCaminhoMapa(proximos);
        viajarPeloGrafo(proximos.get(escolha), jogador, Shrek); // deve continuar aqui
    }

}
