import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day18{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/18.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		long ans=0;
		while(sc.hasNext()) {
			ans+=evalExpr(new myScan(sc.nextLine().replace(" ","")));
		}
		return ans;
	}
	
	public static long evalExpr(myScan s) {
		List<Long> nums=new ArrayList<Long>();
		List<Character> ops=new ArrayList<Character>();
		nums.add(evalTerm(s));
		while(s.hasNextOp()) {
			ops.add(s.nextOp());
			nums.add(evalTerm(s));
		}
		
		/*
		 * Part one
		 * 
		 * long ans=nums.get(0);
		 * for(int i=0;i<ops.size();i++) {
		 *	if(ops.get(i)=='+') ans+=nums.get(i+1));
		 *	else ans*=nums.get(i+1));
		 *}
		 * 
		 * return ans;
		 * */
		
		
		/*
		 * Part two
		 */
		
		List<Long> added=new ArrayList<Long>();
		added.add(nums.get(0));
		for(int i=0;i<ops.size();i++) {
			if(ops.get(i)=='+') added.add(added.remove(added.size()-1)+nums.get(i+1));
			else added.add(nums.get(i+1));
		}
		long ans=1;
		for(long cur:added)ans*=cur;
		return ans;
	}
	public static long evalTerm(myScan s) {
		if(s.hasNextInt())return s.nextInt();
		else if(s.hasNextOpen()) {
			s.nextOpen();
			long cur=evalExpr(s);
			if(!s.hasNextClose())System.out.println("Error in Term");
			else {
				s.nextClose();
				return cur;
			}
		}
		else System.out.println("error in term");
		return -1;
	}
} 

class myScan{
	int pos;
	String s;
	myScan(String s){
		this.s=s;
		pos=0;
	}
	boolean hasNext() {
		return pos<s.length();
	}
	boolean hasNextOpen() {
		return hasNext()&&s.charAt(pos)=='(';
	}
	boolean hasNextClose() {
		return hasNext()&&s.charAt(pos)==')';
	}
	boolean hasNextOp() {
		return hasNext()&&(s.charAt(pos)=='+'||s.charAt(pos)=='*');
	}
	boolean hasNextInt() {
		return hasNext()&&(""+s.charAt(pos)).matches("[0-9]");
	}
	void nextOpen() {
		if(!hasNextOpen())System.out.println("error");
		pos++;
	}
	void nextClose() {
		if(!hasNextClose())System.out.println("error");
		pos++;
	}
	char nextOp() {
		if(!hasNextOp())System.out.println("error");
		return s.charAt(pos++);
	}
	int nextInt() {
		if(!hasNextInt())System.out.println("error");
		return Integer.valueOf(""+s.charAt(pos++));
	}
}