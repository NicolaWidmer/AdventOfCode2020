import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8{
	
	public static void main(String[] args) {
		System.out.println(sol());
	}
	
	public static int sol() {
		File file=new File("Files/8.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		Device device=new Device(sc);
		System.out.println(device.comp1());
		return device.comp2();
	}
	
	static class Device{
		private int pc;
		private int n; //number of instructions
		private List<String> instructions;
		private List<Integer> values;
		
		private boolean[] executed;
		
		private boolean hasInfinitLoop;
		
		Device(Scanner sc){
			instructions=new ArrayList<String>();
			values=new ArrayList<Integer>();
			while(sc.hasNext()) {
				instructions.add(sc.next());
				values.add(sc.nextInt());
			}
			n=instructions.size();
		}
		
		private int compute() {
			hasInfinitLoop=false;
			executed=new boolean[n];
			pc=0;
			int ans=0;
			
			while(!executed[pc]) {
				executed[pc]=true;
				
				if(instructions.get(pc).equals("jmp"))
					pc+=values.get(pc);
				else if(instructions.get(pc).equals("acc")) {
					ans+=values.get(pc);
					pc++; }
				else 
				  pc++;
				if(pc>=n) 
				  return ans;
			}
			hasInfinitLoop=true;
			return ans;
		}
		
		int comp1() {
			return compute();
		}
		
		int comp2() {
			int ans=0;
			for(int i=0;i<instructions.size();i++) {
				swap(i);
				ans=compute();
				swap(i);
				if(!hasInfinitLoop)break;
			}
			return ans;
			
		}
		
		private void swap(int i) {
			if(instructions.get(i).equals("jmp")) {
				instructions.set(i, "nop");
			}
			else if(instructions.get(i).equals("nop")) {
				instructions.set(i, "jmp");
			}
		}
	}
} 