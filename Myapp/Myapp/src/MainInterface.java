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

	// ����ȫ������
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


		InitGlobalFont(new Font("����", Font.BOLD, 14)); // ����ȫ�������С

		JFrame frame = new JFrame("Сѧ�������������");
		JPanel container = new JPanel();
		JPanel Jpanel1 = new JPanel();

		JPanel Jpanel3 = new JPanel();

		// ָ�����Ĳ���ΪGridLayout��1��4�У���϶Ϊ5
		Jpanel1.setLayout(new GridLayout(1, 4, 10, 5));

		// ��ҳ��title����
		JLabel title1 = new JLabel("    ��Ŀ���");
		title1.setFont(new Font("����", Font.BOLD, 18));
		Jpanel1.add(title1);
		JLabel title2 = new JLabel("    ������ʽ ");
		title2.setFont(new Font("����", Font.BOLD, 18));
		Jpanel1.add(title2);
		JLabel title3 = new JLabel("      �����   ");
		title3.setFont(new Font("����", Font.BOLD, 18));
		Jpanel1.add(title3);
		JLabel title4 = new JLabel("       ��ȷ��   ");
		title4.setFont(new Font("����", Font.BOLD, 18));
		Jpanel1.add(title4);

		// �ײ����
		JButton createItem = new JButton("������Ŀ"); // createItem��������Ŀ
		createItem.addActionListener(new ActionListener1());
		JButton submit = new JButton("��   ��"); // submit���ύ��ť
		submit.addActionListener(new ActionListener1());

		Jpanel3.add(createItem);
		Jpanel3.add(submit);
		JLabel rightNum = new JLabel("         �� ȷ �� ����");
		rightNum.setFont(new Font("����", Font.BOLD, 18));
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

	// ���ò������
	class SetParamInterface extends JDialog {// jdialog�Ի���

		private static final long serialVersionUID = 1L;

		JFrame frame = new JFrame("���ò������");
		JPanel Jpanel = new JPanel();

		JLabel l1 = new JLabel("������������Ŀ�ĸ�����");
		JTextField itemNum = new JTextField(7);// �����ı�
		JLabel l2 = new JLabel("��������Ŀ��ֵ�ķ�Χ��");
		JTextField range = new JTextField(7);
		JButton submit = new JButton("       ȷ          ��       ");
		
		String item_answer = "";		//�������ɵ�������Ŀ�����
		

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
				if (e.getActionCommand() == "       ȷ          ��       ") {
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
						frame.dispose();// �ر����

						item = new String[itemArray.length];
						for (var k = 0; k < itemArray.length; k++) {
							item[k] = itemArray[k];
						}

//						String[] inputArr = new String[itemTemp.length];
//						String[] answerArr = new String[itemTemp.length];

						// �����ҳ����Ŀ����
						JPanel Jpanel2 = new JPanel();
						scrollPane.setViewportView(Jpanel2);
						Jpanel2.setLayout(new GridLayout(item.length, 4, 50, 5));


						
		
						input = new JTextField[item.length];
						answer = new JTextField[item.length];
						for (var i = 0; i < item.length; i++) {
							Jpanel2.add(new JLabel("       ��" + (i + 1) + "�⣺ "));
							Jpanel2.add(new JLabel(item[i]));
							input[i] = new JTextField(10); // input���û�������ı���
							input[i].setHorizontalAlignment(JTextField.CENTER);
							Jpanel2.add(input[i]);
							
							
							answer[i] = new JTextField(10); // answer�� ��ȷ���ı���
							answer[i].setHorizontalAlignment(JTextField.CENTER);
							
							Jpanel2.add(answer[i]);
						}
					
						dispose();
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "������������Ŀ�ĸ�������Ŀ��ֵ�ķ�Χ");
					}
				}
			}
		}
	}

	// ��ҳ��ļ����¼�
	class ActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "������Ŀ") {
				new SetParamInterface();
				System.out.println("CreateItem");

			}

			else if (e.getActionCommand() == "��   ��") {
				
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
