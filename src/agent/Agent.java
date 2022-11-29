package agent;

import java.util.ArrayList;

import model.InputMap;
import strategyDeplacement.DeplacementStrategie;
import utils.AgentAction;
import utils.ColorSnake;
import utils.FeaturesItem;
import utils.Position;

public interface Agent {
	public ArrayList<Position> getPositions();
	public void setEliminated(boolean elimine);
	public void setStrategy(DeplacementStrategie strat);

	public void moveAgent(InputMap im);
	public boolean isLegalPos(AgentAction action,boolean walls[][]);

	public ColorSnake getColorSnake();
	public boolean isInvincible();
	public boolean isSick();
	public AgentAction getLastAction();	
	public Position getLastPosition();
	
	public boolean elimination();
	
	public void InteractionEntreAgents( Agent agent2);
	public void eatItem(ArrayList<FeaturesItem> items_list);
	
	
}
