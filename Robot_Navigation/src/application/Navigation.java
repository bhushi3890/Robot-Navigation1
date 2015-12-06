package application;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Board;
import model.Coordinates;
import model.Node;
import ui.DrawBoard;
import util.RobotUtils;

public class Navigation {

	private Coordinates goal;
	public Queue<Node> priorityQueue;//To get the minimum hvalue node.
	RobotUtils rUtil;
	private DrawBoard DB;
	
	public Navigation(DrawBoard DB){
		this.DB=DB;
		
	}
	
	public void SearchPath(Board B){
		Node start=init(B);
		//priorityQueue= new PriorityQueue<>(11,hDAshValueComparator);
		findNeighbours(start);
		if(priorityQueue!=null && !priorityQueue.isEmpty()){
			Node succesor=priorityQueue.remove();
			DB.drawLine(start.getC(), succesor.getC());
			Node prev;
			while(succesor!=null && !succesor.equal(goal)){
				findNeighbours(succesor);
				prev=succesor;				
				succesor=priorityQueue.remove();
				if(succesor!=null){
					DB.drawLine(prev.getC(), succesor.getC());
				}
			}
		}
	}
	
	
	
	private Node init(Board B){
		Coordinates start;
		Node startNode;
		start=B.getStart();
		goal=B.getGoal();
		rUtil= new RobotUtils(B);
		startNode= new Node(start,0);
		rUtil.calculateHeuristic(startNode);
		return startNode;
	}
	
	private void findNeighbours(Node currentNode){
		
		Coordinates c=currentNode.getC();
		int x=c.getX();
		int y=c.getY();
		priorityQueue=new PriorityQueue<>(11,hDAshValueComparator);
		//left Neighbor
		Coordinates CorLeft=new Coordinates(x-1,y);
		Node leftNode= new Node(CorLeft,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(leftNode);
		priorityQueue.add(leftNode);
		
		//right Neighbor
		Coordinates CorRight=new Coordinates(x+1,y);
		Node rightNode= new Node(CorRight,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(rightNode);
		priorityQueue.add(rightNode);
		
		//Top Neighbor
		Coordinates CorTop=new Coordinates(x,y+1);
		Node TopNode= new Node(CorTop,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(TopNode);
		priorityQueue.add(TopNode);
		
		//Down Neighbor
		Coordinates CorDown=new Coordinates(x,y-1);
		Node DownNode= new Node(CorDown,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(DownNode);
		priorityQueue.add(DownNode);
		
		//North-West Neighbor
		Coordinates CorLeftTop=new Coordinates(x-1,y+1);
		Node leftTopNode= new Node(CorLeftTop,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(leftTopNode);
		priorityQueue.add(leftNode);
		
		//North-East Neighbor
		Coordinates CorRightTop=new Coordinates(x+1,y+1);
		Node rightTopNode= new Node(CorRightTop,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(rightTopNode);
		priorityQueue.add(rightTopNode);
		
		//South-East Neighbor
		Coordinates CorRightDown=new Coordinates(x+1,y-1);
		Node rightDownNode= new Node(CorRightDown,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(rightDownNode);
		priorityQueue.add(rightDownNode);
		
		//South-West Neighbor
		Coordinates CorLeftDown=new Coordinates(x-1,y-1);
		Node leftDownNode= new Node(CorLeftDown,currentNode.getPathCost()+1);
		rUtil.calculateHeuristic(leftDownNode);
		priorityQueue.add(leftDownNode);
	}
	
	public static Comparator<Node> hDAshValueComparator = new Comparator<Node>(){
        
        @Override
        public int compare(Node n1, Node n2) {
            return (int) (n1.gethDash() - n2.gethDash());
        }
    };
}
