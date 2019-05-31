package edu.handong.csee.java.examples;

import java.io.File;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Runner {
	
	String path;
	String fullpath;
	boolean verbose;
	boolean help;
	int number =0;
	

	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);

	}

	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}
			
			// path is required (necessary) data so no need to have a branch.
			
			
			// TODO show the number of files in the path
			
			if(path!=null) {
				System.out.println("You provided \"" + path + "\" as the value of the option p");
				
				File dir = new File(path);
				
				for(File file : dir.listFiles()){
					
						number ++;
				}
				
				System.out.println("number of files in the path : "+number);
				
			}else if(fullpath!=null) {
				System.out.println("You provided \"" + fullpath + "\" as the value of the option f");
				
				File dir = new File(fullpath);
				
				for(File file : dir.listFiles()){
					
						number ++;
						
				}
				
				System.out.println("number of files in the path : "+number);
				
			}
			
			
			
			if(verbose) {
				
				if(path!=null) {
					File dir = new File(path);
					
					for(File file : dir.listFiles()){
						
							System.out.println(file.getName());
					}
					
				}else if(fullpath!=null) {
					File dir = new File(fullpath);
					
					for(File file : dir.listFiles()){
						
							System.out.println(file.getAbsolutePath());
					}
					
				}
					
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!)");
			}
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			fullpath = cmd.getOptionValue("f");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");
			

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.build());
		
		options.addOption(Option.builder("f").longOpt("fullpath")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.build());
		
	
		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

}
