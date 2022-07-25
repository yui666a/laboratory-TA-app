package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Date;
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

//--------サーバの初回起動時に実行される関数-------------------------------------------
	public void contextInitialized(ServletContextEvent arg0)  {

		//ファイルの読み込み
    	BufferedReader br = FileReader("/WEB-INF/data/pcIdTable.csv", arg0);
    	//読み込み行
	    String line;
	    //読み込み行数の管理
	    int i = 0;

	    //1行ずつ読み込みを実行
	    try {
			while ((line = br.readLine()) != null) {
			  //先頭行は列名なので除外
				if (i != 0) {
			    //カンマで分割した内容を配列に格納する
				  String[] lineData = line.split(",");

				  //読み込んだ行をPcクラスに格納
				  pcData = new Pc();
				  pcData.setPcId(lineData[0]);
				  pcData.setIpAdress(lineData[1]);
				  pcData.setIsStudent(Boolean.valueOf(lineData[2]));
				  pcData.setIsLogin(false);
				  pcData.setHelpStatus("None");
				  pcData.setLastRequestTime(null);
				  pcData.setLastHandTime(null);

				  //pcListに追加
				  pcList.add(pcData);
				}
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println(e);
		}
	    
        ScheduleManager schedule = ScheduleManager.getInstance();
        schedule.start();

	    System.out.println("サーバを起動します");
	}

    public void contextDestroyed(ServletContextEvent arg0)  {
    	System.out.println("サーバがダウンされました");
    }

//-----------アクセッサ-----------------------------------------------------------------
	public static List<Pc> getPcList() {
		return pcList;
	}
    public static void setLogin(String pcId, boolean b) {
		List<Pc> pcList = StartServlet.getPcList();
		int i=0;
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setIsLogin(b);
				pcList.set(i, pcTmp);
			}else {
				i++;
			}
		}
	}
    public static void setHelpStatus(String pcId, String str) {
		List<Pc> pcList = StartServlet.getPcList();
		int i=0;
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				Pc pcTmp = new Pc();
				pcTmp = pc;
				pcTmp.setHelpStatus(str);
				pcList.set(i, pcTmp);
			}else {
				i++;
			}
		}
    }

  
	public static Pc getPcFromIpAddr(String addr) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(addr.equals(pc.getIpAdress())) {
				return pc;
			}

		}
		return null;
	}
	
	public static Date setRequestTime(String pcId) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				long millis = System.currentTimeMillis();
				Timestamp timestamp = new Timestamp(millis);
				Date dt = new Date(timestamp.getTime());
				//リクエストをしたpcの最終アクセス時間を格納
				pc.setLastRequestTime(dt);
				return pc.getLastRequestTime();
			}
		}
		return null;
	}
	public static Date getRequestTime(String pcId) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				//リクエストをしたpcの最終アクセス時間をリターン
				return pc.getLastRequestTime();
			}
		}
		return null;
	}
	
	public static Date setHandTime(String pcId, Boolean resetFlag) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				
				//引数resetFlagがtrueの時、挙手した時間をリセットする
				if(resetFlag) {
					pc.setLastHandTime(null);
				}else {
					long millis = System.currentTimeMillis();
					Timestamp timestamp = new Timestamp(millis);
					Date dt = new Date(timestamp.getTime());
					//リクエストをしたpcの最終アクセス時間を格納
					pc.setLastHandTime(dt);
				}
				return pc.getLastHandTime();
			}
		}
		return null;
	}
	
	public static Date getHandTime(String pcId) {
		List<Pc> pcList = StartServlet.getPcList();
		for(Pc pc : pcList) {
			if(pcId.equals(pc.getPcId())) {
				//リクエストをしたpcの最終アクセス時間をリターン
				return pc.getLastHandTime();
			}
		}
		return null;
	}

//-----------補助関数-----------------------------------------------------------------
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

}