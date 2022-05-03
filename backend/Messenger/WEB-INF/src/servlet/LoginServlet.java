package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	
	private static final Boolean False = null;


	private String[] getStudentNameFromSIdAndPC(String studentId, String passCode) {
		String realPath = this.getServletContext().getRealPath("/WEB-INF/data/studentIdTable.csv");
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		InputStreamReader is = new InputStreamReader(fi);
		BufferedReader br = new BufferedReader(is);
		
		//読み込み行
	    String line;
	    //読み込み行数の管理
	    int i = 0;
	    
	    String[] Name = new String[2];
	    String[] tableData = new String[4];
	    //1行ずつ読み込みを実行
	    try {
			while ((line = br.readLine()) != null) {
			  //先頭行は列名
			  if (i != 0) {
			    //カンマで分割した内容を配列に格納する
			    tableData = line.split(",");
			    if(studentId.equals(tableData[0])) {
			    	if(passCode.equals(tableData[1])) {
			    		Name[0] = tableData[2];
			    		Name[1] = tableData[3];
			    		break;
			    	}
			    }
			  }
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return Name;
	}
	
	private String getPcIdFromIpAddr(String addr) {
		
		String realPath = this.getServletContext().getRealPath("/WEB-INF/data/pcIdTable.csv");
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		InputStreamReader is = new InputStreamReader(fi);
		BufferedReader br = new BufferedReader(is);
		
		//読み込み行
	    String line;
	    //読み込み行数の管理
	    int i = 0;
	    String pcId = null;
	    String[] tableData = null;
	    //1行ずつ読み込みを実行
	    try {
			while ((line = br.readLine()) != null) {
			  //先頭行は列名
			  if (i != 0) {
			    //カンマで分割した内容を配列に格納する
			    tableData = line.split(",");
			    if(addr.equals(tableData[1])) {
			    	pcId = tableData[0];
			    	break;
			    }
			  }
			  i++;
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return pcId;
		
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();

		// クライアントからデータを取得
		String StudentId = req.getParameter("StudentId"); //学籍番号
		String PassCode = req.getParameter("PassCode"); //パスワード
		//クライアントIPアドレスの取得
		InetAddress cIpAddr = InetAddress.getLocalHost();
		String clientIpAddr = cIpAddr.getHostAddress();

		//クライアントIPアドレスからPC番号を取得
		String pcIdTable = getPcIdFromIpAddr(clientIpAddr);
//		String pcIdTable = getPcIdFromIpAddr("133.44.118.159"); //テスト用コード (True) -> icsXXXが返ってくる
//		String pcIdTable = getPcIdFromIpAddr("127.0.0.1"); //テスト用コード (False) -> nullが返ってくる
		if(pcIdTable != null) {
			System.out.println(pcIdTable);
		}else {
			System.out.println("error!");
		}
		
		//クライアントから取得したデータから学生氏名を取得
		String[] studentName = getStudentNameFromSIdAndPC(StudentId, PassCode); 
//		String[] studentName = getStudentNameFromSIdAndPC("18336787", ""); //ユーザ用アカウント
//		String[] studentName = getStudentNameFromSIdAndPC("12345678", "1qaz2wsx"); //TA用アカウント
		if(studentName[0] != null) {
			System.out.println(studentName[0]);
			System.out.println(studentName[1]);
		}else {
			System.out.println("error!");
		}

		// Sessionにユーザ名を保存
		session.setAttribute("pcIdTable", pcIdTable);
		session.setAttribute("lastName", studentName[0]);
		session.setAttribute("firstName", studentName[1]);
		session.setAttribute("handStatus", false);
	}
	

}
