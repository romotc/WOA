import java.util.Date;

import com.hongganju.common.util.DBUtil;
import com.hongganju.db.entity.task.Woacrawledurllog;


public class TestWoatimeouturl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Woacrawledurllog url = new Woacrawledurllog();
		url.setInstanceId(1);
		url.setTaskid(2);
		url.setUploadTime(new Date());
		url.setUrl("testTimeouturl");
		url.setErrorDescription("error");
		url.setStatus(5);
		DBUtil.saveJDBC(url, (short)41);
	}

}
