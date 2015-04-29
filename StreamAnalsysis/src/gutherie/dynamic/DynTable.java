package gutherie.dynamic;


/*
 * Use Needleman-Wunsch Algorithm to compare strings
 * http://en.wikipedia.org/wiki/Needleman-Wunsch_algorithm
 */
public class DynTable {
	public DynTable(String a, String b) throws Exception{
		if (a != null && b != null && a.length() < MAXLENGTH && b.length()< MAXLENGTH){
			charA = a.toCharArray();
			charB = b.toCharArray();
			walk = new int[charA.length + charB.length];
		}
		else {
			throw new Exception("String parameters must not be null nor larger than " + MAXLENGTH + " characters!");
		}
	}
	
	public long analyse(){
		long startTime = System.currentTimeMillis();
		
		// initialize the data array and static entries

		count = new int[charA.length+1][charB.length+1];
		
		for (int i = 0; i < charA.length; i ++){
			for (int j = 0; j < charB.length; j++){
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
		
		for (int i = 0; i < walkCounter; i++){
			sb.append(walk[i] + " ");
		}
		System.out.println("Current walk through the matrix: \n" + sb.toString());
	}
	
	public void dumpMatrix(){
		StringBuffer sb = new StringBuffer();
		
		for (int i = -1; i < charA.length; i++){
			for (int j = -1; j < charB.length; j++){
				if (i == -1){
					if (j == -1 || j == 0){
						sb.append("\t");
					}
					else {
						sb.append(charB[j] + "\t");
					}
				}
				else if (i == 0 && j == -1){
					sb.append("\t");
				}
				else if (j == -1){
					sb.append(charA[i] + "\t");
				}
				else {
					sb.append(count[i][j] + "\t");
				}				
				
				if (j == charA.length-1){
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
		
		if (charA[I] == charB[J]){
			largest += 1;
		}
		else {
			largest -= 1;
		}
		

		walk[walkCounter++] = path;
		return largest;
	}
	
	
	
	private final int MAXLENGTH = 10000;
	private int[] walk = {};
	private int walkCounter = 0;
	private char[] charA = {};
	private char[] charB = {};
	private int[][] count;
}
