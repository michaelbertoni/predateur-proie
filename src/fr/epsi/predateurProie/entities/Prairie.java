package fr.epsi.predateurProie.entities;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import fr.epsi.predateurProie.Application;
import fr.epsi.predateurProie.components.PredateurProieJPanel;

/**
 * Created by Michael on 31/10/2016.
 */
public class Prairie extends Observable {

	// Singleton
    private static Prairie instance;

    public static Prairie getInstance() {
        if (instance == null) {
            instance = new Prairie();
        }
        return instance;
    }

    // Timer de l'application (Singleton)
    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
    // Générateur de valeurs
    private static Random generateur = new Random();

    // PARAMETRES DE L'APPLICATION
    public static long FREQUENCE_APPARITION_ANIMAUX_MS = 1000;
    public static int NOMBRE_LAPINS_INITIAL = 15;
    public static int NOMBRE_RENARDS_INITIAL = 20;
    public static double DISTANCE_VISIBILITE_RENARD = 2500;
    public static int ESPERANCE_VIE_ANIMAUX = 120;
    public static int DUREE_VIE_RENARD = 30;
    public static int DUREE_LAPIN_CACHE = 1000;
	public static double DISTANCE_LAPIN_VUE_TERRIER = 90000;

    // Attributs
    private double largeur;
    private double hauteur;
    private ArrayList<Lapin> lapins;
    private ArrayList<Renard> renards;
    private ArrayList<Terrier> terriers;
    private ScheduledFuture<?> frequenceApparitionLapin;
    private ScheduledFuture<?> frequenceApparitionRenard;
    
    // Méthodes
    private Prairie() {
        lapins = new ArrayList<>();
        renards = new ArrayList<>();
        terriers = new ArrayList<>();
        generateur = new Random();
    }

    public void reinitialiserPrairie() {
        terriers.clear();
        frequenceApparitionLapin.cancel(true);
        frequenceApparitionRenard.cancel(true);
        executor.shutdown();
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public double getLargeur() { return largeur; }
    public double getHauteur() { return hauteur; }

    public void initialiser(int _nbLapins, int _nbRenards, double _largeur, double _hauteur) {
        largeur = _largeur - 20;
        hauteur = _hauteur - 20;
        lapins.clear();
        for (int i = 0; i < _nbLapins; i++) {
        	creerLapin();
        }
        renards.clear();
        for (int i = 0; i < _nbRenards; i++) {
            creerRenard();
        }
    }

    public Runnable miseAJour = () -> {
        for (int i = 0; i < lapins.size(); i++) {
        	if (lapins.get(i).getMortNaturelle() > 0) {
        		lapins.get(i).miseAJourDirection(renards, terriers);
        		lapins.get(i).miseAJourPosition();
        		lapins.get(i).setMortNaturelle(lapins.get(i).getMortNaturelle() - PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE);
        	} else {
        		mortLapin(lapins.get(i));
        	}
        }
        for (int i = 0; i < renards.size(); i++) {
        	if (renards.get(i).getMortDeFaim() > 0 && renards.get(i).getMortNaturelle() > 0) {
        		renards.get(i).miseAJourDirection(lapins);
        		renards.get(i).miseAJourPosition();
        		renards.get(i).setMortDeFaim(renards.get(i).getMortDeFaim() - PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE);
        		renards.get(i).setMortNaturelle(renards.get(i).getMortNaturelle() - PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE);
        	} else {
        		mortRenard(renards.get(i));
        	}
        }

        Application.majCompteurs.run();

        setChanged();
        notifyObservers();
    };
    
    private void mortRenard(Renard renard) {
    	Prairie.getInstance().getRenards().remove(renard);
    	if (renard.getMortDeFaim() <= 0 && renard.getMortNaturelle() > 0) {
    		System.out.println("Un renard est mort de faim !");
		} else {
			System.out.println("Un renard est décédé de mort naturelle !");
		}
    };
    
    private void mortLapin(Lapin lapin) {
    	Prairie.getInstance().getLapins().remove(lapin);
    	System.out.println("Un lapin est décédé de mort naturelle !");
    }
    
	public Runnable apparitionLapin = () -> {
        if (lapins.size() > 1) {
        	creerLapin();
        }
    };
    
    public Runnable apparitionRenard = () -> {
    	if (renards.size() > 1) {
    		creerRenard();
    	}
    };
    
    private void creerLapin() {
    	Lapin lapin = new Lapin(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur);
		lapins.add(lapin);
    }
    
    private void creerRenard() {
    	Renard renard = new Renard(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur);
        renards.add(renard);
    }
    
    public void creerTerrier(Double posX, Double posY) {
    	this.terriers.add(new Terrier(posX, posY));
    }
    
    

	public static ScheduledExecutorService getExecutor() {
		return executor;
	}
	
	public static void getNewExecutor() {
		executor = Executors.newSingleThreadScheduledExecutor();
	}

	public Random getGenerateur() {
		return generateur;
	}

	public ArrayList<Lapin> getLapins() {
		return lapins;
	}

	public void setLapins(ArrayList<Lapin> lapins) {
		this.lapins = lapins;
	}

	public ArrayList<Renard> getRenards() {
		return renards;
	}

	public void setRenards(ArrayList<Renard> renards) {
		this.renards = renards;
	}

	public ArrayList<Terrier> getTerriers() {
		return terriers;
	}

	public void setTerriers(ArrayList<Terrier> terriers) {
		this.terriers = terriers;
	}

	public ScheduledFuture<?> getFrequenceApparitionLapin() {
		return frequenceApparitionLapin;
	}

	public void setFrequenceApparitionLapin(ScheduledFuture<?> frequenceApparitionLapin) {
		this.frequenceApparitionLapin = frequenceApparitionLapin;
	}

	public ScheduledFuture<?> getFrequenceApparitionRenard() {
		return frequenceApparitionRenard;
	}

	public void setFrequenceApparitionRenard(ScheduledFuture<?> frequenceApparitionRenard) {
		this.frequenceApparitionRenard = frequenceApparitionRenard;
	}

	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}
    
}
