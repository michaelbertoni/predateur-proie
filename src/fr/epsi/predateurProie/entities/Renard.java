	package fr.epsi.predateurProie.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michael on 31/10/2016.
 */
public class Renard extends Animal {

	// Constante
    private final static double PORTEE_MANGER_PROIE_CARRE = 25;

    // Attributs
    private Lapin proie;
    private int mortDeFaim;

    // Méthodes
    public Renard(double _posX, double _posY) {
        setPAS(2);
        setPosX(_posX);
        setPosY(_posY);
        setVitesseX(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
        setVitesseY(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
		mortDeFaim = Prairie.DUREE_VIE_RENARD * 1000 ;
		setMortNaturelle(Prairie.ESPERANCE_VIE_ANIMAUX * 1000);
        normaliser();
    }

    public void miseAJourDirection(ArrayList<Lapin> lapins) {
        ArrayList<Lapin> dansZone = new ArrayList<>();
        dansZone.addAll(lapins);
        dansZone.removeIf(d -> DistanceCarre(d) > Prairie.DISTANCE_VISIBILITE_RENARD);
        dansZone.removeIf(d -> d.isCache() == true);
        Collections.sort(dansZone, (Lapin l1, Lapin l2) -> (DistanceCarre(l1) < DistanceCarre(l2) ? -1 : 1));
        Lapin but = null;
        // définition du but
        if (!dansZone.isEmpty()) {
            but = dansZone.get(0);
        }

        // avons nous un but ?
        if (but == null) {
        	setPAS(2);
        	changementDirectionAleatoire();
            if (proie != null) {
                proie = null;
            }
        } else {
        	// on accélère un peu
        	setPAS(3);
        	// on se dirigie vers le lapin
            setVitesseX(but.getPosX() - getPosX() + 0.001);
            setVitesseY(but.getPosY() - getPosY() + 0.001);

            if (DistanceCarre(but) <= PORTEE_MANGER_PROIE_CARRE) {
                // lapin à portée
                mangerLapin(but);
            } else {
                proie = but;
            }
        }
        normaliser();
    }

    private void mangerLapin(Lapin lapin) {
        Prairie.getInstance().getLapins().remove(lapin);
        System.out.println("Un lapin s'est fait manger !");
        mortDeFaim = Prairie.DUREE_VIE_RENARD * 1000;
        proie = null;
    }
    
    @Override
	public void eviterMur() {
    	if (proie == null) {
    		super.eviterMur();
    	}
    }

	public Lapin getProie() {
		return proie;
	}

	public void setProie(Lapin proie) {
		this.proie = proie;
	}

	public int getMortDeFaim() {
		return mortDeFaim;
	}

	public void setMortDeFaim(int mortDeFaim) {
		this.mortDeFaim = mortDeFaim;
	}
	
}
