package model;


import strategyDeplacement.DeplacementStrategie;

public class SimpleGame extends Game  {

	public SimpleGame(int maxturn) {
		super(maxturn);
		//notifierObservateurs();
	}

	@Override
	public void takeTurn() {
		System.out.println("Tour "+this.turn+" du jeu en cours");
		System.out.println(gameContinue());
		
	}

	@Override
	public void initializeGame(InputMap input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierObservateurs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStrategie(DeplacementStrategie s1,DeplacementStrategie s2) {
		// TODO Auto-generated method stub
		
	}

	

}
