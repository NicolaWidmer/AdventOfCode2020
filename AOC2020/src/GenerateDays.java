
public class GenerateDays {
	public static void main(String[] args) {
		int i=7;
		day(i);
	}
	static void day(int i) {
		System.out.println("import java.io.File;\n" + 
				"import java.io.FileNotFoundException;\n" + 
				"import java.util.*;\n" + 
				"\n" + 
				"public class Day7 {\n" + 
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
				"} ");
	}
}
