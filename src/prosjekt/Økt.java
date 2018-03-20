package prosjekt;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;
import java.time.*;


public class Økt {
	
	
	private Connection myconn;
	private boolean current ;
	
	private int øktID;
	
	
	private Date dato; //TODO: implementer sett dato & tid
	
	private int varighet;
	
	private String treningssenter;
	
	public Økt(Connection myconn){
		this.myconn = myconn;
		
	}
	
	public int getØktID(){
		return øktID;
	}
	public Date getØktnow(){
		return dato;
	}
	
	
	
	public void nyØkt(Scanner scanner){
		System.out.println("legger til ny økt..");
		dato = new Date(); 
		System.out.println("angi treningssenter: \n");
		treningssenter = scanner.next();
		
		System.out.println("angi varighet i minutter");
		varighet = Integer.parseInt(scanner.next());
		
		String øktInsert = String.format("insert into økt(dato,tidspunkt,varighet,treningssenter) values(curdate(),curtime(), %d, '%s');",varighet,treningssenter);
		//String øktInsert = String.format("insert into økt(dato,tidspunkt,varighet,treningssenter) values(curdate(),curtime(), 3, '%s');",treningssenter);
		System.out.println("");
		try{
			Statement statement = myconn.createStatement();
			statement.executeUpdate(øktInsert);
			//get øktID last created
			Statement mystatement = myconn.createStatement();			
			ResultSet myRs = mystatement.executeQuery("select max(øktid) from økt ;");			
			if (myRs.next()){
				øktID = Integer.parseInt(myRs.getString(1));
			}
			
			//System.out.println(øktID);
			
			//todo legg inn øvelser
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	

}
