package prosjekt;

import java.sql.*;
import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.*;

public class Treningsdagbok {
	private enum Choice{
		NEW_Session("new session"),
		Exit("End program");
		
		private String text;
		
		Choice(String text){
			this.text=text;
		}
	}
	private static �kt current�kt;
	

	public static void main(String[] args) {
		
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","database");
			
			System.out.println("Treningsdagbok SaeterLid, Velkommen");
			while(true){
				//main loop runs here
				Scanner scanner = new Scanner(System.in);
				System.out.println("Angi valg 1-inf");
				int choicevar = scanner.nextInt();
				
				switch (choicevar){
				
				case 1: //start new �kt
					�kt �kt= new �kt(myconn);
					�kt.ny�kt(scanner);
					current�kt = �kt;
					break;
					
				case 2: //start ned �velse
					break;
					
				case 3: // legg til notat
					
					Notat notat = new Notat(myconn);
					notat.nyttNotat(scanner,current�kt);
					break;
					
				
				default:
					break;
				}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		

	}
	
	
	

}
