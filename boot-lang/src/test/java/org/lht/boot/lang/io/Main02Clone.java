package org.lht.boot.lang.io;

import org.junit.jupiter.api.Test;
import org.lht.boot.lang.TestObject;

import java.io.*;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/19 13:48
 **/
public class Main02Clone {

    /**
     * 深度克隆
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void test01() throws IOException, ClassNotFoundException {
        TestObject testObject = new TestObject("nihao", "hello");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(testObject);
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        TestObject testObject1 = (TestObject) ois.readObject();
        System.out.println(testObject1);
        // 说明：调用 ByteArrayInputStream 或 ByteArrayOutputStream 对象的 close 方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这不同于对外部资源（如文件流）的释放
    }
}
