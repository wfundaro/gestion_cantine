package vue;

public class item {
	private String _choix;
	private String _libelle;
	private int    _action;
	
	public String getChoix() {
		return this._choix;
	}
	private void setChoix(final String choix) {
		this._choix = choix;
	}
	
	private String getLibelle() {
		return this._libelle;
	}
	private void setLibelle(final String libelle) {
		this._libelle = libelle;
	}
	
	public int getAction() {
		return this._action;
	}
	private void setAction(final int action) {
		this._action = action;
	}
	
	public item(
			final String choix, 
			final String libelle,
			final int action){	
		this.setChoix(choix);
		this.setLibelle(libelle);
		this.setAction(action);
	}	
	
	public item(){
		this.setChoix(null);
		this.setLibelle(null);
		this.setAction(0);
	}
	
	@Override
	public String toString() {
		String str;
		String c,l;
		
		str = "";
		
		c = this.getChoix();
		l = this.getLibelle();
		
		if (
				(c!=null)&&
				(l!=null)
				){
		str += 
			c +
			" - "+
			l;
		}
		return (str);
	}	
}
