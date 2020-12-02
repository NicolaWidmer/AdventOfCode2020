import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/2.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		
		int ans=0;
		while(sc.hasNext()) {
			if(isKorrekt(sc.nextLine()))ans++;
		}
		sc.close();
		
		return ans;
	}
	public static boolean isKorrekt(String line) {
		
		line=line.replace('-', ' ');
		line=line.replace(":", "");
		
		Scanner sc=new Scanner(line);
		int lower=sc.nextInt();
		int upper=sc.nextInt();
		char c=sc.next().charAt(0);
		String word=sc.next();
		sc.close();
		
		return word.charAt(lower-1)==c^word.charAt(upper-1)==c;
	}
}
