package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.Navigation;
import model.Board;
import model.Coordinates;

/**
 * Draw board positions obstacle, start and goal state on UI
 * Obstacle shape is as per user choice - Polygons and circle 
 * @author apoorva
 *
 */
public class DrawBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image robotImage;
	Board board = Board.getBoardInstance();
	Graphics2D g2d; 

	@Override
	public void paint(Graphics g){

		super.paintComponent( g );
		g2d = (Graphics2D)g.create();
		loadImage();
		if(board.isFileOpen()){
			g2d.drawImage(robotImage, board.getStart().getX(), board.getStart().getY(), this);
			g2d.drawString("Goal", board.getGoal().getX(), board.getGoal().getY());
			drawObstacle(g2d);
			Navigation nv= new Navigation(this);
			nv.SearchPath(board);
		}else{		
			g2d.drawImage(robotImage, 0, 400, this);
		}
		g2d.dispose();

		repaint();
	} 


	/**
	 * Draws obstacle
	 * @param g2d
	 */
	private void drawObstacle(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Iterator<?> it = board.getObstacleMap().entrySet().iterator();

		while(it.hasNext()){
			Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
			List<Coordinates> coordinateList = pair.getValue();
			int sides =coordinateList.size();
			for(int i=0;i<sides;i++){
				if(i==sides-1)
					g2d.drawLine(coordinateList.get(i).getX(), coordinateList.get(i).getY(), coordinateList.get(0).getX(), coordinateList.get(0).getY());
				else
					g2d.drawLine(coordinateList.get(i).getX(), coordinateList.get(i).getY(), coordinateList.get(i+1).getX(), coordinateList.get(i+1).getY());


			}
		}
		
	}

	private Image loadImage(){
		ImageIcon robotIcon = new ImageIcon(this.getClass().getResource("/images/Robot_opt.png"));
		return robotImage = robotIcon.getImage(); 
	}
	
	public void drawLine(Coordinates c1,Coordinates c2){
		g2d.drawLine(c1.getX(),c1.getY(), c2.getX(),c2.getY());
		repaint();
	}
}
