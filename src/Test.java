import controller.ControllerSimpleGame;
import model.*;

public class Test {
	
	public static void main(String[] args) throws Exception
	{
		Game g = new SimpleGame(10);
		/*ViewSimpleGame vg = new ViewSimpleGame(g);
		ViewCommand com = new ViewCommand(g);
		vg.viewInterface();
		com.commande();
		g.init();
		g.step();
		g.run();  */
		
		ControllerSimpleGame csg = new ControllerSimpleGame(g);
		csg.restart();
		
	}
	

}
