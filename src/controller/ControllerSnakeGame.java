package controller;

import model.*;
import strategyDeplacement.*;
import view.ViewCommand;
import view.ViewSnakeGame;

public class ControllerSnakeGame extends AbstractController{
	
	private ViewSnakeGame vsg;
	private ViewCommand vc ;
	private InputMap input;
	private String file;
	private DeplacementStrategie strat1;
	private DeplacementStrategie strat2;
	
	public ControllerSnakeGame(Game g, String im) throws Exception {
		super(g);
		this.file=im;
	
		startGame();
	}
	
	public void startGame() throws Exception {
		input = new InputMap(file);
		g = new SnakeGame(5000,input);

		vsg = new ViewSnakeGame(g, input);
		
	    strat1 = new DeplacementInteractif(1,vsg.getFrame());
	    strat2 = new DeplacementInteractif(2,vsg.getFrame());
	    g.setStrategie(strat1,strat2);
		g.init();
	    vc = new ViewCommand(g,this);
	    vsg.viewInterface();
	    vc.commande();
	}
	
	

	//fonction qui permet de recharger le jeu à son état initial
	public void restart(){
		vsg.getFrame().dispose();
		vc.getFrame().dispose();
		
		
		try {
			startGame();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
	
	
	

