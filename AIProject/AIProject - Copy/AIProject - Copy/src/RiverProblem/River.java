package RiverProblem;

import java.util.LinkedList;
public class River 
{
	public static void main(String[] args)
	{
		LinkedList<Integer> iterator = new LinkedList<Integer>();
		Solver ai = new Solver(4400, 3);
		int i = 0;
		while(ai.finished != true)          //keep going until the state = final state
		{
			ai.Decide();
		}
		
		//Iterate from the root to the final state
		
		Node output = ai.finalNode;
		iterator.add(output.stateNode);
		while(output.parent != null)
		{
			output = output.parent;
			iterator.addFirst(output.stateNode);
		}
		for(Integer a : iterator)
		{
			System.out.print(i + ":" + a + " - ");
			i++;
		}
	}
}
