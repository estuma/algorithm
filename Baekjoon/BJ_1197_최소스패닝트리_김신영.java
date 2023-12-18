import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1197_최소스패닝트리_김신영 | https://www.acmicpc.net/problem/1197
 * ----- ----- -----
 * MST (크루스칼 -> union-find)
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_1197_최소스패닝트리_김신영 {

	static int V, E;
	static int[] root;

	public static class Node implements Comparable<Node> {
		int start, to, weight;

		public Node(int s, int t, int w) {
			this.start = s;
			this.to = t;
			this.weight = w;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}

		@Override
		public String toString() {
			return start + ", " + to + ", " + weight;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 1 <= V <= 10,000 정점
		E = Integer.parseInt(st.nextToken()); // 1 <= E <= 100,000 간선

		root = new int[V];
		for (int i = 0; i < V; i++) {
			root[i] = i;
		}

		PriorityQueue<Node> queue = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			queue.add(new Node(from, to, weight));
			queue.add(new Node(to, from, weight));
		}

		int cnt = 0;
		long sum = 0;
		boolean[] isVisited = new boolean[V];
		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			if (!union(curr.start, curr.to)) {
				continue;
			}

			isVisited[curr.start] = true;
			cnt++;
			sum += curr.weight;

			if (cnt == V - 1) {
				break;
			}
		}

		System.out.println(sum);
	}

	public static int find(int a) {
		if (root[a] == a) {
			return a;
		}

		return root[a] = find(root[a]);

	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		root[aRoot] = bRoot;
		return true;
	}

}
