package controller;

import model.*;
import strategyDeplacement.*;
import view.ViewCommand;
import view.ViewSnakeGame;

public class ControllerSnakeGame extends AbstractController{
	
	private ViewSnakeGame vsg;
	private ViewCommand vc ;
	private DeplacementStrategie strat;
	
	public ControllerSnakeGame(Game g, InputMap im) {
		super(g);
		
		vsg = new ViewSnakeGame(g, im);
	    strat = new DeplacementManuel(vsg.getFrame());
	    g.setStrategie(strat);
	    vc = new ViewCommand(g,this);
	    vsg.viewInterface();
	    vc.commande();

	    
	    
	}

}
