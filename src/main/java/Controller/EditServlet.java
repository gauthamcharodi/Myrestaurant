package Controller;

import java.io.IOException;

import dao.MyDao;
import dto.FoodItems;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  int id=Integer.parseInt(req.getParameter("id"));
		MyDao dao=new MyDao();
		FoodItems item=dao.find(id);
		
		req.setAttribute("item", item);
		req.getRequestDispatcher("Edit.jsp").forward(req, resp);
}
}
