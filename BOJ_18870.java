import java.lang.reflect.Array;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			set.add(arr[i]);
		}
		
		ArrayList<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			int index = N;
			int left = 0;
			int right = list.size()-1;
			while(left <= right) {
				int mid = (left + right)/2;
				
				if(arr[i] > list.get(mid)) {
					left = mid + 1;
				}else {
					right = mid - 1;
				}
			}
			sb.append(left + "\n");
		}
		System.out.println(sb.toString());
	}
}
