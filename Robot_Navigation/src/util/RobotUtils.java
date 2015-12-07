package util;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Coordinates;
import model.Node;

/**
 * Helper class
 * @author apoorva
 *
 */
public class RobotUtils {

	public static List<Node> visitedNodes = new ArrayList<Node>();;
	public static List<Coordinates> visitedCoordinates = new ArrayList<Coordinates>();

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

	public static List<Node> getVisitedNodes() {
		return visitedNodes;
	}
	public static void setVisitedNodes(Node node) {
		if(visitedNodes==null)
			visitedNodes= new ArrayList<Node>();

		visitedNodes.add(node);
	}

	public static List<Coordinates> getVisitedCoordinates() {
		return visitedCoordinates;
	}

	public static void setVisitedCoordinates(Coordinates addCoordinates) {
		if(visitedCoordinates==null)
			visitedCoordinates= new ArrayList<Coordinates>();

		visitedCoordinates.add(addCoordinates);
	}


	public static boolean searchInVisitedCoordinates(Coordinates coord){
		boolean isFound = false; 
		for(Coordinates coordObj : visitedCoordinates){
			if(coord.getX()==coordObj.getX() && coord.getY()==coordObj.getY()){
				isFound=true;
			}			 
		}
		return isFound;
	}

	
	
	public static Node searchNode(Coordinates coord){
		Node node = null;
		if(searchInVisitedCoordinates(coord)){
			for(Node nodeObj:visitedNodes){
				if(nodeObj.getC().getX()==coord.getX() && nodeObj.getC().getY()==coord.getY()){
					node=nodeObj;
					return node;
				}
			}
		}	
		return node;
	}

}
