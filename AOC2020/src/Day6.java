import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/6.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		Set<Character> cur=new HashSet<Character>();
		Set<Character> prev=new HashSet<Character>();
		String line;
		while(sc.hasNext()) {
			line=sc.nextLine();
			if(line.length()==0) {
				ans+=cur.size();
				cur=new HashSet<Character>();
				if(sc.hasNext()) {
					for(char c:sc.nextLine().toCharArray()) {
						cur.add(c);
					}
					prev=cur;
				}
			}
			else {
				cur=new HashSet<Character>();
				for(char c:line.toCharArray()) {
					if(prev.contains(c)) {
						cur.add(c);
					}
				}
				prev=cur;
			}
		}
		return ans;
	}
}
