import java.io.*;
import java.util.*;

public class h4532___���dp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 55, M = 510;
	static int arr[] = new int[N];
	static long dp[][] = new long[N][M], c[][] = new long[M][M],
			func[] = new long[N];
	static long mod = 1000000007;
	static int n;

	static void prework() {
		for (int i = 0; i < M; i++) {
			c[i][0] = c[i][i] = 1;
			for (int j = 1; j < i; j++)
				c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
		}
		func[0] = 1;
		for (int i = 1; i < N; i++)
			func[i] = (func[i - 1] * i % mod + mod) % mod;
	}

	static void init() {
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], 0);
	}

	public static long fastPow(long a, int p) {
		if (p == 0)
			return 1;
		if (p % 2 == 1) {
			long temp = fastPow(a, p / 2);
			return (temp * temp * a) % mod;
		} else {
			long temp = fastPow(a, p / 2);
			return (temp * temp) % mod;
		}
	}

	public static void main(String[] args) throws IOException {
		int ttt = nextInt();
		prework();
		for (int test = 1; test <= ttt; test++) {
			n = nextInt();
			for (int i = 1; i <= n; i++)
				arr[i] = nextInt();

			// i-1: 1 2 3 4 ... sum
			// i: 1 2 3 ... t
			// j= 0 ... sum-1
			// ��a[i]�ֳ�k���� c(a[i]-1,k-1)
			// ��ǰi-1���˵��У���j����ѡu���շ�u���� c(j,u) ʣ�µ�k-u���˾ͷŵ�����Ϸ���λ�� c(sum+1-j,k-u)
			// ��ô�µ����е��У�ʵ�����µĲ����������Ŀ� Ӧ����ԭ����j��ȥ���ĵ���u���ټ����µ�(a[i]-1)-(k-1)
			// ��Ϊj-u+a[i]-k
			// ÿ�����ڶ���p���˵���������Ҫ�ٳ���p�Ľ׳�
			init();
			int sum = arr[1], t;
			dp[1][arr[1] - 1] = 1;
			for (int i = 2; i <= n; i++) {
				t = arr[i];
				for (int j = 0; j < sum; j++)
					if (dp[i - 1][j] > 0)
						for (int k = 1; k <= t && k <= sum + 1; k++)
							for (int u = 0; u <= k && u <= j; u++) {
								dp[i][j + t - u - k] = (dp[i][j + t - u - k] + (((dp[i - 1][j]
										* c[j][u] % mod)
										* c[sum + 1 - j][k - u] % mod)
										* c[t - 1][k - 1] % mod))
										% mod;
							}
				sum += arr[i];
			}

			long ans = dp[n][0] % mod;
			for (int i = 1; i <= n; i++)
				ans = ans * func[arr[i]] % mod;
			ans = (ans + mod) % mod;

			out.println("Case " + test + ": " + ans);
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

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}