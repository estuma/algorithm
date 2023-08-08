import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 스팟마트에는 N개의 과자봉지가 있음 -> 각 과자 봉지는 ai그램의 무게를 가짐.
 * 최대한 양이 많은 과자봉지를 고르면서
 * 과자 두 봉지의 무게가 M그램을 초과하지 않음
 * 한빈이는 정확히 과자를 두 봉지 사야함!
 * ---
 * 과자 2개를 골라 최대 M이 넘지 않으면 되므로 
 * 두 봉지를 골리 M이 넘지 않는 값 중에 최대값을 찾으면 된다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class D3_9229_한빈이와SpotMart_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 테스트케이스만큼 반복
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 과자 개수
			int M = Integer.parseInt(st.nextToken());	// 최대 무게
			int max = -1;	// 고를 수 없는 경우 -1을 출력하므로 초기값을 -1로 준다.

			// 과자 가격을 가지고 있는 배열
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 2개로 모든 조합을 비교해보면 된다.
			for (int i = 0; i < N - 1; i++) {
				for (int j = i+1; j < N; j++) {
					// 과자 두 봉지의 무게 합이 M이하인 경우 Max값과 비교검사하여 갱신해준다.
					int sum = arr[i] + arr[j];
					if (sum <= M) {
						max = Math.max(max, sum);
					}
				}
			}

			// 테스트케이스번호마다 정답 만들어 추가
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(max);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
