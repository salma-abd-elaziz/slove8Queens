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
	
	/*
	 * the function moves the queen to the right from the current state by 
	 * offset.
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
	
}
