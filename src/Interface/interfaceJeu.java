package Interface;

import dico.Dictionnaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class interfaceJeu implements affichage {
    private JFrame theCadre;
    private JPanel interfacePrincipal;
    private JLabel motmot;

    private Dictionnaire dico;

    public interfaceJeu() {
        dico = new Dictionnaire("mots.txt");

        initUI();
        setupActions();
        displayUI();
    }

    @Override
    public void initUI() {
        theCadre = new JFrame("Jeu de mots");
        theCadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theCadre.setSize(400, 200);
        theCadre.setLocationRelativeTo(null);

        interfacePrincipal = new JPanel();
        interfacePrincipal.setLayout(new BoxLayout(interfacePrincipal, BoxLayout.Y_AXIS));

        motmot = new JLabel("");
        motmot.setAlignmentX(Component.CENTER_ALIGNMENT);
        interfacePrincipal.add(Box.createVerticalGlue());
        interfacePrincipal.add(motmot);
        interfacePrincipal.add(Box.createVerticalGlue());

        JButton newWordButton = new JButton("Nouveau mot");
        newWordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        interfacePrincipal.add(newWordButton);

        theCadre.getContentPane().add(interfacePrincipal, BorderLayout.CENTER);
    }

    @Override
    public void setupActions() {
        JButton newWordButton = (JButton) interfacePrincipal.getComponent(2);
        newWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String randomWord = getRandomWord();
                motmot.setText(randomWord);
            }
        });
    }

    @Override
    public void displayUI() {
        theCadre.setVisible(true);
    }

    @Override
    public void closeUI() {
        theCadre.dispose();
    }

    private String getRandomWord() {
        String[] allWords = dico.getAllWords();
        Random random = new Random();
        int randomIndex = random.nextInt(allWords.length);
        return allWords[randomIndex];
    }

    public static void main(String[] args) {
        new interfaceJeu();
    }
}
