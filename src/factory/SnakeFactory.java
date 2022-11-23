package factory;
import java.util.ArrayList;

import agent.Snake;
import utils.AgentAction;
import utils.ColorSnake;
import utils.Position;


public class SnakeFactory implements Factory{

	public SnakeFactory() {		
	}

	@Override
	public Snake createAgent(ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick) {
		return new Snake(positions,lastAction,colorSnake,isInvincible,isSick);
		
	}
}
