package nullguo.guobbo;

import Serialization.SerializationUtil;
import pojo.RpcResponse;
import zookeeper.Registy;
import zookeeper.Subscriber;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        Registy registy=new Registy("111.230.100.33",2181);
        registy.register("fuck");
        Thread.sleep(2000);
        Subscriber subscriber=new Subscriber("111.230.100.33",2181);
        subscriber.subscribe();
        RpcResponse response=new RpcResponse();
        response.setRequestId("gjb654");
        response.setErrorCode("200");
        response.setResult("fuckyoutiyubu");
        byte[] data=SerializationUtil.serialize(response);
        RpcResponse response2=(RpcResponse)SerializationUtil.deserialize(data, RpcResponse.class);
        System.out.println(response2.result.toString());
    }
}
