package vue;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modele.LabelError;
import modele.colconvives;
import java.awt.Color;

public class Fsaisie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5179585019775983838L;
	private JPanel contentPane;
	private JTextField entryNom;
	private JTextField entryPrenom;
	private JTextField entryAge;
	private JTextField entryFeature;
	private ctrlvue cv;
	private LabelError errorNom;
	private LabelError errorPrenom;
	private LabelError errorAge;
	private LabelError errorFeature;
	private JButton btnQuitter;
	private JComboBox<Object> typeCombo;
	private JList<String> listeConvives;
	private JLabel lblCaracteristiques;
	private JButton btnDetruire;
	private JButton btnAfficher;
	private JButton btnSupprimer;	
	private DefaultListModel<String> dlm;
	private JButton btnAjouter;
	private JLabel lblListConvives;
	private final String[] tootlipFeature = {"Entrez une formation entre 1 et 10 caractères",
			"Entrez l'ancienneté entre 1 et 42",
			"Entrez le salaire supérieur à 0.0"};
	/**
	 * Create the frame.
	 */
	public Fsaisie(ctrlvue _cv) {
		super("Fenêtre de saisie");
		this.cv = _cv;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Fsaisie.this.closeWindow();
			}
		});
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblConvive = new JLabel("Convive :");
		panel.add(lblConvive);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		panel_1.add(lblType, gbc_lblType);
		
		JLabel lblNom = new JLabel("Nom");
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 0;
		panel_1.add(lblNom, gbc_lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		GridBagConstraints gbc_lblPrenom = new GridBagConstraints();
		gbc_lblPrenom.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenom.gridx = 2;
		gbc_lblPrenom.gridy = 0;
		panel_1.add(lblPrenom, gbc_lblPrenom);
		
		JLabel lblAge = new JLabel("Age");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 3;
		gbc_lblAge.gridy = 0;
		panel_1.add(lblAge, gbc_lblAge);
		
		lblCaracteristiques = new JLabel("Formation");
		GridBagConstraints gbc_lblCaracteristiques = new GridBagConstraints();
		gbc_lblCaracteristiques.insets = new Insets(0, 0, 5, 5);
		gbc_lblCaracteristiques.gridx = 4;
		gbc_lblCaracteristiques.gridy = 0;
		panel_1.add(lblCaracteristiques, gbc_lblCaracteristiques);
		
		String[] liste_type = new String[] {colconvives.STAGIAIRE,
                colconvives.STAGIAIRE_CIF,
                colconvives.FORMATEUR,
                colconvives.DIRECTEUR};
		typeCombo = new JComboBox<Object>(liste_type);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panel_1.add(typeCombo, gbc_comboBox);
		
		entryNom = new JTextField();
		entryNom.setToolTipText("Entrez un nom compris entre 1 et 20 caract\u00E8res");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(entryNom, gbc_textField);
		entryNom.setColumns(10);
		
		entryPrenom = new JTextField();
		entryPrenom.setToolTipText("Entrez un pr\u00E9nom entre 1 et 15 caract\u00E8res");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panel_1.add(entryPrenom, gbc_textField_1);
		entryPrenom.setColumns(10);
		
		entryAge = new JTextField();
		entryAge.setToolTipText("Age doit \u00EAtre compris entre 18 et 67");
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		panel_1.add(entryAge, gbc_textField_2);
		entryAge.setColumns(10);
		
		entryFeature = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 1;
		panel_1.add(entryFeature, gbc_textField_3);
		entryFeature.setColumns(12);
		
		btnAjouter = new JButton("Ajouter");
		GridBagConstraints gbc_btnAjouter = new GridBagConstraints();
		gbc_btnAjouter.insets = new Insets(0, 0, 5, 0);
		gbc_btnAjouter.gridx = 5;
		gbc_btnAjouter.gridy = 1;
		panel_1.add(btnAjouter, gbc_btnAjouter);
		
		Component verticalStrut = Box.createVerticalStrut(80);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 1;
		contentPane.add(verticalStrut, gbc_verticalStrut);
		
		errorNom = new LabelError("Erreur nom");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel_1.add(errorNom, gbc_lblNewLabel);
		
		errorPrenom = new LabelError("Erreur prenom");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(errorPrenom, gbc_lblNewLabel_1);
		
		errorAge = new LabelError("Erreur age");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(errorAge, gbc_lblNewLabel_2);
		
		errorFeature = new LabelError("Erreur caracteristique");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 2;
		panel_1.add(errorFeature, gbc_lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblListConvives = new JLabel("Liste des convives :   0 convive");
		panel_2.add(lblListConvives);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		this.dlm = new DefaultListModel<String>();
		listeConvives = new JList<String>(this.dlm);
		scrollPane.setViewportView(listeConvives);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.SOUTH;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		contentPane.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAfficher = new JButton("Afficher");
		btnAfficher.setBackground(Color.GREEN);
		btnAfficher.setForeground(Color.BLACK);
		panel_3.add(btnAfficher);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(Color.YELLOW);
		panel_3.add(btnSupprimer);
		
		btnDetruire = new JButton("Detruire");
		btnDetruire.setBackground(Color.ORANGE);
		panel_3.add(btnDetruire);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setBackground(Color.RED);
		panel_3.add(btnQuitter);
		this.init();
	}
	
	private void init() {
		btnQuitter.addActionListener(e -> this.closeWindow());
		typeCombo.addActionListener(e -> eventCombo(e));	      
		btnDetruire.addActionListener(e -> Fsaisie.this.cv.Detruire());
		btnAfficher.addActionListener(this::btnafficher);
		btnSupprimer.addActionListener(this::supprimerConvive);
		btnAjouter.addActionListener(this::btnAjouter);
		//on sélectionne d'entrée l'item 0 dans la comboBox
		typeCombo.setSelectedIndex(0);
	}
	
	private void eventCombo(ActionEvent e) {
		//Si l'event sur la comboBox est un changement d'item sélectionné
		if (e.getActionCommand() == "comboBoxChanged") {
			String text = "";
			switch (typeCombo.getSelectedIndex()) {
			case 2:
				text = "Ancienneté";
				this.entryFeature.setToolTipText(this.tootlipFeature[1]);
				break;
			case 3:
				text = "Salaire";
				this.entryFeature.setToolTipText(this.tootlipFeature[2]);
				break;
			default:				
				text = "Formation";
				this.entryFeature.setToolTipText(this.tootlipFeature[0]);
				break;
			}
			lblCaracteristiques.setText(text);
		}
	}
	
	public void updateNbListeConvives(final int nb) {
		String str = String.format("Liste des convives :   %d convive", nb);
		if(nb > 1)
			str = str + "s";			
		this.lblListConvives.setText(str);
	}
	
	private void btnAjouter(ActionEvent e) {
		String prenom="";
		if(Fsaisie.this.entryPrenom.getText().length() > 0) {
			prenom = Fsaisie.this.entryPrenom.getText().substring(0,1).toUpperCase();
			if(Fsaisie.this.entryPrenom.getText().length() > 1) {
				prenom = prenom + Fsaisie.this.entryPrenom.getText().substring(1).toLowerCase();
			}
		}
		this.cv.ajouterConvive(Fsaisie.this.entryNom.getText().toUpperCase(),
				prenom,
				Fsaisie.this.entryAge.getText(),
				Fsaisie.this.entryFeature.getText(),
				Fsaisie.this.typeCombo.getSelectedIndex());
	}
	
	private void supprimerConvive(ActionEvent e) {
		//on vérifie si au moins 1 item est sélectionnée
		if (listeConvives.getSelectedIndex() > -1) {
			Fsaisie.this.cv.fSaisieSupprimer(listeConvives.getSelectedIndices());
		}
	}
	
	private void btnafficher(ActionEvent e) {
		this.cv.afficherFenHistorique();
		this.cv.afficherFenCalcul();
	}
	
//	private void closeWindow(WindowEvent e) {
//		this.closeWindow();
//	}
	
	private void closeWindow() {
		int choix = JOptionPane.showConfirmDialog(this, "Voulez-vous Quitter ?", "Quitter l'application", JOptionPane.YES_NO_OPTION);
		if (choix == JOptionPane.YES_OPTION) {
			Fsaisie.this.cv.Arreter();
		}		
	}
	
	public void clearListeConvives() {
		this.dlm.clear();
	}
	
	public void ajouterConviveListe(final String _c) {
		this.dlm.addElement(_c);
		this.entryNom.setText("");
		this.entryPrenom.setText("");
		this.entryAge.setText("");
		this.entryFeature.setText("");
	}
	
	public void setErrorNom(boolean _valid) {
		this.errorNom.setError(_valid);
	}
	
	public void setErrorPrenom(boolean _valid) {
		this.errorPrenom.setError(_valid);
	}
	
	public void setErrorAge(boolean _valid) {
		this.errorAge.setError(_valid);
	}
	
	public void setErrorCaracteristique(boolean _valid) {
		this.errorFeature.setText("Erreur " + this.lblCaracteristiques.getText());
		this.errorFeature.setError(_valid);
	}

	public void resetAllError() {
		this.setErrorNom(false);
		this.setErrorPrenom(false);
		this.setErrorAge(false);
		this.setErrorCaracteristique(false);
	}
}
