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
    private static PredateurProieJPanel instance;

    protected static PredateurProieJPanel getInstance() {
        if (instance == null) {
            try {
                instance = new PredateurProieJPanel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private Prairie prairie;
    private final BufferedImage imageRenard = ImageIO.read(this.getClass().getResource("img/fox.png"));
    private final BufferedImage imageLapin = ImageIO.read(this.getClass().getResource("img/rabbit.png"));
    private final BufferedImage imageLapinCache = ImageIO.read(this.getClass().getResource("img/rabbithidden.png"));
    private final BufferedImage imageTerrier = ImageIO.read(this.getClass().getResource("img/hole.png"));

    private PredateurProieJPanel() throws IOException {
    	this.addMouseListener(this);
        this.setPreferredSize(new Dimension(400,400));
        this.setBackground(Color.white);
    }

    protected void lancer() {
        prairie = Prairie.getInstance();
        prairie.initialiser(Prairie.NOMBRE_LAPINS_INITIAL, Prairie.NOMBRE_RENARDS_INITIAL, getWidth(), getHeight());
        prairie.addObserver(this);
        Prairie.executor.scheduleAtFixedRate(prairie.miseAJour, 0, 25, TimeUnit.MILLISECONDS);
        prairie.frequenceApparitionLapin = Prairie.executor.scheduleAtFixedRate(prairie.apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS);
        prairie.frequenceApparitionRenard = Prairie.executor.scheduleAtFixedRate(prairie.apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS);
    }

    private void dessignerLapin(Lapin lapin, Graphics g) {
    	if (lapin.cache == false) {
    		g.drawImage(imageLapin, (int) lapin.posX -1, (int) lapin.posY -1, 20, 20, null);    		
    	} else {
    		g.drawImage(imageLapinCache, (int) lapin.posX -1, (int) lapin.posY -1, 20, 20, null);  
    	}
    }

    private void dessignerRenard(Renard renard, Graphics g) {
        g.drawImage(imageRenard, (int) renard.posX -1, (int) renard.posY -1, 20, 20, null);
    }
    
    private void dessinerTerrier(Terrier terrier, Graphics g) {
    	g.drawImage(imageTerrier, (int) terrier.posX -1, (int) terrier.posY -1, 20, 20, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < prairie.lapins.size(); i++) {
            Lapin lapin = prairie.lapins.get(i);
            dessignerLapin(lapin, g);
        }
        for (int i = 0; i < prairie.renards.size(); i++) {
            Renard renard = prairie.renards.get(i);
            dessignerRenard(renard, g);
        }
        for (int i = 0; i < prairie.terriers.size(); i++) {
        	Terrier terrier = prairie.terriers.get(i);
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
