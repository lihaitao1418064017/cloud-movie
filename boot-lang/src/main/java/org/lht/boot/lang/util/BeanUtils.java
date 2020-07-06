package org.lht.boot.lang.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.map.MapUtil.newHashMap;

/**
 * Description:Bean工具类
 *
 * @Author lht
 * @Date 2020/1/11 6:40 PM
 **/
public class BeanUtils extends BeanUtil {

    /**
     * 将对象转换成Map
     *
     * @param object  要转换的对象
     * @param putNull 是否将属性值为null的也放入map，true表示将null也put进去，false反之
     * @return
     */
    public static Map objectToMap(Object object, boolean putNull) {
        Map<Object, Object> map = newHashMap();
        List<Field> accessibleFields = ReflectionUtil.getAccessibleFields(object.getClass());
        accessibleFields.forEach(field -> {
            Object fieldValue = ReflectionUtil.getFieldValue(object, field);
            if (putNull) {
                map.put(field.getName(), fieldValue);
            } else {
                if (ObjectUtil.isNotNull(fieldValue)) {
                    map.put(field.getName(), fieldValue);
                }
            }
        });
        return map;
    }

    /**
     * 将对象转换成Map
     *
     * @param object 要转换的对象
     * @return
     */
    public static Map objectToMap(Object object) {
        return objectToMap(object, true);
    }

}
