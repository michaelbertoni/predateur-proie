package fr.epsi.predateurProie;

import javax.swing.*;

/**
 * Created by Michael on 31/10/2016.
 */
public class Application {
    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Proie - Prédateur");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        JPanel panel = new JPanel();
        fenetre.setContentPane(panel);
        fenetre.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        // Création du contenu
        PredateurProieJPanel predateurProidPanel = PredateurProieJPanel.getInstance();
        panel.add(predateurProidPanel);
        ParametresJPanel parametrePanel = ParametresJPanel.getInstance();
        panel.add(parametrePanel);

        // Affichage
        fenetre.setVisible(true);
        predateurProidPanel.lancer();
    }
}
