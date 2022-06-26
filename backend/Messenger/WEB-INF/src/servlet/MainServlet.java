package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/MainServlet" })
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		
		if((Boolean)session.getAttribute("handStatus")) {
			session.setAttribute("handStatus", false);
		}else {
			session.setAttribute("handStatus", true);
		}

		// クライアントに送信
		req.getRequestDispatcher("/output.jsp").forward(req,resp);
	}
}
