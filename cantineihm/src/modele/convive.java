package modele;

import java.math.BigInteger;

// classe m�re de toutes les autres
// permet de g�rer les attributs communs
// aux classes filles

// Elle est abstraite parce qu'elle contient une m�thode
// abstraite
// Une classe peut etre abstraite meme si elle ne contient
// de m�thode abstraite
// Une classe abstraite ne peut pas etre instnaci�e

public abstract class convive {
	
	// **********
	// Constantes
	// **********
	
	private static final String INDEFINI = "XX";
	private static final int TYPE = 1000;
	
	private static final int LGMINNOM = 1;
	private static final int LGMAXNOM = 20;
	private static final String RXNOM = "^[a-zA-Z]*$";

	private static final int LGMINPRENOM = 1;
	private static final int LGMAXPRENOM = 15;
	private static final String RXPRENOM = "^[a-zA-Z]*$";
	
	private static final int AGEMINI = 18;
	private static final int AGEMAXI = 67;

	//	*************************
	// 	Les attributs de classe
	//	*************************

	private static boolean TRACE = false;
	
	//	*************************
	// 	Les attributs d'instance
	//	Ce sont les donn�es communes
	// 	aux m�thodes de la classe
	//  ET ASSOCIE A UN OBJET
	// 	ILS SONT TOUJOURS PRIVES
	//	**************************
	
	// Le nom
	// La valeur null correspond � un nom qui
	// n'est pas d�fini
	private String _nom;
	
	// Mode de recherche pour le nom
	// 0 pour == nom
	// 1 pour > nom (alphabetic)
	// -1 pour < nom (alphabetic)
	// 100 pour aucun
	
	private int _moderecherchenom;
		
	// Le pr�nom
	// La valeur null correspond � un pr�nom qui
	// n'est pas d�fini
	private String _prenom;
	
	// Mode de recherche pour le pr�nom
	// 0 pour == prenom
	// 1 pour > pr�nom (alphabetic)
	// -1 pour < pr�nom (alphabetic)
	// 100 pour aucun
	
	private int _moderechercheprenom;
		
	// L'age
	// Le boolean _agedef permet de savoir si
	// l'age est d�fini ou pas
	private int _age;
	
	// Booleen permettant de savoir si 
	// l'age est defini ou pas
	private boolean _agedef;
	
	// Mode de recherche pour l'age
	// 0 pour == age
	// 1 pour >age
	// -1 pour <age
	// 100 pour aucun
	
	private int _moderechercheage;
	
	private boolean _moderecherchecara;
	
	//	*************************
	// 	Les m�thodes d'instance
	//	Elles permettent de manipuler
	//  les attributs d'instance de la meme
	// 	mani�re quelque soit l'objet
	//	**************************	
	
	// Les constructeurs
	// Le constructeur est appel� 
	// lors de l'instanciation (cr�ation)
	// d'un nouvel objet
	// Un contructeur porte le nom de la classe
	// et n'a pas de retour
	
	private void Initialiser() {
		this.setNom(null);
		this.setPrenom (null);
		this.setAgeDef(false);
		this.setModeRechercheAge(0);
		this.setModeRechercheNom(0);
		this.setModeRecherchePrenom(0);
		this.setModeRechercheCaracteristiques(true);
	}
	
	public convive() {
		AfficherTrace("Constructeur convive par defaut");
		this.Initialiser();
	}
	
	// surcharges constructeurs
	public convive(
			final String nom) {
		AfficherTrace("Constructeur convive avec le nom");
		this.Initialiser();
		this.setNom(nom);
	}
	
	public convive(
			final String prenom,
			final String nom) {
		AfficherTrace("Constructeur convive avec le pr�nom et le nom");
		this.Initialiser();
		this.setNom(nom);
		this.setPrenom(prenom);
	}
	
