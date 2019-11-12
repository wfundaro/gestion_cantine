package modele;

import java.util.ArrayList;
import java.util.Random;

// classe statique
// permet de tester le modèle

public class tester {
	
	// attributs de classe communs
	// a l'ensemble des méthodes de classe
	// permettant d'effectuer les tests	
	
	private static Random rnd = new Random();
	
	private static final int MAX = 15;
	private static final int LIMITAGE = 38;
	
	private static final int AGEMINI = 32;
	private static final int AGEMAXI = 45;
	private static final int NBAMINI = 2;
	private static final int NBAMAXI = 8;
	
	// les tableaux de données
	private static String[] tnoms= {
		"NOM1",
		"NOM2",
		"NOM3",
		"NOM4",
		"NOM5"
	};
	private static String[] tprenoms= {
		"PRENOM1",
		"PRENOM2",
		"PRENOM3",
		"PRENOM4"
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
		"CDA",
		"DWWM",
		"DL",
		"TSSI",
		"GDP"
	};
	private static String[] tsalaires= {
		"12454,67",
		"53456.98",
		"59999.97",
		"41055,01",
		"47500,99"
	};
	
	private enum ETESTDATA{
		NOM,
		PRENOM,
		AGE,
		NBANNEE,
		FORMATION,
		SALAIRE,
		PRIXBASE
	};
	
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
	
	private static void CalculerPrix(
			final colconvives cc,
			final double pb) {
		String p;
		String s;
		int i;
		
		System.out.println("Prix pour les convives de: "+cc.getNom());

		for (i=0;i<cc.getNbConvives();i++) {
			p = cc.CalculerPrix(i,pb);
			if (p == null)
				p = "Inconnu";
			s = cc.toString(i);
			if (s!=null)
				System.out.println(s+" -> "+"Prix de base= "+pb+"; Prix = "+p);
		}
	}
	
	private static void Tester(
			final ETESTDATA data,
			final String s) {
		boolean ok;
		String sdata;
		int[] cerr = new int[1];
		
		sdata = null;
		ok = false;
		switch (data) {
		case NOM:
			sdata = "Le nom";
			ok = convive.VerifierNom(s, cerr);
			break;
		case PRENOM:
			sdata = "Le prenom";
			ok = convive.VerifierPrenom(s, cerr);
			break;
		case AGE:
			sdata = "L'age";
			ok = convive.VerifierAge(s, cerr);
			break;
		case FORMATION:
			sdata = "Le nom de la formation";
			ok = stagiaire.VerifierFormation(s, cerr);
			break;
		case NBANNEE:
			sdata = "Le nombre d'annees";
			ok = formateur.VerifierNbannees(s, cerr);
			break;
		case SALAIRE:
			sdata = "Le salaire";
			ok = directeur.VerifierSalaire(s, cerr);
			break;
		case PRIXBASE:
			sdata = "Le prix de base";
			ok = convive.VerifierPrixBase(s, cerr);
			break;
		}
		if (sdata != null) {
			if (ok == false)
				System.out.println(
						sdata+
						" ["+s+"]"+
						" n'est pas correct -> "+
						convive.DecoderErreur(cerr[0])
						);
			else
				System.out.println(
						sdata+
						" ["+s+"]"+
						" est correct"
						);
		}
	}
	
