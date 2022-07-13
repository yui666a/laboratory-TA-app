package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Pc;

@WebServlet(urlPatterns = { "/v1/active-seats" })
//active-seatsの応答関数
public class Active extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//pcListをJsonに変換
		String jsonList = "";
		List<Pc> pcList = StartServlet.getPcList();
		jsonList = getJsonList(pcList);

		
		Pc pc = StartServlet.getPcFromIpAddr("133.44.118.191");
		
		req.setAttribute("pcIpAddress", pc.getIpAdress());
		req.setAttribute("pcId", pc.getPcId());
		req.setAttribute("handStatus", pc.getHandStatus());
		req.setAttribute("helpStatus", pc.getHelpStatus());
		req.setAttribute("pcList", jsonList.toString());
		
		req.getRequestDispatcher("/output.jsp").forward(req,resp);
	}


	//---------------補助関数-----------------------------------------------------
	private String getJsonList(List<Pc> pcList) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(pcList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonList;
	}
}