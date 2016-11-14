package fr.epsi.predateurProie;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import fr.epsi.predateurProie.components.AProposJFrame;
import fr.epsi.predateurProie.components.ManuelJFrame;
import fr.epsi.predateurProie.components.PredateurProieJPanel;
import fr.epsi.predateurProie.components.SettingsJFrame;
import fr.epsi.predateurProie.entities.Prairie;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.Box;
import java.awt.Toolkit;

public class Application extends JFrame {

	// Serial
	private static final long serialVersionUID = -7687618967101960830L;
	
	// Attributs
	private JPanel contentPane;
	private static JLabel nbreLapins;
	private static JLabel nbreRenards;

	// Lancement de l'application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Application frame = new Application();
					frame.setVisible(true);
					PredateurProieJPanel.getInstance().lancer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Méthodes
	public Application() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/fr/epsi/predateurProie/resources/fox.png")));
		setTitle("Prédateur vs Proie !");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mainMenu = new JMenu("Menu");
		menuBar.add(mainMenu);
		
		JMenuItem settingsItem = new JMenuItem("Options");
		settingsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SettingsJFrame.getInstance().setVisible(true);
			}
		});
		mainMenu.add(settingsItem);
		
		JMenuItem exitItem = new JMenuItem("Quitter");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mainMenu.add(exitItem);
		
		JMenu simulationMenu = new JMenu("Simulation");
		menuBar.add(simulationMenu);
		
		JMenuItem playPauseMenuItem = new JMenuItem("Play/Pause");
		playPauseMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Prairie.getExecutor().isShutdown()) {
					Prairie.getNewExecutor();
					Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().miseAJour, 0, PredateurProieJPanel.RAFRAICHISSEMENT_PRAIRIE, TimeUnit.MILLISECONDS);
					Prairie.getInstance().setFrequenceApparitionLapin(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionLapin, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS, TimeUnit.MILLISECONDS));
					Prairie.getInstance().setFrequenceApparitionRenard(Prairie.getExecutor().scheduleAtFixedRate(Prairie.getInstance().apparitionRenard, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, Prairie.FREQUENCE_APPARITION_ANIMAUX_MS*10, TimeUnit.MILLISECONDS));
				} else {
					Prairie.getExecutor().shutdown();
				}
			}
		});
		simulationMenu.add(playPauseMenuItem);
		
		JMenuItem restartItem = new JMenuItem("Réinitialiser");
		simulationMenu.add(restartItem);
		
		JMenu aideMenu = new JMenu("Aide");
		menuBar.add(aideMenu);
		
		JMenuItem manuelItem = new JMenuItem("Manuel");
		manuelItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManuelJFrame.getInstance().setVisible(true);;
			}
		});
		aideMenu.add(manuelItem);
		
		JMenuItem aProposItem = new JMenuItem("À propos");
		aProposItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AProposJFrame.getInstance().setVisible(true);
			}
		});
		aideMenu.add(aProposItem);
		restartItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prairie.getInstance().reinitialiserPrairie();
                PredateurProieJPanel.getInstance().lancer();
			}
		});
		
		PredateurProieJPanel panel = PredateurProieJPanel.getInstance();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		nbreLapins = new JLabel("Lapins : " + String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
		nbreLapins.setAlignmentY(Component.TOP_ALIGNMENT);
		toolBar.add(nbreLapins);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);
		
		nbreRenards = new JLabel("Renards : " + String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
		nbreRenards.setAlignmentY(Component.TOP_ALIGNMENT);
		toolBar.add(nbreRenards);
	}
	
	public static Runnable majCompteurs = () -> {
        nbreLapins.setText("Lapins : " + String.valueOf(Prairie.getInstance().getLapins().size()));
        nbreRenards.setText("Renards : " + String.valueOf(Prairie.getInstance().getRenards().size()));
    };

}
