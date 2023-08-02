import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 길이 100인 땅의 평탄화 작업
 * 입력으로 주어지는 최대 작업 횟수만큼 반복하여
 * 최고지점 -> 최저지점으로 옮기는 작업을 한다.
 * 만일 최고와 최저가 1이하로 차이가 난다면 평탄화가 끝난것이므로 이를 종료조건으로 준다.
 * 값이 100으로 작으므로 수행시마다 sort 작업을 하여 최저점과 최고점 사이에서 값을 이동시킬 수 있다.
 * 27,460 kb, 146 ms
 * @author 김신영
 *
 */

class D3_Flattern
{
	public static void main(String args[]) throws Exception

	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int answer = 0;
            int max = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();		// Collection.sort를 사용하기 위해 List로 만듦
            
            // 각 땅의 높이 입력받기
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 100; i++) {
            	list.add(Integer.parseInt(st.nextToken()));
            }
            
            // 최대작업횟수만큼 반복
            for(int i = 0; i < max; i++) {
            	Collections.sort(list);	// 오름차순으로 정렬
            	// 종료조건: 차이가 1보다 작으면 평탄화가 끝난것!
            	if(list.get(0) + 1 >= list.get(list.size()-1)) {
            		break;
            	}
            	// 최저 + 1
            	list.set(0, list.get(0)+1);
            	// 최고 - 1
            	list.set(list.size()-1, list.get(list.size()-1)-1);
            }
            
            // 마지막으로 정렬 수
            Collections.sort(list);
            // 두 값 사이의 차를 계산
            answer = list.get(list.size()-1) - list.get(0); 
            
            // 답 출력
            System.out.println("#" + test_case + " " + answer);
		}
	}
}