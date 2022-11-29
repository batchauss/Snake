package observables;

import java.util.ArrayList;


import agent.Agent;
import utils.FeaturesItem;

public interface Observateur {
	public void actualiser(int turn,ArrayList<Agent> agent_list,ArrayList<FeaturesItem> items_list);
}
