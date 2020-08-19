package org.lht.boot.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiHaitao
 * @description 测试对象
 * @date 2020/8/19 13:14
 **/
@Data
public class TestObject implements Serializable {

    public TestObject() {

    }

    public TestObject(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    private String field1;

    private String field2;
}
