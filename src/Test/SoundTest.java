package Test;

import org.junit.jupiter.api.Test;

import Donnees.Sound;

import static org.junit.jupiter.api.Assertions.*;

public class SoundTest {

    @Test
    public void testConstructor() {
        // Test when type is "good"
        Sound goodSound = new Sound("good");
        assertNotNull(goodSound);
        assertEquals("src/Sounds/xp.wav", goodSound.getCheminFichierAudio());

        // Test when type is "bad"
        Sound badSound = new Sound("bad");
        assertNotNull(badSound);
        assertEquals("src/Sounds/oof.wav", badSound.getCheminFichierAudio());

        // Test when type is unknown
        assertThrows(IllegalArgumentException.class, () -> {
            new Sound("unknown");
        });
    }

    // This test assumes that the audio files "xp.wav" and "oof.wav" exist in the specified paths
    @Test
    public void testJouer() {
        // Testing "good" sound
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
