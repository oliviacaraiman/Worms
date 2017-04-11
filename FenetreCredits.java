
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class FenetreCredits extends JFrame{
	
	private JPanel Panel;
	private JTextArea texte = new JTextArea("Credits");
	
	
	public FenetreCredits(){
		
		
		
		this.Panel = new JPanel();
		
		
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
		
        
        this.setContentPane(Panel);
        
        
        setVisible(true);
        
        
     
        
        
        texte.setOpaque(true);
        //texte.setColor(Color.WHITE)
        texte.setBackground(Color.BLACK);
        texte.setEditable(false);
		//texte.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,50));
        texte.setForeground(Color.WHITE);
		texte.setFont(new Font("Marker Felt", Font.BOLD, 35));
        texte.setText("Jeu développé par: "
        		+ "\n" + "Boriasse Louis"
        		+ "\n" + "Caraiman Olivia"
        		+ "\n" + "Legendre Julien"
        		+ "\n" + "Slimani Sara"
        		+ "\n" + "Créé à l'aide de la  bibiliothèque Slick 2D."
        		+ "\n"+ "Il s'agit d'un jeu déstiné à un usage interne (INSA Lyon)");
		Panel.setBackground(Color.BLACK);
       
        Panel.add(texte);
  
		
		}
}

