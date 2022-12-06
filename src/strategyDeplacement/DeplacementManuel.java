package strategyDeplacement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import utils.AgentAction;

public class DeplacementManuel implements DeplacementStrategie{
	private AgentAction action=AgentAction.MOVE_UP;
	
	public DeplacementManuel(JFrame f) {
		
		KeyListener key = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

					switch (e.getKeyCode())
					{
						case KeyEvent.VK_RIGHT:
							action=AgentAction.MOVE_RIGHT;
							break;
						case KeyEvent.VK_LEFT:
							action=AgentAction.MOVE_LEFT;
							break;
						case KeyEvent.VK_UP:
							action=AgentAction.MOVE_UP;
							break;
						case KeyEvent.VK_DOWN:
							action=AgentAction.MOVE_DOWN;
							break;
						default:
							//action=AgentAction.MOVE_UP;
							break;
					}
									
				}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
			
		};
		f.addKeyListener(key);
	}
	@Override
	
	public AgentAction chooseAction() {
		
		return action;
	}

	
}
