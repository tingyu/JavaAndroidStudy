package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionSetDB extends AbstractDB implements DBCreatable, DBUpdatable, DBDeletable{

	private String model = null;
	private String name = null;
	private int id = 0;
	
	public OptionSetDB(){
		super.ConnectDB();
	}
	
	  public String getSQLStatement(String sqlReq){
		  String sql = parseProperties(SQLfileName, sqlReq);
		  String formated_sql = null;
		  if(sqlReq.equals("CREATEOPSET")){
			  formated_sql = String.format(sql, id, model, name);
		  }
		  if(sqlReq.equals("UPDATEOPSET")){
			  formated_sql = String.format(sql, name, id);
		  }
		  if(sqlReq.equals("DELETEOPSET")){
			  formated_sql = String.format(sql, id);
		  }
		  
		System.out.println("OpsetSQL:" + formated_sql);

		return formated_sql;
	  }
	  
	  public void setParameters(int id, String model, String name){
		  this.id = id;
		  this.model = model;
		  this.name = name;
	  }
	  
	  public void setID(int id){
		  this.id = id;
	  }
	  
	  public void setName(String name){
		  this.name = name;
	  }

}