	public static void TesterPolymorphisme() {
		ArrayList<convive> lc;
		formateur f;
		directeur d;
		stagiaire s;
		stagiaireCif sc;
		convive c;
		int i;
		double pb;
		String p;
		
		// array liste contenant des convives quelconques
		lc = new ArrayList<convive>();
		
		// instanciation des convives
		f = new formateur("PNF","NF",34,3);
		d = new directeur("PND","ND",42,17563.56);
		s = new stagiaire("PNS", "NS", 24, "CDA");
		sc = new stagiaireCif("PNSCIF", "NSCIF", 26, "CDA");
		
		// mémorisation dans l'array liste
		lc.add(f);
		lc.add(d);
		lc.add(s);
		lc.add(sc);
		
		pb = 10;
		
		for (i=0;i<lc.size();i++) {
			c = lc.get(i);
			System.out.print(c+"-> ");
			p = c.CalculerPrix(pb);
			if (p != null)
				System.out.println(
						"Prix de base = "+
						pb+
						"; Prix  = "+
						p);
			else
				System.out.println(
						"Impossible de calculer le prix");	
		}	
	}
	
	
	public static void TesterUneCollection() {
		colconvives cstag,cafpa;
		formateur f;
		stagiaireCif sc;
		stagiaire s;
		directeur d;
		int nb,i;
		
		cstag = new colconvives("LES STAGIAIRES");
		cafpa = new colconvives("LES AGENTS");
		
		nb = TirerAusort(1, MAX);
		for (i=0;i<nb;i++) {
			s = cstag.AjouterStagiaire();
			s.setNom(TirerAusort(tnoms));
			s.setPrenom(TirerAusort(tprenoms));
			s.setAge(TirerAusort(tages));
			s.setFormation(TirerAusort(tformations));
		}
		
		nb = TirerAusort(1, MAX);
		for (i=0;i<nb;i++) {
			f = cafpa.AjouterFormateur();
			f.setNom(TirerAusort(tnoms));
			f.setPrenom(TirerAusort(tprenoms));
			f.setAge(TirerAusort(tages));
			f.setNbannees(TirerAusort(tnbannees));
		}
		
		nb = TirerAusort(1, MAX);
		for (i=0;i<nb;i++) {
			sc = cstag.AjouterStagiaireCif();
			sc.setNom(TirerAusort(tnoms));
			sc.setPrenom(TirerAusort(tprenoms));
			sc.setAge(TirerAusort(tages));
			sc.setFormation(TirerAusort(tformations));
		}
		
		nb = TirerAusort(1, MAX);
		for (i=0;i<nb;i++) {
			d = cafpa.AjouterDirecteur();
			d.setNom(TirerAusort(tnoms));
			d.setPrenom(TirerAusort(tprenoms));
			d.setAge(TirerAusort(tages));
			d.setSalaire(TirerAusort(tsalaires));
		}
		
		System.out.println(cstag);
		System.out.println();

		System.out.println(cafpa);
		System.out.println();
		
		CalculerPrix(cstag,10);
		System.out.println();
		CalculerPrix(cafpa,10);		
	}
	
