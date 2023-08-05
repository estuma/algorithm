import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * D4_1218_SW문제해결기본4일차_괄호짝짓기
 * 17,428kb, 112 ms
 * ---------- 아이디어 ---------- 
 * 괄호가 모두 짝이 맞는지 검사하는것이므로 스택 이용
 * 주어진 문자열을 순차적으로 검사하며
 * 1. 여는괄효이면 스택에 넣고
 * 2. 닫는괄호이면 스택에서 값을 꺼내 짝이 맞는지 검사한다.
 * 3. 짝이 맞지 않으면 해당 테스트케이스 -> 값 유효하지 않음 처리
 * 4. 값 출력하고 반복
 * </pre>
 * @author 김신영
 *
 */
public class D4_1218_SW문제해결기본4일차_괄호짝짓기 {

	public static void main(String[] args) throws Exception {
		// 입력받기위한 변수 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 닫는 괄호와 여는괄효인지 검사하기 위해 선언
		// 짝이 되는 괄호끼리 인덱스 순서 동일하게 함
		List<Character> open = Arrays.asList('[', '{', '(', '<');
		List<Character> close = Arrays.asList(']', '}', ')', '>');
		
		// 테스트케이스 10개
		for (int test_case = 1; test_case <= 10; test_case++) {
			// 스택 생성
			Stack<Character> stack = new Stack<>();
			// 1. 첫줄 입력: 문자열 길이
			int n = Integer.parseInt(br.readLine());
			// 2. 두번째 줄 입력 : 괄호문자열
			String str = br.readLine();
			// 유효한지 아닌지 저장할 변수
			int answer = -1;

			// 문자열 길이만큼 검사
			for (int i = 0; i < str.length(); i++) {
				// 현재 검사하는 위치의 괄호가 여는 괄호이면
				if (open.contains(str.charAt(i))) {
					// 스택에 넣는다.
					stack.push(str.charAt(i));
				} 
				// 현재 검사하는 위치의 괄호가 닫는 괄호이면 스택에서 값을 하나 뽑아서 비교한다.
				else {
					// 스택에서 꺼내기 전에 스택에 값이 들어있는지 확인
					if (stack.size() > 0) {
						// 스택에서 값을 하나 꺼내고
						char tmp = stack.pop();
						// 리스트의 인덱스를 통하여 서로 짝이 맞는지 검사한다.
						if (open.indexOf(tmp) != close.indexOf(str.charAt(i))) {
							// 맞지 않으면 답을 0으로 설정하고 반복문 종료
							answer = 0;
							break;
						}
					} else {	// 스택에 값이 없으면 짝이 맞지 않는 것이므로 위와 마찬가지로 답을 0으로 설정하고 반복문 종료
						answer = 0;
						break;
					}
				}
			}
			
			// answer 변수가 0이 아니라면 유효하지 않은 경우를 만난적이 없는 것이므로 답 1
			if (answer == -1) {
				answer = 1;
			}

			// 답 출력
			System.out.println("#" + test_case + " " + answer);
		}
	}

}
