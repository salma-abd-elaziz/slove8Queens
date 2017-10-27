package fouthYear.AI;

public class Node {
	// Number of attacks in the state which is the value of the node.
	private int numberOfAttacks;
	private State state;
	

	// Called to generate the initial state.
	public Node() {
		numberOfAttacks = 0;
		state = new State(); // Generate a random state.
		// Calculate its number of attacks.
		numberOfAttacks = state.calculatenumberOfAttacks();
	}
	
	public Node (State state, int numberOfAttacks){
		this.state = state;
		this.numberOfAttacks = numberOfAttacks;
	}
	
	public int getValue() {
		return numberOfAttacks;
	}
	
	public State getState() {
		return state;
	}

	/*
	 * the function calls a function on State class to find the best neighbor
	 * @returns a node which contain the state and the value of the best 
	 * 		successor*/
	public Node getBestSuccessor(){
		return state.neighborWithlowestAttachNo();
	}
}
