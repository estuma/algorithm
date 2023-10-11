import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2239_스토쿠_김신영 {
	
	static char[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[9][9];
		for(int i = 0; i < 9; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
	}

}
