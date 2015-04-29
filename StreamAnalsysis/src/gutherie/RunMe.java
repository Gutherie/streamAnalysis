package gutherie;

import gutherie.dynamic.DynTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class RunMe {

	public static void main(String[] args) {
		System.out.println(INTRO + System.lineSeparator() + HELP);
		String input = "";
		
		while ((input = getLine("CMD : ")).compareToIgnoreCase("q")!=0){
			if (input.compareTo("1")==0){
				runCompare();
			}
		}
		System.exit(0);
	}
	
	private static void runCompare(){
		String a = "";
		String b = "";
		boolean done = false;
		
		while (!done){
			a = getLine("String 1 : ");
			if (a.length() > 0 && a.length() < DynTable.MAXLENGTH){
				b = getLine("String 2 : ");
				if (b.length() > 0 && b.length() < DynTable.MAXLENGTH){
					try{
						DynTable dynTable = new DynTable(a,b);
						dynTable.analyse();
						dynTable.dumpPath();
						dynTable.dumpMatrix();
					}catch(Exception e){
						System.out.println("Failed to complete : " + e.getMessage() + System.lineSeparator() + e.getStackTrace());
					}
					
					done = true;
				}
			}
			if (getLine("q to quit, any other to continue").compareToIgnoreCase("q")!=0){
				done = false;
			}
			
		}
		
		
	}
	
	private static String getLine(String question){
		String input = "";
		System.out.print(question);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input= br.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage() + System.lineSeparator() + "Please try again.");
		}
		return input;
	}
	private static final String INTRO = ""
			+ "Welcome to the stream analysis tool.";
	private static final String HELP = ""
			+ "h - for help" + System.lineSeparator() + ""
			+ "1 - two string analysis" + System.lineSeparator() + ""
			+ "q - quit.";

}
