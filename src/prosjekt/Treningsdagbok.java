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
				//Kjører hovedmenyen.TODO: Bør legge inn en break for å avslutte program fra menyen
				Scanner scanner = new Scanner(System.in);
				if (currentøkt==null){
					System.out.println("\nIngen økt er valgt  ");
				}else {
						System.out.println("Nåværende valgt økt er : "+currentøkt.getØktnow() +" ID: "+currentøkt.getØktID());
						//System.out.println(currentøkt);
				}
				String mainText = "***************************************\n" +
						"Velg kategori ved å taste inn tallet i parantes: \n" +
		                "(1) Registrer ny treningsøkt \n" +
		                "(2) Legg til øvelse(r) \n" +
		                "(3) Legg til notat til økt \n" +
		                "(4) Lag ny øvelsesgruppe \n" +
		                "(5) Hent ut N siste økter \n" +
		                "(6) Se resultatlogg for en øvelse \n" +
		                "(7) Se Øvelsesgrupper og tilhørende øvelser \n" +
		                "(8) Se rangering av øvelser \n"+
		                "(9) Avslutt";
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
					if (currentøkt==null){
						System.out.println("Ingen økt er valgt \n ");
					}else {
						Øvelse øvelse = new Øvelse(myconn, scanner,currentøkt);
					}
					
					break;
					
				case 3: // legg til notat
					if (currentøkt==null){
						System.out.println("Ingen økt er valgt \n ");
					}else {
						Notat notat = new Notat(myconn);
						notat.nyttNotat(scanner,currentøkt);
						}
					break;
				
				case 4:
					new Øvelsesgruppe(myconn,scanner);
					break;
					
				case 5:
					System.out.println("Angi hvor mange økter du vil se: \n ");
					int n = scanner.nextInt();
					getNlastØkts(n,myconn);
					break;
				case 6:
					getØvelsesResultat(myconn,scanner);
					break;
				case 7:
					getØvelsesgruppe( myconn, scanner);
					break;
				case 8:
					getStatistikk(myconn);
					break;
				case 9:
					return;
					
				
				default:
					System.out.println("Ukjent nummer");
					break;
				}
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		

	}
	public static void getStatistikk(Connection myconn){
		String statQuery = "select øvelse.øvelsesnavn,avg(øvelseiøkt.prestasjon) as sort from øvelse "+
								"join øvelseiøkt on øvelse.øvelsesnavn = øvelseiøkt.øvelsesnavn "+
									"group by øvelse.øvelsesnavn "+
										"order by sort desc;";
		try{
			Statement statement = myconn.createStatement();
			ResultSet rs = statement.executeQuery(statQuery);
			while(rs.next()){
				System.out.println("Øvelse: "+ rs.getString("øvelsesnavn") + " Snittprestasjon: "+rs.getString("sort")  );
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
	}
	
	public static void getØvelsesResultat(Connection myconn,Scanner scanner ){
		// list opp øvelser registrert i db
		// be brukeren velge hvilken han vil se logg for
		int maxID ;
		String størsteid =("select max(øktid) from økt;");
		//select something
		String alleøvelser= String.format("select * from Øvelse ;");
		try{
			Statement statement = myconn.createStatement();
			ResultSet myRs = statement.executeQuery(størsteid);
			if (myRs.next()){
				maxID = (myRs.getInt("max(øktid)"));
			}else{
				System.out.println("Fins ikke øvelses så langt tilbake. Beklager");
				return;

			}
			Statement listøvelser = myconn.createStatement();
			 myRs = listøvelser.executeQuery(alleøvelser);
			System.out.println("Øvelser registrert i databasen:\n");
			while (myRs.next()){
				String first = myRs.getString("øvelsesnavn");
				System.out.println(first);
				//System.out.println("\n");
			}
			System.out.println("Hvilken øvelse vil du se resultatlogg for?:\n");
			String øvelsesvalg = scanner.next();
			System.out.println("Hvor langt tilbake vil du se loggen?:\n");
			int periode = scanner.nextInt();
			//build query
			String getøvelseslogg= String.format("select * from øvelse join øvelseiøkt on øvelse.øvelsesnavn = øvelseiøkt.øvelsesnavn where( øvelse.øvelsesnavn='%s' and øktid > '%d');",øvelsesvalg,maxID-periode);
			
			 statement = myconn.createStatement();
			myRs = listøvelser.executeQuery(getøvelseslogg);
			while (myRs.next()){
				String first = "Økt ID: "+ myRs.getString("øktid")+ "   "+ "Prestasjon: "+myRs.getString("prestasjon");
				System.out.println(first);
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public static void getØvelsesgruppe(Connection myconn,Scanner scanner){
		
		//het ut alle øvelsesgrupper
		String muskelQuery =("select muskelgruppe from øvelsesgruppe;");
		try{
			Statement statement = myconn.createStatement();
			ResultSet myRs = statement.executeQuery(muskelQuery);
			while (myRs.next()){
				String muskelgruppe = myRs.getString("muskelgruppe");
				System.out.println("Øvelser i gruppen "+ muskelgruppe + " er:");
				String øvelseQuery = 	"select * from øvelsesgruppe "+ 
										"join øvelseigruppe on øvelsesgruppe.Muskelgruppe=øvelseigruppe.Muskelgruppe " +
											"join øvelse on øvelseigruppe.øvelsesnavn = øvelse.øvelsesnavn " +
												String.format("where øvelsesgruppe.muskelgruppe = '%s';", muskelgruppe);
				//System.out.println(øvelseQuery);
				Statement statement2 = myconn.createStatement();
				
				ResultSet qRs = statement2.executeQuery(øvelseQuery);
				while (qRs.next()){
					System.out.println(qRs.getString("øvelsesnavn"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}
	
	public static void getNlastØkts(int n, Connection myconn){

		int maxID ;
		String størsteid =("select max(øktid) from økt;");
		try{
			Statement statement = myconn.createStatement();
			ResultSet myRs = statement.executeQuery(størsteid);
			if (myRs.next()){
				maxID = (myRs.getInt("max(øktid)"));
			}else{
				System.out.println("Ingen tidligere økter i databasen");
				return;

			}
		
		
			String allegrupper = String.format("select * from økt left join Øktnotat on økt.øktid=øktnotat.øktid  left join notat on notat.notatid=øktnotat.notatid  where økt.øktid> '%d' ;",maxID-n);
		
			Statement listøkter = myconn.createStatement();
			 myRs = listøkter.executeQuery(allegrupper);
			
			while (myRs.next()){
				String notatt;
				if (myRs.getString("tekstfelt") == null){
					notatt = "";
				}else{
					notatt=myRs.getString("tekstfelt");
				}
				String first = "Økt ID: "+ myRs.getString("øktid")+ "   "+ "Notat: "+ notatt;
				System.out.println(first);
				//System.out.println("\n");
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
	}
	

}
