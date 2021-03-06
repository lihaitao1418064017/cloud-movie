//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.lht.boot.lang.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 反射工具类
 * @Author: Lihaitao
 * @Date: 2020/1/13 17:01
 * @UpdateUser:
 * @UpdateRemark:
 */
public class ReflectionUtil extends ReflectUtil {
    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    public static final Class[] NO_PARAM_SIGNATURE = new Class[0];
    public static final Object[] NO_PARAMS = new Object[0];
    public static final Class[] SINGLE_OBJECT_PARAM_SIGNATURE = new Class[]{Object.class};
    private static Table<Class, String, Field> classStringFieldTable = HashBasedTable.create();
    public static final LoadingCache<Class, List<Field>> declaredFieldsCache = CacheBuilder.newBuilder().build(new CacheLoader<Class, List<Field>>() {
        public List<Field> load(Class clazz) {
            List<Field> fields = Lists.newArrayList();

            for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
                Field[] superClassFields = superClass.getDeclaredFields();
                fields.addAll(Lists.newArrayList(superClassFields));
            }

            return fields;
        }
    });

    private ReflectionUtil() {
    }

    public static Object invokeGetter(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[0], new Object[0]);
    }

    public static void invokeSetter(Object obj, String propertyName, Object value) {
        invokeSetter(obj, propertyName, value, (Class) null);
    }

    public static void invokeSetter(Object obj, String propertyName, Object value, Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[]{type}, new Object[]{value});
    }

    public static Object getFieldValue(Object obj, String fieldName) throws IllegalArgumentException {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        } else {
            Object result = null;

            try {
                result = field.get(obj);
            } catch (IllegalAccessException var5) {
                log.error("不可能抛出的异常{}", var5.getMessage());
            }

            return result;
        }
    }

    public static Object getFieldValueSilently(Object obj, String fieldName) throws IllegalArgumentException {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            return null;
        } else {
            Object result = null;

            try {
                result = field.get(obj);
            } catch (IllegalAccessException var5) {
                log.error("不可能抛出的异常{}", var5.getMessage());
            }

            return result;
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        Field field = getAccessibleField(obj, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
        } else {
            try {
                field.set(obj, value);
            } catch (IllegalAccessException var5) {
                log.error("不可能抛出的异常:{}", var5.getMessage());
            }

        }
    }

    public static void setFieldValueSilent(Object obj, String fieldName, Object value) {
        Field field = getAccessibleField(obj, fieldName);
        if (field != null) {
            try {
                field.set(obj, value);
            } catch (IllegalAccessException var5) {
                log.error("不可能抛出的异常:{}", var5.getMessage());
            }
        }

    }

    public static Class<?> getUserClass(Object instance) {
        if (instance == null) {
            return null;
        } else {
            Class clazz = instance.getClass();
            if (clazz != null && clazz.getName().contains("$$")) {
                Class<?> superClass = clazz.getSuperclass();
                if (superClass != null && !Object.class.equals(superClass)) {
                    return superClass;
                }
            }

            return clazz;
        }
    }

    public static Object invokeMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
        } else {
            try {
                return method.invoke(obj, args);
            } catch (Exception var6) {
                throw convertReflectionExceptionToUnchecked(var6);
            }
        }
    }


    public static Field getAccessibleField(Object obj, String fieldName) {
        Validate.notNull(obj, "object can't be null", new Object[0]);
        return getAccessibleField(obj.getClass(), fieldName);
    }

    public static Field getAccessibleField(Class clazz, String fieldName) {
        Validate.notNull(clazz, "object can't be null", new Object[0]);
        Validate.notBlank(fieldName, "fieldName can't be blank", new Object[0]);
        Field field = (Field) classStringFieldTable.get(clazz, fieldName);
        if (field == null) {
            Class superClass = clazz;

            while (superClass != Object.class) {
                try {
                    field = superClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    classStringFieldTable.put(clazz, fieldName, field);
                    break;
                } catch (NoSuchFieldException var5) {
                    superClass = superClass.getSuperclass();
                }
            }
        }

        return field;
    }

    public static Method getAccessibleMethod(Object obj, String methodName, Class... parameterTypes) {
        Validate.notNull(obj, "object can't be null", new Object[0]);
        Class superClass = obj.getClass();

        while (superClass != Object.class) {
            try {
                Method method = superClass.getDeclaredMethod(methodName, parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException var5) {
                superClass = superClass.getSuperclass();
            }
        }

        return null;
    }

    public static <T> Class<T> getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class) params[index];
                }
            } else {
                log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
                return Object.class;
            }
        }
    }

    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else if (!(e instanceof IllegalAccessException) && !(e instanceof NoSuchMethodException)) {
            return e instanceof InvocationTargetException ? new RuntimeException(((InvocationTargetException) e).getTargetException()) : new RuntimeException("Unexpected Checked Exception.", e);
        } else {
            return new IllegalArgumentException(e);
        }
    }

    public static Type[] getSuperClassGenricTypes(Class clazz) {
        Type typeClass = clazz.getGenericSuperclass();
        return typeClass instanceof ParameterizedType ? ((ParameterizedType) typeClass).getActualTypeArguments() : null;
    }

    public static List<Field> getAccessibleFields(Class clazz, Class annotationClass) {
        Validate.notNull(clazz, "object can't be null", new Object[0]);
        Validate.notNull(annotationClass, "annotationClass can't be blank", new Object[0]);
        List<Field> fields = Lists.newArrayList();

        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            Field[] superClassFields = superClass.getDeclaredFields();
            Field[] var5 = superClassFields;
            int var6 = superClassFields.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                if (field.getAnnotation(annotationClass) != null) {
                    fields.add(field);
                    field.setAccessible(true);
                }
            }
        }

        return fields;
    }

    public static List<Field> getAccessibleFields(Class clazz) {
        return (List) declaredFieldsCache.getUnchecked(clazz);
    }

    public static <E> E repackage(E entity, Set<String> includes, Set<String> excludes) {
        if (entity == null) {
            return entity;
        } else if (CollectionUtil.isEmpty(includes) && CollectionUtil.isEmpty(excludes)) {
            return entity;
        } else {
            if (CollectionUtil.isEmpty(excludes)) {
                excludes = (Set) getAccessibleFields(entity.getClass()).stream().map(Field::getName).collect(Collectors.toSet());
            }

            if (CollectionUtil.isNotEmpty(includes)) {
                excludes.removeAll(includes);
            }

            Iterator var3 = excludes.iterator();

            while (var3.hasNext()) {
                String fieldName = (String) var3.next();
                setFieldValue(entity, fieldName, (Object) null);
            }

            return entity;
        }
    }
}
