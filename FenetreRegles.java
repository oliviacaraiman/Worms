import java.awt.*;
import javax.swing.*;

public class FenetreRegles extends JFrame{
	
	private JPanel Panel;
	private JTextArea texte = new JTextArea("R�gles");
	
	
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
        		+ "\n" + "Le but du jeu est de tuer l'adversaire � l'aide de lancers de grenades."
        		+ "\n" + "Pour vous d�placer utilisez les touches fl�ch�es et la fl�che sup�rieure pour sauter. "
        		+ "\n" + "Faite attention votre distance de d�placement est limit�e!"
        		+ "\n" + "Afin de lancer une grenade il vous suffira de saisir la puissance initiale et l'angle de tir, "
        		+ "\n" +"puis cliquer sur le panneau indiquant le c�t� droit ou gauche,"
        		+ "\n"+ " et enfin appuyer sur la touche D."
        		+ "\n" + "Appuyer alors sur la touche 'entrer' pour passer la main � l'adversaire."
        		+ "\n" +"Bonne partie!");
		Panel.setBackground(Color.BLACK);
       
        Panel.add(texte);
        this.setContentPane(Panel);
        setVisible(true);
	}
}