	public static void TesterCollectionsv1(
			final int nbs,
			final int nbscif,
			final int nbf,
			final int nbd) {
		int i;
		colconvives ctous;
		colconvives cstag;
		colconvives cafpa;
		colconvives cjeunes;
		colconvives cseniors;
		stagiaire s;
		stagiaireCif sc;
		formateur f;
		directeur d;
		String n,pn,fm,sal;
		int a,nba;

		ctous = new colconvives("LES CONVIVES");
		cstag = new colconvives("LES STAGIAIRES");
		cafpa = new colconvives("LES AGENTS AFPA");
		cjeunes = new colconvives("LES JEUNES (<"+LIMITAGE+")");
		cseniors = new colconvives("LES SENIORS (>="+LIMITAGE+")");

		for (i=0;i<nbs;i++) {
			n = TirerAusort(tnoms);
			pn = TirerAusort(tprenoms);
			a = TirerAusort(AGEMINI,AGEMAXI);
			fm = TirerAusort(tformations);
			
			s = ctous.AjouterStagiaire();
			s.setNom(n);
			s.setPrenom(pn);
			s.setAge(a);
			s.setFormation(fm);
			
			s = cstag.AjouterStagiaire();
			s.setNom(n);
			s.setPrenom(pn);
			s.setAge(a);
			s.setFormation(fm);
			
			if (a<LIMITAGE)
				s = cjeunes.AjouterStagiaire();
			else
				s = cseniors.AjouterStagiaire();
			s.setNom(n);
			s.setPrenom(pn);
			s.setAge(a);
			s.setFormation(fm);		
		}
		
		for (i=0;i<nbscif;i++) {
			n = TirerAusort(tnoms);
			pn = TirerAusort(tprenoms);
			a = TirerAusort(AGEMINI,AGEMAXI);
			fm = TirerAusort(tformations);
			
			sc = ctous.AjouterStagiaireCif();
			sc.setNom(n);
			sc.setPrenom(pn);
			sc.setAge(a);
			sc.setFormation(fm);
			
			sc = cstag.AjouterStagiaireCif();
			sc.setNom(n);
			sc.setPrenom(pn);
			sc.setAge(a);
			sc.setFormation(fm);
			
			if (a<LIMITAGE)
				sc = cjeunes.AjouterStagiaireCif();
			else
				sc = cseniors.AjouterStagiaireCif();
			sc.setNom(n);
			sc.setPrenom(pn);
			sc.setAge(a);
			sc.setFormation(fm);		
		}
		
		for (i=0;i<nbf;i++) {
			n = TirerAusort(tnoms);
			pn = TirerAusort(tprenoms);
			a = TirerAusort(AGEMINI,AGEMAXI);
			nba = TirerAusort(NBAMINI,NBAMAXI);
			
			f = ctous.AjouterFormateur();
			f.setNom(n);
			f.setPrenom(pn);
			f.setAge(a);
			f.setNbannees(nba);
			
			f = cafpa.AjouterFormateur();
			f.setNom(n);
			f.setPrenom(pn);
			f.setAge(a);
			f.setNbannees(nba);
			
			if (a<LIMITAGE)
				f = cjeunes.AjouterFormateur();
			else
				f = cseniors.AjouterFormateur();
			f.setNom(n);
			f.setPrenom(pn);
			f.setAge(a);
			f.setNbannees(nba);	
		}
		

		for (i=0;i<nbd;i++) {
			n = TirerAusort(tnoms);
			pn = TirerAusort(tprenoms);
			a = TirerAusort(AGEMINI,AGEMAXI);
			sal = TirerAusort(tsalaires);
			
			d = ctous.AjouterDirecteur();
			d.setNom(n);
			d.setPrenom(pn);
			d.setAge(a);
			d.setSalaire(sal);
			
			d = cafpa.AjouterDirecteur();
			d.setNom(n);
			d.setPrenom(pn);
			d.setAge(a);
			d.setSalaire(sal);
			
			if (a<LIMITAGE)
				d = cjeunes.AjouterDirecteur();
			else
				d = cseniors.AjouterDirecteur();
			d.setNom(n);
			d.setPrenom(pn);
			d.setAge(a);
			d.setSalaire(sal);
		}
		
		System.out.println(ctous);
		System.out.println(cstag);
		System.out.println(cafpa);
		System.out.println(cjeunes);
		System.out.println(cseniors);

		CalculerPrix(ctous,10);
	}
	
	public static void TesterCollectionsv2(
			final int nbs,
			final int nbscif,
			final int nbf,
			final int nbd) {
		int i;
		colconvives ctous;
		colconvives cstag;
		colconvives cafpa;
		colconvives cjeunes;
		colconvives cseniors;
		stagiaire s;
		stagiaireCif sc;
		formateur f;
		directeur d;
		int a;
		
		ctous = new colconvives("LES CONVIVES");
		cstag = new colconvives("LES STAGIAIRES");
		cafpa = new colconvives("LES AGENTS AFPA");
		cjeunes = new colconvives("LES JEUNES (<"+LIMITAGE+")");
		cseniors = new colconvives("LES SENIORS (>="+LIMITAGE+")");
		
		for (i=0;i<nbs;i++) {
			s = ctous.AjouterStagiaire();
			s.setNom(TirerAusort(tnoms));
			s.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI,AGEMAXI);
			s.setAge(a);
			s.setFormation(TirerAusort(tformations));
			
			cstag.AjouterStagiaire().Copier(s);
			if (a<LIMITAGE)
				cjeunes.AjouterStagiaire().Copier(s);
			else
				cseniors.AjouterStagiaire().Copier(s);
		}
		
