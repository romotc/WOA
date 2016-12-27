package com.flv.weitang;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class CopyAndWrite {

	public static void main(String[] args) {
		
		CopyAndWrite cw = new CopyAndWrite();
		
		try {
			cw.setClipboardText("this is a test String");
			
//			String h = cw.getClipboardText();
//			
//			System.out.println(h);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected String getClipboardText() throws Exception {
		
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		// 获取剪切板中的内容
		Transferable clipT = clip.getContents(null);
		if (clipT != null) {
			// 检查内容是否是文本类型
			if (clipT.isDataFlavorSupported(DataFlavor.stringFlavor))
				return (String) clipT.getTransferData(DataFlavor.stringFlavor);
		}
		return null;
	}

	protected void setClipboardText(String writeMe) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}
}
