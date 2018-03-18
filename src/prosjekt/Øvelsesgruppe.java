package prosjekt;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Øvelsesgruppe {
	Connection myconn;
	String muskelgruppe;
	
	public Øvelsesgruppe(Connection myconn,Scanner scanner){
		this.myconn = myconn;
		
		System.out.println("Lager ny Muskelgruppe");
		System.out.println("Skriv navn på gruppe:");
		muskelgruppe = scanner.next();
		
		String gruppeInsert = String.format("insert into Øvelsesgruppe values('%s');",muskelgruppe);
		System.out.println("");
		
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(gruppeInsert);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	

}
