package vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controleur.ctrl;
import modele.colconvives;

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
	
	public ctrlvue(ctrl c) {
		this._c = c;
		
		this._fs = new Fsaisie(this);
		this._fc = new Fcalcul(this);
		this._fh = new Faffichage();
		//this._fa.setTitle("Affichage des personnes");
		this._fh.setTitle("Historique des erreurs");
		
		this.Repositionner();
		this.Demarrer();
	}
	
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
	
<<<<<<< HEAD
=======
	public ctrlvue(ctrl c) {
		this._c = c;
		
		this._fs = new Fsaisie(this);
		this._fc = new Fcalcul(this);
		this._fh = new Faffichage();
		this._fc.setTitle("Fenêtre des calcul");
		this._fh.setTitle("Historique des erreurs");
		
		this.Repositionner();
	}
	
>>>>>>> 17614cc0c64b9d09268a15d4ce271ce611ba2b27
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
	
	public void afficherFenHistorique() {
		this._fh.setVisible(true);	
	}
<<<<<<< HEAD
	
	public void afficherFenCalcul() {
		this._fc.setVisible(true);
=======
	
	public void AfficherFenCalcul() {
		this._fc.setVisible(true);			
	}
	
	/*
	public static String[] getNoms() {
		return(ctrl.getNoms());
	}
	
	public static String[] getAges() {
		return (ctrl.getAges());
>>>>>>> 17614cc0c64b9d09268a15d4ce271ce611ba2b27
	}
	
	public void fSaisieSupprimer(int[] selectIndices) {
		this._c.supprimer(selectIndices);
	}
		
	public void destroyAllItem() {
		this._fs.clearListeConvives();
	}
	
	public void ajouterConvive(final String _nom, final String _prenom, final String _age, final String _feature, final int _type) {
		boolean validNom = this._c.checkNom(_nom);
		boolean validPrenom = this._c.checkPrenom(_prenom);
		boolean validAge = this._c.checkAge(_age);
		boolean validFeature =  this._c.checkFeature(_feature, _type);	
		if (validNom & validPrenom & validAge & validFeature) {
			this._c.ajouterConvive(_nom, _prenom, _age, _feature, _type);
		}
		this._fs.setErrorNom(!validNom);
		this._fs.setErrorPrenom(!validPrenom);
		this._fs.setErrorAge(!validAge);
		this._fs.setErrorCaracteristique(!validFeature);
	}
	
<<<<<<< HEAD
	public void addListeConvive(final String _c) {
		this._fs.ajouterConviveListe(_c);
=======
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
	*/
	public static String getAidePb() {
		return (ctrl.getAidePb());
	}

	public static String AfficherPrix(String pb) {
		return (ctrl.AfficherPrix(pb));
		
	}

	public boolean VerifierPb(
			String pb,  
			String[] errpb) {
		boolean ok;
		
		errpb[0] = ctrl.VerifierPb(pb);
		
		ok =
			(errpb[0].equals(""));
		
		if (!errpb[0].equals(""))
			this._fh.Ajouter(errpb[0]);
		
		if (ok == false)
			this.AfficherFenHistorique();
		
		return (ok);
	}
	



	private void AfficherCalculs() {
		ArrayList<Object> lc;
		String snb;
		
		lc = this._c.getConvives();
		this._fc.Ajouter(lc);
		this.AfficherFenCalcul();
		
		snb = this._c.getNbPersonnes();
		this._fs.AfficherNbPersonnes(snb);
		this._fc.AfficherNbPersonnes(snb);
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
>>>>>>> 17614cc0c64b9d09268a15d4ce271ce611ba2b27
	}
	
	public void Detruire() {
		if (Questionner(
				"Voulez vous détruire les enregistrements")
				== true) {
			this._c.detruire();
			this._fs.clearListeConvives();
		}
		
	}


}
