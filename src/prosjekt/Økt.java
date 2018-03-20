package prosjekt;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.time.*;


public class �kt {
	
	
	private Connection myconn;
	private boolean current ;
	
	private int �ktID;
	
	
	private Date dato; //TODO: implementer sett dato & tid
	
	private int varighet;
	
	private String treningssenter;
	
	public �kt(Connection myconn){
		this.myconn = myconn;
		
	}
	
	public int get�ktID(){
		return �ktID;
	}
	public Date get�ktnow(){
		return dato;
	}
	
	
	
	public void ny�kt(Scanner scanner){
		System.out.println("legger til ny �kt..");
		dato = new Date(); 
		System.out.println("angi treningssenter: \n");
		treningssenter = scanner.next();
		
		System.out.println("angi varighet i minutter");
		varighet = Integer.parseInt(scanner.next());
		
		String �ktInsert = String.format("insert into �kt(dato,tidspunkt,varighet,treningssenter) values(curdate(),curtime(), %d, '%s');",varighet,treningssenter);
		//String �ktInsert = String.format("insert into �kt(dato,tidspunkt,varighet,treningssenter) values(curdate(),curtime(), 3, '%s');",treningssenter);
		System.out.println("");
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(�ktInsert);
			//get �ktID last created
			Statement mystatement = myconn.createStatement();			
			ResultSet myRs = mystatement.executeQuery("select max(�ktid) from �kt ;");			
			if (myRs.next()){
				�ktID = Integer.parseInt(myRs.getString(1));
			}
			
			//System.out.println(�ktID);
			
			//todo legg inn �velser
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	

}
