package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.RpcService;

public class AnnotationUtil {
public static Map<String,Object> beanList=new HashMap<String,Object>();	
public static void validAnnotation(String packageName){
	beanList.clear();
	List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(packageName);
	for(Class<?> c:clsList) {
		if(c.isAnnotationPresent(RpcService.class)) {
			try {
				beanList.put(c.getName(),c.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
}
public static Map<String, Object> getBeanList() {
	return beanList;
}
public static void setBeanList(Map<String,Object> beanList) {
	AnnotationUtil.beanList = beanList;
}

}
