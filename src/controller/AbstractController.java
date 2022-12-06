package controller;
import model.*;

public abstract class AbstractController {
	protected Game g;
	
	public AbstractController(Game g) {
		this.g=g;
	}
	
	public void start() {
		g.init();
		g.launch();
		g.pause();
		
	}
	
	public abstract void restart();
	
	
	
	
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
		g.setTime(1000);
		g.setTime((long) (g.getTime() /speed));
	}
}
