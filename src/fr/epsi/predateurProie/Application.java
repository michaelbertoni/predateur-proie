package fr.epsi.predateurProie;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.Box;

public class Application extends JFrame {

	private static final long serialVersionUID = -7687618967101960830L;
	
	private JPanel contentPane;
	private static JLabel nbreLapins;
	private static JLabel nbreRenards;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Application() {
		setTitle("Prédateur vs Proie !");
		setLocationByPlatform(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JMenuItem restartItem = new JMenuItem("Réinitialiser");
		restartItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prairie.getInstance().reinitialiserPrairie();
                PredateurProieJPanel.getInstance().lancer();
			}
		});
		mainMenu.add(restartItem);
		mainMenu.add(exitItem);
		
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
