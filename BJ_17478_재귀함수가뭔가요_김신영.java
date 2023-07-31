import java.util.Scanner;

/**
 * 반복: 재귀함수가~찾아와서 물었어
 * 종료조건: 재귀함수가~호출하는 함수라네
 * 재귀 호출 후: 라고 답변하였지.
 * 14084KB, 132ms
 * @author 김신영
 *
 */
public class BJ17478 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recursion(N, N);
	}
	
	public static void recursion(int depth, int n) {
		StringBuilder sb = new StringBuilder();
		// 밑줄 만들기
		for(int i = 0; i < n - depth; i++) {
			sb.append("____");
		}
		
		// 종료조건
		if(depth == 0) {
			System.out.println(sb + "\"재귀함수가 뭔가요?\"");
			System.out.println(sb + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(sb + "라고 답변하였지.");
			return;
		}
		
		// 반복호출할 문장
		System.out.println(sb + "\"재귀함수가 뭔가요?\"");
		System.out.println(sb + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(sb + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(sb + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		// 재귀 호출
		recursion(depth - 1, n);
		// 종료 후 출력
		System.out.println(sb + "라고 답변하였지.");
	}
}
