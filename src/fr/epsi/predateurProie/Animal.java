package fr.epsi.predateurProie;

/**
 * Created by Michael on 31/10/2016.
 */
public class Animal {
    public double posX;
    public double posY;
    public double PAS;
    protected double vitesseX;
    protected double vitesseY;

    public Animal() {}
    public Animal(double _x, double _y) {
        posX = _x;
        posY = _y;
    }

    public double Distance(Animal o) {
        return Math.sqrt((o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY));
    }

    public double DistanceCarre(Animal o) {
        double distanceCarre = (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
        return distanceCarre;
    }

    public void miseAJourPosition() {
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
}
