package mc322.jogo.interfaceUsuario;

import java.util.ArrayList;

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
    
    /**
     * Método para o início do jogo
     */
    public void telaInicial();

    /**
     * Contrato para que o usuário escolha a sua dificuldade.
     * @return
     */
    public int escolheDificuldade();

    public void mostrarDialogoEvento(String nomeFase, String dialogo);

    /** Desenha o mapa para o jogador se localizar */
    public void mostrarMapa(String nomeFaseAtual);

    /** Pede ao jogador para escolher o próximo nó do grafo */
    public int escolherCaminhoMapa(ArrayList<NoMapa> caminhos);

}
