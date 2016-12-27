
package irm;

/**
 * 主程序入口
 * @author	TangDican
 * @email	tangdican2008@163.com
 * @date	2013 Aug 28
 */
public class IRMMain {	
	// main方法
	public static void main(String[] args)
	{	
		// 给定实例id的值
		String[] instanceIds = "82".split(",");
		
		// 创建WResourceView
		// QualityAnalysis qa = new QualityAnalysis();
		// 运行实例
		// qa.run(instanceIds);
		
		// 创建WResourceView
		//WResourceView wrv = new WResourceView();
		// 运行实例
		//wrv.run(instanceIds);
		
		// 创建Monitor
		//Monitor m = new Monitor();
		// 运行实例
		//m.run(instanceIds);
		
		// 创建ResourceIn
		// ResourceIn ri = new ResourceIn();
		// 运行实例
		// ri.run(instanceIds);
		
		// 创建ResourceDuplicate
		 //ResourceDuplicate rd = new ResourceDuplicate();
		// 运行实例
		// rd.run(instanceIds);
		
		// 创建ResourceDuplicateDetail
		//ResourceDuplicateDetail rdd = new ResourceDuplicateDetail();
		// 运行实例
		//rdd.run(instanceIds);
		
		// 创建SiteIn
		SiteIn si = new SiteIn();
		// 运行实例
		si.run(instanceIds);
		
		// 创建SiteView
		//SiteView sv = new SiteView();
		// 运行实例
		//sv.run(instanceIds);
		
		// 创建ProvinceSiteView
		//ProvinceSiteView psv = new ProvinceSiteView();
		// 运行实例
		//psv.run(instanceIds);
		
		// 创建SiteInView
		//SiteInView siv = new SiteInView();
		// 运行实例
		//siv.run(instanceIds);
		
		// 创建SiteDetail
		//SiteDetail sd = new SiteDetail();
		// 运行实例
		//sd.run(instanceIds);
		
		// 创建DomainView
		//DomainView dv = new DomainView();
		// 运行实例
		//dv.run(instanceIds);
		
		// 创建Domain
		//Domain d = new Domain();
		// 运行实例
		//d.run(instanceIds);
	}
}
