import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day17{
	public static void main(String[] args) {
		System.out.println(sol());
	}
	public static int sol() {
		File file=new File("Files/17.txt");
		Scanner sc;
		
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc=new Scanner(System.in);
		}
		PocketDimension pocket=new PocketDimension(22, 22, 15,15);
		for(int i=0;sc.hasNext();i++) {
			String line=sc.nextLine();
			for(int j=0;j<line.length();j++) {
				pocket.set(6+i, 6+j, 7,7, line.charAt(j)=='#');
			}
		}
		for(int count=0;count<6;count++) {
			PocketDimension newpocket=new PocketDimension(22, 22, 15,15);
			for(int i=1;i<21;i++) {
				for(int j=1;j<21;j++) {
					for(int k=1;k<14;k++) {
						for(int l=1;l<14;l++) {
							if(pocket.get(i,j,k,l)) {
								int num=pocket.numOfAktiveNeigh(i, j, k,l);
								if(!(num==2||num==3)) newpocket.set(i, j, k,l, false);
								else newpocket.set(i, j, k,l, true);
							}
							else {
								int num=pocket.numOfAktiveNeigh(i, j, k,l);
								if(num==3)newpocket.set(i, j, k,l, true);
								else newpocket.set(i, j, k,l, false);
							}
						}
					}
				}
			}
			
			pocket=newpocket;
		}
		return pocket.getAktive();
	}
}


class PocketDimension{
	boolean[][][][] aktive;
	int dimX,dimY,dimZ,dimW;
	public PocketDimension(int x,int y,int z,int w) {
		aktive=new boolean[x][y][z][w];
		dimX=x;
		dimY=y;
		dimZ=z;
		dimW=w;
	}
	boolean get(int x,int y,int z,int w) {
		return aktive[x][y][z][w];
	}
	void set(int x,int y,int z,int w,boolean setTo) {
		aktive[x][y][z][w]=setTo;
	}
	int getAktive() {
		int ans=0;
		for(int i=0;i<dimX;i++) {
			for(int j=0;j<dimY;j++) {
				for(int k=0;k<dimZ;k++) {
					for(int l=0;l<dimW;l++){
						if(aktive[i][j][k][l])ans+=1;
					}
				}
			}
		}
		return ans;
	}
	int numOfInAktiveNeigh(int x,int y,int z,int w) {
		return 80-numOfAktiveNeigh(x,y,z,w);
	}
	int numOfAktiveNeigh(int x,int y,int z,int w) {
		int ans=0;
		for(int i=-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				for(int k=-1;k<2;k++) {
					for(int l=-1;l<2;l++){
						if(aktive[x+i][y+j][z+k][w+l])ans+=1;
					}
				}
			}
		}
		if(aktive[x][y][z][w])ans-=1;
		return ans;
	}
}
