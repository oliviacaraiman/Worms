import java.awt.*;
import javax.swing.*;

public class FenetreCredits extends JFrame{
	
	private JPanel Panel;
	private JTextArea texte = new JTextArea("Credits");
	
	/**
	 * Constructeur de la classe FenetreCredits, qui affiche les crédits du jeu.
	 */
	public FenetreCredits(){
		this.Panel = new JPanel();
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
        texte.setOpaque(true);
        texte.setBackground(Color.BLACK);
        texte.setEditable(false);
        texte.setForeground(Color.WHITE);
        texte.setFont(new Font("Marker Felt",Font.BOLD,35));
        texte.setText("Jeu développé par: "
        		+ "\n" + "Boriasse Louis"
        		+ "\n" + "Caraiman Olivia"
        		+ "\n" + "Legendre Julien"
        		+ "\n" + "Slimani Sara"
        		+ "\n" + "Créé à l'aide de la  bibiliothèque Slick 2D."
+ "\n"+ "Il s'agit d'un jeu déstiné à un usage interne (INSA Lyon)");
		Panel.setBackground(Color.BLACK);
		
        Panel.add(texte);
		this.setContentPane(Panel);
        setVisible(true);  
	}
}