package controller;
import model.*;

public abstract class AbstractController {
	Game g;
	
	public AbstractController(Game g) {
		this.g=g;
	}
	
	public void start() {
		g.init();
		g.launch();
		g.pause();
		
	}
	
	public void restart() {	/// a faire
		g.init();
		g.launch();
		g.pause();
		
	}
	
	/*public void restart() {
		g.restart();
	} */
	
	public void step() {
		g.step();
	}
	
	public void play() {
		g.launch();
	}

	public void pause() {
		g.pause();
	}
	
	public void setSpeed(double speed) {
		g.setTime((long) (g.getTime() /speed));
	}
}
