/**
 * O efeito de veneno sempre acontece no início de cada turno e gera um dano
 * diretamente na vida do infectado por esse efeito em um valor igual a
 * quantidade de acúmulos de efeito.
 */
public class EfeitoVeneno extends Efeito {

    public EfeitoVeneno(int acumulos, GameManager gm) {
        super(acumulos, gm);
        this.nome = "Efeito Veneno";
    }

    public void serNotificado(Estados state) {
        if (this.getAcumulos() > 0) { // ele ainda pode usar o efeito
            this.acaoEfeito();
            this.diminuiAcumulos();
        } else {
            this.getDono().terminaEfeito(TiposEfeitos.VENENO);
            this.getGm().desinscrever(this, Estados.INICIO_DE_TURNO);
        }
    }

    @Override
    public void acaoEfeito() {
        this.getDono().recebeDanoEfeito(acumulos);
        System.out.println(this.getDono().acessoNome() + " sofreu o efeito de veneno: " + this.nome+ " com um dano sofrido de " + this.acumulos);
    }

    @Override
    public Estados tipoDeEstado() {
        return Estados.INICIO_DE_TURNO;
    }
}