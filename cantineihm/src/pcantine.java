
public class pcantine {
	public static void main(String[] args) {
		int type;
		
		type= 100;
		
		switch (type) {
		case 1:
			System.out.println("Tests du polymorphisme");
			modele.tester.TesterPolymorphisme();
			break;
		case 2:
			System.out.println("Tests d'un collectionneur");
			modele.tester.TesterUneCollection();
			break;
		case 3:
			System.out.println("Exercice collectionneur v1");
			modele.tester.TesterCollectionsv1(25,3,8,5);
			break;
		case 4:
			System.out.println("Exercice collectionneur v2");
			modele.tester.TesterCollectionsv2(25,3,8,5);
			break;
		case 5:
			System.out.println("Exercice collectionneur v3");
			modele.tester.TesterCollectionsv3(25,3,8,5);
			break;
		case 6:
			System.out.println("Tests recherche et tri");
			modele.tester.TesterRechercheEtTri(3,3,3,3);
			break;
		case 7:
			System.out.println("Tests des vérifications");
			modele.tester.TesterVerifications();
			break;
		case 8:
			System.out.println("Tests du menu");
			vue.tester.TesterMenu();
			break;
		case 100:
			System.out.println("Cantine IHM/MVC");
			controleur.ctrl.Exploiter();
			break;
		default:
			System.out.println("Aucun traitement");
			break;
		}
	}
}
