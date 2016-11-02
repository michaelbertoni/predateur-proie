package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 31/10/2016.
 */
public class Lapin extends Animal {

    // Méthodes
    protected Lapin(double _posX, double _posY) {
        PAS = 2;
        posX = _posX;
        posY = _posY;
        vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        normaliser();
    }

    protected void miseAJourDirection(ArrayList<Renard> renards) {
        ArrayList<Renard> repereParRenard = new ArrayList<>();
        repereParRenard.addAll(renards);
        repereParRenard.removeIf(d -> d.proie != this);
        Collections.sort(repereParRenard, (Renard r1, Renard r2) -> (DistanceCarre(r1) < DistanceCarre(r2) ? -1 : 1));

        if(repereParRenard.isEmpty()) {
            PAS = 2;
            changementDirectionAleatoire();
        } else {
            // accélérer !
            PAS = 4;

            // fuir le renard le plus proche (prend la même direction que le renard)
            vitesseX = repereParRenard.get(0).vitesseX;
            vitesseY = repereParRenard.get(0).vitesseY;

            normaliser();
        }
    }
}
