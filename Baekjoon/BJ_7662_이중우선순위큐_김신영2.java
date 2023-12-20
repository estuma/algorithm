import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * <pre>
 * BJ_7662_이중우선순위큐_김신영 | https://www.acmicpc.net/problem/7662
 * 44,0180KB	2,684ms
 * ----- ----- -----
 * TreeMap
 * ----- ----- -----
 * TreeMap을 사용하면 오름차순으로 정렬된 값을 저장할 수 있음
 * 작은 값 -> 왼쪽 자식 노드 first..
 * 큰 값 -> 오른쪽 자식 노드 last..
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

			TreeMap<Integer, Integer> map = new TreeMap<>();
			
			for(int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				if(st.nextToken().equals("I")) {
					int curr = Integer.parseInt(st.nextToken());
					map.put(curr, map.getOrDefault(curr, 0) + 1);
				} else {
					if(map.isEmpty()) {
						continue;
					}
					
					int curr;
					if(st.nextToken().equals("1")) {
						curr = map.lastKey();
					} else {
						curr = map.firstKey();
					}
					
					if(map.put(curr, map.get(curr) - 1) == 1) {
						map.remove(curr);
					}
				}
			}
			
			if(map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}

		System.out.println(sb.toString());
	}

}
