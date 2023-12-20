import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_7662_이중우선순위큐_김신영 | https://www.acmicpc.net/problem/7662
 * 459,000KB	3,672ms
 * ----- ----- -----
 * PriorityQueue + Map 사용..
 * ----- ----- -----
 * comparator 사용시 o1 - o2에서 overflow날 수 있음
 * map.remove(값) 사용할 경우 시간복잡도 O(N)으로 안됨
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_7662_이중우선순위큐_김신영 {

	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			K = Integer.parseInt(br.readLine());

			PriorityQueue<Integer> pq1 = new PriorityQueue<>();
			PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());

			Map<Integer, Integer> map = new HashMap<>();

			int qSize = 0;
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				if (st.nextToken().equals("I")) {
					qSize++;
					int curr = Integer.parseInt(st.nextToken());
					pq1.add(curr);
					pq2.add(curr);

					map.put(curr, map.getOrDefault(curr, 0) + 1);
				} else {
					if (qSize > 0) {

						if (st.nextToken().equals("-1")) {
							while (true) {
								int tmp = pq1.poll();

								if (map.get(tmp) > 0) {
									map.replace(tmp, map.get(tmp) - 1);
									break;
								}
							}
						} else {
							while (true) {
								int tmp = pq2.poll();

								if (map.get(tmp) > 0) {
									map.replace(tmp, map.get(tmp) - 1);
									break;
								}
							}
						}

						qSize--;
					}

				}

			}

			if (qSize == 0) {
				sb.append("EMPTY\n");
			} else {
				int max, min;
				while (true) {
					int curr = pq2.poll();
					if (map.get(curr) > 0) {
						max = curr;
						break;
					}

				}

				while (true) {
					int curr = pq1.poll();
					if (map.get(curr) > 0) {
						min = curr;
						break;
					}

				}
				sb.append(max).append(" ").append(min).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

}
