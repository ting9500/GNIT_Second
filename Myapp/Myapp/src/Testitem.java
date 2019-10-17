

import java.util.Stack;

//生成题目及计算出答案
public class Testitem {

	public static String createitem(int range, int num) {
	
		
		String operator[] = { "+", "-", "×", "÷" };
		String allAnswer = "";				//全部答案
		String allItems = "";				//全部题目
		String Item_Answer = "";			//全部题目+答案
		String item = "";					//每道题目
		String answer = "";					//每道答案
		
		for(int i = 0;i < num;i++) {
			
			int operatornum = (int) (Math.random() * 3 + 1);
			String figure = null, operate = null;	
			int undo = 0;
			int bracketnum = 0;
			String expression = "";
			boolean flag = true;
			String result;
			
			Stack<String> operaStack = new Stack<String>();
			Stack<String> numStack = new Stack<String>();

			
			
			try {
				// 未包含最后一个数
				while (operatornum > 0) {
					

					int tag = (int) (Math.random() * 2);			//判断是否要生成括号
					figure = FormatProcess.fraction(range);
					operate = operator[(int) (Math.random() * 4)];
		
					if (tag == 0 && bracketnum < operatornum - 1) { // (
						expression += "(";
						operaStack.push("(");
						bracketnum++;
						undo++;
						flag = false;
					} else if (tag == 1&& flag == true && undo > 0) { // b ) +
						expression += figure + ")" + " " + operate + " ";
						numStack.push(figure);
						ExpressionCalculate.doCalcul(operaStack, numStack, true);
						ExpressionCalculate.comparePriority(operaStack, numStack, operate);
						operatornum--;
						undo--;
						flag = false;
					} else { // a +
						expression += figure + " " + operate + " ";
						if (operaStack.empty()) {
							operaStack.push(operate);
							numStack.push(figure);
						} else {
							numStack.push(figure);
							ExpressionCalculate.comparePriority(operaStack, numStack, operate);
						}
						operatornum--;
						flag = true;
					}
				}
		
			
				// 添加最后一个数
				if (undo == 0) {
					figure = FormatProcess.fraction(range);
					
					expression += figure;
				} else if (undo == 1) {
					if (expression.startsWith("((") || expression.startsWith("(") && expression.contains(")") == false) {
						figure = FormatProcess.fraction(range);
						expression = expression.substring(1) + figure;
					} else {
						figure = FormatProcess.fraction(range);
						expression += figure + ")";
		
					}
				} else if (undo == 2) {
					if (expression.startsWith("((")) {
						figure = FormatProcess.fraction(range);
						expression = expression.substring(2) + figure;
					} else if (expression.startsWith("(")) {
						figure = FormatProcess.fraction(range);
						expression = expression.substring(1) + figure + ")";
					} else {
						figure = FormatProcess.fraction(range);
						expression += figure + "))";
					}
				}
		
				numStack.push(figure);
				ExpressionCalculate.doCalcul(operaStack, numStack, false);				
				result = ExpressionCalculate.finalResult(numStack.peek());
				expression += " " + "=";
				if(expression.contains("÷ 0")) {
					i--;
					expression = "";
				}else {
					allItems += "(" + (i + 1) + ")  " + expression + "\n" + "\n";
					allAnswer += "(" + (i + 1) + ")  " + result + "\n" + "\n";
					
					item += expression + "&";
					answer += result + "&";
					
					Item_Answer += expression + "&" + result + "&";
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				i--;
			}
						
		}	//end -> for
		
		Item_Answer = item + "#" + answer;

		
		Save.save(allItems, 1);
		Save.save(allAnswer, 2);
		
		return Item_Answer;
		
	}
}

