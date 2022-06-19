package db;

import java.sql.ResultSet;
import java.util.LinkedList;

import beans.MessageInfo;

public class MessageInfoManager extends DataBaseManager {

	public Object copyRecord(ResultSet rs) throws Exception {
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setRID(rs.getInt("RID"));
		messageInfo.setUserName(rs.getString("UserName"));
		messageInfo.setMessage(rs.getString("message"));
		messageInfo.setYear(rs.getInt("Year"));
		messageInfo.setMonth(rs.getInt("Month"));
		messageInfo.setDay(rs.getInt("Day"));
		messageInfo.setHour(rs.getInt("Hour"));
		messageInfo.setMinute(rs.getInt("Minute"));
		messageInfo.setSecond(rs.getInt("Second"));
		return messageInfo;
	}

	public LinkedList<MessageInfo> getMessageInfoList() {
		String sql = "SELECT * FROM MessageInfos";
		return getRecords(sql);
	}

	public void addMessageInfo(MessageInfo messageInfo) {
		String sql = "";
		sql += "Insert into MessageInfos ( UserName,Message,Year, Month, Day, Hour, Minute, Second )VALUES(";
		sql += "'" + messageInfo.getUserName() + "'";
		sql += ",";
		sql += "'" + messageInfo.getMessage() + "'";
		sql += ",";
		sql += messageInfo.getYear();
		sql += ",";
		sql += messageInfo.getMonth();
		sql += ",";
		sql += messageInfo.getDay();
		sql += ",";
		sql += messageInfo.getHour();
		sql += ",";
		sql += messageInfo.getMinute();
		sql += ",";
		sql += messageInfo.getSecond();
		sql += ")";
		updateRecord(sql);
	}
}
