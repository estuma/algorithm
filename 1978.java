import java.util.*;
import java.io.*;

/**
 * 소수 찾기
 * @author 김신영
 * 1~1000인 1~100개의 숫자의 소수 판별
 * 에라토스테네스의 체 이용하여 판별
 * 소수 개수 세기
 */

public class Main {
	
	public static boolean[] isprime() {
		// 소수인지 결과 담는 배열
		boolean[] arr = new boolean[1001];
		// 올 수 있는 최대값
		int max = 1001;
		
		// true: 소수 아님 || false: 소수
		arr[1] = true;
		for(int i = 2; i < max; i++) {
			// 소수이면 이 수의 배수 모두 소수가 아닌걸로 표시
			if(arr[i] == false) {
				// 소수의 2배수부터 시작
				for(int j = i * 2; j < max; j += i) {
					arr[j] = true;
				}
			}
		}
		
		// 만들어진 배열 반환
		return arr;
	}
	
	public static void main(String[] args) throws IOException {
		// 사용자입력받기위해 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		// 입력받을 숫자 개수
		int n = Integer.parseInt(br.readLine());
		// 소수인지 판별하기 위한 배열, isprime함수로 초기화
		boolean[] arr = isprime();
		// 정답 담을 변수
		int answer = 0;
		
		// 입력받은 값을 공백 단위로 나누어 읽어들임
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			// 읽은 값이 소수이면 arr[i]가 false이면 카운트
			if(arr[Integer.parseInt(st.nextToken())] == false) {
				answer++;
			}
		}
	      
		// 정답 출력
	    System.out.println(answer);
	}
}