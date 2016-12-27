package com.hongganju.woa;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hongganju.common.Path;
import com.hongganju.common.util.NumUtil;
import com.hongganju.node.NodeConfig;
import com.hongganju.woa.factory.WOASpiderFactory;
import com.hongganju.woa.spider.WOASpider;
import com.hongganju.woa.spider.WOASpiderParam;

import net.sf.json.JSONArray;

public class WOAServlet extends HttpServlet {
	public void init() {
		String path = this.getServletContext().getRealPath("");
		//path += "";
		Path.setPath(path);
		System.out.println(path);
		System.out.println("初始化WOAServlet");
		System.out.println("初始化WOAServlet成功");
		WOASimpleTest.runTest();
	}

//	  <p>请输入TaskId: <input type="text" name="taskId" /></p>
//	  <p>请输入InstanceId: <input type="text" name="instanceId" /></p>
//	  <p>请输入url数量: <input type="text" name="totalUrl" /></p>
//	  <p>请输入深度: <input type="text" name="depth" /></p>
//	  <p>WebSite: <input type="text" name="webSite" /></p>
//	  <p>domain key: <input type="text" name="domain" /></p>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("enter");
		Integer taskId;
		Integer instanceId;
		Integer totalUrl;
		Integer depth;
		try{
			taskId = Integer.parseInt(request.getParameter("taskId"));
			instanceId = Integer.parseInt(request.getParameter("instanceId"));
			totalUrl = Integer.parseInt(request.getParameter("totalUrl"));
			depth = Integer.parseInt(request.getParameter("depth"));
		}catch(Exception e)
		{
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("请输入数字");
			return;
		}
		String webSite = request.getParameter("webSite");
		String domain = request.getParameter("domain");
		if(webSite == null || webSite.trim().equals("") || domain == null || domain.trim().equals(""))
		{
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("webSite和domain不能空");
			return;
		}
		if(totalUrl > 10000)
		{
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("totalUrl不能超过10000");
			return;
		}
		System.out.println("2");
		// MysqlMigration m = new MysqlMigration();
		// m.migrationCenterDB();
		// 教育网
		NodeConfig.setIsp("CN0002");
		NodeConfig.setIp("119.57.54.29");
		NodeConfig.setArea("CN1100");
		NodeConfig.setClusterId(2);

		// TODO Auto-generated method stub
		WOASpiderParam param = new WOASpiderParam();
		param.setDepth(depth);
		param.setSeed(webSite);
		param.setDomains(domain);
		// param.setFilterKeys("zol");
		param.setTotalUrls(totalUrl);
		param.taskId = taskId;
		param.dbid = (short) 41;
		param.taskInstanceId = instanceId;
		param.customerId = 13;
		System.out.println("ready");
		final WOASpider spider = WOASpiderFactory.createIESpider(param);
		new Thread()
		{
			public void run(){
				System.out.println("开始执行");
				spider.start();
				System.out.println("执行完毕");
			}
		}.start();
		
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("任务正在执行");
	}
	
}