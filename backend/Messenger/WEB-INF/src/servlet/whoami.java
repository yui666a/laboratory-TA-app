package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Person;

@WebServlet(urlPatterns = { "/whoami" })
public class whoami extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//本番用
//		InetAddress cIpAddr = InetAddress.getLocalHost();
//		String clientIpAddr = cIpAddr.getHostAddress();
//		Person person = getPcIdFromIpAddr(clientIpAddr);
		//テスト用
		Person person = getPcIdFromIpAddr("133.44.118.181");

		// メッセージリストをJSON形式のメッセージリストに変換
		String jsonList = getJsonList(person);

		// JSON形式のメッセージリストを出力
		PrintWriter out = resp.getWriter();
		out.println(jsonList);
	}

	//---------------補助関数-----------------------------------------------------
	private String getJsonList(Person person) throws JsonProcessingException{
		String jsonList = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonList = mapper.writeValueAsString(person);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return jsonList;
	}
	private BufferedReader FileReader(String filePath) {
		String realPath = this.getServletContext().getRealPath(filePath);
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
	private Person getPcIdFromIpAddr(String addr) {
		//ファイルの読み込み
		BufferedReader br = FileReader("/WEB-INF/data/pcIdTable.csv");
		//読み込み行
	    String line;
	    //読み込み行数の管理
	    int i = 0;
	    String[] tableData = new String[2];
	    
	    Person person = new Person(null, null);
	    //1行ずつ読み込みを実行
	    try {
			while ((line = br.readLine()) != null) {
			  //先頭行は列名
			  if (i != 0) {
			    //カンマで分割した内容を配列に格納する
			    tableData = line.split(",");
			    //ログインしてきたPCのIPアドレスとDB内のテーブルとの照合
			    if(addr.equals(tableData[1])) {
			    	//一致した場合、一致した行のpcIdとisStudentを格納
			    	person.setPcId(tableData[0]);
			    	person.setIsStudent(Boolean.valueOf(tableData[2]));
			    	break;
			    }
			  }
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return person;
	}
	//---------------補助関数(ここまで)-----------------------------------------------------
}