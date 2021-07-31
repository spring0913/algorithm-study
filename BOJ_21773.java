import java.util.*;

class Process implements Comparable<Process>{
	
	private int id;
	private int time;
	private int priority;
	
	public Process(int id, int time, int priority){
		this.id = id;
		this.time = time;
		this.priority = priority;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getTime(){
		return this.time;
	}
	
	@Override
	public int compareTo(Process p) {
		if(this.priority == p.priority) {
			return Integer.compare(this.id, p.id);
		}
		
		return Integer.compare(p.priority, this.priority);
	}
	
	public void execProcess() {
		this.time--;
		this.priority--;
	}
}

class ProcessQueue{
	private PriorityQueue<Process> pq;
	
	ProcessQueue(){
		pq = new PriorityQueue<>();
	}
	
	public void addProcess(Process p) {
		pq.offer(p);
	}
	
	public boolean isEmpty() {
		return (pq.size() == 0);
	}
	
	public int exec() {		
		Process currentProcess = pq.poll();
		currentProcess.execProcess();
		
		if(currentProcess.getTime() != 0) {
			pq.offer(currentProcess);
		}
		
		return currentProcess.getId();
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProcessQueue pq = new ProcessQueue();
		
		int T = sc.nextInt();
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			int id = sc.nextInt();
			int time = sc.nextInt();
			int priority = sc.nextInt();
			
			pq.addProcess(new Process(id, time, priority));
		}
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0 && !pq.isEmpty()) {
			sb.append(pq.exec() + "\n");
		}
		
		System.out.print(sb.toString());
	}

}
