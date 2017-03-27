import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOver extends JFrame {

	private JPanel pane ;
	private JLabel image ;

	public GameOver(int lg, int larg) {

        //on definit le nom de la fenetre
        super("Vous avez perdu ...");

        //Dimensions de la fenetre graphique et fermeture
        this.setSize(new Dimension(lg,larg));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icon = new ImageIcon("gameOver.jpg");
		JLabel image = new JLabel(icon) ;
						
        //Création d'un conteneur principal
        JPanel conteneurPrincipal = new JPanel(new FlowLayout());
        JPanel fen = new JPanel(new FlowLayout());
        //Coloration en blanc
        conteneurPrincipal.setBackground(Color.BLACK);
        //mise en place du conteneur principal dans la fenetre
        this.setContentPane(conteneurPrincipal);
		this.getContentPane().add(image);

        //Rendre la fenêtre visible
        this.setVisible(true);
        
    }

}

