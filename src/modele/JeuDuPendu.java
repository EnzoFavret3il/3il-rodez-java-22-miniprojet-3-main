package modele;

import java.util.ArrayList;
import java.util.Random;

public class JeuDuPendu {
    private String motCache;
    private ArrayList<Character> lettresUtilisees;
    private int vie;

    private Dictionnaire dictionnaire;

    public JeuDuPendu(String fichierMots) {
        dictionnaire = new Dictionnaire(fichierMots);
        initialiserPartie();
    }

    public void initialiserPartie() {
        lettresUtilisees = new ArrayList<>();
        vie = 7;
        motCache = getRandomWord();
    }

    public boolean devinerLettre(char lettre) {
        lettresUtilisees.add(lettre);
        if (motCache.indexOf(lettre) != -1) {
            return true; // La lettre devinée est dans le mot
        } else {
            vie--;
            return false; // La lettre devinée n'est pas dans le mot
        }
    }

    public String getMotCache() {
        return motCache;
    }

    public int getViesRestantes() {
        return vie;
    }

    private String getRandomWord() {
        String[] allWords = dictionnaire.getTousMots();
        Random random = new Random();
        int randomIndex = random.nextInt(allWords.length);
        return allWords[randomIndex];
    }

    public boolean partieTerminee() {
        return vie <= 0 || motCache.equals(getMotAffiche());
    }

    public String getMotAffiche() {
        StringBuilder affichage = new StringBuilder();
        for (int i = 0; i < motCache.length(); i++) {
            char lettre = motCache.charAt(i);
            if (lettresUtilisees.contains(lettre)) {
                affichage.append(lettre);
            } else {
                affichage.append("_");
            }
        }
        return affichage.toString();
    }

    public String getDefinition() {
        return dictionnaire.getDef(motCache);
    }

    public ArrayList<Character> getLettresUtilisees() {
        return lettresUtilisees;
    }
}
