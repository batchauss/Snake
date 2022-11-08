package controller;

import model.*;
import view.ViewCommand;
import view.ViewSnakeGame;

public class ControllerSnakeGame extends AbstractController{
	
	private ViewSnakeGame vsg;
	private ViewCommand vc ;
	
	public ControllerSnakeGame(Game g, InputMap im) {
		super(g);
		vsg = new ViewSnakeGame(g, im);
	    vc = new ViewCommand(g,this);
	    vsg.viewInterface();
	    vc.commande();
	}

}
