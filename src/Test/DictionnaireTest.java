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

        // Test quand mot existe dans dico
        String definition = dictionary.getDef("agenda");
        assertNotNull(definition);
        assertEquals("A book or electronic application used to store data", definition);

        // Test quand mot n'existe pas dans dico
        String nonExistentDefinition = dictionary.getDef("nonexistent");
        assertNull(nonExistentDefinition);
    }

    @Test
    public void testGetTousMots() {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");

        // Test l'intégrité
        String[] allWords = dictionary.getTousMots();
        assertNotNull(allWords);
        assertEquals(5, allWords.length); 
        assertArrayEquals(new String[]{"agenda", "book", "computer", "phone", "tablet"}, allWords);
    }

    @Test
    public void testGetMotetDef() {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");

        // Test si mot existe dans dico avec def
        String[] motetDef = dictionary.getMotetDef("agenda");
        assertNotNull(motetDef);
        assertEquals("agenda", motetDef[0]);
        assertEquals("A book or electronic application used to store data", motetDef[1]);

        // Test si mot existe pas dans dico avec def
        String[] nonExistentMotetDef = dictionary.getMotetDef("nonexistent");
        assertNull(nonExistentMotetDef);
    }
}
