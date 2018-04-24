package sudokusolver.cse.miamioh.edu;

public class SudokuPuzzle {
	public int[][] board;
	public int currentCol;
	public int currentRow;
	
	public SudokuPuzzle() {
		this.board = new int[][]{
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}
		};
		this.currentCol = 0;
		this.currentRow = 0;
	}
//	public setValue(int row, int col, int num) {
//		this.board[row][col] = num;
//	}
}
