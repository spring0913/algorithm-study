import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static StringBuilder sb;
	private static int[] num;
	
	public static void dfs(int[] arr, boolean[] visited, int index) {
		if(index == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				arr[index] = num[i];
				visited[i] = true;
				dfs(arr, visited, index+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		sb = new StringBuilder();
		num = new int[N];
		
		input = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(input[i]);
		}
		
		Arrays.sort(num);
		
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];
		dfs(arr, visited, 0);
		
		System.out.print(sb.toString());
	}

}
