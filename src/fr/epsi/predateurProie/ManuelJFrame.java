package fr.epsi.predateurProie;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class ManuelJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6453475499274768074L;

	// Singleton
	private static ManuelJFrame instance;
	private JScrollPane contentPane;

    public static ManuelJFrame getInstance() {
        if (instance == null) {
            instance = new ManuelJFrame();
        }
        return instance;
    }

	/**
	 * Create the frame.
	 */
	public ManuelJFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(670,400,600,500);
		setTitle("Manuel - Prédateur vs Proie !");
		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.getDocument().putProperty("IgnoreCharsetDirective", Boolean.TRUE);
		try {
			editorPane.setPage(this.getClass().getResource("doc/manuel.html"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		contentPane = new JScrollPane(editorPane);
		setContentPane(contentPane);
	}

}
