package model;
import java.util.ArrayList;
import java.util.List;


import observables.*;
import strategyDeplacement.DeplacementStrategie;

public abstract class Game implements Runnable, Observable{

	protected int turn;
	protected int maxturn;
	protected boolean isRunning;
	protected Thread thread;
	protected long time=1000;
	protected DeplacementStrategie stratJ1;
	protected DeplacementStrategie stratJ2;
	protected InputMap im;
	
	protected List<Observateur> observateurs = new ArrayList<>();

	public void enregistrerObservateur(Observateur observateur){
		observateurs.add(observateur);
	}
	public void supprimerObservateur(Observateur observateur){
		observateurs.remove(observateur);
	}
	
	
	public abstract void notifierObservateurs() ;
	
	public abstract void initializeGame(InputMap input);
	
	public abstract void setStrategie(DeplacementStrategie s1,DeplacementStrategie s2);
	
	public int getTurn() {
		return this.turn;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public void setTurn(int turn) {
		this.turn=turn;
	}
	
	public void setTime(long time) {
		this.time=time;
	}
	
	
	
	public Game(int maxturn) {
		this.maxturn =maxturn;
	}
	
	//initialisation du jeu
	public void init() { 
		turn =0;
		isRunning=true;
		initializeGame(im); 
	}
	
	//tours du jeu pas a pas 
	public void step() {
		if(gameContinue()) {
			setTurn(turn +1);
			takeTurn();

		}
	}
	
	//vérifie si la partie continue ou si elle est terminée
	public boolean gameContinue() {
		if(turn>=maxturn) {
			isRunning=false;
			gameOver();
			return false;
		}
		else {
			return true;
		}
	}

	//affiche un message de fin de partie
	public void gameOver() {
		System.out.println("la partie est terminée ! ");
		isRunning=false;
	}
	
	public abstract void takeTurn();
	
	//mets la partie en pause
	public void pause() {
		isRunning = false;
	}
	
	//lance le jeu en pas à pas
	public void run() {
		while(isRunning) {
			step();
			try {
			Thread.sleep(time);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//lancement du jeu
	public void launch() {
		isRunning =true;
		thread= new Thread(this);
		thread.start();

	}
	

	
}










