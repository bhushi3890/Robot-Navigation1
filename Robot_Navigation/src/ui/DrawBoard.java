package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Arc2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Board;
import model.Coordinates;
import util.ShapeUtils;
import application.Navigation;

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
	public static Arc2D vision;
	Graphics2D g2d; 
    Graphics gref;
	@Override
	public void paint(Graphics g){

		super.paintComponent( g );
		g2d = (Graphics2D)g.create();
		gref=g;
		loadImage();
		if(board.isFileOpen()){
			g2d.drawImage(robotImage, board.getStart().getX(), board.getStart().getY(), this);
			g2d.drawString("Goal", board.getGoal().getX(), board.getGoal().getY());
			ShapeUtils.drawObstacle(g);
			ShapeUtils.drawCircularObstacle(g);
			vision=ShapeUtils.drawArc(board.getStart(),g2d);
			
		}else{		
			g2d.drawImage(robotImage, 0, 400, this);
		}
		g2d.dispose();
		repaint();
		
		if(board.isFileOpen()){
			Navigation nv= new Navigation(this);
			nv.SearchPath(board);
		}
	} 



	public void obsession(){
		//if(board.isFileOpen()) ShapeUtils.intersectObstacle();
	}

	private Image loadImage(){
		ImageIcon robotIcon = new ImageIcon(this.getClass().getResource("/images/Robot_opt.png"));
		return robotImage = robotIcon.getImage(); 
	}

	public void drawLine(Coordinates c1,Coordinates c2){
		//g2d.setColor(Color.red);
		//g2d.drawLine(c1.getX(),c1.getY(), c2.getX(),c2.getY());
		
		//repaint();
		setBackground(Color.white);
		//drawObstacle(g2d);
		ShapeUtils.drawObstacle(gref);
		ShapeUtils.drawCircularObstacle(gref);
		g2d.setColor(Color.red);
		g2d.drawLine(c1.getX(),c1.getY(), c2.getX(),c2.getY());
		g2d.drawImage(robotImage, c2.getX(),c2.getY(), this);
		g2d.setColor(Color.black);
		repaint();
	}
}
