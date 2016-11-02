package fr.epsi.predateurProie;

/**
 * Created by Michael on 31/10/2016.
 */
public class Animal {
	// Constantes
    private final static double PROB_CHGT_DIRECTION = 0.05;
	
    protected double posX;
    protected double posY;
    protected double PAS;
    protected double vitesseX;
    protected double vitesseY;

    protected Animal() {}

    protected void normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }

    protected double Distance(Animal o) {
        return Math.sqrt((o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY));
    }

    protected double DistanceCarre(Animal o) {
        return (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
    }

    protected void miseAJourPosition() {
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
        double largeur = Prairie.getInstance().getLargeur();
        double hauteur = Prairie.getInstance().getHauteur();
        if (posX < 0) {
            posX = 0;
        }
        else if (posX > largeur) {
            posX = largeur;
        }
        if (posY < 0) {
            posY = 0;
        }
        else if (posY > hauteur) {
            posY = hauteur;
        }
    }
    
    protected void changementDirectionAleatoire() {
    	// Changement de direction aléatoire
        if (Prairie.getInstance().generateur.nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        }
        // change la direction du renard s'il arrive sur un bord du panel
        if( posY == 0
        		|| posX == 0 
        		|| posX >= PredateurProieJPanel.getInstance().getWidth() - 20
        		|| posY >= PredateurProieJPanel.getInstance().getHeight() - 20) {
        	vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        }
        normaliser();
    }
}
