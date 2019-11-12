package vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class menu {
	private static String SAUTDELIGNE = 
			System.getProperty("line.separator");
	private static String TABULATION = 
		"\t";
	private static final String MSGERRDESAISIEDEF = 
		"Erreur de saisie de la reponse";
	private static final String MSGERRDECHOIXDEF = 
		"Le choix est invalide";
	private static final String QUESTIONCHOIXDEF = 
		"Veuillez saisir votre choix SVP";
	
	private BufferedReader _cin;
	private String _titre;
	private ArrayList<item> _cit;
	
	private String getTitre() {
		return this._titre;
	}
	public void setTitre(final String titre) {
		this._titre = titre;
	}
	
	public item Ajouter(){
		item it;
		it= new item();
		this._cit.add(it);
		return (it);
	}
	
	public void Ajouter(
			final String choix, 
			final String libelle,
			final int action){
		item it;
		it= new item(choix,libelle,action);
		this._cit.add(it);
	}
			
	public void SauterUneLigne(){
		this.Ajouter();
	}
	
	private void Initialiser(){
		this._cin = new BufferedReader(
				new InputStreamReader(System.in));
		this._cit = new ArrayList<item>();
		this.setTitre("MENU");
	}

	public menu(){
		this.Initialiser();
	}

	public menu(String titre){
		this.Initialiser();
		this.setTitre(titre);
	}
	
	private int getNbitem(){
		return (this._cit.size());
	}

	private item get(final int i){
		if (
		(i<0)||
		(i>= this.getNbitem())
		) return (null);
		return (this._cit.get(i));
	}
	
	@Override
	public String toString() {
		String str;
		item it;
		String tirets;
		char[] ctirets;
		int nbtirets;
		
		str = "";

		nbtirets = this.getTitre().length();
		ctirets = new char[nbtirets];
		for (int i=0;i<nbtirets;i++) ctirets[i] = '-';
		tirets = new String(ctirets);
		
		str +=
			TABULATION +
			TABULATION + 
			tirets+
			SAUTDELIGNE;

		str += 
			TABULATION +
			TABULATION +
			this.getTitre()+
			SAUTDELIGNE;

		str +=
			TABULATION +
			TABULATION + 
			tirets+
			SAUTDELIGNE;


		for (
				int i=0;
				i<this.getNbitem();
				i++){
			it = this.get(i);
			if (it != null){
				str += it;
				str += SAUTDELIGNE;
			}
		}	
		return (str);
	}
	
	private int ChercherChoix(final String ch){
		int trouve;
		item it;
		String choix;

		trouve = -1;
		for (
			int i=0;
			(trouve == -1)&&(i<this.getNbitem());
			i++){
			it = this.get(i);
			if (it!= null){
				choix = it.getChoix();
				if (choix != null)
					if (choix.equals(ch))
						trouve = i;
			}
		}
		return (trouve);
	}
	
	private int LireChoix(){
		String reponse;
		int i;
		boolean ok;

		i= -1;
		do{
			System.out.print(
					QUESTIONCHOIXDEF+": ");

			reponse = "";

			ok = true;

			try {
				reponse = this._cin.readLine();
			} catch (IOException e) {
				System.out.println (
						MSGERRDESAISIEDEF
						);
				ok=false;
			}

			if (ok == true){
				i = this.ChercherChoix(reponse);
				if (i == -1){
					System.out.println (
							"<" +reponse + ">"+
							" : "+
							MSGERRDECHOIXDEF
					);
					ok = false;
				}
			}
		}while (ok==false);
		return (i);
	}
	
	public int LireAction(){
		int i;
		int act;
		item it;
		i = this.LireChoix();
		it = this.get(i);
		act = it.getAction();
		return (act);
	}
}
