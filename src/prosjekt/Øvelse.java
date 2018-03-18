package prosjekt;

import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class �velse {
	Connection myconn;
	String �velsesnavn;
	String subclass;
	Scanner scanner;
	
	public �velse(Connection myconn,Scanner scanner){//TODO: sjekke om den finnes f�rst
		this.myconn = myconn;
		this.scanner = scanner;
		
		System.out.println("Lager ny �velse");
		System.out.println("Skriv navn p� �velse:");
		scanner.nextLine();
		�velsesnavn = scanner.nextLine(); //TODO: debug denne
		
		while(true) {
			System.out.println("Velg type �velse; Fri�velse eller Apparat�velse:");
			String valg = scanner.next();
			
			if( valg.equals("F")|| valg.equals("Fri")|| valg.equals("f")|| valg.equals("Fri�velse") ) {
				String �velseInsert = String.format("insert into �velse values('%s','%s');",�velsesnavn,"F");
				
				System.out.println("Angi beskrivelse for denne �velsen:");
				scanner.nextLine();
				String beskrivelse = scanner.nextLine();

				String subclassInsert = String.format("insert into Fri�velse values('%s','%s','%s');",�velsesnavn,"F", beskrivelse);
				
				try{
					Statement statement = myconn.createStatement();
					statement.executeUpdate(�velseInsert);
					statement.executeUpdate(subclassInsert);
					System.out.println(String.format("Fri�velse '%s' lagt til!",�velsesnavn));
					break;
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}
			else if (valg.equals("A") || valg.startsWith("App")) { //TODO: Legg inn fler alternativ
				String �velseInsert = String.format("insert into �velse values('%s','%s');",�velsesnavn,"A");
				
				System.out.println("Angi kilo:");
				scanner.nextLine();
				Double kilo = scanner.nextDouble();

				System.out.println("Angi sett:");
				scanner.nextLine();
				Integer sett= scanner.nextInt();
				
				String subclassInsert = String.format("insert into Apparat�velse values('%s','%s',%.2f, %d);",�velsesnavn,"A", kilo, sett);
				System.out.println("Debug: " + subclassInsert);
				try{
					Statement statement = myconn.createStatement();
					statement.executeUpdate(�velseInsert);
					statement.executeUpdate(subclassInsert);
					System.out.println(String.format("Apparat�velse '%s' lagt til!\n",�velsesnavn));
					leggTilApparat();
					break;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else {
				System.out.println("!!! Feil skrevet...  !!!");
			}
			
		System.out.println("");
					
		}
		
		leggTilGruppe();
		
		
		
		
	}
	
	public void leggTilGruppe() {
		System.out.println("Skriv navn p� gruppen �velsen tilh�rer:");
		String muskelgruppe = scanner.next();
		�velsesgruppe �velsesgruppe = new �velsesgruppe(myconn, muskelgruppe);
		String �velseInsert = String.format("insert into �velseIGruppe values('%s','%s');", �velsesnavn, �velsesgruppe.muskelgruppe);
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(�velseInsert);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void leggTilApparat() {
		System.out.println("Skriv navn p� apparatet til �velsen:");
		String apparatnavn = scanner.next();
		Apparat apparat = new Apparat(myconn, scanner, apparatnavn);
		String apparatInsert = String.format("insert into ApparatTil�velse values( '%s', '%s');",�velsesnavn, apparat.apparatnavn);
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(apparatInsert);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
