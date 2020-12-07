import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class GenerateDays {
	public static void main(String[] args) {
		for(int i=9;i<26;i++) {
			day(i);
		}
	}
	static void day(int i) {
		File f=new File("Files/"+i+".txt");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File java=new File("src/Day"+i+".java");
		try {
			java.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			OutputStream stream =new FileOutputStream("src/Day"+i+".java");
			String text="import java.io.File;\n" + 
					"import java.io.FileNotFoundException;\n" + 
					"import java.util.*;\n" + 
					"\n" + 
					"public class Day"+i+"{\n" + 
					"	public static void main(String[] args) {\n" + 
					"		System.out.println(sol());\n" + 
					"	}\n" + 
					"	public static int sol() {\n" + 
					"		File file=new File(\"Files/"+i+".txt\");\n" + 
					"		Scanner sc;\n" + 
					"		\n" + 
					"		try {\n" + 
					"			sc=new Scanner(file);\n" + 
					"		} catch (FileNotFoundException e) {\n" + 
					"			System.out.println(e);\n" + 
					"			sc=new Scanner(System.in);\n" + 
					"		}\n" + 
					"		int ans=0;\n" + 
					"		return ans;\n" + 
					"	}\n"+
					"} ";
			stream.write(text.getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
}
