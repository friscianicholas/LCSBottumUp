package dynamicProgramming401;
import java.io.*;
import java.util.*;
public class LCSbottomup {
	public static void main(String[] args) {
		Scanner obj = new Scanner(System.in);
		System.out.print("Enter first 6 character sequence: ");
		String X = obj.nextLine();
		System.out.print("Enter second 6 character sequence: ");
		String Y = obj.nextLine();
		//System.out.println("String X and Y are " + X + " and " + Y);
		//try: applys, python
		int m = X.length();
		int n = Y.length();
		System.out.println("First row and column of tables will be 0s");
		int arrows[][] = new int[m+1][n+1];
		// define "0" to be a diagonal arrow, "1" to be an upwards arrow,
		// and "2" to be a sideways arrow. 
		int lengths[][] = new int[m+1][n+1];
		for(int i = 1; i <= m; i++) { //initializing first row of length to 0
			lengths[i][0] = 0;
		}
		for(int j = 0; j <= n; j++) { //initializing first column of length to 0
			lengths[0][j] = 0;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if(X.charAt(i-1) == Y.charAt(j-1)) {
					lengths[i][j] = lengths[i - 1][j - 1] + 1;
					arrows[i][j] = 0;
				}
				else if(lengths[i - 1][j] >= lengths[i][j-1]) {
					lengths[i][j] = lengths[i-1][j];
					arrows[i][j] = 1;
				}
				else {
					lengths[i][j] = lengths[i][j-1];
					arrows[i][j] = 2;
				}
				// a test System.out.print(X.charAt(i));
			}
			System.out.println("\nPrinting row " + (i + 1) + " of lengths and arrows.");
			print2d(lengths, arrows, m);
		}
		System.out.println("\nFINAL RESULT:");
		printLCS(arrows, X, m, n);
		
	}
	static void print2d(int lengths[][], int arrows[][], int m){
		System.out.println("Printing lengths");
		for(int row[]: lengths) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("Printing arrows - 0 is diagonal, 1 is up, 2 is to the left.");
		for(int row2[]: arrows) {
			System.out.println(Arrays.toString(row2));
		}
	}
	static void printLCS(int arrows[][], String X, int m, int n) { 
		if (m == 0 || n == 0) return;
		if (arrows[m][n] == 0) {
			printLCS(arrows, X, m -1, n -1);
			System.out.print(X.charAt(m-1));
		}
		else if(arrows[m][n] == 1) {
			printLCS(arrows, X, m -1, n);
		}
		else {
			printLCS(arrows, X, m, n-1);
		}
	}
}
