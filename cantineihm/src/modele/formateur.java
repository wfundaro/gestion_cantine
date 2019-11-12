package modele;

// classe fille de convive
// elle va gérer ce qui lui est propre

public class formateur extends convive {
	
	// **********
	// Constantes
	// **********
	
	private static final int TYPE = 3000;

	private static final int NBAMINI = 1;
	private static final int NBAMAXI = 42;
	
	//	*************************
	// 	Les attributs d'instance
	//	Ce sont les données communes
	// 	aux méthodes de la classe
	//  ET ASSOCIE A UN OBJET
	// 	ILS SONT TOUJOURS PRIVES
	//	**************************
	
	// Le nombre d'années d'expérience
	// Le boolean _nbanneesdef permet de savoir si
	// le nombre d'années est défini ou pas
	private int _nbannees;

	// Booleen permettant de savoir si 
	// le nombre d'années est defini ou pas
	private boolean _nbanneesdef;
	
	// Mode de recherche pour le nombre d'années
	// 0 pour == nbannees
	// 1 pour >nbannees
	// -1 pour <nbannees
	// 100 pour aucun
	
	private int _moderecherchenbannees;
	
	//	*************************
	// 	Les méthodes d'instance
	//	Elles permettent de manipuler
	//  les attributs d'instance de la meme
	// 	manière quelque soit l'objet
	//	**************************	
	
	// Les constructeurs
	// Le constructeur est appelé 
	// lors de l'instanciation (création)
	// d'un nouvel objet
	// Un contructeur porte le nom de la classe
	// et n'a pas de retour
	
	private void Initialiser() {
		this.setNbannneesDef(false);
		this.setModeRechercheNbannees(0);
	}
	
	public formateur() {
		super();
		convive.AfficherTrace("Constructeur formateur par defaut");
		this.Initialiser();
	}
	
	// surcharges constructeurs
	public formateur(
			final String nom) {
		super(nom);
		convive.AfficherTrace("Constructeur formateur avec le nom");
		this.Initialiser();
	}
	
	public formateur(
			final String prenom,
			final String nom) {
		super(prenom,nom);
		convive.AfficherTrace("Constructeur formateur avec le prénom et le nom");
		this.Initialiser();
	}
	
	public formateur(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur formateur avec le prénom, le nom et l'age");
		this.Initialiser();	
	}
	
	public formateur(
			final String prenom,
			final String nom,
			final int age,
			final int nbannees) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur formateur complet");
		this.Initialiser();
		this.setNbannees(nbannees);
	}
	
	// Les accesseurs

	// Nb annees en écriture
	
	private void setNbannneesDef(final boolean b) {
		this._nbanneesdef = b;
	}
	
	public void setNbannees(final int n) {
		this._nbannees = n;
		this.setNbannneesDef(true);
	}
	
	// surcharge du setteur du nombre d'années
	public void setNbannees(final String s) {
		int[] v =new int[1];
		boolean ok;
	
		ok = convive.Convertir(s, v);
		if (ok == true)
			this.setNbannees(v[0]);	
	}
	
	private boolean getNbannneesDef() {
		return (this._nbanneesdef);
	}
	
	// Nombre d'années en lecture
	// Retourne true si la valeur a été définie
	// et false sinon
	// La paramètre de sortie contient
	// la valeur si elle a été définie
	
	public boolean getNbannees(
			int[] v){
		boolean ok;
		
		ok = this.getNbannneesDef();
		if (ok == true)
			v[0] = this._nbannees;
		return (ok);
	}
	
	// surcharge du getteur du nombre d'année
	// Retourne null si la valeur n'est pas définie
	// si la valeur a été définie, elle est retournée dans
	// une chaine de caractères
	
	public String getNbannees() {
		String s;
		int[] v = new int[1];
		boolean ok;
		
		ok = this.getNbannees(v);
		if (ok == true)
			s = convive.Formatter(v[0]);
		else
			s = null;
		return (s);
	}	
		
	// mode de recherche
	
	public void setModeRechercheNbannees(final int m) {
		this._moderecherchenbannees = m;
	}
	
	private int getModeRechercheNbannees() {
		return (this._moderecherchenbannees);
	}
	
	// Redéfinition de la méthode getEntete
	@Override
	protected String getEntete() {
		return ("<FORMATEUR>");
	}
	
	// Redéfinition de la méthode getCaracteristiques
	@Override
	protected String getCaracteristiques() {
		String s;
		String nba;
				
		nba = this.getNbannees();
		if (nba == null) nba = convive.getIndefini();
		
		s =
			"est présent depuis "+
			"["+nba+"] annee(s)";
		return (s);	
	}	
	
	// retourne true si le prix peut etre calculé
	// et false sinon
	// Le prix est recupéré dans le paramètre de sortie
	@Override
	public boolean CalculerPrix(
			final double pb,
			double[] p
			) {
		double participation;
		int[] nba = new int[1];
		boolean ok;
		
		ok = this.getNbannees(nba);
		if (ok == false) return (false);
		
		participation = 
				(pb*10./100.)+1.50+(nba[0]*0.2);
		p[0] = pb+participation;
		
		return (true);	
	}
	
	@Override
	protected void CopierCaracteristiques(final convive src){
		formateur osrc;
		int[] nba = new int[1];
		
		if (this.getType() != src.getType())
			return;
		
		// detypage de l'objet src en un objet formateur
		// PARCE QU'ON EST SUR QUE SRC EST UN FORMATEUR !!!
		osrc = (formateur)src;
		
		if (osrc.getNbannees(nba)==true)
			this.setNbannees(nba[0]);
	}
	
	// méthodes de comparaison
	// ***********************
	
	@Override
	protected boolean ComparerCaracteristiques(final convive src){
		formateur osrc;
		int[] nbasrc = new int[1];
		int[] nbacour = new int[1];
		boolean oksrc,okcour;
		int nbcompar;
		
		nbcompar = 0;
		if (this.getType() != src.getType())
			return (false);
		
		osrc = (formateur)src;
		
		oksrc = osrc.getNbannees(nbasrc);
		okcour = this.getNbannees(nbacour);
		if (oksrc == false) nbasrc[0] = Integer.MIN_VALUE;
		if (okcour == false) nbacour[0] = Integer.MAX_VALUE;
		if (convive.Comparer(
			nbasrc[0], 
			osrc.getModeRechercheNbannees(), 
			nbacour[0])==true)
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
	
	public static boolean VerifierNbannees(
			final String s,
			int[] cerr) {
		return (convive.Verifier(s, NBAMINI, NBAMAXI, cerr));
	}
	
	public static String AideNbannees() {
		return (convive.Aide(
				"Le nombre d'annee de presence", 
				NBAMINI, 
				NBAMAXI));
	}		
}
