package Interface;
import javax.swing.*;
import java.awt.*;

public class interfaceBegin implements affichage{
	private JFrame inter;
 
	@Override
    public void initUI() {
        inter = new JFrame("Jeu");
        inter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inter.setSize(300, 200);

        // Bouton Jouer
        JButton playButton = new JButton("Jouer");
        // Bouton centrer
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Ajout BTN au conteneur
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue()); 
        panel.add(playButton);
        panel.add(Box.createVerticalGlue());
        inter.getContentPane().add(panel, BorderLayout.CENTER);
        
    }

	@Override
	public void setupActions() {
		// TODO Auto-generated method stub
		
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
