class Solution
{
    public int solution(int [][]board)
    {
		int max = board[0][0];
        for(int i=1; i<board.length; i++) {
			for(int j=1; j<board[i].length; j++) {
				if(board[i][j] == 1) {
					board[i][j] = Math.min(Math.min(board[i-1][j-1], board[i-1][j]), board[i][j-1]) + 1;
				}
				max = Math.max(max, board[i][j]);
			}
		}
		
        return max*max;
    }
}