package flvTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.hongganju.common.util.FileUtil;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public class TestFlv {

	public interface Analyser extends Library{
    	Analyser INSTANCE = (Analyser)Native.loadLibrary("lib/Analyzer", Analyser.class);
    	
    	void InitAnalyzer();
//    	boolean GetTrueUrlCo(String pInitialUrl, PointerByReference ppTrueUrl, 
//    			PointerByReference ppTitle, PointerByReference ppReferer, 
//    			PointerByReference ppAgent);
    	boolean GetMultiVersionTrueUrlWithReferer(String pInitialUrl, PointerByReference ppTrueUrl, 
    			PointerByReference ppTitle, PointerByReference ppReferer, 
    			PointerByReference ppAgent);
    }

    public static void main(String[] args) throws IOException {

    	Analyser.INSTANCE.InitAnalyzer();
    	
    	for(int i=0; i<5000; i++)
    	{
    		long t1 = System.currentTimeMillis();
    		String result = test();
    		long t2 = System.currentTimeMillis();
    		String line = new Date() + ": runTime=" + (t2-t1) + "ms " + result;
    		System.out.println(line);
    		FileUtil.append("dll.log", line);
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    }
    public static String test()
    {
    	PointerByReference turl = new PointerByReference();
    	PointerByReference title = new PointerByReference();
    	PointerByReference ref = new PointerByReference();
    	PointerByReference agent = new PointerByReference();
    	
    	String url ="http://tv.sohu.com/20121214/n360404534.shtml";
    	url = "http://v.youku.com/v_show/id_XNDg3NzEzNjQ0.html";
    	url ="http://v.youku.com/v_show/id_XNDg5OTU1MTI0.html?f=18729103";
    	url = "http://v.youku.com/v_show/id_XMzk3ODY0OTIw.html";
    	//http://f.youku.com/player/getFlvPath/sid/13558286046361041_00/st/flv/fileid/030002010050CFF9F0733804E9D2A796F8C70C-5B5B-16AA-BD1B-7988E09234C3?K=824be968af65731a24113d81&hd=0|||http://f.youku.com/player/getFlvPath/sid/13558286046361041_00/st/mp4/fileid/030008010050CFFBE1733804E9D2A796F8C70C-5B5B-16AA-BD1B-7988E09234C3?K=919ca9dcaf4cd50c24113d81&hd=1|||
//    	Analyser.INSTANCE.GetTrueUrlCo(url, turl, title, ref, agent);
    	Analyser.INSTANCE.GetMultiVersionTrueUrlWithReferer(url, turl, title, ref, agent);
    	Pointer s = turl.getValue();
    	if(s == null)
    		return null;
    	String strUrl = turl.getValue().getString(0);
    	String strTitle = title.getValue().getString(0);
    	byte[] bbb;
    	try {
    		bbb = title.getValue().getByteArray(0, strTitle.length());
    		strTitle = new String(bbb, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String strRef = ref.getValue().getString(0);
    	String strAgent = agent.getValue().getString(0);
    	
    	System.out.println(strUrl);
    	System.out.println(strTitle);
    	System.out.println(strRef);
    	System.out.println(strAgent);
    	return strUrl;
    }
}
