package fouthYear.AI;

import java.util.ArrayList;
import java.util.Random;

public class State {
	// The index number is the row number, the value is the column number. 
	private Queen[] stateBoard;
	// To save the K neighbors needed in k-Beam algorithm.
	private ArrayList<State> neighbors;


	public State(){
		neighbors = new ArrayList<State>();
		stateBoard = new Queen[Luncher.queensNumber];
		this.genStateRan();
	}

	public State(State state){
 		neighbors = new ArrayList<State>();
		stateBoard = new Queen[Luncher.queensNumber];
		this.copyState(state);
	}
	
	// Setter and getter for variable.
	public Queen[] getStateBoard() {
		return stateBoard;
	}

	public ArrayList<State> getNeighbors() {
		return neighbors;
	}
	
	public void setStateBoard(Queen[] stateBoard) {
		this.stateBoard = stateBoard;
	}
	
	public Queen getQueenInRow(int rowNo) {
		return stateBoard[rowNo];
	}


	/* The function generates the sate at random, only one queen in each row. */
	private void genStateRan(){
		Random rand = new Random(); 
		int ranNo;
		for (int i = 0; i < Luncher.queensNumber; i++){
			// Generate random number, and use it as column number.
			ranNo = rand.nextInt(Luncher.queensNumber);
			System.out.println(ranNo);
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
	}
	
	/* The function finds all the neighbors of the state (64 board) 
	 * and returns the one with minimum number of attacks
	 * @param saveNeighbors 
	 * 		true if we need to save k neighbors, the function in this case is 
	 * 		called from kBeams in SearchMethods class
	 * @return the best node in the neighbors of this state.
	 * */
	public Node neighborWithlowestAttachNo(boolean saveNeighbors) {
		State result = null;
		int resultNoAttacks = Integer.MAX_VALUE;
		
		for (int i = 0; i < Luncher.queensNumber; i++) {
			for (int j = 0; j < Luncher.queensNumber; j++) {
				State neighbor = new State(this); 
				// Move the i(th) queen by j steps for 8 times. 
				neighbor.getQueenInRow(i).moveRight(j); 
				if (saveNeighbors) saveNeignhor(neighbor);
				int neighborNoAttacks = neighbor.calculatenumberOfAttacks();
				// Update the result with the neighbor with the smaller 
				//number of attacks.
				if (neighborNoAttacks < resultNoAttacks) {
					result = neighbor;
					resultNoAttacks = neighborNoAttacks;
				}
			}
		}
		return new Node(result, resultNoAttacks);
	}
	
	/*
	 * save k neighbors in the neighbor
	 * @param neighbor the neighbor to add in the neighbors list. */
	private void saveNeignhor(State neighbor) {
		int size = neighbors.size();
		for (int i = 0; i < size; i++) {
			// Compare neighbors to add new neighbor in the right place. 
			if (neighbors.get(i).calculatenumberOfAttacks() > neighbor.calculatenumberOfAttacks()){ 
				neighbors.add(i, neighbor);
				break;
			}
		}
		// the neighbor's number of attacks bigger then all neighbors, so it's 
		//not added to the list.
		if (size < SearchMethods.getK() && size == neighbors.size()){
			neighbors.add(size, neighbor);
		}
		// if the size exceeded k return the first k elements.
		if (neighbors.size() > SearchMethods.getK()) {
			neighbors = new ArrayList<State>(neighbors.subList(0, SearchMethods.getK()));
		}
	}

	/* The function calculate the number of attacks of the state.
	 * 
	 * @return the number of attacks it calculated
	 * */
	public int calculatenumberOfAttacks() {
		int numberOfAttacks = 0;
		for (int i = 0; i < Luncher.queensNumber; i++) {
			for (int j = i + 1; j < Luncher.queensNumber; j++) {
				if (stateBoard[i].isAttacking(stateBoard[j])) numberOfAttacks++;
			}
		}
		return numberOfAttacks;
	}
}
