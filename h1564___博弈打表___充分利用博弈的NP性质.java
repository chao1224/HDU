import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioFormat.Encoding;

public class h1564___���Ĵ��___������ò��ĵ�NP���� {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(System.out);

	static int n, m;
	static boolean vis[][];

	static int dir[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

	static boolean dfs(int x, int y) {
		vis[x][y] = true;
		boolean ret;
		int i, j;
		for (int c = 0; c < 4; c++) {
			i = x + dir[c][0];
			j = y + dir[c][1];
			if (i >= 0 && j >= 0 && i < n && j < n && !vis[i][j]) {
				ret = dfs(i, j);
				if (!ret)// �����һ���бذܵ㣬��ô��ǰ��һ���Ǳ�ʤ�㣬��Ϊ��һ����P�㣬��ǰ�����N��
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		while (true) {
			n = nextInt();
			if (n == 0)
				break;
			vis = new boolean[n][n];

			if (dfs(0, 0))
				out.println("8600");
			else
				out.println("ailyanlu");
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