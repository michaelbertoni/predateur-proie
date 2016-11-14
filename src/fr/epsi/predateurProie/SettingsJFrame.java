package fr.epsi.predateurProie;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SettingsJFrame extends JFrame {
	
	// Serial
	private static final long serialVersionUID = -7774983463447224599L;
	
	// Singleton
	private static SettingsJFrame instance;

    public static SettingsJFrame getInstance() {
        if (instance == null) {
            instance = new SettingsJFrame();
        }
        return instance;
    }
	
    // Attributs
	private JTextField frequenceApparitionLapinTfield;
	private JTextField distanceVisibiliteRenardTField;
	private JTextField esperanceVieAnimauxTfield;
	private JTextField dureeVieRenardTField;
	private JTextField nombreLapinsInitialTField;
	private JTextField nombreRenardsInitialTField;
	private JTextField dureeLapinCacheTfield;
	private JTextField distanceVueLapinTerrierTfield;

	// Méthodes
	public SettingsJFrame() {
		setTitle("Paramètres - Prédateur vs Proie !");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationByPlatform(true);
		setBounds(670, 50, 500, 300);
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setForeground(Color.LIGHT_GRAY);
		mainPanel.setBorder(null);
		mainPanel.setLayout(null);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelTfield = new JPanel();
		mainPanel.add(panelTfield);
		panelTfield.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel nombreLapinsInitialLabel = new JLabel("Nombre initial de lapins");
		panelTfield.add(nombreLapinsInitialLabel);
		nombreLapinsInitialLabel.setLabelFor(nombreLapinsInitialTField);
		
		nombreLapinsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
		panelTfield.add(nombreLapinsInitialTField);
		nombreLapinsInitialTField.setColumns(10);
		
		JLabel nbreRenardsInitialLabel = new JLabel("Nombre initial de renards");
		panelTfield.add(nbreRenardsInitialLabel);
		nbreRenardsInitialLabel.setLabelFor(nombreRenardsInitialTField);
		
		nombreRenardsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
		panelTfield.add(nombreRenardsInitialTField);
		nombreRenardsInitialTField.setColumns(10);
		
		JLabel frequenceApparitionLapinLabel = new JLabel("Fréquence apparition lapins (ms)");
		panelTfield.add(frequenceApparitionLapinLabel);
		frequenceApparitionLapinLabel.setLabelFor(frequenceApparitionLapinTfield);
		
		frequenceApparitionLapinTfield = new JTextField(String.valueOf(Prairie.FREQUENCE_APPARITION_ANIMAUX_MS));
		panelTfield.add(frequenceApparitionLapinTfield);
		frequenceApparitionLapinTfield.setColumns(10);
		
		JLabel esperanceVieAnimauxLabel = new JLabel("Mort naturelle animaux (sec)");
		panelTfield.add(esperanceVieAnimauxLabel);
		esperanceVieAnimauxLabel.setLabelFor(esperanceVieAnimauxTfield);
		
		esperanceVieAnimauxTfield = new JTextField(String.valueOf(Prairie.ESPERANCE_VIE_ANIMAUX));
		panelTfield.add(esperanceVieAnimauxTfield);
		esperanceVieAnimauxTfield.setColumns(10);
		
		JLabel dureeVieRenardLabel = new JLabel("Mort de faim renard (sec)");
		panelTfield.add(dureeVieRenardLabel);
		dureeVieRenardLabel.setLabelFor(dureeVieRenardTField);
		
		dureeVieRenardTField = new JTextField(String.valueOf(Prairie.DUREE_VIE_RENARD));
		panelTfield.add(dureeVieRenardTField);
		dureeVieRenardTField.setColumns(10);
		
		JLabel distanceVisibiliteRenardLabel = new JLabel("Distance visibilité proie (px)");
		panelTfield.add(distanceVisibiliteRenardLabel);
		distanceVisibiliteRenardLabel.setLabelFor(distanceVisibiliteRenardTField);
		
		distanceVisibiliteRenardTField = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_VISIBILITE_RENARD)));
		panelTfield.add(distanceVisibiliteRenardTField);
		distanceVisibiliteRenardTField.setColumns(10);
		
		JLabel dureeLapinCacheLabel = new JLabel("Durée lapin caché (ms)");
		panelTfield.add(dureeLapinCacheLabel);
		dureeLapinCacheLabel.setLabelFor(dureeLapinCacheTfield);
		
		dureeLapinCacheTfield = new JTextField(String.valueOf(Prairie.DUREE_LAPIN_CACHE));
		panelTfield.add(dureeLapinCacheTfield);
		dureeLapinCacheTfield.setColumns(10);
		
		JLabel distanceVueLapinTerrierLabel = new JLabel("Distance visibilité terrier (px)");
		panelTfield.add(distanceVueLapinTerrierLabel);
		distanceVueLapinTerrierLabel.setLabelFor(distanceVueLapinTerrierTfield);
		
		distanceVueLapinTerrierTfield = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_LAPIN_VUE_TERRIER)));
		panelTfield.add(distanceVueLapinTerrierTfield);
		distanceVueLapinTerrierTfield.setColumns(10);
		
		JButton btnSave = new JButton("Appliquer dans la simulation en cours");
		mainPanel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		
		JButton btnRestart = new JButton("Appliquer et réinitialiser la simulation");
		mainPanel.add(btnRestart);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
                Prairie.getInstance().reinitialiserPrairie();
                PredateurProieJPanel.getInstance().lancer();
			}
		});

	}
	
	private Runnable majParametres = () -> {
        Prairie.FREQUENCE_APPARITION_ANIMAUX_MS = Long.valueOf(frequenceApparitionLapinTfield.getText());
        Prairie.getInstance().getFrequenceApparitionLapin().cancel(true);
        Prairie.getInstance().setFrequenceApparitionLapin(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS));
        Prairie.getInstance().getFrequenceApparitionRenard().cancel(true);
        Prairie.getInstance().setFrequenceApparitionRenard(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS));

        Double distanceVisibiliteRenard = Double.valueOf(distanceVisibiliteRenardTField.getText());
        Prairie.DISTANCE_VISIBILITE_RENARD = distanceVisibiliteRenard*distanceVisibiliteRenard;
        Prairie.ESPERANCE_VIE_ANIMAUX = Integer.valueOf(esperanceVieAnimauxTfield.getText());
        Prairie.DUREE_VIE_RENARD = Integer.valueOf(dureeVieRenardTField.getText());
        Prairie.NOMBRE_LAPINS_INITIAL = Integer.valueOf(nombreLapinsInitialTField.getText());
        Prairie.NOMBRE_RENARDS_INITIAL = Integer.valueOf(nombreRenardsInitialTField.getText());
        Prairie.DUREE_LAPIN_CACHE = Integer.valueOf(dureeLapinCacheTfield.getText());
        Double distanceVisibiliteTerrier = Double.valueOf(distanceVueLapinTerrierTfield.getText());
        Prairie.DISTANCE_LAPIN_VUE_TERRIER = distanceVisibiliteTerrier*distanceVisibiliteTerrier;
    };

}
