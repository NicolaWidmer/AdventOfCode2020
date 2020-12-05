import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/5.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		
		boolean[] ids=new boolean[1024];
		while(sc.hasNext()) {
			String line=sc.nextLine();
			ids[id(line)]=true;
		}
		for(int i=1;i<1023;i++) {
			if(!ids[i]&&ids[i-1]&&ids[i+1])return i;
		}
		return -1;
	}
	
	static int id(String s) {
		int ans=0;
		for(int i=0;i<10;i++) {
			ans*=2;
			ans+=s.charAt(i)=='B'||s.charAt(i)=='R'?1:0;
		}
		return ans;	
	}
}
