import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
	
	private static List<Edge>[] adList;
	private static Map<String,Integer> colToInt;
	public static void main(String[] args) {
		System.out.println(sol2());
	}
	private static long sol2() {
		readInput();
		int dest=colToInt.get("shiny gold");
		inside=new long[adList.length];
		Arrays.fill(inside, -1);
		return count(dest)-1;
	}
	
	private static int sol1() {
		
		readInput();
		int ans=0;
		int dest=colToInt.get("shiny gold");
		for(int source:colToInt.values()) {
			if(dest!=source&&reachable(source,dest))ans+=1;
		}
		return ans;
	}
	
	private static long[] inside;
	private static long count(int i) {
		if (inside[i]!=-1) return inside[i];
		inside[i]=1;
		for(Edge e:adList[i]) {
			inside[i]+=e.val*count(e.dest);
		}
		return inside[i];
		
	}
	
	
	private static boolean[] vis;
	private static boolean reachable(int source, int dest) {
		vis=new boolean[adList.length];
		dfs(source);
		return vis[dest];
	}
	
	
	private static void dfs(int i) {
		vis[i]=true;
		for(Edge e:adList[i]) {
			if(!vis[e.dest])dfs(e.dest);
		}
	}
	
	
	private static void readInput() {
		
		File file=new File("Files/7.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		colToInt=new HashMap<String,Integer>();
		List<Map<String,Integer>> colToColToQuan=new ArrayList<Map<String,Integer>>();
		int i=0;
		while(sc.hasNext()) {
			String color=sc.next();
			color+=" "+sc.next();
			colToInt.put(color, i++);
			HashMap<String,Integer> curMap=new HashMap<String,Integer>();
			colToColToQuan.add(curMap);
			sc.next();
			sc.next();
			if(sc.hasNextInt()) {
				while(sc.hasNextInt()) {
					int quan=sc.nextInt();
					String curCol=sc.next();
					curCol+=" "+sc.next();
					sc.next();
					curMap.put(curCol, quan);
				}
			}
			else {
				sc.nextLine();
			}
		}
		adList=new List[i];
		for(int node:colToInt.values()) {
			adList[node]=new ArrayList<Edge>();
			for(String bag:colToColToQuan.get(node).keySet()) {
				Edge e=new Edge(colToInt.get(bag),colToColToQuan.get(node).get(bag));
				adList[node].add(e);
			}
		}
		
	}
	
	static class Edge{
		int dest;
		int val;
		Edge(int d,int v){
			val=v;
			dest=d;
		}
	}
} 
