package etat;

import javax.swing.JButton;
import controller.AbstractController;
import view.ViewCommand;

//état de l'interface au démarrage ou lorsqu'on restart le jeu
public class EtatStart implements Etat {
	
	ViewCommand viewcommand;
	
	public EtatStart(ViewCommand view) {
		this.viewcommand=view;
	}

	@Override
	public void restart(AbstractController abs ,JButton res, JButton pla, JButton ste, JButton pau) {
		//le bouton restart est désactivé		
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
		//le bouton pause est désactivé	
	}

	@Override
	public void step(AbstractController abs) {
		abs.step();	
	}

}
