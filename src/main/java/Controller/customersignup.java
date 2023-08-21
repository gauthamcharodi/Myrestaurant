package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import dao.MyDao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/signup")
@MultipartConfig
public class customersignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fullName = req.getParameter("Fullname");
		String password = req.getParameter("password");
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		LocalDate dob = LocalDate.parse(req.getParameter("date"));
		int age = Period.between(dob, LocalDate.now()).getYears();

		Part pic = req.getPart("picture");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

//		System.out.println(fullName);
//		System.out.println(password);
//		System.out.println(mobile);
//		System.out.println(email);
//		System.out.println(gender);
//		System.out.println(dob);
//		System.out.println(age);
//		System.out.println(picture);
		
		
		MyDao dao = new MyDao();		
		//logic to verify email & mobile number is not repeated
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null)
		{
       //Loading values inside object
		Customer customer = new Customer();
		customer.setUsername(fullName);
		customer.setPhonenumber(mobile);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setGender(gender);
		customer.setDob(dob);
		customer.setAge(age);
		customer.setPicture(picture);

		//Persisting 
		dao.save(customer);
		
		resp.getWriter().print("<h1 style='color:green'>Account created sucessfully</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);

	}
		else {
			resp.getWriter().print("<h1 style='color:red'>email and mobile number already exist</h1>");
			req.getRequestDispatcher("CustomerHome.html").include(req, resp);

		}
}
}