	public convive(
			final String prenom,
			final String nom,
			final int age) {
		AfficherTrace("Constructeur convive avec le pr�nom, le nom et l'age");
		this.Initialiser();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
	}
	
	// Les accesseurs
	
	// Nom en ecriture
	public void setNom(final String n) {
		this._nom = n;
	}
	
	// Nom en lecture
	// Retourne null si le nom n'est pas d�fini
	public String getNom() {
		return (this._nom);
	}
	
	private String getNomF() {
		String s;
		s = this.getNom();
		if (s == null)
			s = getIndefini();
		return(Formatter(s,LGMAXNOM));
	}
	
	public void setModeRechercheNom(final int m) {
		this._moderecherchenom = m;
	}
	
	public int getModeRechercheNom() {
		return (this._moderecherchenom);
	}
	
	// Pr�nom en ecriture
	public void setPrenom(final String pn) {
		this._prenom = pn;
	}
	
	// Pr�nom en lecture
	// Retourne null si le pr�nom n'est pas d�fini
	public String getPrenom() {
		return (this._prenom);
	}
	
	private String getPrenomF() {
		String s;
		s = this.getPrenom();
		if (s == null)
			s = getIndefini();
		return(Formatter(s,LGMAXPRENOM));
	}
	
	public void setModeRecherchePrenom(final int m) {
		this._moderechercheprenom = m;
	}
	
	public int getModeRecherchePrenom() {
		return (this._moderechercheprenom);
	}
	
	// Age en �criture
	private void setAgeDef(final boolean b) {
		this._agedef = b;
	}
	
	public void setAge(final int a) {
		this._age = a;
		this.setAgeDef(true);
	}
	
	// surcharge du setteur de l'age
	public void setAge(final String s) {
		int[] v = new int[1];
		boolean ok;
		
		ok = Convertir(s, v);
		if (ok == true)
			this.setAge(v[0]);	
	}
	
	// Age en lecture
	// Retourne true si l'age a �t� d�fini
	// et false sinon
	// La param�tre de sortie contient
	// la valeur de l'age si elle a �t� d�finie
	
	private boolean getAgeDef() {
		return (this._agedef);
	}
	
	public boolean getAge(
			int[] v){
		boolean ok;
		
		ok = this.getAgeDef();
		if (ok == true)
			v[0] = this._age;
		return (ok);
	}
	
	// surcharge du getteur de l'age
	// Retourne null si l'age n'est pas d�fini 
	// si l'age a �t� d�fini, il est retourn� dans
	// une chaine de caract�res
	
	public String getAge() {
		String s;
		int[] v = new int[1];
		boolean ok;
		
		ok = this.getAge(v);
		if (ok == true)
			s = Formatter(v[0]);
		else
			s = null;
		return (s);
	}
	
	// mode de recherche
	
	public void setModeRechercheAge(final int m) {
		this._moderechercheage = m;
	}
	
	public int getModeRechercheAge() {
		return (this._moderechercheage);
	}
	
	public void setModeRechercheCaracteristiques(final boolean m) {
		this._moderecherchecara=m;
	}
	
	public boolean getModeRechercheCaracteristiques() {
		return (this._moderecherchecara);
	}
	
	protected String getCaracteristiques() {
		return ("");
	}
	
	protected String getEntete() {
		return ("<CONVIVE>");
	}

	// Red�finition de la m�thode toString
	@Override
	public String toString() {
		String s;
		String n,pn,a;
		String carac;
		String entete;
		
		entete = Formatter(this.getEntete(),-18);
		
		n = this.getNomF();
		if (n == null) n = getIndefini();
		
		pn = this.getPrenomF();
		if (pn == null) pn = getIndefini();
		
		a = this.getAge();
		if (a == null) a = getIndefini();
		
		carac = this.getCaracteristiques();
		
		s =
			entete+
			" "+
			"["+pn+"]"+
			" "+
			"["+n+"]"+
			" est age de "+
			"["+a+"] an(s)"+
			" "+carac;
		return (s);	
	}	
	
