package fr.epsi.predateurProie.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.epsi.predateurProie.components.PredateurProieJPanel;

/**
 * Created by Michael on 31/10/2016.
 */
public class Lapin extends Animal {
	
	private boolean cache = false;
	private Terrier terrier;
	private int compteurTempsCache = 0;

    // Méthodes
    public Lapin(double _posX, double _posY) {
        setPAS(2);
        setPosX(_posX);
        setPosY(_posY);
        setVitesseX(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
        setVitesseY(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
		setMortNaturelle(Prairie.ESPERANCE_VIE_ANIMAUX * 1000);
        normaliser();
    }

    public void miseAJourDirection(ArrayList<Renard> renards, ArrayList<Terrier> terriers) {
    	List<Renard> renardsProches = new ArrayList<>();
    	renardsProches = renards.stream() //
    			.filter(d -> d.getProie() == this) //
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
    			if (compteurTempsCache < Prairie.DUREE_LAPIN_CACHE) {
    				compteurTempsCache += PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE;
    			} else {
    				sortirTerrier();
        			cache = false;
        			compteurTempsCache = 0;
    			}
    			
    		} else {
    			setPAS(2);
    			changementDirectionAleatoire();
        		normaliser();
    		}
    	} else {
    		// oui, chassé. je suis caché ?
    		if (cache == false) {
	    		// non, chassé, j'accélère
	    		setPAS(4);
	    		
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
	    					setVitesseX(but.getPosX() - getPosX() + 0.001);
	    		            setVitesseY(but.getPosY() - getPosY() + 0.001);
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
    
    public double DistanceCarreTerrier(Terrier t) {
    	return (t.getPosX() - getPosX()) * (t.getPosX() - getPosX()) + (t.getPosY() - getPosY()) * (t.getPosY() - getPosY());
    }
    
    public void sortirTerrier() {
    	terrier.retirerLapin(this);
    	setVitesseX(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
        setVitesseY(Prairie.getInstance().getGenerateur().nextDouble() - 0.5);
        normaliser();
    }
    
    public void rentrerTerrier(Terrier terrier) {
    	terrier.ajoutLapin(this);
    	// ne plus bouger
		setVitesseX(0);
		setVitesseY(0);
    }
    
    public void fuirRenard(Renard renard) {
    	setVitesseX(renard.getVitesseX());
		setVitesseY(renard.getVitesseY());
		normaliser();
    }
    
    @Override
    public void eviterMur() {
    	if (cache == false) {
    		super.eviterMur();
    	}
    }

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	public Terrier getTerrier() {
		return terrier;
	}

	public void setTerrier(Terrier terrier) {
		this.terrier = terrier;
	}

	public int getCompteurTempsCache() {
		return compteurTempsCache;
	}

	public void setCompteurTempsCache(int compteurTempsCache) {
		this.compteurTempsCache = compteurTempsCache;
	}
    
}
