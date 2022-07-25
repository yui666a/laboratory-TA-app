package servlet;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
		ArrayList<String> handTimeList = new ArrayList<>(); //座席数分のリストを用意
		for(Pc pc : pcList) {
			//挙手しているPCの数とその時間を調べる
			if(pc.getLastHandTime() != null) {
				String strDate = new SimpleDateFormat("yyyyMMddHHmmss").format(pc.getLastHandTime());
				handTimeList.add(strDate);
			} else {
				//挙手の順番をリセット
				pc.setHandPriority(-1);
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
		
		//挙手の時間をソートし順番を決める
		Collections.sort(handTimeList);
		//手をあげた順番をhandPriorityにセットする
		int priority = 1;
		for(String handTime : handTimeList) {
			for(Pc pc : pcList) {
				if(pc.getLastHandTime() != null) {
					String strDate = new SimpleDateFormat("yyyyMMddHHmmss").format(pc.getLastHandTime());
					if(strDate.equals(handTime)) {
						pc.setHandPriority(priority);
						priority++;
						break;
					}
				}

			}
		}
    }

    public static Schedule getInstance() {
        return instance;
    }
}