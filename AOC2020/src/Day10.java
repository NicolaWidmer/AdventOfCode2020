import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/10.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		List<Integer> list=new ArrayList<Integer>();
		while(sc.hasNext()) {
			list.add(sc.nextInt());
		}
		int[] arr=new int[list.size()];
		for(int i=0;i<list.size();i++) {
			arr[i]=list.get(i);
		}
		Arrays.sort(arr);
		
		int device=arr[list.size()-1]+3;
		long[] dp=new long[device+1];
		dp[0]=1L;
		for(int i=0;i<list.size();i++) {
			int cur=arr[i];
			dp[cur]=0;
			for(int j=1;j<4&&cur-j>=0;j++) {
				dp[cur]+=dp[cur-j];
			}
		}
		for(int j=1;j<4;j++) {
			dp[device]+=dp[device-j];
		}
		return dp[device];
	}
} 