// Classe collectionneur de convives
// de type COMPOSITION

package modele;

import java.util.ArrayList;

public class colconvives {
	// **********
	// Constantes
	// **********
	public static final String STAGIAIRE = "Stagiaire";
	public static final String STAGIAIRE_CIF = "Stagiaire Cif";
	public static final String FORMATEUR = "Formateur";
	public static final String DIRECTEUR = "Directeur";
	
	private static final String NOMDEF = "LES CONVIVES";
	
	// ********************
	// Attributs d'instance
	// ********************
	
	// un ensemble de convives
	private ArrayList<convive> _lc;
	
	// un nom
	private String _nom;
	
	// ********************
	// Méthodes d'instance
	// ********************

	// Les constructeurs
	// -----------------
	
	private void Initialiser() {
		this._lc = new ArrayList<convive>();
		this.setNom(NOMDEF);
	}
	
	public colconvives() {
		this.Initialiser();
	}
	
	public colconvives(final String nom) {
		this.Initialiser();
		this.setNom(nom);
	}
	
	// Les accesseurs
	// -----------------
	
	public void setNom(final String n) {
		this._nom = n;
	}
	
	public String getNom() {
		return (this._nom);
	}
	
	// Gestion de la mémoire
	// ---------------------

	// *** AJOUTS ***
	
	// méthode d'ajout de type agrégation
	/*
	public void Ajouter(final convive c) {
		this._lc.add(c);
	}
	*/
	
	// méthodes d'ajout de type composition
	// Chaque méthode retourne l'objet qui a été instancié
	// et mémorisé, afin qu'il soit valorisé.
	
//	public stagiaire AjouterStagiaire() {
//		stagiaire c;
//		c = new stagiaire();
//		this._lc.add(c);
//		return (c);
//	}
	
	public stagiaire ajouterStagiaire(final String _nom, final String _prenom, final int _age, final String _feature) {
		stagiaire c;
		c = new stagiaire(_nom, _prenom, _age);
		this._lc.add(c);
		c.setFormation(_feature);
		return (c);	
	}
	
//	public stagiaireCif AjouterStagiaireCif() {
//		stagiaireCif c;
//		c = new stagiaireCif();
//		this._lc.add(c);
//		return (c);
//	}
	
	public stagiaireCif ajouterStagiaireCif(final String _nom, final String _prenom, final int _age, final String _formation) {
		stagiaireCif c;
		c = new stagiaireCif(_nom, _prenom, _age);
		c.setFormation(_formation);
		this._lc.add(c);
		return (c);
	}
	
//	public formateur AjouterFormateur() {
//		formateur c;
//		c = new formateur();
//		this._lc.add(c);
//		return (c);
//	}
//	
//	public directeur AjouterDirecteur() {
//		directeur c;
//		c = new directeur();
//		this._lc.add(c);
//		return (c);
//	}
	public formateur ajouterFormateur(final String _nom, final String _prenom, final int _age, final String _anciennete) {
		formateur c;
		c = new formateur(_nom, _prenom, _age);
		c.setNbannees(_anciennete);
		this._lc.add(c);
		return (c);
	}
	
	public directeur ajouterDirecteur(final String _nom, final String _prenom, final int _age, final String _salaire) {
		directeur c;
		c = new directeur(_nom, _prenom, _age);
		c.setSalaire(_salaire);
		this._lc.add(c);
		return (c);
	}
	
	// l contient la liste des objets à ajouter
//	public void Ajouter(final ArrayList<convive> l) {
//		// ERREUR MONUMENTALE
//		// CAR ON A AFAIRE A UN COLLECTIONNEUR
//		// DE TYPE COMPOSITION !!!
//		/*
//		int i;
//		for (i=0;i<l.size();i++)
//			this._lc.add(l.get(i));
//		*/
//		int i;
//		convive c;
//		
//		// parcours de la liste des objets à ajouter
//		for (i=0;i<l.size();i++) {
//			c = l.get(i);
//			if (c.getType()==formateur.getTypeClasse()) {
//				this.AjouterFormateur().Copier(c);
//			}
//			if (c.getType()==directeur.getTypeClasse()) {
//				this.AjouterDirecteur().Copier(c);
//			}
//			if (c.getType()==stagiaire.getTypeClasse()) {
//				this.AjouterStagiaire().Copier(c);
//			}
//			if (c.getType()==stagiaireCif.getTypeClasse()) {
//				this.AjouterStagiaireCif().Copier(c);
//			}
//		}		
//	}
	
	// concatenation de collectionneurs
	// this = this+src
//	public void Ajouter(final colconvives src) {
//		ArrayList<convive> l;
//		l = new ArrayList<convive>(src._lc);
//		this.Ajouter(l);
//	}
	
	// *** ACCES ***
	
	// retourne true si le convive existe
	// à l'emplacement i
	private boolean TesterExistence(final int i) {
		boolean ok;
		ok = (i>=0)&&(i<this.getNbConvives());
		return(ok);
	}
	
	// retourne le nombre de convives
	// mémorisés
	public int getNbConvives() {
		return (this._lc.size());
	}
	
