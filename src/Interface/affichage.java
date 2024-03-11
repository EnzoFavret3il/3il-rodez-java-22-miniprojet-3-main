package Interface;
import javax.swing.*;

public interface affichage {
	// Initialisation Graphique
    void initUI();

    //action des composants
    void setupActions();

    // affichage de l'interface graphique
    void displayUI();

    // fermer interface graphique
    void closeUI();
}
