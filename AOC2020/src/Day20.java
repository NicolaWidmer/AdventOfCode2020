import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Day20{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static long sol() {
		File file=new File("Files/20.2.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		long ans=1;
		List<Tile> tiles=new ArrayList<Tile>();
		while(sc.hasNext()) {
			String next="";
			String line=sc.nextLine();
			while(!line.equals("")) {
				next+=line+"\n";
				line=sc.nextLine();
			}
			tiles.add(new Tile(next));
		}
		int numoftile=tiles.size();
		int len=(int)Math.sqrt(numoftile);
		Tile[][] grid=new Tile[len][len];
		int k=0;
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				grid[i][j]=tiles.get(k++);
			}
		}
		for(int i=1;i<len;i++) {
			for(int j=0;j<len;j++) {
				grid[i][j].alling(grid[i-1][j].chars[9]);
			}
		}
		
		char[][] whole=new char[len*8][len*8];
		for(int i=0;i<len;i++) {
			for(int l=1;l<9;l++) {
				for(int j=0;j<len;j++) {
					for(int m=1;m<9;m++) {
						whole[i*8+l-1][j*8+m-1]=grid[i][j].chars[l][m];
					}
				}
			}
		}
		int numOfHashtag=0;
		for(int i=0;i<whole.length;i++) {
			for(int j=0;j<whole.length;j++) {
				if(whole[i][j]=='#')numOfHashtag++;
			}
		}
		return numOfHashtag-15*countMonsters(whole);
 		
		
		//Used to make input good.
		
//		List<Tile> corner=new ArrayList<Tile>();
//		List<Tile> edge=new ArrayList<Tile>();
//		List<Tile> inner=new ArrayList<Tile>();
//		for(Tile t:tiles) {
//			if(t.maxNeighbour(tiles)==2)corner.add(t);
//			if(t.maxNeighbour(tiles)==3)edge.add(t);
//			if(t.maxNeighbour(tiles)==4)inner.add(t);
//		}
//		int len=(int)Math.sqrt(numoftile);
//		Tile[][] grid=new Tile[len][len];
//		grid[0][0]=corner.get(0);
//		for(int i=1;i<grid.length-1;i++) {
//			Tile last=grid[0][i-1];
//			for(Tile t:edge) {
//				if(t.neighbour.contains(last)&&!t.used) {
//					t.used=true;
//					grid[0][i]=t;
//					break;
//				}
//			}
//		}
//		for(Tile t:corner) {
//			if(t.neighbour.contains(grid[0][grid.length-2]))grid[0][grid.length-1]=t;
//		}
//		for(int i=1;i<grid.length-1;i++) {
//			Tile last=grid[i-1][0];
//			for(Tile t:edge) {
//				if(t.neighbour.contains(last)&&!t.used) {
//					t.used=true;
//					grid[i][0]=t;
//					break;
//				}
//			}
//		}
//		for(Tile t:corner) {
//			if(t.neighbour.contains(grid[grid.length-2][0]))grid[grid.length-1][0]=t;
//		}
//		for(int i=1;i<grid.length-1;i++) {
//			Tile last=grid[i-1][grid.length-1];
//			for(Tile t:edge) {
//				if(t.neighbour.contains(last)&&!t.used) {
//					t.used=true;
//					grid[i][grid.length-1]=t;
//					break;
//				}
//			}
//		}
//		for(Tile t:corner) {
//			if(t.neighbour.contains(grid[grid.length-2][grid.length-1]))grid[grid.length-1][grid.length-1]=t;
//		}
//		for(int i=1;i<grid.length-1;i++) {
//			Tile last=grid[grid.length-1][i-1];
//			for(Tile t:edge) {
//				if(t.neighbour.contains(last)&&!t.used) {
//					t.used=true;
//					grid[grid.length-1][i]=t;
//					break;
//				}
//			}
//		}
//		for(Tile t:corner) {
//			if(t.neighbour.contains(grid[grid.length-1][grid.length-2]))grid[grid.length-1][grid.length-1]=t;
//		}
//		for(int i=1;i<grid.length-1;i++) {
//			for(int j=1;j<grid.length-1;j++) {
//				Tile last=grid[i-1][j];
//				for(Tile t:inner) {
//					if(t.neighbour.contains(last)&&!t.used) {
//						t.used=true;
//						grid[i][j]=t;
//						break;
//					}
//				}
//			}
//		}
//		grid[0][0].rotClock();
//		grid[0][0].rotClock();
//		grid[0][0].rotClock();
//		grid[0][1].flipOnY();
//		grid[0][3].flipOnY();
//		grid[0][3].rotClock();
//		grid[0][4].flipOnX();
//		grid[0][5].flipOnX();
//		grid[0][6].rotClock();
//		grid[0][6].rotClock();
//		grid[0][6].rotClock();
//		grid[0][7].flipOnX();
//		grid[0][8].flipOnX();
//		grid[0][8].rotClock();
//		grid[0][9].flipOnX();
//		grid[0][10].flipOnX();
//		grid[0][10].rotClock();
//		grid[0][11].flipOnY();
//		grid[0][11].rotClock();
		
		
//		try {
//			OutputStream stream =new FileOutputStream("Files/20.2.txt");
//			for(int i=0;i<len;i++) {
//				for(int j=0;j<len;j++) {
//					stream.write(grid[i][j].toString().getBytes());
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return ans;
	}
	
	private static int countMonsters(char[][] whole) {
			if(count(whole)!=0)return count(whole);
			for(int i=0;i<4;i++) {
				whole=rotClock(whole);
				if(count(whole)!=0)return count(whole);
			}
			whole=flipOnX(whole);
			if(count(whole)!=0)return count(whole);
			for(int i=0;i<4;i++) {
				whole=rotClock(whole);
				if(count(whole)!=0)return count(whole);
			}
		
		return 0;
	}
	private static int count(char[][] whole) {
		int ans=0;
		for(int i=0;i<whole.length-20;i++) {
			for(int j=0;j<whole.length-3;j++) {
				if(hasMonsterAt(i,j,whole))ans++;
			}
		}
		return ans;
	}
	
	private static boolean hasMonsterAt(int i,int j,char[][] whole){
		int[][] pos=new int[][] {{0,1},{1,2},{4,2},{5,1},{6,1},{7,2},{10,2},{11,1},{12,1},{13,2},{16,2},{17,1},{18,1},{18,0},{19,1}};
		for(int[] cur:pos) {
			if(whole[i+cur[0]][j+cur[1]]!='#')return false;
		}
		return true;
	}
	
	//for square only
	static char[][] rotClock(char[][] arr) {
		char[][] ans=new char[arr.length][arr.length];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				ans[j][arr.length-i-1]=arr[i][j];
			}
		}
		return ans;
	}
	//for square only
	static  char[][] flipOnY( char[][] arr) {
		char[][] ans=new char[arr.length][arr.length];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				ans[i][arr.length-j-1]=arr[i][j];
			}
		}
		return ans;
	}
	//for square only
		static  char[][] flipOnX( char[][] arr) {
			char[][] ans=new char[arr.length][arr.length];
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr.length;j++) {
					ans[arr.length-i-1][j]=arr[i][j];
				}
			}
			return ans;
		}
	
} 

