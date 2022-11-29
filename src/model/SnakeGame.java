package model;

import java.util.ArrayList;

import javax.swing.JFrame;

import agent.Agent;
import agent.Snake;
import factory.*;
import strategyDeplacement.*;
import utils.FeaturesItem;


public class SnakeGame extends Game {
	
	public Factory fabrique = new SnakeFactory();
	ArrayList<Agent> agent_list= new ArrayList<Agent>();
	ArrayList<FeaturesItem> items_list= new ArrayList<FeaturesItem>();
	JFrame frame =new JFrame();
	

	public SnakeGame(int maxturn ,InputMap im) {
		super(maxturn);
		this.im=im;
	}
	
	public void notifierObservateurs() {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(this.turn,agent_list,items_list);
			}
	}

	@Override
	public void takeTurn() {
			
			System.out.println("Tour "+this.turn+" du jeu en cours");
			System.out.println(gameContinue());
		
			for (int ag=0; ag<agent_list.size();++ag) {  //chaque agent effectue une action */
				if(!agent_list.get(ag).elimination()) {
					agent_list.get(ag).moveAgent(im);
					agent_list.get(ag).eatItem( items_list);
					//agent.InteractionEntreAgents(agent_list.get(0));
					
				}
				if(agent_list.get(ag).elimination()) {
					agent_list.remove(agent_list.get(ag));
					System.out.println("Agent éliminé !"); 
					}	
				notifierObservateurs();
			}
	}
	
	
	
	
	public void initializeGame(InputMap input) { //initialisation du jeu et récupération des éléments du layout
		System.out.println("nb d'agents  "+agent_list.size());
		 for( Agent f: input.getStart_snakes()) { 	//récuperation des agents snakes		 
			 Snake s= fabrique.createAgent(f.getPositions(), f.getLastAction(), f.getColorSnake(), f.isInvincible(), f.isInvincible());
			 
			 s.setStrategy(strat);  //stratégie random pour le moment
			 agent_list.add(s);	
			 
		 }
		 
		 System.out.println("nb d'agents  "+agent_list.size());
		 for(FeaturesItem it : input.getStart_items()) { //récupérations des items
			 items_list.add(it);			 
		 }
		 
		for( Agent a : agent_list) {
			System.out.println( "position de  l'agent: " + a.getPositions().get(0).getX() +"," +a.getPositions().get(0).getY() +"\n");
		}
		for (FeaturesItem it: items_list) {
			System.out.println( "position de  l'item: " + it.getX() +"," +it.getY() +"\n");
		}
		
	}

	@Override
	public void setStrategie(DeplacementStrategie s) {
		this.strat=s;
		
	}



}
