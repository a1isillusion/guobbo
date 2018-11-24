package util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.RpcService;

public class AnnotationUtil {
public static Map<String,Object> beanList=new HashMap<String,Object>();	
public void scanAnootation() {
	 beanList.clear();
	 File f = new File(this.getClass().getResource("/").getPath());
	 File [] fileList=f.listFiles();
	 for(File file:fileList) {
		 if(file.isDirectory()&&!file.getName().equals("META-INF")) {
			  validAnnotation(file.getName());
		 }
	 }
    
}
public static void validAnnotation(String packageName){
	List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(packageName);
	for(Class<?> c:clsList) {
		if(c.isAnnotationPresent(RpcService.class)) {
			try {
				beanList.put(c.getAnnotation(RpcService.class).value().getName(),c.newInstance());
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
