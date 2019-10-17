import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class MainInterface extends JFrame {

	public static void main(String[] args) {
		
		new MainInterface();
	}

	private static final long serialVersionUID = 1L;

	// 设置全局字体
	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	String param;
	String[] item;
	String[] userInput;

	JScrollPane scrollPane = new JScrollPane();
	JTextField[] input;
	JTextField[] answer;
	JTextField correct;
	
	String[] itemArray;
	String[] answerArray;
	
	public MainInterface() {


		InitGlobalFont(new Font("宋体", Font.BOLD, 14)); // 设置全局字体大小

		JFrame frame = new JFrame("小学生四则运算程序");
		JPanel container = new JPanel();
		JPanel Jpanel1 = new JPanel();

		JPanel Jpanel3 = new JPanel();

		// 指定面板的布局为GridLayout，1行4列，间隙为5
		Jpanel1.setLayout(new GridLayout(1, 4, 10, 5));

		// 主页面title部分
		JLabel title1 = new JLabel("    题目序号");
		title1.setFont(new Font("宋体", Font.BOLD, 18));
		Jpanel1.add(title1);
		JLabel title2 = new JLabel("    运算表达式 ");
		title2.setFont(new Font("宋体", Font.BOLD, 18));
		Jpanel1.add(title2);
		JLabel title3 = new JLabel("      输入答案   ");
		title3.setFont(new Font("宋体", Font.BOLD, 18));
		Jpanel1.add(title3);
		JLabel title4 = new JLabel("       正确答案   ");
		title4.setFont(new Font("宋体", Font.BOLD, 18));
		Jpanel1.add(title4);

		// 底部组件
		JButton createItem = new JButton("生成题目"); // createItem：生成题目
		createItem.addActionListener(new ActionListener1());
		JButton submit = new JButton("提   交"); // submit：提交按钮
		submit.addActionListener(new ActionListener1());

		Jpanel3.add(createItem);
		Jpanel3.add(submit);
		JLabel rightNum = new JLabel("         正 确 题 数：");
		rightNum.setFont(new Font("宋体", Font.BOLD, 18));
		Jpanel3.add(rightNum);
		correct = new JTextField(20);
		correct.setHorizontalAlignment(JTextField.CENTER);
		Jpanel3.add(correct);

		container.setLayout(new BorderLayout());
		container.add(Jpanel1, BorderLayout.NORTH);
		container.add(scrollPane);
		container.add(Jpanel3, BorderLayout.SOUTH);

		frame.add(container);
		frame.setBounds(400, 250, 800, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 设置参数面板
	class SetParamInterface extends JDialog {// jdialog对话框

		private static final long serialVersionUID = 1L;

		JFrame frame = new JFrame("设置参数面板");
		JPanel Jpanel = new JPanel();

		JLabel l1 = new JLabel("请输入生成题目的个数：");
		JTextField itemNum = new JTextField(7);// 单行文本
		JLabel l2 = new JLabel("请输入题目数值的范围：");
		JTextField range = new JTextField(7);
		JButton submit = new JButton("       确          认       ");
		
		String item_answer = "";		//包含生成的所有题目及其答案
		

		public SetParamInterface() {

			Jpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
			submit.addActionListener(new Submit());

			Jpanel.add(l1);
			Jpanel.add(itemNum);
			Jpanel.add(l2);
			Jpanel.add(range);
			Jpanel.add(submit);
			frame.add(Jpanel);

			frame.setSize(300, 200);
			frame.setLocation(650, 350);
			frame.setVisible(true);

		}

		class Submit implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getActionCommand() == "       确          认       ") {
					try {
						int num = Integer.parseInt(itemNum.getText());
						int numRange = Integer.parseInt(range.getText());
						item_answer = Testitem.createitem(numRange, num);
				
						ArrayList<String> list = 
						        new ArrayList<String>(Arrays.asList(item_answer.split("#")));
						String[] itemAndanswer = new String[2];
						for(int i = 0;i<list.size();i++) {
							itemAndanswer[i] = list.get(i);
						}
						
						itemArray = itemAndanswer[0].split("&");
						answerArray = itemAndanswer[1].split("&");
				
						param = (itemNum.getText() + range.getText());
						frame.dispose();// 关闭面板

						item = new String[itemArray.length];
						for (var k = 0; k < itemArray.length; k++) {
							item[k] = itemArray[k];
						}

//						String[] inputArr = new String[itemTemp.length];
//						String[] answerArr = new String[itemTemp.length];

						// 添加主页面题目部分
						JPanel Jpanel2 = new JPanel();
						scrollPane.setViewportView(Jpanel2);
						Jpanel2.setLayout(new GridLayout(item.length, 4, 50, 5));


						
		
						input = new JTextField[item.length];
						answer = new JTextField[item.length];
						for (var i = 0; i < item.length; i++) {
							Jpanel2.add(new JLabel("       第" + (i + 1) + "题： "));
							Jpanel2.add(new JLabel(item[i]));
							input[i] = new JTextField(10); // input：用户输入答案文本域
							input[i].setHorizontalAlignment(JTextField.CENTER);
							Jpanel2.add(input[i]);
							
							
							answer[i] = new JTextField(10); // answer： 正确答案文本域
							answer[i].setHorizontalAlignment(JTextField.CENTER);
							
							Jpanel2.add(answer[i]);
						}
					
						dispose();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "请输入生成题目的个数及题目数值的范围");
					}
				}
			}
		}
	}

	// 主页面的监听事件
	class ActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "生成题目") {
				new SetParamInterface();
				System.out.println("CreateItem");

			}

			else if (e.getActionCommand() == "提   交") {
				
				String gradeString = "";
				for (var k = 0; k < item.length; k++) {
					gradeString += input[k].getText() + "&";
					answer[k].setText(answerArray[k]);
				}
				String[] userAnswer = gradeString.split("&");			
				String checkResult = Check.check(answerArray, userAnswer);

				String CheckFile = checkResult.split("&")[0];
				String rightNum = checkResult.split("&")[1];

				correct.setText(rightNum);
				Save.save(CheckFile,3);
				
				
			}

		}
	}

}
