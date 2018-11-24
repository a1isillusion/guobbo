package zookeeper;


import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.AnnotationUtil;

public class Registy {
public Logger logger=LoggerFactory.getLogger(Registy.class);
public String ip;
public int port;
public static ZkClient zkClient;
public static String path="/GuobboRegisty";
public Registy(String ip,int port) {
	this.ip=ip;
	this.port=port;
	if(zkClient==null) {
		zkClient = new ZkClient(ip+":"+port,5000);
	}
	
}
public void initRegisty(String service) {
	if(!zkClient.exists(path+"/"+service)) {
		zkClient.createPersistent(path+"/"+service, true);
	}
}
public void regist(String service,String address) {
     logger.info("regisy zookeeper node"+ip+":"+port+"for service"+service+" begin");
     initRegisty(service);
     zkClient.createEphemeral(path+"/"+service+"/"+address,Ids.OPEN_ACL_UNSAFE);
     logger.info("regisy zookeeper node"+ip+":"+port+"for service"+service+" success");
}
public void registService(String address) {
	AnnotationUtil annotationUtil=new AnnotationUtil();
	annotationUtil.scanAnootation();
	for(String service:AnnotationUtil.beanList.keySet()) {
		regist(service, address);
	}
}
}
