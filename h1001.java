import java.util.Scanner;

public class h1001 {
	// ��������ᳬ��long�������м���̻���������������
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			long n = scan.nextInt();
			long sum = n * (n + 1);
			sum = sum / 2;
			System.out.println(sum);
			System.out.println();
		}
	}

	// public static void main(String[] args) {
	// Scanner scan = new Scanner(System.in);
	// while (scan.hasNext()) {
	// long n = scan.nextInt();
	// int sum=0
	// for (int a=1;a<=n;a++)
	// sum+=a;
	// System.out.println(sum);
	// System.out.println();
	// }
	// }

}
