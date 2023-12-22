import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4_1251_하나로		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15StKqAQkCFAYD
 * 4,488kb	351ms
 * ----- ----- -----
 * PRIM 활용
 * 임의의 정점하나 선택하여 비용이 가장 적게 드는 경로 선택
 * 제곱할 때 숫자 조심
 * </pre>
 * 
 * @author 김신영
 *
 */

public class D4_1251_하나로_김신영 {

	// 섬 번호, 가중치 저장
	static class Land implements Comparable<Land> {
		int no;
		double weight;

		public Land(int no, double weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Land o) {
			return Double.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] land = new int[N][2]; // 좌표

			// 행 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				land[i][0] = Integer.parseInt(st.nextToken());
			}
			
			// 열 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				land[i][1] = Integer.parseInt(st.nextToken());
			}

			
			// 환경부담세율 E
			double E = Double.parseDouble(br.readLine());

			PriorityQueue<Land> pq = new PriorityQueue<>();
			boolean[] isVisited = new boolean[N];

			int cnt = 0;
			Double answer = 0.0;
			// 0번 섬부터 시작 (가중치 0)
			pq.offer(new Land(0, 0));
			while (!pq.isEmpty()) {
				Land curr = pq.poll();
				if (isVisited[curr.no])
					continue;

				// 가중치 더하기, 방문체크
				answer += curr.weight;
				isVisited[curr.no] = true;

				// N개 다 연결되면 종료
				if (++cnt == N)
					break;

				for (int i = 0; i < N; i++) {
					if (!isVisited[i]) {
						// 가로 세로 길이
						int a = Math.abs(land[curr.no][0] - land[i][0]);
						int b = Math.abs(land[curr.no][1] - land[i][1]);
						// 제곱하면 숫자가 커지므로 E를 같이 곱해서 오버플로우 방지
						double weight = E * a * a + E * b * b;
						pq.offer(new Land(i, weight));
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(Math.round(answer)).append("\n");
		}

		System.out.println(sb);
	}
}
