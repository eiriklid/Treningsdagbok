package prosjekt;

import java.sql.*;
import java.util.Locale;
//import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.*;

public class Treningsdagbok {

	private static �kt current�kt;
	
	

	public static void main(String[] args) {

		Locale.setDefault(Locale.US); //For � f� . og ikke , i double stirng format.
		
		try{
			Connection myconn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","database");
			String introText = "***************************************\n" +
								"Treningsdagbok SaeterLid, Velkommen \n"+
								"**************************************\n";
			System.out.println(introText);
			while(true){
				//main loop runs here
				Scanner scanner = new Scanner(System.in);
				if (current�kt==null){
					System.out.println("Ingen �kt er valgt \n ");
				}else {
						System.out.println("N�v�rende valgt �kt er : "+current�kt.get�ktnow() +" ID: "+current�kt.get�ktID());
						//System.out.println(current�kt);
				}
				String mainText = "***************************************\n" +
						"Velg kategori ved � taste inn tallet i parantes: \n" +
		                "(1) Registrer ny trenings�kt \n" +
		                "(2) Legg til �velse(r) \n" +
		                "(3) Legg til notat til �kt \n" +
		                "(4) Lag ny �velsesgruppe \n" //+
		                //"(5) Registrer m�l for �velser \n" +
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
					�velse �velse = new �velse(myconn, scanner);
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
