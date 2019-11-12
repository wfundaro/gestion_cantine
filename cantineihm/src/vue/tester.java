package vue;

public class tester {
	private static final int ACTCH1 = 10;
	private static final int ACTCH2 = ACTCH1+1;
	private static final int ACTCHSM = ACTCH2+1;
	private static final int ACTCH4 = ACTCHSM+1;
	private static final int ACTCH5 = ACTCH4+1;
	private static final int ACTQUITTER = -100;
	private static final int ACTRETOUR = -200;
	
	private static menu m, ssm;
	
	private static void CreerMenus(){
		m.Ajouter("A", "CHOIX1", ACTCH1);
		m.Ajouter("B", "CHOIX2", ACTCH2);
		m.Ajouter("SM", "SOUS MENU ->", ACTCHSM);
		m.SauterUneLigne();
		m.Ajouter("Q", "QUITTER", ACTQUITTER);
		m.Ajouter("q", "QUITTER", ACTQUITTER);
		
		ssm.Ajouter("1", "CHOIX2", ACTCH2);
		ssm.Ajouter("2", "CHOIX4", ACTCH4);
		ssm.Ajouter("3", "CHOIX5", ACTCH5);
		ssm.SauterUneLigne();
		ssm.Ajouter("R", "RETOUR", ACTRETOUR);
		ssm.Ajouter("r", "RETOUR", ACTRETOUR);
	}
	
	private static boolean TraiterAction(
			final int act){
		boolean fin;
		
		fin = false;
		switch (act){
		case ACTQUITTER:
			if (saisies.Questionner(
					"Voulez vous quitter")== true)
				fin = true;
			break;
		case ACTRETOUR:
			fin=true;
			break;
		case ACTCHSM:
			GererMenu(ssm);
			break;
		default:
			System.out.println(act);
			saisies.Attendre();
		}
		return (fin);
	}
	
	private static void GererMenu(
			final menu om){
		int act;
		boolean fin;
		
		do{
			System.out.println(om);
			act = om.LireAction();
			fin = TraiterAction(act);
		}while (fin == false);
	}
	
	public static void TesterMenu(){
		saisies.Initialiser();
		
		m = new menu("MENU PRINCIPAL");
		ssm = new menu("SOUS MENU");
		
		CreerMenus();		
		GererMenu(m);
		
		saisies.Terminer();
	}
}
