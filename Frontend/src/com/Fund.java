package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	//DB connection
	private Connection connect() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/frontdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

			public String readFund() {
				
				
				//System.out.println("read");
				String output = "";
				

				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for reading.";
					}

					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Name</th>" + "<th>Amount</th><th>NIC</th>"
							+ "<th>Address</th>" + "<th>Update</th><th>Remove</th></tr>";

					String query = "select * from fund";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					while (rs.next()) {

						String FundID = Integer.toString(rs.getInt("FundID"));
						String Name = rs.getString("Name");
						String Amount = rs.getString("Amount");
						String NIC = rs.getString("NIC");
						String Address =rs.getString("Address");

						// Add into the html table

						//output += "<tr><td>" + FundID + "</td>";//<input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='"
						output += "<td>" + Name + "</td>";		//+ FundID + "'>" + Name + "</td>";
						output += "<td>" + Amount + "</td>";
						output += "<td>" + NIC + "</td>";
						output += "<td>" + Address + "</td>";

						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-FundID='"
								+ FundID + "'>" + "</td></tr>";

					}

					con.close();

					// Complete the html table
					
					output += "</table>";
					
				} catch (Exception e) {
					output = "Error while reading the Appointment.";
					System.err.println(e.getMessage());
				}
				
				

				return output;
				
				
			}

			
			public String insertFund(String Name, String Amount, String NIC,String Address) {
				
				
				String output = "";
				
				System.out.println("insert " + Name + Amount + NIC + Address);

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database";
					}

					// create a prepared statement
					String query = " insert into fund (`Name`,`Amount`,`NIC`,`Address`)"
							+ " values (?, ?, ?, ?)";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, Name);
					preparedStmt.setString(2, Amount);
					preparedStmt.setString(3, NIC);
					preparedStmt.setString(4, Address);

					// execute the statement
					preparedStmt.execute();
					con.close();

					// Create JSON Object to show successful msg.
					String newFund = readFund();
					output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
				} catch (Exception e) {
					// Create JSON Object to show Error msg.
					output = "{\"status\":\"error\", \"data\": \"Error while Inserting Fund.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			// Update updateCustomer
			public String updateFund(String Name, String Amount, String NIC,String Address, int FundID) {
				
				
				String output = "";

				
				
				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "update fund SET Name=?,Amount=?,NIC=?,Address=? WHERE FundID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setString(1, Name);
					preparedStmt.setString(2, Amount);
					preparedStmt.setString(3, NIC);
					preparedStmt.setString(4, Address);
					preparedStmt.setInt(5, FundID);

					// execute prepared statement
					preparedStmt.execute();
					con.close();

					// create JSON object to show successful msg
					String newFund = readFund();
					output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while Updating Fund Details.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}

			
			
			public String deleteFund(String FundID) {
				
				
				String output = "";
				
				

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from fund where FundID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					
					preparedStmt.setInt(1, Integer.parseInt(FundID));
					// execute the statement
					preparedStmt.execute();
					con.close();

					
					String newFund = readFund();
					output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}";
					
				} catch (Exception e) {
					
					
					output = "{\"status\":\"error\", \"data\": \"Error while Deleting Fund.\"}";
					System.err.println(e.getMessage());

				}

				return output;
				
				
			}
		}

		
