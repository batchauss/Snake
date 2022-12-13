package strategyDeplacement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import utils.AgentAction;


//stratégie pour un ou deux joueurs 
public class DeplacementInteractif implements DeplacementStrategie{
	private AgentAction action=AgentAction.MOVE_UP;
	
	public DeplacementInteractif(int numJoueur,JFrame f) {
		
		KeyListener key = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
	
				switch(numJoueur) {
				case 1:
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
					break;
				case 2:
					switch (e.getKeyCode())
					{
						case KeyEvent.VK_D:
							action=AgentAction.MOVE_RIGHT;
							break;
						case KeyEvent.VK_Q:
							action=AgentAction.MOVE_LEFT;
							break;
						case KeyEvent.VK_Z:
							action=AgentAction.MOVE_UP;
							break;
						case KeyEvent.VK_S:
							action=AgentAction.MOVE_DOWN;
							break;
						default:
							//action=AgentAction.MOVE_UP;
							break;
					}
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
