package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.Automobile;

public abstract class AbstractDB {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql56.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/carconfiguration";

	static final String SQLfileName = "property_SQL2.txt";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "111111";
	private Connection conn = null;
	private Statement stmt = null;

	public void ConnectDB() {
		System.out.println("AbstractDB");
		// STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readDataBase() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}

	public void createDB(String sqlReq) {
		// STEP 4: Execute a query
		System.out.println("Inserting records into the table...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String sql = parseProperties(SQLfileName, sqlReq);
		String sql = getSQLStatement(sqlReq);
		System.out.println("createDB:" +sql);
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Inserted records into the table...");
	}

	public void updateDB(String sqlReq) {
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String sql = parseProperties(SQLfileName, sqlReq);
		String sql = getSQLStatement(sqlReq);

		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteDB(String sqlReq) {
		// STEP 4: Execute a query
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String sql = parseProperties(SQLfileName, sqlReq);
		String sql = getSQLStatement(sqlReq);

		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

	public String parseProperties(String fileName, String sqlReq) {

		Properties props = new Properties();
		FileInputStream in = null;

		try {
			in = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // This loads the entire file in memory

		String out = null;
		if (sqlReq.equals("CREATEAUTO")) {
			out = props.getProperty("CREATEAUTO");
		}
		if (sqlReq.equals("UPDATEAUTO")) {
			out = props.getProperty("UPDATEAUTO");
		}
		if (sqlReq.equals("DELETEAUTO")) {
			out = props.getProperty("DELETEAUTO");
		}

		if (sqlReq.equals("CREATEOPSET")) {
			out = props.getProperty("CREATEOPSET");
		}
		if (sqlReq.equals("UPDATEOPSET")) {
			out = props.getProperty("UPDATEOPSET");
		}
		if (sqlReq.equals("DELETEOPSET")) {
			out = props.getProperty("DELETEOPSET");
		}

		if (sqlReq.equals("CREATEOPT")) {
			out = props.getProperty("CREATEOPT");
		}
		if (sqlReq.equals("UPDATEOPT")) {
			out = props.getProperty("UPDATEOPT");
		}
		if (sqlReq.equals("DELETEOPT")) {
			out = props.getProperty("DELETEOPT");
		}

		return out;
	}

	protected abstract String getSQLStatement(String sqlReq);
}
