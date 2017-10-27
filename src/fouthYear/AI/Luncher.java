package fouthYear.AI;


public class Luncher {

	public static void p(State ss) {
		String result="";
		String[][] board = new String[queensNumber][queensNumber];
		
		for(int i=0; i<queensNumber; i++)
			for(int j=0; j<queensNumber; j++)
				board[i][j]=". ";
		
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
	
	public static final int queensNumber = 8;
	public static void main(String[] args) {
		SearchMethods s = new SearchMethods();
		Node ss = s.localSearch();
		System.out.println("Luncher ***** no of attacks " + ss.getValue());
		p(ss.getState());
	}

}
