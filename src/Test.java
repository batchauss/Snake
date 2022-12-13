
import controller.ControllerSnakeGame;
import model.*;


public class Test {
	
	public static void main(String[] args) throws Exception
	{
		InputMap im = new InputMap( "./layouts/arenaNoWall.lay");
		Game g = new SnakeGame(5000,im);		
		new ControllerSnakeGame(g,"./layouts/arenaNoWall.lay");
		
	}
	

}
