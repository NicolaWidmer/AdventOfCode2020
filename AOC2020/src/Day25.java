import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day25{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/25.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		long mod=20201227L;
		long card=sc.nextLong();
		long door=sc.nextLong();
		long loopcard=calcLoop(card,mod);
		long ans=1;
		for(int i=0;i<loopcard;i++) {
			ans=(ans*door)%mod;
		}
		return ans;
	}
	static long calcLoop(long key,long mod) {
		long ans=0;
		long cur=1;
		while(cur!=key) {
			ans++;
			cur=(cur*7)%mod;
		}
		return ans;
	}
} 