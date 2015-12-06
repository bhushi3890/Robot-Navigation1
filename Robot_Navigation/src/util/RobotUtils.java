package util;

import model.Board;
import model.Coordinates;
import model.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

/**
 * Helper class
 * @author apoorva
 *
 */
public class RobotUtils {
	
	private Board environment;
	//private HashMap <Integer,List<Node>> knowledgeBase = new HashMap <Integer,List<Node>>();
	

	public RobotUtils(Board B){
		
		this.environment=B;
	}
	public void calculateHeuristic(Node n){
		Coordinates goal=environment.getGoal();
		n.setH(calulateDistance(n.getC(),goal));
	}
	private int calulateDistance(Coordinates C1, Coordinates C2){
				
		return (int)(Math.sqrt((C1.getX()-C2.getX())*(C1.getX()-C2.getX()) + (C1.getY()-C2.getY())*(C1.getY()-C2.getY()))); 
	}
	/*public HashMap <Integer,List<Node>> getKnowledgeBase() {
		return knowledgeBase;
	}
	public void setKnowledgeBase(HashMap <Integer,List<Node>> knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}*/
}
