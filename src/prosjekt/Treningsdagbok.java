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
				//Kj�rer hovedmenyen.TODO: B�r legge inn en break for � avslutte program fra menyen
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
		                "(4) Lag ny �velsesgruppe \n" +
		                "(5) Hent ut N siste �kter \n"// +
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
					if (current�kt==null){
						System.out.println("Ingen �kt er valgt \n ");
					}else {
						�velse �velse = new �velse(myconn, scanner,current�kt);
					}
					
					break;
					
				case 3: // legg til notat
					if (current�kt==null){
						System.out.println("Ingen �kt er valgt \n ");
					}else {
						Notat notat = new Notat(myconn);
						notat.nyttNotat(scanner,current�kt);
						}
					break;
				
				case 4:
					new �velsesgruppe(myconn,scanner);
					break;
					
				case 5:
					System.out.println("Angi hvor mange �kter du vil se: \n ");
					int n = scanner.nextInt();
					getNlast�kts(n,myconn);
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
	
	public static void get�velsesResultat(Connection myconn, ){
		// list opp �velser registrert i db
		// be brukeren velge hvilken han vil se logg for
		//select something
		
	}
	
	public static void getNlast�kts(int n, Connection myconn){
		int maxID ;
		String st�rsteid =("select max(�ktid) from �kt;");
		try{
			Statement statement = myconn.createStatement();
			ResultSet myRs = statement.executeQuery(st�rsteid);
			if (myRs.next()){
				maxID = (myRs.getInt("max(�ktid)"));
			}else{
				System.out.println("Ingen tidligere �kter i databasen");
				return;

			}
		
		
			String allegrupper = String.format("select * from �kt left join �ktnotat on �kt.�ktid=�ktnotat.�ktid  left join notat on notat.notatid=�ktnotat.notatid  where �kt.�ktid> '%d' ;",maxID-n);
		
			Statement list�kter = myconn.createStatement();
			 myRs = list�kter.executeQuery(allegrupper);
			
			while (myRs.next()){
				String notatt;
				if (myRs.getString("tekstfelt") == null){
					notatt = "";
				}else{
					notatt=myRs.getString("tekstfelt");
				}
				String first = "�kt ID: "+ myRs.getString("�ktid")+ "   "+ "Notat: "+ notatt;
				System.out.println(first);
				//System.out.println("\n");
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}
	

}
