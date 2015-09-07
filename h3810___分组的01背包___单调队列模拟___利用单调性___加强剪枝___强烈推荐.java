import java.io.*;
import java.util.*;

public class h3810___�����01����___��������ģ��___���õ�����___��ǿ��֦___ǿ���Ƽ� {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int N = 55, M = 1000000;
	static int t[] = new int[N], g[] = new int[N];
	static boolean c[][] = new boolean[N][N];
	static int cnt[] = new int[N];

	static class node implements Comparable<node> {
		long costTime;
		long getMoney;

		node(long c, long v) {
			costTime = c;
			getMoney = v;
		}

		public int compareTo(node o) {
			if (this.getMoney > o.getMoney)
				return -1;
			else if (this.getMoney < o.getMoney)
				return 1;
			else if (this.costTime > o.costTime)
				return -1;
			else if (this.costTime < o.costTime)
				return 1;
			else
				return 0;
		}
	}

	static PriorityQueue<node> cur = new PriorityQueue<node>(),
			pre = new PriorityQueue<node>();

	static int n, m;

	static void init() {
		for (int i = 0; i < N; i++)
			Arrays.fill(c[i], false);
		Arrays.fill(cnt, -1);
	}

	static void dfs(int u, int dep) {
		cnt[u] = dep;
		for (int v = 1; v <= n; v++)
			if (c[u][v] && cnt[v] == -1) {
				dfs(v, dep);
			}
	}

	public static void main(String[] args) throws IOException {
		int ttt = nextInt(), d;
		for (int test = 1; test <= ttt; test++) {
			n = nextInt();
			m = nextInt();
			init();
			for (int i = 1; i <= n; i++) {
				t[i] = nextInt();
				g[i] = nextInt();
				d = nextInt();
				for (int j = 1; j <= d; j++)
					c[i][nextInt()] = true;
			}

			int index = 0;
			for (int i = 1; i <= n; i++)
				if (cnt[i] == -1)
					dfs(i, ++index);

			long inf = 1l << 45;
			long mincost, min = inf;
			node temp, now;

			for (int c = 1; c <= index; c++) {
				pre.clear();
				cur.clear();
				pre.add(new node(0, 0));
				for (int i = 1; i <= n; i++)
					if (cnt[i] == c) {
						while (!pre.isEmpty()) {
							temp = pre.poll();
							cur.add(temp);
							now = new node(temp.costTime + t[i], temp.getMoney
									+ g[i]);

							// �����������ﵽ�����õ���money
							if (now.getMoney >= m) {
								min = Math.min(min, now.costTime);
								continue;
							}
							// û�дﵽ������money������������ʱ���Ѿ��ȵ�ǰ��С��Ҫ�󣬼�֦
							if (now.costTime >= min)
								continue;
							cur.add(now);
						}

						mincost = inf;
						while (!cur.isEmpty()) {
							temp = cur.poll();
							// Ϊ�˱��ֵ�ǰ�ĵ����ԣ�Ҫ����ӵ�temp��һ��Ҫ���Ѿ���ӵģ����Ľ�Ǯ�ٵ�ͬʱ��ʱ�仨��ҲҪ��
							if (mincost > temp.costTime) {
								mincost = temp.costTime;
								pre.add(temp);
							}
						}

					}

			}

			if (min == inf)
				out.printf(
						"Case %d: Poor Magina, you can't save the world all the time!",
						test);
			else
				out.printf("Case %d: %d", test, min);
			out.println();
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