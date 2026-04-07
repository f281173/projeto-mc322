package mc322.jogo.entidades;

import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.EfeitoForca;
import mc322.jogo.efeitos.EfeitoFraqueza;
import mc322.jogo.efeitos.TiposEfeitos;

import java.util.ArrayList;

/**
 * Classe abstrata com o contrato mínimo que uma Entidade (Herois e inimigos)
 * devem ter.
 */
public abstract class Entidade {
    protected String nome;
    protected int vida;
    protected int escudo;
    protected int vidaInicial;
    protected boolean turno;
    protected GameManager gm;
    protected int velocidade;

    /** atributo para gerenciar os efeitos que estão agindo na entidade */
    protected ArrayList<Efeito> listaEfeitos;

    /** método para gerar o dano partindo de uma carta de dano */
    public abstract void recebeDano(int dano);

    /**
     * método para gerar o dano partindo de uma carta de Efeito. O efeito veneno tem
     * a peculiaridae de retirar o dono direto da vida.
     * 
     * @param dano: dano no oponente, já descontado de possíveis efeitos de fraqueza
     */
    public abstract void recebeDanoEfeito(int dano);

    public void ganhaEscudo(int valorEscudo) {
        this.escudo += valorEscudo;
    }

    /** Método responsável por retirar o escudo da entidade a cada final de turno */
    public void zeraEscudo() {
        this.escudo = 0;
    }

    public int getEscudo() {
        return this.escudo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getVidaInicial() {
        return this.vidaInicial;
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public boolean getTurno() {
        return this.turno;
    }

    public void verificaseAtacou(boolean status) {
        this.turno = status;
    }

    public boolean estaVivo() {
        if (this.vida > 0)
            return true;
        return false;
    }

    public ArrayList<Efeito> getListaEfeitos() {
        return this.listaEfeitos;
    }

    /**
     * Encontra o índice de um efeito na lista de efeitos da entidade. Caso
     * não exista tal efeito, retorna-se -1
     * 
     * @param tipoAlvo o tipo de efeito buscado
     * @return índice no vetor com a posição do efeito.
     */
    private int buscaEfeito(TiposEfeitos tipoAlvo) {
        for (int i = 0; i < this.getListaEfeitos().size(); i++) {
            Efeito efeito = getListaEfeitos().get(i);

            if (efeito.getTipo() == tipoAlvo)
                return i;
        }
        return -1; // não existe ainda esse efeito agindo no Heroi
    }

    /**
     * Método responsável por inserir o efeito na lista de efeitos da
     * Entidade.Primeiro,
     * verifico se esse efeito já existe, caso sim implemento a lógica de acumúlos
     * apropriada {@link EfeitoFraqueza} {@link EfeitoForca}
     * para cada tipo de efeito.
     * 
     * @param efeito efeito que será aplicado por uma carta ou por um inimigo.
     */
    public void aplicarEfeito(Efeito efeito) {
        int valor = this.buscaEfeito(efeito.getTipo());

        /* significa que ainda não existe esse efeito nessa entidade */
        if (valor == -1) {
            Efeito novoEfeito = Efeito.criaEfeito(efeito);
            novoEfeito.setDono(this);
            this.getListaEfeitos().add(novoEfeito);

            /* preciso inscrever cada efeito do modo correto */
            if (novoEfeito.getTipo() == TiposEfeitos.VENENO) {
                this.gm.inscrever(novoEfeito, Estados.INICIO_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);

            } else if (novoEfeito.getTipo() == TiposEfeitos.FORCA) {
                this.gm.inscrever(novoEfeito, Estados.ATAQUE);
                this.gm.inscrever(novoEfeito, Estados.FIM_DE_TURNO);
            }

        } else {
            /* decido como cada tipo de efeito vai se comportar */
            if (efeito.getTipo() == TiposEfeitos.VENENO) {
                this.getListaEfeitos().get(valor).aumentaAcumulos(efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FRAQUEZA) {
                ((EfeitoFraqueza) this.getListaEfeitos().get(valor))
                        .alteraFraqueza(((EfeitoFraqueza) efeito).getValorFraqueza(), efeito.getAcumulosInicial());

            } else if (efeito.getTipo() == TiposEfeitos.FORCA) {
                ((EfeitoForca) this.getListaEfeitos().get(valor)).alteraForca(((EfeitoForca) efeito).getValorForca(),
                        efeito.getAcumulosInicial());
            }
        }
    }

    /**
     * Retira o efeito da lista de efeitos daquela entidade
     * 
     * @param tipo: especifífica qual o tipo de efeito na lista de efeitos da
     *              Entidade para que possa ser removido
     * 
     */
    public void terminaEfeito(TiposEfeitos tipo) {
        int indice = this.buscaEfeito(tipo);
        this.getListaEfeitos().remove(indice);
    }

    /**
     * Método abstrato que será implementado em cada entidade com a forma personalizada de ataque.
     * Recebe o valor de dano base de uma carta ou de uma ação do Inimigo e caso a entidade esteja sob ação de
     * algum efeito que modfique o dano, Ataque será o método que faz essa verificação. 
     * 
     * @param alvo Heroi ou vilão que será atacado
     * @param valorDano valor de dano da carta para ser usada.
     *   
     */
    public abstract void ataque(Entidade alvo, int valorDano); 

    /** Método para impressão de todos os efeitos ativos naquela Entidade */
    public void imprimeEfeitos() {

        /*Somente será impresso alguma coisa se existirem efeitos ativos */
        if (this.getListaEfeitos().size() > 0) {
            System.out.println("=================================================================");
            System.out.println("EFEITOS QUE ESTÃO EM AÇÃO EM: " + this.getNome());
            for (Efeito efeito : this.getListaEfeitos()) {
                System.out.println(this.getNome() + "está sob " + efeito.getString());
            }
            System.out.println("=================================================================");
        }
    }

}
