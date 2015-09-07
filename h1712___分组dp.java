import java.io.*;
import java.util.*;

public class h1712___����dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {

		int n, m, cost[][], dp[], V = 10000;
		while (true) {
			n = nextInt();
			m = nextInt();
			if (n == 0 && m == 0)
				break;
			cost = new int[n + 1][m + 1];
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= m; j++)
					cost[i][j] = nextInt();

			// ����dp
			// dp[i][v]��ʾǰi�Ź���ѧ��v����������
			// dp[i][v]=Math.max(dp[i][v],dp[i-1][v-d])
			// d��ʾǰi�Ź���ѧ��v���������� ��������ǰi��ѧ��v������棬��ǰi��ѧ��v-d�� �� ��i��ѧ��d�������ȡ���ֵ
			// ����ķ��飨Ҳ����ÿһ�����໥��ͻ����û�Ź���ѧϰ�Ĵ���������ÿһ�Ź��ξ���һ��

			dp = new int[m + 1];
			for (int i = 1; i <= n; i++) {
				for (int v = m; v >= 0; v--) {
					for (int d = 1; d <= m; d++)
						if (v - d >= 0)
							dp[v] = Math.max(dp[v], dp[v - d] + cost[i][d]);
				}
			}

			out.println(dp[m]);
		}

		out.flush();
		out.close();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}