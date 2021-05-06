package com;

import java.sql.*;
import java.util.Scanner;

public class Demo {
	
    public static void main(String[] args){
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
           "jdbc:mysql://localhost:3306/demo","root","root");
            System.out.println("connected " + con);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter two empno numbers?");
            CallableStatement cst = con.prepareCall("{? = CALL getAverage(?,?)}");
            cst.registerOutParameter(1, Types.FLOAT);
            cst.setInt(2, sc.nextInt());
            cst.setInt(3, sc.nextInt());
            cst.execute();
            float avgsalary = cst.getFloat(1);
            System.out.println("Average salary of two employess = " + avgsalary);
            
    	}
    	catch(Exception e) {
    		System.out.println(e);
    	}
    	
    	

    }
}





