package util;

import annotation.RpcService;

@RpcService(value = tiyubu.class)
public class Testz implements tiyubu {
public String tiyubusima(int a,int b) {
	return a+b+"";
}
}
