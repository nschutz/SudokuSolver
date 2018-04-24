package sudokusolver.cse.miamioh.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SolverMain {
	
	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		int[][] board = new int[9][9];
		readFile(board, "fifth.txt"); // here's where you can change the text file
		System.out.println("Puzzle Provided: ");
		printBoard(board);
		if(SolveSudoku(board)) {
		System.out.println("Puzzle Solved Succesfully: ");	
		} else {
			System.out.println("This puzzle can not be solved");
			System.out.println("Here is the last solution that was found: ");
		}
		printBoard(board);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Took " + duration / 1000000 + " milliseconds to execute");
	}
	
	static void readFile(int[][] board, String fileName) throws IOException {
		String line = null;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int row = 0;
		while((line = bufferedReader.readLine()) != null) {
			for(int col = 0; col < 9; col++) {
				if (line.charAt(col) == '-'){
					board[row][col] = 0;
				} else {
				board[row][col]= Character.getNumericValue(line.charAt(col));
				}
			}
			row++;
		}
		bufferedReader.close();
	}
	
	static boolean SolveSudoku(int[][] board) { // change to board when read in method is done
		int[] rowCol = new int[]{0,0};
		if(!isComplete(board, rowCol)) {
			return true;
		}
		// loop 1 to 9
		for (int newNum = 1; newNum <= 9; newNum++) {
			// if it is safe
			if (canInsertValue(board, rowCol, newNum)) {
				//make a test assignment
				int row = rowCol[0];
				int col = rowCol[1];
				board[row][col] = newNum;
				
				// if successful then complete
				if(SolveSudoku(board)) {
					return true;
				}
				
				// undo the value that didn't work
				board[rowCol[0]][rowCol[1]] = 0; 
				
			}
		}
		return false; // this starts the backtracking
	}
	/**
	 * Searches the board to find an unassigned (0) value
     * if found, the puzzle object's currentRow currentCol 
     * variables will be set to the unassigned value 
	 * @param board 
	 * @return true if unassigned values exist, false if not
	 */
	static boolean isComplete(int[][] board, int[] rowCol) {
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(board[x][y] == 0) {
					rowCol[0] = x;
					rowCol[1] = y;
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Returns a boolean that reflects whether any numbers in the row
	 * are the same number as the test number
	 * @param board
	 * @param row
	 * @param num
	 * @return
	 */
	static boolean isInRow(int[][] board, int row, int num) {		
		for (int col = 0; col < 9; col++) {
			if(board[row][col] == num) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns a boolean that reflects whether any numbers in the col
	 * are the same number as the test number
	 * @param board
	 * @param col
	 * @param num
	 * @return
	 */
	static boolean isInCol(int[][] board, int col, int num) {
		
			for (int row = 0; row < 9; row++) {
				if(board[row][col] == num) {
					return true;
				}
			}
			return false;
		}
	
	static boolean isInSquare(int[][] board, int squareStartRow, int squareStartCol, int num) {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if(board[row + squareStartRow][col + squareStartCol] == num) {
					return true;
				}
			}
		}
		return false;		
	}
	
	static boolean canInsertValue(int[][] board, int[] rowCol, int num) {
		int row = rowCol[0];
		int col = rowCol[1];
		return !isInCol(board, col, num) &&
				!isInRow(board, row, num) &&
				!isInSquare(board, row - row % 3, col - col % 3, num); 
	}
	static void printBoard(int[][] board) {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

