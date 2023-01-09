
import controller.ControllerSnakeGame;
import model.*;


public class Test {
	
	public static void main(String[] args) throws Exception
	{
		InputMap im = new InputMap( "./layouts/arenaNoWall.lay"); //le chemin ici n'as pas d'importance car il est redéfini dans le ControllerSnakeGame
		Game g = new SnakeGame(5000,im);		//5000 tours
		new ControllerSnakeGame(g,"./layouts/arenaNoWall.lay");
	}
}
