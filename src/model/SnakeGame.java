package model;

import java.util.ArrayList;
import java.util.Random;

import agent.Agent;
import agent.Snake;
import factory.*;
import strategyDeplacement.*;
import utils.FeaturesItem;
import utils.ItemType;
import utils.Position;


public class SnakeGame extends Game {
	
    Factory fabrique = new SnakeFactory();
	ArrayList<Agent> agent_list= new ArrayList<Agent>();
	ArrayList<FeaturesItem> items_list= new ArrayList<FeaturesItem>();
	int probaItem =10;
	int tempsInvincible=0;
	int tempsMalade=0;
	

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
					agent_list.get(ag).InteractionEntreAgents(agent_list);
					eatItem(agent_list.get(ag), items_list);
					
					//gestion du temps d'invincibilité et de maladie du snake par rapport au nombre de tours (20 tours)
					if(agent_list.get(ag).isInvincible()) {
						++tempsInvincible;
					}
					if(tempsInvincible==20) {
						agent_list.get(ag).setInvincible(false);
						tempsInvincible=0;
					}
					if(agent_list.get(ag).isSick()) {
						++tempsMalade;
					}
					if(tempsMalade==20) {
						agent_list.get(ag).setSick(false);
						tempsMalade=0;
					}
					
				}
				if(agent_list.get(ag).elimination()) {
					agent_list.remove(agent_list.get(ag));
					System.out.println("Agent éliminé !"); 
					}	
				notifierObservateurs();
			}
			
			if(agent_list.size()==0) {
				gameOver();
			}
	}
	

	
	
	public void initializeGame(InputMap input) { //initialisation du jeu et récupération des éléments du layout
		System.out.println("nb d'agents  "+agent_list.size());
		
		int cpt=1;
		 for( Agent f: input.getStart_snakes()) { 	//récuperation des agents snakes		 
			 Snake s= fabrique.createAgent(f.getPositions(), f.getLastAction(), f.getColorSnake(), f.isInvincible(), f.isSick());
			 if(cpt==1)s.setStrategy(stratJ1); 
			 else if(cpt==2)s.setStrategy(stratJ2);
			 agent_list.add(s);	
			 ++cpt;
			 
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
	
	

	public void eatItem(Agent ag,ArrayList<FeaturesItem> items_list) {  //changer en eatItzm aves tous les items
		
		for ( int i=0; i<items_list.size();++i) {
			Position posItem = new Position(items_list.get(i).getX(),items_list.get(i).getY());
			System.out.println("position item "+items_list.get(i).getX() +" "+items_list.get(i).getY());
			if(posItem.getX() ==ag.getPositions().get(0).getX() && posItem.getY() == ag.getPositions().get(0).getY() && !ag.isSick()) { 
				
				switch(items_list.get(i).getItemType()) {  //traitement des différents types d'items mangé par le snake
				case APPLE:				
						Random r = new Random();
						while(!itemDeplacable(items_list.get(i))) {
							items_list.get(i).setX(r.nextInt(im.getSizeX()));
							items_list.get(i).setY(r.nextInt(im.getSizeY()));
						}
						ag.getPositions().add(ag.getLastPosition());
						ag.gotOnlyHead();		
						
						Random pItem = new Random();
						if(pItem.nextInt(probaItem)==0)  randomItemProbability();
					break;
				case BOX:
					int[] values = {1,2};
			        int randIndex = new Random().nextInt(2);
			        int randItem= values[randIndex];
			        
			        if(randItem==1) {
						items_list.remove(items_list.get(i));
						ag.setInvincible(true);
						tempsInvincible=0;
			        }
			        else if(randItem==2) {
						items_list.remove(items_list.get(i));
						ag.setSick(true);
						tempsMalade=0;
			        }			        
					break;
				case INVINCIBILITY_BALL:
					items_list.remove(items_list.get(i));
					ag.setInvincible(true);
					tempsInvincible=0;
					break;
				case SICK_BALL:
					items_list.remove(items_list.get(i));
					ag.setSick(true);
					tempsMalade=0;
					break;
				default:
									
				}	
				
			}
		}	
		if(ag.isLegalMove(ag.getStrategy().chooseAction()))  ag.getPositions().remove(ag.getPositions().size()-1);
	}
	
	
	public void randomItemProbability() {
		int[] values = {1,2,3};
        int randIndex = new Random().nextInt(3);
        int randItem= values[randIndex];
        Random randPos=new Random();
        if(randItem==1) {
			FeaturesItem SickBall = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.SICK_BALL);
			while(!itemDeplacable(SickBall)) {
				SickBall = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.SICK_BALL);
			}
			items_list.add(SickBall);
        }
        else if(randItem==2) {
        	FeaturesItem InvincibleBall = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.INVINCIBILITY_BALL);
        	while(!itemDeplacable(InvincibleBall)) {
            	InvincibleBall = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.INVINCIBILITY_BALL);

        	}
			items_list.add(InvincibleBall);
        }
        else if(randItem==3) {
        	FeaturesItem Box = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.BOX);
        	while(!itemDeplacable(Box)) {
        		Box = new FeaturesItem(randPos.nextInt(im.getSizeX()),randPos.nextInt(im.getSizeY()),ItemType.BOX);
        	}
        	items_list.add(Box);
        }
	}
	

	public boolean itemDeplacable(FeaturesItem item) {
		for (Agent ag : agent_list) {
			for (Position p: ag.getPositions()) {
				if(p.getX()==item.getX() &&p.getY()==item.getY()) return false;
			}		
		}
		if(im.get_walls()[item.getX()][item.getY()]) return false;
		return true;
	}
	
	@Override
	public void setStrategie(DeplacementStrategie s1,DeplacementStrategie s2) {
		this.stratJ1=s1;
		this.stratJ2=s2;
		
	}



}
