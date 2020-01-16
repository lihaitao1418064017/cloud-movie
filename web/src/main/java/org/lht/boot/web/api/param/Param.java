package org.lht.boot.web.api.param;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.*;

/**
 * @author LiHaitao
 * @description Param:
 * @date 2020/1/8 15:15
 **/
@Getter
public class Param<E> implements Serializable, Cloneable {

    /**
     * 条件
     */
    @Getter
    protected List<Term> terms = new LinkedList<>();

    /**
     * 指定要处理的字段
     */
    protected Set<String> includes = new LinkedHashSet<>();

    /**
     * 指定不处理的字段
     */
    protected Set<String> excludes = new LinkedHashSet<>();


    public <T extends Param> T and(Term term) {
        terms.add(term);
        return (T) this;
    }

    public <T extends Param> T or(String column, Object value) {
        return or(column, TermEnum.eq, value);
    }

    public <T extends Param> T and(String column, Object value) {
        return and(column, TermEnum.eq, value);
    }

    public <T extends Param> T or(String column, TermEnum termType, Object value) {
        Term term = new Term();
        term.setTermType(termType);
        term.setColumn(column);
        term.setValue(value);
        term.setType(Term.Type.or);
        terms.add(term);
        return (T) this;
    }

    public <T extends Param> T and(String column, TermEnum termType, Object value) {
        Term term = new Term();
        term.setTermType(termType);
        term.setColumn(column);
        term.setValue(value);
        term.setType(Term.Type.and);
        terms.add(term);
        return (T) this;
    }


    public Term nest() {
        return nest(null, null);
    }

    public Term orNest() {
        return orNest(null, null);
    }

    public Term nest(String termString, Object value) {
        Term term = new Term();
        term.setColumn(termString);
        term.setValue(value);
        term.setType(Term.Type.and);
        terms.add(term);
        return term;
    }

    public Term orNest(String termString, Object value) {
        Term term = new Term();
        term.setColumn(termString);
        term.setValue(value);
        term.setType(Term.Type.or);
        terms.add(term);
        return term;
    }

    public <T extends Param> T includes(String... fields) {
        includes.addAll(Arrays.asList(fields));
        excludes.removeAll(Arrays.asList(fields));
        return (T) this;
    }

    public <T extends Param> T excludes(String... fields) {
        excludes.addAll(Arrays.asList(fields));
        includes.removeAll(Arrays.asList(fields));
        return (T) this;
    }

    public <T extends Param> T includes(List<String> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            includes.addAll(fields);
            excludes.removeAll(fields);
        }
        return (T) this;
    }

    public <T extends Param> T excludes(List<String> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            excludes.addAll(fields);
            includes.removeAll(Arrays.asList(fields));
        }
        return (T) this;
    }

    public <T extends Param> T where(String key, Object value) {
        and(key, value);
        return (T) this;
    }

    public <T extends Param> T where(Term term) {
        and(term);
        return (T) this;
    }


    public <T extends Param> T where(String key, TermEnum termType, Object value) {
        and(key, termType, value);
        return (T) this;
    }

    public Set<String> getIncludes() {
        if (includes == null) {
            includes = new LinkedHashSet<>();
        }
        return includes;
    }

    public Set<String> getExcludes() {
        if (excludes == null) {
            excludes = new LinkedHashSet<>();
        }
        return excludes;
    }

    public void setIncludes(Set<String> includes) {
        this.includes = includes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public <T extends Param> T addTerm(Term term) {
        terms.add(term);
        return (T) this;
    }

    public <T extends Param> T addTerms(List<Term> terms) {
        if (CollectionUtils.isNotEmpty(terms)) {
            this.terms.addAll(terms);
        }
        return (T) this;
    }


}
