
import controller.ControllerSnakeGame;
import model.*;


public class Test {
	
	public static void main(String[] args) throws Exception
	{
		InputMap im = new InputMap( "./layouts/aloneNoWall.lay");
		Game g = new SnakeGame(50,im);
		
		ControllerSnakeGame csg = new ControllerSnakeGame(g,im);
		csg.start();
		
	}
	

}
