package fr.epsi.predateurProie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 31/10/2016.
 */
public class ParametresJPanel extends JPanel {
        private static ParametresJPanel instance;

    public static ParametresJPanel getInstance() {
        if (instance == null) {
            instance = new ParametresJPanel();
        }
        return instance;
    }

    private JPanel labelCompteursPanel;
    private JPanel labelInputPanel;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JTextField frequenceApparitionTField;
    private JTextField distanceVisibiliteRenardTField;
    private JTextField dureeVieRenardTField;
    private JTextField nombreLapinsInitialTField;
    private JTextField nombreRenardsInitialTField;
    private JLabel nbLapinsLive;
    private JLabel nbRenardsLive;

    public ParametresJPanel() {
        super(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);

        labelCompteursPanel = new JPanel(new GridLayout(2, 1));
        labelInputPanel = new JPanel(new GridLayout(5, 1));
        fieldPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel = new JPanel(new FlowLayout());
        add(labelCompteursPanel, BorderLayout.NORTH);
        add(labelInputPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        frequenceApparitionTField();
        distanceVisibiliteRenardTField();
        dureeVieRenardTField();
        nombreLapinsInitialTField();
        nombreRenardsInitialTField();

        JButton buttonSaveParameters = new JButton("Save");
        buttonSaveParameters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majParametres.run();
            }
        });
        buttonPanel.add(buttonSaveParameters);

        JButton buttonRestart = new JButton("Restart");
        buttonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majParametres.run();
                Prairie.getNewInstance();
                PredateurProieJPanel.getInstance().lancer();
            }
        });
        buttonPanel.add(buttonRestart);

        nbLapinsLive = new JLabel("Nombre lapins : " + String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
        nbRenardsLive = new JLabel("Nombre renards : " + String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
        labelCompteursPanel.add(nbLapinsLive);
        labelCompteursPanel.add(nbRenardsLive);
    }

    private void frequenceApparitionTField() {
        frequenceApparitionTField = new JTextField(String.valueOf(Prairie.FREQUENCE_APPARITION_ANIMAUX_SECONDES));
        JLabel frequenceApparitionLab = new JLabel("<html>Fréq.<br>apparition (sec)</html>");
        frequenceApparitionLab.setLabelFor(frequenceApparitionTField);
        labelInputPanel.add(frequenceApparitionLab);
        fieldPanel.add(frequenceApparitionTField);
    }

    private void distanceVisibiliteRenardTField() {
        distanceVisibiliteRenardTField = new JTextField(String.valueOf(Prairie.DISTANCE_VISIBILITE_RENARD));
        JLabel distanceVisibiliteRenardLab = new JLabel("<html>Distance visibilité<br>renards (px, au carré)</html>");
        distanceVisibiliteRenardLab.setLabelFor(distanceVisibiliteRenardTField);
        labelInputPanel.add(distanceVisibiliteRenardLab);
        fieldPanel.add(distanceVisibiliteRenardTField);
    }

    private void dureeVieRenardTField() {
        dureeVieRenardTField = new JTextField(String.valueOf(Prairie.DUREE_VIE_RENARD));
        JLabel dureeVieRenardLab = new JLabel("<html>Durée vie<br>renards (sec)</html>");
        dureeVieRenardLab.setLabelFor(dureeVieRenardTField);
        labelInputPanel.add(dureeVieRenardLab);
        fieldPanel.add(dureeVieRenardTField);
    }

    private void nombreLapinsInitialTField() {
        nombreLapinsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_LAPINS_INITIAL));
        JLabel nombreLapinsInitialLab = new JLabel("<html>Nombre lapins<br>initial</html>");
        nombreLapinsInitialLab.setLabelFor(nombreLapinsInitialTField);
        labelInputPanel.add(nombreLapinsInitialLab);
        fieldPanel.add(nombreLapinsInitialTField);
    }

    private void nombreRenardsInitialTField() {
        nombreRenardsInitialTField = new JTextField(String.valueOf(Prairie.NOMBRE_RENARDS_INITIAL));
        JLabel nombreRenardsInitialLab = new JLabel("<html>Nombre lapins<br>initial</html>");
        nombreRenardsInitialLab.setLabelFor(nombreRenardsInitialTField);
        labelInputPanel.add(nombreRenardsInitialLab);
        fieldPanel.add(nombreRenardsInitialTField);
    }

    private Runnable majParametres = () -> {
        Prairie.FREQUENCE_APPARITION_ANIMAUX_SECONDES = Long.valueOf(frequenceApparitionTField.getText());
        Prairie.getInstance().frequenceApparitionAnimauxSchedule.cancel(true);
        Prairie.getInstance().frequenceApparitionAnimauxSchedule = Prairie.executor.scheduleAtFixedRate(Prairie.getInstance().apparitionAnimaux, 0, Prairie.FREQUENCE_APPARITION_ANIMAUX_SECONDES, TimeUnit.SECONDS);
        Prairie.DISTANCE_VISIBILITE_RENARD = Long.valueOf(frequenceApparitionTField.getText());
        Prairie.DUREE_VIE_RENARD = Integer.valueOf(dureeVieRenardTField.getText());
        Prairie.NOMBRE_LAPINS_INITIAL = Integer.valueOf(nombreLapinsInitialTField.getText());
        Prairie.NOMBRE_RENARDS_INITIAL = Integer.valueOf(nombreRenardsInitialTField.getText());
    };

    public Runnable majCompteurs = () -> {
        nbLapinsLive.setText("Nombre lapins : " + String.valueOf(Prairie.getInstance().lapins.size()));
        nbRenardsLive.setText("Nombre renards : " + String.valueOf(Prairie.getInstance().renards.size()));
    };
}
