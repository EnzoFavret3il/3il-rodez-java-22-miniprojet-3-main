package Interfaces;
import javax.swing.*;

public interface affichage {
	/**Interface de base*/
	
	// Initialisation Graphique
    void initUI();

    //action des composants
    void setupActions();

    // affichage de l'interface graphique
    void displayUI();

    // fermer interface graphique
    void closeUI();
    
    void afficherLettresProposees();
}
