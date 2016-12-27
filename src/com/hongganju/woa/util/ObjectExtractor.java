package com.hongganju.woa.util;

import java.util.ArrayList;
import java.util.List;

import lotusroots.browser.html.parser.NekoHtmlParser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pt.client.crawl.Crawler;


public class ObjectExtractor {
	//url用来拼object的完整url
	public static void main(String[] args)
	{
		Crawler c = new Crawler();
	}
	public static List<String> extractObjects(String html, String url)
	{
		NekoHtmlParser parser = new NekoHtmlParser();
		Node root;
		int p = url.lastIndexOf("/");
		String url2 = url;
		if(p > 0)
		{
			url2 = url.substring(0, p+1);
		}
		try {
			root = parser.parseStandard(html);
			return extractUrl(root,url2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	//by liqiang:直接抽取href
	private static List<String> extractUrl(Node root, String url)
	{
		List<String> list = new ArrayList<String>();
		extractUrl(root, list, url);
		return list;
	}
	private static void extractUrl(Node node, List<String> list, String url) {
		// TODO Auto-generated method stub
		if(node == null)
			return;
		if(node.getNodeName().equals("script") || node.getNodeName().equals("SCRIPT"))
		{
			NamedNodeMap map = node.getAttributes();
			String value = null;
			Node n = map.getNamedItem("SRC");
			if( n!= null)
			{
				value = n.getNodeValue();
			}
			n = map.getNamedItem("src");
			if(n != null)
			{
				value = n.getNodeValue();
			}
//			System.out.println(value);			
			addValue(value, url, list);			
//			String href = aNode.getAttributes().getNamedItem("HREF").getNodeValue();
		}
		
		if(node.getNodeName().equals("img") || node.getNodeName().equals("IMG"))
		{
			NamedNodeMap map = node.getAttributes();
			String value = null;
			Node n = map.getNamedItem("SRC");
			if( n!= null)
			{
				value = n.getNodeValue();
			}
			n = map.getNamedItem("src");
			if(n != null)
			{
				value = n.getNodeValue();
			}
//			System.out.println(value);			
			addValue(value, url, list);			
//			String href = aNode.getAttributes().getNamedItem("HREF").getNodeValue();
		}
		
		if(node.getNodeName().equals("link") || node.getNodeName().equals("LINK"))
		{
			NamedNodeMap map = node.getAttributes();
			String value = null;
			Node n = map.getNamedItem("HREF");
			if( n!= null)
			{
				value = n.getNodeValue();
			}
			n = map.getNamedItem("href");
			if(n != null)
			{
				value = n.getNodeValue();
			}
//			System.out.println(value);			
			addValue(value, url, list);			
//			String href = aNode.getAttributes().getNamedItem("HREF").getNodeValue();
		}
		
		//递归
		NodeList childs = node.getChildNodes();
		if(childs == null)
			return;
		for(int i=0; i<childs.getLength(); i++)
		{
			Node child = childs.item(i);
			extractUrl(child, list, url);
		}
	}
	static private void addValue(String value, String url, List<String> list)
	{
		//去掉内部锚点
		if(value != null && !value.trim().startsWith("#"))
		{
			String fullUrl = value.trim();
			if(fullUrl.startsWith("/")){
				String prefix = url;
				int p = url.indexOf("/", 7);
				if(p > 0)
					prefix = url.substring(0,p);
				fullUrl = prefix + fullUrl;
			}else if(!fullUrl.startsWith("http://") && !fullUrl.startsWith("https://"))
			{
				fullUrl = url + fullUrl;
			}
			list.add(fullUrl);
		}
	}
}
