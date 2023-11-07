import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * D4_1486_장훈이의높은선반
 * 18,644kb 105ms
 * ---------- ----------
 * 부분집합 이용
 * 만들 수 있는 부분집합을 모두 만들어 합이 B이상인 경우 높이를 찾는다
 * 이후에 높이가 B에 가장 가까운 값으로 min값을 갱신하며 계속 반복
 * </pre>
 * 
 * @author 김신영
 *
 */

public class D4_1486_장훈이의높은선반_김신영 {

	static int N, B, min;
	static int[] hight;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// N: 직원수, B:탑 높이의 최솟값
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// 직원의 키 입력
			hight = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				hight[i] = Integer.parseInt(st.nextToken());
			}

			// 높이가 B이상이면서 가장 작은 값 구하기
			min = Integer.MAX_VALUE;
			powerSet(0, 0, 0);

			System.out.println("#" + tc + " " + (min - B));
		}
	}

	// 부분집합 구하기
	public static void powerSet(int cnt, int start, int sum) {
		// N까지 모두 돌아 부분집합 생성했으면
		if (start == N) {
			// B보다 큰 값 중에 min값 찾기
			if (sum >= B) {
				min = Math.min(min, sum);
			}
			return;
		}

		powerSet(cnt + 1, start + 1, sum + hight[start]);
		powerSet(cnt, start + 1, sum);
	}

}
