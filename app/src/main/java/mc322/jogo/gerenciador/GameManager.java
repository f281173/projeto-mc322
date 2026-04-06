package mc322.jogo.gerenciador;

import mc322.jogo.Dados;
import mc322.jogo.Musica;
import mc322.jogo.entidades.Entidade;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.mapa.Campanha;
import mc322.jogo.mapa.EventoMapa;
import mc322.jogo.mapa.NoMapa;
import mc322.jogo.mapa.TipoEvento;
import mc322.jogo.observer.Estados;
import mc322.jogo.cartas.Baralho;
import mc322.jogo.cartas.Carta;
import mc322.jogo.observer.Publisher;
import mc322.jogo.observer.Subscriber;
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
     * Um hashMap com as chaves sendo os dicionários de estados e os valores lista
     * de subscribers
     */
    private HashMap<Estados, ArrayList<Subscriber>> efeitosAtivos;
    Musica dj = new Musica();

    public GameManager() {
        this.inicializaEfeitosAtivos();
    }

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



    public void prepararPartida(Scanner sc, Prints tela) {

        tela.comeco();
        dj.tocarMusica("../sons/Funkytown.wav");
        Jogador jogador = new Jogador();
        Heroi shrek = new Heroi("Shrek", 100, 20, 6, 100, 20, true, this);
        jogador.adicionarHeroiTodos(shrek);
        jogador.getHeroisEscolhidos().add(shrek);

        // criar um mapa aqui eu acho
        NoMapa inicioDoMapa = Campanha.criarMapa(this);
        System.out.println("\nA JORNADA VAI COMEÇAR!\n\n");
        viajarPeloGrafo(inicioDoMapa, jogador, sc, tela);

    }



    public void viajarPeloGrafo(NoMapa nodoAtual, Jogador jogador, Scanner sc, Prints tela) {
        
        
        EventoMapa evento = nodoAtual.getEvento();
        Prints.limparTela();  //talvez tenha q mudar de lugar
        System.out.println(evento.getNomeFase());
        System.out.println(evento.getDialogo());
        

        if (evento.getTipo() == TipoEvento.BATALHA || evento.getTipo() == TipoEvento.BOSS) {
            Batalha arena = new Batalha();
            boolean sobreviveu = arena.executarCombate(jogador, evento.getOponente(), this, sc, tela);
            if (!sobreviveu) return; 
        } 

        else if (evento.getTipo() == TipoEvento.DESCANSO_BAR) {
            System.out.println(Prints.VERDE + "🍺 Você bebeu uma poção de lama no bar! Recuperou 30 de vida." + Prints.RESET);
            
            for (Heroi heroi : jogador.getHeroisEscolhidos()) {
                if (heroi.estaVivo()) {
                    heroi.curar(30); 
                }
            }
            
        } 

        else if (evento.getTipo() == TipoEvento.ARMADILHA) {
            System.out.println(Prints.VERMELHO + "🕳️ Você caiu num buraco com espinhos! Perdeu 15 de vida." + Prints.RESET);
            for (Heroi heroi : jogador.getHeroisEscolhidos()) {
                if (heroi.estaVivo()) {
                    heroi.recebeDanoEfeito(15); 
                }
            }
        }

        else if (evento.getTipo() == TipoEvento.RECOMPENSA_CARTA) {
            Carta novaCarta = evento.getCartaRecompensa();
            if (novaCarta != null) {
                System.out.println(Prints.AZUL + "📜 Você encontrou: " + novaCarta.getNome() + "!" + Prints.RESET);
                // Código para adicionar a carta no baralho 

            }
        } 


        //companheiros
        if (evento.getNomeFase().equals("Flor azul com espinhos vermelhos")) {
            System.out.println(Prints.AZUL + " O Burro se juntou à sua equipe!" + Prints.RESET);
            Heroi burro = new Heroi("Burro", 80, 10, 7, 80, 50, true, this);
            jogador.adicionarHeroiTodos(burro);
            jogador.getHeroisEscolhidos().add(burro); 
        } 

        else if (evento.getNomeFase().equals("Flor vermelha com espinhos azuis")) {
            System.out.println(Prints.AZUL + " O Gato de Botas se juntou à sua equipe!" + Prints.RESET);
            Heroi gato = new Heroi("Gato de Botas", 70, 15, 5, 70, 80, true, this);
            jogador.adicionarHeroiTodos(gato);
            jogador.getHeroisEscolhidos().add(gato);
        }

        else if (evento.getNomeFase().equals("Entrando na masmorra")) {
            System.out.println(Prints.AZUL + " Princesa Fiona se juntou à sua equipe!" + Prints.RESET);
            Heroi fiona = new Heroi("Fiona", 90, 25, 5, 90, 40, true, this);
            jogador.adicionarHeroiTodos(fiona);
            jogador.getHeroisEscolhidos().add(fiona);
        }

 
      

        //navegação
        ArrayList<NoMapa> proximos = nodoAtual.getProximos();
        
        if (proximos.isEmpty() || nodoAtual.FimDeJogo()) {
            System.out.println(Prints.VERDE + Prints.NEGRITO + "🏆 FIM DE JOGO! VOCÊ ZEROU A CAMPANHA!" + Prints.RESET);
            return;
        }

        System.out.println("\nPara onde você quer ir agora?");
        for (int i = 0; i < proximos.size(); i++) {
            System.out.println(i + " - " + proximos.get(i).getEvento().getNomeFase());
        }


        int escolha;
        while (true) {
            System.out.print(Prints.NEGRITO + "Sua escolha: " + Prints.RESET);
            escolha = sc.nextInt();
            
            if (escolha >= 0 && escolha < proximos.size()) {
    
                viajarPeloGrafo(proximos.get(escolha), jogador, sc, tela);
                break; 
            } else {
                System.out.println(Prints.VERMELHO + "Opção inválida! Digite um número do menu." + Prints.RESET);
            }
        }
    } 

}


