package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Pc;

@WebServlet(urlPatterns = { "/v1/whoami" })
//whoamiの応答関数
public class WhoamI extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//リクエストがあったPCのipアドレスを取得
		//本番用
		String clientIpAddr = req.getRemoteAddr();
		if(clientIpAddr.equals("0:0:0:0:0:0:0:1")) {
			InetAddress cIpAddr = InetAddress.getLocalHost();
			clientIpAddr = cIpAddr.getHostAddress();
		}
		Pc pc = getPcFromIpAddr(clientIpAddr);
		//テスト用
//		Pc pc = getPcFromIpAddr("133.44.118.191");

		// メッセージリストをJSON形式のメッセージリストに変換
		String jsonList = getJsonList(pc);

		// JSON形式のメッセージリストを出力
		PrintWriter out = resp.getWriter();
		out.println(jsonList);




	}

	//---------------補助関数-----------------------------------------------------
	private String getJsonList(Pc pc) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(pc);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return jsonList;
	}
	private Pc getPcFromIpAddr(String addr) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(addr.equals(pc.getIpAdress())) return pc;
		}
		return null;
	}

	//---------------補助関数(ここまで)-----------------------------------------------------
}