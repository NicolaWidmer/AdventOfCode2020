import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day21{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/21.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		
		List<String> all=new ArrayList<String>();
		Map<String,Set<String>> allergToPosIngr=new HashMap<String,Set<String>>();
		
		while(sc.hasNext()) {
			String line=sc.nextLine();
			Set<String> cur=new HashSet<String>();
			Scanner s=new Scanner(line);
			String next=s.next();
			while(!next.equals("(contains")) {
				all.add(next);
				cur.add(next);
				next=s.next();
			}
			while(s.hasNext()) {
				String allerg=s.next().replace(",", "").replace(")", "");
				if(!allergToPosIngr.containsKey(allerg))allergToPosIngr.put(allerg,cur);
				else {
					Set<String> pos=allergToPosIngr.get(allerg);
					Set<String> nextpos=new HashSet<String>();
					for(String str:pos) {
						if(cur.contains(str))nextpos.add(str);
					}
					allergToPosIngr.put(allerg, nextpos);
				}
			}
		}
		Map<String,String> Allerg=new TreeMap<String,String>();
		boolean change=true;
		while(change) {
			String next="";
			String ingr="";
			for(String str:allergToPosIngr.keySet()) {
				if(allergToPosIngr.get(str).size()==1) {
					next=str;
					ingr=allergToPosIngr.get(str).iterator().next();
					break;
				}
			}
			change=!next.equals("");
			if(change) {
				Allerg.put(next,ingr);
				allergToPosIngr.remove(next);
				for(var cur:allergToPosIngr.values()) {
					cur.remove(ingr);
				}
			}
		}
		/*
		 * Part 1
		 */
		for(String cur:all) {
			if(!Allerg.values().contains(cur))ans++;
		}
		/*
		 * Part 2
		 */
		for(String cur:Allerg.values()) {
			System.out.print(cur+",");
		}
		System.out.println();
		return ans;
	}
} 