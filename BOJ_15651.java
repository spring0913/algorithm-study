import java.io.*;

public class Main {

	private static int N, M;
	private static StringBuilder sb;
	
	public static void dfs(int[] arr, int index) {
		if(index == M) {
			
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			arr[index] = i;
			dfs(arr, index+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		sb = new StringBuilder();
		int[] arr = new int[N];
		
		dfs(arr, 0);
		
		System.out.println(sb.toString());
	}

}
