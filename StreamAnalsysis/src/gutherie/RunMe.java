package gutherie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class RunMe {

	public static void main(String[] args) {
		System.out.println(INTRO + System.lineSeparator() + HELP);
		String input = "-";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (input.compareToIgnoreCase("q")!=0){
			System.out.print("CMD : ");
			try {
				input= br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage() + System.lineSeparator() + "Please try again.");
			}
		}
		System.exit(0);
	}
	
	private static final String INTRO = ""
			+ "Welcome to the stream analysis tool.";
	private static final String HELP = ""
			+ "h for help" + System.lineSeparator() + ""
			+ "No tools yet!" + System.lineSeparator() + ""
			+ "q to quit.";

}
