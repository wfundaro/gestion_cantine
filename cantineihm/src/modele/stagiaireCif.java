package modele;

public class stagiaireCif extends stagiaire{
	
	// **********
	// Constantes
	// **********
	
	private static final int TYPE = 5000;
		
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
		convive.AfficherTrace("Constructeur stagiaire en cif avec le pr�nom et le nom");
	}
	
	public stagiaireCif(
			final String prenom,
			final String nom,
			final int age) {
		super(prenom,nom,age);
		convive.AfficherTrace("Constructeur stagiaire en cif avec le pr�nom, le nom et l'age");
	}
	
	public stagiaireCif(
			final String prenom,
			final String nom,
			final int age,
			final String formation) {
		super(prenom,nom,age,formation);
		convive.AfficherTrace("Constructeur stagiaire en cif complet");
	}
	
	// Red�finition de la m�thode getEntete
	@Override
	protected String getEntete() {
		return ("<STAGIAIRE CIF>");
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
		
		participation = (pb*0.05)+2.50;
		p[0] = pb+participation;	
		return (true);	
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
}
