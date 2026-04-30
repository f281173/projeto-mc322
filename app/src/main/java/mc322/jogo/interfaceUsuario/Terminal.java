package mc322.jogo.interfaceUsuario;

import java.util.ArrayList;
import java.util.Scanner;
import mc322.jogo.Cores;
import mc322.jogo.cartas.Carta;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.Prints;
import mc322.jogo.gerenciador.SistemaAcoes.ResultadoAcao;
import mc322.jogo.gerenciador.SistemaAcoes.ResultadoAcao.TipoAcao;
import mc322.jogo.mapa.NoMapa;

public class Terminal implements InterfaceUsuario {
    Scanner sc = new Scanner(System.in);

    public void telaInicial() {
        /* lógica identica ao comeco() em Prints inicio do jogo */
        Prints.comeco();
    }

    public int escolheDificuldade() {
        /* lógica identica ao dificuldade() em Prints */
        Prints.dificuldade();

        /* vamos fazer a leitura do teclado com a dificuldade */
        int dificuldade = this.validaDificuldade();
        sc.nextLine(); // verificar a necessidade dessa linha !

        System.out.println("\nA JORNADA VAI COMEÇAR!\n\n");

        return dificuldade;
    }

    /**
     * Método para forçar uma entrada correta do usuário.
     * Para o futuro seria interessante a gente impedir que o usuário digite
     * uma String ou Enter.
     * 
     * @return inteiro entre 1 a 3
     */
    private int validaDificuldade() {
        int dificuldade = sc.nextInt();

        while (dificuldade < 1 || dificuldade > 3) {
            System.out.println(
                    Cores.VERMELHO + "ESCOLHA UM VALOR QUE CORRESPONDA AO NÍVEL DE DIFICULDADE !!" + Cores.RESET);
            System.out.print(Cores.NEGRITO + "Opção: " + Cores.RESET);
            dificuldade = sc.nextInt();
        }
        return dificuldade;
    }

    public void mostrarDialogoEvento(String nomeFase, String dialogo) {
        Prints.limparTela();

        System.out.println(nomeFase);
        Prints.imprimirLetraPorLetra(dialogo);
    }

    public void mostrarMapa(String nomeFaseAtual) {
        Prints.PrintaMapa(nomeFaseAtual);
    }

    public void mostrarEventoBar() {
        System.out.println(Cores.VERDE + "🍺 Você bebeu uma poção de lama no bar! Recuperou 30 de vida." + Cores.RESET);
    }

    public void mostrarEventoArmadilha() {
        System.out.println(Cores.VERMELHO + "🕳️ Você caiu num buraco com espinhos! Perdeu 15 de vida." + Cores.RESET);
    }

    public void mostrarRecompensaCarta(Carta carta) {
        System.out.println(Cores.AZUL + "📜 Você encontrou uma nova carta: " + carta.getNome() + "!" + Cores.RESET);
    }

    public void mostrarNovoCompanheiro(String nomeCompanheiro) {
        System.out.println(Cores.AZUL + " Princesa " + nomeCompanheiro + " se juntou à sua equipe!" + Cores.RESET);
    }

    public void fimDeJogo() {
        System.out.println(Cores.VERDE + Cores.NEGRITO + "🏆 FIM DE JOGO! VOCÊ ZEROU A CAMPANHA!" + Cores.RESET);
    }

    public int escolherCaminhoMapa(ArrayList<NoMapa> caminhos) {
        System.out.println("\nPara onde você quer ir agora?");
        for (int i = 0; i < caminhos.size(); i++) {
            System.out.println(i + " - " + caminhos.get(i).getEvento().getNomeFase());
        }

        System.out.print(Cores.NEGRITO + "Sua escolha: " + Cores.RESET);
        int escolha = sc.nextInt();

        while (escolha < 0 || escolha >= caminhos.size()) {
            System.out.println(Cores.VERMELHO + "Opção inválida! Digite um número do menu." + Cores.RESET);
            escolha = sc.nextInt();
        }
        return escolha;

    }

    /*Implementação dos métodos que são do TurnoVilao*/

    /** Anuncia que o turno do inimigo começou */
    public void mostrarInicioTurnoVilao(String nomeVilao) {
                System.out
                .println("\n" + Cores.VERMELHO + Cores.NEGRITO
                        + "================== TURNO DO INIMIGO ==================" + Cores.RESET);
                
        System.out.println(Cores.VERMELHO + "O " + nomeVilao + " está atacando..." + Cores.RESET);
    }

