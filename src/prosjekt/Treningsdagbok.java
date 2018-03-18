package prosjekt;

import java.sql.*;
//import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.*;

public class Treningsdagbok {

	private static Økt currentøkt;
	

	public static void main(String[] args) {
		
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","database");
			
			System.out.println("Treningsdagbok SaeterLid, Velkommen");
			while(true){
				//main loop runs here
				Scanner scanner = new Scanner(System.in);
				String mainText = "Velg kategori ved å taste inn tallet i parantes: \n" +
		                "(1) Registrer ny treningsøkt \n" +
		                //"(2) Legg til nye øvelser \n" +
		                "(3) Legg til notat til økt \n" +
		                //"(2) Registrer mål for øvelser \n" +
		                "(4) Lag ny øvelsesgruppe \n" //+
		                //"(6) Legg til resultatlogg \n" +
		                //"(7) Treningsrapport \n" +
		                //"(8) Avslutt";
		                ;
				System.out.println(mainText);
				int choicevar = scanner.nextInt();
				
				switch (choicevar){
				
				case 1: //start new økt
					Økt økt= new Økt(myconn);
					økt.nyØkt(scanner);
					currentøkt = økt;
					break;
					
				case 2: //Legg til Øvelse
					break;
					
				case 3: // legg til notat
					
					Notat notat = new Notat(myconn);
					notat.nyttNotat(scanner,currentøkt);
					break;
				
				case 4:
					new Øvelsesgruppe(myconn,scanner);
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
