package prosjekt;

import java.sql.*;
import java.util.Locale;
//import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.*;

public class Treningsdagbok {

	private static Økt currentøkt;
	
	

	public static void main(String[] args) {

		Locale.setDefault(Locale.US); //For å få . og ikke , i double stirng format.
		
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","database");
			String introText = "***************************************\n" +
								"Treningsdagbok SaeterLid, Velkommen \n"+
								"**************************************\n";
			System.out.println(introText);
			while(true){
				//main loop runs here
				Scanner scanner = new Scanner(System.in);
				if (currentøkt==null){
					System.out.println("Ingen økt er valgt \n ");
				}else {
						System.out.println("Nåværende valgt økt er : "+currentøkt.getØktnow() +" ID: "+currentøkt.getØktID());
						//System.out.println(currentøkt);
				}
				String mainText = "***************************************\n" +
						"Velg kategori ved å taste inn tallet i parantes: \n" +
		                "(1) Registrer ny treningsøkt \n" +
		                "(2) Legg til øvelse(r) \n" +
		                "(3) Legg til notat til økt \n" +
		                "(4) Lag ny øvelsesgruppe \n" //+
		                //"(5) Registrer mål for øvelser \n" +
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
					Øvelse øvelse = new Øvelse(myconn, scanner);
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
