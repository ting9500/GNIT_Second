
//��У��
//�����ʽ
//		Correct: 5 (1, 3, 5, 7, 9)
//
//		Wrong: 5 (2, 4, 6, 8, 10)
public class Check {

	
	public static String check(String[] rightAnswer, String[] userAnswer) {
		
		
		String checkString = "";			//����Grade.txt���ݼ���ȷ����
		String rightSet = "";				//��ȷ��Correct: 5 (1, 3, 5, 7, 9)
		String wrongSet = "";				//����Wrong: 5 (2, 4, 6, 8, 10)
		int allRight = 0, allWrong = 0;		//��ȷ�������Ŀ��
		
		for(int i = 0; i < rightAnswer.length; i++) {			
			if(rightAnswer[i].equals(userAnswer[i])) {
				allRight ++;
				rightSet += (i+1) + ", ";
			}else {
				allWrong ++ ;
				wrongSet += (i+1) + ", ";
			}
			
		}
		
		//�˲���Ŀ���ǽ����1,2,3���������
		int index_1 = rightSet.lastIndexOf(',');	
		rightSet = rightSet.substring(0, index_1);
		int index_2 = wrongSet.lastIndexOf(',');
		wrongSet = wrongSet.substring(0,index_2);


		//ʹ��&����ָ����ȷ��Ŀ��
		checkString = "Correct: " + allRight + " (" + rightSet + ")" + "\n" + "\n" 
						+ "Wrong: " + allWrong + " (" + wrongSet + ")" + "\n" + "&" + allRight;
		
		return checkString;
		
	}
	
}
