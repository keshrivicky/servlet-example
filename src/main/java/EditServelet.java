import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServelet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int empId = Integer.parseInt(req.getParameter("id")) ;
		System.out.println("updated id is :- "+ empId);
		PrintWriter out = resp.getWriter();
		//out.print("Record updated successfully !!");
		//String sql = "UPDATE employee  SET password = '"+empId;
		Emp emp = new Emp();
		resp.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_servlet", "root",
					"password");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee WHERE emp_id = "+empId);
		//	out.print("<table border='1' width='100%'");
		//	out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");
			while (rs.next()) {
				out.print("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>Insert title here</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "	<form action='EditServlet2' method='post'>\r\n"
						+ "		<input type='hidden' name='id' value='"+rs.getInt("emp_id")+"' />\r\n"
						+ "		Name:<input type='text' name='name'\r\n"
						+ "			value='"+rs.getString("emp_name")+"' /> \r\n"
						+ "			<br>\r\n"
						+ "			Password:<input\r\n"
						+ "			type='password' name='password' value='"+rs.getString("password")+"' />\r\n"
						+ "			<br>\r\n"
						+ "		Email:<input type='email' name='email'\r\n"
						+ "			value='"+rs.getString("email_id")+"' />\r\n"
						+ "			<br>\r\n"
						+ "			 <input type='submit'\r\n"
						+ "			value='Edit & Save ' />\r\n"
						+ "\r\n"
						+ "	</form>\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
		
		con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		out.close();
	}
	

}
