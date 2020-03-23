package org.lht.boot.lang;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/11 7:05 PM
 **/
public class MockTest {

    @Test
    public void test01() {
        Iterator mock = Mockito.mock(Iterator.class);
        Mockito.when(mock.next()).thenReturn("hello").thenReturn("world");

        //使用mock的对象
        String result = mock.next() + " " + mock.next() + " " + mock.next();
        //验证结果
        Assert.assertEquals("hello world world", result);
    }

    @Test
    public void test02() {
//        List mock = Mockito.mock(List.class);
//        mock.add(1);
//        mock.clear();
//        //校验方法是否执行
//        Mockito.verify(mock).add(2);
//        Mockito.verify(mock).clear();
    }
//
//    @Test(expected=Exception.class)
//    public void test03() throws IOException {
////        OutputStream mock = Mockito.mock(OutputStream.class);
//        //预设当流关闭时抛出异常
////        Mockito.doThrow(new IOException()).when(mock).close();
////        mock.close();
//    }
}
