import java.awt.event.*;

public class EcouteurCredits implements ActionListener{
	
	FenetreCredits fen;
	
	/**
	 * Constructeur de la classe EcouteurCredits, qui ouvre une FenetreCredit lors de l'appui sur le bouton.
	 */
	public EcouteurCredits(){}
	
	/**
	 * Crée une nouvelle FenetreCredits lors de l'appui sur le bouton.
	 */
	public void actionPerformed (ActionEvent e) {
		fen = new FenetreCredits();
	}
}