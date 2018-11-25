package nullguo.guobbo;

import java.beans.beancontext.BeanContext;
import java.lang.reflect.Method;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

import com.alibaba.fastjson.JSON;

import init.InitClient;
import netty.RPCClient;
import pojo.RpcRequest;
import pojo.RpcResponse;
import proxy.RpcProxy;
import serialization.SerializationUtil;
import util.AnnotationUtil;
import util.ClassUtil;
import util.ReflectionUtil;
import util.Testz;
import util.tiyubu;
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
     InitClient.init("111.230.100.33:2181");
     tiyubu tiyububean=RpcProxy.getInstance(tiyubu.class);
     System.out.println(tiyububean.tiyubusima(2, 4));
     tiyubu tiyububean1=RpcProxy.getInstance(tiyubu.class);
     System.out.println(tiyububean1.tiyubusima(3, 4));
    }
   
    
     
}
