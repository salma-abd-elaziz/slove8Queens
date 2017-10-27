package fouthYear.AI;

import java.util.Random;

public class State {
	// The index number is the row number, the value is the column number. 
	private Queen[] stateBoard;
	// Number of attacks in the state.
	private int numberOfAttacks;
	

	public State(){
		stateBoard = new Queen[Luncher.queensNumber];
		numberOfAttacks = 0;
		this.genStateRan();
		this.calculatenumberOfAttacks();
	}

	public State(State state){
		this.copyState(state);
	}
	// Setter and getter for variable.
	public Queen[] getStateBoard() {
		return stateBoard;
	}

	public void setStateBoard(Queen[] stateBoard) {
		this.stateBoard = stateBoard;
	}
	
	public Queen getQueenInRow(int rowNo) {
		return stateBoard[rowNo];
	}

	public int getNumberOfAttacks() {
		return numberOfAttacks;
	}

	/* The function generates the sate at random, only one queen in each row. */
	private void genStateRan(){
		Random rand = new Random(); 
		int ranNo;
		for (int i = 0; i < Luncher.queensNumber; i++){
			// Generate random number, and use it as column number.
			ranNo = rand.nextInt(Luncher.queensNumber);
			stateBoard[i] = new Queen(i, ranNo);
		}
	}
	
	/* The function copies a state to this state, this will be used 
	 * when we generate the neighbors of state.
	 *@param state
	 *		the state that this one will be a copy of it. */
	private void copyState(State state) {
		Queen queen;
		for (int i = 0; i < Luncher.queensNumber; i++) {
			// Get queen at row i.
			queen = state.getQueenInRow(i);
			stateBoard[i] = new Queen(i, queen.getColunmnNumber()); 
		}
		this.numberOfAttacks = state.getNumberOfAttacks();
	}
	
	/* The function finds all the neighbors of the state (64 board) 
	 * and returns the one with minimum number of attacks*/
	public State neighborWithlowestAttachNo() {
		State result = null;
		for (int i = 0; i < Luncher.queensNumber; i++) {
			for (int j = 0; j < Luncher.queensNumber; j++) {
				State neighbor = new State(this); 
				// Move the i(th) queen by j steps for 8 times. 
				neighbor.getQueenInRow(i).moveRight(j); 
				neighbor.calculatenumberOfAttacks();
				if (result != null && neighbor.getNumberOfAttacks() < result.getNumberOfAttacks()) {
					result = neighbor;
				}
			}
		}
		return result;
	}

	/* The function calculate the number of attacks of the state.
	 * @return the number of attacks it calculated*/
	public int calculatenumberOfAttacks() {
		for (int i = 0; i < Luncher.queensNumber; i++) {
			for (int j = i + 1; j < Luncher.queensNumber; j++) {
				if (stateBoard[i].isAttacking(stateBoard[j])) this.numberOfAttacks++;
			}
		}
		return numberOfAttacks;
	}
}
