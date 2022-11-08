import controller.ControllerSnakeGame;
import model.*;

public class Test {
	
	public static void main(String[] args) throws Exception
	{
		Game g = new SnakeGame(10);
		/*ViewSimpleGame vg = new ViewSimpleGame(g);
		ViewCommand com = new ViewCommand(g);
		vg.viewInterface();
		com.commande();
		g.init();
		g.step();
		g.run();  */
		
		InputMap im = new InputMap( "./layouts/alone.lay");
		
		ControllerSnakeGame csg = new ControllerSnakeGame(g,im);
		csg.restart();
		
	}
	

}
