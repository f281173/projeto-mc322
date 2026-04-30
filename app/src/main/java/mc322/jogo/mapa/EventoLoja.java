package mc322.jogo.mapa;

import mc322.jogo.Dados;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.interfaceUsuario.InterfaceUsuario;

public class EventoLoja extends Evento {
    private int numeroLoja;

    public EventoLoja(String nomeFase, String dialogo, int numeroLoja) {
        super(nomeFase, dialogo);
        this.numeroLoja = numeroLoja;
    }

    @Override
    public boolean iniciar(Jogador jogador, GameManager gm, InterfaceUsuario ui, int dificuldade) {
        ui.telaInicialLoja(this.getDialogo(), numeroLoja);

        while (true) {
            ui.telaOpcaoLoja();

            if (numeroLoja == 2)
                ui.mostrarMensagem("4 - [EASTER EGG] Recrutar o Lobinho! - 💰 100");

            int escolha = ui.escolhaOpcaoLoja();

            if (escolha == 1 && jogador.getMoedas() >= 20) {
                jogador.removerMoedas(20);
                jogador.getHeroisEscolhidos().get(0).curar(30);
                ui.mostrarMensagem("❤️ HP recuperado!");

            } else if (escolha == 2 && jogador.getMoedas() >= 50) {
                jogador.removerMoedas(50);
                jogador.getHeroisEscolhidos().get(0).aumentarVidaInicial(20);
                ui.mostrarMensagem("💪 Vida máxima aumentada!");

            } else if (escolha == 3 && jogador.getMoedas() >= 70) {
                jogador.removerMoedas(70);
                jogador.getHeroisEscolhidos().get(0).aumentarEnergia(1);
                ui.mostrarMensagem("⚡ Energia máxima aumentada!");

            } else if (escolha == 4 && numeroLoja == 2 && jogador.getMoedas() >= 100) {
                jogador.removerMoedas(100);
                jogador.getHeroisEscolhidos().add(Dados.criarLobinho(gm));
                ui.mostrarMensagem("VOCÊ É UM MONSTRO! - O Lobinho entrou no time!");

            } else if (escolha == 0) {
                ui.mostrarSaidaLoja();
                break;
            } else {
                ui.mostrarSaldoInsuficiente();
            }
            ui.mostrarSaldo(jogador.getMoedas());
        }
        return true;
    }
}