package prosjekt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Apparat {
	String apparatnavn;
	String beskrivelse;
	public Apparat(Connection myconn, Scanner scanner, String apparatnavn) { //sjekker om gruppen eksister og ev. lager den
		this.apparatnavn = apparatnavn;
		try{
		Statement statement = myconn.createStatement();
		String eksisterer = String.format("select * from apparat where Apparatnavn = '%s';", apparatnavn);
		
		ResultSet myRs = statement.executeQuery(eksisterer);
		if (myRs.next()){
			System.out.println("Apparat finnes allerede");
		}
		else {
			System.out.println("Lager nytt apparat");
			System.out.println("Angi beskrivelse for dette apparatet:");
			scanner.nextLine();
			beskrivelse = scanner.nextLine();
			
			String apparatInsert = String.format("insert into Apparat values( '%s', '%s');",apparatnavn,beskrivelse);
			statement.executeUpdate(apparatInsert);
			
			System.out.println("");
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
	}
	
}
