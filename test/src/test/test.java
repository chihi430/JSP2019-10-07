package test;

public class test {

	public static void main(String[] args) {
		int a[][] = new int[5][5];
		int r=0;
		int c;
		
		do {
			c=r;
			
			do {
				a[r][c] = 5-r;				
				System.out.print(a[r][c]);
				c++;
				
			}while(c<=4);			
			r++;
				
		}while(r<=4);		
		
	}

}
