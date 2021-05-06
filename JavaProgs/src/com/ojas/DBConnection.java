package com.ojas;
import java.sql.*;
import java.util.*;
public class DBConnection {

	public static void main(String[] args) {
		
		try{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection con = DriverManager.getConnection(
				  "jdbc:mysql://localhost:3306/employees","root","root");
		  System.out.println("connected successfully " +con);
		  CallableStatement cst = con.prepareCall("{? =  CALL getAverage(?,?)}");
		  Scanner sc = new Scanner(System.in);
		  System.out.println("Enter 2 employees, empids ?");
		  cst.registerOutParameter(1, Types.FLOAT);
		  cst.setInt(2, sc.nextInt());
		  cst.setInt(3, sc.nextInt());
		  cst.execute();
		  System.out.println("Average Salary = " + cst.getFloat(1));
		  cst.close();
		  con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

	}

}
