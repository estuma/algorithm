import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_21924_도시건설_김신영 | https://www.acmicpc.net/problem/21924
 * 184412KB	1160ms
 * ------ ------ ------
 * 무향그래프 + 최소 신장 트리(크루스칼)
 * 건물의 개수 N
 * ------ ------ ------
 * 모든 도로를 다 설치하는데 드는 비용 - 최소 비용
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_21924_도시건설_김신영 {

	static int N, M;
	static int[] parents;

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}

		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aParents = find(a);
		int bParents = find(b);

		if (aParents == bParents) {
			return false;
		}

		parents[aParents] = bParents;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		long total = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		// 길입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			total += weight;

			pq.add(new int[] { from, to, weight });
		}
		
		long min = 0;
		int cnt = 0;
		
		// MST 만들기
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();

			if(union(curr[0], curr[1])) {
				cnt++;
				min += curr[2];
			}
			
			if(cnt == N - 1) {
				break;
			}
		}

		if(cnt == N-1) {
			System.out.println(total - min);
		} else {
			System.out.println(-1);
		}
	}

}
