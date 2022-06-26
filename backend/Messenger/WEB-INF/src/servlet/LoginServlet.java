package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	
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
	
	private String[] getStudentNameFromSIdAndPC(String studentId, String passCode) {
		//ファイルの読み込み
		BufferedReader br = FileReader("/WEB-INF/data/studentIdTable.csv");
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
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();

		// クライアントからデータを取得
//		String studentId = req.getParameter("StudentId"); //学籍番号
//		String passCode = req.getParameter("PassCode"); //パスワード
		//クライアントIPアドレスの取得
		//テスト用
		String clientIpAddr = req.getParameter("pcIpAddr");
		//本番用
//		InetAddress cIpAddr = InetAddress.getLocalHost();
//		String clientIpAddr = cIpAddr.getHostAddress();
		
		//ログイン成否フラグ
		Boolean addrCollationFlag = false;
//		Boolean personIdCollationFlag = false;
		
		//クライアントIPアドレスからPC番号を取得
		Person person = getPcIdFromIpAddr(clientIpAddr);
//		Person person = getPcIdFromIpAddr("133.44.118.182"); //テスト用コード (True) -> icsXXXが返ってくる
//		String pcIdTable = getPcIdFromIpAddr("127.0.0.1"); //テスト用コード (False) -> nullが返ってくる
		
		//クライアントから取得したデータから学生氏名を取得
//		String[] studentName = getStudentNameFromSIdAndPC(studentId, passCode); 
//		String[] studentName = getStudentNameFromSIdAndPC("18336787", ""); //ユーザ用アカウント(テスト用)
//		String[] studentName = getStudentNameFromSIdAndPC("12345678", "1qaz2wsx"); //TA用アカウント(テスト用)
		
		//登録ipアドレスとの照合
		if(person.getPcId() != null) addrCollationFlag = true;
		
		if(addrCollationFlag) {
//			// ログイン成功時の処理
//			// Sessionにユーザ名を保存
			session.setAttribute("pcId", person.getPcId());
			session.setAttribute("isStudent", person.getIsStudent());
			session.setAttribute("handStatus", false);
			session.setAttribute("helpStatus", false);
			
			//学生用とTA教員用を分けてページ遷移させる
			if(person.getIsStudent()) {
				//学生用ページ遷移
				req.getRequestDispatcher("/output_student.jsp").forward(req,resp);
			}else {
				//TA教員用ページ遷移
				req.getRequestDispatcher("/output_teacher.jsp").forward(req,resp);
			}
//			req.getRequestDispatcher("/output.jsp").forward(req,resp);
		}else {
//			//ログイン失敗時の処理
			req.getRequestDispatcher("/error.html").forward(req,resp);
		}
		
		
		//入力データとの照合 (姓名が両方ないとダメにする)
//		if(studentName[0] != null || studentName[1] != null) personIdCollationFlag = true;
		
//		if(addrCollationFlag && personIdCollationFlag) {
//			// ログイン成功時の処理
//			// Sessionにユーザ名を保存
//			session.setAttribute("pcId", person.getPcId());
//			session.setAttribute("lastName", studentName[0]);
//			session.setAttribute("firstName", studentName[1]);
//			session.setAttribute("handStatus", false);
//			session.setAttribute("helpStatus", false);
//			
//			req.getRequestDispatcher("/output.jsp").forward(req,resp);
//		}else{
//			//ログイン失敗時の処理
//			req.getRequestDispatcher("/error.html").forward(req,resp);
//		}
	}
}
