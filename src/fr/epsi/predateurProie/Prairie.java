package fr.epsi.predateurProie;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Michael on 31/10/2016.
 */
public class Prairie extends Observable{
    // Gestion du singleton
    private static Prairie instance;

    public static Prairie getInstance() {
        if (instance == null) {
            instance = new Prairie();
        }
        return instance;
    }

    public static Prairie getNewInstance() {
        return instance = new Prairie();
    }

    // Timer de l'application (Singleton)
    protected static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    // PARAMETRES DE L'APPLICATION
    public static long FREQUENCE_APPARITION_ANIMAUX_MS = 1000;
    public static int NOMBRE_LAPINS_INITIAL = 15;
    public static int NOMBRE_RENARDS_INITIAL = 10;
    public static double DISTANCE_VISIBILITE_RENARD = 900;
    public static int DUREE_VIE_RENARD = 30;

    // Attributs
    protected Random generateur;
    private double largeur;
    private double hauteur;
    protected ArrayList<Lapin> lapins;
    protected ArrayList<Renard> renards;
    protected ScheduledFuture frequenceApparitionLapin;
    protected ScheduledFuture frequenceApparitionRenard;

    // Méthodes
    private Prairie() {
        lapins = new ArrayList<>();
        renards = new ArrayList<>();
        generateur = new Random();
    }

    protected double getLargeur() { return largeur; }
    protected double getHauteur() { return hauteur; }

    protected void initialiser(int _nbLapins, int _nbRenards, double _largeur, double _hauteur) {
        largeur = _largeur - 20;
        hauteur = _hauteur - 20;
        lapins.clear();
        for (int i = 0; i < _nbLapins; i++) {
        	apparitionLapin.run();
        }
        renards.clear();
        for (int i = 0; i < _nbRenards; i++) {
            apparitionRenard.run();
        }
    }

    protected Runnable miseAJour = () -> {
        for (Lapin lapin : lapins) {
            lapin.miseAJourDirection(renards);
            lapin.miseAJourPosition();
        }
        for (Renard renard : renards) {
            renard.miseAJourDirection(lapins);
            renard.miseAJourPosition();
        }

        ParametresJPanel.getInstance().majCompteurs.run();

        setChanged();
        notifyObservers();
    };
    
    protected Runnable apparitionLapin = () -> {
        Lapin lapin = new Lapin(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur);
        lapins.add(lapin);
    };
    
    protected Runnable apparitionRenard = () -> {
    	Renard renard = new Renard(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur);
        renards.add(renard);
    };
    
}
