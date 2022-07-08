package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import beans.Pc;


@WebListener
public class StartServlet implements ServletContextListener {
	
	Pc pcData;
    private static List<Pc> pcList = new LinkedList<Pc>();

	public static List<Pc> getPcList() {
		return pcList;
	}
    public static void setLogin(String pcId, boolean b) {
		List<Pc> pcList = StartServlet.getPcList();
		int i=0;
		for(Pc pc : pcList) {
			if(pcId == pc.getPcId()) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setIsLogin(b);
				pcList.set(i, pcTmp);
			}else {
				i++;
			}
		}
	}
    public static void setHandStatus(String pcId, boolean b) {
		List<Pc> pcList = StartServlet.getPcList();
		int i=0;
		for(Pc pc : pcList) {
			if(pcId == pc.getPcId()) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setHandStatus(b);
				pcList.set(i, pcTmp);
			}else {
				i++;
			}
		}
    }
    public static void setHelpStatus(String pcId, boolean b) {
		List<Pc> pcList = StartServlet.getPcList();
		int i=0;
		for(Pc pc : pcList) {
			if(pcId == pc.getPcId()) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setHelpStatus(b);
				pcList.set(i, pcTmp);
			}else {
				i++;
			}
		}
    }

	private BufferedReader FileReader(String filePath, ServletContextEvent arg0) {
		String realPath = arg0.getServletContext().getRealPath(filePath);
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		InputStreamReader is = new InputStreamReader(fi);
		BufferedReader br = new BufferedReader(is);
		return br;
	}

	public void contextInitialized(ServletContextEvent arg0)  {

    	BufferedReader br = FileReader("/WEB-INF/data/pcIdTable.csv", arg0);
    	//読み込み行
	    String line;
	    //読み込み行数の管理
	    int i = 0;
	    
	    //1行ずつ読み込みを実行
	    try {
			while ((line = br.readLine()) != null) {
			  //先頭行は列名
				if (i != 0) {
			    //カンマで分割した内容を配列に格納する
				  String[] lineData = line.split(",");
				  pcData = new Pc();
				  pcData.setPcId(lineData[0]);
				  pcData.setIpAdress(lineData[1]);
				  pcData.setIsStudent(Boolean.valueOf(lineData[2]));
				  pcData.setIsLogin(false);
				  pcData.setHandStatus(false);
				  pcData.setHelpStatus(false);
				  pcList.add(pcData);
				}
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println(e);
		}
	    
	    System.out.println(pcList.size());
	}

    public void contextDestroyed(ServletContextEvent arg0)  {
    	System.out.println("サーバがダウンされました");
    }    
}