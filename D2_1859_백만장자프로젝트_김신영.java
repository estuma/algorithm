import java.util.*;
import java.io.*;

/**
 * SWEA 1859 백만 장자 프로젝트 https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc&categoryId=AV5LrsUaDxcDFAXc&categoryType=CODE&problemTitle=%EB%B0%B1%EB%A7%8C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 * 테스트케이스 실행 시간 : 0.13173s
 * @author 김신영 
 * 구매는 하루에 하나, 판매는 마움대로 할 수 있음
 * 최대의 이익을 내는 방법
 * 가격 n이 나오는 마지막 날짜를 저장한 hashMap과 정렬된 가격을 가지고
 * 현재 가격보다 비싸게 팔 수 있는 날이 있다면 그 날과의 값 차이를 계산한다.
 */

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			long answer = 0L;
			int days = sc.nextInt();
			int[] arr = new int[days];
			// <가격, 마지막 날짜>
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

			// 가격 입력받기, map에 가격과 그 가격이 되는 날짜 입력
			for (int i = 0; i < days; i++) {
				arr[i] = sc.nextInt();
				map.put(arr[i], i);
			}

			// 가격순 정렬된 배열 만들기 (가격)
			int[] arr2 = arr.clone();
			Arrays.sort(arr2);

			// arr: 날짜순 arr2: 가격순 정렬
			for (int i = 0; i < days; i++) { // 날짜 0~
				for (int j = days - 1; j > 0; j--) { // 가격 찾기
					// 현재 날짜 arr[i] 기준으로 더 비싸게 파는 날이 있으면
					if (arr2[j] > arr[i] && map.get(arr2[j]) > i) {
						answer += (long) arr2[j] - (long) arr[i]; // 값에 더해준다.
						break; // 현재 날짜 검색 종료
					}
					// 현재 기준으로 더 비싸게 팔 수 있는 날이 없으면 다음날로 넘어감
					if (arr2[j] < arr[i])
						break;
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}
}