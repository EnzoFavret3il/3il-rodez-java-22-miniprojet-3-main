package dico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionnaire {
    private Map<String, String> lesmots;
    private List<String> tousLesMots;

    public Dictionnaire(String filename) {
        lesmots = new HashMap<>();
        tousLesMots = new ArrayList<>(); // Initialisation de la liste
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
                    tousLesMots.add(word); // Ajouter le mot à la liste
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDef(String word) {
        return lesmots.get(word);
    }

    public String[] getTousMots() {
        return tousLesMots.toArray(new String[0]);
    }

    public String[] getMotetDef(String word) {
        String[] result = new String[2];
        result[0] = word;
        result[1] = lesmots.get(word);
        return result;
    }

    public static void main(String[] args) {
        Dictionnaire dictionary = new Dictionnaire("mots.txt");
        String word = "agenda";
        String definition = dictionary.getDef(word);
        if (definition != null) {
            System.out.println("Définition de \"" + word + "\": " + definition);
        } else {
            System.out.println("Le mot \"" + word + "\" n'a pas été trouvé dans le dictionnaire.");
        }

        String[] allWords = dictionary.getTousMots();
        for (String w : allWords) {
            System.out.println(w);
        }
    }
}
