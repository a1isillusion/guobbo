package pojo;

public class RpcResponse {
public String requestId;
public String errorCode;
public Object result;
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
public String getErrorCode() {
	return errorCode;
}
public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
}
public Object getResult() {
	return result;
}
public void setResult(Object result) {
	this.result = result;
}

}
