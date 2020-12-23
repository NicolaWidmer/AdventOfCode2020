import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day23{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/23.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		String str=sc.next();
		int numNodes= 1000000;
		int numRounds=10000000;
		CircularList list=new CircularList(numNodes);
		for(char c:str.toCharArray())list.add(Integer.valueOf(""+c));
		for(int i=10;i<=numNodes;i++) {
			list.add(i);
		}
		for(int i=0;i<numRounds;i++) {
			Tripple next=list.removeTripple();
			int curCup=list.next();
			int destCup=curCup==1?numNodes:curCup-1;
			while(next.contains(destCup))destCup=destCup==1?numNodes:destCup-1;
			list.addTripple(next, destCup);
		}
		long ans=1;
		ans*=list.nodes[1].next.val;
		ans*=list.nodes[1].next.next.val;
		return ans;
	}

} 

class Node{
	int val;
	Node next;
	Node(int v){
		val=v;
	}
}
class Tripple{
	Node first;
	Node last;
	boolean contains(int i) {
		return first.val==i||first.next.val==i||last.val==i;
	}
}
class CircularList{
	Node last;
	Node[] nodes;
	CircularList(int cap){
		nodes=new Node[cap+1];
	}
	void add(int val) {
		Node neu=new Node(val);
		nodes[val]=neu;
		if(last==null) {
			last=neu;
			last.next=last;
		}
		else {
			Node first=last.next;
			last.next=neu;
			last=last.next;
			last.next=first;
		}
	}
	Tripple removeTripple() {
		Tripple ans=new Tripple();
		ans.first=last.next.next;
		ans.last=ans.first.next.next;
		last.next.next=ans.last.next;
		return ans;
	}
	void addTripple(Tripple add,int i) {
		Node cur=nodes[i];
		Node next=cur.next;
		cur.next=add.first;
		add.last.next=next;
		if(cur==last)last=add.last;
	}
	int next() {
		last=last.next;
		return last.val;
	}
	public String toString() {
		String ans="";
		for(Node cur=last.next;cur!=last;cur=cur.next) {
			ans+=cur.val+",";
		}
		ans+=last.val;
		return ans;
	}
}