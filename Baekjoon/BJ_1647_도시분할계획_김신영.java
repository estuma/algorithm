import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1647_도시분할계획
 * 324,120KB	1,428ms
 * ---------- ----------
 * N개의 마을과 M개의 길로 이루어져있음
 * 분리된 두 마을은 연결될 필요가 없다.
 * 마을안에서는 모든 집이 연결되어있기만 하면 된다.
 * => 남은 길의 유지비가 최소가되도록 한다. 유지비의 합의 최솟값 출력
 * ---------- ----------
 * A에서 시작하는 MST 구하기 -> 크루스칼 알고리즘 이용 (union-find)
 * 가장 마지막에 추가하는 간선이 비용이 가장 크므로 -1개까지 간선 구함
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_1647_도시분할계획_김신영 {

	static int N, M;
	static int[] parents;
	
	static class Node implements Comparable<Node>{
		public int from, to, price;
		
		
		public Node(int from, int to, int price) {
			this.from = from;
			this.to = to;
			this.price = price;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.price - o.price;
		}

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N];
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int price = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(from, to, price));
		}
		
		int cnt = 0;
		int answer = 0;
		while(cnt < N-2) {
			Node curr = pq.poll();
			
			// true -> 연결 | false -> 넘어감
			if(union(curr.from, curr.to)) {
				cnt++;
				answer += curr.price;
				System.out.println(curr);
			}
		}
		
		System.out.println(answer);
	}

	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
				
		if(pa == pb) {
			return false;
		}
		
		parents[pa] = pb;
		return true;
	}
	
}
