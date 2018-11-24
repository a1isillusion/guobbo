package util;

import java.lang.reflect.Method;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import pojo.RpcRequest;
import pojo.RpcResponse;

public class ReflectionUtil {
public static RpcResponse invoke(RpcRequest request) {
	RpcResponse response=new RpcResponse();
	response.setRequestId(request.getRequestId());
	response.setErrorCode("200");
	response.setResult(CglibReflect(request));
	return response;
}
public static Object JdkReflect(RpcRequest request) {//JDK反射
	Object result=null;
	String classname=request.getClassName();
	Object serviceBean=AnnotationUtil.getBeanList().get(classname);
	Class<?>serviceClass=serviceBean.getClass();
	String methodName=request.getMethodName();
	Class<?>[]parameterTypes=request.getParameterTypes();
	Object[] parameters=request.getParameters();
	try {
		Method method=serviceClass.getMethod(methodName, parameterTypes);
		method.setAccessible(true);
		result=method.invoke(serviceBean, parameters);
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return result;
}

public static Object CglibReflect(RpcRequest request) {//Cglib反射
	Object result=null;
	String classname=request.getClassName();
	Object serviceBean=AnnotationUtil.getBeanList().get(classname);
	Class<?>serviceClass=serviceBean.getClass();
	String methodName=request.getMethodName();
	Class<?>[]parameterTypes=request.getParameterTypes();
	Object[] parameters=request.getParameters();
	try {
		 FastClass serviceFastClass = FastClass.create(serviceClass);
	     FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
	     return serviceFastMethod.invoke(serviceBean, parameters);
	} catch (Exception e) {
		e.printStackTrace();
	} 
	return result;
}
}