	// Les m�thodes de classe
	
	protected static void AfficherTrace(String s) {
		if (TRACE == true)
			System.out.println("Trace -> "+s);
	}
	
	public static void ValiderTrace() {
		TRACE = true;
	}
	
	public static void DevaliderTrace() {
		TRACE = false;
	}
	
	protected static String getIndefini() {
		return (INDEFINI);
	}

	public abstract boolean CalculerPrix(
			final double pb, 
			double[] p);
	
	// retourne null si le prix ne peut pas etre calcul�
	// sinon la valeur est r�cup�r� dans un string
	public String CalculerPrix(
			final double pb) {
		double[] p = new double[1];
		String s;
		
		if (this.CalculerPrix(pb, p)== false)
			s = null;
		else
			s = ""+p[0];
		
		return(s);
	}
	
	// retourne null si le prix ne peut pas etre calcul�
	// sinon la valeur est r�cup�r� dans un string
	public String CalculerPrix(
			final String spb) {
		String sp;
		double[] pb = new double[1];
		
		if (Convertir(spb, pb)== false)
			sp = null;
		else
			sp = this.CalculerPrix(pb[0]);
		
		return(sp);
	}
	
	// m�thodes de copie
	
	protected void CopierCaracteristiques(final convive src) {}
		
	//ctrc de src
	//ctrv dans this
	
	public void Copier(final convive src) {
		String c;
		int[] a = new int[1];
		
		if (this.getType()!= src.getType())
			return;
		
		c = src.getNom();
		if (c != null) this.setNom(c);
		
		c = src.getPrenom();
		if (c != null) this.setPrenom(c);
		
		if (src.getAge(a)==true)
			this.setAge(a[0]);
		
		this.CopierCaracteristiques(src);	
	}
	
	// m�thodes de comparaison
	// ***********************
	
	protected static boolean Comparer(
			final String src,
			final int modesrc,
			final String cour) {
		boolean ok;
		
		ok = false;
		if (modesrc==0)
			if (cour.compareTo(src)==0) ok=true;
		if (modesrc==-1)
			if (cour.compareTo(src)<0) ok = true;
		if (modesrc==1)
			if (cour.compareTo(src)>0) ok = true;
		if (modesrc==100)
			ok = true;
		
		return (ok);
	}
	
	protected static boolean Comparer(
			final int src,
			final int modesrc,
			final int cour) {
		boolean ok;
		
		ok = false;
		if (modesrc==0)
			if (cour == src) ok=true;
		if (modesrc==-1)
			if (cour < src) ok = true;
		if (modesrc==1)
			if (cour > src) ok = true;
		if (modesrc==100)
			ok = true;
		
		return (ok);
	}
	
	protected static boolean Comparer(
			final double src,
			final int modesrc,
			final double cour) {
		boolean ok;
		
		ok = false;
		if (modesrc==0)
			if (cour == src) ok=true;
		if (modesrc==-1)
			if (cour < src) ok = true;
		if (modesrc==1)
			if (cour > src) ok = true;
		if (modesrc==100)
			ok = true;
		
		return (ok);
	}
	
	protected boolean ComparerCaracteristiques(final convive src){
		return (true);
	}
	
