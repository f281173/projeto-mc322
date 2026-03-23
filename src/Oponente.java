import java.util.ArrayList;
import java.util.Collections;

public class Oponente {
    private ArrayList<Inimigo> todosInimigos = new ArrayList<>();
    private ArrayList<Inimigo> inimigosEscolhidos = new ArrayList<>();

    public void adicionarInimigoTodos(Inimigo enemy) {
        this.todosInimigos.add(enemy);
    }


    public void gerarInimigos(int dificuldade) {
        this.inimigosEscolhidos.clear();
        
        Collections.shuffle(this.todosInimigos);
        
        int quantidade = Math.min(dificuldade, todosInimigos.size());
        
        for (int i = 0; i < quantidade; i++) {
            this.inimigosEscolhidos.add(this.todosInimigos.get(i));
        }
    }

    public ArrayList<Inimigo> getInimigosEscolhidos() {
        return this.inimigosEscolhidos;
    }

    public boolean temInimigosVivos() {
        for (Inimigo i : inimigosEscolhidos) {
            if (i.estaVivo()) {
                return true; 
            }
        }
        return false; 
    }

}
