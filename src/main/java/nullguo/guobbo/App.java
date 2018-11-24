package nullguo.guobbo;

import java.lang.reflect.Method;
import java.util.List;

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
    AnnotationUtil.validAnnotation("util");
    RpcRequest request=new RpcRequest();
    request.setClassName(Testz.class.getName());
    request.setMethodName("tiyubusima");
    Method[] methods=Testz.class.getMethods();
    Method method=null;
    for(int i=0;i<methods.length;i++) {
    	if(methods[i].getName().equals("tiyubusima"))method=methods[i];
    }
    request.setParameterTypes(method.getParameterTypes());
    request.setParameters(new Object[] {3,2});
    System.out.println(ReflectionUtil.JdkReflect(request).toString());

    }
}
