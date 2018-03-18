package prosjekt;

import java.sql.*;
//import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.*;

public class Treningsdagbok {

	private static �kt current�kt;
	

	public static void main(String[] args) {
		
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","database");
			
			System.out.println("Treningsdagbok SaeterLid, Velkommen");
			while(true){
				//main loop runs here
				Scanner scanner = new Scanner(System.in);
				String mainText = "Velg kategori ved � taste inn tallet i parantes: \n" +
		                "(1) Registrer ny trenings�kt \n" +
		                //"(2) Legg til nye �velser \n" +
		                "(3) Legg til notat til �kt \n" +
		                //"(2) Registrer m�l for �velser \n" +
		                "(4) Lag ny �velsesgruppe \n" //+
		                //"(6) Legg til resultatlogg \n" +
		                //"(7) Treningsrapport \n" +
		                //"(8) Avslutt";
		                ;
				System.out.println(mainText);
				int choicevar = scanner.nextInt();
				
				switch (choicevar){
				
				case 1: //start new �kt
					�kt �kt= new �kt(myconn);
					�kt.ny�kt(scanner);
					current�kt = �kt;
					break;
					
				case 2: //Legg til �velse
					break;
					
				case 3: // legg til notat
					
					Notat notat = new Notat(myconn);
					notat.nyttNotat(scanner,current�kt);
					break;
				
				case 4:
					new �velsesgruppe(myconn,scanner);
					break;
					
				
				default:
					System.out.println("Ukjent nummer");
					break;
				}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		

	}
	
	
	

}
