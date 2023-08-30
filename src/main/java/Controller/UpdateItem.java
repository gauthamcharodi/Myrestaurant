package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import dao.MyDao;
import dto.FoodItems;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/update")
@MultipartConfig
public class UpdateItem extends HttpServlet {
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    int id=Integer.parseInt(req.getParameter("id"));
	    String name=req.getParameter("name");
		double price=Double.parseDouble(req.getParameter("price"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		String type=req.getParameter("type");
		Part part=req.getPart("pic");
		byte[] picture=null;
		MyDao dao=new MyDao();
		FoodItems item1=dao.find(id);
		if(part==null)
		{
			picture=item1.getPicture();
		}
		else {
			picture=new byte[part.getInputStream().available()];
			part.getInputStream().read(picture);
		}
		 FoodItems item=new FoodItems();
		 item.setItem_id(id);
		 item.setItem_name(name);
		 item.setPicture(picture);
		 item.setItem_price(price);
		 item.setQuantity(quantity);
		 item.setFood_type(type);
		 
         dao.update(item);
         resp.getWriter().print("<h1 style='color:green'>Data Updated Successfully</h1>");
	     req.getRequestDispatcher("viewmenu").include(req, resp);
}
}
