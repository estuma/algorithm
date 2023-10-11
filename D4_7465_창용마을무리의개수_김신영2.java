import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4_7465_창용마을무리의개수
 * 25,268KB 132ms
 * ---------- ----------
 * N명의 사람 (1번~N번)
 * 서로를 알고 있을수도, 아닐수도 있음
 * 서로 아는 관계이거나 몇 사람을 걸쳐서 알 수 있는 관계이면 하나의 무리라고한다.
 * 몇 개의 무리가 있는지 계산하는 프로그램 작성
 * ---------- ----------
 * 
 * @author 김신영
 *
 */
public class D4_7465_창용마을무리의개수_김신영2 {

	static int N, M, answer;
	static boolean[] isVisited;
	static boolean[][] adj;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 부모노드 초기화
			parents = new int[N];
			for(int i = 0; i < N; i++) {
				parents[i] = i;
			}
			
			// 인접행렬 만들기
			adj = new boolean[N][N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				union(a, b);
			}

			for(int i = 0; i < N; i++) {
				find(i);
			}
			
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < N; i++) {
				set.add(parents[i]);
			}

			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}

		System.out.println(sb.toString());
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
