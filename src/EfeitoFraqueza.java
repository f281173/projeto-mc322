/**
 * O Efeito fraqueza diminui em uma porcentagem fixa o ataque de uma entidade
 * durante a quantidade de turnos respectiva a quantidade de acúmulos.
 */
public class EfeitoFraqueza extends Efeito {

    public EfeitoFraqueza(int acumulos, GameManager gm, int dano) {
        super(acumulos, gm, dano);
        this.nome = "Efeito Fraqueza";
    }

    public void serNotificado(Estados state) {
        if (state == Estados.ATAQUE) {
            this.acaoEfeito();
        } else if (state == Estados.FIM_DE_TURNO) {
            this.diminuiAcumulos();
            /* aqui é a condição para o efeito terminar */
            if (this.getAcumulos() <= 0) {
                this.getDono().terminaEfeito(TiposEfeitos.FRAQUEZA);
                this.getGm().desinscrever(this, Estados.ATAQUE);
                this.getGm().desinscrever(this, Estados.FIM_DE_TURNO);
            }
        }

    }

    @Override
    public void acaoEfeito() {
        this.getDono().setHasEfeitoFraqueza(true);
        System.out.println(this.getDono().getNome() + " sofreu o efeito de fraqueza: " + this.nome
                + " que reduziu a força de suas cartas em 25% ");
    }
}
