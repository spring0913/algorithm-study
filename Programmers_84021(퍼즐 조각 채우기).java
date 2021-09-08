import java.util.*;

class Solution {

    private final int[] dh = {-1, 0, 1, 0};
    private final int[] dw = {0, 1, 0, -1};
    
    public int solution(int[][] game_board, int[][] table) {
        ArrayList<Puzzle> spaceList = new ArrayList<>();
        ArrayList<Puzzle> puzzleList = new ArrayList<>();

        for(int i=0; i<game_board.length; i++) {
            for(int j=0; j<game_board.length; j++) {
                if(game_board[i][j] == 0) {
                    spaceList.add(bfs(new Space(i, j), game_board, 0));
                }
            }
        }
        
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<table.length; j++) {
                if(table[i][j] == 1) {
                    puzzleList.add(bfs(new Space(i, j), table, 1));
                }
            }
        }
        
        int count = 0;
        for(int i=spaceList.size()-1; i>=0; i--) {
            for(int j=puzzleList.size()-1; j>=0; j--) {
                if(spaceList.get(i).equals(puzzleList.get(j))) {
                    count += spaceList.get(i).size;
                    spaceList.remove(i);
                    puzzleList.remove(j);
                    break;
                }
            }
        }
        
        return count;
    }
    
    public Puzzle bfs(Space space, int[][] board, int flag) {
        Queue<Space> q = new LinkedList<>();
        Puzzle puzzle = new Puzzle();
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        
        int h = space.getH();
        int w = space.getW();
        puzzle.addPuzzle(new Space(0, 0));
        q.offer(space);
        visited[h][w] = true;
        
        while(!q.isEmpty()) {
            Space s = q.poll();
            
            for(int i=0; i<4; i++) {
                int nh = dh[i] + s.getH();
                int nw = dw[i] + s.getW();
                if(nh >= 0 && nh < n && nw >= 0 && nw < n && !visited[nh][nw]) {
                    if(board[nh][nw] == flag) {
                        board[nh][nw] = (flag == 0)? 1 : 0;
                        visited[nh][nw] = true;
                        q.offer(new Space(nh, nw));
                        puzzle.addPuzzle(new Space(nh-h, nw-w));
                    }
                }
            }
        }
        
        return puzzle;
    }
    
    class Space implements Comparable<Space>{
        private int h;
        private int w;

        public Space(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setW(int w) {
            this.w = w;
        }

        @Override
        public boolean equals(Object obj) {
            Space s = (Space)obj;
            return s.h == this.h && s.w == this.w;
        }

        @Override
        public int compareTo(Space s) {
            if(s.getH() == this.h) {
                return Integer.compare(this.w, s.getW());
            }
            return Integer.compare(this.h, s.getH());
        }
    }
    
    class Puzzle{
        private ArrayList<Space> puzzle;
        private int size;

        public Puzzle(){
            this.puzzle = new ArrayList<>();
            size = 0;
        }

        public void addPuzzle(Space space) {
            puzzle.add(space);
            size++;
            Collections.sort(puzzle);
        }

        public int size() {
            return size;
        }

        public Space get(int index) {
            return puzzle.get(index);
        }

        @Override
        public boolean equals(Object obj) {
            Puzzle p = (Puzzle)obj;

            if(this.size != p.size) {
                return false;
            }

            for(int i=0; i<4; i++) {
                int count = 0;
                for(int j=0; j<p.size; j++) {
                    if(this.get(j).equals(p.get(j))) {
                        count++;
                    }
                }
                if(count == p.size) {
                    return true;
                }

                rotate();
            }

            return false;
        }

        public void rotate() {
            for(int i=0; i<puzzle.size(); i++) {
                int h = puzzle.get(i).getH();
                int w = puzzle.get(i).getW();

                puzzle.get(i).setH(-w);
                puzzle.get(i).setW(h);
            }

            Collections.sort(puzzle);

            int h = puzzle.get(0).getH();
            int w = puzzle.get(0).getW();
            for(int i=0; i<size; i++) {
                int ph = puzzle.get(i).getH();
                int pw = puzzle.get(i).getW();
                puzzle.get(i).setH(ph-h);
                puzzle.get(i).setW(pw-w);
            }
        }
    }
}