class Tile{
	int num;
	String[] edge;
	String[] rotedge;
	char[][] chars;
	List<Tile> neighbour;
	boolean used;
	Tile() {
		
	}
	Tile(String tile){
		chars=new char[10][10];
		edge=new String[4];
		rotedge=new String[4];
		neighbour=new ArrayList<Tile>();
		Scanner sc=new Scanner(tile.replace(":",""));
		sc.next();
		num=sc.nextInt();
		String line=sc.nextLine();
		line=sc.nextLine();
		int i=0;
		chars[i++]=line.toCharArray();
		edge[0]=line;
		edge[3]=""+line.charAt(0);
		edge[1]=""+line.charAt(line.length()-1);
		while(sc.hasNext()) {
			line=sc.nextLine();
			chars[i++]=line.toCharArray();
			edge[3]=line.charAt(0)+edge[3];
			edge[1]=edge[1]+line.charAt(line.length()-1);
			if(!sc.hasNext())edge[2]=line;
		}
		for(int j=0;j<4;j++) {
			rotedge[j]=rotate(edge[j]);
		}
	}
	
	/*
	 * inputs are nice every tile has a unique set of possible neighours
	 * */
	int maxNeighbour(List<Tile> tiles) {
		int ans=0;

		for(String e:edge) {
			boolean has=false;
			for(Tile t:tiles) {
				if(t!=this) {
					for(String e1:t.edge)
						if(e.equals(e1)) {
							if(has)System.out.println("multiple");
							has=true;
							neighbour.add(t);
						}
					for(String e2:t.rotedge)
						if(e.equals(e2)) {
							if(has)System.out.println("multiple");
							has=true;
							neighbour.add(t);
						}
				}
			}
			if(has)ans++;
		}
		return ans;
	}
	
	//allines the grid such that chars[0]==top
	public void alling(char[] top) {
		if(Arrays.equals(top, chars[0]))return;
		for(int i=0;i<4;i++) {
			rotClock();
			if(Arrays.equals(top, chars[0]))return;
		}
		flipOnX();
		if(Arrays.equals(top, chars[0]))return;
		for(int i=0;i<4;i++) {
			rotClock();
			if(Arrays.equals(top, chars[0]))return;
		}
		System.out.println("error "+num);
	}
	
	public String toString() {
		String ans="Tile "+num+":\n";
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				ans+=chars[i][j];
			}
			ans+="\n";
		}
		ans+="\n";
		return ans;
	}
	
	void rotClock() {
		chars=Day20.rotClock(chars);
	}
	void flipOnY() {
		chars=Day20.flipOnY(chars);
	}
	void flipOnX() {
		chars=Day20.flipOnX(chars);
	}
	
	String rotate(String s) {
		String ans="";
		for(int i=0;i<s.length();i++) {
			ans=s.charAt(i)+ans;
		}
		return ans;
	}
}