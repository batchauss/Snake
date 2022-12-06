package agent;

import java.util.ArrayList;

import model.InputMap;
import strategyDeplacement.DeplacementStrategie;
import utils.*;

public class Snake implements Agent{
	
	private Position pos;
	private DeplacementStrategie strategie;  //strategie de deplacement (random, manuel,...)
	
	private ArrayList<Position> positions;
	
	private AgentAction lastAction;
	private boolean onlyHead; //détermine si le snake possède un corps ou non 
	
	private ColorSnake colorSnake;
	private boolean isInvincible;
	private boolean isSick;
	private boolean isEliminated;
	private int score;

	
	public Snake (ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
		this.positions = positions;
		this.colorSnake = colorSnake;
		this.lastAction = lastAction;		
		this.isInvincible = isInvincible;		
		this.isSick = isSick;
		this.isEliminated=false;
		this.score=0;
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
	public DeplacementStrategie getStrategy(){
		return this.strategie;
		
	}
	
	
	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void augmenteScore() {
		++score;		
	}
	
	
	 @Override
	 public void moveAgent(InputMap im) {
		AgentAction action =strategie.chooseAction();
		
		
	
		
		if(isLegalMove(action) ){
			switch (action) { 	
				case MOVE_UP:
					setPosition(pos.getX(),pos.getY()-1);				
					setLastAction(AgentAction.MOVE_UP);					
					break;
				case MOVE_DOWN:
					setPosition(pos.getX(),pos.getY()+1);
					setLastAction(AgentAction.MOVE_DOWN);
					break;
				case MOVE_LEFT:
					setPosition(pos.getX()-1,pos.getY());
					setLastAction(AgentAction.MOVE_LEFT);
					break;
				case MOVE_RIGHT:
					setPosition(pos.getX()+1,pos.getY());
					setLastAction(AgentAction.MOVE_RIGHT);
					break;

			}
			traverseCarte( im);
			positions.add(0,pos);
			isLegalPos(action, im.get_walls());
			toucheSonCorps();
		}
	 }
		
	
	 
	public void traverseCarte(InputMap im) {
		if(pos.getY()<0) {
			setPosition(pos.getX(), pos.getY()+im.getSizeY());
		}
		if(pos.getY()>=im.getSizeY()) {
			setPosition(pos.getX(), pos.getY()-im.getSizeY());
		}

		if(pos.getX()<0) {
			setPosition(pos.getX()+im.getSizeX(), pos.getY());
		}
		if(pos.getX()>=im.getSizeX()) {
			setPosition(pos.getX()-im.getSizeX(), pos.getY());
		}
		
	}
	
	@Override
	public boolean isLegalMove(AgentAction action) { //gestion du snake qui ne peut pas revenir en arriere
		if(!onlyHead)
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
		if(!isInvincible) {
			
			if(walls[pos.getX()][pos.getY()]) {
				isEliminated=true;
				return false;
			}
		}
		return true;
	}
	
	
	public void toucheSonCorps() {
		for(int i=1; i<positions.size();++i) {
			if( pos.getX()==positions.get(i).getX() && pos.getY()==positions.get(i).getY() ) {
				isEliminated=true;
				return;
			}
		}
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

	@Override
	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	@Override
	public boolean isSick() {
		return isSick;
	}
	
	@Override
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
			return new Position(positions.get(positions.size()-1).getX(),positions.get(positions.size()-1).getY());			
		case MOVE_DOWN:
			return new Position(positions.get(positions.size()-1).getX(),positions.get(positions.size()-1).getY());	
		case MOVE_LEFT:
			return new Position(positions.get(positions.size()-1).getX(),positions.get(positions.size()-1).getY());	
		case MOVE_RIGHT:
			return new Position(positions.get(positions.size()-1).getX(),positions.get(positions.size()-1).getY());	
		default:
			return pos;
}
		
	}

	@Override
	public void InteractionEntreAgents( ArrayList<Agent> agent_list) {  
		
		for (Agent ag : agent_list) {
			if(!ag.equals(this)) {
				for(Position p : ag.getPositions()) {
					
					if(this.positions.get(0).getX()==p.getX() &&this.positions.get(0).getY()==p.getY() && this.positions.size()==ag.getPositions().size() ) { // les 2 agents se rencontrent et sont de meme taille = elimination des 2
						if(this.isInvincible())  ag.setEliminated(true);
						else if (ag.isInvincible()) this.isEliminated=true;
						else {
							this.isEliminated=true;
							ag.setEliminated(true);
						}
					}
					if(this.positions.get(0).getX()==p.getX() &&this.positions.get(0).getY()==p.getY() && this.positions.size()<ag.getPositions().size() ) { // les 2 agents se rencontrent et le 1er est plus petit = 1er eliminé
						if(this.isInvincible())  ag.setEliminated(true);
						else this.isEliminated=true;
					}
					if(this.positions.get(0).getX()==p.getX() &&this.positions.get(0).getY()==p.getY() && this.positions.size()>ag.getPositions().size() ) { // les 2 agents se rencontrent et le 2e est plus petit = 2eme eliminé
						if(ag.isInvincible())  this.setEliminated(true);
						else ag.setEliminated(true);
					}
				}
				
			}
		}
		
	}

	@Override
	public void gotOnlyHead() {
		onlyHead=false;
	}


	
	
}
