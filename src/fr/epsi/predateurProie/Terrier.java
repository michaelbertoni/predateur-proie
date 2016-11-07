package fr.epsi.predateurProie;

import java.util.ArrayList;

public class Terrier {
	
	protected final static double DISTANCE_LAPIN_ENTREE = 100;
	protected final static double DISTANCE_LAPIN_VUE = 100000;
	
	protected double posX;
    protected double posY;
    protected ArrayList<Lapin> lapins;
    
    protected Terrier(double posX, double posY) {
    	this.posX = posX;
    	this.posY = posY;
    	this.lapins = new ArrayList<>();
    }
    
    protected void ajoutLapin(Lapin lapin) {
    	lapins.add(lapin);
    }
    
    protected void retirerLapin(Lapin lapin) {
    	lapins.remove(lapin);
    }

}
