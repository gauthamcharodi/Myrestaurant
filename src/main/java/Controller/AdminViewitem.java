package Controller;

import java.io.IOException;
import java.util.List;

import dto.FoodItems;
import dao.MyDao;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewmenu")
public class AdminViewitem extends HttpServlet {
	
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		//logic to fetch data from database
				MyDao dao=new MyDao();
			   List<FoodItems> items=dao.fetchAllFooditems();
				
				
				
				//logic to display data on the front end
			//   resp.getWriter().print("hiii");
//				resp.getWriter().print("<html><body><h1>Menu</h1>");
//				resp.getWriter().print("<table border='1'>");
//				resp.getWriter().print(
//						"<tr><th>Name</th><th>Type</th><th>Price</th><th>Quantity</th><th>Edit</th><th>Delete</th></tr>");
//				for (FoodItems item : items) {
//					resp.getWriter().print(
//							"<tr><th>"+item.getItem_name()+"</th><th>"+item.getFood_type()+"</th><th>"+item.getItem_price()+"</th><th>"+item.getQuantity()+"</th><th><button>Edit</button></th><th><button>Delete</button></th></tr>");
//				}
//				resp.getWriter().print("</table></body></html>");
			   if(items.isEmpty()) {
					resp.getWriter().print("<h1 style='color:red'>NO ITEMS FOUND</h1>");
					req.getRequestDispatcher("AdminHome.html").include(req, resp);
					
				}
				else {
					req.setAttribute("list",items);
					req.getRequestDispatcher("ViewMenu.jsp").include(req, resp);
				}
	}
}