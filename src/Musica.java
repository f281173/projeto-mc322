import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {

    private Clip clipBackground; // Clip para a música de fundo que fica em loop

    public void tocarMusica(String nomeArquivo) {
        pararMusica();

        try {
            // PLANO A: Tenta achar a música assumindo que o Java rodou na raiz do projeto
            File arquivoMusica = new File("sons/" + nomeArquivo);

            // PLANO B: Se não achou na raiz, assume que o Java rodou dentro da pasta 'src'
            // ou 'bin' e volta uma pasta (../)
            if (!arquivoMusica.exists()) {
                arquivoMusica = new File("../sons/" + nomeArquivo);
            }

            // Agora sim, com o arquivo encontrado, ele dá o play!
            if (arquivoMusica.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(arquivoMusica);
                clipBackground = AudioSystem.getClip();
                clipBackground.open(audioInput);
                clipBackground.start();
                clipBackground.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println(Prints.VERMELHO + "🎵 [Aviso] Arquivo não encontrado em nenhum diretório: "
                        + nomeArquivo + Prints.RESET);
            }

        } catch (Exception e) {
            System.out.println(Prints.VERMELHO + "🎵 [Erro] Falha ao tocar o áudio." + Prints.RESET);
        }
    }

    public void pararMusica() {
        // Só tenta parar se existir uma música tocando no momento
        if (clipBackground != null && clipBackground.isRunning()) {
            clipBackground.stop();
            clipBackground.close();
        }
    }
}