    /** Mostra a ação que o vilão executou (ex: causou dano, ganhou escudo) */
    public void mostrarAcaoVilao(String descricaoAcao) {
        System.out.println(descricaoAcao);
    }

    /** Mostra os efeitos atuais do vilão (se houver) */
    public void mostrarEfeitosEntidade(ArrayList<Efeito> listaEfeito, String nome) {
        /* Somente será impresso alguma coisa se existirem efeitos ativos */
        if (listaEfeito.size() > 0) {
            System.out.println("=================================================================");
            System.out.println("EFEITOS QUE ESTÃO EM AÇÃO EM: " + nome);
            for (Efeito efeito : listaEfeito) {
                System.out.println(efeito.getString());
            }
            System.out.println("=================================================================");
        }
    }

    public void mostrarAcaoVilao(ResultadoAcao resposta) {
        if (resposta.getTipo() == TipoAcao.ATAQUE) {

            System.out.println(resposta.getDono().getNome() + " usou '" + resposta.getNomeHabilidade() + "' e causou " + Cores.VERMELHO + resposta.getValor() + " de Dano" 
            + Cores.RESET + " de dano no " + resposta.getAlvo().getNome() + "!");

        } else if (resposta.getTipo() == TipoAcao.EFEITO) {
            System.out.println(resposta.getDono().getNome() + " usou '" + resposta.getNomeHabilidade() + Cores.AZUL + " e ativou " 
             + " efeito em " + resposta.getAlvo().getNome());

        } else if (resposta.getTipo() == TipoAcao.ESCUDO) {
            System.out.println(resposta.getDono().getNome() + " usou '" + resposta.getNomeHabilidade() + "' e ganhou " + Cores.AZUL 
            + resposta.getValor() + Cores.RESET + " de escudo!");
        }
    }

    /** Anuncia que o turno do inimigo acabou */
    public void mostrarFimTurnoVilao() {
        System.out.println(Cores.VERMELHO + Cores.NEGRITO + "======================================================\n"
                + Cores.RESET);
    }

    /*Implementação dos métodos que são de TurnoHeroi */

    public void mostrarStatusTurno(Heroi heroiAtual, ArrayList<Heroi> herois, ArrayList<Inimigo> inimigos) {
        this.mostrarEfeitosEntidade(heroiAtual.getListaEfeitos(), heroiAtual.getNome());
        Prints.statusBatalha(heroiAtual, herois, inimigos);
        Prints.energia(heroiAtual.getEnergiaAtual());
    }

    public int escolherAcaoCompra(int limiteCompra, int cartasCompradas) {
        Prints.faseCompra(limiteCompra, cartasCompradas);

        int opcaoCompra = sc.nextInt(); // podemos melhorar impedindo entrada de Strings.
        return opcaoCompra;
    }

    public int escolherCartaParaComprar(ArrayList<Carta> pilhaCompra) {
        /*exatamente a mesma lógica de imprimir o baralho */
        for (int i = 0; i < pilhaCompra.size(); i++) {
            System.out.println(Cores.NEGRITO + i + Cores.RESET + "-" + Cores.AZUL + pilhaCompra.get(i).getNome() + Cores.RESET + " -  "
                    + pilhaCompra.get(i).getDescricao());
        }

        int i = sc.nextInt(); // podemos melhorar no futuro com a entrada de STRINGS
        return i;
    }

    public void limparTela() {
        Prints.limparTela();
        System.out.println("\n");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public int escolherAcaoBatalha() {
        Prints.faseBatalha();
        int  opcao = sc.nextInt(); // podemos validar essa entrada com um método no futuro.

        return opcao;
    }

    public void mostrarCartasMao(ArrayList<Carta> mao) {
         System.out.println("\nSuas Cartas na Mão:");

        if (mao.isEmpty()) {
            System.out.println("A sua mão está Vazia !!");
        }

        /*como a mão não está vazia faz sentido imprimir as cartas */
        for (int i = 0; i < mao.size(); i++) {
            System.out.println(Cores.NEGRITO + i + Cores.RESET + "-" + Cores.AZUL + mao.get(i).getNome() + Cores.RESET + " -  "
                    + mao.get(i).getDescricao());
        }
    }

    public int escolherCartaParaUsar(ArrayList<Carta> mao) {
        if (mao.isEmpty())
            return -1;

        System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET);
        int i = sc.nextInt();

        while (i < 0 || i >= mao.size()) {
            System.out.println(Cores.VERMELHO + "Carta inválida!" + Cores.RESET);
            System.out.print(Cores.NEGRITO + "Escolha o número da carta para usar: " + Cores.RESET);
            i = sc.nextInt();
        }
        return i;
    }

