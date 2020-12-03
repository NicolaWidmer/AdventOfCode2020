import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/3.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		
		ArrayList<String> list=new ArrayList<String>();
		while(sc.hasNext()) {
			list.add(sc.nextLine());
		}
		int m=list.get(0).length();
		
		int[] down= {1,1,1,1,2};
		int[] right= {1,3,5,7,1};
		int ans=1;
		for(int i=0;i<down.length;i++){
			int d=down[i];
			int r=right[i];
			int curans=0;
			for(int j=d,k=r;j<list.size();j+=d,k=(k+r)%m) {
				curans+=list.get(j).charAt(k)=='.'?0:1;
			}
			ans*=curans;
		}
		sc.close();
		return ans;
	}
}
