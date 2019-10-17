
//答案校对
//输出形式
//		Correct: 5 (1, 3, 5, 7, 9)
//
//		Wrong: 5 (2, 4, 6, 8, 10)
public class Check {

	
	public static String check(String[] rightAnswer, String[] userAnswer) {
		
		
		String checkString = "";			//返回Grade.txt内容及正确题数
		String rightSet = "";				//正确：Correct: 5 (1, 3, 5, 7, 9)
		String wrongSet = "";				//错误：Wrong: 5 (2, 4, 6, 8, 10)
		int allRight = 0, allWrong = 0;		//正确与错误题目数
		
		for(int i = 0; i < rightAnswer.length; i++) {			
			if(rightAnswer[i].equals(userAnswer[i])) {
				allRight ++;
				rightSet += (i+1) + ", ";
			}else {
				allWrong ++ ;
				wrongSet += (i+1) + ", ";
			}
			
		}
		
		//此部分目的是解决（1,2,3，）的情况
		int index_1 = rightSet.lastIndexOf(',');	
		rightSet = rightSet.substring(0, index_1);
		int index_2 = wrongSet.lastIndexOf(',');
		wrongSet = wrongSet.substring(0,index_2);


		//使用&方便分割出正确题目数
		checkString = "Correct: " + allRight + " (" + rightSet + ")" + "\n" + "\n" 
						+ "Wrong: " + allWrong + " (" + wrongSet + ")" + "\n" + "&" + allRight;
		
		return checkString;
		
	}
	
}
