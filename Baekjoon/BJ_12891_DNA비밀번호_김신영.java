import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ 12891 DNA비밀번호 https://www.acmicpc.net/problem/12891
 * 40508KB	284ms
 * ---------- 입력 ----------
 * [ 1 ≤ |P| ≤ |S| ≤ 1,000,000 ]
 * S: DNA 문자열 길이
 * P: 부분문자열 길이
 * 부분문자열에 포함되어야 할 A, C, G, T의 최소 개수
 * ---------- 출력 ----------
 * 민호가 만들 수 있는 비밀번호 종류의 수
 * --------- 아이디어 ----------
 * 문자열 S가 최대 백만까지이므로 n^2은 불가능 1,000,000,000,000 => 1조
 * 문자열 S를 순차적으로 검사하면서 A, C, G, T 개수를 계산한다.
 * Map에 각각의 개수를 저장하고, 1칸씩 오른쪽으로 밀면서 
 * -> 더 이상 포함되지 않는 문자열 개수는 -1을 하고
 * -> 새로 포함되는 문자열 개수는 +1을 한다.
 * 새로 만들어진 문자열이 필요한 개수 이상으로 만들어진 문자열이면 카운트해준다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_12891_DNA비밀번호 {

	static int P, S;
	static int[] dnaAmount;
	static char[] dna = { 'A', 'C', 'G', 'T' };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		dnaAmount = new int[4];
		int answer = 0;
		Map<Character, Integer> map = new HashMap<>(4);
		String dnaStr = br.readLine();

		// MAP에 들어갈 수 있는 키가 정해져있으므로 이 값으로 초기화
		for(int i = 0; i < dna.length; i++) {
			map.put(dna[i], 0);
		}

		// A, C, G, T가 들어가야하는 최소 개수 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			dnaAmount[i] = Integer.parseInt(st.nextToken());
		}
		

		// 길이 P만큼 문자열의 각 DNA 개수 세기
		for (int i = 0; i < P; i++) {
			char tmp = dnaStr.charAt(i);
			map.replace(tmp, map.get(tmp) + 1);
		}
		
		// 위에서 만들어진 문자열이 가능한 문자열인지 확인
		if(isDnaStr(map)) {
			answer++;
		}
		
		// 한칸씩 더해가면서 개수 같은지 비교
		for (int i = P; i < S; i++) {
			char curr = dnaStr.charAt(i);
			char pre = dnaStr.charAt(i-P);
			if(curr != pre) {	// 서로 다르면 값 변경한다.
				map.replace(curr, map.get(curr)+1);
				map.replace(pre, map.get(pre)-1);
			}
			
			// 가능한 문자열이면 answer+1
			if(isDnaStr(map)) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	/**
	 * 비밀번호가 될 수 있는 문자열인지 판단하는 메소드
	 * @param map <Dna 문자, 개수>
	 * @return
	 */
	public static boolean isDnaStr(Map<Character, Integer> map) {
		// 현재 검사하는 문자열이 각 dna를 필요한만큼 가지고 있는지 검사
		for (int i = 0; i < dnaAmount.length; i++) {
			// 보다 적게 가지고 있으면 가능한 문자열이 아님
			if(map.get(dna[i]) < dnaAmount[i]) {
				return false;
			}
		}

		return true;
	}
}
