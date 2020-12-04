import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
	

	static List<String> color;
	static List<Character> chars;
	
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/4.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		String[] in= {"byr",
				"iyr" ,
				"eyr" ,
				"hgt" ,
				"hcl" ,
				"ecl" ,
				"pid"};

		List<String> ins=new ArrayList<String>();
		for(String s:in) {
			ins.add(s);
		}
		char[] c= {'a','b','c','d','e','f'};
		String[] clr= {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
		color=new ArrayList<String>();
		chars=new ArrayList<Character>();
		for(String s:clr) {
			color.add(s);
		}
		for(char ch:c) {
			chars.add(ch);
		}
		int ans=0;
		String line;
		List<String> cur=new ArrayList<String>();
		boolean legal=true;
		while(sc.hasNext()) {
			line=sc.nextLine();
			if(line.length()==0) {
				if(cur.containsAll(ins)&&legal)ans+=1;
				legal=true;
				cur=new ArrayList<String>();
			}
			else {
				Scanner scan=new Scanner(line);
				while(scan.hasNext()) {
					String next=scan.next();
					String key=next.substring(0,3);
					String val=next.substring(4);
					cur.add(key);
					if(!eval(key,val))legal=false;
				}
				scan.close();
			}
			
		}
		return ans;
	}
	
	static boolean eval(String key,String value) {
		if(key.equals("byr")) {
			return 1920<=valOf(value)&&valOf(value)<=2002;
		}
		else if(key.equals("iyr")) {
			return 2010<=valOf(value)&&valOf(value)<=2020;
		}
		else if(key.equals("eyr")) {
			return 2020<=valOf(value)&&valOf(value)<=2030;
		}
		else if(key.equals("hgt")) {
			if(value.length()<4)return false;
			String mass=value.substring(value.length()-2);
			int height=valOf(value.substring(0, value.length()-2));
			if(mass.equals("cm")) {
				return 150<=height&&height<=193;
			}
			else if(mass.equals("in")) {
				return 59<=height&&height<=76;
			}
			else return false;
		}
		else if(key.equals("hcl")) {
			if(value.length()!=7)return false;
			if(!(value.charAt(0)=='#'))return false;
			for(int i=1;i<7;i++) {
				if(!isHex(value.charAt(i)))return false;
			}
			return true;
		}
		else if(key.equals("ecl")) {
			return color.contains(value);
		}
		else if(key.equals("pid")) {
			if(value.length()!=9)return false;
			for(char c:value.toCharArray()) {
				if(!isZiffer(c))return false;
			}
			return true;
		}
		
		return true;
	}
	static boolean isHex(char c) {
		return isZiffer(c)||chars.contains(c);
	}
	static boolean isZiffer(char c) {
		return 0<=c-'0'&&c-'0'<=9;
	}
	static int valOf(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (Exception e) {
			return -1;
		}
		
	}
}