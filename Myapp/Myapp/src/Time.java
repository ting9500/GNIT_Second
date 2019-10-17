import java.util.Scanner;

public class Time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int num, range;
		System.out.println("***********测试实例****************");
		System.out.println("请输入参数控制生成题目的个数：");
		Scanner input = new Scanner(System.in);
		num = input.nextInt();
		System.out.println("请输入参数控制题目中数值（自然数、真分数和真分数分母）的范围：");
		Scanner input1 = new Scanner(System.in);
		range = input1.nextInt();
		
		
		long startTime = System.currentTimeMillis();
		Testitem testitem = new Testitem();
		testitem.createitem(range, num);
		long endTime = System.currentTimeMillis();

		System.out.println("");
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

	}

}
