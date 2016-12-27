import com.hongganju.common.socket.DCSocketConfig;


public class TestLinuxExec {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cmd = "./crawlUrl.sh 1 http://www.sina.com.cn " + DCSocketConfig.innerPort;
		System.out.println(cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