    public int escolherAlvoInimigo(ArrayList<Inimigo> inimigos) {
        /*em tese isso aqui resolve a questão dos inimigos */
        System.out.println(Cores.VERMELHO + "\nEscolha o seu alvo: " + Cores.RESET);
        for (int j = 0; j < inimigos.size(); j++) {
            if (inimigos.get(j).estaVivo()) {
                System.out.println(j + " - " + inimigos.get(j).getNome() + " (Vida: " + inimigos.get(j).getVida() + ")");
            }
        }
        System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
        int alvoEscolhido = sc.nextInt();

        while (alvoEscolhido < 0 || alvoEscolhido >= inimigos.size()) {
            System.out.println(Cores.VERMELHO + "Alvo inválido!" + Cores.RESET);
            System.out.print(Cores.NEGRITO + "Alvo: " + Cores.RESET);
            alvoEscolhido = sc.nextInt();
        }
        return alvoEscolhido;
    }

    public int escolherAlvoHeroi(ArrayList<Heroi> herois) {
        System.out.println(Cores.VERDE + "\nEscolha o seu Herói Aliado: " + Cores.RESET);
        for (int j = 0; j < herois.size(); j++) {
            if (herois.get(j).estaVivo()) {
                System.out.println(j + " - " + herois.get(j).getNome() + " (Vida: " + herois.get(j).getVida() + ")");
            }
        }
        System.out.print(Cores.NEGRITO + "Herói: " + Cores.RESET);
        int heroiEscolhido = sc.nextInt();

        while (heroiEscolhido < 0 || heroiEscolhido >= herois.size()) {
            System.out.println(Cores.VERMELHO + "Heroi inválido!" + Cores.RESET);
            System.out.print(Cores.NEGRITO + "Heroi: " + Cores.RESET);
            heroiEscolhido = sc.nextInt();
        }
        return heroiEscolhido;
    }

    /*Implementação de Batalha */

    public void mostrarInicioBatalha() {
        System.out.println(Cores.AMARELO + Cores.NEGRITO + "\n=== A BATALHA COMEÇOU! ===" + Cores.RESET);
    }

    public void mostrarNovaRodada(String nomeHeroi) {
        System.out.println(Cores.CIANO + "\n>>> Turno de " + nomeHeroi + " <<<" + Cores.RESET);
    }

    public void mostrarVitoriaBatalha() {
        System.out.println(Cores.VERDE + "\n🎉 VITÓRIA! A área foi limpa." + Cores.RESET);
    }

    public void mostrarDerrotaBatalha() {
        System.out.println(Cores.VERMELHO + "\n☠️ DERROTA... Sua jornada termina aqui." + Cores.RESET);
    }

    /*implementação de jogar novamente */
    public int escolheJogarNovamente() {
        Prints.jogarNovamente();

        int escolha = sc.nextInt();
        return escolha;
    }

    /*métodos para o evento da loja*/

    public void telaInicialLoja(String dialogo, int numeroLoja) {
        Prints.imprimirLetraPorLetra(dialogo);
        System.out.println("\n" + Cores.AMARELO + "=== 💰 BEM-VINDO À LOJA DO PÂNTANO (Loja " + numeroLoja + ") ==="
                + Cores.RESET);
    }

    public void mostrarSaldo(int moedas) {
        System.out.println("Saldo atual: 💰 " + moedas);
    }

    public void mostrarSaldoInsuficiente() {
        System.out.println("Dinheiro insuficiente ou opção inválida!");
    }

    public void mostrarSaidaLoja() {
        System.out.println("Saindo da loja...");
    }

    public void telaOpcaoLoja() {
            System.out.println("\nO que deseja comprar?");
            System.out.println("1 - Poção de Cura (Recupera 30 HP) - 💰 20");
            System.out.println("2 - Treinamento de Resiliência (+20 HP Máx) - 💰 50");
            System.out.println("3 - Elixir de Café (+1 Energia Máx) - 💰 70");
    }

    public int escolhaOpcaoLoja() {
        System.out.println("0 - Sair da Loja");
        System.out.print("Escolha: ");

        int escolha = sc.nextInt();
        sc.nextLine();

        return escolha;
    }

}
