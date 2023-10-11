import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 19,224KB	102ms
 * ---------- ---------- 
 * 나무가 자랄 수 있는 최소 날짜 구하기 
 * 홀수는 1, 짝수는 2 자람 | 물을 항상 줘야하는것은 아님
 * ---------- ---------- 
 * 나무들 중 가장 높은 높이 찾는다. 
 * -> 자라야하는 높이를 짝수, 홀수로 구한다.
 * </pre>
 * @author SSAFY
 *
 */

public class SW_14510_나무높이_김신영 {

	static int N;
	static int[] trees;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = 0;

			trees = new int[N];
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());

				max = Math.max(max, n);
				trees[i] = n;
			}

			// 짝수, 홀수 개수 세기
			int oddCnt = 0, evenCnt = 0;
			for (int i = 0; i < N; i++) {
				int tmp = max - trees[i];

				evenCnt += tmp / 2;
				oddCnt += tmp % 2;
			}

			// 짝수, 홀수 개수 균등하게 맞추기
			// 짝수 1은 홀수 2
			while (oddCnt + 1 < evenCnt) {
				evenCnt--;
				oddCnt += 2;
			}

			
			int answer = 0;
			if (oddCnt == evenCnt) { // 값이 같으면 두개 더한 값이 최소
				answer = oddCnt + evenCnt;
			} else if (oddCnt > evenCnt) {
				// 더 적은수만큼 먼저 채워주고 나머지 홀수 키우기
				answer = evenCnt * 2 + (oddCnt - evenCnt) * 2 - 1;
			} else {
				// if(odd+1 == even)
				// odd == even - 1이므로 even이 1개 많음 -> 하루 추가
				answer = oddCnt + evenCnt + 1;
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

}
