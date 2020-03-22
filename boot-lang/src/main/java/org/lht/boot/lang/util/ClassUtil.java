//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.lht.boot.lang.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Description: Class工具类
 * @Author: Lihaitao
 * @Date: 2020/1/13 17:01
 * @UpdateUser:
 * @UpdateRemark:
 */
public class ClassUtil extends cn.hutool.core.util.ClassUtil {
    private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
    public static final String ARRAY_SUFFIX = "[]";
    public static final String CGLIB_CLASS_SEPARATOR = "$$";
    public static final String CLASS_FILE_SUFFIX = ".class";
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final char PACKAGE_SEPARATOR = '.';
    private static final char PATH_SEPARATOR = '/';
    private static final char INNER_CLASS_SEPARATOR = '$';

    public ClassUtil() {
    }

    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotation) {
        if (clazz == null) {
            return null;
        } else {
            T ann = clazz.getAnnotation(annotation);
            if (ann != null) {
                return ann;
            } else {
                return clazz.getSuperclass() != Object.class ? getAnnotation(clazz.getSuperclass(), annotation) : ann;
            }
        }
    }

    public static <T extends Annotation> T getAnnotation(Method method, Class<T> annotation) {
        T ann = method.getAnnotation(annotation);
        if (ann != null) {
            return ann;
        } else {
            Class clazz = method.getDeclaringClass();
            Class superClass = clazz.getSuperclass();
            if (superClass != null && superClass != Object.class) {
                try {
                    Method suMethod = superClass.getMethod(method.getName(), method.getParameterTypes());
                    return getAnnotation(suMethod, annotation);
                } catch (NoSuchMethodException var6) {
                    return null;
                }
            } else {
                return ann;
            }
        }
    }

    public static <A extends Annotation> A getAnnotation(Method method, Class<?> targetClass, Class<A> annotationClass) {
        Method specificMethod = getMostSpecificMethod(method, targetClass);
        A annotation = getAnnotation(specificMethod, annotationClass);
        if (annotation != null) {
            log.debug(annotation + " found on specific method: " + specificMethod);
            return annotation;
        } else {
            if (specificMethod != method) {
                annotation = getAnnotation(method, annotationClass);
                if (annotation != null) {
                    log.debug(annotation + " found on: " + method);
                    return annotation;
                }
            }

            annotation = getAnnotation(specificMethod.getDeclaringClass(), annotationClass);
            if (annotation != null) {
                log.debug(annotation + " found on: " + specificMethod.getDeclaringClass().getName());
                return annotation;
            } else {
                return null;
            }
        }
    }

    public static Method getMostSpecificMethod(Method method, Class<?> targetClass) {
        if (method != null && isOverridable(method, targetClass) && targetClass != null && targetClass != method.getDeclaringClass()) {
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    try {
                        return targetClass.getMethod(method.getName(), method.getParameterTypes());
                    } catch (NoSuchMethodException var3) {
                        return method;
                    }
                }

                Method specificMethod = ReflectionUtil.getAccessibleMethod(targetClass, method.getName(), method.getParameterTypes());
                return specificMethod != null ? specificMethod : method;
            } catch (SecurityException var4) {
                ;
            }
        }

        return method;
    }

    private static boolean isOverridable(Method method, Class<?> targetClass) {
        if (Modifier.isPrivate(method.getModifiers())) {
            return false;
        } else {
            return !Modifier.isPublic(method.getModifiers()) && !Modifier.isProtected(method.getModifiers()) ? getPackageName(method.getDeclaringClass()).equals(getPackageName(targetClass)) : true;
        }
    }

    public static String getPackageName(Class<?> clazz) {
        Validate.notNull(clazz, "Class must not be null", new Object[0]);
        return getPackageName(clazz.getName());
    }

    public static String getPackageName(String fqClassName) {
        Validate.notNull(fqClassName, "Class name must not be null", new Object[0]);
        int lastDotIndex = fqClassName.lastIndexOf(46);
        return lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "";
    }

    public static Class<?> getGenericTypeByType(ParameterizedType genType, int index) {
        Type[] params = genType.getActualTypeArguments();
        if (index < params.length && index >= 0) {
            Object res = params[index];
            if (res instanceof Class) {
                return (Class) res;
            } else {
                return res instanceof ParameterizedType ? (Class) ((ParameterizedType) res).getRawType() : null;
            }
        } else {
            return null;
        }
    }

    public static Class<?> getGenericType(Class clazz, int index) {
        List<Type> arrys = new ArrayList();
        arrys.add(clazz.getGenericSuperclass());
        arrys.addAll(Arrays.asList(clazz.getGenericInterfaces()));
        return (Class) arrys.stream().filter(Objects::nonNull).map((type) -> {
            return clazz != Object.class && !(type instanceof ParameterizedType) ? getGenericType(clazz.getSuperclass(), index) : getGenericTypeByType((ParameterizedType) type, index);
        }).filter(Objects::nonNull).filter((res) -> {
            return res != Object.class;
        }).findFirst().orElse(Object.class);
    }

    public static Class<?> getGenericType(Class clazz) {
        return getGenericType(clazz, 0);
    }

    public static boolean instanceOf(Class clazz, Class target) {
        if (clazz == null) {
            return false;
        } else if (clazz == target) {
            return true;
        } else {
            Class[] var2;
            int var3;
            int var4;
            Class aClass;
            if (target.isInterface()) {
                var2 = clazz.getInterfaces();
                var3 = var2.length;

                for (var4 = 0; var4 < var3; ++var4) {
                    aClass = var2[var4];
                    if (aClass == target) {
                        return true;
                    }
                }
            }

            if (clazz.getSuperclass() == target) {
                return true;
            } else {
                if (clazz.isInterface()) {
                    var2 = clazz.getInterfaces();
                    var3 = var2.length;

                    for (var4 = 0; var4 < var3; ++var4) {
                        aClass = var2[var4];
                        if (instanceOf(aClass, target)) {
                            return true;
                        }
                    }
                }

                return instanceOf(clazz.getSuperclass(), target);
            }
        }
    }

    public static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField("TYPE").get((Object) null)).isPrimitive();
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isPrimaryClass(Class clz) {
        return clz.isPrimitive();
    }

    public static boolean isPrimaryOrWrapClass(Class clz) {
        return isPrimaryClass(clz) || isWrapClass(clz);
    }

    public static boolean isInterfaceOrAbstractClass(Class clz) {
        return Modifier.isAbstract(clz.getModifiers()) || Modifier.isInterface(clz.getModifiers());
    }

    public static <S extends Collection<T>, T> Collection createCollection(Class<S> type, int len) throws Exception {
        if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
            try {
                return (Collection) type.newInstance();
            } catch (Exception var3) {
                ;
            }
        }

        if (List.class.isAssignableFrom(type)) {
            if (type.isAssignableFrom(ArrayList.class)) {
                return Lists.newArrayListWithExpectedSize(len);
            }

            if (type.isAssignableFrom(LinkedList.class)) {
                return Lists.newLinkedList();
            }
        } else if (Set.class.isAssignableFrom(type)) {
            if (type.isAssignableFrom(HashSet.class)) {
                return Sets.newHashSetWithExpectedSize(len);
            }

            if (type.isAssignableFrom(LinkedHashSet.class)) {
                return Sets.newLinkedHashSet();
            }
        }

        throw new Exception(String.format("collection class[%s] not supported."));
    }

    public static <S extends Collection<T>, T> Collection createCollection(Class<S> type) throws Exception {
        if (List.class.isAssignableFrom(type)) {
            if (type.isAssignableFrom(LinkedList.class)) {
                return Lists.newLinkedList();
            }

            if (type.isAssignableFrom(ArrayList.class)) {
                return Lists.newArrayList();
            }
        } else if (Set.class.isAssignableFrom(type)) {
            if (type.isAssignableFrom(LinkedHashSet.class)) {
                return Sets.newLinkedHashSet();
            }

            if (type.isAssignableFrom(HashSet.class)) {
                return Sets.newHashSet();
            }
        }

        if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
            try {
                return (Collection) type.newInstance();
            } catch (Exception var2) {
                ;
            }
        }

        throw new Exception(String.format("collection class[%s] not supported."));
    }

    public static <S extends Map> S createMap(Class<S> cl) {
        Map result = null;
        if (HashMap.class == cl) {
            result = new HashMap();
        } else if (Hashtable.class == cl) {
            result = new Hashtable();
        } else if (IdentityHashMap.class == cl) {
            result = new IdentityHashMap();
        } else if (LinkedHashMap.class == cl) {
            result = new LinkedHashMap();
        } else if (Properties.class == cl) {
            result = new Properties();
        } else if (TreeMap.class == cl) {
            result = new TreeMap();
        } else if (WeakHashMap.class == cl) {
            result = new WeakHashMap();
        } else if (ConcurrentHashMap.class == cl) {
            result = new ConcurrentHashMap();
        } else if (ConcurrentSkipListMap.class == cl) {
            result = new ConcurrentSkipListMap();
        } else {
            try {
                result = (Map) cl.newInstance();
            } catch (Exception var4) {
                ;
            }

            if (result == null) {
                try {
                    Constructor<?> constructor = cl.getConstructor(Map.class);
                    result = (Map) constructor.newInstance(Collections.EMPTY_MAP);
                } catch (Exception var3) {
                    ;
                }
            }
        }

        if (result == null) {
            result = new HashMap();
        }

        return (S) result;
    }
}
