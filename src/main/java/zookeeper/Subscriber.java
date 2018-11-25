package zookeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subscriber {
	
public Logger logger=LoggerFactory.getLogger(Subscriber.class);
public String ip;
public int port;
public static ZkClient zkClient;
public static String path="/GuobboRegisty";
public static HashMap<String, ArrayList<String>> directory=new HashMap<String, ArrayList<String>>();

public Subscriber(String ip,int port) {
		this.ip=ip;
		this.port=port;
		if(zkClient==null) {
			zkClient = new ZkClient(ip+":"+port,5000);
		}
		
}

public void subscribe() {
     logger.info("subscribe zookeeper node"+ip+":"+port+" begin");
     List<String>serviceList=zkClient.getChildren(path);
     for(String service:serviceList) {
    	 List<String> addressList=zkClient.getChildren(path+"/"+service);
    	 for(String address:addressList) {
    		 directoryPut(service, address);
    	 }
     }
     logger.info("subscribe zookeeper node"+ip+":"+port+" success");
}

public void directoryPut(String service,String address) {
	if(!directory.containsKey(service)) {
		ArrayList<String> addressList=new ArrayList<String>();
		addressList.add(address);
		directory.put(service,addressList);
	}else {
		ArrayList<String>addressList=directory.get(service);
		addressList.add(address);
	}
}
public void directoryShow() {
	for(String service:directory.keySet()) {
		for(String address:directory.get(service)) {
			System.out.println(service+"！"+address);
		}
	}
}
public static String discover(String service) {
	ArrayList<String>addressList=directory.get(service);
	Random random=new Random();
	return addressList.get(random.nextInt()%addressList.size());
}
public static boolean ifsubscribe() {
	return !directory.isEmpty();
}
}