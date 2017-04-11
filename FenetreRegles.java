import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class FenetreRegles extends JFrame{
	
	private JPanel Panel;
	private JTextArea texte = new JTextArea("Règles");
	
	
	public FenetreRegles(){
		
		
		
		this.Panel = new JPanel();
		
		
		setSize(2000,1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
		
        
        this.setContentPane(Panel);
        
        
        setVisible(true);
        
        
     
        
        
        texte.setOpaque(true);
        //texte.setColor(Color.WHITE)
       
        texte.setBackground(Color.BLACK);
        texte.setEditable(false);
        texte.setForeground(Color.WHITE);
		texte.setFont(new Font("Marker Felt", Font.BOLD, 35));
        texte.setText("Bienvenue dans Worms!"
        		+ "\n" + "Le but du jeu est de tuer l'adversaire à l'aide de lancers de grenades."
        		+ "\n" + "Pour vous déplacer utilisez les touches fléchées et la flèche supérieure pour sauter. "
        		+ "\n" + "Faite attention votre distance de déplacement est limitée!"
        		+ "\n" + "Afin de lancer une grenade il vous suffira de saisir la puissance initiale et l'angle de tir, "
        		+ "\n" +"puis cliquer sur le panneau indiquant le côté droit ou gauche,"
        		+ "\n"+ " et enfin appuyer sur la touche D."
        		+ "\n" + "Appuyer alors sur la touche 'entrer' pour passer la main à l'adversaire."
        		+ "\n" +"Bonne partie!");
		Panel.setBackground(Color.BLACK);
       
        Panel.add(texte);
  
		
		}
}

