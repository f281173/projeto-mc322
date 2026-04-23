package mc322.jogo.interfaceUsuario;

import java.util.ArrayList;

import mc322.jogo.cartas.Carta;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.mapa.NoMapa;

/**
 * Interface responsável pelo padrão Strategy. A ideia é ter um conjunto de métodos
 * para a interação com o usuário, sendo que queremos poder fazer isso por meio de estratégias
 * diferentes.
 * 
 * - Usar o terminal como forma de interação
 * - Usar a interface gráfica como forma de interação.
 */
public interface InterfaceUsuario {

    /*----------MÉTODOS DO GAMEMANAGER--------------*/
    
    /**
     * Método para o início do jogo
     */
    public void telaInicial();

    /** Contrato para que o usuário escolha a sua dificuldade */
    public int escolheDificuldade();

    public void mostrarDialogoEvento(String nomeFase, String dialogo);

    /** Desenha o mapa para o jogador se localizar */
    public void mostrarMapa(String nomeFaseAtual);

    /** Pede ao jogador para escolher o próximo nó do grafo */
    public int escolherCaminhoMapa(ArrayList<NoMapa> caminhos);

    public void fimDeJogo();

    /** Avisa o usuário que a equipe descansou no bar */
    public void mostrarEventoBar();

    /** Avisa o usuário que a equipe caiu em uma armadilha */
    public void mostrarEventoArmadilha();

    /** Mostra a nova carta que o jogador encontrou */
    public void mostrarRecompensaCarta(Carta carta);

    /** Anuncia que um novo aliado entrou para o time */
    public void mostrarNovoCompanheiro(String nomeCompanheiro);

    /*----------------MÉTODOS TURNO VILAO------------------------- */

    /** Anuncia que o turno do inimigo começou */
    public void mostrarInicioTurnoVilao(String nomeVilao);

    /** Mostra a ação que o vilão executou (ex: causou dano, ganhou escudo) */
    public void mostrarAcaoVilao(String descricaoAcao);

    /** Mostra os efeitos atuais do vilão (se houver) */
    public void mostrarEfeitosVilao(String efeitos);

    /** Anuncia que o turno do inimigo acabou */
    public void mostrarFimTurnoVilao();

    /*----------------MÉTODOS TURNO HEROI------------------------- */

/** Mostra a vida, energia e efeitos de todos na tela */
    public void mostrarStatusTurno(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos);

    /** Pergunta se ele quer comprar carta ou ir para a batalha */
    public int escolherAcaoCompra(int limiteCompra, int cartasCompradas);

    /** Mostra a vitrine de cartas e pede para escolher uma (ou cancelar) */
    public int escolherCartaParaComprar(ArrayList<Carta> pilhaCompra);

    /** Menu principal da batalha: Ver Mão, Usar Carta ou Encerrar */
    public int escolherAcaoBatalha();

    /** Mostra as cartas que estão na mão do herói */
    public void mostrarCartasMao(ArrayList<Carta> mao);

    /** Pede para o jogador escolher o índice de uma carta da mão */
    public int escolherCartaParaUsar(ArrayList<Carta> mao);

    /** Pede para o jogador escolher qual inimigo vai atacar */
    public int escolherAlvoInimigo(ArrayList<Inimigo> inimigos);

    /** Pede para o jogador escolher qual herói aliado vai receber o buff/cura */
    public int escolherAlvoHeroi(ArrayList<Heroi> herois);

    public void mostrarMensagem(String mensagem);



}
