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
	//	Ce sont les donn�es communes
	// 	aux m�thodes de la classe
	//  ET ASSOCIE A UN OBJET
	// 	ILS SONT TOUJOURS PRIVES
	//	**************************
	
	// La formation
	// La valeur null correspond � une formation qui
	// n'est pas d�finie
	private String _formation;
	
	// Mode de recherche pour la formation
	// 0 pour == formation
	// 1 pour > formation (alphabetic)
	// -1 pour < formation (alphabetic)
	// 100 pour aucun
	
	private int _moderechercheformation;
	
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
		convive.AfficherTrace("Constructeur stagiaire avec le pr�nom et le nom");
		this.Initialiser();
	}
	
	public stagiaire(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur stagiaire avec le pr�nom, le nom et l'age");
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
	
	// formation en �criture
	public void setFormation(final String n) {
		this._formation = n;	
	}
	
	// Formation en lecture
	// Retourne null si la formation n'est pas d�finie
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
	
	// Red�finition de la m�thode getEntete
	@Override
	protected String getEntete() {
		return ("<STAGIAIRE>");
	}
		
	// Red�finition de la m�thode getCaracteristiques
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
			
	// retourne true si le prix peut etre calcul�
	// et false sinon
	// Le prix est recup�r� dans le param�tre de sortie
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
	
	// m�thodes de comparaison
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
		
	// m�thode d'acces au type
	
	// m�thode d'instance
	@Override
	public int getType() {
		return (TYPE);
	}
	
	// m�thode de classe	
	public static int getTypeClasse() {
		return (TYPE);
	}
	
	// m�thodes de classe pour les v�rifications
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
				" caract�res alphab�tiques"));
	}
		
}
