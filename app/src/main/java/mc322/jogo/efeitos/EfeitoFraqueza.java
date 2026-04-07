package mc322.jogo.efeitos;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.observer.Estados;

/**
 * O Efeito fraqueza diminui em uma porcentagem fixa o ataque de uma entidade
 * durante a quantidade de turnos respectiva a quantidade de acúmulos.
 * 
 * A lógica de acúmulos funciona de uma forma diferente, nesse caso soma-se os acúmulos
 * caso seja aplicado mais um efeito, mas permanece a maior fraqueza. Dessa forma, caso
 * tenha uma fraqueza de 50% e uma de 25%, será a de maior redução que passa agir durante
 * todos os acúmulos
 * 
 */
public class EfeitoFraqueza extends Efeito {
    private int valorFraqueza;

    public EfeitoFraqueza(int acumulos, GameManager gm, int valorFraqueza) {
        super(acumulos, gm);
        this.nome = "Efeito Fraqueza";
        this.valorFraqueza = valorFraqueza;
        this.tipo = TiposEfeitos.FRAQUEZA;
    }

    public EfeitoFraqueza(EfeitoFraqueza efeito) {
        this(efeito.getAcumulosInicial(), efeito.getGm(), efeito.getValorFraqueza());
    }

    public void serNotificado(Estados state) {
        if (state == Estados.FIM_DE_TURNO) {
            this.diminuiAcumulos();
            /* aqui é a condição para o efeito terminar */
            if (this.getAcumulos() <= 0) {
                this.getDono().terminaEfeito(TiposEfeitos.FRAQUEZA);
                this.getGm().desinscrever(this, Estados.ATAQUE); //Posso tirar dessa lista de subscriber
                this.getGm().desinscrever(this, Estados.FIM_DE_TURNO);
            }
        }
    }

    @Override // aqui tenho que pensar melhor como vou usar isso aqui (minha ideia era que não fosse necessário o heroi verificar se está sob ação do efeito)
    public void acaoEfeito() {
        System.out.println(this.getDono().getNome() + " sofreu o efeito de fraqueza: " + this.nome
                + " que reduziu a força de suas cartas em 25% ");
    }

    public int getValorFraqueza() {
        return this.valorFraqueza;
    }

    public void setValorFraqueza(int valorFraqueza) {
        this.valorFraqueza = valorFraqueza;
    }

    /**
     * Esse método implementa a lógica de acúmulos para esse efeito. Veja que caso
     * o efeito que será aplicado seja maior que o existente em termos de valor em porcentagem, então
     * substituímos os efeitos e somamos os acúmulos
     * 
     * Não deixamos a aplicação de aumentos independentes em cada efeito. Como aumentar duas 
     * vezes a força ou fraqueza em uma determinada porcentagem
     * 
     * @param valorFraqueza porcentagem de fraqueza do efeito que será aplicada
     * @param acumulos quantidade de acúmulos do efeito.
     */
    public void alteraFraqueza(int valorFraqueza, int acumulos) {
        if (this.getValorFraqueza() < valorFraqueza)
            this.setValorFraqueza(valorFraqueza);
        this.aumentaAcumulos(acumulos);
    }

    @Override
    public RequisitoJogo requisitoEfeito() {
        return RequisitoJogo.INIMIGO;
    }
}
