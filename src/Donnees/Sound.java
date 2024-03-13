package Donnees;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private String cheminFichierAudio;

    public Sound(String type) {
        if (type.equals("good")) {
            this.cheminFichierAudio = "src/Sounds/xp.wav"; 
        } else if (type.equals("bad")) {
            this.cheminFichierAudio = "src/Sounds/oof.wav";
        } else {
            throw new IllegalArgumentException("Type de son inconnu : " + type);
        }
    }

    public void jouer() {
        try {
            File fichierAudio = new File(cheminFichierAudio);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fichierAudio);

            Clip lecteurAudio = AudioSystem.getClip();
            lecteurAudio.open(audioInputStream);
            lecteurAudio.start();

            // Attendre que la lecture soit terminée avant de fermer le lecteur
            lecteurAudio.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    lecteurAudio.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
