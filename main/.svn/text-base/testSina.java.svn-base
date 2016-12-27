import java.io.IOException;

import com.hongganju.common.socket.DCSocketClient;
import com.hongganju.common.socket.DCSocketConfig;
import com.hongganju.common.util.FileUtil;
import com.hongganju.woa.browser.ie.WOAMultiIEConfig;



public class testSina {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String encoded = FileUtil.read("sina.txt");
		System.out.println("文件长度：" + encoded.length());
		System.out.println("字节数：" + encoded.getBytes().length);
		String json_return = DCSocketClient.sendMessage(WOAMultiIEConfig.innerMsg, encoded, "localhost");
	}

}
