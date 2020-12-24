import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day24{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/24.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		Set<String> flipped=new HashSet<String>();
		while(sc.hasNext()) {
			String line=sc.nextLine();
			int x=0,y=0;
			for(int i=0;i<line.length();) {
				char next=line.charAt(i++);
				if(next=='e')x++;
				else if(next=='w')x--;
				else {
					char nextnext=line.charAt(i++);
					if(next=='n') {
						y++;
						if(nextnext=='e')x++;
					}
					else {
						y--;
						if(nextnext=='w')x--;
					}
				}
			}
			String next=numToTile(x,y);
			if(flipped.contains(next))flipped.remove(next);
			else flipped.add(next);
		}
		int turns=100;
		for(int i=0;i<turns;i++) {
			Set<String> next=new HashSet<String>();
			for(String cur:flipped) {
				int neigh=blackNiegh(cur,flipped);
				if(!(neigh==0||neigh>2))next.add(cur);
			}
			Map<String,Integer> map=new HashMap<String,Integer>();
			for(String cur:flipped) {
				List<String> neighs=neigh(cur);
				for(String str:neighs) {
					map.put(str, map.getOrDefault(str, 0)+1);
				}
			}
			for(String cur:map.keySet()) {
				if(map.get(cur)==2&&!flipped.contains(cur))next.add(cur);
			}
			flipped=next;
		}
		int ans=flipped.size();
		return ans;
	}
	static int blackNiegh(String cur,Set<String> flipped) {
		int[][] neigh=new int[][] {{-1,0},{1,0},{0,1},{1,1},{0,-1},{-1,-1}};
		int x=tileToNum(cur)[0];
		int y=tileToNum(cur)[1];
		int ans=0;
		for(int[] next:neigh) {
			if(flipped.contains(numToTile(x+next[0],y+next[1])))ans++;
		}
		return ans;
	}
	static List<String> neigh(String cur) {
		List<String> ans=new ArrayList<String>();
		int[][] neigh=new int[][] {{-1,0},{1,0},{0,1},{1,1},{0,-1},{-1,-1}};
		int x=tileToNum(cur)[0];
		int y=tileToNum(cur)[1];
		for(int[] next:neigh) {
			ans.add(numToTile(x+next[0],y+next[1]));
		}
		return ans;
	}
	
	static int[] tileToNum(String tile) {
		Scanner sc=new Scanner(tile);
		int[] ans=new int[2];
		ans[0]=sc.nextInt();
		ans[1]=sc.nextInt();
		sc.close();
		return ans;
	}
	static String numToTile(int x,int y) {
		return ""+x+" "+y;
	}
} 