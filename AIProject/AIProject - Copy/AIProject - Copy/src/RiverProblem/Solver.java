package RiverProblem;

import java.util.ArrayList;

public class Solver 
{
	public int state, previousState, finalState;
	public int direction = 0, forward = 0, backward = 1;
	public int maxMissionaries, maxCannibals, boatSize;
	boolean finished = false;
	Tree stateTree;
	Node nextNode, finalNode;
	ArrayList<Integer> moves = new ArrayList<Integer>();
	//ArrayList<Integer> badCombinations = new ArrayList<Integer>();
	ArrayList<Integer> previousFStates = new ArrayList<Integer>();
	ArrayList<Integer> previousBStates = new ArrayList<Integer>();
	ArrayList<Node> fringe = new ArrayList<Node>();
	int fringeIndex = 0;
	ArrayList<Node> previousFringe = new ArrayList<Node>();
	int searchLevel = 0;
	
	public Solver(int state, int boatSize)
	{
		this.boatSize = boatSize;

		this.state = state;
		previousState = this.state;
		stateTree = new Tree(this.state);
		
		maxMissionaries = state/1000 + state%100/10;
		maxCannibals = (state%1000)/100 + state%10;

		finalState = maxMissionaries*10 + maxCannibals;
		
		findMoves(this.boatSize);
		//System.out.println(moves.toString());
		previousBStates.add(this.state);
		previousFringe.add(stateTree.pointer);
	}
	
	public void Decide()
	{
		for(Node a: previousFringe)
		{
			System.out.print(a.stateNode + " ");
		}
		System.out.println("");
		direction = stateTree.pointer.level%2; //if the direction is odd, then go back, if even go forward
		//look at direction/state, list option
		state = stateTree.pointer.stateNode;
		previousState = state;
		System.out.println("level :" + stateTree.pointer.level + " direction: " + direction);
		
		
		if(direction == forward)
		{
			for(int i: moves) //checks all possible moves
			{
				System.out.println("current state " + state);
				System.out.println(-i*100);
				
				state = state - (i*100);
				int cannibals = (state%1000)/100;
				int missionaries  = state/1000;
				/*checks to see if the left has
				* more missionaries to cannibals (but doesn't matter if there are no missionaries)
				* or if there is a carrying issues
				* 
				*/
				if(cannibals > missionaries && missionaries > 0 || state < 100 && state > finalState || cannibals < 0 || missionaries < 0) 
				{
					System.out.println(state+ " No good");
					state = previousState;
					System.out.println("");
				}
				else //if all is well, do other side
				{
					System.out.println("+" +i);
					state = state + i;
					cannibals = state%10;
					missionaries  = state%100/ 10;
					int total = cannibals + missionaries;
					/*Again, checks current cannibals vs missionaries
					* or if there aren't enough people to send back over
					* 
					*/
					if(cannibals > missionaries && missionaries > 0 || total < 2 || cannibals > maxCannibals || missionaries > maxMissionaries)
					{
						//learns different bad combinations (but not implemented)
						System.out.println(state+ " No good");
						state = previousState;
						
						System.out.println("");
						
					}
					else
					{
						boolean found = false;
						
						//Checks for previous states going in the same direction
						for(int a: previousFStates)
						{
							if(a == state)
							{
								found = true;
								System.out.println("duplicate " + state + " rejected");
								state = previousState;
								System.out.println("");
							}
						}
						if(found == false)
						{
							Node tempNode = new Node(state,stateTree.pointer);
							previousFStates.add(state);
							if(state == finalState) //checks to see if the state = final state, if so, we're done!
							{
								finished = true;
								System.out.println("We're Done!");
								finalNode = tempNode;
								stateTree.pointer.children.add(tempNode);
								return;
							}
							//After all the checks, save the node as child
							stateTree.pointer.children.add(tempNode);
							fringe.add(tempNode);
							System.out.println("saved " + state);
							state = previousState; 
							System.out.println("");
							
						}
					}
				}
			}
			//A breadth first search - checks to see if the node is the root or at the end of the fringe

			if(fringeIndex  + 1< previousFringe.size())
			{
				fringeIndex++;
				stateTree.pointer = previousFringe.get(fringeIndex);
				System.out.println("next node in the fringe is: " + stateTree.pointer.stateNode);
			}
			else if(fringeIndex + 1 >= previousFringe.size())
			{
				fringeIndex = 0;
				previousFringe.clear();
				for(Node a: fringe)
				{
					previousFringe.add(a);
				}
				fringe.clear();
				System.out.println("onto the next level");
				stateTree.pointer = previousFringe.get(fringeIndex);
			}
		}
			
		else //going backwards
		{
			for(int i: moves)
			{
				System.out.println("current state " + state);
				System.out.println("+" + i*100);
				state = state + (i*100);
				int cannibals = (state%1000)/100;
				int missionaries  = state/1000;
				System.out.println("missionaries/cannibals " + missionaries + " + " + cannibals);
				if(cannibals > missionaries && missionaries > 0 || state < 0 || cannibals > maxCannibals || missionaries > maxMissionaries) 
				{
					System.out.println(state+ " No good");
					state = previousState;
					System.out.println("");
				}
				else
				{
					System.out.println(-i);
					state = state - i;
					cannibals = state%10;
					missionaries  = state%100/ 10;
					if(cannibals > missionaries && missionaries > 0 || missionaries < 0 || cannibals < 0)
					{
						System.out.println(state+ " No good");
						state = previousState;
						System.out.println("");
					}
					else
					{
						boolean found = false;
						//Checks for previous states going in the same direction
						for(int a: previousBStates)
						{
							if(a == state)
							{
								found = true;
								System.out.println("duplicate " + state + " rejected");
								state = previousState;
								System.out.println("");
								
							}
						}
						
						if(found == false)
						{
							
							previousBStates.add(state);
							Node tempNode = new Node(state,stateTree.pointer);
							if(state == finalState)
							{
								finished = true;
								System.out.println("We're Done!");
								finalNode = tempNode;
								System.out.println(finalNode.stateNode);
								return;
							}
							stateTree.pointer.children.add(tempNode);
							fringe.add(tempNode);
							System.out.println("saved " + state);
							state = previousState;
							System.out.println("");
						}
					}
				}
			}
			
			if(fringeIndex  + 1 < previousFringe.size())
			{
				fringeIndex++;
				stateTree.pointer = previousFringe.get(fringeIndex);
				System.out.println("next Node in the fringe is: " + stateTree.pointer.stateNode);
			}
			else if(fringeIndex + 1 >= previousFringe.size())
			{
				fringeIndex = 0;
				previousFringe.clear();
				for(Node a: fringe)
				{
					previousFringe.add(a);
				}
				fringe.clear();
				stateTree.pointer = previousFringe.get(fringeIndex);
			}
		}
		
	}
	public void findMoves(int n)
	{
		/* here n represents the size of the boat
		 * so now we populate the moves array that
		 * determines the combinations of missionaries
		 * and cannibals on the boat
		 */
		int i = 1;
		while(i < ((n+1)*(n+1)))
		{
			int first = i%(n+1);
			int second = i/(n+1);
			int result = (second*10)+first;
			if(first + second <= n)
				moves.add(result);
			i++;
		}
	}
	
	
}
