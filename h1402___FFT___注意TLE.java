import java.io.*;
import java.util.*;

public class h1402___FFT___ע��TLE {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	// http://www.csdn123.com/html/itweb/20130725/20149_20120_20137.htm
	// ģ����
	// дjava�汾��ʱ������û��struct������Ѹ����ļӼ��˳�д�ɷ���������static complex add(){}����ô�����׻�TLE
	// ��Ϊ�����ṹ���л���ֺܶ��new complex������java��Ҫ�����е�complex�ȸ�new������Ȼ���ٵ���д�Ӽ��˳�

	static class complex {
		double real, image;

		complex(double a, double b) {
			real = a;
			image = b;
		}

		void multiply(complex o) {
			double x = real * o.real - image * o.image;
			double y = real * o.image + image * o.real;
			real = x;
			image = y;
		}

	}

	static void swap(complex a, complex b) {
		double x = a.real;
		a.real = b.real;
		b.real = x;
		double y = a.image;
		a.image = b.image;
		b.image = y;
	}

	static void rev(complex[] y, int len) {
		int i, j, k;
		for (i = 1, j = len / 2; i < len - 1; i++) {
			if (i < j)
				swap(y[i], y[j]);
			// ������ΪС�귴ת��Ԫ�أ�i<j��֤����һ��
			// i��������+1��j��ת���͵�+1,ʼ�ձ���i��j�Ƿ�ת��
			k = len / 2;
			while (j >= k) {
				j -= k;
				k /= 2;
			}
			if (j < k)
				j += k;
		}
	}

	static double PI = Math.PI;
	static complex u = new complex(0, 0), t = new complex(0, 0);

	static void fft(complex[] y, int len, int op) {
		int i, j, k, h;

		rev(y, len);
		double real, image;

		for (h = 2; h <= len; h <<= 1) { // ���Ʋ���
			complex wn = new complex(Math.cos(op * 2 * PI / h), Math.sin(op * 2
					* PI / h));// �ò㵥λ����,hΪ����
			for (j = 0; j < len; j += h) {// ������ʼ�±�
				complex w = new complex(1, 0); // ��ʼ����������
				for (k = j; k < j + h / 2; k++) { // ���
					// u=y[k];
					u.real = y[k].real;
					u.image = y[k].image;
					// t = multiply(w, y[k + h / 2]);
					real = w.real * y[k + h / 2].real - w.image
							* y[k + h / 2].image;
					image = w.real * y[k + h / 2].image + w.image
							* y[k + h / 2].real;
					t.real = real;
					t.image = image;
					// y[k]=u+t;
					y[k].real = u.real + t.real;
					y[k].image = u.image + t.image;
					// y[k+h/2]=u-t;
					y[k + h / 2].real = u.real - t.real;
					y[k + h / 2].image = u.image - t.image;
					w.multiply(wn); // ������������
				}
			}
		}

		// ����ֵ
		if (op == -1)
			for (i = 0; i < len; i++)
				y[i].real /= len;
	}

	static String stra, strb;
	static int len1, len2, len;
	static int N = 1 << 17, sum[] = new int[N];
	static complex x1[] = new complex[N], x2[] = new complex[N];

	static void init() {
		for (int i = 0; i < N; i++) {
			x1[i] = new complex(0, 0);
			x2[i] = new complex(0, 0);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		while ((stra = br.readLine()) != null) {
			strb = br.readLine();
			len1 = stra.length();
			len2 = strb.length();
			len = 1;
			while (len < len1 * 2 || len < len2 * 2)
				len <<= 1;

			for (int i = 0; i < len1; i++) {
				x1[i].real = stra.charAt(len1 - i - 1) - '0';
				x1[i].image = 0;
			}
			for (int i = len1; i < len; i++)
				x1[i].real = x1[i].image = 0;

			for (int i = 0; i < len2; i++) {
				x2[i].real = strb.charAt(len2 - i - 1) - '0';
				x2[i].image = 0;
			}
			for (int i = len2; i < len; i++)
				x2[i].real = x2[i].image = 0;

			fft(x1, len, 1);
			fft(x2, len, 1);

			for (int i = 0; i < len; i++)
				x1[i].multiply(x2[i]);
			fft(x1, len, -1);

			for (int i = 0; i < len; i++)
				sum[i] = (int) (Math.round(x1[i].real));
			len = len1 + len2 - 1;
			for (int i = 0; i < len; i++) {
				sum[i + 1] += sum[i] / 10;
				sum[i] %= 10;
			}
			while (sum[len] <= 0 && len > 0)
				len--;
			for (int i = len; i >= 0; i--)
				out.print(sum[i]);
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