package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class StartTest implements ServletContextListener {
	
    //	TODO 現在は100台までのPCに対応、100台を超える場合はString[100][]の値を変更    
    public static String[][] pcIdTable = new String[100][];
	
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
			    pcIdTable[i-1] = line.split(",");
			  }
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	 System.out.println(pcIdTable[0][0]);
    }

    public void contextDestroyed(ServletContextEvent arg0)  {
    }
}