package etat;

import javax.swing.JButton;

import controller.AbstractController;

public interface Etat {
	public void restart(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau);
	public void play(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau);
	public void pause(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau);
	public void step(AbstractController abs);
}
