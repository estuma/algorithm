import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 원본 암호문을 받고
 * 명령어를 받아 인덱스 위체에서 새로운 값들을 추가해준다.
 * 중간에 값을 추가해야하므로 linkedList 사용
 * </pre>
 * @author 김신영
 *
 */

public class D3_SW문제해결기본8일차_암호문1_김신영 {

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int test_case = 1; test_case <= 10; test_case++) {            
            // 1. 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new LinkedList<>();
            // 2. 원본 암호문
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            // 3. 원본 암호문의 길이 N ( 10 ≤ N ≤ 20 의 정수)
            int M = Integer.parseInt(br.readLine());
            // 4. 명령어
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                st.nextToken();    // I
                int idx = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                for(int j = 0; j < num; j++) {
                	// 인덱스 위치에 새로운 숫자 추가
                    list.add(idx++, Integer.parseInt(st.nextToken()));
                }
            }
            // 정답 만들기
            sb.append("#" + test_case + " ");
            // 10번째까지 출력
            for(int i = 0; i < 10; i++) {
                sb.append(list.get(i) + " ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        
    }
}