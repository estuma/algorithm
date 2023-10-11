import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SW_8458_원점으로집합
 * 31,264KB	196ms
 * ---------- ----------
 * 짝수만있거나, 홀수만 있는 경우 원점으로 집합 가능
 * 집합 가능한경우 -> 1, 2, 3씩 차례로 더한 값이 가장 멀리 있는 값 이상이 될 때 원점으로 집합 가능하다.
 * 더한 값과 max값의 차가 2의 배수인 경우 집합 가능
 * </pre>
 * @author 김신영
 *
 */

public class SW_8458_원점으로집합_김신영 {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			int oddCnt = 0, evenCnt = 0;
			boolean isValid = true;

			int max = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				// 원점으로부터 거리
				int diff = Math.abs(r) + Math.abs(c);
				
				// 홀수, 짝수 개수 세기
				if (diff % 2 == 0) {
					evenCnt++;
				} else {
					oddCnt++;
				}

				// 홀수 짝수가 모두 있는 경우 답이 나올 수 없음
				if (evenCnt > 0 && oddCnt > 0) {
					isValid = false;
				}

				// 최댓값 갱신
				max = Math.max(max, diff);
			}

			
			int answer = 0;
			if (!isValid) {
				answer = -1; // 모두 원점에 도달할 수 없는 경우
			} else {
				int sum = 0;
				int i = 0;
				while (true) {
					sum += i;

					// 현재 이동한 횟수가 max값 이상이고
					if (sum >= max) {
						// sum - max의 나머지가 짝수인 경우 도달 가능
						// 남은 수가 짝수이면 원점에서 한칸 이동 후 다시 원점으로 돌아올 수 있음
						if ((sum - max) % 2 == 0) {
							answer = i;
							break;
						}
					}

					i++;
				}

			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
