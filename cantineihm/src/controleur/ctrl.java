package controleur;

import modele.colconvives;
import vue.ctrlvue;

public class ctrl {
		
	private static ctrlvue ctv;
	private static colconvives colC;
	
	public ctrl() {
		this.init();
	}
	
	private void init() {
		ctv = new ctrlvue(this);
		colC = new colconvives();
	}
	
	public void detruire() {
		colC.SupprimerAll();
	}
		
	public void ajouterConvive(final String _nom, final String _prenom, final String _age, final String _feature, final int _type) {
		switch (_type) {
		case 1:
			colC.ajouterStagiaireCif(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		case 2:
			colC.ajouterFormateur(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		case 3:
			colC.ajouterDirecteur(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		default:
			colC.ajouterStagiaire(_nom, _prenom, Integer.parseInt(_age), _feature);
			break;
		}
		this.ajouterConviveToVue(colC.getNbConvives() - 1);
	}
	
	private void ajouterConviveToVue(final int index) {
		this.ctv.addListeConvive(this.colC.toString(index));
	}
	
	private void ajouterAllConviveToVue() {
		this.ctv.destroyAllItem();
		for(int i=0; i < this.colC.getNbConvives(); i++) {
			this.ajouterConviveToVue(i);
		}
	}
	
	public void supprimer(int[] selectIndices) {
		for (int j = selectIndices.length-1; j >= 0; j--) {
		    colC.Supprimer(j);
		}
		ajouterAllConviveToVue();
	}

	public boolean checkNom(final String _nom) {
		System.out.println(_nom);
		if(_nom.matches("^[a-zA-Z]{1,20}")) {
			return true;
		}
		return false;
	}

	public boolean checkPrenom(final String _prenom) {
		if(_prenom.matches("[a-zA-Z]{1,15}")) {
			return true;
		}
		return false;
	}

	public boolean checkAge(final String _age) {
		boolean result = false;
		try {
			int age = Integer.parseInt(_age);
			if(age > 17 & age < 68)
				result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean checkFeature(final String _feature, final int _type) {
		boolean result = false;
		switch (_type) {
		case 2:
			try {
				int anciennete = Integer.parseInt(_feature);
				if(anciennete > 0 & anciennete < 43)
					result = true;
			} catch (Exception e) {
				result = false;
			}
			break;
		case 3:
			try {
				double salaire = Double.parseDouble(_feature.replaceAll(",", "."));
				if(salaire > 0)
					result = true;
			} catch (Exception e) {
				result = false;
			}			
			break;
		default:
			if(_feature.matches("[a-zA-Z]{1,10}"))
				result = true;
			break;
		}
		return result;
	}

}
