package Vue;
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
        inter = new JFrame("Le Pendu");
        inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inter.setSize(400, 200);
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
            	inter.setTitle("Le Pendu : Choix Difficulté");
                JButton ezMode = new JButton("Facile");
                JButton darkSoulMode = new JButton("Difficile");
                JPanel buttonPanel = new JPanel(new FlowLayout());
                buttonPanel.add(ezMode);
                buttonPanel.add(darkSoulMode);
                myMain.remove(playButton);
                myMain.add(buttonPanel, 1);
                
                // Action listener  bouton "Facile"
                ezMode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        interfaceJeu interJeu= new interfaceJeu("Facile");
                        closeUI();
                    }
                });
                
                // Action listener pour "Difficile"
                darkSoulMode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	interfaceJeu interJeu= new interfaceJeu("Difficile");
                    	closeUI();
                    }
                });
                
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

	@Override
	public void afficherLettresProposees() {
		// TODO Auto-generated method stub
		
	}
}
