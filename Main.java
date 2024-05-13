import java.io.*;
import java.util.Scanner;
public class Main {
	static Date today = new Date(1, 1, 2021);
	static int CountOfDay = 0;
	static Scanner scan = new Scanner(System.in);	
	public static void main(String[] args) throws IOException {
		while(true) {
			System.out.print(">>>");
			String command = scan.next();
			if(command.startsWith("load")) {
				String[] filePath = command.split(";");
				FileOperations.uploadInput(filePath[1]);
			}
			else {
				FileOperations.findCommand(command);
			}		
		}
	}
}
