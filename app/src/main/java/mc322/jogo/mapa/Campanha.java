package mc322.jogo.mapa;

import mc322.jogo.Dados;
import mc322.jogo.cartas.CartaDano;
import mc322.jogo.cartas.CartaEscudo;
import mc322.jogo.entidades.Inimigo;
import mc322.jogo.gerenciador.GameManager;
import mc322.jogo.gerenciador.Oponente;
import mc322.jogo.gerenciador.Prints;



/*  Esquemaático do mapa

                                1-inicio
                                1-batalha 
                                10-rumpel
                                10-flores

                  21-burro                                    22-gato
           31-golpe        32-batalha              33-golpe          34-buraco
    41-batalha    42-bar     43-batalha      44-batalha  45-golpe      46-bar

                                5-farquaad
                                5-masmorra

                           51-batalha     52-batalha
                       61 -dragao/fiona     62-golpe
                                  7-encantado

                            81-batalha    82-golpe

                                    9-fada

                                    90-golpe

                                    100-rumpel

*/



/*   Novo  Esquemaático do mapa

                                1-inicio
                                1-batalha (ogre hunter)
                                10- (merry men)
                                10-flores

                  21-burro(guards)                                                                      22-gato(guards)
                      31-golpe             32-batalha(lobo)                                        33-golpe           34-buraco
41-batalha(farquad mascote)   42-bar      43-batalha(farquad mascote)     44-batalha(farquad mascote)  45-golpe       46-bar

                                5-farquaad
                                5-masmorra da velha

                     51-batalha(witch)           52-batalha(hammer soldiesr)
                       61 -old-lady/fiona             62-golpe
                                  7-encantado (hammer soldier)

                      81-batalha(big bad wolf)    82-golpe

                                    9-fada (thelonius)

                                    90-golpe

                                    100-rumpel (dragao)

*/



public class Campanha {

    
    private static Oponente carregarOponente(Inimigo ini) {
        Oponente op = new Oponente();
        op.adicionarInimigoTodos(ini);
        op.gerarInimigos(1); 
        return op;
    }

   

