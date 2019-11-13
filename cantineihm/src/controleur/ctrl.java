package controleur;

import modele.colconvives;
import vue.ctrlvue;

public class ctrl {
<<<<<<< HEAD
=======
	private static final int ACT_CREERSTAGIAIRE = 
			10;
	private static final int ACT_CREERSTAGIAIRECIF = 
			ACT_CREERSTAGIAIRE+1;
	private static final int ACT_CREERDIRECTEUR = 
			ACT_CREERSTAGIAIRECIF+1;
	private static final int ACT_CREERFORMATEUR = 
			ACT_CREERDIRECTEUR+1;
	private static final int ACT_AFFICHERCONVIVES = 
			ACT_CREERFORMATEUR+1;
	private static final int ACT_AFFICHERSTAGIAIRES = 
			ACT_AFFICHERCONVIVES+1;
	private static final int ACT_AFFICHERSTAGIAIRESCIF = 
			ACT_AFFICHERSTAGIAIRES+1;
	private static final int ACT_AFFICHERDIRECTEURS = 
			ACT_AFFICHERSTAGIAIRESCIF+1;
	private static final int ACT_AFFICHERFORMATEURS = 
			ACT_AFFICHERDIRECTEURS+1;
	private static final int ACT_CALCULER = 
			ACT_AFFICHERFORMATEURS+1;
	private static final int ACT_DETRUIRE = 
			ACT_CALCULER+1;
	private static final int ACT_QUITTER = 
			ACT_DETRUIRE+1;
	private static final int ACT_CREER = 
			ACT_QUITTER+1;
	
	// les tableaux de données
	private static String[] tnoms= {
		"NOMUN",
		"NOMDEUX",
		"NOMTROIS",
		"NOMQUATRE",
		"NOMCINQ"
	};
	private static String[] tprenoms= {
		"PRENOMUN",
		"PRENOMDEUX",
		"PRENOMTROIS",
		"PRENOMQUATRE"
		};
	private static String[] tages= {
		"32",
		"23",
		"44",
		"55",
		"61"
	};
	private static String[] tnbannees= {
		"2",
		"4",
		"8",
		"10",
		"12"
	};
	private static String[] tformations= {
		"CD1234",
		"DW3456",
		"DL1023",
		"TS9999",
		"GD0007"
	};
	private static String[] tsalaires= {
		"12454,67",
		"53456.98",
		"59999.97",
		"41055,01",
		"47500,99"
	};
	
	private static Random rnd = new Random();
		
	// menu (vue)
	private static menu m;
	
	// collectionneurs de convives (modele)
	private static colconvives cc;
	private static colconvives cs;
	private static colconvives csc;
	private static colconvives cd;
	private static colconvives cf;
	
	// Dialogue avec la VUE au travers du controleur de vue
	private ctrlvue _cv;
	
	// Dialogue avec le MODELE au travers du collectionneur de personnes
	private colconvives _cp;
	
	public ctrl(){
		this._cv = new ctrlvue(this);
		this._cp = new colconvives("LES CONVIVES");
	}
	
	public void Demarrer() {
		this._cv.Demarrer();
	}
	
	// méthode de classe privée permettant
	// d'obtenir une valeur entière aléatoire 
	// comprise entre 2 bornes
	private static int TirerAusort(
			final int vmini,
			final int vmaxi) {
		int v;
		
		v = rnd.nextInt(vmaxi-vmini+1)+vmini;
		return (v);	
	}
	
	// méthode de classe privée permettant
	// d'obtenir un string aléatoire
	// dans un tableau de strings
	private static String TirerAusort(
			final String[] ts) {
		String s;
		int i;
		
		i = TirerAusort(0, ts.length-1);
		s = ts[i];
		return (s);
	}
	
	private static void CreerMenu() {
		m.SauterUneLigne();
		m.Ajouter(
				"S",
				"Creer un convive stagiaire",
				ACT_CREERSTAGIAIRE);
		m.Ajouter(
				"SC",
				"Creer un convive stagiaire CIF",
				ACT_CREERSTAGIAIRECIF);
		m.Ajouter(
				"D",
				"Creer un convive directeur",
				ACT_CREERDIRECTEUR);
		m.Ajouter(
				"F",
				"Creer un convive formateur",
				ACT_CREERFORMATEUR);
		m.SauterUneLigne();
		m.Ajouter(
				"A",
				"Afficher les convives",
				ACT_AFFICHERCONVIVES);
		m.Ajouter(
				"AS",
				"Afficher les stagiaires",
				ACT_AFFICHERSTAGIAIRES);
		m.Ajouter(
				"ASC",
				"Afficher les stagiaire CIF",
				ACT_AFFICHERSTAGIAIRESCIF);
		m.Ajouter(
				"AD",
				"Afficher les directeurs",
				ACT_AFFICHERDIRECTEURS);
		m.Ajouter(
				"AF",
				"Afficher les formateurs",
				ACT_AFFICHERFORMATEURS);
		m.SauterUneLigne();
		m.Ajouter(
				"C",
				"Creer des convives",
				ACT_CREER);
		m.Ajouter(
				"P",
				"Calculer les prix",
				ACT_CALCULER);
		m.Ajouter(
				"DC",
				"Detruire les convives",
				ACT_DETRUIRE);
		m.SauterUneLigne();
		m.Ajouter(
				"Q",
				"Quitter",
				ACT_QUITTER);
		m.Ajouter(
				"q",
				"Quitter",
				ACT_QUITTER);
		m.Ajouter(
				"X",
				"Quitter",
				ACT_QUITTER);
		m.Ajouter(
				"x",
				"Quitter",
				ACT_QUITTER);
	}
	
