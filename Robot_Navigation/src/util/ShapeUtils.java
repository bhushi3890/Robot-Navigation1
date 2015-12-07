package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ui.DrawBoard;
import model.Board;
import model.Circle;
import model.Coordinates;
import model.Node;

/**
 * Helper class for UI
 * @author apoorva
 *
 */
public class ShapeUtils {


	static Board board = Board.getBoardInstance();
	public static Shape radiusShape(int points, int... radii)
	{
		Polygon polygon = new Polygon();

		for (int i = 0; i < points; i++)
		{
			double radians = Math.toRadians(i * 360 / points);
			int radius = radii[i % radii.length];

			double x = Math.cos(radians) * radius;
			double y = Math.sin(radians) * radius;

			polygon.addPoint((int)x, (int)y);
		}

		Rectangle bounds = polygon.getBounds();
		polygon.translate(-bounds.x, -bounds.y);

		return polygon;
	}


	public static Arc2D drawArc(Coordinates currentNode,Graphics2D g){

		//Arc2D temp= new Arc2D.Double(currentNode.getX()+180,currentNode.getY()-55, 180, 180, 0, 120, Arc2D.PIE);
		Arc2D temp= new Arc2D.Double();
		temp.setArcByCenter(currentNode.getX()+3, currentNode.getY()-3,150 , 0, 120, Arc2D.PIE);
		Color c=new Color(1.0f,1.0f,0.0f,.3f );
		g.setColor(c);
		g.fill(temp);
		return temp;
	}

	/**
	 * Draws obstacle
	 * @param g2d
	 */
	public static void drawObstacle(Graphics g) {
		// TODO Auto-generated method stub
		Iterator<?> it = board.getObstacleMap().entrySet().iterator();

		while(it.hasNext()){
			Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
			List<Coordinates> coordinateList = pair.getValue();
			int sides =coordinateList.size();
			int[] polyX = new int[sides];
			int[] polyY =new int[sides];

			int counter=0;
			for(Coordinates obj:coordinateList){
				polyX[counter]=obj.getX();
				polyY[counter]=obj.getY();
				counter++;				
			}

			//g.drawPolygon(polyX, polyY, sides);	
			g.setColor(Color.GRAY);
			g.fillPolygon(polyX, polyY, sides);
		}
	}


	public static void drawCircularObstacle(Graphics g) {
		// TODO Auto-generated method stub
		if(board.getCircularObstacle()!=null){
			Iterator<?> it = board.getCircularObstacle().entrySet().iterator();

			while(it.hasNext()){
				Map.Entry<Integer, List<Circle>> pair = (Map.Entry)it.next();
				List<Circle> coordinateList = pair.getValue();
				g.fillOval(coordinateList.get(0).getX(),coordinateList.get(0).getY(), coordinateList.get(0).getRadius(), coordinateList.get(0).getRadius());


			}		
		}

	}

	public static boolean intersectObstacle(Node currentNode, Coordinates corLeft) {
		boolean isIntersecting = false; 
		Iterator<?> it = board.getObstacleMap().entrySet().iterator();
		Line2D displacement = new Line2D.Double(currentNode.getC().getX(),currentNode.getC().getY(), corLeft.getX(),corLeft.getY());
		while(it.hasNext()){

			Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
			List<Coordinates> coordinateList = pair.getValue();
			int sides =coordinateList.size();
			for(int i=0;i<sides;i++){
				Line2D line;
				if(i==sides-1)
					line = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(),  coordinateList.get(0).getX(), coordinateList.get(0).getY());
				else
					line = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(), coordinateList.get(i+1).getX(), coordinateList.get(i+1).getY());
				if(line.intersectsLine(displacement)){
					//					System.out.println("Hii");
					//					System.out.println( corLeft.getX());
					//					System.out.println( corLeft.getY());
					isIntersecting = true;
					return isIntersecting;
				}	
			}
		}
		//System.out.println("outside"+isIntersecting);
		return isIntersecting;
	}



	//	/**
	//	 * Draws obstacle
	//	 * @param succesor 
	//	 * @param start 
	//	 * @param vision 
	//	 * @param g2d
	//	 */
	//	public static boolean intersectObstacle(Node start, Node succesor) {
	//		// TODO Auto-generated method stub
	//		boolean isIntersecting = false; 
	//		Iterator<?> it = board.getObstacleMap().entrySet().iterator();
	//		Line2D displacement = new Line2D.Double(start.getC().getX(),start.getC().getY(), succesor.getC().getX(),succesor.getC().getY());
	//		while(it.hasNext()){
	//			Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
	//			List<Coordinates> coordinateList = pair.getValue();
	//			int sides =coordinateList.size();
	//			for(int i=0;i<sides;i++){
	//				if(displacement.contains(coordinateList.get(i).getX(),coordinateList.get(i).getY())){
	//					System.out.println("Hmmmm...");
	//					isIntersecting = true;
	//					return isIntersecting;
	//				} 
	//
	////				Line2D line;
	////				if(i==sides-1)
	////					line = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(),  coordinateList.get(0).getX(), coordinateList.get(0).getY());
	////				else{
	////					line = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(), coordinateList.get(i+1).getX(), coordinateList.get(i+1).getY());
	////				}
	////				if(displacement.intersectsLine(line)){
	////					System.out.println("Lines are intersecting");
	////					isIntersecting=true;
	////					return isIntersecting;
	////				}
	//			}
	//		}
	//		return isIntersecting;
	//	}


}