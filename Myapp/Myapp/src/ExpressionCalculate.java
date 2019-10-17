import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionCalculate {
	
	//设置运算符的优先级
	public static Map<String, Integer> map = new HashMap<>() {
		private static final long serialVersionUID = 6968472606692771458L;
		{
			put("(", 0);
			put("+", 1);
			put("-", 1);
			put("×", 2);
			put("÷", 2);
			put(")", 3);
			put("=", 4);
		}
	};



	//将最终结果进行格式化
	public static String finalResult(String num) throws Exception {
		String[] nums = new String[2];
		int mole, deno;
		nums = Calculate.change(num);
		mole = Integer.parseInt(nums[0]);
		deno = Integer.parseInt(nums[1]);
		String finalResult = FormatProcess.Format(mole, deno);
		if(finalResult.contains("-")) {				//若中间结果出现负数，即出现负号，则抛出异常
			throw new Exception();
		}else {
			return finalResult;
		}
		
	}
	
	
	// 括号内运算符的优先级是从低到高
	public static void doCalcul(Stack<String> operaStack, Stack<String> numStack, boolean flag) throws Exception {

		//将两个数值及运算符出栈进行计算
		String nowOpera = operaStack.pop();
		String nowNum_2 = numStack.pop();
		String nowNum_1 = numStack.pop();
		
		String result = Calculate.result(nowOpera, nowNum_1, nowNum_2);			//计算出结果
		if(result.contains("-")) {												//若结果出现负数，即出现负号，则抛出异常
			throw new Exception();
		}else {
			numStack.push(result);			//结果符合要求，压入数据栈进行下一个运算
		}
		if(flag) {
			if("(".equals(operaStack.peek())) {					//栈顶为（，说明括号内的运算已完成，“（”出栈
				operaStack.pop();
			}else {
				doCalcul(operaStack, numStack, flag);			//栈顶不是“（”，说明括号内的运算未完成，继续递归计算
			}
		}else {
			if(!operaStack.empty()) {							
				doCalcul(operaStack, numStack, flag);
			}
		}
		
	}

	// 优先级比较
	public static void comparePriority(Stack<String> operaStack, Stack<String> numStack, String operator) throws Exception{
		
		
		String topOpera = operaStack.peek();
		int priority = map.get(operator) - map.get(topOpera);			//优先级判断
		if (priority > 0) {												//若当前运算符的优先级较高，则先入栈，等待优先级比它更高的运算符
			operaStack.push(operator);
		} else {
			String nowOpera = operaStack.pop();
			String nowNum_2 = numStack.pop();
			String nowNum_1 = numStack.pop();
			String result = Calculate.result(nowOpera, nowNum_1, nowNum_2);

			numStack.push(result);
			if (operaStack.empty()) {				//运算符栈为空，入栈
				operaStack.push(operator);
			} else {
				comparePriority(operaStack, numStack, operator);
			}
		}

	}	
}
