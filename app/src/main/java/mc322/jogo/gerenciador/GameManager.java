package mc322.jogo.gerenciador;

import mc322.jogo.Cores;
import mc322.jogo.Dados;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.mapa.Campanha;
import mc322.jogo.mapa.Evento;
import mc322.jogo.mapa.NoMapa;
import mc322.jogo.observer.Estados;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.cartas.CartaDanoArea;
import mc322.jogo.observer.Publisher;
import mc322.jogo.observer.Subscriber;
import mc322.jogo.Cores;
import java.util.ArrayList;
import java.util.Scanner;
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
     * possíveis no jogo e os valores  a lista
     * de subscribers
     */
    private HashMap<Estados, ArrayList<Subscriber>> efeitosAtivos;
    Musica dj = new Musica();

    public GameManager() {
        this.inicializaEfeitosAtivos();
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
     * @param sc Scanner de leitura de dados do teclado
     * @param tela para impressão no terminal.
     */
    public void prepararPartida(Scanner sc, Prints tela) {

        tela.comeco();
        dj.tocarMusica("../sons/Funkytown.wav");

        tela.dificuldade();
        int dificuldade = sc.nextInt();
        sc.nextLine();

        Jogador jogador = new Jogador();
        Heroi shrek = Dados.criarShrek(this);
        jogador.adicionarHeroiTodos(shrek);
        jogador.getHeroisEscolhidos().add(shrek);

        // criar um mapa aqui eu acho
        NoMapa inicioDoMapa = Campanha.criarMapa(this, dificuldade);
        System.out.println("\nA JORNADA VAI COMEÇAR!\n\n");
        viajarPeloGrafo(inicioDoMapa, jogador, sc, tela, shrek, dificuldade);

    }



   public void viajarPeloGrafo(NoMapa nodoAtual, Jogador jogador, Scanner sc, Prints tela, Heroi Shrek, int dificuldade) {
        Prints.limparTela();
        Evento evento = nodoAtual.getEvento();
        
        System.out.println(Cores.AMARELO + "📍 VOCÊ CHEGOU EM: " + evento.getNomeFase() + Cores.RESET);

        
        boolean sobreviveu = evento.iniciar(jogador, this, sc, tela, dificuldade);

        if (!sobreviveu) return; 

       
        ArrayList<NoMapa> proximos = nodoAtual.getProximos();
        if (proximos.isEmpty() || nodoAtual.FimDeJogo()) {
            System.out.println(Cores.VERDE + Cores.NEGRITO + "🏆 FIM DE JOGO! VOCÊ ZEROU A CAMPANHA!" + Cores.RESET);
            return;
        }

        Prints.PrintaMapa(evento.getNomeFase());
        System.out.println("\nPara onde você quer ir agora?");
        for (int i = 0; i < proximos.size(); i++) {
            System.out.println(i + " - " + proximos.get(i).getEvento().getNomeFase());
        }

        int escolha;
        while (true) {
            System.out.print(Cores.NEGRITO + "Sua escolha: " + Cores.RESET);
            escolha = sc.nextInt();
            
            if (escolha >= 0 && escolha < proximos.size()) {
                viajarPeloGrafo(proximos.get(escolha), jogador, sc, tela, Shrek, dificuldade);
                break; 
            } else {
                System.out.println(Cores.VERMELHO + "Opção inválida! Digite um número do menu." + Cores.RESET);
            }
        }
    }


    
}


