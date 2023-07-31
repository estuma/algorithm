import java.io.*;
import java.util.*;

/**
 * 백준 13300 방배정 https://www.acmicpc.net/problem/13300
 * 11764KB 88ms
 * @author 김신영
 * 같은 학년의 같은 성별끼리 같은 방 배정
 * 최대 인원수가 제한되어있음
 * -> 학년과성별을 key값으로하여 인원 세고
 * 인원수만큼 나눠서 방 개수 구한다.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	// 인원수
		int k = Integer.parseInt(st.nextToken());	// 한 방의 최대 인원수
		Map<String, Integer> map = new HashMap<>(n);// 학년성별 인원수 저장할 변수
		int answer = 0;

		// 사용자 입력 받으면서 인원수 세기
		for (int i = 0; i < n; i++) {
			String tmp = br.readLine();
			if (map.containsKey(tmp)) {	// 키가 존재하면
				map.replace(tmp, map.get(tmp) + 1);	// 인원수 +1
			} else {	// 키가 존재하지 않으면
				map.put(tmp, 1);	// 새로 넣어준다. 초기값 1명
			}
		}

		// 키셋으로 받아와 값 검색
		Set<String> set = map.keySet();
		for (String s : set) {
			// 나누어 떨어지지 않으면 방이 하나 더 필요함
			answer += map.get(s) / k;
			if(map.get(s) % k != 0) {
				answer++;
			}
		}

		// 값 출력
		System.out.println(answer);
	}

}
