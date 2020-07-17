/*
 *
 *  * Copyright 2016 http://www.kedacomframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.lht.boot.lang.util;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import static cn.hutool.core.util.StrUtil.format;

/**
 * @Description: 响应类
 * @Author: Lihaitao
 * @Date: 2020/1/14 19:57
 * @UpdateUser:
 * @UpdateRemark:
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 8992436576262574064L;

    @Getter
    @Setter
    protected String message;

    @Getter
    @Setter
    protected T result;

    @Getter
    @Setter
    protected int status;

    @Getter
    @Setter
    protected String code;

    @Getter
    @Setter
    protected LinkedHashSet<String> fields;

    @Getter
    @Setter
    protected Long timestamp;


    @Getter
    protected transient Map<Class<?>, Set<String>> includes;


    @Getter
    protected transient Map<Class<?>, Set<String>> excludes;


    public R() {

    }

    public static <T> R<T> error(String message) {
        return error(500, "-1", message);
    }

    public static <T> R<T> error(String message, Object... args) {
        return error(500, message, args);
    }

    public static <T> R<T> error(int status, String message) {
        return error(status, "-1", message);
    }

    public static <T> R<T> error(int status, String message, Object... args) {
        return error(status, "-1", message, args);
    }

    public static <T> R<T> error(int status, String code, String message) {
        return error(status, code, message, null);
    }

    public static <T> R<T> error(int status, String code, String message, Object... args) {
        R<T> msg = new R<>();
        msg.message = format(message, args);
        msg.status(status);
        msg.code(code);
        return msg.putTimeStamp();
    }

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T result) {
        return new R<T>()
                .result(result)
                .putTimeStamp()
                .code("0")
                .status(200)
                .message("ok");
    }

    private R<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public R<T> result(T result) {
        this.result = result;
        return this;
    }

    public R<T> include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }

    public R<T> include(Class<?> type, Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(includes, type).add(field);
            }
        });
        return this;
    }

    public R<T> exclude(Class type, Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(excludes, type).add(field);
            }
        });
        return this;
    }

    public R<T> exclude(Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (getResult() != null) {
            type = getResult().getClass();
        } else {
            return this;
        }
        exclude(type, fields);
        return this;
    }

    public R<T> include(Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (getResult() != null) {
            type = getResult().getClass();
        } else {
            return this;
        }
        include(type, fields);
        return this;
    }

    public R<T> exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    public R<T> exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    public R<T> include(String... fields) {
        return include(Arrays.asList(fields));
    }

    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
    }

    public R<T> status(int status) {
        this.status = status;
        return this;
    }

    public R<T> code(String code) {
        this.code = code;
        return this;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

    public R<T> fields(LinkedHashSet<String> fields) {
        this.fields = fields;
        return this;
    }

    public R<T> field(String field) {
        if (this.fields == null) {
            synchronized (this) {
                if (this.fields == null) {
                    this.fields = Sets.newLinkedHashSet();
                }
            }
        }
        this.fields.add(field);
        return this;
    }


}