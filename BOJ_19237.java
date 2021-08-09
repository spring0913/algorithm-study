import java.util.*;
import java.io.*;

class Track{
	private int shark;
	private int time;
	
	public void setShark(int shark) {
		this.shark = shark;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getShark() {
		return shark;
	}
	
	public void passTime() {
		time--;
		
		if(time == 0) {
			shark = 0;
		}
	}
}

class Shark implements Comparable<Shark>{
	private int num;
	private int h;
	private int w;
	private int dir;
	private int[][] priority;

	private final static int dh[] = {0, -1, 1, 0, 0};
	private final static int dw[] = {0, 0, 0, -1, 1};

	public Shark(int num, int h, int w) {
		this.num = num;
		this.h = h;
		this.w = w;
		this.priority = new int[5][5];
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public void setPriority(int dir, int[] priority) {
		for(int i=0; i<priority.length; i++) {
			this.priority[dir][i] = priority[i];
		}
	}
	
	public int[] getPriority() {
		return priority[dir];
	}
	
	public int getNum() {
		return num;
	}
	
	public int getDir() {
		return dir;
	}
	
	public int getH() {
		return h;
	}
	
	public int getW() {
		return w;
	}
	
	public void move(int shark, int dir) {
		int nh = h+dh[dir];
		int nw = w+dw[dir];

		this.h = nh;
		this.w = nw;
		this.dir = dir;
	}
	
	@Override
	public boolean equals(Object obj) {
		Shark shark = (Shark)obj;
		if(this.h == shark.getH() && this.w == shark.getW()) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Shark o) {
		return Integer.compare(this.num, o.num);
	}
}

public class Main {

	private final static int dh[] = {0, -1, 1, 0, 0};
	private final static int dw[] = {0, 0, 0, -1, 1};
	private static int[][] map;
	private static Track[][] track;
	private static ArrayList<Shark> shark;
	private static int N, M, k;
	private static int count = 0;
	
	public static void passTime() {
		count++;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(track[i][j].getTime() > 0) {
					track[i][j].passTime();
				}
			}
		}
	}
	
	public static void spraySmell() {
		for(int i=0; i<shark.size(); i++) {
			int nh = shark.get(i).getH();
			int nw = shark.get(i).getW();
			
			track[nh][nw].setShark(shark.get(i).getNum());
			track[nh][nw].setTime(k);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		k = Integer.parseInt(input[2]);
		
		map = new int[N][N];
		shark = new ArrayList<>();
		track = new Track[N][N];
	
		for(int i=0; i<N; i++) {
			input = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				track[i][j] = new Track();
				
				if(map[i][j] != 0) {
					shark.add(new Shark(map[i][j], i, j));
					track[i][j].setShark(map[i][j]);
					track[i][j].setTime(k);
				}
			}
		}

		Collections.sort(shark);
		
		input = br.readLine().split(" ");
		for(int i=0; i<M; i++) {
			shark.get(i).setDir(Integer.parseInt(input[i]));
		}
		
		int[] priority = new int[4];
		for(int i=0; i<M; i++) {
			for(int j=1; j<=4; j++) {
				input = br.readLine().split(" ");

				for(int l=0; l<4; l++) {
					priority[l] = Integer.parseInt(input[l]);
				}
				shark.get(i).setPriority(j, priority);
			}
		}

		while(true) {
			for(int i=0; i<shark.size(); i++) {
				Shark s = shark.get(i);
				int dir = s.getDir();
				int[] dirPriority = s.getPriority();
				int h = s.getH();
				int w = s.getW();
				
				// 냄새가 없는 방향으로 이동
				boolean flag = false;
				for(int j=0; j<dirPriority.length; j++) {
					int nh = h + dh[dirPriority[j]];
					int nw = w + dw[dirPriority[j]];
					
					if(nh >= 0 && nh < N && nw >= 0 && nw < N) {
						if(track[nh][nw].getTime() == 0) {
							s.move(s.getNum(), dirPriority[j]);
							flag = true;
							break;
						}
					}
				}
				
				// 자신의 냄새가 있는 방향으로 이동
				if(!flag) {
					for(int j=0; j<dirPriority.length; j++) {
						int nh = shark.get(i).getH() + dh[dirPriority[j]];
						int nw = shark.get(i).getW() + dw[dirPriority[j]];
					
						if(nh >= 0 && nh < N && nw >= 0 && nw < N) {
							if(track[nh][nw].getShark() == s.getNum()) {
								s.move(s.getNum(), dirPriority[j]);
								break;
							}
						}
					}
				}
			}
			
			passTime();

			if(count > 1000) {
				System.out.println("-1");
				return;
			}
			
			// 한 칸에 상어가 여러 마리 있는 경우
			for(int i=0; i<shark.size(); i++) {
				for(int j=shark.size()-1; j>=i+1; j--) {
					if(shark.get(i).equals(shark.get(j))) {
						shark.remove(j);
						if(shark.size() == 1) {
							System.out.println(count);
							return;
						}
					}
				}
			}
			
			spraySmell();
		}
	}
}
