package prosjekt;

import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class Øvelse {
	Connection myconn;
	String øvelsesnavn;
	String subclass;
	Scanner scanner;
	
	public Øvelse(Connection myconn,Scanner scanner){//TODO: sjekke om den finnes først
		this.myconn = myconn;
		this.scanner = scanner;
		
		System.out.println("Lager ny Øvelse");
		System.out.println("Skriv navn på øvelse:");
		scanner.nextLine();
		øvelsesnavn = scanner.nextLine(); //TODO: debug denne
		
		while(true) {
			System.out.println("Velg type øvelse; Friøvelse eller Apparatøvelse:");
			String valg = scanner.next();
			
			if( valg.equals("F")|| valg.equals("Fri")|| valg.equals("f")|| valg.equals("Friøvelse") ) {
				String øvelseInsert = String.format("insert into øvelse values('%s','%s');",øvelsesnavn,"F");
				
				System.out.println("Angi beskrivelse for denne øvelsen:");
				scanner.nextLine();
				String beskrivelse = scanner.nextLine();

				String subclassInsert = String.format("insert into Friøvelse values('%s','%s','%s');",øvelsesnavn,"F", beskrivelse);
				
				try{
					Statement statement = myconn.createStatement();
					statement.executeUpdate(øvelseInsert);
					statement.executeUpdate(subclassInsert);
					System.out.println(String.format("Friøvelse '%s' lagt til!",øvelsesnavn));
					break;
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}
			else if (valg.equals("A") || valg.startsWith("App")) { //TODO: Legg inn fler alternativ
				String øvelseInsert = String.format("insert into øvelse values('%s','%s');",øvelsesnavn,"A");
				
				System.out.println("Angi kilo:");
				scanner.nextLine();
				Double kilo = scanner.nextDouble();

				System.out.println("Angi sett:");
				scanner.nextLine();
				Integer sett= scanner.nextInt();
				
				String subclassInsert = String.format("insert into Apparatøvelse values('%s','%s',%.2f, %d);",øvelsesnavn,"A", kilo, sett);
				System.out.println("Debug: " + subclassInsert);
				try{
					Statement statement = myconn.createStatement();
					statement.executeUpdate(øvelseInsert);
					statement.executeUpdate(subclassInsert);
					System.out.println(String.format("Apparatøvelse '%s' lagt til!\n",øvelsesnavn));
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
		System.out.println("Skriv navn på gruppen øvelsen tilhører:");
		String muskelgruppe = scanner.next();
		Øvelsesgruppe øvelsesgruppe = new Øvelsesgruppe(myconn, muskelgruppe);
		String øvelseInsert = String.format("insert into ØvelseIGruppe values('%s','%s');", øvelsesnavn, øvelsesgruppe.muskelgruppe);
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(øvelseInsert);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void leggTilApparat() {
		System.out.println("Skriv navn på apparatet til øvelsen:");
		String apparatnavn = scanner.next();
		Apparat apparat = new Apparat(myconn, scanner, apparatnavn);
		String apparatInsert = String.format("insert into ApparatTilØvelse values( '%s', '%s');",øvelsesnavn, apparat.apparatnavn);
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(apparatInsert);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
