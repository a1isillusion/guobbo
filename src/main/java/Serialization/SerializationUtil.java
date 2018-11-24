package serialization;

import com.alibaba.fastjson.JSON;

public class SerializationUtil {
public static Object deserialize(byte[] data,Class<?> genericClass) {
	return JSON.parseObject(new String(data), genericClass);
}
public static byte[] serialize(Object obj) {
	return JSON.toJSONString(obj).getBytes();
}
}
