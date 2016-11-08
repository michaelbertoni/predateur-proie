package fr.epsi.predateurProie;

/**
 * Created by Michael on 31/10/2016.
 */
public class Animal {
	// Constantes
    public final static double PROB_CHGT_DIRECTION = 0.05;
    public static final double DISTANCE_MIN = 20;
	
    // Attributs
    private double posX;
    private double posY;
    private double PAS;
    private double vitesseX;
    private double vitesseY;
    private int mortNaturelle;

    // Méthodes
    public Animal() {}

    public void normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }

    public double Distance(Animal o) {
        return Math.sqrt((o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY));
    }

    public double DistanceCarre(Animal o) {
        return (o.posX - posX) * (o.posX - posX) + (o.posY - posY) * (o.posY - posY);
    }
    
    public double DistanceAuMur(double murXMin, double murYMin, double murXMax, double murYMax) {
        double min = Math.min(posX - murXMin, posY - murYMin);
        min = Math.min(min, murXMax - posX);
        min = Math.min(min, murYMax - posY);
        return min;
    }

    public void miseAJourPosition() {
    	eviterMur();
    	
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
        double largeur = Prairie.getInstance().getLargeur();
        double hauteur = Prairie.getInstance().getHauteur();
        if (posX < 0) {
            posX = 0;
        }
        else if (posX > largeur) {
            posX = largeur;
        }
        if (posY < 0) {
            posY = 0;
        }
        else if (posY > hauteur) {
            posY = hauteur;
        }
    }
    
    public void changementDirectionAleatoire() {
    	// Changement de direction aléatoire
        if (Prairie.getInstance().getGenerateur().nextDouble() < PROB_CHGT_DIRECTION) {
            vitesseX = Prairie.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().getGenerateur().nextDouble() - 0.5;
        }
        // change la direction du renard s'il arrive sur un bord du panel
        if( posY == 0
        		|| posX == 0 
        		|| posX >= PredateurProieJPanel.getInstance().getWidth() - 20
        		|| posY >= PredateurProieJPanel.getInstance().getHeight() - 20) {
        	vitesseX = Prairie.getInstance().getGenerateur().nextDouble() - 0.5;
            vitesseY = Prairie.getInstance().getGenerateur().nextDouble() - 0.5;
        }
    }
    
    public void eviterMur() {
    	double distance = DistanceAuMur(0, 0, Prairie.getInstance().getLargeur(), Prairie.getInstance().getHauteur());
        if (distance < DISTANCE_MIN) {
            if (distance == (posX - 0)) {
                vitesseX += 0.3;
            }
            else if (distance == (posY - 0)) { 
                vitesseY += 0.3; 
            } 
            else if (distance == (Prairie.getInstance().getLargeur() - posX)) {
                vitesseX -= 0.3;
            } 
            else if (distance == (Prairie.getInstance().getHauteur() - posY)) {
                vitesseY -= 0.3;
            }   
            normaliser();
        }
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

	public double getPAS() {
		return PAS;
	}

	public void setPAS(double pAS) {
		PAS = pAS;
	}

	public double getVitesseX() {
		return vitesseX;
	}

	public void setVitesseX(double vitesseX) {
		this.vitesseX = vitesseX;
	}

	public double getVitesseY() {
		return vitesseY;
	}

	public void setVitesseY(double vitesseY) {
		this.vitesseY = vitesseY;
	}

	public int getMortNaturelle() {
		return mortNaturelle;
	}

	public void setMortNaturelle(int mortNaturelle) {
		this.mortNaturelle = mortNaturelle;
	}
    
}
