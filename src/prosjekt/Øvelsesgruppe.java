package prosjekt;

import java.sql.*;
import java.util.Scanner;

public class �velsesgruppe {
	Connection myconn;
	String muskelgruppe;
	
	public �velsesgruppe(Connection myconn,Scanner scanner){
		this.myconn = myconn;
		String allegrupper = String.format("select * from �velsesgruppe ;");
		try{
			Statement listgrupper = myconn.createStatement();
			ResultSet myRs = listgrupper.executeQuery(allegrupper);
			System.out.println("Muskelgrupper registrert i databasen:\n");
			while (myRs.next()){
				String first = myRs.getString("muskelgruppe");
				System.out.println(first);
				System.out.println("\n");
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		System.out.println("Skriv navn p� muskelgruppe:");
		muskelgruppe = scanner.next();
		try{
			String eksisterer = String.format("select * from �velsesgruppe where muskelgruppe= '%s';", muskelgruppe);
			Statement statement = myconn.createStatement();
			ResultSet myRs = statement.executeQuery(eksisterer);
			if (myRs.next()){
				System.out.println("Muskelgruppe finnes allerede");
			}else {
				
				String gruppeInsert = String.format("insert into �velsesgruppe values('%s');",muskelgruppe);
				System.out.println("");
				statement = myconn.createStatement();
				statement.executeUpdate(gruppeInsert);
				
				}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	public �velsesgruppe(Connection myconn, String muskelgruppe) { //sjekker om gruppen eksister og ev. lager den
		this.muskelgruppe = muskelgruppe;
		try{
		Statement statement = myconn.createStatement();
		
		System.out.println("*************************\n");
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
