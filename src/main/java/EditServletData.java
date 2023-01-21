import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet2")
public class EditServletData extends HttpServlet{
	Connection con =null;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id= Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email=req.getParameter("email");
		String password =req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_servlet", "root", "password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//UPDATE `project_servlet`.`employee` SET `emp_name` = 'raghu1', `email_id` = 'raghu@gml.co', `password` = 'rt' WHERE (`emp_id` = '4');
		}
		try {
			String str = "UPDATE employee SET emp_name = '"+name+"' , email_id = '"+email+"', password = '"+password+"' WHERE emp_id = "+id;
			//System.out.println(str);
			int  rs = stmt.executeUpdate(str);
			if(rs>0) {
				System.out.println("Data Updated successfully!!");
			}else {
				System.out.println("Failed to insert ");
			}
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
