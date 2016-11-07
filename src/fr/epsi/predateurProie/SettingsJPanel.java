package fr.epsi.predateurProie;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class SettingsJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7774983463447224599L;
	
	private static SettingsJPanel instance;

    protected static SettingsJPanel getInstance() {
        if (instance == null) {
            instance = new SettingsJPanel();
        }
        return instance;
    }
	
	private JTextField frequenceApparitionLapinTfield;
	private JTextField distanceVisibiliteRenardTField;
	private JTextField dureeVieRenardTField;
	private JTextField nombreLapinsInitialTField;
	private JTextField nombreRenardsInitialTField;
	private JTextField dureeLapinCacheTfield;
	private JTextField distanceVueLapinTerrierTfield;
	private JLabel nbreLapins;
	private JLabel nbreRenards;

	/**
	 * Create the panel.
	 */
	public SettingsJPanel() {
		setForeground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(200, 400));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelPopulation = new JPanel();
		panelPopulation.setForeground(Color.LIGHT_GRAY);
		panelPopulation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Populations", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		add(panelPopulation);
		panelPopulation.setLayout(new GridLayout(2, 1, 0, 0));
		
		nbreLapins = new JLabel("Nombre lapins : " + String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
		nbreLapins.setAlignmentY(Component.TOP_ALIGNMENT);
		panelPopulation.add(nbreLapins);
		
		nbreRenards = new JLabel("Nombre renards : " + String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
		nbreRenards.setAlignmentY(Component.TOP_ALIGNMENT);
		panelPopulation.add(nbreRenards);
		
		JPanel panelTextfields = new JPanel();
		panelTextfields.setForeground(Color.LIGHT_GRAY);
		panelTextfields.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Param\u00E8tres", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		add(panelTextfields);
		panelTextfields.setLayout(new BoxLayout(panelTextfields, BoxLayout.Y_AXIS));
		
		JLabel frequenceApparitionLapinLabel = new JLabel("Fréquence apparition lapins (sec)");
		panelTextfields.add(frequenceApparitionLapinLabel);
		frequenceApparitionLapinLabel.setLabelFor(frequenceApparitionLapinTfield);
		
		frequenceApparitionLapinTfield = new JTextField(String.valueOf(Prairie.FREQUENCE_APPARITION_ANIMAUX_MS));
		panelTextfields.add(frequenceApparitionLapinTfield);
		frequenceApparitionLapinTfield.setColumns(10);
		
		JLabel distanceVisibiliteRenardLabel = new JLabel("Distance visibilité proie (px)");
		panelTextfields.add(distanceVisibiliteRenardLabel);
		distanceVisibiliteRenardLabel.setLabelFor(distanceVisibiliteRenardTField);
		
		distanceVisibiliteRenardTField = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_VISIBILITE_RENARD)));
		panelTextfields.add(distanceVisibiliteRenardTField);
		distanceVisibiliteRenardTField.setColumns(10);
		
		JLabel dureeVieRenardLabel = new JLabel("Survie renard sans manger (sec)");
		panelTextfields.add(dureeVieRenardLabel);
		dureeVieRenardLabel.setLabelFor(dureeVieRenardTField);
		
		dureeVieRenardTField = new JTextField(String.valueOf(Prairie.DUREE_VIE_RENARD));
		panelTextfields.add(dureeVieRenardTField);
		dureeVieRenardTField.setColumns(10);
		
		JLabel nombreLapinsInitialLabel = new JLabel("Nombre initial de lapins");
		panelTextfields.add(nombreLapinsInitialLabel);
		nombreLapinsInitialLabel.setLabelFor(nombreLapinsInitialTField);
		
		nombreLapinsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
		panelTextfields.add(nombreLapinsInitialTField);
		nombreLapinsInitialTField.setColumns(10);
		
		JLabel nbreRenardsInitialLabel = new JLabel("Nombre initial de renards");
		panelTextfields.add(nbreRenardsInitialLabel);
		nbreRenardsInitialLabel.setLabelFor(nombreRenardsInitialTField);
		
		nombreRenardsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
		panelTextfields.add(nombreRenardsInitialTField);
		nombreRenardsInitialTField.setColumns(10);
		
		JLabel dureeLapinCacheLabel = new JLabel("Durée lapin caché (sec)");
		panelTextfields.add(dureeLapinCacheLabel);
		dureeLapinCacheLabel.setLabelFor(dureeLapinCacheTfield);
		
		dureeLapinCacheTfield = new JTextField(String.valueOf(Prairie.DUREE_LAPIN_CACHE));
		panelTextfields.add(dureeLapinCacheTfield);
		dureeLapinCacheTfield.setColumns(10);
		
		JLabel distanceVueLapinTerrierLabel = new JLabel("Distance visibilité terrier (px)");
		panelTextfields.add(distanceVueLapinTerrierLabel);
		distanceVueLapinTerrierLabel.setLabelFor(distanceVueLapinTerrierTfield);
		
		distanceVueLapinTerrierTfield = new JTextField(String.valueOf(Math.sqrt(Prairie.DISTANCE_LAPIN_VUE_TERRIER)));
		panelTextfields.add(distanceVueLapinTerrierTfield);
		distanceVueLapinTerrierTfield.setColumns(10);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setForeground(Color.LIGHT_GRAY);
		add(panelButtons);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
			}
		});
		panelButtons.add(btnSave);
		btnSave.setHorizontalAlignment(SwingConstants.LEADING);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majParametres.run();
                Prairie.getInstance().reinitialiserPrairie();
                PredateurProieJPanel.getInstance().lancer();
			}
		});
		panelButtons.add(btnRestart);

	}
	
	private Runnable majParametres = () -> {
        Prairie.FREQUENCE_APPARITION_ANIMAUX_MS = Long.valueOf(frequenceApparitionLapinTfield.getText());
        Prairie.getInstance().frequenceApparitionLapin.cancel(true);
        Prairie.getInstance().frequenceApparitionLapin = Prairie.executor.scheduleAtFixedRate(Prairie.getInstance().apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS);
        Prairie.getInstance().frequenceApparitionRenard.cancel(true);
        Prairie.getInstance().frequenceApparitionRenard = Prairie.executor.scheduleAtFixedRate(Prairie.getInstance().apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS);

        Double distanceVisibiliteRenard = Double.valueOf(distanceVisibiliteRenardTField.getText());
        Prairie.DISTANCE_VISIBILITE_RENARD = distanceVisibiliteRenard*distanceVisibiliteRenard;
        Prairie.DUREE_VIE_RENARD = Integer.valueOf(dureeVieRenardTField.getText());
        Prairie.NOMBRE_LAPINS_INITIAL = Integer.valueOf(nombreLapinsInitialTField.getText());
        Prairie.NOMBRE_RENARDS_INITIAL = Integer.valueOf(nombreRenardsInitialTField.getText());
        Prairie.DUREE_LAPIN_CACHE = Integer.valueOf(dureeLapinCacheTfield.getText());
        Double distanceVisibiliteTerrier = Double.valueOf(distanceVueLapinTerrierTfield.getText());
        Prairie.DISTANCE_LAPIN_VUE_TERRIER = distanceVisibiliteTerrier*distanceVisibiliteTerrier;
    };

    protected Runnable majCompteurs = () -> {
        nbreLapins.setText("Nombre lapins : " + String.valueOf(Prairie.getInstance().lapins.size()));
        nbreRenards.setText("Nombre renards : " + String.valueOf(Prairie.getInstance().renards.size()));
    };
	

}
