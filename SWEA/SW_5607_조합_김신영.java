import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SW_5607_조합
 * 77,564KB	428ms
 * ---------- ----------
 * 
 * </pre>
 * @author 김신영
 *
 */

public class SW_5607_조합_김신영 {

	static int N, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(tc).append(" ").append(nCr(N, R, 1234567891)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	// 페르마의 소정리 
	static long nCr(int n, int r, int p) {
		if(r == 0) {
			return 1L;
		}
		
		long[] fac = new long[n+1];
		fac[0] = 1;
		
		for(int i = 1; i <= n; i++) {
			fac[i] = fac[i-1] * i % p;
		}
		
		return (fac[n] * power(fac[r], p-2,p) % p * power(fac[n-r], p-2, p) % p) % p;
	}
	
	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		
		while(y > 0) {
			if(y % 2 == 1) {
				res = (res * x) % p;
			}
			y = y >> 1;
			x = (x * x) % p;
		}
		
		return res;
	}

}
