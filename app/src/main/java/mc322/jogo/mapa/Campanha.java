package mc322.jogo.mapa;

import java.util.Random;
import java.util.function.Supplier;

import mc322.jogo.Cores;
import mc322.jogo.Dados;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.entidades.Heroi;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.Batalha;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Jogador;
import mc322.jogo.gerenciador.Oponente;
import mc322.jogo.gerenciador.Prints;

public class Campanha {

    private static Oponente carregaInimigoGenerico(GameManager gm, Supplier<Inimigo> inimigo1, int dificuldade) {
        Oponente op = new Oponente();
        op.adicionarInimigoTodos(inimigo1.get());

        Random gerador = new Random();
        for (int i = 0; i < (dificuldade - 1); i++) {
            int sorteio = gerador.nextInt(4); 
            if (sorteio == 0) {
                op.adicionarInimigoTodos(Dados.criarOgreHunter(gm));
            } else if (sorteio == 1) {
                op.adicionarInimigoTodos(Dados.criarWolf(gm));
            } else if (sorteio == 2){
                op.adicionarInimigoTodos(Dados.criarCrow(gm));
            } else if (sorteio == 3){
                op.adicionarInimigoTodos(Dados.criarPig(gm));
            }
        }
        op.gerarInimigos(dificuldade); 
        return op;
    }

    private static Oponente carregarBoss(Inimigo boss) {
        if (boss == null) return null;
        Oponente op = new Oponente();
        op.adicionarInimigoTodos(boss);
        op.gerarInimigos(1); 
        return op;
    }

