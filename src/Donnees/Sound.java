package Donnees;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private String cheminFichierAudio;

    /**Constructeur du type son
     * @param type => type de son que l'on souhaite données*/
    public Sound(String type) {
        if (type.equals("good")) {
            this.setCheminFichierAudio("src/Sounds/xp.wav"); 
        } else if (type.equals("bad")) {
            this.setCheminFichierAudio("src/Sounds/oof.wav");
        } else {
            throw new IllegalArgumentException("Type de son inconnu : " + type);
        }
    }

    public void jouer() {
        try {
            File fichierAudio = new File(getCheminFichierAudio());
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

	public String getCheminFichierAudio() {
		return cheminFichierAudio;
	}

	public void setCheminFichierAudio(String cheminFichierAudio) {
		this.cheminFichierAudio = cheminFichierAudio;
	}

}
