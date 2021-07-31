import java.util.*;

public class Main {

	private static ArrayList<LinkedList<Integer>> wheelList = new ArrayList<>();
	
	public static boolean compareElectrode(int before, int after) {
		if(wheelList.get(before).get(2) == wheelList.get(after).get(6)) {
			return true;
		}
		return false;
	}
	
	public static void rotate(int num, int flag) {
		if(flag == 0) {
			return;
		}
		
		if(flag == 1) {
			wheelList.get(num).addFirst(wheelList.get(num).removeLast());
		}else {
			wheelList.get(num).addLast(wheelList.get(num).removeFirst());
		}
	}
	
	public static int calculateScore() {
		int score = 0;
		if(wheelList.get(0).get(0) == 1) {
			score += 1;
		}
		
		if(wheelList.get(1).get(0) == 1) {
			score += 2;
		}
		
		if(wheelList.get(2).get(0) == 1) {
			score += 4;
		}
		
		if(wheelList.get(3).get(0) == 1) {
			score += 8;
		}
		
		return score;
	}
	
	public static void exec(int num, int direction) {
		int[] flag = new int[4];
		
		flag[--num] = direction;

		// 오른쪽 톱니바퀴 회전
		for(int i=num+1; i<4; i++) {
			if(!compareElectrode(i-1, i)) {
				flag[i] = flag[i-1]*-1;
			}
		}
		
		// 왼쪽 톱니바퀴 회전
		for(int i=num; i>=1; i--) {
			if(!compareElectrode(i-1, i)) {
				flag[i-1] = flag[i]*-1;
			}
		}
		
		for(int i=0; i<4; i++) {
			rotate(i, flag[i]);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input = new String[4];
		
		for(int i=0; i<4; i++) {
			input[i] = sc.nextLine();
			LinkedList<Integer> wheel = new LinkedList<>();
			
			for(int j=0; j<8; j++) {
				wheel.add(input[i].charAt(j) - '0');
			}
			
			wheelList.add(wheel);
		}
		
		int K = sc.nextInt();
		for(int i=0; i<K; i++) {
			int num = sc.nextInt();
			int direction = sc.nextInt();
			
			exec(num, direction);
		}
		
		System.out.println(calculateScore());
	}

}
