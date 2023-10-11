import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * <pre>
 * SW_5658_보물상자비밀번호
 * 21,164KB 125ms
 * ---------- --------
 * 보물상자의 뚜껑은 시계방향으로 돌릴 수 있음
 * 시계방향순으로 높은 자리 숫자에 해당
 * 적힌 숫자로 만들 수 있는 수 중 K번째로 큰 수 찾기
 * ---------- --------
 * 문자열 4로 나눠서 만들 수 있는 숫자 모두 만들기
 * 중복확인 -> Set 사용
 * </pre>
 * 
 * @author 김신영
 *
 */
public class SW_5658_보물상자비밀번호_김신영 {

	static int N, K;
	static String S;
	static List<Integer> list;
	static Set<String> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			S = br.readLine();
			set = new HashSet<>();
			
			// 가능한 숫자 만들기
			int size = N / 4;
			for (int i = 0; i < N; i++) {
				String tmp = "";
				for (int j = 0; j < 4; j++) {
					int start = i + size * j;
					int end = i + size * j + size;
					if (end <= N) {
						tmp = S.substring(start, end);
					} else {
						if (start > N) {
							tmp = S.substring(start - N, end - N);
						} else {
							tmp = S.substring(start) + S.substring(0, end - N);
						}
					}

					// 중복된 값 아니면 set에 값 추가하고 리트스에 변환된 10진수 넣기
					if (!set.contains(tmp)) {
						set.add(tmp);
						list.add(convert(tmp));
					}
				}
			}
			
			// K번째로 큰 수 찾기 위해 정렬
			Collections.sort(list);
			
			// 출력
			sb.append("#").append(tc).append(" ").append(list.get(list.size()-K)).append("\n");
		}
		
		System.out.println(sb);
	}

	// 16진수 -> 10진수 변환
	public static int convert(String s) {
		int num = 0;

		for (int i = 0; i < s.length(); i++) {
			char tmp = s.charAt(i);
			int minus = 48;
			
			// 알파벳인 경우
			if (tmp >= 65 && tmp <= 70) {
				minus = 55;
			}

			num += Math.pow(16, s.length() - i - 1) * (tmp - minus);
		}

		return num;
	}

}
