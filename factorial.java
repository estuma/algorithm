import java.util.*;
import java.io.*;

/**
 * 백준 10872 팩토리얼 
 * https://www.acmicpc.net/problem/10872
 * @author 김신영
 * 재귀로 구현하기
 * n이 0이 되면 1 반환하고
 * n값을 계속 곱해준다.
 * 0! = 1
 */

public class Main {
	
	public static int factorial(int n) {
		// n이 0이 되면 1 반환
		if(n == 0) {
			return 1;
		}
		
		// 반환된 값에 n 곱한값 반환
		return n * factorial(n-1);
	}
	
	public static void main(String[] args) throws IOException {
		// 사용자입력받기위해 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
		// n 입력
		int n = Integer.parseInt(br.readLine());
		// n! 계산
		int answer = factorial(n);
	      
		// 정답 출력
	    System.out.println(answer);
	}
}