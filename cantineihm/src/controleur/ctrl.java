package controleur;

import java.util.ArrayList;
import java.util.Random;

import modele.*;
import vue.*;

public class ctrl {
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
		
		q = "Merci de saisir une formation";
		q += "\n\t";
		q += " ("+stagiaire.AideFormation()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = stagiaire.VerifierFormation(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirAnciennete() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un nombre d'annees";
		q += "\n\t";
		q += " ("+formateur.AideNbannees()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = formateur.VerifierNbannees(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirSalaire() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un salaire";
		q += "\n\t";
		q += " ("+directeur.AideSalaire()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = directeur.VerifierSalaire(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static String SaisirPrixdebase() {
		String s;
		String q;
		boolean ok;
		int[] cerr = new int[1];
		
		q = "Merci de saisir un prix de base";
		q += "\n\t";
		q += " ("+convive.AidePrixBase()+")";
		do {
			s = saisies.SaisirChaine(q);
			ok = convive.VerifierPrixBase(s, cerr);
			if (ok == false) {
				System.out.println(
						"La valeur saisie est incorrecte -> "+
						convive.DecoderErreur(cerr[0]));
			}
		}while (ok == false);
		return (s);
	}
	
	private static void CreerStagiaire() {
		String nom,prenom,age,formation;
		stagiaire s;
		
		// saisir et vérifier
		nom = SaisirNom();
		prenom = SaisirPrenom();
		age = SaisirAge();
		formation = SaisirFormation();
		
		// ajouter et valoriser
		s = cc.AjouterStagiaire();
		s.setNom(nom);
		s.setPrenom(prenom);
		s.setAge(age);
		s.setFormation(formation);
	}
	
	private static void CreerStagiaireCif() {
		String nom,prenom,age,formation;
		stagiaire scif;
		
		// saisir et vérifier
		nom = SaisirNom();
		prenom = SaisirPrenom();
		age = SaisirAge();
		formation = SaisirFormation();
		
		// ajouter et valoriser
		scif = cc.AjouterStagiaireCif();
		scif.setNom(nom);
		scif.setPrenom(prenom);
		scif.setAge(age);
		scif.setFormation(formation);
	}
	
	private static void CreerFormateur() {
		String nom,prenom,age,nbannees;
		formateur f;
		
		// saisir et vérifier
		nom = SaisirNom();
		prenom = SaisirPrenom();
		age = SaisirAge();
		nbannees = SaisirAnciennete();
		
		// ajouter et valoriser
		f = cc.AjouterFormateur();
		f.setNom(nom);
		f.setPrenom(prenom);
		f.setAge(age);
		f.setNbannees(nbannees);
	}
	
	private static void CreerDirecteur() {
		String nom,prenom,age,salaire;
		directeur d;
		
		// saisir et vérifier
		nom = SaisirNom();
		prenom = SaisirPrenom();
		age = SaisirAge();
		salaire = SaisirSalaire();
		
		// ajouter et valoriser
		d = cc.AjouterDirecteur();
		d.setNom(nom);
		d.setPrenom(prenom);
		d.setAge(age);
		d.setSalaire(salaire);
	}
	
	private static void AfficherConvives() {
		System.out.println(cc);
		saisies.Attendre();
	}
	
	private static void AfficherStagiaires() {
		ArrayList<convive> lc;
		
		lc = cc.Chercher(stagiaire.getTypeClasse());
		cs.Supprimer();
		cs.Ajouter(lc);
		System.out.println(cs);
		saisies.Attendre();
	}
	
	private static void AfficherStagiairesCif() {
		ArrayList<convive> lc;
		
		lc = cc.Chercher(stagiaireCif.getTypeClasse());
		csc.Supprimer();
		csc.Ajouter(lc);
		System.out.println(csc);
		saisies.Attendre();
	}
	
	private static void AfficherDirecteurs() {
		ArrayList<convive> lc;
		
		lc = cc.Chercher(directeur.getTypeClasse());
		cd.Supprimer();
		cd.Ajouter(lc);
		System.out.println(cd);
		saisies.Attendre();
	}
	
	private static void AfficherFormateurs() {
		ArrayList<convive> lc;
		
		lc = cc.Chercher(formateur.getTypeClasse());
		cf.Supprimer();
		cf.Ajouter(lc);
		System.out.println(cf);
		saisies.Attendre();
	}
	
	private static void Creer() {
		int nb,i;
		stagiaire s;
		stagiaireCif sc;
		directeur d;
		formateur f;
		
		nb = 6;
		for (i=0;i<nb;i++) {
			s = cc.AjouterStagiaire();
			s.setNom(TirerAusort(tnoms));
			s.setPrenom(TirerAusort(tprenoms));
			s.setAge(TirerAusort(tages));
			s.setFormation(TirerAusort(tformations));
		}
		
		nb = 3;
		for (i=0;i<nb;i++) {
			sc = cc.AjouterStagiaireCif();
			sc.setNom(TirerAusort(tnoms));
			sc.setPrenom(TirerAusort(tprenoms));
			sc.setAge(TirerAusort(tages));
			sc.setFormation(TirerAusort(tformations));
		}
		
		nb = 4;
		for (i=0;i<nb;i++) {
			f = cc.AjouterFormateur();
			f.setNom(TirerAusort(tnoms));
			f.setPrenom(TirerAusort(tprenoms));
			f.setAge(TirerAusort(tages));
			f.setNbannees(TirerAusort(tnbannees));
		}
		
		nb = 2;
		for (i=0;i<nb;i++) {
			d = cc.AjouterDirecteur();
			d.setNom(TirerAusort(tnoms));
			d.setPrenom(TirerAusort(tprenoms));
			d.setAge(TirerAusort(tages));
			d.setSalaire(TirerAusort(tsalaires));
		}
	}
	
	private static void DetruireConvives() {
		boolean ok;
		
		if (cc.getNbConvives()>0) {
			ok = saisies.Questionner(
					"Voulez vous detruire tous les convives");
			if (ok == true)
				cc.Supprimer();
		}
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
		}
	}
	
	private static boolean Traiter(final int act) {
		boolean ok;
		
		ok = false;
		switch (act) {
		case ACT_CREER:
			Creer();
			break;
		case ACT_CREERSTAGIAIRE:
			CreerStagiaire();
			break;
		case ACT_CREERSTAGIAIRECIF:
			CreerStagiaireCif();
			break;
		case ACT_CREERDIRECTEUR:
			CreerDirecteur();
			break;
		case ACT_CREERFORMATEUR:
			CreerFormateur();
			break;
		case ACT_AFFICHERCONVIVES:
			AfficherConvives();
			break;
		case ACT_AFFICHERSTAGIAIRES:
			AfficherStagiaires();
			break;
		case ACT_AFFICHERSTAGIAIRESCIF:
			AfficherStagiairesCif();
			break;
		case ACT_AFFICHERDIRECTEURS:
			AfficherDirecteurs();
			break;
		case ACT_AFFICHERFORMATEURS:
			AfficherFormateurs();
			break;
		case ACT_DETRUIRE:
			DetruireConvives();
			break;
		case ACT_CALCULER:
			Calculer();
			break;
		case ACT_QUITTER:
			ok = saisies.Questionner(
					"Voulez vous quitter l'application");
			break;
		default:
			System.out.println(act);
			saisies.Attendre();
		}
		return (ok);
	}
	
	public static void Exploiter() {
		int a;
		boolean fin;
		
		saisies.Initialiser();
		cc = new colconvives("CONVIVES AFPA");
		cs = new colconvives("LES STAGIAIRES");
		csc = new colconvives("LES STAGIAIRES CIF");
		cd = new colconvives("LES DIRECTEURS");
		cf = new colconvives("LES FORMATEURS");
		
		m = new menu("Gestion de cantine");
		CreerMenu();	
		
		do {
			System.out.println(m);
			a = m.LireAction();
			fin = Traiter(a);
		}while (fin == false);
	
		saisies.Terminer();
	}
}
