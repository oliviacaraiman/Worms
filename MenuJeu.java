import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;

public class MenuJeu extends JFrame {
	
	//private JLabel title = new JLabel("WORMS",20);
	private JButton b1;
	private JButton b2;
	private JLabel lgd1 = new JLabel("Player 1");
	private JTextField nomJoueur1 = new JTextField("votre prenom",10);
	private JLabel lgd2 = new JLabel("Player 2");
	private JTextField nomJoueur2 = new JTextField("votre prenom",10);
	
	
	public MenuJeu (){
		
		
		ImageFond i = new ImageFond();
        i.setLayout(null);
		
        //JPanel p = new JPanel(new GridLayout(3,1)); 
		//p.setSize(500,500);
		//p.setBackground(Color.BLACK);
		//i.add(p,BorderLayout.SOUTH);
		
		
		//JPanel panel2 = new JPanel(new GridLayout());
		//panel2.setSize(600,400);
		//panel.add(panel2);
		
		 b1 = new JButton("START GAME");
		 b2 = new JButton ("Credits");
		 b1.setBounds(300, 450, 200, 50);
         b2.setBounds(600,450, 200, 50);
         
         lgd1.setBounds(250,100,200,50);
         nomJoueur1.setBounds(350, 120, 200,50);
         lgd2.setBounds(250,200,200,50);
         nomJoueur2.setBounds(350,220,200,50);
         
         
        
		
		//GridBagConstraints g = new GridBagConstraints();
		//g.fill = GridBagConstraints.BOTH;
		//g.insets = new Insets(2, 2, 2, 2);
		//g.weightx = 500;
		
		//i.add(title);
		i.add(lgd1);
        i.add(nomJoueur1);
        i.add(lgd2);
        i.add(nomJoueur2);
        i.add(b1);
        i.add(b2);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(i);
		setSize(1000,1000);
		setVisible(true);
        
		
		b1.setSize(200,100);
		b1.setOpaque(true);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.RED);
		b1.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		lgd1.setSize(200,100);
		lgd1.setOpaque(true);
		lgd1.setBackground(Color.BLACK);
		lgd1.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,50));
		lgd1.setText("<html><body><font color='red'>Player 1:</body></html>");

		lgd2.setSize(200,100);
		b2.setSize(200,100);
		b2.setOpaque(true);
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.RED);
		b2.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		
		lgd2.setOpaque(true);
		lgd2.setBackground(Color.BLACK);
		lgd2.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,50));
		lgd2.setText("<html><body><font color='red'>Player 2:</body></html>");

		
		b2.addActionListener(new EcouteurCredits());
	}
	
	public static void main(String[] a) {
		new MenuJeu();
	}
}
