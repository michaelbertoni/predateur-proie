package fr.epsi.predateurProie;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.JTextPane;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class AProposJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5546585852646598599L;
	private JPanel contentPane;

	// Singleton
	private static AProposJFrame instance;

    public static AProposJFrame getInstance() {
        if (instance == null) {
            instance = new AProposJFrame();
        }
        return instance;
    }

	/**
	 * Create the frame.
	 */
	public AProposJFrame() {
		setTitle("A propos - Prédateur vs Proie !");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(50, 570, 536, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel foxImg = new JLabel("");
		foxImg.setIcon(new ImageIcon(this.getClass().getResource("/fr/epsi/predateurProie/img/fox.png")));
		panel.add(foxImg);
		
		JLabel rabbitImg = new JLabel("");
		rabbitImg.setIcon(new ImageIcon(this.getClass().getResource("/fr/epsi/predateurProie/img/rabbit.png")));
		panel.add(rabbitImg);
		
		JTextPane txtpnprdateurVsProie = new JTextPane();
		txtpnprdateurVsProie.setContentType("text/html");
		txtpnprdateurVsProie.setEditable(false);
		txtpnprdateurVsProie.setText("<header>\r\n\t<style>\r\n\t\tbody {\r\n\t\t\tfont-family:calibri,arial;\r\n\t\t}\r\n\t</style>\r\n</header>\r\n\r\n<center><h1>Prédateur vs Proie !</h1>\r\n<p>v2.1.0</p>\r\n<h2>Sources : <a href=\"https://gitlab.com/michaelbertoni/predateur-proie\">GitLab</a></h2></center>");
		txtpnprdateurVsProie.setBackground(UIManager.getColor("Label.background"));
		txtpnprdateurVsProie.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
		        }
			}
		});
		contentPane.add(txtpnprdateurVsProie);
	}

}
