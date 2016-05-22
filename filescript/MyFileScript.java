package oop.ex5.filescript;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import oop.ex5.parsing.BadCommandFileException;
import oop.ex5.parsing.Parser;
import oop.ex5.section.Section;

/**
 * Receives a commands file and a directory and prints the filtered files
 * from the directory in the orders specified in the commands file. 
 */
public class MyFileScript {
	
	private static final int VALID_NUM_OF_ARGUMENTS = 2;
	private static final int SOURCE_DIR_PATH = 0;
	private static final int COMMAND_FILE_PATH = 1;
	private static final String ERROR_MESSAGE = "ERROR";
	
	/**
	 * Main method - runs the application. Calls the parser and receives a list
	 * of section. Then uses the sections to print the relevant files and
	 * warnings.
	 *  @param args 2 arguments: source directory and command file path. 
	 */
	public static void main(String args[]){
		File directory;
		Parser parser;
		
		// If there aren't two arguments, print an error message and quit
		if(args.length != VALID_NUM_OF_ARGUMENTS){
			System.err.println(ERROR_MESSAGE);
			return;
		}
		
		directory = new File(args[SOURCE_DIR_PATH]);
		parser = new Parser(args[COMMAND_FILE_PATH]);
		
		try{
			ArrayList<Section> sections = parser.parseCommandFile();
			for(Section section:sections){
				printWarningMessages(section.getWarningMessages());
				printFiles(section.filterAndSort(directory));
				
			}
		}
		catch(BadCommandFileException e){
			System.err.println(ERROR_MESSAGE);
		}catch (IOException e) {
			System.err.println(ERROR_MESSAGE);
		}
		
	}
	
	/*
	 * Receives an array of files and prints their names to the screen
	 */
	private static void printFiles(File[] files){
		for(File file:files){
			System.out.println(file.getName());
		}	
	}
	
	/*
	 * Receives an array of message Strings and prints them to the
	 * error stream.
	 */
	private static void printWarningMessages(ArrayList<String> warningMessages){
		for(String warning: warningMessages){
			System.err.println(warning);
		}
	}

}
