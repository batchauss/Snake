package view;
import javax.swing.*;
import observables.*;
import java.awt.*;
public class ViewSimpleGame implements Observateur {
	
	public ViewSimpleGame (Observable obs) {
		obs.enregistrerObservateur(this);
	}

	protected int turn_update;
    JFrame jFrame =new JFrame();
    JLabel texte = new JLabel();
    JPanel snakegame = new PanelSnakeGame(500, 500, null, null, null);
    
    public void viewInterface() {
    	

    	texte.setHorizontalAlignment(JLabel.CENTER);
        texte.setVerticalAlignment(JLabel.CENTER);
        snakegame.setVisible(true);
    	texte.setVisible(true);
    	
    	jFrame.add(texte);
    	jFrame.setTitle("Game");
    	//jFrame.add(snakegame);
    	
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
	public void actualiser(int turn) {
		System.out.println( "Nouveau tour (interface graphique ViewSimpleGame) :" + turn);
		turn_update=turn;
		texte.setText("Turn: "+ turn_update);
	}
	


}
