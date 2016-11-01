package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 31/10/2016.
 */
public class Renard extends Animal {
    // Paramètres
    protected static double PROBA_CHGT_DIRECTION = 0.05;

    // Attributs
    protected boolean proie;
    protected ScheduledFuture<?> mortDeFaim;

    // Méthodes
    public Renard(double _posX, double _posY) {
        PAS = 3;
        posX = _posX;
        posY = _posY;
        vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        mortDeFaim = Prairie.getInstance().executor.schedule(deceder, Prairie.DUREE_VIE_RENARD, TimeUnit.SECONDS);
        normaliser();
    }

    protected void normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }

    protected void miseAJourDirection(ArrayList<Lapin> lapins) {
        ArrayList<Lapin> dansZone = new ArrayList<>();
        dansZone.addAll(lapins);
        dansZone.removeIf(d -> DistanceCarre(d) > Prairie.DISTANCE_VISIBILITE_RENARD);
        Collections.sort(dansZone, (Lapin l1, Lapin l2) -> (Distance(l1) < Distance(l2) ? -1 : 1));
        Lapin but = null;
        // définition du but
        if (!dansZone.isEmpty()) {
            but = dansZone.get(0);
        }

        // avons nous un but ?
        if (but == null) {
            if (Prairie.getInstance().generateur.nextDouble() < PROBA_CHGT_DIRECTION) {
                vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
                vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
            }
            if (proie && but == null) {
                proie = false;
            }
        } else {
            // on se dirigie vers le lapin
            vitesseX = but.posX - posX;
            vitesseY = but.posY - posY;

            if (DistanceCarre(but) < PAS) {
                // lapin à portée
                Prairie.getInstance().mangerLapin(but);
                mortDeFaim.cancel(true);
                mortDeFaim = Prairie.getInstance().executor.schedule(deceder, Prairie.DUREE_VIE_RENARD, TimeUnit.SECONDS);
                proie = false;
            } else {
                proie = true;
            }
        }
        normaliser();
    }

    public Runnable deceder = () -> {
        Prairie.getInstance().renards.remove(this);
        System.out.println("Un renard est décédé !");
    };
}
