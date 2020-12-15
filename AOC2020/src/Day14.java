import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/14.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		long ans=0;
		Map<Long,Long> mem=new HashMap<Long,Long>();
		int[] mask=new int[36];
		while(sc.hasNext()) {
			String line=sc.nextLine();
			if(line.startsWith("mask")) {
				line=line.replace("mask = ","");
				for(int i=0;i<line.length();i++) {
					char c=line.charAt(i);
					mask[i]=c=='X'?-1:c=='1'?1:0;
				}
			}
			else {
				line=line.replace("mem[", "");
				line=line.replace("] =", "");
				Scanner s=new Scanner(line);
				long addr=s.nextLong();
				long num=s.nextLong();
				for(int[] curAddr:applyMaskOnAddr(mask,intToMem(addr))) {
					mem.put(memToInt(curAddr), num);
				}
			}
		}
		for(long val:mem.values()) {
			ans+=val;
		}
		return ans;
	}
	static List<int[]> applyMaskOnAddr(int[] mask,int[] addr){
		List<int[]> ans=new ArrayList<int[]>();
		ans.add(new int[36]);
		for(int i=0;i<36;i++) {
			if(mask[i]==-1) {
				List<int[]> curs=new ArrayList<int[]>();
				for(int[] cur:ans) {
					int[] cur2=cur.clone();
					cur[i]=0;
					cur2[i]=1;
					curs.add(cur);
					curs.add(cur2);
				}
				ans=curs;
			}
			else if(mask[i]==0) {
				for(int[] cur:ans){
					cur[i]=addr[i];
				}
			}
			else {
				for(int[] cur:ans){
					cur[i]=1;
				}
			}
		}
		return ans;
	}
	static int[] applyMask(int[] mask,int[] mem) {
		int[] ans=new int[36];
		for(int i=0;i<36;i++) {
			if(mask[i]==-1)
				ans[i]=mem[i];
			else
				ans[i]=mask[i];
		}
		return ans;
	}
	
	static int[] intToMem(long num) {
		int[] ans=new int[36];
		for(int i=35;i>=0;i--) {
			ans[i]=(int) (num%2);
			num/=2;
		}
		return ans;
	}
	static long memToInt(int[] mem) {
		long ans=0;
		for(int i=0;i<36;i++) {
			ans*=2;
			ans+=mem[i];
		}
		return ans;
	}
	
} 