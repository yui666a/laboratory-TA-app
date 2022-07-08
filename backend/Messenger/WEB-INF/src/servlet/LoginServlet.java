package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Pc;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	
	private Pc getPcFromIpAddr(String addr) {
		
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(addr.equals(pc.getIpAdress())) {
				return pc;	
			}
					
		}
		return null;
	}
	

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
		
		//ログイン成否フラグ
		Boolean addrCollationFlag = false;
		
		//クライアントIPアドレスからPC情報を取得
		Pc pc = getPcFromIpAddr(clientIpAddr);
//		Person person = getPcIdFromIpAddr("133.44.118.182"); //テスト用コード (True) -> icsXXXが返ってくる
//		String pcIdTable = getPcIdFromIpAddr("127.0.0.1"); //テスト用コード (False) -> nullが返ってくる
		
		
		//ipアドレスから登録情報を取得できたか
		if(pc != null) addrCollationFlag = true;
		
		if(addrCollationFlag) {
//			// ログイン成功時の処理
			StartServlet.setLogin(pc.getPcId(), true);
//			// Requestにユーザ名を保存
			req.setAttribute("pcIpAddress", pc.getIpAdress());
			req.setAttribute("pcId", pc.getPcId());
			req.setAttribute("handStatus", pc.getHandStatus());
			req.setAttribute("helpStatus", pc.getHelpStatus());
			
			req.getRequestDispatcher("/output.jsp").forward(req,resp);
		}else{
//			//ログイン失敗時の処理
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
	}
}
