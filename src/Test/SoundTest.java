package Test;

import org.junit.jupiter.api.Test;

import Donnees.Sound;

import static org.junit.jupiter.api.Assertions.*;

public class SoundTest {

    @Test
    public void testConstructor() {
        // Test quand 'good'
        Sound goodSound = new Sound("good");
        assertNotNull(goodSound);
        assertEquals("src/Sounds/xp.wav", goodSound.getCheminFichierAudio());

        // Test quand 'bad'
        Sound badSound = new Sound("bad");
        assertNotNull(badSound);
        assertEquals("src/Sounds/oof.wav", badSound.getCheminFichierAudio());

        // Test quand inconnue
        assertThrows(IllegalArgumentException.class, () -> {
            new Sound("unknown");
        });
    }

    
    @Test
    public void testJouer() {
        // Test du son
        Sound goodSound = new Sound("good");
        assertDoesNotThrow(() -> {
            goodSound.jouer();
        });

        // Testing "bad" sound
        Sound badSound = new Sound("bad");
        assertDoesNotThrow(() -> {
            badSound.jouer();
        });
    }
}
