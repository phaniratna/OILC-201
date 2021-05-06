package com.ojas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
	int sno;
	String sname;
	int marks;

	public Student() {
		System.out.println("Default Constructor");
	}

	public Student(int sno, String sname, int marks) {
		this.sno = sno;
		this.sname = sname;
		this.marks = marks;
	}

}

class Operations {

	Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/java200", "root", 
					"root");
			System.out.println("Connected " + con);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	void menu() {
		String m = "Menu Driven Application\n ";
		m += "1.Add Student\n ";
		m += "2.Delete Student\n ";
		m += "3.Update Student\n ";
		m += "4.List Students\n ";
		m += "5.Exit\n ";
		m += "Select any option?";
		System.out.println(m);

	}

	boolean addStudent(Student stud) {
		boolean b = false;
		try {
			Connection con = getConnect();
			PreparedStatement pst = con
					.prepareStatement(
							"insert into student values(?,?,?)");
			pst.setInt(1, stud.sno);
			pst.setString(2, stud.sname);
			pst.setInt(3, stud.marks);
			int res = pst.executeUpdate();
			if (res > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	boolean deleteStudent(int num) {
		boolean b = false;
		try {
			Connection con = getConnect();
			PreparedStatement pst = con
					.prepareStatement("delete from student where sno = ?");
			pst.setInt(1, num);

			int res = pst.executeUpdate();
			if (res > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	boolean updateStudent(Student stud) {
		boolean b = false;
		try {
			Connection con = getConnect();
			PreparedStatement pst = con
					.prepareStatement(
							"update student set sname=?"
							+ ",marks =? where sno= ?");

			pst.setString(1, stud.sname);
			pst.setInt(2, stud.marks);
			pst.setInt(3, stud.sno);
			int res = pst.executeUpdate();
			if (res > 0) {
				b = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return b;
	}

	List<Student> listStudent() {
		List<Student> list = new ArrayList<Student>();
		try {
			Connection con = getConnect();
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select * from student");
			while (res.next()) {
				Student s = new Student(res.getInt(1), res.getString(2),
						res.getInt(3));
				list.add(s);
			}
			res.close();
			st.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public void accept() {
		System.out.println("Enter your num,name,marks ?");
	}
}

public class MyDBProg {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Operations obj = new Operations();
		for (;;) {
			obj.menu();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				obj.accept();
				obj.addStudent(new Student(sc.nextInt(), sc.next(), sc
						.nextInt()));
				break;
			case 2:
				System.out.println("Enter your num to delete?");
				obj.deleteStudent(sc.nextInt());
				break;
			case 3:
				obj.accept();
				obj.updateStudent(new Student(sc.nextInt(), 
						sc.next(), sc
						.nextInt()));
				break;
			case 4:
				System.out.println("Student Info");
				List<Student> list = obj.listStudent();
				list.forEach(x -> System.out.println(x.sno + " " 
				+ x.sname + ""
						+ x.marks));
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
			}// closing switch
		}// closing for loop
	}// closing main

}
