package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Pc;

@WebServlet(urlPatterns = { "/call/*" })
//call-teacher/XXXの応答関数
public class Call extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//URLからpc番号を取得
		String url = req.getRequestURI();

		int beginIdx = url.lastIndexOf("/"); //icsのsの位置を取得
		String myPcId = url.substring(beginIdx+1); //実際は番号だけ知りたいので+1する
		
		Pc pc = getPcFromPcId("ics"+myPcId);
		if(pc != null) {
			
			//現在の挙手状態を取得
			boolean preHandStatus = pc.getHandStatus();
			
			//現在の挙手状態を反転
			if(preHandStatus) StartServlet.setHandStatus("ics"+myPcId, false);
			else StartServlet.setHandStatus("ics"+myPcId, true);
			
			// Requestに各種データを保存
			req.setAttribute("pcIpAddress", pc.getIpAdress());
			req.setAttribute("pcId", pc.getPcId());
			req.setAttribute("handStatus", pc.getHandStatus());
			req.setAttribute("helpStatus", pc.getHelpStatus());
			
			req.getRequestDispatcher("/output.jsp").forward(req,resp);
		} else {
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
	}


//-------------補助関数---------------------------------------------
	private Pc getPcFromPcId(String pcId) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				return pc;	
			}
		}
		return null;
	}
}