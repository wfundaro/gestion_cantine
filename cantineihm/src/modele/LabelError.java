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
	//Variable qui nous servira � compter le nombre de fois ou la m�thode blink est relanc�e
	private int countBlink;
	//Constante qui nous donne le nombre de fois que nous voulons faire clignoter notre texte
	private final int NB_BLINK = 8;
	//Constante qui donne le temps avant de relancer la m�thode Blink, cela nous donne le clignotement
	private final int DURATION = 400;
	private String text;
	
	//Constructeur avec seulement le texte
	public LabelError(String text) {
		super(text);
		this.text = text;
		this.setForeground(Color.RED);
		this.setError(false);
	}
	
	//m�thode pour mettre en erreur le texte avec d�clenchement du clignotement
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
		//Si le compteur atteint la constante NB_BLINK on arr�te de faire clignoter
		if (countBlink == NB_BLINK) {
			//R�initialisation du compteur pour la prochaine fois
			countBlink =0;
			//On laisse le texte affich�
			this.setText(this.text);
			return;
		} else { //Nous n'avons pas atteint le nombre de clignotement max donc on alterne
			//On cr�e un timer qui va relancer la m�thode automatiquement toutes les DURATION
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
					//La m�thode sera relanc� au bout de DURATION
					blink();
				}
			}, DURATION);
		}
		//Incr�ment du compteur de clignotement
		this.countBlink++;
	}
	
}
