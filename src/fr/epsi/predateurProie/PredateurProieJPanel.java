package fr.epsi.predateurProie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 31/10/2016.
 */
public class PredateurProieJPanel extends JPanel implements Observer, MouseListener {
    
	// Serial
	private final static long serialVersionUID = -6670365572011564482L;
	
	// Constantes
	public final static int RAFRAICHISSEMENT_PRAIRIE = 25;
	private final BufferedImage imageRenard = ImageIO.read(this.getClass().getResource("img/fox.png"));
    private final BufferedImage imageLapin = ImageIO.read(this.getClass().getResource("img/rabbit.png"));
    private final BufferedImage imageLapinCache = ImageIO.read(this.getClass().getResource("img/rabbithidden.png"));
    private final BufferedImage imageTerrier = ImageIO.read(this.getClass().getResource("img/hole.png"));

	// Singleton
	private static PredateurProieJPanel instance;

    public static PredateurProieJPanel getInstance() {
        if (instance == null) {
            try {
                instance = new PredateurProieJPanel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    // Attribut
    private Prairie prairie;
    
    // Méthodes
    public PredateurProieJPanel() throws IOException {
    	this.addMouseListener(this);
        this.setPreferredSize(new Dimension(400,400));
        this.setBackground(Color.white);
    }

    public void lancer() {
        prairie = Prairie.getInstance();
        prairie.initialiser(Prairie.NOMBRE_LAPINS_INITIAL, Prairie.NOMBRE_RENARDS_INITIAL, getWidth(), getHeight());
        prairie.addObserver(this);
        Prairie.getExecutor().scheduleAtFixedRate(prairie.miseAJour, 0, RAFRAICHISSEMENT_PRAIRIE, TimeUnit.MILLISECONDS);
        prairie.setFrequenceApparitionLapin(Prairie.getExecutor().scheduleAtFixedRate(prairie.apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS));
        prairie.setFrequenceApparitionRenard(Prairie.getExecutor().scheduleAtFixedRate(prairie.apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS));
    }

    private void dessignerLapin(Lapin lapin, Graphics g) {
    	if (lapin.isCache() == false) {
    		g.drawImage(imageLapin, (int) lapin.getPosX() -1, (int) lapin.getPosY() -1, 20, 20, null);    		
    	} else {
    		g.drawImage(imageLapinCache, (int) lapin.getPosX() -1, (int) lapin.getPosY() -1, 20, 20, null);  
    	}
    }

    private void dessignerRenard(Renard renard, Graphics g) {
        g.drawImage(imageRenard, (int) renard.getPosX() -1, (int) renard.getPosY() -1, 20, 20, null);
    }
    
    private void dessinerTerrier(Terrier terrier, Graphics g) {
    	g.drawImage(imageTerrier, (int) terrier.getPosX() -1, (int) terrier.getPosY() -1, 20, 20, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < prairie.getLapins().size(); i++) {
            Lapin lapin = prairie.getLapins().get(i);
            dessignerLapin(lapin, g);
        }
        for (int i = 0; i < prairie.getRenards().size(); i++) {
            Renard renard = prairie.getRenards().get(i);
            dessignerRenard(renard, g);
        }
        for (int i = 0; i < prairie.getTerriers().size(); i++) {
        	Terrier terrier = prairie.getTerriers().get(i);
        	dessinerTerrier(terrier, g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) { 
    	prairie.creerTerrier((double) e.getX(), (double) e.getY());
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
        this.getToolkit().sync();
    }
}
