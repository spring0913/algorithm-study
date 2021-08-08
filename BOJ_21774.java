import java.io.*;
import java.util.*;

public class Main {
	
	private final static int MAX_LOG_LEVEL = 6;
	private static ArrayList<ArrayList<String>> logList;
	
	public static int lowerBound(String date, int logLevel) {
		int left = 0;
		int right = logList.get(logLevel).size();
		while(left < right) {
			int mid = (left + right)/2;
			if(logList.get(logLevel).get(mid).compareTo(date) < 0) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		
		return right;
	}
	
	public static int upperBound(String date, int logLevel) {
		int left = 0;
		int right = logList.get(logLevel).size();
		while(left < right) {
			int mid = (left + right)/2;
			if(logList.get(logLevel).get(mid).compareTo(date) <= 0) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		
		return right;
	}
	
	public static void main(String[] args) throws Exception {
		logList = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int Q = Integer.parseInt(input[1]);
		
		for(int i=0; i<=MAX_LOG_LEVEL; i++) {
			logList.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			input = br.readLine().split("#");
			String date = input[0].replaceAll("[-: ]","");
			int logLevel = Integer.parseInt(input[1]);
			
			logList.get(logLevel).add(date);
		}
		
		for(int i=0; i<Q; i++) {
			input = br.readLine().split("#");
			String start = input[0].replaceAll("[-: ]","");
			String end = input[1].replaceAll("[-: ]","");
			int logLevel = Integer.parseInt(input[2]);
			
			int count = 0;
			for(int j=logLevel; j<=MAX_LOG_LEVEL; j++) {
				count += (upperBound(end, j) - lowerBound(start, j));
			}
			sb.append(count + "\n");
			
		}
		
		System.out.println(sb.toString());
	}
}
