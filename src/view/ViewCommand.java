package view;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import agent.Agent;
import controller.*;
import etat.Etat;
import etat.EtatStart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import observables.*;
import utils.FeaturesItem;

public class ViewCommand implements Observateur{
	
	//variable de mise a jour des tours
	public int turn_update;
	//controleur de ViewCommand
	public AbstractController absC ;
	JLabel texte = new JLabel();
	JFrame jFrame =new JFrame();
	
	Etat etat = new EtatStart(this); // trois états possibles ici : start(restart) / play / pause
	
	public ViewCommand (Observable obs, AbstractController a) {
		obs.enregistrerObservateur(this);
		this.absC=a;
	}
	
	
	public void setEtat (Etat e) {
		etat=e;
	}
	
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
		//restartButton.setEnabled(false);
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
				etat.restart(absC, restartButton, playButton, stepButton, pauseButton);
			}});
		
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				etat.play(absC, restartButton, playButton, stepButton, pauseButton);
			}});
		
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				etat.step(absC);
			}});
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				etat.pause(absC, restartButton, playButton, stepButton, pauseButton);
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
	public void actualiser(int turn, ArrayList<Agent> agent_list,ArrayList<FeaturesItem>items_list) {
		turn_update=turn;
		texte.setText("Turn: "+ turn_update);
		//System.out.println( "Nouveau tour( interface graphique ViewCommand  ):" + turn);			
	}
}


