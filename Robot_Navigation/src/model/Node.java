package model;

public class Node {
	
	private Coordinates c;
	private int hDash;
	private int pathCost;
	private int h;
	private boolean visited=false;
	
	public Node(Coordinates c,int pc){
		this.c=c;
		this.pathCost=pc;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
		this.hDash=this.pathCost+this.h;
	}
	public double getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
		this.hDash=this.pathCost+this.h;
	}
	public double gethDash() {
		return hDash;
	}
	/*public void sethDash(int hDash) {
		this.hDash = hDash;
	}*/
	public Coordinates getC() {
		return c;
	}
	public void setC(Coordinates c) {
		this.c = c;
	}	
	
	public boolean equal(Coordinates c1){
		
		if(c.getX()==c1.getX() && c.getY()==c1.getY()){
			
			return true;
		}
		
		return false;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean isVisited) {
		this.visited = isVisited;
	}
}
