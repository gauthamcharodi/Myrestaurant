package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

//mapping the url
@WebServlet("/login")
public class CustomerLogin extends HttpServlet {
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   // Receive values from front end
	   String email = req.getParameter("email");
		String password = req.getParameter("pass");
		
	 //verify if email exists

		MyDao dao = new MyDao();
		if(email.equals("admin@jsp.com")&& password.equals("admin")) {
			resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}else {
		Customer customer=dao.fetchByEmail(email);
		if(customer==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);

		}
		else {
			if(password.equals(customer.getPassword())) {
				resp.getWriter().print("<h1 style='color:green'>Login Success"+ "</h1>");
				// This is logic to send to next page
				req.getRequestDispatcher("CustomerHome.html").include(req, resp);

			}
			else {
				// If response should be both text & html
				resp.getWriter().print("<h1 style='color:red'>Invalid password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);

			}
		}
}
		
}
}
