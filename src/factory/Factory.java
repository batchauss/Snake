package factory;
import java.util.ArrayList;

import agent.Snake;
import utils.AgentAction;
import utils.ColorSnake;
import utils.Position;

public interface Factory {

	public Snake createAgent(ArrayList<Position> positions, AgentAction lastAction, ColorSnake colorSnake, boolean isInvincible, boolean isSick);
}
