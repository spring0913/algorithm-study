class Solution
{
    public int solution(int [][]board)
    {
        for(int i=1; i<board.length; i++) {
			for(int j=1; j<board[i].length; j++) {
				if(board[i][j] == 1) {
					int min = Math.min(board[i-1][j-1], board[i-1][j]);
					min = Math.min(min, board[i][j-1]);
					board[i][j] = min + 1;
				}		
			}
		}
		
		int max = board[0][0];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				max = Math.max(max, board[i][j]);		
			}
		}

        return max*max;
    }
}