package agent;

import java.util.ArrayList;

import model.InputMap;
import strategyDeplacement.DeplacementStrategie;
import utils.AgentAction;
import utils.ColorSnake;
import utils.Position;

public interface Agent {
	public ArrayList<Position> getPositions();
	public void setEliminated(boolean elimine);
	public void setStrategy(DeplacementStrategie strat);
	public DeplacementStrategie getStrategy();

	public void moveAgent(InputMap im);
	public boolean isLegalPos(AgentAction action,boolean walls[][]);

	public ColorSnake getColorSnake();
	
	public boolean isInvincible();
	public boolean isSick();
	public void setSick(boolean isSick);
	public void setInvincible(boolean isInvincible);
	
	public void gotOnlyHead();
	
	public AgentAction getLastAction();	
	public Position getLastPosition();
	
	public boolean elimination();
	
	public boolean isLegalMove(AgentAction action);
	
	public void InteractionEntreAgents( ArrayList<Agent> agent_list);
	
	public int getScore();
	public void augmenteScore();
	
	
}
