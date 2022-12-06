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
	private int turn_update;
	private JLabel scores= new JLabel();
	
	//controleur de ViewCommand
	private AbstractController absC ;
	private JLabel texte = new JLabel();
	
	private JFrame jFrame =new JFrame();
	
	private Etat etat = new EtatStart(this); // trois états possibles ici : start(restart) / play / pause
	
	public ViewCommand (Observable obs, AbstractController a) {
		obs.enregistrerObservateur(this);
		this.absC=a;
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void setEtat (Etat e) {
		etat=e;
	}
	
	public JFrame getFrame() {
		return jFrame;
	}
	
	public void commande() {
				 
		JPanel panel_button = new JPanel();
		JPanel panel_slide = new JPanel();
		JPanel panel_slide_text = new JPanel();
		JPanel panel_score = new JPanel();
		JPanel panel_touches = new JPanel();
		 
		GridLayout layout_global = new GridLayout(4,1); //fenetre divisée en deux partie
		GridLayout layout_button = new GridLayout(1,4);	//haut de la fenetre	
		GridLayout layout_commandes = new GridLayout(1,2); //bas de la fenetre
		GridLayout layout_slide = new GridLayout(2,1); //
		GridLayout layout_score = new GridLayout(1,1); //bas de la fenetre
		GridLayout layout_touches = new GridLayout(1,2);
		
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
		restartButton.setEnabled(true);
		pauseButton.setEnabled(false);
		
		//gestion du slider
		JSlider slider = new JSlider(1, 10, 1);
		JLabel texte_au_dessus_slide= new JLabel();
		texte_au_dessus_slide.setText("Vitesse du jeu");
    	texte_au_dessus_slide.setHorizontalAlignment(JLabel.CENTER);
        texte_au_dessus_slide.setVerticalAlignment(JLabel.CENTER);
		panel_slide.setLayout(layout_slide);
		
		
		slider.setPaintTrack(true); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
		slider.setMajorTickSpacing(1); 
        slider.setMinorTickSpacing(1); 
		panel_slide_text.setLayout(layout_commandes);

		panel_slide.add(texte_au_dessus_slide);
		panel_slide.add(slider);	
		panel_slide_text.add(panel_slide);
		
		
		//gestion du score
		scores.setText("Start  ou Step pour commencer la partie ");
		scores.setHorizontalAlignment(JLabel.CENTER);
		scores.setVerticalAlignment(JLabel.CENTER);
		panel_score.setLayout(layout_score);	
		panel_score.add(scores);
		
		
		//affichage des commandes
		ImageIcon touches = new ImageIcon("images/touches.png");
		JLabel t=new JLabel(touches);
		t.setHorizontalAlignment(JLabel.CENTER);
		t.setVerticalAlignment(JLabel.CENTER);
		JLabel texte_touches= new JLabel();
		texte_touches.setText("Commandes :");
		texte_touches.setHorizontalAlignment(JLabel.CENTER);
		texte_touches.setVerticalAlignment(JLabel.CENTER);
		panel_touches.setLayout(layout_touches);
		panel_touches.add(texte_touches);
		panel_touches.add(t);
		
		
		
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
		
		slider.addChangeListener( new ChangeListener() {
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
		jFrame.add(panel_score);
		jFrame.add(panel_touches);

		jFrame.setSize(500, 350);
		jFrame.setVisible(true);
    }

	@Override
	public void actualiser(int turn, ArrayList<Agent> agent_list,ArrayList<FeaturesItem>items_list) {
		texte.setText("Turn: "+ turn);
		if(agent_list.size()==1) scores.setText("Score J1: "+agent_list.get(0).getScore());
		else if(agent_list.size()==2) {
			scores.setText("Scores J1: "+agent_list.get(0).getScore() +"   J2: "+agent_list.get(1).getScore() );
		}
		else if(agent_list.size()==0) scores.setText("La partie est terminée !"); 
	}
}


