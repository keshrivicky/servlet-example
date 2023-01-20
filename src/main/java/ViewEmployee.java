import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewEmployee extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<a href='index.html'>Add New Employee</a>");
		out.println("<h1>Employees List</h1>");

		List<Emp> list = new ArrayList<>();
		Emp emp = new Emp();
		// emp.setEmail("dummy@gmail.com");
		// emp.setId(2);
		// emp.setName("dummy");
		// emp.setPassword("dummy123");
		// list.add(emp);
		// HashMap<String, String> hm = new HashMap<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_servlet", "root",
					"password");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");
			out.print("<table border='1' width='100%'");
			out.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");
			while (rs.next()) {
				emp.setEmail(rs.getString("email_id"));
				emp.setId(rs.getInt("emp_id"));
				emp.setName(rs.getString("emp_name"));
				emp.setPassword(rs.getString("password"));
				//list.add(emp);
		
			
		
		
		
			out.print("<tr><td>" + emp.getId() + "</td><td>" + emp.getName() + "</td><td>" +emp.getPassword() + "</td> "
					+ "<td>" + emp.getEmail() + "</td><td><a href='EditServlet?id=" + emp.getId() + "'>edit</a></td>"
					+ "  <td><a href='DeleteServlet?id=" + emp.getId() + "'>delete</a></td></tr>");
			}
		out.print("</table>");
		con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		out.close();
	}
}
