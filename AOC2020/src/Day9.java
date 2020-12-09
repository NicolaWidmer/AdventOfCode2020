import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/9.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=-1;
		Long sum=1309761972L;
		Long cursum=0L;
		List<Long> list=new ArrayList<Long>();
		while(sc.hasNextLong()) {
			list.add(sc.nextLong());
		}
		int i=0;
		int j=0;
		while(!cursum.equals(sum)) {
			if(cursum<sum) {
				cursum+=list.get(j++);
			}
			else if(cursum>sum) {
				cursum-=list.get(i++);
			}
		}
		Long max=Long.MIN_VALUE;
		Long min=Long.MAX_VALUE;
		for(int k=i;k<j;k++) {
			max=Math.max(max, list.get(k));
			min=Math.min(min, list.get(k));
		}
		return max+min;
	}
	static Set<Long> summs(int start,int end,List<Long> list){
		Set<Long> ans=new HashSet<Long>();
		for(int i=start;i<end;i++) {
			for(int j=start+1;j<end;j++) {
				if(list.get(i)!=list.get(j))
				 ans.add(list.get(i)+list.get(j));
			}
		}
		return ans;
	}
} 