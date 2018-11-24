package init;

import zookeeper.Subscriber;

public class InitClient {
public static void init(String address) {
	String[] ipport=address.split(":");
    Subscriber subscriber=new Subscriber(ipport[0], Integer.parseInt(ipport[1]));
    subscriber.subscribe();
}
}
