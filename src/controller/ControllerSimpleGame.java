package controller;

import model.*;
import view.*;

public class ControllerSimpleGame extends AbstractController{
	
	private ViewSimpleGame vsg;
	private ViewCommand vc ;
	
	public ControllerSimpleGame(Game g) {
		super(g);
		vsg = new ViewSimpleGame(g);
	    vc = new ViewCommand(g,this);
	    vsg.viewInterface();
	    vc.commande();
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
