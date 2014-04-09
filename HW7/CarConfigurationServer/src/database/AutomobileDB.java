package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutomobileDB extends AbstractDB implements DBCreatable,
		DBUpdatable, DBDeletable {

	private String model = null;
	private String make = null;
	private int basePrice = 0;
	private String name = null;

	public AutomobileDB() {
		super.ConnectDB();
	}

	public String getSQLStatement(String sqlReq) {
		String sql = parseProperties(SQLfileName, sqlReq);
		String formated_sql = null;
		if (sqlReq.equals("CREATEAUTO")) {
			formated_sql = String.format(sql, model, make, basePrice, name);
		}
		if (sqlReq.equals("UPDATEAUTO")) {
			formated_sql = String.format(sql, make, basePrice, name,  model);
		}
		if (sqlReq.equals("DELETEAUTO")) {
			formated_sql = String.format(sql, model);
		}

		System.out.println("AutoSQL:" + formated_sql);
		return formated_sql;
	}

	public void setParameters(String model, String make, int basePrice,
			String name) {
		this.model = model;
		this.make = make;
		this.basePrice = basePrice;
		this.name = name;
	}
	
	public void setModel(String model){
		this.model = model;
	}

}
