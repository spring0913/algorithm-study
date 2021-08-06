import java.util.*;

public class Main {

	private static ArrayList<LinkedList<Integer>> gearList = new ArrayList<>();
	
	public static boolean compareElectrode(int before, int after) {
		if(gearList.get(before).get(2) == gearList.get(after).get(6)) {
			return true;
		}
		return false;
	}
	
	public static void rotate(int num, int flag) {
		if(flag == 0) {
			return;
		}
		
		if(flag == 1) {
			gearList.get(num).addFirst(gearList.get(num).removeLast());
		}else {
			gearList.get(num).addLast(gearList.get(num).removeFirst());
		}
	}
	
	public static int calculateScore() {
		int score = 0;
		
		for(int i=0; i<4; i++) {
			if(gearList.get(i).get(0) == 1) {
				score += 1<<i;
			}
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
			LinkedList<Integer> gear = new LinkedList<>();
			
			for(int j=0; j<8; j++) {
				gear.add(input[i].charAt(j) - '0');
			}
			
			gearList.add(gear);
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