	public static String getAidePb() {
		return (convive.AidePrixBase());
	}
	
	public static String VerifierPb(String pb) {
		boolean ok;
		String serr;
		int[] err = new int[1];
		
		serr = "";
		ok = convive.VerifierPrixBase(pb, err);
		if (ok == false) {
			serr = 
				"Prix de base incorrect -> "+
				convive.DecoderErreur(err[0]);
		}
		return (serr);
	}
	
	private static String SaisirNom() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un nom";
		q += "\n\t";
		q += " ("+convive.AideNom()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = convive.VerifierNom(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirPrenom() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un prenom";
		q += "\n\t";
		q += " ("+convive.AidePrenom()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = convive.VerifierPrenom(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirAge() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un age";
		q += "\n\t";
		q += " ("+convive.AideAge()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = convive.VerifierAge(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirFormation() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
>>>>>>> 17614cc0c64b9d09268a15d4ce271ce611ba2b27
		
	private static ctrlvue ctv;
	private static colconvives colC;
	
	public ctrl() {
		this.init();
	}
	
	private void init() {
		ctv = new ctrlvue(this);
		colC = new colconvives();
	}
	
	public void detruire() {
		colC.SupprimerAll();
	}
		
	public void ajouterConvive(final String _nom, final String _prenom, final String _age, final String _feature, final int _type) {
		switch (_type) {
		case 1:
			colC.ajouterStagiaireCif(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		case 2:
			colC.ajouterFormateur(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		case 3:
			colC.ajouterDirecteur(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		default:
			colC.ajouterStagiaire(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		}
		this.ajouterConviveToVue(colC.getNbConvives() - 1);
	}
	
	private void ajouterConviveToVue(final int index) {
		this.ctv.addListeConvive(this.colC.toString(index));
	}
	
	private void ajouterAllConviveToVue() {
		this.ctv.destroyAllItem();
		for(int i=0; i < this.colC.getNbConvives(); i++) {
			this.ajouterConviveToVue(i);
		}
	}
	
	public void supprimer(int[] selectIndices) {
		for (int j = selectIndices.length-1; j >= 0; j--) {
		    colC.Supprimer(selectIndices[j]);
		}
		ajouterAllConviveToVue();
	}

	public boolean checkNom(final String _nom) {
		if(_nom.matches("^[a-zA-Z]{1,20}")) {
			return true;
		}
		return false;
	}

	public boolean checkPrenom(final String _prenom) {
		if(_prenom.matches("[a-zA-Z]{1,15}")) {
			return true;
		}
		return false;
	}
<<<<<<< HEAD

	public boolean checkAge(final String _age) {
		boolean result = false;
		try {
			int age = Integer.parseInt(_age);
			if(age > 17 & age < 68)
				result = true;
		} catch (Exception e) {
			result = false;
=======
	
	
	public static String AfficherPrix(String pb) {
		String p, r;
		int i;
		int nb;
		
		r ="";
		if (cc.getNbConvives()>0) {
			nb = cc.getNbConvives();
			for (i=0;i<nb;i++) {
				p = cc.CalculerPrix(i, pb);
				if (p!=null) {
					r =
						""+cc.getConvive(i)+
						"\n\t"+
						"Prix de base: "+
						pb+
						" -> Prix: "+
						p;
				}
			}
		}
		return (r);
	}
	
	public ArrayList<Object> getConvives(){
		ArrayList<Object> lc;
		int nb;
		convive c;
		int i;
		
		lc = new ArrayList<Object>();
		nb = this._cp.getNbConvives();
		for (i=0;i<nb;i++) {
			c = this._cp.getConvive(i);
			if (c!= null) {
				lc.add(c);
			}
		}
		return (lc);
	}
	
	
	
	private static void Calculer() {
		String pb,p;
		int i;
		int nb;
		
		if (cc.getNbConvives()>0) {
			pb = SaisirPrixdebase();
			nb = cc.getNbConvives();
			for (i=0;i<nb;i++) {
				p = cc.CalculerPrix(i, pb);
				if (p!=null) {
					System.out.println(
						cc.getConvive(i)+
						"\n\t"+
						"Prix de base: "+
						pb+
						" -> Prix: "+
						p);
				}
			}
			saisies.Attendre();
>>>>>>> 17614cc0c64b9d09268a15d4ce271ce611ba2b27
		}
		return result;
	}

	public boolean checkFeature(final String _feature, final int _type) {
		boolean result = false;
		switch (_type) {
		case 2:
			try {
				int anciennete = Integer.parseInt(_feature);
				if(anciennete > 0 & anciennete < 43)
					result = true;
			} catch (Exception e) {
				result = false;
			}
			break;
		case 3:
			try {
				double salaire = Double.parseDouble(_feature.replaceAll(",", "."));
				if(salaire > 0)
					result = true;
			} catch (Exception e) {
				result = false;
			}			
			break;
		default:
			if(_feature.matches("[a-zA-Z]{1,10}"))
				result = true;
			break;
		}
		return result;
	}

}
