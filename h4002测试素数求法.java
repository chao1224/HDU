import java.util.Scanner;


public class h4002���������� {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		int limit=scan.nextInt();
		int[] prime=new int[limit+1];
		
		//�������
		for (int i=2;i<=limit;i++)
			if (prime[i]==0)
				for (int j=2;j*i<=limit;j++)
					prime[i*j]=1;
		
		//�������
		for (int i=2;i<=limit;i++)
			if (prime[i]==0)
				System.out.println(i);
	}
}
