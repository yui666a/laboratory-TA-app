package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.MessageInfo;
import db.MessageInfoManager;

@WebServlet(urlPatterns = { "/MessageInfoServlet" })
public class MessageInfoServlet extends HttpServlet {

	//=======================================
	// (GetMethod=参照処理)messageInfoListの取得
	//=======================================
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		// <==(ブラウザ) 入力テキストの入力
		String str = req.getParameter("LastMessageInfoRID");
		int lastMessagInfoRID = Integer.parseInt(str);

		// <==(Session) メッセージリストの取得
		LinkedList<MessageInfo> messageInfoList = getMessageInfoList(session);
		List<MessageInfo> filterdMessageInfoList = messageInfoList
				.stream()
				.filter(messageInfo -> messageInfo.getRID() > lastMessagInfoRID)
				.collect(Collectors.toList());

		// メッセージリスト を JSON形式のメッセージリスト に変換
		String jsonMessageInfoList = getJsonMessageInfoList(filterdMessageInfoList);

		// ==>(ブラウザ) JSON形式のメッセージリストを出力
		PrintWriter out = resp.getWriter();
		out.println(jsonMessageInfoList);
	}

	//=======================================
	// (PostMethod=追加処理)messageInfoの追加
	//=======================================
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 設定（文字コード、Session）
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();

		// <==(Session) 利用者名の取得
		String currentUserName = getCurrentUserName(session);

		// <==(ブラウザ) 入力テキストの取得
		String message = req.getParameter("Message");

		// メッセージを生成
		MessageInfo messageInfo = createMessageInfo(currentUserName, message);

		// ==>(ブラウザ)メッセージを保存
		addMessageInfo(session, messageInfo);
	}

	//=============================
	// 補助関数（入力、出力）
	//=============================

	//-----------------------------
	// （Sessionの場合）
	//-----------------------------
	// <--(Session) ユーザ名
	private String getCurrentUserName(HttpSession session) {
		String currentUserName = "";

		if (session.getAttribute("CurrentUserName") != null) {
			currentUserName = (String) session.getAttribute("CurrentUserName");
		}

		return currentUserName;
	}

	// <--(Session) メッセージリスト
	private LinkedList<MessageInfo> getMessageInfoList(HttpSession session) {
		LinkedList<MessageInfo> messageInfoList = new LinkedList<MessageInfo>();

		if (session.getAttribute("MessageInfoList") != null) {
			messageInfoList = (LinkedList) session.getAttribute("MessageInfoList");
		}

		return messageInfoList;
	}

	// <--(Session) メッセージ
	private void addMessageInfo(HttpSession session, MessageInfo messageInfo) {
		// メッセージリストを取得
		LinkedList<MessageInfo> messageInfoList = getMessageInfoList(session);

		// RIDを設定
		int nextRID = 1;
		if (messageInfoList.size() > 1) {
			nextRID = messageInfoList.getLast().getRID() + 1;
		}
		messageInfo.setRID(nextRID);

		// メッセージリストにメッセージを追加
		messageInfoList.add(messageInfo);

		// メッセージリストを保存
		session.setAttribute("MessageInfoList", messageInfoList);
	}

	//-----------------------------
	// （DataBaseの場合）
	//-----------------------------
	// <--(DB) メッセージリスト
	private LinkedList<MessageInfo> getMessageInfoList() {
		MessageInfoManager mm = new MessageInfoManager();
		return mm.getMessageInfoList();
	}

	// <--(DB) メッセージ
	private void addMessageInfo(MessageInfo messageInfo) {
		MessageInfoManager mm = new MessageInfoManager();
		mm.addMessageInfo(messageInfo);
	}

	//=============================
	// 補助関数（生成、変換）
	//=============================
	//----------------------------------------------------
	// メッセージリストをJSON形式のメッセージリストに変換
	//----------------------------------------------------
	private String getJsonMessageInfoList(List<MessageInfo> messageInfoList) throws JsonProcessingException {
		String jsonMessageInfoList = "";

		ObjectMapper mapper = new ObjectMapper();
		jsonMessageInfoList = mapper.writeValueAsString(messageInfoList);

		return jsonMessageInfoList;
	}

	//-----------------------------
	// メッセージオブジェクト生成
	//-----------------------------
	private MessageInfo createMessageInfo(String userName, String message) {
		MessageInfo messageInfo = new MessageInfo();

		messageInfo.setUserName(userName);
		messageInfo.setMessage(message);

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		messageInfo.setYear(year);
		int month = calendar.get(Calendar.MONTH) + 1;
		messageInfo.setMonth(month);
		int day = calendar.get(Calendar.DATE);
		messageInfo.setDay(day);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		messageInfo.setHour(hour);
		int minute = calendar.get(Calendar.MINUTE);
		messageInfo.setMinute(minute);
		int second = calendar.get(Calendar.SECOND);
		messageInfo.setSecond(second);

		return messageInfo;
	}

	//=============================
	// サンプルデータ生成
	//=============================
	private LinkedList<MessageInfo> createSampleMessageInfoList() {
		LinkedList<MessageInfo> messageInfoList = new LinkedList<MessageInfo>();

		// 1つ目
		MessageInfo messageInfo1 = new MessageInfo();
		messageInfo1.setRID(1);
		messageInfo1.setUserName("吉田");
		messageInfo1.setMessage("おはよう");
		messageInfo1.setYear(2021);
		messageInfo1.setMonth(11);
		messageInfo1.setDay(29);
		messageInfo1.setHour(13);
		messageInfo1.setMinute(00);

		messageInfoList.add(messageInfo1);

		// 2つ目

		MessageInfo messageInfo2 = new MessageInfo();
		messageInfo2.setRID(1);
		messageInfo2.setUserName("謎のTA");
		messageInfo2.setMessage("おはようございます");
		messageInfo2.setYear(2021);
		messageInfo2.setMonth(11);
		messageInfo2.setDay(29);
		messageInfo2.setHour(13);
		messageInfo2.setMinute(05);

		messageInfoList.add(messageInfo2);

		return messageInfoList;
	}
}
