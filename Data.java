
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

public class Data {

	public static void main(String[] args) {
		
//Prompt user to enter New Employees information
		Scanner user_input=new Scanner(System.in);
		String socialScurity;
		System.out.print("Please enter employees SIN number: ");
		socialScurity= user_input.next();
		String firstName;
		System.out.print("Please enter employees first name: ");
		firstName= user_input.next();
		String lastName;
		System.out.print("Please enter employees last name: ");
		lastName= user_input.next();
		String empType;
		System.out.print("Please enter employees employee Type: ");
		empType= user_input.next();
		String department;
		System.out.print("Please enter employees department: ");
		department= user_input.next();
		double payment;
		
// prompt user to choose employees payment method
		System.out.print("Please enter employees payment method:(1.Salaried, 2.commissioned, 3.hourly, 4.base plus commission): ");
		payment= user_input.nextDouble();
		
//Mysql connection
		try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/employees","root","password");
				
//Get current Date
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

//Assignment part 2-A
			    String query1 = " insert into employees(socialSecurityNumber,firstName, lastName, birthday, employeeType, departmentName)" + " values (?, ?, ?, ?, ?, ?)";
			    
			    PreparedStatement preparedStmt = conn.prepareStatement(query1);
			    preparedStmt.setString (1, socialScurity);
			    preparedStmt.setString(2, firstName);
			    preparedStmt.setString(3, lastName);
			    preparedStmt.setDate(4, startDate);
			    preparedStmt.setString(5, empType);
			    preparedStmt.setString(6, department);
			    preparedStmt.executeUpdate();
			    
//Assignment part 2-B
			    if(payment == 1)
			    {
			    	String query2 = " insert into salariedEmployees(socialSecurityNumber,weeklySalary, bonus)" + " values (?, ?, ?)";
			    	
			    	double weeklySalary;
					System.out.print("Please enter employees weekly Salary: ");
					weeklySalary= user_input.nextDouble();
					double bonus;
					System.out.print("Please enter employees bonus: ");
					bonus= user_input.nextDouble();
					
				    PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
				    preparedStmt2.setString (1, socialScurity);
				    preparedStmt2.setDouble(2, weeklySalary);
				    preparedStmt2.setDouble(3, bonus);
				    preparedStmt2.executeUpdate();
			    	
			    }
			    else if(payment == 2)
			    {
			    	String query2 = " insert into commissionEmployees(socialSecurityNumber, grossSales, commissionRate, bonus)" + " values (?, ?, ?, ?)";
			    	
			    	double grossSales;
					System.out.print("Please enter employees gross sales: ");
					grossSales= user_input.nextDouble();
					double commissionRate;
					System.out.print("Please enter employees commission rate: ");
					commissionRate= user_input.nextDouble();
					double bonus;
					System.out.print("Please enter employees bonus: ");
					bonus= user_input.nextDouble();
					
				    PreparedStatement preparedStmt3 = conn.prepareStatement(query2);
				    preparedStmt3.setString (1, socialScurity);
				    preparedStmt3.setDouble(2, grossSales);
				    preparedStmt3.setDouble(3, commissionRate);
				    preparedStmt3.setDouble(4, bonus);
				    preparedStmt3.executeUpdate();
			    	
			    }
			   
			    else if(payment == 3)
			    {
			    	String query2 = " insert into hourlyEmployees(socialSecurityNumber, hours, wage, bonus)" + " values (?, ?, ?, ?)";
			    	
			    	double hours;
					System.out.print("Please enter employees hours: ");
					hours= user_input.nextDouble();
					double wage;
					System.out.print("Please enter employees wage: ");
					wage= user_input.nextDouble();
					double bonus;
					System.out.print("Please enter employees bonus: ");
					bonus= user_input.nextDouble();
					
				    PreparedStatement preparedStmt4 = conn.prepareStatement(query2);
				    preparedStmt4.setString (1, socialScurity);
				    preparedStmt4.setDouble(2, hours);
				    preparedStmt4.setDouble(3, wage);
				    preparedStmt4.setDouble(4, bonus);
				    preparedStmt4.executeUpdate();
			    	
			    }
			    else if(payment == 4)
			    {
			    	String query2 = " insert into basePluscommissionEmployees(socialSecurityNumber, grossSales, commissionRate, baseSalary, bonus)" + " values (?, ?, ?, ?, ?)";
			    	
			    	double grossSales;
					System.out.print("Please enter employees gross sales: ");
					grossSales= user_input.nextDouble();
					double commissionRate;
					System.out.print("Please enter employees commission rate: ");
					commissionRate= user_input.nextDouble();
					double baseSalary;
					System.out.print("Please enter employees base salary: ");
					baseSalary= user_input.nextDouble();
					double bonus;
					System.out.print("Please enter employees bonus: ");
					bonus= user_input.nextDouble();
					
				    PreparedStatement preparedStmt5 = conn.prepareStatement(query2);
				    preparedStmt5.setString (1, socialScurity);
				    preparedStmt5.setDouble(2, grossSales);
				    preparedStmt5.setDouble(3, commissionRate);
				    preparedStmt5.setDouble(4, baseSalary);
				    preparedStmt5.setDouble(5, bonus);
				    preparedStmt5.executeUpdate();
				    
//Assignment part3-A  	
			    }
			    String query3 = "select * from employees where departmentName like 'SALES'";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query3);
				System.out.println("Employees working in sales department");
				while (rs.next()) {
					String firstN = rs.getString("firstName");
					System.out.format("%s, ", firstN);
					String lastN = rs.getString("lastName");
					System.out.format("%s, ", lastN);
					String employeeT = rs.getString("employeeType");
					System.out.format("%s\n", employeeT);
				    }
				
//Assignment part3-B 
				 String query4 = "select * from employees inner join hourlyEmployees on employees.socialSecurityNumber=hourlyEmployees.socialSecurityNumber where hours > 30";
					rs = stmt.executeQuery(query4);
					System.out.println("Employees working greater than 30 hours");
				  while (rs.next()) {
						String firstN = rs.getString("firstName");
						System.out.format("%s, ", firstN);
						String lastN = rs.getString("lastName");
						System.out.format("%s, ", lastN);
						double employeeh = rs.getDouble("hours");
						System.out.format("%f\n", employeeh);
					    }
				  
//Assignment part3-C 
				  String query5 = "select * from employees inner join commissionEmployees on employees.socialSecurityNumber=commissionEmployees.socialSecurityNumber order by commissionRate Desc";
					rs = stmt.executeQuery(query5);
					System.out.println("All commission employees in the descending order of their comission rate ");
				  while (rs.next()) {
						String firstN = rs.getString("firstName");
						System.out.format("%s, ", firstN);
						String lastN = rs.getString("lastName");
						System.out.format("%s, ", lastN);
						double employeer = rs.getDouble("commissionRate");
						System.out.format("%f\n", employeer);
					    }
				  
//Assignment part3-D
				  String query6=" update basePluscommissionemployees set baseSalary=baseSalary *1.1";
				  PreparedStatement preparedStmt1 = conn.prepareStatement(query6);
				  System.out.println("All base plus commission employees have 10% increase in thier base salary ");
				  preparedStmt1.executeUpdate();
				  
//Assignment part3-F
				  String query7=" update commissionEmployees set bonus=bonus + 100 where grossSales >1000";
				  PreparedStatement preparedStmt2 = conn.prepareStatement(query7);
				  System.out.println("Add 100 dollar bonus to all commission employees that have a gross sale over 1000");
				  preparedStmt2.executeUpdate();
				  
				stmt.close();
			    user_input.close();
				conn.close();
				
				
		}catch(Exception e) {System.err.println(e);}
	}

}
