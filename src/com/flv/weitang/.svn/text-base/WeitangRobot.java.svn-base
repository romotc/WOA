package com.flv.weitang;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class WeitangRobot {

	public final static String ViDownUri = "C:\\Program Files\\ViDown\\ViDown.exe";
	public final static String videoUri = "http://www.iqiyi.com/dianshiju/20130614/1a3add7b03001f0e.html";
	public final static int ScreenWidthBase = 1440;
	public final static int ScreenHeightBase = 900;
	
	private String actualVideoUri = "";
	private String videoTitle = "";
	
	public String getUri(){
		return this.actualVideoUri;
	}
	
	public String getVideoTitle() {
		return this.videoTitle;
	}

	

	private void sleep(int mSeconds) {

		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void getVideoString(String ViDownUri, String uri) {

		String actualUri = null;
		String videoDes = null;

		try {
			Process p = null;
			p = Runtime.getRuntime().exec(ViDownUri);

			this.sleep(2000);

			Robot robot;
			try {

				CopyAndWrite cw = new CopyAndWrite();
				cw.setClipboardText(uri);

				robot = new Robot();

				// 新建
				robot.mouseMove(95, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				// 正在下载
				robot.mouseMove(90, 120);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				this.sleep(1000);

				// 第一行
				robot.mouseMove(280, 120);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				this.sleep(1000);

				// 暂停
				// robot.mouseMove(60, 65);
				// robot.mousePress(InputEvent.BUTTON1_MASK);
				// robot.mouseRelease(InputEvent.BUTTON1_MASK);
				// this.sleep(1000);

				cw.setClipboardText("none");
				// 复制
				robot.mouseMove(285, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				this.sleep(1000);

				actualUri = cw.getClipboardText();
				if (actualUri.equals("none")) {
					this.sleep(4000);
					// 复制
					robot.mouseMove(285, 65);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					this.sleep(1000);
					actualUri = cw.getClipboardText();
				}

				// 暂停
				robot.mouseMove(60, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				this.sleep(1000);

				// 删除
				robot.mouseMove(130, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				if (!actualUri.equals("none")) {
					// System.out.print(uri);
				} else {
					// 已下载
					robot.mouseMove(90, 140);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					// 第一行
					robot.mouseMove(280, 120);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					this.sleep(1000);

					// 暂停
					robot.mouseMove(60, 65);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					this.sleep(1000);

					cw.setClipboardText("none");
					// 复制
					robot.mouseMove(285, 65);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					this.sleep(1000);

					// 删除
					robot.mouseMove(130, 65);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					actualUri = cw.getClipboardText();
					// System.out.print(uri);
				}

				this.sleep(1000);

				// 已删除
				robot.mouseMove(90, 160);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				// 第一行
				robot.mouseMove(280, 120);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				this.sleep(1000);
				
				//属性
				robot.mouseMove(165, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				this.sleep(1000);
				
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				
				robot.keyPress(KeyEvent.VK_CONTROL);  
			    robot.keyPress(KeyEvent.VK_C);
			    robot.keyRelease(KeyEvent.VK_C);  
			    robot.keyRelease(KeyEvent.VK_CONTROL); 
			    
			    this.sleep(1000);
			    robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			    
			    videoDes = cw.getClipboardText();

			    
				// 删除
				robot.mouseMove(130, 65);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				this.sleep(1000);

				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				// 正在下载
				robot.mouseMove(90, 120);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				this.sleep(1000);


				p.destroy();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// p.destroy();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.actualVideoUri = actualUri;
		this.videoTitle = videoDes;

//		return actualUri + '\n' + videoDes;
	}
	
	
	public static void main(String[] agrv) {

		WeitangRobot tc = new WeitangRobot();
		// String h = tc.getVideoString(TestControl.ViDownUri,
		// TestControl.videoUri);
		String h = "";
		if (agrv.length == 0) {
			System.out.println("use default weitang:" + WeitangRobot.ViDownUri);
			tc.getVideoString(WeitangRobot.ViDownUri, WeitangRobot.videoUri);
		} else {
			System.out.println("use weitang:" + agrv[0]);
			tc.getVideoString(agrv[0], WeitangRobot.videoUri);
		}

		System.err.println(tc.getUri() + ", " + tc.getVideoTitle());

	}
}
