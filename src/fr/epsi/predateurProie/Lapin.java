package fr.epsi.predateurProie;

/**
 * Created by Michael on 31/10/2016.
 */
public class Lapin extends Animal {
    // Constantes
    protected final static double PROB_CHGT_DIRECTION = 0.05;

    // Méthodes
    public Lapin(double _posX, double _posY) {
        PAS = 3;
        posX = _posX;
        posY = _posY;
        vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        normaliser();
    }

    protected void normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }

    protected void miseAJourDirection() {
        if (Prairie.getInstance().generateur.nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
            normaliser();
        }
    }
}
