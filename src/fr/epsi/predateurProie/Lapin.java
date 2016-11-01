package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 31/10/2016.
 */
public class Lapin extends Animal {
    // Constantes
    private final static double PROB_CHGT_DIRECTION = 0.05;

    // Méthodes
    protected Lapin(double _posX, double _posY) {
        PAS = 3;
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
        Collections.sort(repereParRenard, (Renard r1, Renard r2) -> (Distance(r1) < Distance(r2) ? -1 : 1));

        if(repereParRenard.isEmpty()) {
            PAS = 3;
        } else {
            // accélérer !
            PAS = 6;

            // fuir le renard le plus proche
            vitesseX = repereParRenard.get(0).posX + posX;
            vitesseY = repereParRenard.get(0).posY + posY;

            normaliser();
        }


        if (Prairie.getInstance().generateur.nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
            normaliser();
        }
    }
}