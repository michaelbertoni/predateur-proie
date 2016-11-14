package fr.epsi.predateurProie.components;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class ManuelJFrame extends JFrame {

	// Serial
	private static final long serialVersionUID = 6453475499274768074L;

	// Singleton
	private static ManuelJFrame instance;

    public static ManuelJFrame getInstance() {
        if (instance == null) {
            instance = new ManuelJFrame();
        }
        return instance;
    }
    
    // Attribut
    private JScrollPane contentPane;

    // Méthodes
	public ManuelJFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setType(javax.swing.JFrame.Type.UTILITY);
		setBounds(670,500,600,500);
		setTitle("Manuel - Prédateur vs Proie !");
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		try {
			editorPane.setPage(this.getClass().getResource("/fr/epsi/predateurProie/resources/manuel.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		contentPane = new JScrollPane(editorPane);
		setContentPane(contentPane);
	}

}
