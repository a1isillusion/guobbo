package pojo;


public class RpcRequest {
public String requestId;
public String className;
public String methodName;
public Class<?>[] parameterTypes;
public Object[] parameters;
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public String getMethodName() {
	return methodName;
}
public void setMethodName(String methodName) {
	this.methodName = methodName;
}
public Class<?>[] getParameterTypes() {
	return parameterTypes;
}
public void setParameterTypes(Class<?>[] parameterTypes) {
	this.parameterTypes = parameterTypes;
}
public Object[] getParameters() {
	return parameters;
}
public void setParameters(Object[] parameters) {
	this.parameters = parameters;
}

}