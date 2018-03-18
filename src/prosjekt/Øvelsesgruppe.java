package prosjekt;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class �velsesgruppe {
	Connection myconn;
	String muskelgruppe;
	
	public �velsesgruppe(Connection myconn,Scanner scanner){
		this.myconn = myconn;
		
		System.out.println("Lager ny Muskelgruppe");
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
	
	

}
