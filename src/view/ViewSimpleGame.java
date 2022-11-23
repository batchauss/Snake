package view;
import javax.swing.*;

import agent.Agent;
import observables.*;
import utils.FeaturesItem;

import java.awt.*;
import java.util.ArrayList;
public class ViewSimpleGame implements Observateur {
	
	public ViewSimpleGame (Observable obs) {
		obs.enregistrerObservateur(this);
	}

	protected int turn_update;
    JFrame jFrame =new JFrame();
    JLabel texte = new JLabel();    
    public void viewInterface() {
    	

    	texte.setHorizontalAlignment(JLabel.CENTER);
        texte.setVerticalAlignment(JLabel.CENTER);
    	texte.setVisible(true);
    	
    	jFrame.add(texte);
    	jFrame.setTitle("Game");

    	jFrame.setSize(new Dimension(500,500));
    	Dimension windowSize = jFrame.getSize();
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	Point centerPoint = ge.getCenterPoint();
    	int dx = centerPoint.x - windowSize.width / 2 ;
    	int dy = centerPoint.y - windowSize.height / 2 - 350;
    	jFrame.setLocation(dx, dy);
    	jFrame.setVisible(true);
    }

   
	@Override
	public void actualiser(int turn,ArrayList<Agent> agent_list,ArrayList<FeaturesItem>items_list) {
		System.out.println( "Nouveau tour (interface graphique ViewSimpleGame) :" + turn);
		turn_update=turn;
		texte.setText("Turn: "+ turn_update);
	}
	


}
