import java.util.Scanner;

public class Time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num, range;
		System.out.println("***********����ʵ��****************");
		System.out.println("�������������������Ŀ�ĸ�����");
		Scanner input = new Scanner(System.in);
		num = input.nextInt();
		System.out.println("���������������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ��");
		Scanner input1 = new Scanner(System.in);
		range = input1.nextInt();
		
		
		long startTime = System.currentTimeMillis();
		Testitem testitem = new Testitem();
		testitem.createitem(range, num);
		long endTime = System.currentTimeMillis();

		System.out.println("");
		System.out.println("��������ʱ�䣺" + (endTime - startTime) + "ms");

	}

}
