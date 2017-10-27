package fouthYear.AI;

public class SearchMethods {
	
	/* The function performs the local search algorithm.
	 * 
	 * @return the best state that it can reach in its search space.
	 * */
	public Node localSearch() {
		// Initial Node.
		Node current = new Node();
		System.out.println("no of attacks " + current.getValue());
		Luncher.p(current.getState());
		boolean found = false;
		while (!found) {
			// Find the highest valued successor of current.
			Node neighbor = current.getBestSuccessor();
			System.out.println("Neighbor***** no of attacks " + neighbor
					.getValue());
			Luncher.p(neighbor.getState());
			// If neighbor is worst than current(bigger number of attacks), I'm 
			//not improving anymore so return current.
			if (neighbor.getValue() >= current.getValue()) return current;
			current = neighbor;
		}
		return current;
	}
	/* The function performs the local search algorithm with random restarts 
	 * to found the best possible solution starting from different start points
	 * the algorithm if the global optimum wasn't found after 5 iterations 
	 * it returns the best solution was reached.
	 * @param initialState
	 *		the initial state that the algorithm will start at.
	 * @return the best state that it can reach in its search iterations.
	 * */
	
	public Node randomRestart() {
		
		
		return null;
	}
	
	/*
	 * the function implements local search, with side way
	 *  moves to over come shoulder.
	 *  
	 *  @return the best state that it can reach in its search space.
	 * */
	public Node localSearchWithSidewayMoves() {
		return null;
	}
	
	/*
	 * the function implements k-local beams search. 
	 *  
	 *  @return the best state that it can reach in its k beams.
	 * */
	public Node kBeams() {
		return null;
	}
}
