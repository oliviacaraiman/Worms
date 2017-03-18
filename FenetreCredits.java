
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class FenetreCredits extends JFrame{
	
	private JPanel Panel;
	private JLabel texte = new JLabel("Credits");
	
	
	public FenetreCredits(){
		
		
		
		this.Panel = new JPanel(new FlowLayout());
		
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
        
        this.setContentPane(Panel);
        
        
        setContentPane(Panel);
        setVisible(true);
        
        
     
        
        
        texte.setOpaque(true);
        texte.setBackground(Color.BLACK);
		texte.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,15));
        texte.setText("<html><body><font color='white'>Jeu développé en collaboration avec....:</body></html>");
        Panel.setBackground(Color.BLACK);
        Panel.add(texte);
        
        
        
		
		
		}
}

