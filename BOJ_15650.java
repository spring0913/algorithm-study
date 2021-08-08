import java.io.*;

public class Main {

	private static int N, M;
	private static StringBuilder sb;
	
	public static void dfs(int[] arr, int index, int target) {
		if(index == M) {
			
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		if(target == N+1) {
			return;
		}
		
		arr[index] = target;
		dfs(arr, index+1, target+1);
		dfs(arr, index, target+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		sb = new StringBuilder();
		int[] arr = new int[N];
		
		dfs(arr, 0, 1);
		
		System.out.println(sb.toString());
	}

}
