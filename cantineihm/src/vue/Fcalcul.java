package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Fcalcul extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5812562113508762576L;
	
	private ctrlvue _cv;
	
	private JPanel contentPane;
	private JTextField TxtPb;
	private DefaultListModel<Object> _lc;
	private JLabel LabErr;
	private JList<Object> LstAffichage;
	private void CBFermer() {
		this.setVisible(false);
	}
	
	private void CBValider() {
		boolean ok;
		String pb;
		String serr;
		String[] errpb = new String[1];

		this._lc.clear();
		// recupération des données
		pb = this.TxtPb.getText();

		
		// vérification
		ok = this._cv.VerifierPb(
				pb,
				errpb);
		
		// traiter les messages d'erreur
		serr = errpb[0];
		if (!serr.equals(""))
			this.LabErr.setText(serr);
		else
			this.LabErr.setText("");

		// mémorisation
		if (ok == true) {
			this._lc.addElement( ctrlvue.AfficherPrix(pb) );
		}				
		
	}
	
	private void InitialiserIhm() {
		String aide;
		
		this.LabErr.setText("");
		
		aide = ctrlvue.getAidePb();
		TxtPb.setToolTipText(aide);
	
	}
	

	/**
	 * Create the frame.
	 */
	public Fcalcul(ctrlvue cv) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				CBFermer();
			}
		});
		this._cv = cv;
		
		setTitle("Fenetre de calcul");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{104, 104, 104, 104, 0};
		gbl_panel.rowHeights = new int[]{29, 28, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPb = new JLabel("Prix de base :");
		GridBagConstraints gbc_lblPb = new GridBagConstraints();
		gbc_lblPb.anchor = GridBagConstraints.NORTH;
		gbc_lblPb.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPb.insets = new Insets(0, 0, 5, 5);
		gbc_lblPb.gridx = 0;
		gbc_lblPb.gridy = 0;
		panel.add(lblPb, gbc_lblPb);
		
		TxtPb = new JTextField();
		GridBagConstraints gbc_TxtPb = new GridBagConstraints();
		gbc_TxtPb.anchor = GridBagConstraints.NORTH;
		gbc_TxtPb.fill = GridBagConstraints.HORIZONTAL;
		gbc_TxtPb.insets = new Insets(0, 0, 5, 5);
		gbc_TxtPb.gridx = 1;
		gbc_TxtPb.gridy = 0;
		panel.add(TxtPb, gbc_TxtPb);
		TxtPb.setColumns(10);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.anchor = GridBagConstraints.NORTH;
		gbc_horizontalGlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 2;
		gbc_horizontalGlue.gridy = 0;
		panel.add(horizontalGlue, gbc_horizontalGlue);
		
		JButton btnValider = new JButton("Valider");
		btnValider.setToolTipText("Permet d'afficher les prix des convives");
		btnValider.setBackground(Color.GREEN);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CBValider();
			}
		});
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.anchor = GridBagConstraints.NORTH;
		gbc_btnValider.insets = new Insets(0, 0, 5, 0);
		gbc_btnValider.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnValider.gridx = 3;
		gbc_btnValider.gridy = 0;
		panel.add(btnValider, gbc_btnValider);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_4 = new GridBagConstraints();
		gbc_horizontalGlue_4.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_4.gridx = 0;
		gbc_horizontalGlue_4.gridy = 1;
		panel.add(horizontalGlue_4, gbc_horizontalGlue_4);
		
		LabErr = new JLabel("");
		LabErr.setFont(new Font("Tahoma", Font.BOLD, 14));
		LabErr.setForeground(Color.RED);
		GridBagConstraints gbc_LabErr = new GridBagConstraints();
		gbc_LabErr.insets = new Insets(0, 0, 0, 5);
		gbc_LabErr.gridx = 1;
		gbc_LabErr.gridy = 1;
		panel.add(LabErr, gbc_LabErr);
		
		Component horizontalGlue_5 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_5 = new GridBagConstraints();
		gbc_horizontalGlue_5.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_5.gridx = 2;
		gbc_horizontalGlue_5.gridy = 1;
		panel.add(horizontalGlue_5, gbc_horizontalGlue_5);
		
		Component horizontalGlue_6 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_6 = new GridBagConstraints();
		gbc_horizontalGlue_6.gridx = 3;
		gbc_horizontalGlue_6.gridy = 1;
		panel.add(horizontalGlue_6, gbc_horizontalGlue_6);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblConvive = new JLabel("Convives, prix de base et participation :");
		panel_1.add(lblConvive);
		
		JScrollPane ScPanAffichage = new JScrollPane();
		GridBagConstraints gbc_ScPanAffichage = new GridBagConstraints();
		gbc_ScPanAffichage.insets = new Insets(0, 0, 5, 0);
		gbc_ScPanAffichage.fill = GridBagConstraints.BOTH;
		gbc_ScPanAffichage.gridx = 0;
		gbc_ScPanAffichage.gridy = 2;
		contentPane.add(ScPanAffichage, gbc_ScPanAffichage);
		
		LstAffichage = new JList<Object>();
		LstAffichage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		_lc = new DefaultListModel<Object>();
		LstAffichage.setModel(_lc);
		ScPanAffichage.setViewportView(LstAffichage);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.SOUTH;
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		contentPane.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_3);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_2);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_1);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.setToolTipText("Permet de cacher la fen\u00EAtre");
		btnFermer.setBackground(Color.ORANGE);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CBFermer();
			}
		});
		panel_3.add(btnFermer);
		
		this.InitialiserIhm();
	}

}
