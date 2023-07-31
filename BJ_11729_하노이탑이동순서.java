import java.util.Scanner;

/**
 * <pre>
 * 하노이탑
 * n을 3으로 옮기려면
 * n-1개를 2로 옮기고
 * n을 3으로 옮긴다.
 * 종료조건: 원판이 1개남으면 -> 3번으로 옮기면 됨
 * </pre>
 * @author 김신영
 *
 */
public class BJ11729 {
	public static StringBuilder sb = new StringBuilder();
	public static int cnt = 0;
	
	public static void main(String[] args) {
		// 사용자 입력 n 받음
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 계산부
		hanoi(n, 1, 2, 3);
		
		// 출력부
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	public static void hanoi(int n, int from, int by, int to) {
		// 옮긴 횟수 세기
		cnt++;
		if(n == 1) {
			// 종료조건, 원판이 하나 남은 경우
			sb.append(from + " " + to + "\n");
			return;
		}
		
		// n-1개를 1번 장대에서 2번 장대로 옮김
		hanoi(n-1, from, to, by);
		// n번째 원판을 을 1번 장대에서 3번 장대로 옮김
		sb.append(from + " " + to + "\n");
		// n-1개를 2번 장대에서 3번 장대로 옮김
		hanoi(n-1, by, from, to);
	}
}
