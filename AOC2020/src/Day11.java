import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/11.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		List<List<Integer>> seats=new ArrayList<List<Integer>>();
		while(sc.hasNext()) {
			String line=sc.next();
			List<Integer> cur=new ArrayList<Integer>();
			for(int i=0;i<line.length();i++) {
				cur.add(line.charAt(i)=='L'?1:0);
			}
			seats.add(cur);
		}
		List<List<Integer>> prev=null;
		while(!same(prev,seats)) {
			prev=seats;
			seats=makeStep(seats);
			
		}
		for (int i = 0; i < prev.size(); i++) {
			for (int j = 0; j < prev.get(i).size(); j++) {
				ans+=prev.get(i).get(j)==2?1:0;
			}
		}
		return ans;
	}
	static List<List<Integer>> makeStep(List<List<Integer>> seats){
		List<List<Integer>> ans=new ArrayList<List<Integer>>();
		for (int i = 0; i < seats.size(); i++) {
			List<Integer> cur=new ArrayList<Integer>();
			for (int j = 0; j < seats.get(i).size(); j++) {
				
				if(seats.get(i).get(j)==0)cur.add(0);
				else if(seats.get(i).get(j)==1) {
					if(numOfNeighbour(i,j,2,seats)!=0)cur.add(1);
					else cur.add(2);
				}
				else if(seats.get(i).get(j)==2) {
					if(numOfNeighbour(i,j,2,seats)>=5)cur.add(1);
					else cur.add(2);
				}
				
			}
			ans.add(cur);
		}
		return ans;		
	}
	
	
	private static int numOfNeighbour(int i, int j, int number, List<List<Integer>> seats) {
		int ans=0;
		int[][] pos= {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
		for(int[] cur:pos) {
			int curi=i+cur[0];
			int curj=j+cur[1];
			while(0<=curi&&curi<seats.size()&&0<=curj&&curj<seats.get(i).size()){
				if(seats.get(curi).get(curj)!=0) {
					if(seats.get(curi).get(curj)==number)ans++;
					break;
				}
				else {
					curi+=cur[0];
					curj+=cur[1];
				}
			}
		}
		return ans;
	}
	
	static boolean same(List<List<Integer>> prev,List<List<Integer>> seats) {
		if(prev==null)return false;
		for (int i = 0; i < prev.size(); i++) {
			for (int j = 0; j < prev.get(i).size(); j++) {
				if(prev.get(i).get(j)!=seats.get(i).get(j))return false;
			}
		}
		return true;
	}
} 