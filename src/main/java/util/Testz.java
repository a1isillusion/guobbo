package util;

import annotation.RpcService;

@RpcService(value = Testz.class)
public class Testz {
public String tiyubusima(int a,int b) {
	return a+b+"";
}
}
