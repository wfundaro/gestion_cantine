package controleur;

import java.util.Random;

import modele.colconvives;
import modele.convive;
import modele.directeur;
import modele.formateur;
import modele.stagiaire;
import vue.ctrlvue;

public class ctrl {
	
	private final static Random rnd = new Random();
	
	private final static String[] tnoms= {
			"NOMUN",
			"NOMDEUX",
			"NOMTROIS",
			"FUNDARO",
			"BOULLIER",
			"NOMVINGTETUNCARACTERE",
			"N0M1",
			"",
			"NOMQUATRE",
			"NOMCINQ",
			"NOMSIX"
	};
	
	private final static String[] tprenoms= {
			"PRENOMUN",
			"PRENOMDEUX",
			"PRENOMTROIS",
			"WILLIAM",
			"MICHAEL",
			"PRENOMSEIZECARAC",
			"PREN0M1",
			"",
			"PRENOMQUATRE",
			"PRENOMCINQ",
			"PRENOMSIX"
	};
	
	private final static String[] tages= {
			"12",
			"130",
			"43",
			"",
			"-12",
			"35",
			"dddd",
			"19",
			"25",
			"30",
			"40",
	};
	
	private final static String[] tsalaires= {
			"12345.67",
			"23456,89",
			"25000.00",
			"30000,50",
			"36090,12",
			"40000,34",
			"45000,34",
			"-12.3",
			"-23,4",
			"",
			"dddd"
	};
	
	private final static String[] tannees= {
			"1",
			"5",
			"10",
			"15",
			"20",
			"25",
			"42",
			"-12.3",
			"",
			"dddd"
	};
	
	private final static String[] tformations= {
			"CDA",
			"CDI",
			"F",
			"FORMATION",
			"DWWM",
			"FORMATIONER",
			"WEBDES",
			"ARH",
			"FORMATION1",
			"",
	};
	
	public static String[] getNoms() {
		return (tnoms);
	}
	
	public static String[] getPrenoms() {
		return (tprenoms);
	}
	
	public static String[] getAges() {
		return (tages);
	}
	
	public static String[] getFormations() {
		return (tformations);
	}
	
	public static String[] getAnnees() {
		return (tannees);
	}
	
	public static String[] getSalaires() {
		return (tsalaires);
	}
	
	private static String TirerSort(final String[] t) {
		int i;
		
		i = rnd.nextInt(t.length);
		return (t[i]);
	}
	
	public static String getUnNom() {
		return (TirerSort(tnoms));
	}
	
	public static String getUnPrenom() {
		return (TirerSort(tprenoms));
	}
	
	public static String getUnAge() {
		return (TirerSort(tages));
	}
	
	public static String getUneFormation() {
		return (TirerSort(tformations));
	}
	
	public static String getUneAnnee() {
		return (TirerSort(tannees));
	}
	
	public static String getUnSalaire() {
		return (TirerSort(tsalaires));
	}
	
	
	// collectionneurs de convives (modele)
	private colconvives colC;
	// Dialogue avec la VUE au travers du controleur de vue
	private ctrlvue _cv;
	
	public ctrl(){
		this._cv = new ctrlvue(this);
		this.colC = new colconvives("LES CONVIVES");
	}
	
	public void Demarrer() {
		this._cv.Demarrer();
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
	
	public void detruire() {
		colC.SupprimerAll();
		this._cv.updateNbListeConviveToFsaisie(colC.getNbConvives());
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
			directeur.AideSalaire();
			break;
		default:
			colC.ajouterStagiaire(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		}
		this.ajouterConviveToVue(colC.getNbConvives() - 1);
	}
	
	private void ajouterConviveToVue(final int index) {
		this._cv.addListeConvive(this.colC.toString(index));
		this._cv.updateNbListeConviveToFsaisie(colC.getNbConvives());
	}
	
	private void ajouterAllConviveToVue() {
		this._cv.destroyAllItem();
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
		this._cv.ajouterLigneToHistorique(convive.AideNom());
		return false;
	}

	public boolean checkPrenom(final String _prenom) {
		if(_prenom.matches("[a-zA-Z]{1,15}")) {
			return true;
		}
		this._cv.ajouterLigneToHistorique(convive.AidePrenom());
		return false;
	}

	public boolean checkAge(final String _age) {
		boolean result = false;
		try {
			int age = Integer.parseInt(_age);
			if(age > 17 & age < 68)
				result = true;
		} catch (Exception e) {
			result = false;
		}
		if(!result) {
			this._cv.ajouterLigneToHistorique(convive.AideAge());
		}
		return result;
	}
	
	
	public String AfficherPrix(String pb) {
		String p, r;
		int i;
		int nb = colC.getNbConvives();
		
		r ="";
		if (nb > 0) {
			for (i=0 ; i<nb ; i++) {
				p = colC.CalculerPrix(i, pb);
				if (p!=null) {
					r = r +
						"" + colC.getConvive(i)+
						"\n\t"+
						"Prix de base: "+
						pb+
						" -> Prix: "+
						p + 
						"\n";
				}
			}
		}
		return (r);
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
			if(!result) {
				this._cv.ajouterLigneToHistorique(formateur.AideNbannees());
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
			if (!result) {
				this._cv.ajouterLigneToHistorique(directeur.AideSalaire());
			}
			break;
		default:
			if(_feature.matches("[a-zA-Z]{1,10}"))
				result = true;
			if(!result) {
				this._cv.ajouterLigneToHistorique(stagiaire.AideFormation());
			}
			break;
		}
		return result;
	}

}
