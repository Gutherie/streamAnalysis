package gutherie.dynamic;


/*
 *   This file is part of StreamAnalysis.
 *
 *   StreamAnalysis is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   StreamAnalysis is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with StreamAnalysis.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * 
 *   Use Needleman-Wunsch Algorithm to compare strings
 *   http://en.wikipedia.org/wiki/Needleman-Wunsch_algorithm
 */
public class DynTable {
	public DynTable(String a, String b) throws Exception{
		if (a != null && b != null && a.length() < MAXLENGTH && b.length()< MAXLENGTH){
			charA = a.toCharArray();
			charB = b.toCharArray();
			walk = new int[charA.length][charB.length];
		}
		else {
			throw new Exception("String parameters must not be null nor larger than " + MAXLENGTH + " characters!");
		}
	}
	
	public long analyse(){
		long startTime = System.currentTimeMillis();
		
		// initialize the data array and static entries

		count = new int[charA.length+1][charB.length+1];
		
		for (int i = 0; i < charA.length+1; i ++){
			for (int j = 0; j < charB.length+1; j++){
				if (i==0 && j==0){
					count[i][j] = 0;
				}
				else if (i==0){
					count[i][j] = (-1)*j;
				}
				else if (j==0){
					count[i][j] = (-1)*i;
				}
				else {
					count[i][j] = calculate(count,i,j);
				}
			}
		}
		
		
		
				
		return System.currentTimeMillis() - startTime;
	}
	
	public void dumpPath(){
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < walk.length; i++){
			for (int j = 0; j< walk[0].length; j++){
				sb.append(walk[i][j] + "\t");
				if (j == walk[0].length-1){
					sb.append("\n");
				}
			}
			
		}
		System.out.println("Current walk through the matrix: \n" + sb.toString());
	}
	
	public void dumpMatrix(){
		StringBuffer sb = new StringBuffer();
		
		for (int i = -1; i <= charA.length; i++){
			for (int j = -1; j <= charB.length; j++){
				if (i == -1){
					if (j == -1 || j == 0){
						sb.append("\t");
					}
					else {
						sb.append(charB[j-1] + "\t");
					}
				}
				else if (i == 0 && j == -1){
					sb.append("\t");
				}
				else if (j == -1){
					sb.append(charA[i-1] + "\t");
				}
				else {
					sb.append(count[i][j] + "\t");
				}				
				
				if (j == (charB.length)){
					sb.append("\n");
				}
			}
		}
		System.out.println("Current walk through the matrix: \n" + sb.toString());		
	}
	
	/*
	 * Single path through the matrix
	 * 
	 * Path is recorded as 1,2 or 3. 
	 * 	- 1 is diagonal
	 *  - 2 is up
	 *  - 3 is left
	 */
	private int calculate(int[][] values, int I, int J){
		// which previous value is largest (first case, not all)
		int largest = values[I-1][J-1];
		int path = 1;
		if (largest < values[I-1][J]){
			largest = values[I-1][J];
			path = 2;
		}
		if (largest < values[I][J-1]){
			largest = values[I][J-1];
			path = 3;
		}
		
		// compare characters, case sensitive
		
		if (charA[I-1] == charB[J-1]){
			largest += 1;
		}
		else {
			largest -= 1;
		}
		

		walk[I-1][J-1] = path;
		return largest;
	}
		
	public static final int MAXLENGTH = 10000;
	private char[] charA = {};
	private char[] charB = {};
	private int[][] count;
	private int[][] walk;
}
