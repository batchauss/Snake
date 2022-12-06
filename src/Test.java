
import controller.ControllerSnakeGame;
import model.*;


public class Test {
	
	public static void main(String[] args) throws Exception
	{
		InputMap im = new InputMap( "./layouts/smallNoWall.lay");
		Game g = new SnakeGame(5000,im);
		
		ControllerSnakeGame csg = new ControllerSnakeGame(g,im);
		csg.start();
		
	}
	

}
