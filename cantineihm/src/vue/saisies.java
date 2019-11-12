package vue;

import java.io.*;

public class saisies {
	// variable commune à toutes
	// les fonctions du fichier
	
	private static BufferedReader cin;
		
	// retourne null si erreur de saisie
	private static String Saisir(){
		String s;
		
		s = null;
		try {
			s = cin.readLine();
		} catch (IOException e) {
			System.out.println("Erreur de communication");
		}
		return (s);
	}
	
	public static String SaisirChaine(
			final String question){
		System.out.print(question+": ");
		return (Saisir());
	}
	
	public static void Initialiser(){
		cin = new BufferedReader(
				new InputStreamReader(System.in)
				);
	}
	
	public static void Terminer(){
		try {
			cin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean Questionner(
		final String question){
		String q;
		String r;
		boolean rok;
		boolean oui,non;
		
		q = 
			question+
			" (y/Y/o/O/n/N)";
		
		oui = false;
		do{
			r = SaisirChaine(q);
			// la reponse doit etre y/Y/o/O/n/N
			if (r != null){
				oui = 
						(r.equals("y"))||
						(r.equals("o"))||
						(r.equals("Y"))||
						(r.equals("O"));
				non = 
						(r.equals("n"))||
						(r.equals("N"));
				rok = (oui ==true)||(non == true);
				if (rok == false){
					System.out.println(
							"La reponse "+
							"["+r+"]"+
							" n'est pas autorisee");
				}
			}
			else
				rok = false;
		}while (rok == false);
		return (oui);
	}
	
	public static void Attendre(){
		SaisirChaine("Taper sur Entree pour continuer");
	}
}
