package model;
import java.util.ArrayList;
import java.util.List;
import observables.*;

public abstract class Game implements Runnable, Observable{

	protected int turn;
	protected int maxturn;
	protected boolean isRunning;
	protected Thread thread;
	protected long time=1000;
	
	
	private List<Observateur> observateurs = new ArrayList<>();

	public void enregistrerObservateur(Observateur observateur){
		observateurs.add(observateur);
	}
	public void supprimerObservateur(Observateur observateur){
		observateurs.remove(observateur);
	}
	public void notifierObservateurs() {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(this.turn);
			}
	}
	
	//getters sur les éléments de Game 
	public int getTurn() {
		return this.turn;
	}
	
	public long getTime() {
		return this.time;
	}
	
	//setters sur les éléments de Game
	public void setTurn(int turn) {
		this.turn=turn;
		notifierObservateurs();
	}
	
	public void setTime(long time) {
		this.time=time;
		notifierObservateurs();
	}
	
	
	
	public Game(int maxturn) {
		this.maxturn =maxturn;
	}
	
	//initialisation du jeu
	public void init() { 
		turn =0;
		isRunning=true;
		//initializeGame(); à implementer plus tard
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
		if(turn==maxturn) {
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
				
				//gameContinue();
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










