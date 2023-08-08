import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1158_요세푸스문제
 * 13068KB	132ms
 * ---------- ----------
 * 요세푸스문제
 * 1부터 N번까지의 사람이 원을 이루어 앉아있고,
 * 양의 정수 K(<=N)이 주어지면 순서대로 K번째 사람 제거.
 * 제거 후 다음 사람이 1번째.
 * (N,K)인 요세푸스 순열 구하기
 * ---------- ----------
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_1158_요세푸스문제_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		// N, M 입력
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken())-1;

		// 1부터 N까지 리스트에  순서대로 넣기
		for(int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		// 정답 출력 양식
		sb.append("<");
		// 리스트에 1개 남을때까지
		while (list.size() > 1) {
			// 새로운 인덱스 설정
			// 원형 && M번째이므로 idx에 M 더하고 사이즈로 나눈다.
			idx = (idx + M) % (list.size());
			sb.append(list.get(idx)+ ", ");
			list.remove(idx);
		}
		sb.append(list.get(0));
		sb.append(">");
		
		System.out.println(sb);
	}

}
