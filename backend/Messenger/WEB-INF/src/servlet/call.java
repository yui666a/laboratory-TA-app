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
public class call extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("call");
		
		String url = req.getRequestURI();
		int beginIdx = url.indexOf("8");
		String myPcId = url.substring(beginIdx);
		
		Pc pc = getPcFromPcId("ics"+myPcId);
		if(pc != null) {
			boolean preHandStatus = pc.getHandStatus();
			
			if(preHandStatus) StartServlet.setHandStatus("ics"+myPcId, false);
			else StartServlet.setHandStatus("ics"+myPcId, true);
			
			// Requestにユーザ名を保存
			req.setAttribute("pcIpAddress", pc.getIpAdress());
			req.setAttribute("pcId", pc.getPcId());
			req.setAttribute("handStatus", pc.getHandStatus());
			req.setAttribute("helpStatus", pc.getHelpStatus());
			
			req.getRequestDispatcher("/output.jsp").forward(req,resp);
		} else {
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
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