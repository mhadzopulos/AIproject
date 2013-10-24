package RiverProblem;

import java.util.ArrayList;

public class Tree 
{
    public Node root;
    public Node pointer;
    public int level;
    public Tree(int t) 
    {
        root = new Node(t);
        root.parent = null;
        root.children = new ArrayList<Node>();
        pointer = root;
    }
    
}
    
