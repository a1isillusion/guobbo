package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
public static Object deserialize(byte[] data) {
    ByteArrayInputStream bais = null;
    try {
        // 反序列化
        bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    } catch (Exception e) {
        System.out.println("bytes Could not deserialize:" + e.toString());
        return null;
    } finally {
        try {
            if (bais != null) {
                bais.close();
            }
        } catch (IOException ex) {
            System.out.println("LogManage Could not serialize:" + ex.toString());
        }
    }
}
public static byte[] serialize(Object obj) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream bos = null;
    try {
        bos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        byte[] b = bos.toByteArray();
        return b;
    } catch (IOException e) {
        System.out.println("序列化失败 Exception:" + e.toString());
        return null;
    } finally {
        try {
            if (oos != null) {
                oos.close();
            }
            if (bos != null) {
                bos.close();
            }
        } catch (IOException ex) {
            System.out.println("io could not close:" + ex.toString());
        }
    }
}
}
