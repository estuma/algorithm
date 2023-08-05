import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * 암호생성기
 * 첫 번째 수를 1 감소시키고 맨 뒤로 보내고, 2 감소시키고 맨 뒤로 보내는 것이 5까지 진행
 * 숫자가 감수할 때 값이 0보다 작아지는 경우 값은 0이고, 프로그램이 종료됨
 * 큐를 사용해서 사이클 5마다 반복되게 앞에서 빼고 뒤로 넣는 작업 반복하면 된다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class D3_암호생성기_김신영 {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;

		for (int test_case = 1; test_case <= 10; test_case++) {
			T = sc.nextInt();
			// 암호를 생성하는 과정을 진행하기 위한 큐 선언
			Queue<Integer> queue = new ArrayDeque<>();
			// 정답 생성할 변수
			StringBuilder sb = new StringBuilder();

			// 입력값 받기
			for (int i = 0; i < 8; i++) {
				queue.offer(sc.nextInt());
			}

			// 0이 나올때까지 반복
			boolean zero = false;
			while (!zero) {
				// 사이클 5 반복
				for (int i = 1; i <= 5; i++) {
					// 제일 앞에 값을 빼고
					int tmp = queue.poll();
					// 현재 사이클의 순서번째만큼 뺀 값을 다시 뒤로 넣는다.
					if (tmp - i > 0) // 만약 0보다 크면 계속 진행
						queue.offer(tmp - i);
					else { // 0보다 작은 값이 나오면 암호생성을 종료한다.
						queue.offer(0);
						zero = true;
						break;
					}
				}
			}

			// 큐 순회하며 답 생성
			while (queue.size() > 0) {
				sb.append(queue.poll() + " ");
			}

			System.out.println("#" + T + " " + sb);
		}
	}
}