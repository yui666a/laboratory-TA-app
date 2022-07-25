package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
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

@WebServlet(urlPatterns = { "/v1/support/*" })
//support/XXXの応答関数
public class Support extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//URLからpc番号を取得
		String url = req.getRequestURI();

		int beginIdx = url.lastIndexOf("/"); //icsのsの位置を取得
		String myPcId = url.substring(beginIdx+1); //実際は番号だけ知りたいので+1する

		Pc studentPc = getPcFromPcId("ics"+myPcId);
		if(studentPc != null) {
			//現在のヘルプ状態を取得
			String preHelpStatus = studentPc.getHelpStatus();

			//現在のヘルプ状態から状態を遷移する "None"状態は遷移なし
			if(preHelpStatus.equals("Troubled")) {
				StartServlet.setHelpStatus("ics"+myPcId, "Supporting");
				StartServlet.setHandTime("ics"+myPcId, true);
			}else if(preHelpStatus.equals("Supporting")) {
				StartServlet.setHelpStatus("ics"+myPcId, "None");
				StartServlet.setHandTime("ics"+myPcId, true);
			}else {
				//"None"状態は遷移なし
				System.out.println("予期しない状態遷移が発生しました");
			}

			//pcJsonListをJsonに変換
			String jsonList = "";
			List<Pc> pcList = StartServlet.getPcList();

			List<PcJson> pcJsonList = new LinkedList<PcJson>();
			for(Pc tempPc : pcList) {
				if(tempPc.getIsLogin()) {
					PcJson pcJson = new PcJson();
					pcJson.setPcId(tempPc.getPcId());
					pcJson.setIpAdress(tempPc.getIpAdress());
					pcJson.setIsLogin(tempPc.getIsLogin());
					pcJson.setIsStudent(tempPc.getIsStudent());
					pcJson.setHelpStatus(tempPc.getHelpStatus());
					pcJson.setHandPriority(tempPc.getHandPriority());
					pcJsonList.add(pcJson);
				}
			}
			jsonList = getJsonList(pcJsonList);
			
			// JSON形式のメッセージリストを出力
			PrintWriter out = resp.getWriter();
			out.println(jsonList);

		} else {
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
	}

	//---------------補助関数-----------------------------------------------------
	private String getJsonList(List<PcJson> pcJsonList) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(pcJsonList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonList;
	}
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