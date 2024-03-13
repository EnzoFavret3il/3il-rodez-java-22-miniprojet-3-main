package Vue;

import javax.swing.*;

import modele.Dictionnaire;
import modele.Sound;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cette classe représente l'interface utilisateur pour le jeu du pendu.
 */
public class interfaceJeu implements affichage {
	private JTextArea penduLabel; 
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
    private JButton definitionButton;
    private JButton devinerButton;
    private JLabel timerLabel; // Ajout du label pour afficher le timer
    private Timer timer; // Ajout du timer

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
        theCadre.setSize(800, 600);
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

        devinerButton = new JButton("Deviner");
        inputPanel.add(devinerButton);

        interfacePrincipal.add(inputPanel, gbc);

        // Toujours initialiser le bouton "Définition"
        gbc.gridy = 3;
        definitionButton = new JButton("Définition");
        interfacePrincipal.add(definitionButton, gbc);

        // Rendre le bouton "Définition" visible ou non en fonction du mode de jeu
        definitionButton.setVisible(!modeDifficile);

        // Ajout du timer en mode difficile
        if (modeDifficile) {
            gbc.gridx = 1;
            timerLabel = new JLabel("Temps restant: 20s");
            interfacePrincipal.add(timerLabel, gbc);
        }

        theCadre.getContentPane().add(interfacePrincipal);
        
     // Ajout du JLabel pour afficher le pendu
        gbc.gridy = 4;
        penduLabel = new JTextArea(7, 11); // 7 lignes, 11 colonnes pour correspondre à la taille du pendu
        penduLabel.setEditable(false); // Empêcher l'édition du pendu
        penduLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); // Utiliser une police monospace pour maintenir l'alignement
        interfacePrincipal.add(new JScrollPane(penduLabel), gbc); // Utiliser un JScrollPane pour afficher le pendu si nécessaire

    }

    @Override
    public void setupActions() {
        Component[] components = ((JPanel) interfacePrincipal.getComponent(2)).getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                devinerButton = (JButton) component;
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
        definitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partieTerminee) {
                    afficherDefinition();
                }
            }
        });
        interfacePrincipal.add(new JButton(new AbstractAction("Afficher lettres proposées") {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherLettresProposees();
            }
        }));

     // Initialisation du timer en mode difficile
        if (modeDifficile) {
            timer = new Timer(1000, new ActionListener() {
                int remainingTime = 20;

                @Override
                public void actionPerformed(ActionEvent e) {
                    remainingTime--;
                    if (remainingTime == 0) {
                        if (!partieTerminee) {
                            vie--; // Retrait d'une vie lorsque le temps est écoulé
                            vieLabel.setText("Vies restantes : " + vie);
                            if (vie > 0) {
                                remainingTime = 20; // Redémarrage du timer si des vies restent
                            }
                            
                        }
                    }
                    timerLabel.setText("Temps restant: " + remainingTime + "s");
                }
            });
            timer.start();
        }

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

    public void afficherDefinition() {
        String definition = dico.getDef(motCache);
        if (definition != null && !definition.isEmpty()) {
            JOptionPane.showMessageDialog(theCadre, "Définition du mot : " + definition);
        } else {
            JOptionPane.showMessageDialog(theCadre, "Aucune définition disponible pour ce mot.");
        }
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

 // Méthode pour la logique de deviner une lettre
    private void devinerLettre() {
        String lettreText = caractereField.getText().toLowerCase(); 
        if (lettreText.length() != 1 || !lettreText.matches("[a-zàéçè-]")) {
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
            Sound lecteurAudio = new Sound("good");
            lecteurAudio.jouer();
        } else {
            vie--;
            mettreAJourPendu(); // Mise à jour du pendu après une erreur
            Sound lecteurAudio = new Sound("bad");
            lecteurAudio.jouer();
            if (vie <= 0) {
                JOptionPane.showMessageDialog(theCadre, "Vous avez perdu! Le mot était : " + motCache);
                partieTerminee = true;
                this.closeUI();
                newGame newGame = new newGame();
            }
        }

        if (motAffiche.indexOf("_") == -1) {
            JOptionPane.showMessageDialog(theCadre, "Félicitations! Vous avez deviné le mot : " + motCache);
            partieTerminee = true;
            this.closeUI();
            newGame newGame = new newGame();
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
    public void afficherLettresProposees() {
        StringBuilder lettresProposees = new StringBuilder();
        for (char lettre : lettresUtilisees) {
            lettresProposees.append(lettre).append(" ");
        }
        JOptionPane.showMessageDialog(theCadre, "Lettres déjà proposées : " + lettresProposees.toString());
    }
    private void mettreAJourPendu() {
        // Définir l'image ou la représentation ASCII du pendu en fonction du nombre d'erreurs
        String[] penduImages = {
        		"   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "  /|\\  |\n" +
        			    "  / \\  |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "  /|\\  |\n" +
        			    "  /    |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "  /|\\  |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "  /|\\  |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "  /|\\  |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "   O   |\n" +
        			    "   |   |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "=========",
        			    
        			    "   +---+\n" +
        			    "   |   |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "       |\n" +
        			    "========="
        };

        // Afficher la représentation correspondante en fonction du nombre d'erreurs
        if (vie <= penduImages.length) {
            penduLabel.setText(penduImages[vie]);
        }
    }
}