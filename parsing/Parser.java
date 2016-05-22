package oop.ex5.parsing;

import oop.ex5.filters.*;
import oop.ex5.orders.*;
import oop.ex5.section.Section;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class receives a path to a commands file and parses
 * it by separating the commands and the descriptions according to the ex5 template.
 * It creates for each two FILTER and ORDER command a section that holds the two
 * commands with their parameters that appear in the files. The descriptions themselves are parsed
 * as well according to the template (NAME#VALUE#VALUE for instance), and given to the relevant factory
 * who will create the filter/orders accordingly.
 * @author alonav11.
 *
 */
public class Parser{
	
	private static final String FILTER_COMMAND = "FILTER";
	private static final String ORDER_COMMAND = "ORDER";
	
	private static final String SPLITTING_SYMBOL = "#";
	private static final int STARTING_LINE_NUMBER = 1;
	
	private static final String WARNING_MESSAGE = "Warning in line ";
	
	
	
	private ArrayList<String> warningMessages;
	private String commandFilePath;
	
	/**
	 * Constructor.
	 * @param commandFilePath Path of the command file to parse
	 */
	public Parser(String commandFilePath){
		this.commandFilePath = commandFilePath;
		warningMessages = new ArrayList<String>();
	}

	/**
	 * This method parses the entire given command file and creates the relevant
	 * sections objects with the filter. 
	 * If an order or filter exception arises, an error message is created
	 * and passed on to the sections.
	 * @throws IOException
	 * @throws BadCommandFileException
	 * @return A list of sections, loaded with the relevant filters and orders.
	 * 
	 */
	public ArrayList<Section> parseCommandFile() 
			                          throws IOException,BadCommandFileException{
 		ArrayList<Section> sections = new ArrayList<>();
		LinkedList<String> parsedFilterLine,parsedOrderLine;
		FileFilter filter;
		FileOrder order;
		
		ArrayList<String> fileLines = getFileLines(new File(commandFilePath));
		int i = 0;
		
		while(i < fileLines.size()){
			// Parse filter subsection
			if(fileLines.get(i).equals(FILTER_COMMAND)){
				i++;
				parsedFilterLine  = new LinkedList<String>(Arrays.asList
						                            (fileLines.get(i).split(SPLITTING_SYMBOL)));
				filter = createFilter(parsedFilterLine, i+STARTING_LINE_NUMBER);
			}else{
				throw new BadSubsectionNameException();
			}	
			
			//Parse order subsection
			i++;
			
			if(i>=fileLines.size()){
				throw new BadCommandFileException();
			} 
			
			if(fileLines.get(i).equals(ORDER_COMMAND)){
				i++;
				if(i >= fileLines.size()){
					order = OrderFactory.createOrder();  //In this place we have reached 
					                                     //the end of the file.
					sections.add(new Section(filter,order, warningMessages));
					break;
					
				}else if(fileLines.get(i).equals(FILTER_COMMAND)){
					order = OrderFactory.createOrder(); //In this place we have an empty
													    // order, as FILTER is the next line.
				}else{
					parsedOrderLine = new LinkedList<String>(
							  Arrays.asList(fileLines.get(i).split(SPLITTING_SYMBOL)));
					order = createOrder(parsedOrderLine, i+STARTING_LINE_NUMBER);
					i++;
				}
			}else{
				throw new BadCommandFileFormatException();
			}
			
			sections.add(new Section(filter,order,warningMessages));
			warningMessages = new ArrayList<String>(); //Clearing the warning list
		}
		return sections;
	}
	
	/*
	 * Receives a file and returns all its lines as strings in an array list
	 */
	private ArrayList<String> getFileLines(File file) throws FileNotFoundException{
		Scanner scanner = new Scanner(file);
		ArrayList<String> lines = new ArrayList<String>();
		while(scanner.hasNextLine()){
			lines.add(scanner.nextLine());
		}
		scanner.close();
		return lines;
		
	}
	
	/**
	 * Receives a Filter's parsed line and returns a relevant filter.
	 * If a filter exception arises, creates a warning message and returns the
	 * default filter.
	 * @param parsedFilterLine The filter's line in the command file
	 * @param lineIndex The line index of the given line
	 * @return Relevant FileFilter
	 */
	private FileFilter createFilter(LinkedList<String> parsedFilterLine, int lineIndex){
		// Removes the first section, which is the name of the filter, and gives it to
		// The factory as a separate parameter.
		try{
			return FilterFactory.createFilter(parsedFilterLine.removeFirst(), 
					                                                parsedFilterLine);
		}
		catch(BadFilterException e){ 
			warningMessages.add(WARNING_MESSAGE + (lineIndex));
			return FilterFactory.createFilter();
		}
	}
	
	/**
	 * Receives a Order's parsed line and returns a relevant order.
	 * If an order exception arises, prints a warning message and returns the
	 * default order.
	 * @param parsedOrderLine The order's line in the command file
	 * @param lineIndex The line index of the given line
	 * @return Relevant FileOrder
	 */
	private FileOrder createOrder(LinkedList<String> parsedOrderLine, int lineIndex){
		// Removes the first section, which is the name of the order, and gives it to
		// The factory as a separate parameter.
		try{
			return OrderFactory.createOrder(parsedOrderLine.removeFirst(), 
					                                                parsedOrderLine);
		}
		catch(BadOrderException e){ 
			warningMessages.add(WARNING_MESSAGE + (lineIndex));
			return OrderFactory.createOrder();
		}
	}
	
}
		
		

	
	
	

	

