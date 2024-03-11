package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaceBegin implements affichage {
    private JFrame inter;
    private JPanel myMain; //my man ?.??

    public interfaceBegin() {
        initUI();
        setupActions();
        displayUI();
    }

    @Override
    public void initUI() {
        inter = new JFrame("Jeu");
        inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inter.setSize(300, 200);
        inter.setLocationRelativeTo(null);

        myMain = new JPanel();
        myMain.setLayout(new BoxLayout(myMain, BoxLayout.Y_AXIS));

        // Bouton Jouer
        JButton playButton = new JButton("Jouer");
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        myMain.add(Box.createVerticalGlue());
        myMain.add(playButton);
        myMain.add(Box.createVerticalGlue());

        inter.getContentPane().add(myMain, BorderLayout.CENTER);
    }

    @Override
    public void setupActions() {
        JButton playButton = (JButton) myMain.getComponent(1);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton easyButton = new JButton("Facile");
                JButton hardButton = new JButton("Difficile");
                JPanel buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(easyButton);
                buttonPanel.add(hardButton);
                myMain.remove(playButton);
                myMain.add(buttonPanel, 1);
                inter.revalidate();
            }
        });
    }

    @Override
    public void displayUI() {
        inter.setVisible(true);
    }

    @Override
    public void closeUI() {
        inter.dispose();
    }
}