package application;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Board;
import model.Coordinates;
import model.Node;
import ui.DrawBoard;
import util.RobotUtils;
import util.ShapeUtils;

public class Navigation {

	private Coordinates goal;
	public Queue<Node> priorityQueue;//To get the minimum hvalue node.
	RobotUtils rUtil;
	private DrawBoard DB;
	static int count=0;

	public Navigation(DrawBoard DB){
		this.DB=DB;

	}
    
	public void SearchPath(Board B){
		System.out.println("in search");
		Node start=init(B);
		//priorityQueue= new PriorityQueue<>(11,hDAshValueComparator);
		findNeighbours(start);
		if(priorityQueue!=null && !priorityQueue.isEmpty()){
			Node succesor=priorityQueue.remove();
			RobotUtils.setFinalVisitedCoordinates(succesor.getC());
			//DB.drawLine(start.getC(), succesor.getC());
			Node prev;
			while(succesor!=null && !succesor.equal(goal)){
				//RobotUtils.setVisitedNodes(succesor);
				RobotUtils.setFinalVisitedCoordinates(succesor.getC());
				boolean isVisible=ShapeUtils.isGoalVisibleFromCurrentPosition(succesor.getC());
				//if(succesor.getC().getX()==100)
				//System.out.println("Hii");
				if(!isVisible){	
					findNeighbours(succesor);
					prev=succesor;				
					succesor=priorityQueue.remove();
					if(succesor.getC().getX()==goal.getX() && succesor.getC().getY()==goal.getY()){
						System.out.println("Reached :)");
						return;  
					}    
					if(succesor.isVisited()){
						succesor.setPathCost(prev.getPathCost()+1);
					}else{
						succesor.setVisited(true);
					}
					/*if(succesor!=null){
						if(count++==400000)
							return;
						//DB.drawLine(prev.getC(), succesor.getC());
						//return;
					}*/
				}else{
					
					RobotUtils.setFinalVisitedCoordinates(goal);
					return;
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
		startNode= new Node(start,0.0);
		rUtil.calculateHeuristic(startNode);
		return startNode;
	}

	private void findNeighbours(Node currentNode){

		Coordinates c=currentNode.getC();
		int x=c.getX();
		int y=c.getY();
		priorityQueue=new PriorityQueue<Node>(11,hDAshValueComparator);

		//left Neighbor
		Coordinates CorLeft=new Coordinates(x-15,y);
		Node leftNode=null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeft)){
			leftNode=RobotUtils.searchNode(CorLeft);
			//leftNode.setVisited(true);
		}else{
			leftNode= new Node(CorLeft,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftNode);
			RobotUtils.setVisitedNodes(leftNode);
			RobotUtils.setVisitedCoordinates(leftNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeft) && leftNode!=null && !RobotUtils.crossingBondary(CorLeft))
			priorityQueue.add(leftNode);


		//right Neighbor
		Coordinates CorRight=new Coordinates(x+15,y);
		Node rightNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRight)){
			rightNode=RobotUtils.searchNode(CorRight);
			//rightNode.setVisited(true);
		}else{
			rightNode=new Node(CorRight,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightNode);
			RobotUtils.setVisitedNodes(rightNode);
			RobotUtils.setVisitedCoordinates(rightNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRight) && rightNode!=null && !RobotUtils.crossingBondary(CorRight))
			priorityQueue.add(rightNode);


		//Top Neighbor
		Coordinates CorTop=new Coordinates(x,y+15);
		Node topNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorTop)){
			//topNode=RobotUtils.searchNode(CorTop);
			//topNode.setVisited(true);
		}else{
			topNode=new Node(CorTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(topNode);
			RobotUtils.setVisitedNodes(topNode);
			RobotUtils.setVisitedCoordinates(topNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorTop) && topNode!=null && !RobotUtils.crossingBondary(CorTop))
			priorityQueue.add(topNode);

		//Down Neighbor
		Coordinates CorDown=new Coordinates(x,y-15);
		Node downNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorDown)){
			downNode=RobotUtils.searchNode(CorDown);
			//downNode.setVisited(true);
		}else{
			downNode=new Node(CorDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(downNode);
			RobotUtils.setVisitedNodes(downNode);
			RobotUtils.setVisitedCoordinates(downNode.getC());

		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorDown) && downNode!=null && !RobotUtils.crossingBondary(CorDown))
			priorityQueue.add(downNode);

		//North-West Neighbor
		Coordinates CorLeftTop=new Coordinates(x-15,y+15);
		Node leftTopNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeftTop)){
			leftTopNode=RobotUtils.searchNode(CorLeftTop);
			//leftTopNode.setVisited(true);
		}else{
			leftTopNode=new Node(CorLeftTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftTopNode);
			RobotUtils.setVisitedNodes(leftTopNode);
			RobotUtils.setVisitedCoordinates(leftTopNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeftTop) && leftTopNode!=null && !RobotUtils.crossingBondary(CorLeftTop))
			priorityQueue.add(leftTopNode);

		//North-East Neighbor
		Coordinates CorRightTop=new Coordinates(x+15,y+15);
		Node rightTopNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRightTop)){
			rightTopNode=RobotUtils.searchNode(CorRightTop);
			//rightTopNode.setVisited(true);
		}else{
			rightTopNode=new Node(CorRightTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightTopNode);
			RobotUtils.setVisitedNodes(rightTopNode);
			RobotUtils.setVisitedCoordinates(rightTopNode.getC());

		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRightTop) && rightTopNode!=null && !RobotUtils.crossingBondary(CorRightTop))
			priorityQueue.add(rightTopNode);

		//South-East Neighbor
		Coordinates CorRightDown=new Coordinates(x+15,y-15);
		Node rightDownNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRightDown)){
			rightDownNode=RobotUtils.searchNode(CorRightDown);
			//rightDownNode.setVisited(true);
		}else{
			rightDownNode=new Node(CorRightDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightDownNode);
			RobotUtils.setVisitedNodes(rightDownNode);
			RobotUtils.setVisitedCoordinates(rightDownNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRightDown) && rightDownNode!=null && !RobotUtils.crossingBondary(CorRightDown))
			priorityQueue.add(rightDownNode);

		//South-West Neighbor
		Coordinates CorLeftDown=new Coordinates(x-15,y-15);
		Node leftDownNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeftDown)){
			leftDownNode=RobotUtils.searchNode(CorLeftDown);
			//leftDownNode.setVisited(true);
		}else{

			leftDownNode = new Node(CorLeftDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftDownNode);
			RobotUtils.setVisitedNodes(leftDownNode);
			RobotUtils.setVisitedCoordinates(leftDownNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeftDown) && leftDownNode!=null && !RobotUtils.crossingBondary(CorLeftDown))
			priorityQueue.add(leftDownNode);

		/*System.out.println(priorityQueue.size());
		for(Node obj:priorityQueue){
			System.out.println(obj.getC().getX());
			System.out.println(obj.getC().getY());
		}*/
	}

	public static Comparator<Node> hDAshValueComparator = new Comparator<Node>(){

		@Override
		public int compare(Node n1, Node n2) {
			
			return  ((n1.gethDash() <= n2.gethDash())? -1 : 1 );
		}
	};
}
