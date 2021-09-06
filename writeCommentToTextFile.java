package testfile;

import java.io.FileWriter;
import java.io.IOException;

public class writeCommentToTextFile {

	public static void main(String[] args) throws IOException {

		FileWriter file = new FileWriter("E:\\workspace\\Helloworld\\src\\testfile\\sh.txt",true);
		file.write("portfolio is exist");
		file.close();
		
		System.out.println("file has been created");
		
	}

}
