package Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import Donnees.Dictionnaire;

public class DictionnaireTest {

    @Test
    public void testGetDef() {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");

        // Test when the word exists in the dictionary
        String definition = dictionary.getDef("agenda");
        assertNotNull(definition);
        assertEquals("A book or electronic application used to store data", definition);

        // Test when the word doesn't exist in the dictionary
        String nonExistentDefinition = dictionary.getDef("nonexistent");
        assertNull(nonExistentDefinition);
    }

    @Test
    public void testGetTousMots() {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");

        // Test the correctness of the list of all words
        String[] allWords = dictionary.getTousMots();
        assertNotNull(allWords);
        assertEquals(5, allWords.length); // Assuming mots.txt contains 5 words
        assertArrayEquals(new String[]{"agenda", "book", "computer", "phone", "tablet"}, allWords);
    }

    @Test
    public void testGetMotetDef() {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");

        // Test when the word exists in the dictionary
        String[] motetDef = dictionary.getMotetDef("agenda");
        assertNotNull(motetDef);
        assertEquals("agenda", motetDef[0]);
        assertEquals("A book or electronic application used to store data", motetDef[1]);

        // Test when the word doesn't exist in the dictionary
        String[] nonExistentMotetDef = dictionary.getMotetDef("nonexistent");
        assertNull(nonExistentMotetDef);
    }
}