    public static NoMapa criarMapa(GameManager gm) {
        
        
        // 1 - 10  Inicio a flores---------------------------------------------------
        NoMapa n1_inicio = new NoMapa(new EventoMapa(
            "Inicio", 
            Prints.DIALOGO_N1,
            TipoEvento.ESCOLHA_HISTORIA, null
        ));


        NoMapa n1_batalha = new NoMapa(new EventoMapa(
            "Batalha", 
            Prints.VERMELHO + Prints.NEGRITO + "Aldeão: " + Prints.RESET + "Renda-se, monstro! Em nome de Lord Farquaad!\n" +
            Prints.AZUL + Prints.NEGRITO + "Shrek: " + Prints.RESET + "Toma essa!\n", 
            TipoEvento.BATALHA, carregarOponente(Dados.criarAldeao(gm))
        ));


        NoMapa n10_caçador = new NoMapa(new EventoMapa(
            "Acampamento do Caçador", 
            Prints.DIALOGO_N10, 
            TipoEvento.BOSS, carregarOponente(Dados.criarRumpelFraco(gm)) //caçador
        ));


        NoMapa n10_flores = new NoMapa(new EventoMapa(
            "Encruzilhada",
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Rumpel foge machucado, jurando vingança. Shrek avança pela floresta e chega a uma encruzilhada.\n" +
            Prints.AZUL + Prints.NEGRITO + "Shrek: " + Prints.RESET + "Flor azul com espinhos vermelhos... ou flor vermelha com espinhos azuis? Qual caminho eu pego?\n", 
              TipoEvento.ESCOLHA_HISTORIA, null
        ));

        n1_inicio.adicionarCaminho(n1_batalha);
        n1_batalha.adicionarCaminho(n10_caçador);
        n10_caçador.adicionarCaminho(n10_flores);


        // --- CAMINHO ESQUERDO (BURRO) -----------------------------------------------
        NoMapa n21_burro = new NoMapa(new EventoMapa(
            "Flor azul com espinhos vermelhos\n", 
            Prints.DIALOGO_N21,
            TipoEvento.BATALHA, 
            carregarOponente(Dados.criarAldeao(gm)) 
        ));
        
        NoMapa n31_golpe = new NoMapa(new EventoMapa("Caminho bonito", 
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "O Burro não para de falar, mas você acha um pergaminho antigo no chão.",
            TipoEvento.RECOMPENSA_CARTA, null));
        n31_golpe.getEvento().setCartaRecompensa(new CartaDano("Voadora Deslizante", "[Dano: 25]", 2, 25, 0));
        

        NoMapa n32_batalha = new NoMapa(new EventoMapa("Caminho tortuoso", 
            Prints.VERMELHO + Prints.NEGRITO + "Lobo Mau: " + Prints.RESET + "Cuidado por onde anda, ogro...", 
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm)))); //lobo
        
        NoMapa n41_batalha = new NoMapa(new EventoMapa("Floresta densa",
            Prints.NEGRITO + "Narrador:" + Prints.RESET + "Uma emboscada na floresta densa!",
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm))));  //mascote
        
        NoMapa n42_bar = new NoMapa(new EventoMapa("Seguir pelo rio",
            Prints.NEGRITO + "Bartender: " + Prints.RESET + "Bem-vindo ao bar Maçã",
            TipoEvento.DESCANSO_BAR, null));
        
        NoMapa n43_batalha = new NoMapa(new EventoMapa("Passar pela ponte", 
            Prints.VERMELHO + Prints.NEGRITO + "Guarda da Ponte: " + Prints.RESET + "Pedágio cobrado em sangue!",
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm)))); //mascote

        n10_flores.adicionarCaminho(n21_burro);

        n21_burro.adicionarCaminho(n31_golpe);
        n21_burro.adicionarCaminho(n32_batalha);

        n31_golpe.adicionarCaminho(n41_batalha);
        n31_golpe.adicionarCaminho(n42_bar);

        n32_batalha.adicionarCaminho(n43_batalha);


        // --- CAMINHO DIREITO (GATO) -----------------------------------------------------------
        NoMapa n22_gato = new NoMapa(new EventoMapa(
            "Flor vermelha com espinhos azuis\n", 
            Prints.DIALOGO_N22,
            TipoEvento.BATALHA, carregarOponente(Dados.criarAldeao(gm))
        ));


        NoMapa n33_golpe = new NoMapa(new EventoMapa("Tempo na fogueira",
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "O Gato de Botas te ensina uma técnica de evasão.", 
            TipoEvento.RECOMPENSA_CARTA, null));
        n33_golpe.getEvento().setCartaRecompensa(new CartaEscudo("Esquiva Furtiva", "[Escudo: 15]", 1, 15, 1));
        

        NoMapa n34_buraco = new NoMapa(new EventoMapa("Floresta à noite",
            Prints.AZUL + Prints.NEGRITO + "Você: " + Prints.RESET + "Ahh! Quem colocou esse buraco aqui?",
            TipoEvento.ARMADILHA, null));
        

        NoMapa n44_batalha = new NoMapa(new EventoMapa("Cabana do caçador",
            Prints.VERMELHO + Prints.NEGRITO + "Caçador: " + Prints.RESET + "O Lord já está sabendo de suas aventuras! O prêmio pela sua cabeça é alto!", 
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm))));  //mascote
        

        NoMapa n45_golpe = new NoMapa(new EventoMapa("Cabana do caçador", 
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Você saqueia o acampamento do caçador.", 
            TipoEvento.RECOMPENSA_CARTA, null));
        n45_golpe.getEvento().setCartaRecompensa(new CartaDano("Corte Duplo", "[Dano: 25]", 2, 25, 0));
        

        NoMapa n46_bar = new NoMapa(new EventoMapa("Seguindo pela floresta", 
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Você encontra uma panela de ferro. Excelente para proteger a cabeça.", 
            TipoEvento.RECOMPENSA_CARTA, null));

        n10_flores.adicionarCaminho(n22_gato);

        n22_gato.adicionarCaminho(n33_golpe);
        n22_gato.adicionarCaminho(n34_buraco);

        n33_golpe.adicionarCaminho(n44_batalha);
        n33_golpe.adicionarCaminho(n45_golpe);

        n34_buraco.adicionarCaminho(n46_bar);


   
        // 5 FARQUAAD-------------------------------------------------------------------------------

        NoMapa n5_farquaad  = new NoMapa(new EventoMapa(
            "5-Farquaad", 
            Prints.DIALOGO_N5,
            TipoEvento.BOSS, carregarOponente(Dados.criarFarquaad(gm)) ));


        NoMapa n5_masmorra = new NoMapa(new EventoMapa("Proposta",
            Prints.DIALOGO_N50, 
            TipoEvento.ESCOLHA_HISTORIA, null));

        
        n41_batalha.adicionarCaminho(n5_farquaad);
        n42_bar.adicionarCaminho(n5_farquaad);
        n43_batalha.adicionarCaminho(n5_farquaad);
        n44_batalha.adicionarCaminho(n5_farquaad);
        n45_golpe.adicionarCaminho(n5_farquaad);
        n46_bar.adicionarCaminho(n5_farquaad);

        n5_farquaad.adicionarCaminho(n5_masmorra);


        // Até encantado-----------------------------------------------------------------------------------

        NoMapa n51_batalha = new NoMapa(new EventoMapa("Vamos lá",
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "No caminho para a masmorra, Shrek encontra inimigos...", 
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm)))); //witch

        NoMapa n52_batalha = new NoMapa(new EventoMapa("52-Nahh. Passar bem",
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Voltando para o pântano, Shrek encontra inimigos...",
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm))));  //witch
        
        NoMapa n61_torre_bruxa = new NoMapa(new EventoMapa("Torre da Bruxa Velha", 
            Prints.DIALOGO_N61,
            TipoEvento.BOSS, carregarOponente(Dados.criarDragao(gm)))); //bruxa velha
            
        NoMapa n62_golpe = new NoMapa(new EventoMapa("Volta para o pântano", 
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Shrek acha a espada do príncipe encantado.", 
            TipoEvento.RECOMPENSA_CARTA, null));
        n62_golpe.getEvento().setCartaRecompensa(new CartaDano("Lâmina da Masmorra", "[Dano: 35]", 2, 35, 0));


        NoMapa n7_cavaleiroMarreta = new NoMapa(new EventoMapa("Na floresta",
            Prints.DIALOGO_N7, 
            TipoEvento.BOSS, carregarOponente(Dados.criarEncantado(gm)))); //marreta


        n5_masmorra.adicionarCaminho(n51_batalha);
        n5_masmorra.adicionarCaminho(n52_batalha);

        n51_batalha.adicionarCaminho(n61_torre_bruxa);
        n52_batalha.adicionarCaminho(n62_golpe);

        n61_torre_bruxa.adicionarCaminho(n7_cavaleiroMarreta);
        n62_golpe.adicionarCaminho(n7_cavaleiroMarreta);


        //fada e rumpel------------------------------------------------------------------------------------------------------
        
        NoMapa n81_batalha = new NoMapa(new EventoMapa("Voltar agora",
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Após derrotar o cavaleiro, Thelonius descobre e envia capangas...\n" + Prints.VERMELHO + Prints.NEGRITO + "Capangas: " + Prints.RESET + "Lá está ele. Atacar!\n",
            TipoEvento.BATALHA, carregarOponente(Dados.criarLobo(gm)))); //big bad wolf
            
        
        NoMapa n82_golpe = new NoMapa(new EventoMapa("Descansar um pouco e voltar depois", 
            Prints.NEGRITO + "Narrador: " + Prints.RESET + "Após derrotar o cavaleiro, você pega uma poção.",
            TipoEvento.RECOMPENSA_CARTA, null));
        n82_golpe.getEvento().setCartaRecompensa(new CartaEscudo("Poção de Defesa", "[Escudo: 40]", 2, 40, 1));

        NoMapa n9_thelonius = new NoMapa(new EventoMapa("Seguir jornada", 
            Prints.DIALOGO_N9, 
            TipoEvento.BOSS, carregarOponente(Dados.criarFada(gm)))); //thelonius
        
        NoMapa n90_golpe = new NoMapa(new EventoMapa("Continuar",
            Prints.DIALOGO_N90,
            TipoEvento.RECOMPENSA_CARTA, null));
           n90_golpe.getEvento().setCartaRecompensa(new CartaDano("Poder da Capa", "[Custo:2 | Dano: 50]", 3, 50, 0));

        NoMapa n100_rumpel = new NoMapa(new EventoMapa("I need a hero", 
            Prints.DIALOGO_N100,
            TipoEvento.BOSS, carregarOponente(Dados.criarRumpel(gm))));//dragao

        n7_cavaleiroMarreta.adicionarCaminho(n81_batalha);
        n7_cavaleiroMarreta.adicionarCaminho(n82_golpe);

        n81_batalha.adicionarCaminho(n9_thelonius);
        n82_golpe.adicionarCaminho(n9_thelonius);

        n9_thelonius.adicionarCaminho(n90_golpe);
        n90_golpe.adicionarCaminho(n100_rumpel);

        return n1_inicio; 
    }
}