    public static NoMapa criarMapa(GameManager gm, int dificuldade) {
        
        // --- 1 a 10: Início até a Encruzilhada ---
        
        NoMapa n1_inicio = new NoMapa(new EventoEscolha(
            "Inicio", 
            Prints.DIALOGO_N1
        ));

        NoMapa n1_batalha = new NoMapa(new Batalha(
            "Batalha", 
            Cores.VERMELHO + Cores.NEGRITO + "Aldeão: " + Cores.RESET + "Renda-se, monstro! Em nome de Lord Farquaad!\n" +
            Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Toma essa!\n", 
            carregaInimigoGenerico(gm, () -> Dados.criarOgreHunter(gm), dificuldade),
            false
        ));

        NoMapa n10_caçador = new NoMapa(new Batalha(
            "Acampamento do Caçador", 
            Prints.DIALOGO_N10, 
            carregarBoss(Dados.criarMerryMen(gm)),
            true
        ));

        NoMapa n10_flores = new NoMapa(new EventoEscolha(
            "Encruzilhada",
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Shrek continua sua jornada pela floresta e encontra uma encruzilhada.\n" +
            Cores.AZUL + Cores.NEGRITO + "Shrek: " + Cores.RESET + "Flor azul com espinhos vermelhos... ou flor vermelha com espinhos azuis? Qual caminho eu pego?\n"
        ));

        n1_inicio.adicionarCaminho(n1_batalha);
        n1_batalha.adicionarCaminho(n10_caçador);
        n10_caçador.adicionarCaminho(n10_flores);

        // --- CAMINHO ESQUERDO (BURRO) ---
        
        NoMapa n21_burro = new NoMapa(new Batalha(
            "Flor azul com espinhos vermelhos", 
            Prints.DIALOGO_N21,
            carregaInimigoGenerico(gm, () -> Dados.criarMonsieurHood(gm), dificuldade),
            false
        ) {
            @Override
            public void darRecompensaExtra(Jogador jogador, GameManager gm) {
                System.out.println(Cores.AZUL + " O Burro se juntou à sua equipe!" + Cores.RESET);
                Heroi burro = Dados.criarBurro(gm);
                jogador.adicionarHeroiTodos(burro);
                jogador.getHeroisEscolhidos().add(burro); 
            }
        });
        
        NoMapa n31_golpe = new NoMapa(new EventoRecompensa(
            "Caminho bonito", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "O Burro não para de falar, mas você acha um pergaminho antigo no chão.",
            new CartaDano("Voadora Deslizante", "[Custo: 2 | Dano: 45]", 2, 45)
        ));

        NoMapa n32_batalha = new NoMapa(new Batalha(
            "Caminho tortuoso", 
            Cores.VERMELHO + Cores.NEGRITO + "Lobo Mau: " + Cores.RESET + "Cuidado por onde anda, ogro...", 
            carregaInimigoGenerico(gm, () -> Dados.criarWolf(gm), dificuldade),
            false
        ));
        
        NoMapa n41_batalha = new NoMapa(new Batalha(
            "Floresta densa",
            Cores.NEGRITO + "Narrador:" + Cores.RESET + "Uma emboscada na floresta densa!",
            carregaInimigoGenerico(gm, () -> Dados.criarCrow(gm), dificuldade),
            false
        ));
        
        NoMapa n42_bar = new NoMapa(new EventoDescanso(
            "Seguir pelo rio",
            Cores.NEGRITO + "Bartender: " + Cores.RESET + "Bem-vindo ao bar Maçã Envenenada."
        ));
        
        NoMapa n43_batalha = new NoMapa(new Batalha(
            "Passar pela ponte", 
            Cores.VERMELHO + Cores.NEGRITO + "Guarda da Ponte: " + Cores.RESET + "Pedágio cobrado em sangue!",
            carregaInimigoGenerico(gm, () -> Dados.criarFarquadMascot(gm), dificuldade),
            false
        ));

        n10_flores.adicionarCaminho(n21_burro);
        n21_burro.adicionarCaminho(n31_golpe);
        n21_burro.adicionarCaminho(n32_batalha);
        n31_golpe.adicionarCaminho(n41_batalha);
        n31_golpe.adicionarCaminho(n42_bar);
        n32_batalha.adicionarCaminho(n43_batalha);

        // --- CAMINHO DIREITO (PINÓQUIO) ---
        
        NoMapa n22_pinoquio = new NoMapa(new Batalha(
            "Flor vermelha com espinhos azuis", 
            Prints.DIALOGO_N22,
            carregaInimigoGenerico(gm, () -> Dados.criarMonsieurHood(gm), dificuldade),
            false
        ) {
            @Override
            public void darRecompensaExtra(Jogador jogador, GameManager gm) {
                System.out.println(Cores.AZUL + " O Pinóquio se juntou à sua equipe!" + Cores.RESET);
                Heroi pinoquio = Dados.criarPinoquio(gm);        
                jogador.adicionarHeroiTodos(pinoquio);
                jogador.getHeroisEscolhidos().add(pinoquio);
            }
        });

        NoMapa n33_golpe = new NoMapa(new EventoRecompensa(
            "Tempo na fogueira",
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "O Pinóquio te ensina uma técnica de mentira.", 
            new CartaEscudo("Só uma Mentirinha", "[Custo:1 | Escudo: 35]", 1, 35)
        ));

        NoMapa n34_buraco = new NoMapa(new EventoArmadilha(
            "Floresta à noite",
            Cores.AZUL + Cores.NEGRITO + "Você: " + Cores.RESET + "Ahh! Quem colocou esse buraco aqui?"
        ));

        NoMapa n44_batalha = new NoMapa(new Batalha(
            "Cabana",
            Cores.VERMELHO + Cores.NEGRITO + "Mascote do Lord: " + Cores.RESET + "Ei, essa é minha casa! Em nome de Farquaad, você morre aqui!", 
            carregaInimigoGenerico(gm, () -> Dados.criarFarquadMascot(gm), dificuldade),
            false
        ));

        NoMapa n45_golpe = new NoMapa(new EventoRecompensa(
            "Quintal de uma casa", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Você encontra uma máscara do farquad. Parece de um mascote...", 
            new CartaDano("Máscara da morte", "[Custo: 3 | Dano: 65]", 3, 65)
        ));

        NoMapa n46_golpe = new NoMapa(new EventoRecompensa(
            "Seguindo pela floresta", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Você encontra uma panela de ferro. Excelente para proteger a cabeça.", 
            new CartaEscudo("Panela de Ferro", "[Custo: 1 | Escudo: 40]", 1, 40)
        ));

        n10_flores.adicionarCaminho(n22_pinoquio);
        n22_pinoquio.adicionarCaminho(n33_golpe);
        n22_pinoquio.adicionarCaminho(n34_buraco);
        n33_golpe.adicionarCaminho(n44_batalha);
        n33_golpe.adicionarCaminho(n45_golpe);
        n34_buraco.adicionarCaminho(n46_golpe);

        // --- 5: FARQUAAD E LOJA ---

        NoMapa n5_farquaad = new NoMapa(new Batalha(
            "Castelo de Duloc", 
            Prints.DIALOGO_N5,
            carregarBoss(Dados.criarFarquaad(gm)),
            true
        ));

        NoMapa n5_loja = new NoMapa(new EventoLoja(
            "Mercado de Duloc", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Aproveite para gastar suas moedas antes da masmorra!", 
            1
        ));

        NoMapa n5_masmorra = new NoMapa(new EventoEscolha(
            "Proposta",
            Prints.DIALOGO_N50
        ));

        n41_batalha.adicionarCaminho(n5_farquaad);
        n42_bar.adicionarCaminho(n5_farquaad);
        n43_batalha.adicionarCaminho(n5_farquaad);
        n44_batalha.adicionarCaminho(n5_farquaad);
        n45_golpe.adicionarCaminho(n5_farquaad);
        n46_golpe.adicionarCaminho(n5_farquaad);

        n5_farquaad.adicionarCaminho(n5_loja);
        n5_loja.adicionarCaminho(n5_masmorra);

        // --- ATÉ O CAVALEIRO ---

        NoMapa n51_batalha = new NoMapa(new Batalha(
            "Vamos lá",
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "No caminho para a masmorra, Shrek encontra inimigos...", 
            carregaInimigoGenerico(gm, () -> Dados.criarWitch(gm), dificuldade),
            false
        ));

        NoMapa n52_batalha = new NoMapa(new Batalha(
            "Quero não!",
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Voltando para o pântano, Shrek encontra inimigos...",
            carregaInimigoGenerico(gm, () -> Dados.criarOgreHunter(gm), dificuldade),
            false
        ));
        
        NoMapa n61_torre_bruxa = new NoMapa(new Batalha(
            "Torre da Bruxa Velha", 
            Prints.DIALOGO_N61,
            carregarBoss(Dados.CriarOldLady(gm)),
            true
        ) {
            @Override
            public void darRecompensaExtra(Jogador jogador, GameManager gm) {
                System.out.println(Cores.AZUL + " Princesa Fiona se juntou à sua equipe!" + Cores.RESET);
                Heroi fiona = Dados.criarFiona(gm);            
                jogador.adicionarHeroiTodos(fiona);
                jogador.getHeroisEscolhidos().add(fiona);
            }
        });
            
        NoMapa n62_golpe = new NoMapa(new EventoRecompensa(
            "Volta para o pântano", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Shrek acha a espada do príncipe encantado.", 
            new CartaDano("Lâmina da Masmorra", "[Custo:1 | Dano: 45]", 1, 45)
        ));

        NoMapa n7_cavaleiroMarreta = new NoMapa(new Batalha(
            "Na floresta",
            Prints.DIALOGO_N7, 
            carregarBoss(Dados.criarHammerSoldier(gm)),
            true
        ));

        n5_masmorra.adicionarCaminho(n51_batalha);
        n5_masmorra.adicionarCaminho(n52_batalha);
        n51_batalha.adicionarCaminho(n61_torre_bruxa);
        n52_batalha.adicionarCaminho(n62_golpe);
        n61_torre_bruxa.adicionarCaminho(n7_cavaleiroMarreta);
        n62_golpe.adicionarCaminho(n7_cavaleiroMarreta);

        // --- FADA E RUMPEL ---
        
        NoMapa n7_loja = new NoMapa(new EventoLoja(
            "Tenda Misteriosa", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Parece de algum conto de fadas...", 
            2
        ));

        NoMapa n81_batalha = new NoMapa(new Batalha(
            "Voltar agora",
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Após derrotar o cavaleiro, Thelonius descobre e envia capangas...\n" + Cores.VERMELHO + Cores.NEGRITO + "Capangas: " + Cores.RESET + "Lá está ele. Atacar!\n",
            carregaInimigoGenerico(gm, () -> Dados.criarGuards(gm), dificuldade),
            false
        ));
            
        NoMapa n82_golpe = new NoMapa(new EventoRecompensa(
            "Descansar um pouco e voltar depois", 
            Cores.NEGRITO + "Narrador: " + Cores.RESET + "Após derrotar o cavaleiro, você pega uma poção.",
            new CartaEscudo("Poção de Defesa", "[Custo:2 | Escudo: 40]", 2, 40)
        ));

        NoMapa n9_thelonius = new NoMapa(new Batalha(
            "Seguir jornada", 
            Prints.DIALOGO_N9, 
            carregarBoss(Dados.criarThelonius(gm)),
            true
        ));
        
        NoMapa n90_golpe = new NoMapa(new EventoRecompensa(
            "Continuar",
            Prints.DIALOGO_N90,
            new CartaDano("Poder da Capa. Hit kill", "[Custo:5 | Dano: 100]", 5, 100)
        ));

        NoMapa n100_rumpel = new NoMapa(new Batalha(
            "I need a hero", 
            Prints.DIALOGO_N100,
            carregarBoss(Dados.criarDragao(gm)),
            true
        ));

        n7_cavaleiroMarreta.adicionarCaminho(n7_loja);
        n7_loja.adicionarCaminho(n81_batalha);
        n7_loja.adicionarCaminho(n82_golpe);
        n81_batalha.adicionarCaminho(n9_thelonius);
        n82_golpe.adicionarCaminho(n9_thelonius);
        n9_thelonius.adicionarCaminho(n90_golpe);
        n90_golpe.adicionarCaminho(n100_rumpel);

        return n1_inicio; 
    }
}