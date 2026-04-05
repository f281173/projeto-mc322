package mc322.jogo.cartas;

import mc322.jogo.RequisitoJogo;
import mc322.jogo.efeitos.Efeito;
import mc322.jogo.efeitos.TiposEfeitos;
import mc322.jogo.entidades.Entidade;

public class CartaEfeito extends Carta {
    protected TiposEfeitos tipo;
    protected int acumulos;

    public CartaEfeito(String nome, String descricao, int custo, int opcaoCarta, Efeito efeito, int acumulos) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.opcaoCarta = opcaoCarta;
            
        this.acumulos = acumulos;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public int getCusto() {
        return this.custo;
    }

    @Override
    public void usar(Entidade personagem, Baralho baralho) {
        personagem.aplicarEfeito(this.tipo, this.acumulos);
        this.personagem = null;
        baralho.adicionaPilhaDescarte(this);
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public int getOpcaoCarta() {
        return this.opcaoCarta;
    }

    public TiposEfeitos getTipoEfeito() {
        return this.tipo;
    }

    public int getAcumulo() {
        return this.acumulos;
    }

    public void explicaEfeito(TiposEfeitos tipo) {
        if (tipo == TiposEfeitos.VENENO) {
            System.out.print(
                    "\nO efeito Veneno gera um dano de dimensão igual ao seu acúmulo e a cada inicio de turno o acúmulo cai em uma unidade.O efeito tem validade até acúmulo atingir zero\n");

        } else if (tipo == TiposEfeitos.FRAQUEZA) {
            System.out.print(
                    "\nO efeito Fraqueza reduz a força do oponente em 25%. Tem duração equivalente a quantidade de acúmulos que vão sendo subtraídos a cada final de turno\n");
        }
        System.out.println("APLICAR CARTAS DO MESMO TIPO DE EFEITO TEM COMO RESULTADO A SOMA DOS ACÚMULOS");

    }

    public TiposEfeitos getTipo() { //essa função já existe (depois exclui !!!)
        return this.tipo;
    }

    @Override
    public RequisitoJogo cartaRequisito() {
        return RequisitoJogo.INIMIGO;
    }
}
