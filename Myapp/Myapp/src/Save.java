import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {

	public static void save(String allItems, int tag) {
		
		String file = "";
		if(tag == 1) {
			file = "D:\\JAVA\\workplace\\Exercises.txt";
		}else if(tag == 2){
			file = "D:\\JAVA\\workplace\\Answers.txt";
		}else {
			file = "D:\\JAVA\\workplace\\Grade.txt";
		}
		File myFile = new File(file);
		FileWriter fw;
		try {
			fw = new FileWriter(myFile,false);
			fw.write(allItems);
			fw.close();
			System.out.println("�ļ��ѱ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ļ����治�ɹ���");
		}
		
		
	}
	
}
