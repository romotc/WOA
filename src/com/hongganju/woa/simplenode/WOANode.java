package com.hongganju.woa.simplenode;

import com.hongganju.common.util.DBUtil;
import com.hongganju.db.dao.WoaclusterDao;
import com.hongganju.db.entity.center.Ipregion;
import com.hongganju.db.entity.center.Woacluster;
import com.hongganju.woa.spider.WOAConfig;

public class WOANode {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WOANode node = new WOANode();
		node.init();
	}
	public void init()
	{
		//初始化
		WOAConfig.config.init();
		initCluster();
	}
	
	private void initCluster()
	{
		Woacluster cluster = getCluster();
		if(cluster == null)
		{
			createCluster();
		}else
		{
			WOAConfig.config.setAreaId(cluster.getAreaId());
			WOAConfig.config.setIspId(cluster.getIspid());
		}
	}
	private void createCluster() {
		// TODO Auto-generated method stub
		Woacluster cluster = new Woacluster();
		String clusterId = WOAConfig.config.getClusterId();
		Integer id = Integer.parseInt(clusterId);
		cluster.setClusterId(id);
		Ipregion region = DBUtil.getIpregion(WOAConfig.config.getIp());
		if(region != null)
		{
			cluster.setAreaId(region.getArea());
			cluster.setIspid(region.getIsp());
		}
		WoaclusterDao dao = new WoaclusterDao();
		dao.save(cluster);
	}
	public static Woacluster getCluster()
    {
		String clusterId = WOAConfig.config.getClusterId();
		Integer id = Integer.parseInt(clusterId);
		WoaclusterDao dao = new WoaclusterDao();
		Woacluster cluster = dao.getWoaclusterByID(id);
		return cluster;
    }
}
