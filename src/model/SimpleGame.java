package model;

public class SimpleGame extends Game  {

	public SimpleGame(int maxturn) {
		super(maxturn);
		notifierObservateurs();
	}

	@Override
	public void takeTurn() {
		System.out.println("Tour "+this.turn+" du jeu en cours");
		System.out.println(gameContinue());
		
	}

}
