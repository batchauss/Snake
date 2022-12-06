package controller;

import model.*;
import strategyDeplacement.*;
import view.ViewCommand;
import view.ViewSnakeGame;

public class ControllerSnakeGame extends AbstractController{
	
	private ViewSnakeGame vsg;
	private ViewCommand vc ;
	private DeplacementStrategie strat1;
	private DeplacementStrategie strat2;
	
	public ControllerSnakeGame(Game g, InputMap im) {
		super(g);
		
		vsg = new ViewSnakeGame(g, im);
		
	    strat1 = new DeplacementInteractif(1,vsg.getFrame());
	    strat2 = new DeplacementInteractif(2,vsg.getFrame());
	    g.setStrategie(strat1,strat2);
	    
	    vc = new ViewCommand(g,this);
	    vsg.viewInterface();
	    vc.commande();

	
	}
	
}
	
	
	

