package fr.epsi.predateurProie;

import java.util.ArrayList;

public class Terrier {
	
	// Constantes
	public final static double DISTANCE_LAPIN_ENTREE = 100;
	
	// Attributs
	private double posX;
	private double posY;
	private ArrayList<Lapin> lapins;
    
	// Méthodes
    public Terrier(double posX, double posY) {
    	this.posX = posX;
    	this.posY = posY;
    	this.lapins = new ArrayList<>();
    }
    
    public void ajoutLapin(Lapin lapin) {
    	lapins.add(lapin);
    }
    
    public void retirerLapin(Lapin lapin) {
    	lapins.remove(lapin);
    }

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public ArrayList<Lapin> getLapins() {
		return lapins;
	}

	public void setLapins(ArrayList<Lapin> lapins) {
		this.lapins = lapins;
	}

}
