package org.lht.boot.lang.io;

import org.junit.jupiter.api.Test;
import org.lht.boot.lang.TestObject;

import java.io.*;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/19 11:24
 **/
public class Main1 {

    @Test
    public void test01() throws IOException, ClassNotFoundException {
        //对象输出流
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(new FileOutputStream(new File("E://io//TestObj.txt")));
        objectOutputStream.writeObject(new TestObject("he", "llow"));
        objectOutputStream.close();
        //对象输入流
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("E://io//TestObj.txt")));
        TestObject user = (TestObject) objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();


    }

    /**
     * 读取文件内容
     */
    @Test
    public void test02() {
        StringBuilder result = new StringBuilder();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("E://io//nihao.txt");
            fileOutputStream.write("你好".getBytes());

            File file = new File("E://io//nihao.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }

    /**
     * 读取文件内容
     */
    @Test
    public void test03() throws IOException {
        File file = new File("E://io//Hello1.txt");
        // 创建文件
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);
        // 向文件写入内容
        writer.write("This\n is\n an\n example\n");
        writer.flush();
        writer.close();
        // 创建 FileReader 对象
        FileReader fr = new FileReader(file);
        char[] a = new char[50];
        fr.read(a); // 读取数组中的内容
        for (char c : a)
            System.out.print(c); // 一个一个打印字符
        fr.close();
    }
}
