package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newGame extends JFrame implements affichage {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
    private JButton rejouerButton;
    private JButton fermerButton;

    public newGame() {
        initUI();
        setupActions();
        displayUI();
    }

    @Override
    public void initUI() {
        frame = new JFrame("Nouvelle Partie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1));

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Souhaitez-vous rejouer ?");
        panel.add(label);

        rejouerButton = new JButton("Rejouer");
        fermerButton = new JButton("Fermer");

        panel.add(rejouerButton);
        panel.add(fermerButton);

        frame.getContentPane().add(panel);
    }

    @Override
    public void setupActions() {
        rejouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                interfaceBegin inter=new interfaceBegin();
                closeUI();
            }
        });

        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                closeUI();
            }
        });
    }

    @Override
    public void displayUI() {
        frame.setVisible(true);
    }

    @Override
    public void closeUI() {
        frame.dispose();
    }

	@Override
	public void afficherLettresProposees() {
		// TODO Auto-generated method stub
		
	}
}
