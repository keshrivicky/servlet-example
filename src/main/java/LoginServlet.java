

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DoPost Calling ");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String userName = req.getParameter("uname");
		String password = req.getParameter("psw");
		HashMap<String, String> hm = new HashMap<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_servlet", "root", "password");
  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select username,password from user");
			while (rs.next())
				hm.put(rs.getString("username"), rs.getString("password"));
			con.close();
			//System.out.println(hm);
			
			
			if(hm.get(userName).equals(password)) {
				System.out.println("success");
				RequestDispatcher rd = req.getRequestDispatcher("home.html");
				rd.include(req, resp);
				//out.print("success");
			}else {
				System.out.println("invalid username and password");
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				rd.include(req, resp);
				out.print("\"invalid username and password\"");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//System.out.println(hm);
		
		
			
		}
		
		
		
	

}
