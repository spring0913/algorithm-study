import java.util.*;

public class Main {

	private static int[] arr;
	private static int N;
	private static int answer;
	
	public static void dfs(int weight, boolean[] visited, int index) {
		if(weight < 500) {
			return;
		}
		
		if(index == N-1) {
			answer++;
			return;
		}

		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(weight + arr[i], visited, index+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		answer = 0;
		N = sc.nextInt();
		int K = sc.nextInt();
		boolean[] visited = new boolean[N];
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			arr[i] -= K;
		}
		
		dfs(500, visited, 0);
		System.out.println(answer);
	}
}
