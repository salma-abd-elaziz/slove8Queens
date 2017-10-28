package fouthYear.AI;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SearchMethods {

	private static final int LOOPINGLIMITS = 50;
	public static final int k = 5;
	int steps = 0;

	/*
	 * The function performs the local search algorithm.
	 * 
	 * @return the best state that it can reach in its search space.
	 */
	public Node hillClimbing() {
		steps = 0;
		// Initial Node.
		Node current = new Node();
		boolean found = false;
		while (!found) {
			steps++;
			// Ever iteration is a step.

			// Find the highest valued successor of current.
			Node neighbor = current.getBestSuccessor();
			// Found optimal solution.
			if (neighbor.getValue() == 0) {
				// System.out.println(steps);
				return neighbor;
			}
			// If neighbor is worst than current(bigger number of attacks), I'm
			// not improving anymore so return current.
			if (neighbor.getValue() >= current.getValue())
				return current;
			current = neighbor;
		}
		return current;
	}
	/*
	 * The function performs the local search algorithm with random restarts to
	 * found the best possible solution starting from different start points the
	 * algorithm if the global optimum wasn't found after 5 iterations it
	 * returns the best solution was reached.
	 * 
	 * @param initialState the initial state that the algorithm will start at.
	 * 
	 * @return the best state that it can reach in its search iterations.
	 */

	public Node randomRestart() {
		State current = null;
		// number of attacks variable.
		int cuurentNoOfAttacks = Integer.MAX_VALUE;
		// Put a limit to not loop for ever.
		for (int i = 0; i < LOOPINGLIMITS; i++) {
			Node result = hillClimbing();
			// Optimal found.
			if (result.getValue() == 0) {
				System.out.println(i);
				return result;
			}
			if (cuurentNoOfAttacks > result.getValue()) {
				current = result.getState();
				cuurentNoOfAttacks = result.getValue();
			}
		}
		return new Node(current, cuurentNoOfAttacks);
	}

	/*
	 * the function implements local search, with sideways moves to over come
	 * shoulder.
	 * 
	 * @return the best state that it can reach in its search space.
	 */
	public Node hillClimbingWithSidewayMoves() {
		steps = 0;
		int noOfSideMoves = 0;
		// Initial Node.
		Node current = new Node();
		boolean found = false;
		while (!found) {
			// Ever iteration is a step.
			steps++;
			// Find the highest valued successor of current.
			Node neighbor = current.getBestSuccessor();
			// Found optimal solution.
			if (neighbor.getValue() == 0) {
				System.out.println(steps);
				return neighbor;
			}
			// If neighbor is worst than current(bigger number of attacks), I'm
			// not improving anymore so return current.
			if (neighbor.getValue() > current.getValue())
				return current;
			// Moving Sideways.
			else if (neighbor.getValue() == current.getValue()) {
				noOfSideMoves++;
				if (noOfSideMoves > LOOPINGLIMITS)
					return current;
			} else { // The value is better than current value.
				noOfSideMoves = 0;
			}
			current = neighbor;
		}
		return current;
	}

	/*
	 * the function implements k-local beams search.
	 * 
	 * @return the best state that it can reach in its k beams.
	 */
	public Node kBeams() {
		steps = 0;
		SortedMap<Integer, State> k_States = new TreeMap<Integer, State>();
		SortedMap< Integer, State> nextGeneration = new TreeMap<Integer, State>();
		// Generate k random state.
		for (int i = 0; i < k; i++){
			State s = new State(); 
			int sNoOfAttacks = s.calculatenumberOfAttacks();
			k_States.put(sNoOfAttacks, s);
		}
		while (true){
			steps++;
			// Find the next generation.
			Set<Integer> keys = k_States.keySet();
			for (Integer key : keys){
				State s = k_States.get(key);
				s.neighborWithlowestAttachNo(true);
				ArrayList<State> neighbors = s.getNeighbors();
				for(State neighbor : neighbors) {
					nextGeneration.put(neighbor.calculatenumberOfAttacks(), neighbor);
				}
			}
			// If the optimal solution is reached.
			if(nextGeneration.firstKey() == 0){
				System.out.println(steps);
				return new Node(nextGeneration.get(nextGeneration.firstKey()),
					nextGeneration.firstKey());
			}
			// If the best state in nextGeneration is worst than the 
			//best state in the current generation, stop.
			if (k_States.firstKey() <= nextGeneration.firstKey()){
				return new Node (k_States.get(k_States.firstKey()), k_States.firstKey().intValue());
			} else {
				Set<Integer> newkeys = nextGeneration.keySet();
				k_States = new TreeMap<Integer, State>();
				int counter = 0;
					for (Integer key : newkeys){
						if (counter == k) break;
						k_States.put(key, nextGeneration.get(key));
						counter++;
				}
			}
		}
	}

}
