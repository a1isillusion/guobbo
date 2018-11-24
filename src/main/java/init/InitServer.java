package init;

import netty.RpcServer;
import zookeeper.Registy;

public class InitServer {
public static void init(String address,String localAddress) {
	String[] ipport=address.split(":");
	String[] localipport=localAddress.split(":");
    Registy registy=new Registy(ipport[0], Integer.parseInt(ipport[1]));
    registy.registService(localAddress);
    RpcServer server=new RpcServer();
    server.bind(Integer.parseInt(localipport[1]));
}
}
