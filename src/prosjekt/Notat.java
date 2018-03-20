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
	
	public void nyttNotat(Scanner scanner, �kt �kt){
		try{
		Statement statement = myconn.createStatement();
		String eksistererNotat = String.format("select * from �ktnotat where �ktid = %d;", �kt.get�ktID());
		
		ResultSet myRs = statement.executeQuery(eksistererNotat);
		if (myRs.next()){
			System.out.println("Det er allerede laget notat for denne �kten");
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
			
			String �ktNotatInsert = String.format("insert into �ktNotat(�ktid,notatid) values('%d', '%d');",�kt.get�ktID(),notatID);
			
			statement.executeUpdate(�ktNotatInsert);
			//System.out.println(�ktNotatInsert);
			//todo legg inn �velser
			
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
