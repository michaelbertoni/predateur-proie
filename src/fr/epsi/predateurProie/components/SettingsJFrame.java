package fr.epsi.predateurProie.components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import fr.epsi.predateurProie.entities.Prairie;

import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

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
	private JTextField frequenceApparitionLapinTField;
	private JTextField distanceVisibiliteRenardTField;
	private JTextField esperanceVieAnimauxTField;
	private JTextField dureeVieRenardTField;
	private JTextField nombreLapinsInitialTField;
	private JTextField nombreRenardsInitialTField;
	private JTextField dureeLapinCacheTField;
	private JTextField distanceVueLapinTerrierTField;

	// Méthodes
	public SettingsJFrame() {
		setTitle("Paramètres - Prédateur vs Proie !");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationByPlatform(true);
		setType(javax.swing.JFrame.Type.UTILITY);
		setBounds(670, 50, 665, 433);
		
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setForeground(Color.LIGHT_GRAY);
		mainPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		mainPanel.setLayout(null);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel presetsPanel = new JPanel();
		presetsPanel.setBorder(new TitledBorder(null, "Pr\u00E9configurations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		presetsPanel.setSize(500, 35);
		mainPanel.add(presetsPanel, BorderLayout.NORTH);
		
		JButton populationsEquilibreesButton = new JButton("Populations équilibrées");
		populationsEquilibreesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFields("1000", "15", "20", "120", "30", "50", "1000", "300");
			}
		});
		presetsPanel.add(populationsEquilibreesButton);
		
		JButton everybodyDeadButton = new JButton("Tout le monde meurt");
		everybodyDeadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFields("50000", "10", "50", "120", "10", "100", "1000", "300");
			}
		});
		presetsPanel.add(everybodyDeadButton);
		
		JButton lapinsOnlyButton = new JButton("Lapins uniquement");
		lapinsOnlyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFields("1000", "30", "30", "120", "9", "50", "1000", "300");
			}
		});
		presetsPanel.add(lapinsOnlyButton);
		
		JButton populationsAugmententButton = new JButton("Beaucoup d'agents");
		populationsAugmententButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setFields("100", "15", "30", "120", "15", "50", "1000", "300");
			}
		});
		presetsPanel.add(populationsAugmententButton);
		
		JPanel tFieldPanel = new JPanel();
		tFieldPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		mainPanel.add(tFieldPanel, BorderLayout.CENTER);
		tFieldPanel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel nombreLapinsInitialLabel = new JLabel("Nombre initial de lapins");
		tFieldPanel.add(nombreLapinsInitialLabel);
		nombreLapinsInitialLabel.setLabelFor(nombreLapinsInitialTField);
		
		nombreLapinsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
		tFieldPanel.add(nombreLapinsInitialTField);
		nombreLapinsInitialTField.setColumns(10);
		
		JLabel nbreRenardsInitialLabel = new JLabel("Nombre initial de renards");
		tFieldPanel.add(nbreRenardsInitialLabel);
		nbreRenardsInitialLabel.setLabelFor(nombreRenardsInitialTField);
		
		nombreRenardsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
		tFieldPanel.add(nombreRenardsInitialTField);
		nombreRenardsInitialTField.setColumns(10);
		
		JLabel frequenceApparitionLapinLabel = new JLabel("Fréquence apparition lapins (ms)");
		tFieldPanel.add(frequenceApparitionLapinLabel);
		frequenceApparitionLapinLabel.setLabelFor(frequenceApparitionLapinTField);
		
		frequenceApparitionLapinTField = new JTextField(String.valueOf(Prairie.FREQUENCE_APPARITION_ANIMAUX_MS));
		tFieldPanel.add(frequenceApparitionLapinTField);
		frequenceApparitionLapinTField.setColumns(10);
		
		JLabel esperanceVieAnimauxLabel = new JLabel("Mort naturelle animaux (sec)");
		tFieldPanel.add(esperanceVieAnimauxLabel);
		esperanceVieAnimauxLabel.setLabelFor(esperanceVieAnimauxTField);
		
		esperanceVieAnimauxTField = new JTextField(String.valueOf(Prairie.ESPERANCE_VIE_ANIMAUX));
		tFieldPanel.add(esperanceVieAnimauxTField);
		esperanceVieAnimauxTField.setColumns(10);
		
		JLabel dureeVieRenardLabel = new JLabel("Mort de faim renard (sec)");
		tFieldPanel.add(dureeVieRenardLabel);
		dureeVieRenardLabel.setLabelFor(dureeVieRenardTField);
		
		dureeVieRenardTField = new JTextField(String.valueOf(Prairie.DUREE_VIE_RENARD));
		tFieldPanel.add(dureeVieRenardTField);
		dureeVieRenardTField.setColumns(10);
		
		JLabel distanceVisibiliteRenardLabel = new JLabel("Distance visibilité proie (px)");
		tFieldPanel.add(distanceVisibiliteRenardLabel);
		distanceVisibiliteRenardLabel.setLabelFor(distanceVisibiliteRenardTField);
		
		distanceVisibiliteRenardTField = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_VISIBILITE_RENARD)));
		tFieldPanel.add(distanceVisibiliteRenardTField);
		distanceVisibiliteRenardTField.setColumns(10);
		
		JLabel dureeLapinCacheLabel = new JLabel("Durée lapin caché (ms)");
		tFieldPanel.add(dureeLapinCacheLabel);
		dureeLapinCacheLabel.setLabelFor(dureeLapinCacheTField);
		
		dureeLapinCacheTField = new JTextField(String.valueOf(Prairie.DUREE_LAPIN_CACHE));
		tFieldPanel.add(dureeLapinCacheTField);
		dureeLapinCacheTField.setColumns(10);
		
		JLabel distanceVueLapinTerrierLabel = new JLabel("Distance visibilité terrier (px)");
		tFieldPanel.add(distanceVueLapinTerrierLabel);
		distanceVueLapinTerrierLabel.setLabelFor(distanceVueLapinTerrierTField);
		
		distanceVueLapinTerrierTField = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_LAPIN_VUE_TERRIER)));
		tFieldPanel.add(distanceVueLapinTerrierTField);
		distanceVueLapinTerrierTField.setColumns(10);
		
		JPanel saveResetPanel = new JPanel();
		mainPanel.add(saveResetPanel, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Appliquer dans la simulation en cours");
		saveResetPanel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
			}
		});
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		
		JButton btnRestart = new JButton("Appliquer et réinitialiser la simulation");
		saveResetPanel.add(btnRestart);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
                Prairie.getInstance().reinitialiserPrairie();
                PredateurProieJPanel.getInstance().lancer();
			}
		});

	}
	
	private Runnable majParametres = () -> {
        Prairie.FREQUENCE_APPARITION_ANIMAUX_MS = Long.valueOf(frequenceApparitionLapinTField.getText());
        Prairie.getInstance().getFrequenceApparitionLapin().cancel(true);
        Prairie.getInstance().setFrequenceApparitionLapin(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS));
        Prairie.getInstance().getFrequenceApparitionRenard().cancel(true);
        Prairie.getInstance().setFrequenceApparitionRenard(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS));

        Double distanceVisibiliteRenard = Double.valueOf(distanceVisibiliteRenardTField.getText());
        Prairie.DISTANCE_VISIBILITE_RENARD = distanceVisibiliteRenard*distanceVisibiliteRenard;
        Prairie.ESPERANCE_VIE_ANIMAUX = Integer.valueOf(esperanceVieAnimauxTField.getText());
        Prairie.DUREE_VIE_RENARD = Integer.valueOf(dureeVieRenardTField.getText());
        Prairie.NOMBRE_LAPINS_INITIAL = Integer.valueOf(nombreLapinsInitialTField.getText());
        Prairie.NOMBRE_RENARDS_INITIAL = Integer.valueOf(nombreRenardsInitialTField.getText());
        Prairie.DUREE_LAPIN_CACHE = Integer.valueOf(dureeLapinCacheTField.getText());
        Double distanceVisibiliteTerrier = Double.valueOf(distanceVueLapinTerrierTField.getText());
        Prairie.DISTANCE_LAPIN_VUE_TERRIER = distanceVisibiliteTerrier*distanceVisibiliteTerrier;
    };
    
    private void setFields(String frequenceApparitionAnimaux, String nbreLapinsInitial,  String nbreRenardsInitial, String esperanceVieAnimaux, String dureeVieRenard, String distanceVisibiliteProie, String dureeLapinCache, String distanceVisibiliteTerrier) {
    	frequenceApparitionLapinTField.setText(frequenceApparitionAnimaux);
		nombreLapinsInitialTField.setText(nbreLapinsInitial);
		nombreRenardsInitialTField.setText(nbreRenardsInitial);
		esperanceVieAnimauxTField.setText(esperanceVieAnimaux);
		dureeVieRenardTField.setText(dureeVieRenard);
		distanceVisibiliteRenardTField.setText(distanceVisibiliteProie);
		dureeLapinCacheTField.setText(dureeLapinCache);
		distanceVueLapinTerrierTField.setText(distanceVisibiliteTerrier);
    }

}
