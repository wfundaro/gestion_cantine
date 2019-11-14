package modele;

import java.math.BigInteger;

public class directeur extends convive{
	
	// **********
	// Constantes
	// **********
	
	private static final Double HSAL=50000.;
	private static final Double BSAL=45000.;
	private static final Double TBSAL=0.8;
	private static final Double TMSAL=1.2;
	private static final Double THSAL=1.5;
	
	private static final int TYPE = 2000;
	
	private static final double SALAIREMINI = 0.0;
	//private static final double SALAIREMAXI = 700000.;
	private static final double SALAIREMAXI = Double.MAX_VALUE;
	
	//	*************************
	// 	Les attributs d'instance
	//	Ce sont les données communes
	// 	aux méthodes de la classe
	//  ET ASSOCIE A UN OBJET
	// 	ILS SONT TOUJOURS PRIVES
	//	**************************
	
	// Le salaire
	// Le boolean _salairedef permet de savoir si
	// le salaire est défini ou pas
	private double _salaire;
	
	// Booleen permettant de savoir si 
	// le salaire est defini ou pas
	private boolean _salairedef;
	
	// Mode de recherche pour le salaire
	// 0 pour == salaire
	// 1 pour >salaire
	// -1 pour <salaire
	// 100 pour aucun
		
	private int _moderecherchesalaire;
	
	//	*************************
	// 	Les méthodes d'instance
	//	Elles permettent de manipuler
	//  les attributs d'instance de la meme
	// 	maniére quelque soit l'objet
	//	**************************	
	
	// Les constructeurs
	// Le constructeur est appelé 
	// lors de l'instanciation (création)
	// d'un nouvel objet
	// Un contructeur porte le nom de la classe
	// et n'a pas de retour
	
	private void Initialiser() {
		this.setSalairedef(false);
		this.setModeRechercheSalaire(0);
	}
	
	public directeur() {
		super();
		convive.AfficherTrace("Constructeur directeur par defaut");
		this.Initialiser();
	}
	
	// surcharges constructeurs
	public directeur(
			final String nom) {
		super(nom);
		convive.AfficherTrace("Constructeur directeur avec le nom");
		this.Initialiser();
	}
	
	public directeur(
			final String prenom,
			final String nom) {
		super(prenom,nom);
		convive.AfficherTrace("Constructeur directeur avec le prénom et le nom");
		this.Initialiser();
	}
	
	public directeur(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur directeur avec le prénom, le nom et l'age");
		this.Initialiser();
	}
	
	public directeur(
			final String prenom,
			final String nom,
			final int age,
			final double salaire) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur directeur complet");
		this.Initialiser();
		this.setSalaire(salaire);
	}
	
	// Les accesseurs
	
	// boolean def salaire
	private void setSalairedef(final boolean b) {
		this._salairedef=b;
	}
	// formation en écriture
	public void setSalaire(final double n) {
		this._salaire = n;
		this.setSalairedef(true);
	}
	
	// surcharge setSalaire
	public void setSalaire(final String s) {
		double[] v =new double[1];
		boolean ok;
	
		ok = convive.Convertir(s, v);
		if (ok == true)
			this.setSalaire(v[0]);	
	}
			
	// boolean def salaire
	
	private boolean getSalairedef() {
		return (this._salairedef);
	}
	
	// salaire en lecture
	public boolean getSalaire(double[] v) {
		boolean ok;
		
		ok=getSalairedef();
		if(ok==true) {
			v[0]=this._salaire;
		}
		return ok;
	}
	
	// surcharge lecture salaire
	public String getSalaire() {
		String s;
		double[]v= new double[1];
		boolean ok;
		
		ok=getSalaire(v);
		if(ok==true)
			s=convive.Formatter(v[0]);
		else 
			s=null;
		return (s);
	}
	
	public void setModeRechercheSalaire(final int m) {
		this._moderecherchesalaire = m;
	}
	
	private int getModeRechercheSalaire() {
		return (this._moderecherchesalaire);
	}
	
	// Redéfinition de la méthode getEntete
	@Override
	protected String getEntete() {
		return ("<DIRECTEUR>");
	}
		
	// Redéfinition de la méthode getCaracteristiques
	@Override
	protected String getCaracteristiques() {
		String s;
		String salaire;
				
		salaire = this.getSalaire();
		if (salaire == null) salaire = convive.getIndefini();
		
		s =
			"dispose d'un salaire de "+
			"["+salaire+"] euro(s)";
		return (s);	
	}	
		
	
	// retourne true si le prix peut etre calculé
	// et false sinon
	// Le prix est recupéré dans le paramétre de sortie
	@Override
	public boolean CalculerPrix(
			final double pb,
			double[] p
			) {
		double[] salaire = new double[1];
		double participation;
		
		if (this.getSalaire(salaire)==false)
			return (false);
		
		participation = 0;
		// determination de la participation
		if(salaire[0]>HSAL) {
			participation = pb*THSAL;
		}
		if(salaire[0]<=HSAL&&salaire[0]>BSAL) {
			participation = pb*TMSAL;
		}
		if(salaire[0]<=BSAL) {
			participation = pb*TBSAL;
		}
			
		//prix total avec participation
		p[0] = pb+participation;
		
		return (true);	
	}
	
	@Override
	protected void CopierCaracteristiques(final convive src){
		directeur osrc;
		double[] sal = new double[1];
		
		if (this.getType() != src.getType())
			return;
		
		// detypage de l'objet src en un objet directeur
		// PARCE QU'ON EST SUR QUE SRC EST UN DIRECTEUR !!!
		osrc = (directeur)src;
		
		if (osrc.getSalaire(sal)==true)
			this.setSalaire(sal[0]);
	}
	
	// méthodes de comparaison
	// ***********************
	
	@Override
	protected boolean ComparerCaracteristiques(final convive src){
		directeur osrc;
		double[] ssrc = new double[1];
		double[] scour = new double[1];
		boolean oksrc,okcour;
		int nbcompar;
		
		nbcompar = 0;
		
		if (this.getType() != src.getType())
			return (false);
		
		osrc = (directeur)src;
		
		oksrc = osrc.getSalaire(ssrc);
		okcour = this.getSalaire(scour);
		if (oksrc == false) ssrc[0] = Double.MAX_VALUE;
		if (okcour == false) scour[0] = Double.MAX_VALUE;
		if (convive.Comparer(
			ssrc[0], 
			osrc.getModeRechercheSalaire(), 
			scour[0])==true)
				nbcompar++;
		return (nbcompar==1);
	}
	
	// méthode d'acces au type
	
	// méthode d'instance
	@Override
	public int getType() {
		return (TYPE);
	}
	
	// méthode de classe	
	public static int getTypeClasse() {
		return (TYPE);
	}
	
	// méthodes de classe pour les vérifications
		// *****************************************
		
	public static boolean VerifierSalaire(
			final String s,
			int[] cerr) {
		return (convive.Verifier(s, SALAIREMINI, SALAIREMAXI, cerr));
	}
	
	public static String AideSalaire() {
		BigInteger salMax = BigInteger.valueOf((long) SALAIREMAXI);
		return (convive.Aide(
				"Le salaire", 
				SALAIREMINI, 
				salMax));
	}
}
