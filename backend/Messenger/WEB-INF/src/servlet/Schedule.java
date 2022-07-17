package servlet;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import beans.Pc;

class Schedule extends TimerTask {

    private static Schedule instance = new Schedule();
    private Schedule() {
    }

    @Override
    public void run() {
    	
    	//現在時刻の取得
		long millis = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(millis);
		// Date型へ変換
        Date dt = new Date(timestamp.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        //現在時刻から30分前に閾値をセット
        cal.add(Calendar.MINUTE, -30);
		
		List<Pc> pcList = StartServlet.getPcList();
		
		for(Pc pc : pcList) {
			if(pc.getLastRequestTime() != null && pc.getHelpStatus() == "None") {
				Calendar pc_cal = Calendar.getInstance();
				pc_cal.setTime(pc.getLastRequestTime());
				if(pc_cal.before(cal)) {
					//ログイン状態をfalseにする
					pc.setIsLogin(false);
					pc.setLastRequestTime(null);
					pc.setHelpStatus("None");
				}
			}
			pc.getLastRequestTime();
		}
    }

    public static Schedule getInstance() {
        return instance;
    }
}