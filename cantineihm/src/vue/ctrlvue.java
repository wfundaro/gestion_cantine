package vue;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controleur.ctrl;

public class ctrlvue {
	private enum EPOSITION{
		ENHAUT,
		ENBAS,
		ADROITE,
		AGAUCHE
	};
	
	private Fsaisie _fs;
	private Fcalcul _fc;
	private Faffichage _fh;
	
	private ctrl _c;
	
	private static void Positionner(
			JFrame f,
			int x,
			int y) {
		f.setLocation(x, y);
	}
	
	private static void Positionner(
			JFrame fd,
			JFrame fs,
			EPOSITION p) {
		int x,y;
		
		switch (p) {
		case ENHAUT:
			x = fs.getX();
			y = fs.getY()-fd.getHeight();
			break;
		case ENBAS:
			x = fs.getX();
			y = fs.getY()+fs.getHeight();
			break;
		case AGAUCHE:
			x = fs.getX()-fd.getWidth();
			y = fs.getY();
			break;
		case ADROITE:
			x = fs.getX()+fs.getWidth();
			y = fs.getY();
			break;
		default:
			x = fs.getX();
			y = fs.getY();
			break;
		}
		Positionner(fd, x, y);
	}
	
	public void Repositionner() {
		Positionner(this._fs, 100, 100);
		Positionner(this._fc,this._fs, EPOSITION.ENBAS);
		Positionner(this._fh,this._fs, EPOSITION.ADROITE);			
	}
	
	public ctrlvue(ctrl c) {
		this._c = c;
		
		this._fs = new Fsaisie(this);
		this._fc = new Fcalcul(this);
		this._fh = new Faffichage();
		//this._fa.setTitle("Affichage des personnes");
		this._fh.setTitle("Historique des erreurs");
		
		this.Repositionner();
	}
	
	public void Demarrer() {
		this._fs.setVisible(true);
		this._fc.setVisible(true);
		this._fh.setVisible(true);
	}
	
	public static void Informer(final String s) {
		JOptionPane.showMessageDialog(
				null,
				s,
				"Information",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean Questionner(final String q) {
		int r;
		boolean ret;
		
		r = JOptionPane.showConfirmDialog(
				null,
				q,
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
				);
		if (r == JOptionPane.YES_OPTION)
			ret = true;
		else
			ret = false;
		return (ret);	
	}

	public void Arreter() {
		this._fs.dispose();
		this._fc.dispose();
		this._fh.dispose();
	}
	
	public void AfficherFenHistorique() {
		this._fh.setVisible(true);	
	}

	public void AfficherFenAffichage() {
		this._fa.setVisible(true);			
	}
	
	public static String[] getNoms() {
		return(ctrl.getNoms());
	}
	
	public static String[] getAges() {
		return (ctrl.getAges());
	}
	
	public static String[] getSalaires() {
		return (ctrl.getSalaires());
	}
	
	public static String getUnNom() {
		return (ctrl.getUnNom());
	}
	
	public static String getUnAge() {
		return (ctrl.getUnAge());
	}
	
	public static String getUnSalaire() {
		return (ctrl.getUnSalaire());
	}

	public static String getAideNom() {
		return (ctrl.getAideNom());
	}

	public static String getAideAge() {
		return (ctrl.getAideAge());
	}

	public static String getAideSalaire() {
		return (ctrl.getAideSalaire());
	}

	public boolean Verifier(
			String n, 
			String a, 
			String sal, 
			String[] errn, 
			String[] erra, 
			String[] errsal) {
		boolean ok;
		
		errn[0] = ctrl.VerifierNom(n);
		erra[0] = ctrl.VerifierAge(a);
		errsal[0] = ctrl.VerifierSalaire(sal);
		
		ok =
			(errn[0].equals(""))&&
			(erra[0].equals(""))&&
			(errsal[0].equals(""));
		
		if (!errn[0].equals(""))
			this._fh.Ajouter(errn[0]);
		
		if (!erra[0].equals(""))
			this._fh.Ajouter(erra[0]);
		
		if (!errsal[0].equals(""))
			this._fh.Ajouter(errsal[0]);
		
		if (ok == false)
			this.AfficherFenHistorique();
		
		return (ok);
	}


	private void AfficherPersonnes() {
		ArrayList<Object> lo;
		String snb;
		
		lo = this._c.getPersonnes();
		this._fa.Ajouter(lo);
		this.AfficherFenAffichage();
		
		snb = this._c.getNbPersonnes();
		this._fs.AfficherNbPersonnes(snb);
		this._fc.AfficherNbPersonnes(snb);
	}
	
	public void AjouterPersonne(
			String n, 
			String a, 
			String sal) {
		this._c.Ajouter(n,a,sal);
		this.AfficherPersonnes();
	}

	public void Detruire() {
		if (Questionner(
				"Voulez vous détruire les enregistrements")
				== true) {
			this._c.Detruire();
			this.AfficherPersonnes();
		}
		
	}
}
