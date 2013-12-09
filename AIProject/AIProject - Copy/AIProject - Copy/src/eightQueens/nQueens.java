package eightQueens;

public class nQueens {
	static int n = 8;
	private static int[] board = new int[n];
	  //solution # counter
	  private static int s = 0; 
	  
	  //checks to see if a square is unsafe by going through and looking diagonally, vertically, and across
	  static boolean notSafe(int y)
	  { 
	    int x = board[y]; //column queen is placed in
	    for (int i = 1; i <= y; i++)
	    {
	      int t = board[y - i];
	      if (t == x || t == x - i || t == x + i)
	      {
	        return true;
	      }
	    }
	 
	    return false;
	  }
	 
	  public static void printBoard() 
	  {
		  //prints out the board
	    System.out.println("\n\nSolution " + (++s));
	    for (int y = 0; y < n; y++) 
	    {
	      for (int x = 0; x < n; x++) 
	      {
	        System.out.print((board[y] == x) ? "|Q" : "|_");
	      }
	      System.out.println("|");
	    }
	  }
	 
	  public static void main(String[] args) {
	    int row = 0;
	    board[0] = -1;
	    while (row >= 0) 
	    {
	      do 
	      {
	        board[row]++; //increment the value of the column
	      } 		// while the column is less than the max queens and is safe
	      while ((board[row] < n) && notSafe(row));
	      
	      if (board[row] < n)  // if the attacking pairs is less than the number of queens
	      {
	        if (row < (n-1)) // if the counter is less than the number of queens-1
	        {
	          board[++row] = -1; //the next row queen is then set to -1
	        } 
	        else
	        {
	          printBoard();
	        }
	      } 
	      else 
	      { 
	        row--;
	      }
	    }
	  }
	}


