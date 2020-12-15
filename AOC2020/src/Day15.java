import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/15.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		List<Integer> nums=new ArrayList<Integer>();
		while(sc.hasNext()) {
			nums.add(sc.nextInt());
		}
		
		int steps=0;
		int last=0;
		int numOfSteps=30000000;
		
		Map<Integer,Integer> lastIndex=new HashMap<Integer,Integer>();
		
		for(;steps<nums.size();steps++) {
			lastIndex.put(last, steps-1);
			last=nums.get(steps);
		}
		for(;steps<numOfSteps;steps++) {
			
			Integer ind=lastIndex.get(last);
			lastIndex.put(last, steps-1);
			
			int next=0;
			
			if(ind==null)next=0;
			else next=steps-1-ind;
			
			last=next;
		}
		return last;
	}
} 