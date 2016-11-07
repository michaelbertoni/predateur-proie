package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 31/10/2016.
 */
public class Renard extends Animal {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5629879751875677151L;

	// Paramètres
    private static double PORTEE_MANGER_PROIE_CARRE = 100;

    // Attributs
    protected Lapin proie;
    private ScheduledFuture<?> mortDeFaim;

    // Méthodes
    protected Renard(double _posX, double _posY) {
        PAS = 2;
        posX = _posX;
        posY = _posY;
        vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        Prairie.getInstance();
		mortDeFaim = Prairie.executor.schedule(deceder, Prairie.DUREE_VIE_RENARD, TimeUnit.SECONDS);
        normaliser();
    }

    protected void miseAJourDirection(ArrayList<Lapin> lapins) {
        ArrayList<Lapin> dansZone = new ArrayList<>();
        dansZone.addAll(lapins);
        dansZone.removeIf(d -> DistanceCarre(d) > Prairie.DISTANCE_VISIBILITE_RENARD);
        dansZone.removeIf(d -> d.cache == true);
        Collections.sort(dansZone, (Lapin l1, Lapin l2) -> (DistanceCarre(l1) < DistanceCarre(l2) ? -1 : 1));
        Lapin but = null;
        // définition du but
        if (!dansZone.isEmpty()) {
            but = dansZone.get(0);
        }

        // avons nous un but ?
        if (but == null) {
        	PAS = 2;
        	changementDirectionAleatoire();
            if (proie != null) {
                proie = null;
            }
        } else {
        	PAS = 3;
        	// on se dirigie vers le lapin
            vitesseX = but.posX - posX + 0.001;
            vitesseY = but.posY - posY + 0.001;

            if (DistanceCarre(but) <= PORTEE_MANGER_PROIE_CARRE) {
                // lapin à portée
                mangerLapin(but);
                mortDeFaim.cancel(true);
                Prairie.getInstance();
				mortDeFaim = Prairie.executor.schedule(deceder, Prairie.DUREE_VIE_RENARD, TimeUnit.SECONDS);
                proie = null;
            } else {
                proie = but;
            }
        }
        normaliser();
    }

    private Runnable deceder = () -> {
        Prairie.getInstance().renards.remove(this);
        System.out.println("Un renard est décédé !");
    };

    private void mangerLapin(Lapin lapin) {
        Prairie.getInstance().lapins.remove(lapin);
        System.out.println("Un lapin s'est fait manger !");
    }
}
