import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * BJ_1786_찾기
 * 65,060KB	496ms
 * ---------- ----------
 * KMP 알고리즘 이용
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_1786_찾기_김신영 {

	static int len, answer;
	static int[] kmp;
	static String T, P;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = br.readLine();
		P = br.readLine();

		len = P.length();
		kmp = new int[len];
		answer = 0;
		list = new ArrayList<>();

		// i위치까지의 접미사 구하기
		int j = 0;	// 현재까지 일치한 부분 문자열
		for (int i = 1; i < P.length(); i++) {
			// 현재까지 일치한 부분 문자열에서 가능한한 일치하는 부분을 찾아냄
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = kmp[j - 1];
			}
			
			// 두 문자가 같으면 부분문자열 길이 증가
			if (P.charAt(i) == P.charAt(j)) {
				kmp[i] = ++j;
			}
		}

		int idx = 0;
		for (int i = 0; i < T.length(); i++) {
			// 문자가 다른 경우 idx값 갱신해주기
			// idx가 0이면 첫번째 문자와 비교하는 경우 -> 첫번째 문자가 같으면 다음 if문에서 비교 / 다르면 i++되어 P의 다음 문자와 비교
			// idx - 1인 이유는 -> 현재 비교하는 문자가 다르므로 -1번째까지 일치함 -> 부분일치테이블은 n번째까지 일치하는 경우에 대해 나와있음
			while (idx > 0 && T.charAt(i) != P.charAt(idx)) {
				idx = kmp[idx - 1];
			}

			if (T.charAt(i) == P.charAt(idx)) {
				// 패턴 완성된 경우 정답에 추가하고 인덱스값 변경
				if (idx == len - 1) {
					answer++;
					list.add(i - len + 2);
					idx = kmp[idx];
					if (i == T.length() - 1) {
						break;
					}
				}
				// 패턴 완성되지 않은 경우 다음 인덱스 배교
				else {
					idx++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(answer).append("\n");

		for (int i : list) {
			sb.append(i).append(" ");
		}

		System.out.println(sb);

	}

}
