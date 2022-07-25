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
import beans.PcJson;

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
		// Pc pc = getPcFromIpAddr("133.44.118.191");

		// JSON形式のメッセージリストを出力
		PrintWriter out = resp.getWriter();

		//最終リクエスト時間を変更
		if(pc!=null) {
			StartServlet.setRequestTime(pc.getPcId());

			//pcをJsonに変換
			String jsonList = "";

			PcJson pcJson = new PcJson();
			pcJson.setPcId(pc.getPcId());
			pcJson.setHelpStatus(pc.getHelpStatus());
			pcJson.setIpAdress(pc.getIpAdress());
			pcJson.setIsStudent(pc.getIsStudent());
			pcJson.setIsLogin(pc.getIsLogin());
			pcJson.setHandPriority(pc.getHandPriority());
			jsonList = getJsonList(pcJson);
			
			out.println(jsonList);
		}else {
			//実験室外のIPアドレスにはnullを返す
			out.println("null");
		}
	}

	//---------------補助関数-----------------------------------------------------
	private String getJsonList(PcJson pcJson) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(pcJson);
		} catch (JsonProcessingException e) {
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