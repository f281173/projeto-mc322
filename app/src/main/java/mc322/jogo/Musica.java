package mc322.jogo;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {

    private Clip clipBackground; 

    public void tocarMusica(String nomeArquivo) {
        pararMusica();

        try {

            URL urlMusica = getClass().getResource("/sons/" + nomeArquivo);


            if (urlMusica != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(urlMusica);
                clipBackground = AudioSystem.getClip();
                clipBackground.open(audioInput);
                clipBackground.start();
                clipBackground.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println(Cores.VERMELHO + "🎵 [Aviso] Arquivo não encontrado na pasta resources: /sons/"
                        + nomeArquivo + Cores.RESET);
            }

        } catch (Exception e) {
            System.out.println(Cores.VERMELHO + "🎵 [Erro] Falha ao tocar o áudio." + Cores.RESET);
            e.printStackTrace(); 
        }
    }

    public void pararMusica() {
        if (clipBackground != null && clipBackground.isRunning()) {
            clipBackground.stop();
            clipBackground.close();
        }
    }
}