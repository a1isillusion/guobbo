package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;
import netty.RPCClient;
import pojo.RpcRequest;
import zookeeper.Subscriber;

public class RpcInvocationHandler implements InvocationHandler{
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RpcRequest request =new RpcRequest();
		request.setRequestId(UUID.randomUUID().toString());
		request.setClassName(method.getDeclaringClass().getName());
		request.setMethodName(method.getName());
		request.setParameterTypes(method.getParameterTypes());
		request.setParameters(args);
		System.out.println(method.getDeclaringClass().getName());
		String address=Subscriber.discover(method.getDeclaringClass().getName());
		RPCClient client=new RPCClient(address, request);
		client.invoke();
        return client.response.getResult();
/*		return ReflectionUtil.JdkReflect(request);*/
    }

}
