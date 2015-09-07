import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class h4655___��ѧ___�����ƶ� {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	// ���е��������prod(ai)*n
	// Ȼ����Ǽ�ȥ�������ظ��ĸ���
	// ����ö������������ai��ai+1,prod(1..a-1)*prod(i+2..n)���ų�ai��ai+1�����п��ܳ��ֵĲ�ͬȾɫ����
	// ��ô�����ԵĿ���֪������ai��ai+1��ͬʱ�����ǵ�pieces�����
	// ���е�������ai��ai+1ʱ�����ǵ���Ҫ��ȥ��pieces��ĿӦ�þ���prod(1..ai-1)*min(ai,ai+1)*prod(i+2..n)
	// ע��Ӧ����ôд:prod(1..ai-1)*min(ai,ai+1)%mod*prod(i+2..n)%mod

	static int n;
	static long arr[], brr[];
	static long mod = 1000000007;
	static long left[], right[];

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		int test = nextInt();
		while (test-- > 0) {
			n = nextInt();
			arr = new long[n + 1];
			long mul = 1;
			for (int i = 1; i <= n; i++) {
				arr[i] = nextInt();
				mul = (mul * arr[i] % mod + mod) % mod;
			}
			Arrays.sort(arr, 1, n + 1);

			brr = new long[n + 1];
			for (int i = 1; i <= n; i += 2)
				brr[i] = arr[(i + 1) / 2];
			for (int i = 2; i <= n; i += 2)
				brr[i] = arr[n + 1 - i / 2];

			left = new long[n + 2];
			right = new long[n + 2];
			left[0] = 1;
			for (int i = 1; i <= n; i++)
				left[i] = (left[i - 1] * brr[i] % mod + mod) % mod;
			right[n + 1] = 1;
			for (int i = n; i >= 1; i--)
				right[i] = (right[i + 1] * brr[i] % mod + mod) % mod;

			long ans = 0;
			for (int i = 1; i < n; i++) {
				ans = ((ans + Math.min(brr[i], brr[i + 1]) * left[i - 1] % mod
						* right[i + 2])
						% mod + mod)
						% mod;
			}

			ans = (mul * n % mod - ans) % mod;
			ans = (ans + mod) % mod;
			out.println(ans);
		}
		out.flush();
		out.close();
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
