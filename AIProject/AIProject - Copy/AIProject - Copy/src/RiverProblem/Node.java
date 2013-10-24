package RiverProblem;

import java.util.ArrayList;

public class Node 
{
	int stateNode;
	int level;
	public Node parent;
    public ArrayList<Node> children = new ArrayList<Node>();
    
	public Node(int a, Node parent)
	{
		stateNode = a;
		this.parent = parent;
		level = parent.level + 1;
	    
	}
	public Node(int a)
	{
		stateNode = a;
		level = 0;
		parent = null;
	}
}
