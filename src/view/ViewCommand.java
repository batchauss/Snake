package view;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import observables.*;

public class ViewCommand implements Observateur{
	
	//variable de mise a jour des tours
	public int turn_update;
	//controleur de ViewCommand
	public ControllerSimpleGame absC ;
	JLabel texte = new JLabel();
	
	public ViewCommand (Observable obs, ControllerSimpleGame a) {
		obs.enregistrerObservateur(this);
		this.absC=a;
	}
	
	 JFrame jFrame =new JFrame();
	
	 public void commande() {
		
		 
		JPanel panel_button = new JPanel();
		JPanel panel_slide_text = new JPanel();
		 
		GridLayout layout_global = new GridLayout(2,1); //fenetre divisée en deux partie
		GridLayout layout_button = new GridLayout(1,4);	//haut de la fenetre	
		GridLayout layout_commandes = new GridLayout(1,2); //bas de la fenetre
		
		//création des boutons avec icones 
		Icon restartIcon = new ImageIcon("icons/icon_restart.png");
		JButton restartButton = new JButton(restartIcon);
		
		Icon playIcon = new ImageIcon("icons/icon_play.png");
		JButton playButton = new JButton(playIcon);

		Icon stepIcon = new ImageIcon("icons/icon_step.png");
		JButton stepButton = new JButton(stepIcon);

		Icon pauseIcon = new ImageIcon("icons/icon_pause.png");
		JButton pauseButton = new JButton(pauseIcon);
		
		
		//gestion des boutons
		panel_button.setLayout(layout_button);		    
		panel_button.add(restartButton);
		panel_button.add(playButton);
		panel_button.add(stepButton);
		panel_button.add(pauseButton);
		
		//boutons activés/désactivés au démarrage du jeu
		restartButton.setEnabled(false);
		pauseButton.setEnabled(false);
		
		//gestion du slider
		JSlider slider = new JSlider(0, 10, 1);
		slider.setPaintTrack(true); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
		slider.setMajorTickSpacing(1); 
        slider.setMinorTickSpacing(1); 
		panel_slide_text.setLayout(layout_commandes);
		panel_slide_text.add(slider);
		
		
		//ajout d'écouteurs sur les différents composants	
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				absC.restart();	
				restartButton.setEnabled(false);
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
			}});
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				absC.play();
				restartButton.setEnabled(true);
				pauseButton.setEnabled(true);
				playButton.setEnabled(false);
			}});
		
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				absC.step();
			}});
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				absC.pause();	
				playButton.setEnabled(true);
				pauseButton.setEnabled(false);
			}});
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				absC.setSpeed(slider.getValue());			
			}});
		
		
		
    	texte.setText("Turn: "+String.valueOf(turn_update));
    	texte.setHorizontalAlignment(JLabel.CENTER);
        texte.setVerticalAlignment(JLabel.CENTER);
        panel_slide_text.add(texte);
		
		jFrame.setLayout(layout_global);
		jFrame.add(panel_button);
		jFrame.add(panel_slide_text);
		jFrame.setSize(500, 350);
		jFrame.setVisible(true);
    }

	@Override
	public void actualiser(int turn) {
		turn_update=turn;
		texte.setText("Turn: "+ turn_update);
		System.out.println( "Nouveau tour( interface graphique ViewCommand  ):" + turn);
		
		
	}
	

}
