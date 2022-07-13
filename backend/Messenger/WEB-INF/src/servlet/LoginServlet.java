package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Pc;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//クライアントIPアドレスの取得
		//テスト用
		String clientIpAddr = req.getParameter("pcIpAddr");
		//TODO: 本番用
//		InetAddress cIpAddr = InetAddress.getLocalHost();
//		String clientIpAddr = cIpAddr.getHostAddress();
		
		//ipアドレスからPC情報を取得
		Pc pc = StartServlet.getPcFromIpAddr(clientIpAddr);
		
		//ipアドレスからpc情報を取得できたか
		Boolean addrCollationFlag = false; //ログイン成否フラグ
		if(pc != null) addrCollationFlag = true;
		
		if(addrCollationFlag) {
//			// ログイン成功時の処理
			
			//ログイン中に変更
			StartServlet.setLogin(pc.getPcId(), true);
//			// Requestにpcデータを保存
			req.setAttribute("pcIpAddress", pc.getIpAdress());
			req.setAttribute("pcId", pc.getPcId());
			req.setAttribute("handStatus", pc.getHandStatus());
			req.setAttribute("helpStatus", pc.getHelpStatus());
			
			req.getRequestDispatcher("/index.html").forward(req,resp);
		}else{
//			//ログイン失敗時の処理
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
	}
	
//-----------------補助関数-------------------------------------------------	
}
