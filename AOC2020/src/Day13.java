import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day13{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/13.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		long time=sc.nextLong();
		String line=sc.next();
		String[] arr=line.split(",");
		List<Integer> list=new ArrayList<Integer>();
		List<Integer> times=new ArrayList<Integer>();
		int j=0;
		for(String s:arr) {
			if(!s.equals("x")) {
				list.add(Integer.valueOf(s));
				times.add(j%Integer.valueOf(s));
			}
			j++;
		}
		long[] a=new long[list.size()],m=new long[list.size()];
		for(int i=0;i<list.size();i++) {
			a[i]=(list.get(i)-times.get(i))%list.get(i);
			m[i]=list.get(i);
		}
		return CRT(a,m);
	}
	
	//applies the Chinese Remainder Theorem for coprime m[i]'s
	static long CRT(long[] a,long[] m) {
		long M=1;
		long ans=0;
		for(long l:m) {
			M*=l;
		}
		for(int i=0;i<m.length;i++) {
			long Mi=M/m[i];
			ans+=a[i]*(recInv(m[i],Mi)[2]+m[i])*Mi;
		}
		return ans%M;
	}
	
	//returns {d,s,t} where d = gcd(a,b) and d = s*a + t*b 
	static long[] recInv(long a,long b) {
		if(b==0L)return new long[] {a,1,0};
		long[] ans=recInv(b,a%b);
		long curs=ans[1];
		ans[1]=ans[2];
		ans[2]=curs-ans[2]*(a/b);
		return ans;
	}

} 