import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/12.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		int shipNorth=0;
		int shipEast=0;
		int wayNorth=1;
		int wayEast=10;
		while(sc.hasNext()) {
			String line=sc.next();
			char inst=line.charAt(0);
			int num=Integer.valueOf(line.substring(1));
			if(inst=='N')wayNorth+=num;
			else if(inst=='S')wayNorth-=num;
			else if(inst=='E')wayEast+=num;
			else if(inst=='W')wayEast-=num;
			else if(inst=='F') {
				shipNorth+=num*wayNorth;
				shipEast+=num*wayEast;
			}
			else{
				if(num==180) {
					wayNorth=-wayNorth;
					wayEast=-wayEast;
				}
				else if((inst=='L'&&num==90)||(inst=='R'&&num==270)) {
					int curN=wayNorth;
					wayNorth=wayEast;
					wayEast=-curN;
				}
				else if((inst=='L'&&num==270)||(inst=='R'&&num==90)) {
					int curN=wayNorth;
					wayNorth=-wayEast;
					wayEast=curN;
				}
			}
		}
		return Math.abs(shipNorth)+Math.abs(shipEast);
	}
} 