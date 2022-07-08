package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Pc;

@WebServlet(urlPatterns = { "/active-seats" })
public class active extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String jsonList = "";
		
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			// メッセージリストをJSON形式のメッセージリストに変換
			jsonList += getJsonList(pc);
		}

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
	
	//---------------補助関数(ここまで)-----------------------------------------------------
}