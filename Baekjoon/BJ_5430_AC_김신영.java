import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_5430_AC_김신영 | https://www.acmicpc.net/problem/5430
 * 77736KB	536ms
 * ---------- ----------
 * error case caution
 * front, rear pointer 사용
 * 1. 정방향 -> D가 나오는 경우 front++
 * 2. 역방향 -> D가 나오는 경우 rear--
 * => font, rear 역전되면 error
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_5430_AC_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int T = 0; T < TC; T++) {
			String order = br.readLine();
			int p = Integer.parseInt(br.readLine());
			int[] arr = new int[p];

			String tmp = br.readLine();
			StringTokenizer st = new StringTokenizer(tmp.substring(1, tmp.length() - 1), ",");
			for (int i = 0; i < p; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// D: 버리기 R: 뒤집기
			int start = 0;
			int end = p;
			boolean direction = true; // true: 정방향, false: 역방향

			// 핵심 로직
			for (int i = 0; i < order.length(); i++) {
				if (order.charAt(i) == 'R') {
					direction = !direction;
				} else {
					if (direction) {
						start++;
					} else {
						end--;
					}
				}
			}
		
			// start가 end보다 커지면 error -> 버릴게 없는데 버린것임
			if (start > end) {
				sb.append("error").append("\n");
				continue;
			}

			// 출력 형식 반들기
			sb.append("[");
			if (direction) {
				// 정방향인경우
				for (int i = start; i < end; i++) {
					sb.append(arr[i]);
					if (i != end - 1) {
						sb.append(",");
					}
				}
			} else {
				// 역방향인경우
				int idx = 0;
				for (int i = end - 1; i >= start; i--) {
					sb.append(arr[i]);
					if (i != start) {
						sb.append(",");
					}
				}
			}

			sb.append("]\n");
		}

		System.out.println(sb.toString());
	}

}
