package com.hf;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

	import java.io.IOException;
	import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;

import com.hb.Product;
import util.HibernateSessionUtil;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("register.html").include(request, response);

}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("register.html").include(request, response);
		
		int id = parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String colour = request.getParameter("colour");
		
		
		try {
			
			SessionFactory factory = HibernateSessionUtil.buildSessionFactory();
			
			
			Session session = factory.openSession();
			
			Product obj1 = new Product(id,name,colour);
			
			// 4. begin transaction
			Transaction tx = session.beginTransaction();
			
			// 5. save product
			session.save(obj1);
			
			// 6. commit transaction
			tx.commit();
			
			if(session != null ) {
				out.print("<h3 style='color:green'> Added sucessfully...Check Database </h3>");
			}
				
			
			// 3. close session
			session.close();
		} catch (Exception e) {
			out.print("<h3 style='color:red'> Not added! </h3>");
		}
	}
	private int parseInt(String parameter) {
		// TODO Auto-generated method stub
		return 0;
	}
}


