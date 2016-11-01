package fr.epsi.predateurProie;

/**
 * Created by Michael on 31/10/2016.
 */
public class Animal {
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
}
