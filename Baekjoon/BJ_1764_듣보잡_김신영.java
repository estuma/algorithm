import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ_1764_듣보잡_김신영 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		TreeSet<String> ts = new TreeSet<>();
		Set<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		for(int i = 0; i < M; i++) {
			String curr = br.readLine();
			if(set.contains(curr)) {
				ts.add(curr);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(ts.size()).append("\n");
		
		Iterator<String> iterator = ts.iterator();
		while(iterator.hasNext()) {
			sb.append(iterator.next()).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
