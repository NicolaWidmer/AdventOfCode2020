import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/1.txt");
		try {
			Scanner sc=new Scanner(file);
			ArrayList<Integer> arr=new ArrayList<Integer>();
			while(sc.hasNext()) {
				arr.add(sc.nextInt());
				for(int i=0;i<arr.size();i++)
					for(int j=i+1;j<arr.size();j++)
						for(int k=j+1;k<arr.size();k++)
							if(arr.get(i)+arr.get(j)+arr.get(k)==2020)return arr.get(i)*arr.get(j)*arr.get(k);
			}
		} catch (FileNotFoundException e) {
			return -1;
		}
		return -2;
	}
}
