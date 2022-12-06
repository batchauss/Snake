package etat;

import javax.swing.JButton;
import controller.AbstractController;
import view.ViewCommand;


//état de l'interface lorsque le jeu est en cours 
public class EtatPlay implements Etat{
	
	private ViewCommand viewcommand;
	
	public EtatPlay(ViewCommand view) {
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
		// le bouton play est désactivé	dans cet état
	}

	@Override
	public void pause(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau) {
		abs.pause();
		res.setEnabled(true);
		pla.setEnabled(true);
		ste.setEnabled(true);
		pau.setEnabled(false);
		viewcommand.setEtat(new EtatPause(viewcommand));		
	}

	@Override
	public void step(AbstractController abs) {
		//le bouton step est désactivé dans cet état
	}

}
