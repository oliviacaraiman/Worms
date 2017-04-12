import java.awt.*;
import javax.swing.*;

public class FenetreRegles extends JFrame{
	
	private JPanel Panel;
	private JTextArea texte = new JTextArea("Règles");
	
	/**
	 * Constructeur de la classe FenetreRegles, qui affiche les règles du jeu.
	 */
	public FenetreRegles(){
		this.Panel = new JPanel();
		setSize(2000,1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
        
		texte.setOpaque(true);
        texte.setBackground(Color.BLACK);
        texte.setEditable(false);
        texte.setForeground(Color.WHITE);
		texte.setFont(new Font("Marker Felt", Font.BOLD, 35));
        texte.setText("Bienvenue dans Worms!"
        		+ "\n" + ""
        		+ "\n" + "Règles du jeu :"
        		+ "\n" + "Le but du jeu est de tuer l'adversaire à l'aide de lancers de grenades."
        		+ "\n" + "Pour vous déplacer utilisez les touches fléchées et la flèche supérieure pour sauter. "
        		+ "\n" + "Faite attention votre distance de déplacement est limitée!"
        		+ "\n" + ""
        		+ "\n" + "Afin de lancer une grenade il vous suffira de saisir la puissance initiale et l'angle de tir, "
        		+ "\n" + "puis de cliquer sur le panneau indiquant le côté droit ou gauche,"
        		+ "\n" + "et enfin d'appuyer sur la touche D. Vous n'avez qu'une grenade par tour."
        		+ "\n" + "Appuyer alors sur la touche 'entrer' pour passer la main à l'adversaire."
        		+ "\n" + ""
        		+ "\n" +"Bonne partie!");
		Panel.setBackground(Color.BLACK);
       
        Panel.add(texte);
        this.setContentPane(Panel);
        setVisible(true);
	}
}