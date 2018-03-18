package prosjekt;

import java.sql.*;
import java.util.Scanner;

public class �velsesgruppe {
	Connection myconn;
	String muskelgruppe;
	
	public �velsesgruppe(Connection myconn,Scanner scanner){
		this.myconn = myconn;
		System.out.println("Skriv navn p� gruppe:");
		muskelgruppe = scanner.next();
		
		String gruppeInsert = String.format("insert into �velsesgruppe values('%s');",muskelgruppe);
		System.out.println("");
		
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(gruppeInsert);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	public �velsesgruppe(Connection myconn, String muskelgruppe) { //sjekker om gruppen eksister og ev. lager den
		this.muskelgruppe = muskelgruppe;
		try{
		Statement statement = myconn.createStatement();
		String eksisterer = String.format("select * from �velsesgruppe where muskelgruppe = '%s';", muskelgruppe);
		
		ResultSet myRs = statement.executeQuery(eksisterer);
		if (myRs.next()){
			System.out.println("Gruppen finnes allerede");
		}
		else {
			System.out.println("Lager ny Muskelgruppe");
			String gruppeInsert = String.format("insert into �velsesgruppe values('%s');",muskelgruppe);
			System.out.println("");
			statement.executeUpdate(gruppeInsert);
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
	}

}
