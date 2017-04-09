
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class FenetreCredits extends JFrame{
	
	private JPanel Panel;
	private JLabel texte = new JLabel("Credits");
	
	
	public FenetreCredits(){
		
		
		
		this.Panel = new JPanel();
		
		
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
		
        
        this.setContentPane(Panel);
        
        
        setVisible(true);
        
        
     
        
        
        texte.setOpaque(true);
        //texte.setColor(Color.WHITE)
        texte.setBackground(Color.BLACK);
		texte.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,50));
        texte.setText("<html><font color='white'>Jeu développé en collaboration avec....:</font></html>");
		Panel.setBackground(Color.BLACK);
       
        Panel.add(texte);
  
		
		}
}

