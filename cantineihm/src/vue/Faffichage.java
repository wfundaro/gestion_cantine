package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Faffichage extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5285081180560490624L;
	
	private DefaultListModel<Object> _la;

	private void CBEffacer() {
		this._la.clear();
	}
	
	private void CBFermer() {
		this.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public Faffichage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				CBFermer();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 430, 380);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{95.0, 5.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane ScPanAffichage = new JScrollPane();
		GridBagConstraints gbc_ScPanAffichage = new GridBagConstraints();
		gbc_ScPanAffichage.insets = new Insets(0, 0, 5, 0);
		gbc_ScPanAffichage.fill = GridBagConstraints.BOTH;
		gbc_ScPanAffichage.gridx = 0;
		gbc_ScPanAffichage.gridy = 0;
		contentPane.add(ScPanAffichage, gbc_ScPanAffichage);
		
		JList<Object> LstAffichage = new JList<Object>();
		LstAffichage.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ScPanAffichage.setViewportView(LstAffichage);
		
		JPanel PanBoutons = new JPanel();
		PanBoutons.setBackground(new Color(255, 255, 255, 0));
		GridBagConstraints gbc_PanBoutons = new GridBagConstraints();
		gbc_PanBoutons.fill = GridBagConstraints.BOTH;
		gbc_PanBoutons.gridx = 0;
		gbc_PanBoutons.gridy = 1;
		contentPane.add(PanBoutons, gbc_PanBoutons);
		PanBoutons.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton BtnEffacer = new JButton("Effacer");
		BtnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CBEffacer();
			}
		});
		BtnEffacer.setToolTipText("Permet d'effacer la liste");
		BtnEffacer.setBackground(new Color(255, 215, 0));
		PanBoutons.add(BtnEffacer);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		PanBoutons.add(horizontalGlue);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		PanBoutons.add(horizontalGlue_1);
		
		JButton BtnFermer = new JButton("Fermer");
		BtnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CBFermer();
			}
		});
		BtnFermer.setToolTipText("Permet de fermer la fenetre");
		BtnFermer.setBackground(new Color(244, 164, 96));
		PanBoutons.add(BtnFermer);
		
		this._la = new DefaultListModel<Object>();
		LstAffichage.setModel(this._la);
	}

	public void Ajouter(String s) {
		this._la.addElement(s);
		
	}

	public void Ajouter(
			ArrayList<Object> lo) {
		this._la.removeAllElements();
		int i;
		
		//for (Object o:lo) {
		//	this._la.addElement(o);
		//}
		
		for (i=0;i<lo.size();i++) {
			this._la.addElement(lo.get(i));
		}	
	}
}
