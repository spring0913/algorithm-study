import java.io.*;

public class Main {

	private static int N, M;
	private static StringBuilder sb;
	
	public static void dfs(int[] arr, boolean[] visited, int index) {
		if(index == M) {
			
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				arr[index] = i;
				visited[i] = true;
				dfs(arr, visited, index+1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		sb = new StringBuilder();
		int[] arr = new int[N];
		boolean[] visited = new boolean[N+1];
		
		dfs(arr, visited, 0);
		
		System.out.println(sb.toString());
	}

}
