package zookeeper;


import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registy {
public Logger logger=LoggerFactory.getLogger(Registy.class);
public String ip;
public int port;
public static String path="/GuobboRegisty";
public Registy(String ip,int port) {
	this.ip=ip;
	this.port=port;
}

public void register(String service) {
	 ZkClient zkClient = new ZkClient(ip+":"+port,5000);
     logger.info("regisy zookeeper node"+ip+":"+port+"for service"+service+" begin");
     zkClient.createEphemeral(path+"/"+service,ip+":"+port,Ids.OPEN_ACL_UNSAFE);
     logger.info("regisy zookeeper node"+ip+":"+port+"for service"+service+" success");
}

}
