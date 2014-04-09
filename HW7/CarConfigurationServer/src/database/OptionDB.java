package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionDB extends AbstractDB implements DBCreatable, DBUpdatable, DBDeletable{


	private int id = 0;
	private int opset_id = 0;
	private int price = 0;
	private String name = null;
	
	public OptionDB(){
		super.ConnectDB();
	}

	  public String getSQLStatement(String sqlReq){
		  String sql = parseProperties(SQLfileName, sqlReq);
		  String formated_sql = null;
		  if(sqlReq.equals("CREATEOPT")){
			  formated_sql = String.format(sql, id, opset_id, name, price);
		  }
		  if(sqlReq.equals("UPDATEOPT")){
			  formated_sql = String.format(sql, name, price, id);
		  }
		  if(sqlReq.equals("DELETEOPT")){
			  formated_sql = String.format(sql, id);
		  }
		  
		System.out.println("OptSQL:" + formated_sql);

		return formated_sql;
	  }
	  
	  public void setParameters(int id, int opset_id, String name, int price){
		  this.id = id;
		  this.opset_id = opset_id;
		  this.name = name;
		  this.price = price;
	  }
	  
	  public void setID(int id){
		  this.id = id;
	  }
	  
	  public void setOpsetID(int opset_id){
		  this.opset_id = opset_id;
	  }
	  
	  public void setName(String name){
		  this.name = name;
	  }
	  
	  public void setPrice(int price){
		  this.price = price;
	  }
}
