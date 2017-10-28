package fouthYear.AI;


public class launcher {

	public static final int queensNumber = 8;
	public static void printBoard(State ss) {
		String result="";
		String[][] board = new String[queensNumber][queensNumber];
		for(int i=0; i<queensNumber; i++)
			for(int j=0; j<queensNumber; j++)
				board[i][j]="X ";
		//place the queens on the board
		for(int i=0; i<queensNumber; i++){
			board[i][ss.getQueenInRow(i).getColunmnNumber()]="Q ";
		}
		//feed values into the result string
		for(int i=0; i<queensNumber; i++){
			for(int j=0; j<queensNumber; j++){
				result+=board[i][j];
			}
			result+="\n";
		}
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		SearchMethods s = new SearchMethods();
		// the number of steps is printed in side the 
		//methods when the optimal solution is reached.
		for (int i = 0; i < 100; i++) {
			Node s1 = s.hillClimbing();
		}
		for (int i = 0; i < 100; i++) {
			Node s2 = s.randomRestart();
		}
		for (int i = 0; i < 100; i++) {
			Node s3 = s.hillClimbingWithSidewayMoves();			
		}
		for (int i = 0; i < 100; i++) {
			Node s4 = s.kBeams();
		}

	}
}