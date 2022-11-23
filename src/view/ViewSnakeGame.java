package view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import agent.Agent;
import observables.Observable;
import observables.Observateur;
import utils.FeaturesItem;
import model.InputMap;

public class ViewSnakeGame implements Observateur  {
	
	//chemin en dur du layout à afficher
	private InputMap chemin_plateau ;

	protected int turn_update;
    private JFrame jFrame =new JFrame();
    private JLabel texte = new JLabel();
    private JPanel plateau;
    private PanelSnakeGame panelSnake;
    private ArrayList<Agent> agents;
    ArrayList<FeaturesItem>items;

	
	public ViewSnakeGame(Observable obs, InputMap chemin)  {
		obs.enregistrerObservateur(this);
		this.chemin_plateau=chemin;
		this.agents=chemin_plateau.getStart_snakes();
		panelSnake =new PanelSnakeGame(chemin_plateau.getSizeX(),chemin_plateau.getSizeY(), chemin_plateau.get_walls(), agents, chemin_plateau.getStart_items());
		plateau = panelSnake;
		}

	public JFrame getFrame() {
		return jFrame;
	}
	 
	    
	public void viewInterface() {
	    	
	    texte.setHorizontalAlignment(JLabel.CENTER);
	    texte.setVerticalAlignment(JLabel.CENTER);
	    texte.setVisible(true);
	    
	    jFrame.add(texte);
	    jFrame.setTitle("Game");
	    	
	    plateau.setVisible(true);
	    jFrame.add(plateau);
	    

	    jFrame.setSize(new Dimension(panelSnake.getSizeX()*40,panelSnake.getSizeY()*40));
	    Dimension windowSize = jFrame.getSize();
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    Point centerPoint = ge.getCenterPoint();
	    int dx = centerPoint.x - windowSize.width / 2 ;
	    int dy = centerPoint.y - windowSize.height / 2 - 350;
	    jFrame.setLocation(dx, dy);
	    jFrame.setVisible(true);
	    
	    
	    }
	
		

	   
	@Override
	public void actualiser(int turn,ArrayList<Agent> agent_list,ArrayList<FeaturesItem> items_list) {
		turn_update=turn;
		agents=agent_list;
		items=items_list;
		panelSnake.updateInfoGame(agent_list, items_list);
		plateau.repaint();
		
		for(Agent a:agents) {
			System.out.println( "position de  l'agent: " + a.getPositions().get(0).getX() +"," +a.getPositions().get(0).getY() );
			System.out.println( "dernier mouvement de  l'agent: " + a.getLastAction().toString() +"\n");
		}
		}
		



}
