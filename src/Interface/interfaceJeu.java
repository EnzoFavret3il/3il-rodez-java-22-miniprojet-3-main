package Interface;

import dico.Dictionnaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe représente l'interface utilisateur pour le jeu du pendu.
 */
public class interfaceJeu implements affichage {

    private int vie = 7;
    private String motCache;
    private StringBuilder motAffiche;
    private ArrayList<Character> lettresUtilisees;
    private boolean partieTerminee;
    private boolean modeDifficile;

    private JFrame theCadre;
    private JPanel interfacePrincipal;
    private JLabel motLabel;
    private JLabel vieLabel;
    private JTextField caractereField;
    private Dictionnaire dico;

    /**
     * Constructeur de l'interface du jeu du pendu.
     *
     * @param difficulté La difficulté du jeu ("Facile" ou "Difficile").
     */
    public interfaceJeu(String difficulté) {
        dico = new Dictionnaire("mots.txt");
        modeDifficile = difficulté.equalsIgnoreCase("Difficile");

        initUI();
        setupActions();
        displayUI();
    }

    @Override
    public void initUI() {
        theCadre = new JFrame("Le pendu");
        theCadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theCadre.setSize(800, 400);
        theCadre.setLocationRelativeTo(null);

        interfacePrincipal = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        motLabel = new JLabel("");
        interfacePrincipal.add(motLabel, gbc);

        gbc.gridy = 1;
        vieLabel = new JLabel("Vies restantes : " + vie);
        interfacePrincipal.add(vieLabel, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel inputPanel = new JPanel();
        caractereField = new JTextField(1);
        inputPanel.add(caractereField);

        JButton devinerButton = new JButton("Deviner");
        inputPanel.add(devinerButton);

        interfacePrincipal.add(inputPanel, gbc);

        if (!modeDifficile) {
            gbc.gridy = 3;
            JButton definitionButton = new JButton("Définition");
            interfacePrincipal.add(definitionButton, gbc);
        }

        theCadre.getContentPane().add(interfacePrincipal);
    }


    @Override
    public void setupActions() {
        Component[] components = ((JPanel) interfacePrincipal.getComponent(2)).getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton devinerButton = (JButton) component;
                devinerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!partieTerminee) {
                            devinerLettre();
                        }
                    }
                });
            }
        }

        caractereField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partieTerminee) {
                    devinerLettre();
                }
            }
        });
    }

    @Override
    public void displayUI() {
        theCadre.setVisible(true);
        initialiserPartie();
    }

    @Override
    public void closeUI() {
        theCadre.dispose();
    }

    private void initialiserPartie() {
        lettresUtilisees = new ArrayList<>();
        partieTerminee = false;
        vie = 7;
        motCache = getRandomWord();
        motAffiche = new StringBuilder();
        for (int i = 0; i < motCache.length(); i++) {
            motAffiche.append("_");
        }
        mettreAJourMotAffiche();
    }

    private void devinerLettre() {
        String lettreText = caractereField.getText().toLowerCase(); // Convertir la lettre en minuscule pour simplifier la vérification
        if (lettreText.length() != 1 || !lettreText.matches("[a-zàéçè-]")) { // Vérifier si la lettre est une lettre de l'alphabet ou un des caractères spéciaux
            JOptionPane.showMessageDialog(theCadre, "Veuillez saisir une lettre de l'alphabet ou un des caractères spéciaux autorisés (à, é, ç, è, -).");
            return;
        }

        char lettre = lettreText.charAt(0);

        if (lettresUtilisees.contains(lettre)) {
            JOptionPane.showMessageDialog(theCadre, "Vous avez déjà deviné cette lettre!");
            return;
        }

        lettresUtilisees.add(lettre);
        if (motCache.indexOf(lettre) != -1) {
            mettreAJourMotAffiche();
        } else {
            vie--;
            if (vie <= 0) {
                JOptionPane.showMessageDialog(theCadre, "Vous avez perdu! Le mot était : " + motCache);
                partieTerminee = true;
            }
        }

        if (motAffiche.indexOf("_") == -1) {
            JOptionPane.showMessageDialog(theCadre, "Félicitations! Vous avez deviné le mot : " + motCache);
            partieTerminee = true;
        }

        caractereField.setText("");
        vieLabel.setText("Vies restantes : " + vie);
    }


    private void mettreAJourMotAffiche() {
        StringBuilder affichage = new StringBuilder();
        for (int i = 0; i < motCache.length(); i++) {
            char lettre = motCache.charAt(i);
            if (lettresUtilisees.contains(lettre)) {
                affichage.append(lettre);
            } else {
                affichage.append("_");
            }
        }
        motAffiche = affichage;
        motLabel.setText(motAffiche.toString());
    }

    private String getRandomWord() {
        String[] allWords = dico.getTousMots();
        Random random = new Random();
        int randomIndex = random.nextInt(allWords.length);
        return allWords[randomIndex];
    }

    /**
     * Méthode principale pour lancer le jeu du pendu.
     *
     * @param args Les arguments de ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        // Mode facile
        new interfaceJeu("Facile");
        
        // Mode difficile
        // new interfaceJeu("Difficile");
    }
}
