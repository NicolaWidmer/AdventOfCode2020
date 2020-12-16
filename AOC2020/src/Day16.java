import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16{
	
	public static void main(String[] args) {
		System.out.println(sol());
	}
	
	public static long sol() {
		File file=new File("Files/16.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		
		List<Integer> myTicket=new ArrayList<Integer>(); 				//numbers on my ticket
		
		List<List<Integer>> tickets=new ArrayList<List<Integer>>();		//list of tickets near me
		
		List<List<Integer>> intervals=new ArrayList<List<Integer>>();	//list of intervals for the given fields for field i
																		//the number has to be between intervals.get(i).get(0)
																		//and intervals.get(i).get(1) and between 
																		//intervals.get(i).get(2) and intervals.get(i).get(3)
		
		List<Integer> departure=new ArrayList<Integer>(); 				//indexes of the departure fields
		
		boolean[] contains=new boolean[1000];							//contains[î] is true <=> i is valid for some field
		
		//input scanning
		String line=sc.nextLine();
		for(int i=0;!line.equals("");i++) {
			if(line.startsWith("departure"))departure.add(i);
			line=line.split(":")[1];
			line=line.replace("-", " ");
			line=line.replace(" or", "");
			Scanner s=new Scanner(line);
			List<Integer> inter=new ArrayList<Integer>();
			for(int j=0;j<2;j++) {
				int start=s.nextInt();
				int end=s.nextInt();
				inter.add(start);
				inter.add(end);
				for(int k=start;k<=end;k++) {
					contains[k]=true;
				}
			}
			intervals.add(inter);
			line=sc.nextLine();
		}
		
		sc.nextLine();
		for(String cur:sc.nextLine().split(",")) {
			myTicket.add(Integer.valueOf(cur));
		}
		tickets.add(myTicket);
		
		sc.nextLine();
		sc.nextLine();
		
		while(sc.hasNext()) {
			boolean add=true;
			List<Integer> ticket=new ArrayList<Integer>();
			for(String cur:sc.nextLine().split(",")) {
				int i=Integer.valueOf(cur);
				ticket.add(i);
				if(!contains[i])add=false;
			}
			if(add)tickets.add(ticket);
		}
		//end of input scanning
		
		int n=intervals.size();
		int m=tickets.size();
		boolean[][][] conected=new boolean[n][m][n];// i j k is true if the i interval could belong to the k entry of the j ticket:
		for(int i=0;i<n;i++) {
			List<Integer> inter=intervals.get(i);
			for(int j=0;j<m;j++) {
				List<Integer> ticket=tickets.get(j);
				for(int k=0;k<n;k++) {
					int num=ticket.get(k);
					conected[i][j][k]=(inter.get(0)<=num&&num<=inter.get(1))||(inter.get(2)<=num&&num<=inter.get(3));
				}
			}
		}
		
		Map<Integer,Integer> intervalToEntry=new HashMap<Integer,Integer>();
		
		while(true) {
			
			int[] next=findeNextEntry(n,m,conected);
			if(next==null)break;
			intervalToEntry.put(next[0], next[1]);
			
			//Update the possible connections
			for(int k=0;k<n;k++) {
				for(int j=0;j<m;j++) {
					conected[next[0]][j][k]=false;
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					conected[i][j][next[1]]=false;
				}
			}
			
		}
		
		long ans=1;
		for(int i:departure) {
			ans*=myTicket.get(intervalToEntry.get(i));
		}
		
		return ans;
	}
	
	//find the next field and entry combination and return [field, entry].
	private static int[] findeNextEntry(int n, int m, boolean[][][] conected) {
		int[] ans=new int[2];
		for(int i=0;i<n;i++) {
			boolean hasFirst=false;
			boolean hasSecond=false;
			for(int k=0;k<n;k++) {
				boolean isPosible=true;
				for(int j=0;j<m;j++) {
					if(!conected[i][j][k])isPosible=false;
				}
				if(isPosible&&!hasFirst) {
					hasFirst=true;
					ans=new int[]{i,k};
				}
				else if(isPosible&&hasFirst) {
					hasSecond=true;
				}
			}
			if(hasFirst&&!hasSecond)return ans;
		}
		return null;
	}
	
	
} 