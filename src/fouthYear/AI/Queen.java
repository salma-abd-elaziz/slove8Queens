package fouthYear.AI;

import fouthYear.AI.Luncher;
public class Queen {
	private int colunmnNumber;
	private int rowNumber;
	
	public Queen(int colNo, int rowNo){
		this.colunmnNumber = colNo;
		this.rowNumber = rowNo;
	}

	public int getColunmnNumber() {
		return colunmnNumber;
	}
	public void setColunmnNumber(int colunmnNumber) {
		this.colunmnNumber = colunmnNumber;
	}
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	/* The function moves the queen to the right from the current state by 
	 * offset(moves the queen to another column in the same row).
	 * @param offset 
	 * 		the value that the queen will move by to the right.
	 * */
	public void moveRight(int offset){
		colunmnNumber += offset;
		// Check if the new column number if bigger than the board.
		if (colunmnNumber > Luncher.queensNumber - 1) {
			if (colunmnNumber % (Luncher.queensNumber - 1) == 0) colunmnNumber = Luncher.queensNumber - 1;
			else colunmnNumber %= (Luncher.queensNumber - 1);
		}
	}
	
	/* The function check if a given queen can be attacked by this queen.
	 * 
	 *  @param queen
	 *  	the queen the check if it is being attacked by this queen .
	 *  @return true if the queen being attached by this queen, otherwise false.*/
	public boolean isAttacking(Queen queen) {
		// The two queens on the same row.
		if (queen.getRowNumber() == rowNumber) return true;
		// The two queens on the same column.
		else if (queen.getColunmnNumber() == colunmnNumber) return false;
		// Check if they on the same diagonal.
		int row , col;
		// Down.
		row = rowNumber + 1;
		col = colunmnNumber + 1;
		// Keep searching while in board bounds.
		while (row < Luncher.queensNumber && col < Luncher.queensNumber) {
			if(queen.getRowNumber() == row &&
					queen.getColunmnNumber() == col) return true;
			row++;
			col++;
		}
		// Up.
		row = rowNumber - 1;
		col = colunmnNumber - 1;
		// Keep searching while in board bounds.
		while (row >= 0 && col >= 0) {
			if(queen.getRowNumber() == row &&
					queen.getColunmnNumber() == col) return true;
			row--;
			col--;
		}
		// Can't attach each other.
		return false;
	}
	
}
