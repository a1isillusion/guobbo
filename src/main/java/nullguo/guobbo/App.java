package nullguo.guobbo;

import java.lang.reflect.Method;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

import netty.RPCClient;
import pojo.RpcRequest;
import pojo.RpcResponse;
import proxy.RpcProxy;
import serialization.SerializationUtil;
import util.AnnotationUtil;
import util.ClassUtil;
import util.ReflectionUtil;
import util.Testz;
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
     Registy registy=new Registy("111.230.100.33", 2181);
     registy.registService("localhost:14561");
     Thread.sleep(2000);
     Subscriber subscriber=new Subscriber("111.230.100.33", 2181);
     subscriber.subscribe();
     subscriber.directoryShow();
    }
}
