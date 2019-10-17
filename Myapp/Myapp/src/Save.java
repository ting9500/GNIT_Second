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
			System.out.println("文件已保存");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件保存不成功！");
		}
		
		
	}
	
}
