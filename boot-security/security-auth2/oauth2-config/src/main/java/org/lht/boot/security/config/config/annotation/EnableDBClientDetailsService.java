package org.lht.boot.security.config.config.annotation;

import org.lht.boot.security.config.config.service.DBClientDetailsService;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(DBClientDetailsService.class)
public @interface EnableDBClientDetailsService {
}
