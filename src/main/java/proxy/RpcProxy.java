package proxy;

import java.lang.reflect.Proxy;

public class RpcProxy  {
@SuppressWarnings("unchecked")
public static <T> T getInstance(Class<?> target) {
	return (T) Proxy.newProxyInstance(target.getClassLoader(),new Class<?>[] {target}, new RpcInvocationHandler());
}
}
