package model;

public class SnakeGame extends Game {

	public SnakeGame(int maxturn) {
		super(maxturn);
		notifierObservateurs();
	}

	@Override
	public void takeTurn() {
		System.out.println("Tour "+this.turn+" du jeu en cours");
		System.out.println(gameContinue());
		
	}

}
