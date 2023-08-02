import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ 1244 스위치 켜고 끄기 https://www.acmicpc.net/problem/1244
 * 남학생: 자신이 받은 수의 배수의 스위치 상태 바꾼다.
 * -> 받은 수의 배수에 해당되는 위치의 값을 반복문을 돌며 바꿔준다.
 * 여학생: 자신이 받은 수를 중심으로 대칭이 가장 많이 이루어지는 구간까지의 스위치 상태를 모두 바꾼다.
 * -> 받은 수 기준으로 -n칸, +n칸의 값이 같은 경우 이 위치들의 값을 바꾸고, 그렇지 않은 경우 다음으로 넘어간다.
 * 스위치 상태 변화 순서: 입력 순서대로
 * 출력: 마지막 상태 출력
 * 11620KB, 80ms
 * </pre>
 * @author 김신영
 *
 */
public class BJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchNum = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 전구 상태 저장하는 배열
		int[] arr = new int[switchNum];
		// 입력받음
		for (int i = 0; i < switchNum; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine());
		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());		// 성별 
			int loc = Integer.parseInt(st.nextToken()) - 1;	// 위치 (배열 인덱스)
			if (sex == 1) { // 남자
				// loc만큼 더해가며 계산한다. (loc+1) -> loc를 배열 인덱스로 잡았기 때문에 +1 해준다.
				for (int j = loc; j < switchNum; j += (loc+1)) {
					arr[j] = (arr[j] + 1) % 2;
				}
			} else { // 여자
				arr[loc] = (arr[loc] + 1) % 2;	// 기준이 되는 지점 바꿔준다.
				for (int j = 1; j < switchNum / 2; j++) {
					// 인덱스 범위 안이고
					if (loc + j < switchNum && loc - j >= 0) {
						// 대칭이면 값 변경시켜준다.
						if (arr[loc + j] == arr[loc - j]) {
							arr[loc + j] = (arr[loc + j] + 1) % 2; 
							arr[loc - j] = (arr[loc - j] + 1) % 2; 
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
		
		// 한 칸 띄고 출력
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
			if(i % 20 == 19) {	// 한줄에 20개만 출력
				System.out.println();
			}
		}
	}

}
