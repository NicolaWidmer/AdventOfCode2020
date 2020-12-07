import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day23{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/23.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		return ans;
	}
} 