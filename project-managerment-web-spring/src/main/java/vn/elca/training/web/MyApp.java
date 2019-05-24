package vn.elca.training.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class MyApp {
	
	static Logger logger=Logger.getLogger(MyApp.class.getName());
	
	public static void main(String[] args) {
		try {
			doSomething();
		} catch (MyNumberFormatException e) {
			e.printStackTrace();
		}
//		
//		try {
//			String s = readFile("C:\\Users\\btd\\Desktop\\input.txt");
//			System.out.println(s);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private static void doSomething() throws MyNumberFormatException {
		try {
			Integer.parseInt("xxx");
		} catch (NumberFormatException e) {
			throw new MyNumberFormatException("Not an integer", e);
		}
	}

	private static String readFile(String filePath) throws IOException {
		File f = new File(filePath);
		int iLength=0;
		try {
			iLength = (int) f.length();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		FileReader fe = new FileReader(f);
		BufferedReader br = new BufferedReader(fe);
		String line;

		 try {
		        StringBuilder sb = new StringBuilder(iLength);
		        line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		        return sb.toString();
		    } finally {
		    	try {
					br.close();
				} catch (IOException e) {
					logger.error("Error occured when closing file", e);
				}

		    }
	}
}

class MyNumberFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyNumberFormatException(String msg, Throwable orgEx) {
		super(msg, orgEx);
	}
}
