package zookeeper;

import java.util.HashMap;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subscriber {
	
public Logger logger=LoggerFactory.getLogger(Subscriber.class);
public String ip;
public int port;
public static String path="/GuobboRegisty";
public static HashMap<String, String> directory=new HashMap<String, String>();

public Subscriber(String ip,int port) {
		this.ip=ip;
		this.port=port;
}

public void subscribe() {
	 ZkClient zkClient = new ZkClient(ip+":"+port,5000);
     logger.info("subscribe zookeeper node"+ip+":"+port+" begin");
     List<String>serviceList=zkClient.getChildren(path);
     for(String service:serviceList) {
    	 String data=zkClient.readData(path+"/"+service);
    	 System.out.println(service+data);
    	 directory.put(service, data);
     }
     logger.info("subscribe zookeeper node"+ip+":"+port+" success");
}
}