/**
 * O Efeito Força aumenta o dano do ataque de uma entidade
 * durante a quantidade de turnos respectiva à quantidade de acúmulos.
 */
public class EfeitoForca extends Efeito {

    public EfeitoForca(int acumulos, GameManager gm) {
        super(acumulos, gm);
        this.nome = "Efeito Força";
    }

    public void serNotificado(Estados state) {
        if (state == Estados.ATAQUE) {  
            this.acaoEfeito();
        } else if (state == Estados.FIM_DE_TURNO) {
            this.diminuiAcumulos();
            
            if (this.getAcumulos() <= 0) {
                this.getDono().terminaEfeito(TiposEfeitos.FORCA);
                this.getGm().desinscrever(this, Estados.ATAQUE);
                this.getGm().desinscrever(this, Estados.FIM_DE_TURNO);
            }
        }
    }

    @Override
    public void acaoEfeito() {
        this.getDono().setHasEfeitoForca(true);
        System.out.println(this.getDono().getNome() + " sofreu o efeito de força: " + this.nome
                + " que aumentou a força de suas cartas em 25% ");
    }
}