	// retourne le convive situé
	// à l'emplacement i
	// retourne le convive ou
	// null si le convive n'existe pas
	public convive getConvive(final int i) {
		boolean ok;
		convive c;
		
		ok = this.TesterExistence(i);
		if (ok == true)
			c = this._lc.get(i);
		else
			c = null;
		return (c);
	}
	
	// *** SUPPRESSION ***
	
	public boolean Supprimer(final int i) {
		boolean ok;
		ok = this.TesterExistence(i);
		if (ok == true)
			this._lc.remove(i);
		return (ok);
	}
	
	public boolean Supprimer(final convive c) {
		boolean ok;
		ok =this._lc.remove(c);
		return (ok);
	}
	
	public void SupprimerAll() {
		this._lc.clear();
	}
	
	public String toString(final int i) {
		String s;
		convive c;
		
		c = this.getConvive(i);
		if (c == null)
			s = null;
		else
			s = ""+c;
		return (s);
	}
	
	@Override
	public String toString() {
		String s,sc;
		int nb,i;

		nb = this.getNbConvives();
		s = 
			"Collectionneur:  "+this.getNom()+
			" ["+nb+" convives]";
		s +="\n";
		for (i=0;i<nb;i++) {
			sc = this.toString(i);
			if (sc != null)
				s += sc+"\n";			
		}
		return (s);
	}
	
	// méthodes de calcul
	
	public boolean CalculerPrix(
			final int i,
			final double pb, 
			double[] p) {
		convive c;
		
		c = this.getConvive(i);
		if (c == null) return (false);
		return (c.CalculerPrix(pb,p));
	}
	
	public String CalculerPrix(
			final int i,
			final double pb) {
		convive c;
		
		c = this.getConvive(i);
		if (c == null) return (null);
		return (c.CalculerPrix(pb));
	}
	
	public String CalculerPrix(
			final int i,
			final String pb) {
		convive c;
		
		c = this.getConvive(i);
		if (c == null) return (null);
		return (c.CalculerPrix(pb));
	}
	
	// méthodes de recherches
	
	public ArrayList<convive> Chercher(
			final int type) {
		ArrayList<convive> l;
		int i;
		int nb;
		convive c;
		
		l = new ArrayList<convive>();
		nb = this.getNbConvives();
		for (i=0;i<nb;i++) {
			c = this.getConvive(i);
			if (c != null) {
				if (c.getType()==type)
					l.add(c);
			}
		}
		return (l);
	}
	
	public ArrayList<convive> Chercher(
			final convive src) {
		ArrayList<convive> l;
		int i;
		int nb;
		convive c;
		
		l = new ArrayList<convive>();
		nb = this.getNbConvives();
		for (i=0;i<nb;i++) {
			c = this.getConvive(i);
			if (c != null) {
				if (c.Comparer(src)==true)
					l.add(c);
			}
		}
		return (l);		
	}
	
	private static void DefinirModeRecherche(
			final convive c,
			final int modetri
			) {
		
		c.setModeRechercheNom(100);
		c.setModeRecherchePrenom(100);
		c.setModeRechercheAge(100);
		c.setModeRechercheCaracteristiques(false);

		switch (modetri) {
		case 1:
			c.setModeRechercheNom(1);
			break;
		case -1:
			c.setModeRechercheNom(-1);
			break;
		case 2:
			c.setModeRecherchePrenom(1);
			break;
		case -2:
			c.setModeRecherchePrenom(-1);
			break;
		case 3:
			c.setModeRechercheAge(1);
			break;
		case -3:
			c.setModeRechercheAge(-1);
			break;
		default:
			break;
		}
	}
	
	// mode de tri
	//	1 -> par nom croissant
	//	-1 -> par nom décroissant
	// 	2 -> par prenom croissant
	// 	-2 -> par prenom décroissant4
	//	3 -> par age croissant
	//	-3 -> par age décroissant
	
	// algo du tri à bulle ..

	public void Trier(
			final int modetri) {
		int nb,i;
		boolean inversion;
		boolean trier;
		convive c1,c2;
		int modeinitnom,modeinitprenom,modeinitage;
		boolean modeinitcara;
		
		trier = 
				(modetri==1)||
				(modetri==-1)||
				(modetri==2)||
				(modetri==-2)||
				(modetri==3)||
				(modetri==-3);
				
		if (trier == false) return;
		
		nb = this.getNbConvives();
		do {
			inversion = false;
			for (i=0;i<nb-1;i++) {
				c1 = this._lc.get(i);
				c2 = this._lc.get(i+1);	
				if ((c1!=null) && (c2!=null)) {
					modeinitnom = c2.getModeRechercheNom();
					modeinitprenom = c2.getModeRecherchePrenom();
					modeinitage = c2.getModeRechercheAge();
					modeinitcara = c2.getModeRechercheCaracteristiques();
					DefinirModeRecherche(c2, modetri);
					if (c1.Comparer(c2,false)==true) {
						this._lc.set(i,c2);
						this._lc.set(i+1,c1);
						inversion = true;
					}
					c2.setModeRechercheNom(modeinitnom);
					c2.setModeRecherchePrenom(modeinitprenom);
					c2.setModeRechercheAge(modeinitage);
					c2.setModeRechercheCaracteristiques(modeinitcara);
				}
			}
		}while (inversion == true);
	}
}
