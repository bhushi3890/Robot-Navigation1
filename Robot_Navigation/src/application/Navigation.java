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
				//RobotUtils.setVisitedNodes(succesor);
				//RobotUtils.setVisitedCoordinates(succesor.getC());
				
				if(succesor.getC().getX()==100)
					System.out.println("Hii");
				
				findNeighbours(succesor);
				prev=succesor;				
				succesor=priorityQueue.remove();
				if(succesor.isVisited()){
					succesor.setPathCost(prev.getPathCost()+1);
				}else{
					succesor.setVisited(true);
				}
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
		Node leftNode=null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeft))
			leftNode=RobotUtils.searchNode(CorLeft);
		else{
			leftNode= new Node(CorLeft,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftNode);
			RobotUtils.setVisitedNodes(leftNode);
			RobotUtils.setVisitedCoordinates(leftNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeft) && leftNode!=null)
			priorityQueue.add(leftNode);


		//right Neighbor
		Coordinates CorRight=new Coordinates(x+1,y);
		Node rightNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRight))
			rightNode=RobotUtils.searchNode(CorRight);
		else{
			rightNode=new Node(CorRight,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightNode);
			RobotUtils.setVisitedNodes(rightNode);
			RobotUtils.setVisitedCoordinates(rightNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRight) && rightNode!=null)
			priorityQueue.add(rightNode);


		//Top Neighbor
		Coordinates CorTop=new Coordinates(x,y+1);
		Node topNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorTop))
			topNode=RobotUtils.searchNode(CorTop);
		else{
			topNode=new Node(CorTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(topNode);
			RobotUtils.setVisitedNodes(topNode);
			RobotUtils.setVisitedCoordinates(topNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorTop) && topNode!=null)
			priorityQueue.add(topNode);

		//Down Neighbor
		Coordinates CorDown=new Coordinates(x,y-1);
		Node downNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorDown))
			downNode=RobotUtils.searchNode(CorDown);
		else{
			downNode=new Node(CorDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(downNode);
			RobotUtils.setVisitedNodes(downNode);
			RobotUtils.setVisitedCoordinates(downNode.getC());

		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorDown) && downNode!=null)
			priorityQueue.add(downNode);

		//North-West Neighbor
		Coordinates CorLeftTop=new Coordinates(x-1,y+1);
		Node leftTopNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeftTop))
			leftTopNode=RobotUtils.searchNode(CorLeftTop);
		else{
			leftTopNode=new Node(CorLeftTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftTopNode);
			RobotUtils.setVisitedNodes(leftTopNode);
			RobotUtils.setVisitedCoordinates(leftTopNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeftTop) && leftTopNode!=null)
			priorityQueue.add(leftTopNode);

		//North-East Neighbor
		Coordinates CorRightTop=new Coordinates(x+1,y+1);
		Node rightTopNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRightTop))
			rightTopNode=RobotUtils.searchNode(CorRightTop);
		else{
			rightTopNode=new Node(CorRightTop,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightTopNode);
			RobotUtils.setVisitedNodes(rightTopNode);
			RobotUtils.setVisitedCoordinates(rightTopNode.getC());

		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRightTop) && rightTopNode!=null)
			priorityQueue.add(rightTopNode);

		//South-East Neighbor
		Coordinates CorRightDown=new Coordinates(x+1,y-1);
		Node rightDownNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorRightDown))
			rightDownNode=RobotUtils.searchNode(CorRightDown);
		else{
			rightDownNode=new Node(CorRightDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(rightDownNode);
			RobotUtils.setVisitedNodes(rightDownNode);
			RobotUtils.setVisitedCoordinates(rightDownNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorRightDown) && rightDownNode!=null)
			priorityQueue.add(rightDownNode);

		//South-West Neighbor
		Coordinates CorLeftDown=new Coordinates(x-1,y-1);
		Node leftDownNode= null;
		if(RobotUtils.searchInVisitedCoordinates(CorLeftDown))
			leftDownNode=RobotUtils.searchNode(CorLeftDown);
		else{

			leftDownNode = new Node(CorLeftDown,currentNode.getPathCost()+1);
			rUtil.calculateHeuristic(leftDownNode);
			RobotUtils.setVisitedNodes(leftDownNode);
			RobotUtils.setVisitedCoordinates(leftDownNode.getC());
		}
		if(!ShapeUtils.intersectObstacle(currentNode,CorLeftDown) && leftDownNode!=null)
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
			return (int) (n1.gethDash() - n2.gethDash());
		}
	};
}
