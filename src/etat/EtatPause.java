package etat;

import javax.swing.JButton;
import controller.AbstractController;
import view.ViewCommand;

//état de l'interface lorsque le jeu est en pause
public class EtatPause implements Etat{
	
	ViewCommand viewcommand;
	
	public EtatPause(ViewCommand view) {
		this.viewcommand=view;
	}

	@Override
	public void restart(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau) {
		abs.restart();	
		res.setEnabled(false);
		pla.setEnabled(true);
		ste.setEnabled(true);
		pau.setEnabled(false);
		viewcommand.setEtat(new EtatStart(viewcommand));		
	}

	@Override
	public void play(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau) {
		abs.play();
		res.setEnabled(true);		
		pla.setEnabled(false);
		ste.setEnabled(false);
		pau.setEnabled(true);
		viewcommand.setEtat(new EtatPlay(viewcommand));	
	}

	@Override
	public void pause(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau) {
		// le bouton pause est désactivé dans cet état		
	}

	@Override
	public void step(AbstractController abs) {
		abs.step();	
	}

}