	public boolean Comparer(
			final convive src,
			final boolean veriftype) {
		String csrc,ccour;
		int[] asrc = new int[1];
		int[] acour= new int[1];
		boolean oksrc,okcour;
		int nbcompar;
		
		if (veriftype==true)
			if (this.getType() != src.getType())
				return (false);
		
		nbcompar = 0;
		
		csrc = src.getNom();
		ccour = this.getNom();
		oksrc = (csrc!=null);
		okcour = (ccour!=null);
		if (oksrc == false) csrc = getIndefini();
		if (okcour == false) ccour = getIndefini(); 
		if (Comparer(
		csrc, 
		src.getModeRechercheNom(), 
		ccour)==true)
			nbcompar++;
		
		csrc = src.getPrenom();
		ccour = this.getPrenom();
		oksrc = (csrc!=null);
		okcour = (ccour!=null);
		if (oksrc == false) csrc = getIndefini();
		if (okcour == false) ccour = getIndefini(); 
		if (Comparer(
			csrc, 
			src.getModeRecherchePrenom(), 
			ccour)==true)
				nbcompar++;
	
		oksrc = src.getAge(asrc);
		okcour = this.getAge(acour);
		if (oksrc == false) asrc[0] = Integer.MAX_VALUE;
		if (okcour == false) acour[0] = Integer.MAX_VALUE;
		if (Comparer(
			asrc[0], 
			src.getModeRechercheAge(), 
			acour[0])==true)
				nbcompar++;
	
		if (nbcompar != 3) return (false);
		if (src.getModeRechercheCaracteristiques()==true)
			return (this.ComparerCaracteristiques(src));
		
		return(true);
	}
	
	public boolean Comparer(
			final convive src) {
		return (this.Comparer(src, true));
	}
	
	// m�thode d'acces au type
	
	// m�thode d'instance
	public int getType() {
		return (TYPE);
	}
	
	// m�thode de classe	
	public static int getTypeClasse() {
		return (TYPE);
	}
	
	// m�thodes de classe pour les conversions
	// ***************************************
	
	protected static boolean Convertir(
			final String s, 
			int[] v) {
		int vtmp;
		boolean ok;
		
		ok = true;
		vtmp = 0;
		try {
			vtmp = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			ok=false;
		}
		
		if (ok == true)
			v[0] = vtmp;
		
		return (ok);		
	}
	
	protected static boolean Convertir(
			final String s, 
			double[] v) {
		double vtmp;
		boolean ok;
		String sm;
		
		sm = s.replace(',', '.');
		
		ok = true;
		vtmp = 0;
		try {
			vtmp = Double.parseDouble(sm);
		} catch (NumberFormatException e) {
			ok=false;
		}
		
		if (ok == true)
			v[0] = vtmp;
		
		return (ok);		
	}
	
	// m�thodes de classe pour les formatages
	// **************************************
	
	protected static String Formatter(
			final int v) {
		return (String.format("%03d", v));
	}
	
	protected static String Formatter(
			final double v) {
		return (String.format("%010.2f" ,v));
	}
	
	protected static String Formatter(
			final String s,
			final int lg) {
		String format;
		
		format = "%"+lg+"s";
		return (String.format(format ,s));
	}
	
	// m�thodes de classe pour les v�rifications
	// *****************************************
	
	// code erreur
	//	-10: nombre de caract�res incorrect
	//	-20: caract�res interdits
	protected static boolean Verifier(
			final String s,
			final int lgmini,
			final int lgmaxi,
			final String rx,
			int[] cerr) {
		int lg;
		boolean ok;
		
		lg = s.length();
		ok = (lgmini<=lg) && (lg<= lgmaxi);
		if (ok == false) {
			cerr[0] = -10;
			return (false);
		}
		
		if (rx !=null) {
			ok = s.matches(rx);
			if (ok == false) {
				cerr[0] = -20;
				return (false);
			}
		}	
		return (true);
	}
	
	// code erreur
	//	-100: nombre non entier
	//	-200: valeurs interdites
	protected static boolean Verifier(
			final String s,
			final int vmini,
			final int vmaxi,
			int[] cerr) {
		boolean ok;
		int[] v = new int[1];
		
		ok = Convertir(s, v);
		if (ok == false) {
			cerr[0] = -100;
			return (false);
		}
		
		ok = (vmini<=v[0]) && (v[0] <= vmaxi);
		if (ok == false) {
			cerr[0] = -200;
			return (false);
		}
		return (true);
	}
	
