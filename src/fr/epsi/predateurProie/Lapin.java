package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

/**
 * Created by Michael on 31/10/2016.
 */
public class Lapin extends Animal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8465454842358844430L;
	
	protected boolean cache = false;
	protected Terrier terrier;
	protected int compteurTempsCache = 0;

    // Méthodes
    protected Lapin(double _posX, double _posY) {
        PAS = 2;
        posX = _posX;
        posY = _posY;
        vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        normaliser();
    }

    protected void miseAJourDirection(ArrayList<Renard> renards, ArrayList<Terrier> terriers) {
    	List<Renard> renardsProches = new ArrayList<>();
    	renardsProches = renards.stream() //
    			.filter(d -> d.proie == this) //
    			.sorted((r1, r2) -> (DistanceCarre(r1) < DistanceCarre(r2) ? -1 : 1))//
    			.collect(Collectors.toList());
    	Renard predateur = null;
    	if (!renardsProches.isEmpty()) {
    		predateur = renardsProches.get(0);
    	}
    	
    	// sommes-nous chassé ?
    	if (predateur == null) {
    		// pas chassé
    		
    		// je suis caché ? si oui attendre quelques temps avant de sortir
    		if (cache == true) {
    			if (compteurTempsCache < Prairie.DUREE_LAPIN_CACHE*1000/PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE) {
    				compteurTempsCache++;
    			} else {
    				sortirTerrier();
        			cache = false;
        			compteurTempsCache = 0;
    			}
    			
    		} else {
    			PAS = 2;
    			changementDirectionAleatoire();
        		normaliser();
    		}
    	} else {
    		// oui, chassé. je suis caché ?
    		if (cache == false) {
	    		// non, chassé, j'accélère
	    		PAS = 4;
	    		
	    		// quels sont les terriers aux alentours, disponibles ?
	    		List<Terrier> terriersDispos = new ArrayList<>();
	    		terriersDispos = terriers.stream() //
	    				.filter(t -> DistanceCarreTerrier(t) < Prairie.DISTANCE_LAPIN_VUE_TERRIER) //
	    				.sorted((t1, t2) -> (DistanceCarreTerrier(t1) < DistanceCarreTerrier(t2) ? -1 : 1)) //
	    				.collect(Collectors.toList());
	    		Terrier but = null;
	    		if (!terriersDispos.isEmpty()) {
	    			but = terriersDispos.get(0);
	    		}
	    		
	    		// terrier dispo ?
	    		if (but == null) {
	    			// non, fuir le renard
	    			fuirRenard(predateur);
	    		} else {
	    			// oui, terrier plus proche que le renard ?
	    			if (DistanceCarreTerrier(but) < DistanceCarre(predateur)) {
	    				// oui - est-ce que je suis suffisamment proche pour entrer dans le terrier ?
	    				if (DistanceCarreTerrier(but) < Terrier.DISTANCE_LAPIN_ENTREE) {
	    					// rentrer dans le terrier 
	    					rentrerTerrier(but);
	    					cache = true;
	    					terrier = but;
	    				} else {
	    					// pas assez proche pour rentrer, je me dirige vers le terrier
	    					vitesseX = but.posX - posX + 0.001;
	    		            vitesseY = but.posY - posY + 0.001;
	    		            normaliser();
	    				}
	    			} else {
	    				// non, fuir le renard
	    				fuirRenard(predateur);
	    			}
	    		}
	    	}
    	}
    }
    
    protected double DistanceCarreTerrier(Terrier t) {
    	return (t.posX - posX) * (t.posX - posX) + (t.posY - posY) * (t.posY - posY);
    }
    
    protected void sortirTerrier() {
    	this.terrier.retirerLapin(this);
    	vitesseX = Prairie.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Prairie.getInstance().generateur.nextDouble() - 0.5;
        normaliser();
    }
    
    protected void rentrerTerrier(Terrier terrier) {
    	terrier.ajoutLapin(this);
    	// ne plus bouger
		vitesseX = 0;
		vitesseY = 0;
    }
    
    protected void fuirRenard(Renard renard) {
    	vitesseX = renard.vitesseX;
		vitesseY = renard.vitesseY;
		normaliser();
    }
}
