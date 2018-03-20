package prosjekt;
import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.time.*;

public class Notat {
	
	private int notatID;
	private String beskrivelse;
	private Connection myconn;
	
	public Notat(Connection myconn){
		this.myconn = myconn;
		
	}
	
	public void nyttNotat(Scanner scanner, Økt økt){
		try{
		Statement statement = myconn.createStatement();
		String eksistererNotat = String.format("select * from øktnotat where øktid = %d;", økt.getØktID());
		
		ResultSet myRs = statement.executeQuery(eksistererNotat);
		if (myRs.next()){
			System.out.println("Det er allerede laget notat for denne økten");
		}else{
		
			System.out.println("Angi beskrivelse for dette notatet:");
			scanner.nextLine();
			beskrivelse = scanner.nextLine();
			
			String notatInsert = String.format("insert into Notat(tekstfelt) values( '%s');",beskrivelse);
			statement.executeUpdate(notatInsert);
						
			myRs = statement.executeQuery("select max(notatID) from notat ;");			
			if (myRs.next()){
				notatID = Integer.parseInt(myRs.getString(1));
			}
			
			//System.out.println(notatID);
			
			String øktNotatInsert = String.format("insert into ØktNotat(øktid,notatid) values('%d', '%d');",økt.getØktID(),notatID);
			
			statement.executeUpdate(øktNotatInsert);
			//System.out.println(øktNotatInsert);
			//todo legg inn øvelser
			
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
