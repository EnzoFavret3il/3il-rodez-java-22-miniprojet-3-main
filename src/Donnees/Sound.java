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
            this.cheminFichierAudio = "src/Sounds/xp.wav"; 
        } else if (type.equals("bad")) {
            this.cheminFichierAudio = "src/Sounds/oof.wav";
        } else {
            throw new IllegalArgumentException("Type de son inconnu : " + type);
        }
    }
}
