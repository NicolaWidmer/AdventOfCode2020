import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day19{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	
	static Set<String>[] setOfStrings;
	static Map<Integer,String> rules;
	static int wordlength;
	static final int maxrules =200;
	public static int sol() {
		File file=new File("Files/19.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		rules=new HashMap<Integer,String>();
		List<String> words=new ArrayList<String>();
		String line=sc.nextLine();
		while(line.length()>0) {
			line.split(":");
			rules.put(Integer.valueOf(line.split(":")[0]),line.split(":")[1].substring(1));
			line=sc.nextLine();
		}
		while(sc.hasNext()) words.add(sc.next());
		
		setOfStrings=new Set[maxrules];
		int ans=0;
		
		//all words in set 42 and 31 have equal lenth namely wordlength only neede for part 2
		wordlength=computeSet(42).iterator().next().length();
		
		for(String word:words) {
			if(isLegal(word))ans+=1;
		}
		return ans;
	}
	
	private static boolean isLegal(String word) {
		/*
		 * Part 1
		 * return computeSet(0).contains(word);
		 */
		
		int l=word.length()/wordlength;
		for(int i=1;i<l;i++) {
			if((word.length()-i*wordlength)%(2*wordlength)==0) {
				if(isInSet8(word.substring(0,i*wordlength))&&isInSet11(word.substring(i*wordlength)))
					return true;
			}
		}
		return false;
	}
	
	private static boolean isInSet8(String word) {
		if(word.equals(""))return true;
		for(String str:computeSet(42)) {
			if(word.startsWith(str))return isInSet8(word.substring(str.length()));
		}
		return false;
	}
	private static boolean isInSet11(String word) {
		if(word.equals(""))return true;
		for(String str:computeSet(42)) {
			for(String str2:computeSet(31)) {
				if(word.startsWith(str)&&word.endsWith(str2))
					return isInSet11(word.substring(str.length(),word.length()-str2.length()));	
			}
		}
		return false;
	}
	
	private static Set<String> computeSet(int i) {
		
		if(setOfStrings[i]!=null)return setOfStrings[i];
		
		Set<String> ans=new HashSet<String>();
		String rule=rules.get(i);
		if(rule.contains("\""))ans.add(rule.replace("\"", ""));
		else {
			Scanner s=new Scanner(rule);
			while(s.hasNext()) {
				Set<String> cur=new HashSet<String>();
				cur.add("");
				while(s.hasNextInt()) {
					Set<String> cur2=new HashSet<String>();
					Set<String> next=computeSet(s.nextInt());
					
					for(String str:next) {
						for(String str2:cur) {
							cur2.add(str2+str);
						}
					}
					cur=cur2;
				}
				ans.addAll(cur);
				if(s.hasNext())s.next();
			}
		}
		
		setOfStrings[i]=ans;
		return ans;
	}
	
} 