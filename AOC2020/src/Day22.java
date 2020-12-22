import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day22{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/22.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int ans=0;
		List<Integer> p1=new ArrayList<Integer>();
		List<Integer> p2=new ArrayList<Integer>();
		sc.nextLine();
		while(sc.hasNextInt()) {
			p1.add(sc.nextInt());
		}
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		while(sc.hasNextInt()) {
			p2.add(sc.nextInt());
		}
		Set<GameState> set=new HashSet<GameState>();
		outer:while(p1.size()!=0&&p2.size()!=0) {
			GameState gs=new GameState(p1,p2);
//			if(set.contains(gs))break; //why does this not work????? rip 1 h
			for(GameState g:set) {
				if(g.equals(gs)) {
					break outer;
				}
			}
			set.add(gs);
			int one=p1.remove(0);
			int two=p2.remove(0);
			if(p1.size()<one||p2.size()<two) {
				if(one>two) {
					p1.add(one);
					p1.add(two);
				}
				else {
					p2.add(two);
					p2.add(one);
				}
			}
			else {
				RecurisvGame rg=new RecurisvGame(subList(p1,one),subList(p2,two));
				if(rg.run()) {
					p1.add(one);
					p1.add(two);
				}
				else {
					p2.add(two);
					p2.add(one);
				}
			}
			
		}
		if(p1.size()==0)p1=p2;
		System.out.println(p1);
		for(int i=0,mul=p1.size();i<p1.size();i++,mul--)ans+=mul*p1.get(i);
		return ans;
	}
	static List<Integer> subList(List<Integer> list,int len){
		List<Integer> ans=new ArrayList<Integer>();
		for(int i=0;i<len;i++) {
			ans.add(list.get(i));
		}
		return ans;
	}
 }

class RecurisvGame{
	List<Integer> p1;
	List<Integer> p2;
	Set<GameState> set;
	RecurisvGame(List<Integer> p1,List<Integer> p2){
		this.p1=p1;
		this.p2=p2;
		set=new HashSet<GameState>();
	}
	boolean run() {
		outer:while(p1.size()!=0&&p2.size()!=0) {
			GameState gs=new GameState(p1,p2);
//			if(set.contains(gs))return true; //why does this not work?????
			for(GameState g:set) {
				if(g.equals(gs))return true;
			}
			set.add(gs);
			int one=p1.remove(0);
			int two=p2.remove(0);
			if(p1.size()<one||p2.size()<two) {
				if(one>two) {
					p1.add(one);
					p1.add(two);
				}
				else {
					p2.add(two);
					p2.add(one);
				}
			}
			else {
				RecurisvGame rg=new RecurisvGame(Day22.subList(p1,one),Day22.subList(p2,two));
				if(rg.run()) {
					p1.add(one);
					p1.add(two);
				}
				else {
					p2.add(two);
					p2.add(one);
				}
			}
			
		}
		return p2.size()==0;
	}
}
class GameState{
	List<Integer> p1;
	List<Integer> p2;
	GameState(List<Integer> p1,List<Integer> p2){
		this.p1=new ArrayList<Integer>();
		for(int i:p1) {
			this.p1.add(i);
		}
		this.p2=new ArrayList<Integer>();
		for(int i:p2) {
			this.p2.add(i);
		}
	}
	public boolean equals(Object other) {
		return p1.equals(((GameState)other).p1)&&p2.equals(((GameState)other).p2);
	}
	public String toString() {
		return p1.toString()+" "+p2.toString();
	}
}