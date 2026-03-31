import java.util.ArrayList;

public class CartaDanoArea extends CartaDano {

    public CartaDanoArea(String nome, String descricao, int custo, int dano, int opcaoCarta) {
        super(nome, descricao, custo, dano, opcaoCarta);
    }

    // Método exclusivo para bater em todos os inimigos vivos de uma vez
    public void usarEmArea(ArrayList<Inimigo> inimigos, Baralho baralho) {
        for (Inimigo alvo : inimigos) {
            if (alvo.estaVivo()) {
                // Se o herói tiver o buff de Força, você pode multiplicar esse dano aqui!
                alvo.recebeDano(this.acessoCartaDanoDano());
            }
        }
        baralho.adicionaPilhaDescarte(this);
    }
}