package gutherie;

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
 */


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
			+ "Welcome to the StreamAnalysis tool." + System.lineSeparator()
			 + System.lineSeparator() 
			+ "StreamAnalysis  Copyright (C) 2015  Jason R. Baker" + System.lineSeparator()
			+ "This program comes with ABSOLUTELY NO WARRANTY." + System.lineSeparator()
			+ "This is free software, and you are welcome to redistribute it" + System.lineSeparator()
			+ "under certain conditions; type `show c' for details.";
	
	private static final String HELP = ""
			+ "h - for help" + System.lineSeparator() + ""
			+ "1 - two string analysis" + System.lineSeparator() + ""
			+ "q - quit.";

}