		for (i=0;i<nbscif;i++) {
			sc = ctous.AjouterStagiaireCif();
			sc.setNom(TirerAusort(tnoms));
			sc.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI,AGEMAXI);
			sc.setAge(a);
			sc.setFormation(TirerAusort(tformations));
			
			cstag.AjouterStagiaireCif().Copier(sc);
			if (a<LIMITAGE)
				cjeunes.AjouterStagiaireCif().Copier(sc);
			else
				cseniors.AjouterStagiaireCif().Copier(sc);
		}
		
		for (i=0;i<nbd;i++) {
			d = ctous.AjouterDirecteur();
			d.setNom(TirerAusort(tnoms));
			d.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI,AGEMAXI);
			d.setAge(a);
			d.setSalaire(TirerAusort(tsalaires));
			
			cafpa.AjouterDirecteur().Copier(d);
			if (a<LIMITAGE)
				cjeunes.AjouterDirecteur().Copier(d);
			else
				cseniors.AjouterDirecteur().Copier(d);
		}
		
		for (i=0;i<nbf;i++) {
			f = ctous.AjouterFormateur();
			f.setNom(TirerAusort(tnoms));
			f.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI,AGEMAXI);
			f.setAge(a);
			f.setNbannees(TirerAusort(NBAMINI,NBAMAXI));
			
			cafpa.AjouterFormateur().Copier(f);
			if (a<LIMITAGE)
				cjeunes.AjouterFormateur().Copier(f);
			else
				cseniors.AjouterFormateur().Copier(f);
		}
		
		System.out.println(ctous);
		System.out.println(cstag);
		System.out.println(cafpa);
		System.out.println(cjeunes);
		System.out.println(cseniors);

		CalculerPrix(ctous,10);
	}
	
	public static void TesterCollectionsv3(
			final int nbs,
			final int nbscif,
			final int nbf,
			final int nbd) {
		int i;
		colconvives ctous;
		colconvives cstag;
		colconvives cafpa;
		stagiaire s;
		stagiaireCif sc;
		formateur f;
		directeur d;
		int a;
		ArrayList<convive> l;

		ctous = new colconvives("LES CONVIVES");
		cstag = new colconvives("LES STAGIAIRES");
		cafpa = new colconvives("LES AGENTS AFPA");
		
		for (i=0;i<nbs;i++) {
			s = ctous.AjouterStagiaire();
			s.setNom(TirerAusort(tnoms));
			s.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			s.setAge(a);
			s.setFormation(TirerAusort(tformations));
		}
		
		for (i=0;i<nbscif;i++) {
			sc = ctous.AjouterStagiaireCif();
			sc.setNom(TirerAusort(tnoms));
			sc.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			sc.setAge(a);
			sc.setFormation(TirerAusort(tformations));
		}
		
		for (i=0;i<nbf;i++) {
			f = ctous.AjouterFormateur();
			f.setNom(TirerAusort(tnoms));
			f.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			f.setAge(a);
			f.setNbannees(TirerAusort(NBAMINI, NBAMAXI));
		}
		
		for (i=0;i<nbd;i++) {
			d = ctous.AjouterDirecteur();
			d.setNom(TirerAusort(tnoms));
			d.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			d.setAge(a);
			d.setSalaire(TirerAusort(tsalaires));
		}
		
		l = ctous.Chercher(stagiaire.getTypeClasse());
		cstag.Ajouter(l);
		l = ctous.Chercher(stagiaireCif.getTypeClasse());
		cstag.Ajouter(l);
		l = ctous.Chercher(formateur.getTypeClasse());
		cafpa.Ajouter(l);
		l = ctous.Chercher(directeur.getTypeClasse());
		cafpa.Ajouter(l);
		
		System.out.println(ctous);
		System.out.println(cstag);
		System.out.println(cafpa);
		
		ctous.Supprimer();
		ctous.Ajouter(cafpa);
		ctous.Ajouter(cstag);
		
		System.out.println(ctous);

		CalculerPrix(ctous,10);
	}
	
	public static void TesterRechercheEtTri(
			final int nbs,
			final int nbscif,
			final int nbf,
			final int nbd) {
		colconvives ctous;
		colconvives ccherche;
		int i;
		formateur f;
		directeur d;
		stagiaire s;
		stagiaireCif sc;
		int a;
			
		formateur fr;
		directeur dr;
		stagiaire sr;
		stagiaireCif scr;
		String str;
		int vi;
		double vf;

		ArrayList<convive> lr;
		
		ctous = new colconvives("LES CONVIVES");
		ccherche = new colconvives("RESULTATS RECHERCHE");
	
		for (i=0;i<nbs;i++) {
			s = ctous.AjouterStagiaire();
			s.setNom(TirerAusort(tnoms));
			s.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			s.setAge(a);
			s.setFormation(TirerAusort(tformations));
		}
		
		for (i=0;i<nbscif;i++) {
			sc = ctous.AjouterStagiaireCif();
			sc.setNom(TirerAusort(tnoms));
			sc.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			sc.setAge(a);
			sc.setFormation(TirerAusort(tformations));
		}
		
		for (i=0;i<nbf;i++) {
			f = ctous.AjouterFormateur();
			f.setNom(TirerAusort(tnoms));
			f.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			f.setAge(a);
			f.setNbannees(TirerAusort(NBAMINI, NBAMAXI));
		}
		
		for (i=0;i<nbd;i++) {
			d = ctous.AjouterDirecteur();
			d.setNom(TirerAusort(tnoms));
			d.setPrenom(TirerAusort(tprenoms));
			a = TirerAusort(AGEMINI, AGEMAXI);
			d.setAge(a);
			d.setSalaire(TirerAusort(tsalaires));
		}
		
		System.out.println(ctous);
		
		fr = new formateur();
		dr = new directeur();
		sr = new stagiaire();
		scr = new stagiaireCif();
		
		str = tnoms[0];
		System.out.println(
				"Recherche des formateurs dont le nom est: "+
				str);
		fr.setModeRechercheNom(100);
		fr.setModeRecherchePrenom(100);
		fr.setModeRechercheAge(100);
		fr.setModeRechercheNbannees(100);
		
		fr.setModeRechercheNom(0);
		fr.setNom(str);
		ccherche.Supprimer();
		lr = ctous.Chercher(fr);
		ccherche.Ajouter(lr);
		System.out.println(ccherche);
			
		vi = (NBAMAXI+NBAMINI)/2;
		System.out.println(
				"Recherche des formateurs dont le nombre d'années est > "+
				vi);
		fr.setModeRechercheNom(100);
		fr.setModeRecherchePrenom(100);
		fr.setModeRechercheAge(100);
		fr.setModeRechercheNbannees(100);
		
		fr.setModeRechercheNbannees(1);
		fr.setNbannees(vi);
		ccherche.Supprimer();
		lr = ctous.Chercher(fr);
		ccherche.Ajouter(lr);
		System.out.println(ccherche);	
			
		vf = 50000;
		System.out.println(
				"Recherche des directeurs dont le salaire est < "+
				vf);
		dr.setModeRechercheNom(100);
		dr.setModeRecherchePrenom(100);
		dr.setModeRechercheAge(100);
		dr.setModeRechercheSalaire(100);
		
		dr.setModeRechercheSalaire(-1);
		dr.setSalaire(vf);
		ccherche.Supprimer();
		lr = ctous.Chercher(dr);
		ccherche.Ajouter(lr);
		System.out.println(ccherche);	
		
		str = "DDD";
		System.out.println(
				"Recherche des stagaires dont le nom de la formation est > "+
				str);
		sr.setModeRechercheNom(100);
		sr.setModeRecherchePrenom(100);
		sr.setModeRechercheAge(100);
		sr.setModeRechercheFormation(100);
		
		sr.setModeRechercheFormation(1);
		sr.setFormation(str);
		ccherche.Supprimer();
		lr = ctous.Chercher(sr);
		ccherche.Ajouter(lr);
		System.out.println(ccherche);
		
		str = "DDD";
		System.out.println(
				"Recherche des stagaires CIF dont le nom de la formation est < "+
				str);
		scr.setModeRechercheNom(100);
		scr.setModeRecherchePrenom(100);
		scr.setModeRechercheAge(100);
		scr.setModeRechercheFormation(100);
		
		scr.setModeRechercheFormation(-1);
		scr.setFormation(str);
		ccherche.Supprimer();
		lr = ctous.Chercher(scr);
		ccherche.Ajouter(lr);
		System.out.println(ccherche);
		
		System.out.println("Tri des convives par nom croissant");
		ctous.Trier(1);
		System.out.println(ctous);
		
		System.out.println("Tri des convives par prenom decroissant");
		ctous.Trier(-2);
		System.out.println(ctous);
		
		System.out.println("Tri des convives par age croissant");
		ctous.Trier(3);
		System.out.println(ctous);
		
		System.out.println("Tri des convives par age decroissant");
		ctous.Trier(-3);
		System.out.println(ctous);
	}
	
		
	public static void TesterVerifications() {
		System.out.println(convive.AideNom());
		Tester(ETESTDATA.NOM, "");
		Tester(ETESTDATA.NOM, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		Tester(ETESTDATA.NOM, "A12");
		Tester(ETESTDATA.NOM, "Aaa");
		
		System.out.println();
		System.out.println(convive.AidePrenom());
		Tester(ETESTDATA.PRENOM, "");
		Tester(ETESTDATA.PRENOM, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		Tester(ETESTDATA.NOM, "A12");
		Tester(ETESTDATA.PRENOM, "aAaa");

		System.out.println();
		System.out.println(convive.AideAge());
		Tester(ETESTDATA.AGE, "");
		Tester(ETESTDATA.AGE, "A12");
		Tester(ETESTDATA.AGE, "112");
		Tester(ETESTDATA.AGE, "-12");
		Tester(ETESTDATA.AGE, "21");

		System.out.println();
		System.out.println(formateur.AideNbannees());
		Tester(ETESTDATA.NBANNEE, "");
		Tester(ETESTDATA.NBANNEE, "A12");
		Tester(ETESTDATA.NBANNEE, "112");
		Tester(ETESTDATA.NBANNEE, "12");
		Tester(ETESTDATA.NBANNEE, "21");
		
		System.out.println();
		System.out.println(stagiaire.AideFormation());
		Tester(ETESTDATA.FORMATION, "");
		Tester(ETESTDATA.FORMATION, "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		Tester(ETESTDATA.FORMATION, "AAAAAA");
		Tester(ETESTDATA.FORMATION, "AAa123");
		Tester(ETESTDATA.FORMATION, "Aa1323");
		Tester(ETESTDATA.FORMATION, "zz0007");

		System.out.println();
		System.out.println(directeur.AideSalaire());
		Tester(ETESTDATA.SALAIRE, "");
		Tester(ETESTDATA.SALAIRE, "A12");
		Tester(ETESTDATA.SALAIRE, "112,a");
		Tester(ETESTDATA.SALAIRE, "-12,234");
		Tester(ETESTDATA.SALAIRE, "12,234");
		Tester(ETESTDATA.SALAIRE, "21234.45");
		
		System.out.println();
		System.out.println(convive.AidePrixBase());
		Tester(ETESTDATA.PRIXBASE, "");
		Tester(ETESTDATA.PRIXBASE, "A12");
		Tester(ETESTDATA.PRIXBASE, "112,a");
		Tester(ETESTDATA.PRIXBASE, "-12,234");
		Tester(ETESTDATA.PRIXBASE, "12,234");
		Tester(ETESTDATA.PRIXBASE, "21234.45");

	}
}