	// code erreur
	//	-300: nombre non d�cimal
	//	-200: valeurs interdites
	protected static boolean Verifier(
			final String s,
			final double vmini,
			final double vmaxi,
			int[] cerr) {
		boolean ok;
		double[] v = new double[1];
		
		ok = Convertir(s, v);
		if (ok == false) {
			cerr[0] = -300;
			return (false);
		}
		
		ok = (vmini<=v[0]) && (v[0]<=vmaxi);
		if (ok == false) {
			cerr[0] = -200;
			return (false);
		}
		return (true);
	}
	
	protected static String Aide(
			final String nomdata,
			final int lgmini,
			final int lgmaxi,
			final String ctx) {
		String s;
		
		s = 
			nomdata;
		
		if (lgmini != lgmaxi)
			s+= 
			" doit comporter un nombre de caracteres compris entre "+
			lgmini+
			" et "+
			lgmaxi;
		else
			s+= 
			" doit comporter "+
			lgmini+
			" caractere(s)";
		
		if (ctx != null) {
			s+=
					" et "+
				ctx;
		}
		return (s);
	}
	
	protected static String Aide(
			final String nomdata,
			final int vmini,
			final int vmaxi) {
		String s;
		
		s = 
			nomdata + 
			" doit etre une valeur entiere comprise entre "+
			vmini+
			" et "+
			vmaxi;
		return (s);
	}
	
	protected static String Aide(
			final String nomdata,
			final double vmini,
			final double vmaxi) {
		String s;
		
		s = 
			nomdata + 
			" doit etre une valeur decimale comprise entre "+
			vmini+
			" et "+
			vmaxi;
		return (s);
	}
	
	protected static String Aide(
			final String nomdata,
			final double vmini,
			final BigInteger vmaxi) {
		String s;
		
		s = 
			nomdata + 
			" doit etre une valeur decimale comprise entre "+
			vmini+
			" et "+
			vmaxi;
		return (s);
	}
	
	public static boolean VerifierNom(
			final String s,
			int[] cerr) {
		return (Verifier(s, LGMINNOM, LGMAXNOM, RXNOM, cerr));
	}
	
	public static String AideNom() {
		return (Aide(
				"Le nom", 
				LGMINNOM, 
				LGMAXNOM,
				"ne comporter que des lettres"));
	}
	
	public static boolean VerifierPrenom(
			final String s,
			int[] cerr) {
		return (Verifier(
				s, 
				LGMINPRENOM, 
				LGMAXPRENOM, 
				RXPRENOM, 
				cerr));
	}
	
	public static String AidePrenom() {
		return (Aide(
				"Le prenom", 
				LGMINPRENOM, 
				LGMAXPRENOM,
				"ne comporter que des lettres"));
	}
	
	public static boolean VerifierAge(
			final String s,
			int[] cerr) {
		return (Verifier(
				s, 
				AGEMINI, 
				AGEMAXI, 
				cerr));
	}
	
	public static String AideAge() {
		return (Aide(
				"L'age", 
				AGEMINI, 
				AGEMAXI));
	}
	
	public static boolean VerifierPrixBase(
			final String s,
			int[] cerr) {
		return (Verifier(
				s, 
				Double.MIN_NORMAL, 
				Double.MAX_VALUE, 
				cerr));
	}
	
	public static String AidePrixBase() {
		String s;
		s = 
			"Le prix de base doit etre une valeur decimale > 0";
		return (s);
	}
	
	public static String DecoderErreur(final int err) {
		String s;
		
		switch(err) {
		case -10:
			s = "Le nombre de caracteres est incorrect";
			break;
		case -20:
			s = "Des caracteres sont interdits";
			break;
		case -100:
			s = "Ce n'est pas une valeur entiere";
			break;
		case -200:
			s = "La valeur est interdite";
			break;
		case -300:
			s = "Ce n'est pas une valeur decimale";
			break;
		default:
			s = "erreur inconnue";
			break;
		}
		return (s);
	}
}
