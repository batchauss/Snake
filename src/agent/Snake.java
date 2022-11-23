package agent;

import java.util.ArrayList;

import model.InputMap;
import strategyDeplacement.DeplacementStrategie;
import utils.*;

public class Snake implements Agent{
	
	private Position pos;
	private DeplacementStrategie strategie;  //strategie de deplacement (random, manuel,...)
	
	ArrayList<Position> positions;
	
	private AgentAction lastAction;
	private boolean onlyHead; //détermine si le snake possède un corps ou non 
	
	ColorSnake colorSnake;
	boolean isInvincible;
	boolean isSick;
	boolean isEliminated;

	
	public Snake (ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
		this.positions = positions;
		this.colorSnake = colorSnake;
		this.lastAction = lastAction;		
		this.isInvincible = isInvincible;		
		this.isSick = isSick;
		this.isEliminated=false;
		pos=positions.get(0);
		if(positions.size()==1) onlyHead=true;
		else onlyHead=false;

	}
	
	public void setPosition( int x, int y) {
		this.pos = new Position(x,y);
	}
	
	public void setEliminated(boolean elimine) {
		isEliminated=elimine;
	}
	
	public void setStrategy(DeplacementStrategie strat) {
		this.strategie=strat;
	}
	
	 @Override
	 public void moveAgent(InputMap im) {
		AgentAction action =strategie.chooseAction();
		
		if(isLegalMove(action) ){
			switch (action) { 	
				case MOVE_UP:
					setPosition(pos.getX(),pos.getY()-1);
					isLegalPos(action, im.get_walls());
					break;
				case MOVE_DOWN:
					setPosition(pos.getX(),pos.getY()+1);
					isLegalPos(action, im.get_walls());
					break;
				case MOVE_LEFT:
					setPosition(pos.getX()-1,pos.getY());
					isLegalPos(action, im.get_walls());
					break;
				case MOVE_RIGHT:
					setPosition(pos.getX()+1,pos.getY());
					isLegalPos(action, im.get_walls());
					break;
				//faire cas ou l'agent sort du plateau
			}	
		positions.add(0,pos);
		positions.remove(positions.size()-1);
		setLastAction(action);
		}
	}
	
	public boolean isLegalMove(AgentAction action) { //gestion du snake qui ne peut pas revenir en arriere
		if(onlyHead)
			switch (action) { 
				case MOVE_UP:
					if(getLastAction()==AgentAction.MOVE_DOWN) return false;
					else return true;			
				case MOVE_DOWN:
					if(getLastAction()==AgentAction.MOVE_UP) return false;
					else return true;
				case MOVE_LEFT:
					if(getLastAction()==AgentAction.MOVE_RIGHT) return false;
					else return true;
				case MOVE_RIGHT:
					if(getLastAction()==AgentAction.MOVE_LEFT) return false;
					else return true;
		}
		return true;
	}
	
	public boolean isLegalPos(AgentAction action,boolean walls[][]) { //gestion des murs
		switch (action) { 
		case MOVE_UP:
			if(walls[pos.getX()][pos.getY()-1]) {
				isEliminated=true;
				return false;
			}
			else return true;			
		case MOVE_DOWN:
			if(walls[pos.getX()][pos.getY()+1]){
				isEliminated=true;
				return false;
			}
			else return true;
		case MOVE_LEFT:
			if(walls[pos.getX()-1][pos.getY()]) {
				isEliminated=true;
				return false;
			}
			else return true;
		case MOVE_RIGHT:
			if(walls[pos.getX()+1][pos.getY()]) {
				isEliminated=true;
				return false;
			}
			else return true;
		}
		return true;
	}
	
	
	
	
	public boolean elimination() {
		return isEliminated;
			
	}
		
	public ArrayList<Position> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Position> positions) {
		this.positions = positions;
	}

	@Override
	public ColorSnake getColorSnake() {
		return colorSnake;
	}

	public void setColorSnake(ColorSnake colorSnake) {
		this.colorSnake = colorSnake;
	}

	@Override
	public boolean isInvincible() {
		return isInvincible;
	}


	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	@Override
	public boolean isSick() {
		return isSick;
	}

	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}

	@Override
	public AgentAction getLastAction() {
		return lastAction;
	}


	public void setLastAction(AgentAction lastAction) {
		this.lastAction = lastAction;
	}

	@Override
	public Position getLastPosition() {
		switch (lastAction) { 
		case MOVE_UP:
			return new Position(pos.getX(),pos.getY()+1);			
		case MOVE_DOWN:
			return new Position(pos.getX(),pos.getY()-1);	
		case MOVE_LEFT:
			return new Position(pos.getX()+1,pos.getY());	
		case MOVE_RIGHT:
			return new Position(pos.getX()-1,pos.getY());	
		default:
			return pos;
}
		
	}

	@Override
	public void InteractionEntreAgents( Agent agent2) {  ///je sais pas si ca marche a revoir

		for (Position pos : agent2.getPositions()) {
			if(this.positions.get(0)==pos && this.positions.size()==agent2.getPositions().size() ) { // les 2 agents se rencontrent et sont de meme taille = elimination des 2
				this.isEliminated=true;
				agent2.setEliminated(true);
			}
			if(this.positions.get(0)==pos && this.positions.size()<agent2.getPositions().size() ) { // les 2 agents se rencontrent et le 1er est plus petit = 1er eliminé
				this.isEliminated=true;
			}
			if(this.positions.get(0)==pos && this.positions.size()>agent2.getPositions().size() ) { // les 2 agents se rencontrent et le 2e est plus petit = 2eme eliminé
				agent2.setEliminated(true);
			}
			
		}
		
	}

	@Override
	public void eatApple(ArrayList<FeaturesItem> items_list) {  //changer en eatItzm aves tous les items
		
		for ( int i=0; i<items_list.size();++i) {
			Position posPomme = new Position(items_list.get(i).getX(),items_list.get(i).getY());
			System.out.println("position pomme "+items_list.get(i).getX() +" "+items_list.get(i).getY());
			if(posPomme.getX() ==this.positions.get(0).getX() &&posPomme.getY() ==this.positions.get(0).getY()) { 
				positions.add(getLastPosition());
				items_list.remove(items_list.get(i));
				
			}
		}
	}


	

	
}
