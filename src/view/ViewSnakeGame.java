package view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import observables.Observable;
import observables.Observateur;
import model.InputMap;

public class ViewSnakeGame implements Observateur  {
	
	//chemin en dur du layout à afficher
	private InputMap chemin_plateau ;

	protected int turn_update;
    private JFrame jFrame =new JFrame();
    private JLabel texte = new JLabel();
    private JPanel plateau;

	
	public ViewSnakeGame(Observable obs, InputMap chemin)  {
		obs.enregistrerObservateur(this);
		this.chemin_plateau=chemin;
		plateau = new PanelSnakeGame(chemin_plateau.getSizeX(),chemin_plateau.getSizeY(), chemin_plateau.get_walls(), chemin_plateau.getStart_snakes(), chemin_plateau.getStart_items());
		}

	 
	    
	public void viewInterface() {
	    	
	    texte.setHorizontalAlignment(JLabel.CENTER);
	    texte.setVerticalAlignment(JLabel.CENTER);
	    texte.setVisible(true);
	    
	    jFrame.add(texte);
	    jFrame.setTitle("Game");
	    	
	    plateau.setVisible(true);
	    jFrame.add(plateau);
	    

	    jFrame.setSize(new Dimension(500,500));
	    Dimension windowSize = jFrame.getSize();
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Point centerPoint = ge.getCenterPoint();
	    int dx = centerPoint.x - windowSize.width / 2 ;
	    int dy = centerPoint.y - windowSize.height / 2 - 350;
	    jFrame.setLocation(dx, dy);
	    jFrame.setVisible(true);
	    }

	   
	@Override
	public void actualiser(int turn) {
		System.out.println( "Nouveau tour (interface graphique ViewSnakeGame) :" + turn);
		turn_update=turn;
		texte.setText("Turn: "+ turn_update);
		}
		



}
