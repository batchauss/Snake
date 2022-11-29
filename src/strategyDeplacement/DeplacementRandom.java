package strategyDeplacement;

import java.util.Random;
import utils.AgentAction;

public class DeplacementRandom implements DeplacementStrategie{

	//un déplacement aléatoire parmis les 4 possibilités de déplacements
	public AgentAction chooseAction() {
		AgentAction[] values = AgentAction.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];		
	}
}
