import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionCalculate {
	
	//��������������ȼ�
	public static Map<String, Integer> map = new HashMap<>() {
		private static final long serialVersionUID = 6968472606692771458L;
		{
			put("(", 0);
			put("+", 1);
			put("-", 1);
			put("��", 2);
			put("��", 2);
			put(")", 3);
			put("=", 4);
		}
	};



	//�����ս�����и�ʽ��
	public static String finalResult(String num) throws Exception {
		String[] nums = new String[2];
		int mole, deno;
		nums = Calculate.change(num);
		mole = Integer.parseInt(nums[0]);
		deno = Integer.parseInt(nums[1]);
		String finalResult = FormatProcess.Format(mole, deno);
		if(finalResult.contains("-")) {				//���м������ָ����������ָ��ţ����׳��쳣
			throw new Exception();
		}else {
			return finalResult;
		}
		
	}
	
	
	// ����������������ȼ��Ǵӵ͵���
	public static void doCalcul(Stack<String> operaStack, Stack<String> numStack, boolean flag) throws Exception {

		//��������ֵ���������ջ���м���
		String nowOpera = operaStack.pop();
		String nowNum_2 = numStack.pop();
		String nowNum_1 = numStack.pop();
		
		String result = Calculate.result(nowOpera, nowNum_1, nowNum_2);			//��������
		if(result.contains("-")) {												//��������ָ����������ָ��ţ����׳��쳣
			throw new Exception();
		}else {
			numStack.push(result);			//�������Ҫ��ѹ������ջ������һ������
		}
		if(flag) {
			if("(".equals(operaStack.peek())) {					//ջ��Ϊ����˵�������ڵ���������ɣ���������ջ
				operaStack.pop();
			}else {
				doCalcul(operaStack, numStack, flag);			//ջ�����ǡ�������˵�������ڵ�����δ��ɣ������ݹ����
			}
		}else {
			if(!operaStack.empty()) {							
				doCalcul(operaStack, numStack, flag);
			}
		}
		
	}

	// ���ȼ��Ƚ�
	public static void comparePriority(Stack<String> operaStack, Stack<String> numStack, String operator) throws Exception{
		
		
		String topOpera = operaStack.peek();
		int priority = map.get(operator) - map.get(topOpera);			//���ȼ��ж�
		if (priority > 0) {												//����ǰ����������ȼ��ϸߣ�������ջ���ȴ����ȼ��������ߵ������
			operaStack.push(operator);
		} else {
			String nowOpera = operaStack.pop();
			String nowNum_2 = numStack.pop();
			String nowNum_1 = numStack.pop();
			String result = Calculate.result(nowOpera, nowNum_1, nowNum_2);

			numStack.push(result);
			if (operaStack.empty()) {				//�����ջΪ�գ���ջ
				operaStack.push(operator);
			} else {
				comparePriority(operaStack, numStack, operator);
			}
		}

	}	
}
