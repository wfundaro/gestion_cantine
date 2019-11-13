package modele;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class LabelError extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variable qui nous servira à compter le nombre de fois ou la méthode blink est relancée
	private int countBlink;
	//Constante qui nous donne le nombre de fois que nous voulons faire clignoter notre texte
	private final int NB_BLINK = 8;
	//Constante qui donne le temps avant de relancer la méthode Blink, cela nous donne le clignotement
	private final int DURATION = 400;
	private String text;
	
	//Constructeur avec seulement le texte
	public LabelError(String text) {
		super(text);
		this.text = text;
		this.setForeground(Color.RED);
		this.setError(false);
	}
	
	//méthode pour mettre en erreur le texte avec déclenchement du clignotement
	public void setError(boolean _valid) {
		if(_valid) {
			super.setText(this.text);
			blink();
		} else {
			super.setText("");
		}
	}
	
	@Override
	public void setText(final String str) {
		this.text = str;
		super.setText(str);
	}
	
	private void blink() {
		//Si le compteur atteint la constante NB_BLINK on arrête de faire clignoter
		if (countBlink == NB_BLINK) {
			//Réinitialisation du compteur pour la prochaine fois
			countBlink =0;
			//On laisse le texte affiché
			this.setText(this.text);
			return;
		} else { //Nous n'avons pas atteint le nombre de clignotement max donc on alterne
			//On crée un timer qui va relancer la méthode automatiquement toutes les DURATION
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					//On alterne text avec une chaine vide
					if (countBlink % 2 == 0) {
						LabelError.super.setText(LabelError.this.text);
					} else {
						LabelError.super.setText("");
					}
					//La méthode sera relancé au bout de DURATION
					blink();
				}
			}, DURATION);
		}
		//Incrément du compteur de clignotement
		this.countBlink++;
	}
	
}
