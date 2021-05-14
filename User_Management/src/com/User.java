package com;

import java.sql.*;

public class User 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "root");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ
			public String readUser()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							// Prepare the HTML table to be displayed
							output = "<table  class='table table-dark table-striped'><tr><th>User Type</th>"
									+"<th>User Name</th><th>Password</th>"
									+ "<th>Email</th>"
									+ "<th>Phone</th>"
									+ "<th>Address</th>"
									+ "<th>Edit</th><th>Delete</th></tr>";

							String query = "select * from users1";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String user_Id = Integer.toString(rs.getInt("user_Id"));
									String user_Type = rs.getString("user_Type");
									String user_Name = rs.getString("user_Name");
									String password = rs.getString("password");
									String user_Email = rs.getString("user_Email");
									String user_Phone = rs.getString("user_Phone");
									String user_Address = rs.getString("user_Address");

									// Add a row into the HTML table
									output += "<tr><td>" + user_Type + "</td>";
									output += "<td>" + user_Name + "</td>"; 
									output += "<td>" + password + "</td>";
									output += "<td>" + user_Email + "</td>"; 
									output += "<td>" + user_Phone + "</td>";
									output += "<td>" + user_Address + "</td>";
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-userid='" + user_Id + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-userid='" + user_Id + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the users.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT
			public String insertUser(String type, String name, String pwd, String email, String phone, String address)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into users1 (`user_Id`,`user_Type`,`user_Name`,`password`,`user_Email`,`user_Phone`,`user_Address`) values (?, ?, ?, ?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, type);
							preparedStmt.setString(3, name);
							preparedStmt.setString(4, pwd);
							preparedStmt.setString(5, email);
							preparedStmt.setString(6, phone);
							preparedStmt.setString(7, address);

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateUser(String ID,String type, String name, String pwd, String email, String phone, String address)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE users1 SET user_Type=?, user_Name=?, password=?, user_Email=?, user_Phone=?, user_Address=? WHERE user_Id=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, type);
							preparedStmt.setString(2, name);
							preparedStmt.setString(3, pwd);
							preparedStmt.setString(4, email);
							preparedStmt.setString(5, phone);
							preparedStmt.setString(6, address);
							preparedStmt.setInt(7, Integer.parseInt(ID));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteUser(String uID)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from users1 where user_Id =?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(uID));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
			}

	
}
