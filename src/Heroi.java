import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe responsável por representar os Heróis controlados pelo jogador.
 * Herda as características básicas de Entidade, mas adiciona mecânicas
 * exclusivas de gerenciamento de Energia e uma Mão de Cartas individual.
 */

public class Heroi extends Entidade {
    private int energia;
    private ArrayList<Carta> maoJogador;
    private ArrayList<Carta> baralhoPessoal;
    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001B[34m";
    public static final String NEGRITO = "\u001B[1m";

    public Heroi(String nome, int vida, int escudo, int energia, int vidaInicial, int velocidade, boolean turno,
            GameManager gm) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.energia = energia;
        this.vidaInicial = vidaInicial;
        this.velocidade = velocidade;
        this.turno = turno;
        this.maoJogador = new ArrayList<>();
        this.gm = gm;
        this.inicializaMap();
    }

    public void inicializaMap() { // poderia estar em entidade
        this.mapEfeitos = new HashMap<>();

        for (TiposEfeitos tipo : TiposEfeitos.values())
            this.mapEfeitos.put(tipo, null);
    }

    // Busca uma carta na mão do jogador pelo nome e retorna o seu índice (posição).
    public int encontraNome(String nomeCarta) { // essa função não pode estar aqui
        int i = 0;

        while (i < this.maoJogador.size()) {
            if (this.maoJogador.get(i).getNome().equals(nomeCarta))
                return i;
            i++;
        }
        return -1;
    }

    // Utiliza o método encontraNome para buscar e retornar o objeto Carta exato que
    // está na mão.
    public Carta encontraCarta(String nomeCarta) {
        int i = encontraNome(nomeCarta);
        Carta carta = this.maoJogador.get(i);
        return carta;
    }

    @Override
    public void recebeDano(int dano) {

        if (this.escudo >= dano) {
            this.escudo -= dano;
        } else {

            int danoRestante = dano - this.escudo;
            this.escudo = 0;
            this.vida -= danoRestante;
        }
    }

    @Override
    public void recebeDanoEfeito(int dano) {
        if (this.vida >= dano) {
            this.vida -= dano;
        } else {
            this.vida = 0;
        }
    }

    @Override
    public void ganhaEscudo(int valorEscudo) { 
        this.escudo += valorEscudo;
    }

    @Override
    public void zeraEscudo() {
        this.escudo = 0;
    }

    @Override
    public boolean estaVivo() {
        if (this.vida <= 0) {
            return false;
        } else {
            return true;
        }
    }

    // Adiciona uma carta recém-comprada da loja pra mão do herói.
    public void adicionaCard(Carta carta) {
        this.maoJogador.add(carta);
    }

    @Override
    public int getEscudo() {
        return this.escudo;
    }

    public int getEnergia() {
        return this.energia;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getVida() {
        return this.vida;
    }

    public void imprimeCartas() {
        for (int i = 0; i < this.maoJogador.size(); i++) {
            System.out.println(NEGRITO + i + RESET + "-" + AZUL + this.maoJogador.get(i).getNome() + RESET + " -  "
                    + this.maoJogador.get(i).getDescricao());
        }
    }

    public ArrayList<Carta> getMaoJogador() {
        return this.maoJogador;
    }

    // Remove da mão e bota numa pilha de descarte
    public void removeCartaMaoJogador(Baralho baralho, int i) {
        Carta carta = this.maoJogador.remove(i);
        baralho.adicionaPilhaDescarte(carta);
    }

    // Limpa a mão do herói, com aquelas q ele n usou. Manda pra pilha de descarte
    public void resetaMaoJogador(Baralho baralho) {
        int tamanho = this.maoJogador.size();

        while (tamanho > 0) {
            tamanho--;
            baralho.adicionaPilhaDescarte(this.maoJogador.remove(tamanho));
        }
    }

    /*essa lógica não precisa estar aqui e sim na mão */
    public boolean maoVazia() {
        if (this.maoJogador.size() == 0)
            return true;
        return false;
    }

    @Override
    public int getVidaInicial() {
        return this.vidaInicial;
    }

    @Override
    public int getVelocidade() {
        return this.velocidade;
    }

    // Pra ver se já jogou ou não naquela rodada
    @Override
    public boolean getTurno() {
        return this.turno;
    }

    @Override
    public void verificaseAtacou(boolean status) {
        this.turno = status;
    }

    @Override
    public void aplicarEfeito(TiposEfeitos tipo, int acumulos) {
        Efeito valor = this.mapEfeitos.get(tipo);

        /* significa que ainda não existe esse efeito nessa entidade */
        if (valor == null) {
            Efeito novoEfeito = Efeito.criaEfeito(tipo, acumulos, this.gm);
            novoEfeito.setDono(this);
            this.mapEfeitos.put(tipo, novoEfeito);

            /* preciso inscrever cada efeito do modo correto */
            if (tipo == TiposEfeitos.VENENO) {
                this.gm.inscrever(novoEfeito, Estados.INICIO_DE_TURNO);

            } else if (tipo == TiposEfeitos.FRAQUEZA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);

            } else if (tipo == TiposEfeitos.FORCA) {
                // implementação do tipo força
            }

        } else {
            valor.aumentaAcumulos(acumulos);
        }
    }

    @Override
    public void terminaEfeito(TiposEfeitos tipo) {
        this.mapEfeitos.put(tipo, null);
    }

    public void setHasEfeitoFraqueza(boolean valor) {
        this.hasEfeitoFraqueza = valor;
    }

    public boolean getHasEfeitoFraqueza() {
        return this.hasEfeitoFraqueza;
    }
}
