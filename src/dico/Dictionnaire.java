package dico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionnaire {
    private Map<String, String> lesmots;

    public Dictionnaire(String filename) {
        lesmots = new HashMap<>();
        loadFromFile(filename);
    }

    private void loadFromFile(String filename) {
        try {
            File file = new File(filename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ", 2); // Séparer le mot de sa définition
                if (parts.length >= 2) {
                    String word = parts[0];
                    String definition = parts[1];
                    lesmots.put(word, definition);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDefinition(String word) {
        return lesmots.get(word);
    }
    
    public String[] getWordAndDefinition(String word) {
        String[] result = new String[2];
        result[0] = word;
        result[1] = lesmots.get(word);
        return result;
    }

    public static void main(String[] args) {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");
        String word = "agenda";
        String definition = dictionary.getDefinition(word);
        if (definition != null) {
            System.out.println("Définition de \"" + word + "\": " + definition);
        } else {
            System.out.println("Le mot \"" + word + "\" n'a pas été trouvé dans le dictionnaire.");
        }
        
        // Exemple d'utilisation du getter pour obtenir le mot et la définition ensemble
        String[] wordAndDefinition = dictionary.getWordAndDefinition(word);
        System.out.println("Mot: " + wordAndDefinition[0] + ", Définition: " + wordAndDefinition[1]);
    }
}
