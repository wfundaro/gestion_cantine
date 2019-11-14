package modele;

public class stagiaire extends convive{
	
	// **********
	// Constantes
	// **********
	
	private static final int TYPE = 4000;
	private static final int LGMINFORMATION = 1;
	private static final int LGMAXFORMATION = 10;
	//private static final String RXFORMATION = "^[a-zA-Z]{2}[0-9]{4}$";
	private static final String RXFORMATION = "^[a-zA-Z]{1,10}";
	
	
	//	*************************
	// 	Les attributs d'instance
	//	Ce sont les données communes
	// 	aux méthodes de la classe
	//  ET ASSOCIE A UN OBJET
	// 	ILS SONT TOUJOURS PRIVES
	//	**************************
	
	// La formation
	// La valeur null correspond à une formation qui
	// n'est pas définie
	private String _formation;
	
	// Mode de recherche pour la formation
	// 0 pour == formation
	// 1 pour > formation (alphabetic)
	// -1 pour < formation (alphabetic)
	// 100 pour aucun
	
	private int _moderechercheformation;
	
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
		this.setFormation(null);
		this.setModeRechercheFormation(0);
	}
	
	public stagiaire() {
		super();
		convive.AfficherTrace("Constructeur stagiaire par defaut");
		this.Initialiser();
	}
	
	// surcharges constructeurs
	public stagiaire(
			final String nom) {
		super(nom);
		convive.AfficherTrace("Constructeur stagiaire avec le nom");
		this.Initialiser();
	}
	
	public stagiaire(
			final String prenom,
			final String nom) {
		super(prenom,nom);
		convive.AfficherTrace("Constructeur stagiaire avec le prénom et le nom");
		this.Initialiser();
	}
	
	public stagiaire(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur stagiaire avec le prénom, le nom et l'age");
		this.Initialiser();
	}
	
	public stagiaire(
			final String prenom,
			final String nom,
			final int age,
			final String formation) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur stagiaire complet");
		this.Initialiser();
		this.setFormation(formation);
	}
	
	// Les accesseurs
	
	// formation en écriture
	public void setFormation(final String n) {
		this._formation = n;	
	}
	
	// Formation en lecture
	// Retourne null si la formation n'est pas définie
	public String getFormation() {
		return (this._formation);
	}
	
	private String getFormationF() {
		String s;
		s = this.getFormation();
		if (s == null)
			s = convive.getIndefini();
		return(Formatter(s,LGMAXFORMATION));
	}

	public void setModeRechercheFormation(final int m) {
		this._moderechercheformation = m;
	}
	
	private int getModeRechercheFormation() {
		return (this._moderechercheformation);
	}
	
	// Redéfinition de la méthode getEntete
	@Override
	protected String getEntete() {
		return ("<STAGIAIRE>");
	}
		
	// Redéfinition de la méthode getCaracteristiques
	@Override
	protected String getCaracteristiques() {
		String s;
		String formation;
				
		formation = this.getFormationF();
		if (formation == null) formation = convive.getIndefini();
		
		s =
			"suit la formation de "+
			"["+formation+"]";
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
		double participation;	
		
		participation = 1.0;
		p[0] = pb+participation;	
		return (true);	
	}
	
	@Override
	protected void CopierCaracteristiques(final convive src){
		String f;
		stagiaire osrc;	

		if (this.getType() != src.getType())
			return;
		
		// detypage de l'objet src en un objet stagiaire
		// PARCE QU'ON EST SUR QUE SRC EST UN STAGIAIRE !!!
		osrc = (stagiaire)src;
		
		f = osrc.getFormation();
		if (f != null) this.setFormation(f);
	}
	
	// méthodes de comparaison
	// ***********************
	
	@Override
	protected boolean ComparerCaracteristiques(final convive src){
		stagiaire osrc;
		String csrc;
		String ccour;
		boolean oksrc,okcour;
		int nbcompar;
		
		nbcompar = 0;
		if (this.getType() != src.getType())
			return (false);
		
		osrc = (stagiaire)src;
		
		csrc = osrc.getFormation();
		ccour = this.getFormation();
		oksrc = (csrc!=null);
		okcour = (ccour!=null);
		if (oksrc == false) csrc = convive.getIndefini();
		if (okcour == false) ccour = convive.getIndefini(); 
		if (convive.Comparer(
			csrc, 
			osrc.getModeRechercheFormation(), 
			ccour)==true)
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
	
	public static boolean VerifierFormation(
			final String s,
			int[] cerr) {
		return (convive.Verifier(s, LGMINFORMATION, LGMAXFORMATION, RXFORMATION, cerr));
	}
	
	public static String AideFormation() {
		return (Aide(
				"Le nom de la formation", 
				LGMINFORMATION, 
				LGMAXFORMATION,
				" caractères alphabétiques"));
	}
		
}
