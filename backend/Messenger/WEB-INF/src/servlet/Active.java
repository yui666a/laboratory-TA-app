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

@WebServlet(urlPatterns = { "/v1/active-seats" })
//active-seatsの応答関数
public class Active extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//pcJsonListをJsonに変換
		String jsonList = "";
		List<Pc> pcList = StartServlet.getPcList();
		
		List<PcJson> pcJsonList = new LinkedList<PcJson>();
		for(Pc pc : pcList) {
			if(pc.getIsLogin()) {
				PcJson pcJson = new PcJson();
				pcJson.setPcId(pc.getPcId());
				pcJson.setIpAdress(pc.getIpAdress());
				pcJson.setIsLogin(pc.getIsLogin());
				pcJson.setIsStudent(pc.getIsStudent());
				pcJson.setHelpStatus(pc.getHelpStatus());
				pcJson.setHandPriority(pc.getHandPriority());
				pcJsonList.add(pcJson);
			}
		}
		
		jsonList = getJsonList(pcJsonList);
		
		// JSON形式のメッセージリストを出力
		PrintWriter out = resp.getWriter();
		out.println(jsonList);
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
}