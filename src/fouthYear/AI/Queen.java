package fouthYear.AI;

import fouthYear.AI.Luncher;
public class Queen {
	private int columnNumber;
	private int rowNumber;
	
	public Queen(int rowNo, int colNo){
		this.rowNumber = rowNo;
		this.columnNumber = colNo;
	}
	// Setter and getter for variable.
	public int getColunmnNumber() {
		return columnNumber;
	}
	public void setColunmnNumber(int colunmnNumber) {
		this.columnNumber = colunmnNumber;
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
		columnNumber += offset;
		// Check if the new column number if bigger than the board.
		if (columnNumber > Luncher.queensNumber - 1) {
			if (columnNumber % (Luncher.queensNumber - 1) == 0) columnNumber = Luncher.queensNumber - 1;
			else columnNumber %= (Luncher.queensNumber - 1);
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
		else if (queen.getColunmnNumber() == columnNumber) return true;
		// Check if they on the same diagonal.
		 else if (Math.abs(columnNumber-queen.getColunmnNumber())
			== Math.abs(rowNumber-queen.getRowNumber())) return true;
		// The two sates can't attach each other.
		return false;
	}
	
}
