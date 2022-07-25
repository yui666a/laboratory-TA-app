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
		Date[] handTimeList = new Date[pcList.size()]; //座席数分の配列を用意
		int listNum = 0; //カウンタ
		for(Pc pc : pcList) {
			
			//挙手の順番をリセット
			pc.setHandPriority(-1);
			//1. 挙手しているPCの数とその時間を調べる
			if(pc.getLastHandTime() != null) {
				handTimeList[listNum] = pc.getLastHandTime();
				listNum++;
			}
			
			//ログイン中かつ手をあげていない状態のみログイン状態を継続するか確認する
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
		}
		
		//1.で調べたPCの数とその時間から手をあげた順番をセットする
		for(int i=0; i<listNum; i++) {
			for(Pc pc : pcList) {
				if(pc.getLastHandTime() == handTimeList[i]) {
					pc.setHandPriority(i+1);
				}
			}
		}
    }

    public static Schedule getInstance() {
        return instance;
    }
}