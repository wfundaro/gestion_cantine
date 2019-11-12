package modele;

public class stagiaireCif extends stagiaire{
	
	// **********
	// Constantes
	// **********
	
	private static final int TYPE = 5000;
		
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
	
	public stagiaireCif() {
		super();
		convive.AfficherTrace("Constructeur stagiaire en cif par defaut");
	}
	
	// surcharges constructeurs
	public stagiaireCif(
			final String nom) {
		super(nom);
		convive.AfficherTrace("Constructeur stagiaire en cif avec le nom");
	}
	
	public stagiaireCif(
			final String prenom,
			final String nom) {
		super(prenom,nom);
		convive.AfficherTrace("Constructeur stagiaire en cif avec le prénom et le nom");
	}
	
	public stagiaireCif(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur stagiaire en cif avec le prénom, le nom et l'age");
	}
	
	public stagiaireCif(
			final String prenom,
			final String nom,
			final int age,
			final String formation) {
		super(prenom,nom,age,formation);
		convive.AfficherTrace("Constructeur stagiaire en cif complet");
	}
	
	// Redéfinition de la méthode getEntete
	@Override
	protected String getEntete() {
		return ("<STAGIAIRE CIF>");
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
		
		participation = (pb*0.05)+2.50;
		p[0] = pb+participation;	
		return (true);	
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
}
