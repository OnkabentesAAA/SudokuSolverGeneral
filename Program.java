import java.util.*;

public class Program
{
	public static void main (String args[])
	{
		int[][] grid = new int[9][9];
		
		// List that stores all the blocks with zeros
		ArrayList<pair> zeros = new ArrayList<>();
		Scanner myObj = new Scanner(System.in);
		for (int r = 0; r<9; ++r){
			String[] LineNumbers =  myObj.nextLine().split("\\s+");
			for (int c =0; c<9; ++c){
				int newNum = Integer.parseInt(LineNumbers[c]);
				grid[r][c] = newNum;
				if( newNum == 0 ) {
					zeros.add(new pair(r,c));
				}
			}
		}
		boolean Solved = solveGrid(grid, zeros);
		//if (Solved) System.out.println("The grid has been solved.");
		//else System.out.println("The grid could not be solved.");
	}
	
	public static class pair{
		int row;
		int col;
		public pair(int rowInput, int colInput){
			row = rowInput;
			col = colInput;
		}
	}

	public static boolean solveGrid(int[][] grid, ArrayList<pair> zeros){
		if(zeros.size() == 0){
			// The only way we end up here is if there is no empty space that is found.
			// This means that the grid has been solved. All we have to do now is print it.
			printGrid(grid);
			return true;
		}
		pair p = zeros.get(0);
		
		// Since the ArrayList is mutable, the zeros array is passed by reference instead
		// of value. So we have to create a new array to pass.
		ArrayList<pair> newZeros = new ArrayList<>(zeros);
		newZeros.remove(0);
		
		// We found a blank position. Now we input a valid Number (1-9)
		int r = p.row, c = p.col;
		for (int validNum = 1; validNum<10; ++validNum){
			if (canInputNumber(r,c,validNum,grid)) {
			
				grid[r][c] = validNum;
				
				// Now we have a modified grid (It has a new number)
				// We will now solve the modified grid
				if(solveGrid(grid, newZeros)) return true;
				
				// If this section runs then that means the modified grid did
				// not lead to a solution. so we reset it.
				grid[r][c] = 0;
			}
		}
		// If the code gets to this point then that means a valid Number
		// was not found. This means that the program should backtrack
		return false;
	}

	public static boolean canInputNumber( int row, int col, int number, int[][] grid){
		// Check the block, row and column
		int rowBlockTopLeft = Math.round(row/3)*3;
		int colBlockTopLeft = Math.round(col/3)*3;
		int count = 0;
		for (int r = 0; r<3; ++r){
			for (int c =0; c<3; ++c){
				if( grid[ rowBlockTopLeft + r ][ colBlockTopLeft + c ] == number || grid[count][col] == number || grid[row][count] == number ) return false;
				++count;
			}
		}

		// Number has not been found so
		return true;
	}

	public static void printGrid(int[][] grid){
		String line;
		for (int i = 0; i<9; ++i){
			line = "";
			for (int j =0; j<9; ++j){
				//if(j == 8) line+=Integer.toString(grid[i][j]);
				//else 
				line+=Integer.toString(grid[i][j]) + " ";
			}
			System.out.println(line);
		}
	}
}
