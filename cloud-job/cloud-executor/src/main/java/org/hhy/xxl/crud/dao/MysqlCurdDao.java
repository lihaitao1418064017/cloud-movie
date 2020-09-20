package org.hhy.xxl.crud.dao;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.hhy.xxl.crud.annotation.ID;
import org.hhy.xxl.crud.annotation.Table;
import org.hhy.xxl.crud.annotation.TableField;
import org.hhy.xxl.job.executor.bean.BaseEntity;
import org.lht.boot.lang.util.ClassUtil;
import org.lht.boot.lang.util.ReflectionUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author LiuHao
 * @date 2020/9/13 22:55
 * @description MySQL curl操作类
 */
@Slf4j
@NoRepositoryBean
public class MysqlCurdDao<E extends BaseEntity<PK>, PK extends Serializable> implements BaseCrudDao<E, PK> {

    /**
     * SQL_TEMPLATE 常量
     */
    private static final String INSERT_SQL = "INSERT INTO {tableName} ({columnNames}) VALUES ({columnValues})",
            DELETE_SQL = "DELETE FROM {tableName} {where} {whereCondition}",
            UPDATE_SQL = "UPDATE {tableName} SET {updateClause} {where} {whereCondition}",
            SELECT_SQL = "SELECT {columnNames} FROM {tableName} {where} {whereCondition} {extCondition}",
            TABLE_NAME_PLACEHOLDER = "{tableName}",
            COLUMN_NAMES_PLACEHOLDER = "{columnNames}",
            COLUMN_VALUES_PLACEHOLDER = "{columnValues}",
            WHERE_CONDITION_PLACEHOLDER = "{whereCondition}",
            EXT_CONDITION_PLACEHOLDER = "{extCondition}",
            UPDATE_CLAUSE_PLACEHOLDER = "{updateClause}",
            WHERE_PLACEHOLDER = "{where}",
            WHERE_CONSTANT = "WHERE",
            WILDCARD_CONSTANT = "*",
            EMPTY_CONSTANT = "";

    private   NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * class类型
     */
    private Class<E> entityType;

    /**
     * table注解相关值
     */
    private Table table;

    /**
     * tableField注解相关值
     */
    private Map<String, String> entityTableFieldMap = new HashMap<>(16);

    /**
     * 主键字段名（实体类）
     */
    private String primaryKeyFieldName;

    @SuppressWarnings("unchecked")
    public MysqlCurdDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.entityType = (Class<E>) ClassUtil.getGenericType(this.getClass(), 0);
//        this.entityType = (Class<Actor>)new Actor().getClass();
        //获取实体E上的注解
        this.table = ClassUtil.getAnnotation(this.entityType, Table.class);
        initTableField();
    }

    /**
     * 使用原生方法时调用
     *
     * @return NamedParameterJdbcTemplate
     */
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return jdbcTemplate;
    }


    /** ============ delete function start =========== **/
    /**
     * ============ SQL_TEMPLATE : DELETE FROM {tableName} {where} {whereCondition} ===========
     **/
    @Override
    public int delete(PK pk) {
        //主键字段名（真正拼接在sql中的）
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = DELETE_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER,
                        primaryKeyName + "=:" + primaryKeyName);
        log.debug(sql);
        Map<String, Object> param = Maps.newHashMap();
        param.put(primaryKeyName, pk);
        return delete(sql, param);
    }

    @Override
    public void deleteById(Collection<PK> ids) {
        //主键字段名（真正拼接在sql中的）
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = DELETE_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER,
                        primaryKeyName + "= exist ( :" + primaryKeyName + " )");
        log.debug(sql);
        Map<String, Object> param = Maps.newHashMap();
        param.put(primaryKeyName, ids);
        delete(sql, param);
    }

    @Override
    public <S extends E> void delete(Collection<S> entities) {
        deleteById(entities.stream().map(this::getPrimaryKeyValue).collect(toList()));
    }

    @Override
    public <S extends E> void delete(S entity) {
        delete(getPrimaryKeyValue(entity));
    }

    /** =========== add function start ===== **/

    /**
     * ============ SQL_TEMPLATE : INSERT INTO {tableName} ({name,age}) VALUES ({:name,:age}) ================
     **/
    @SuppressWarnings("unchecked")
    @Override
    public PK add(E e) {
        String sql = INSERT_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(COLUMN_NAMES_PLACEHOLDER, generateColumnNames())
                .replace(COLUMN_VALUES_PLACEHOLDER, generateColumnValues());
        log.debug(sql);
        return (PK) Integer.valueOf(insert(sql, generateParams(e)));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S extends E> List<PK> add(Collection<S> entities) {
        String sql = INSERT_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(COLUMN_NAMES_PLACEHOLDER, generateColumnNames())
                .replace(COLUMN_VALUES_PLACEHOLDER, generateColumnValues());
        log.debug(sql);
        return (List<PK>) Lists.newArrayList(batchInsert(sql, generateParams(entities)));
    }

    /** =========== add function end ======*/

    /** =========== select function start=======*/
    /**
     * ======== SQL_TEMPLATE : SELECT_SQL = "SELECT {columnNames} FROM {tableName} {where} {whereCondition} {extCondition}
     **/
    @Override
    public E findOne(PK pk) {
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = SELECT_SQL.replace(COLUMN_NAMES_PLACEHOLDER, generateColumnNames())
                .replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER, primaryKeyName + "=:" + primaryKeyName)
                .replace(EXT_CONDITION_PLACEHOLDER, EMPTY_CONSTANT)
                .trim();

        Map<String, PK> param = Maps.newHashMap();
        param.put(primaryKeyName, pk);
        return map2Bean(jdbcTemplate.queryForMap(sql, param));
    }

    @Override
    public List<E> findAll(Collection<PK> ids) {
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = SELECT_SQL.replace(COLUMN_NAMES_PLACEHOLDER, generateColumnNames())
                .replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER, primaryKeyName + "= exist (:" + primaryKeyName + ")")
                .replace(EXT_CONDITION_PLACEHOLDER, EMPTY_CONSTANT)
                .trim();

        Map<String, Collection<PK>> param = Maps.newHashMap();
        param.put(primaryKeyName, ids);
        //此处应该转化成map，经过处理转换为相应的对象
        return jdbcTemplate.queryForList(sql, param).stream().map(this::map2Bean).collect(toList());
    }

    @Override
    public List<E> findAll() {
        String sql = SELECT_SQL.replace(COLUMN_NAMES_PLACEHOLDER, generateColumnNames())
                .replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(WHERE_PLACEHOLDER, EMPTY_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER, EMPTY_CONSTANT)
                .replace(EXT_CONDITION_PLACEHOLDER, EMPTY_CONSTANT)
                .trim();
        return jdbcTemplate.queryForList(sql, Maps.newHashMap()).stream().map(this::map2Bean).collect(toList());
    }
    /** =========== select function end=======*/


    /** ==========  update function start =======**/
    /*** ============ SQL_TEMPLATE : UPDATE {tableName} SET {updateClause} {where} {whereCondition} ============**/
    @Override
    public PK update(E e) {
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = UPDATE_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(UPDATE_CLAUSE_PLACEHOLDER, "")
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER, primaryKeyName + "=:" + primaryKeyName)
                .trim();
        Map<String, Object> param = Maps.newHashMap();
        param.put(primaryKeyName, getPrimaryKeyValue(e));
        update(sql, param);
        /*待商榷*/
        return null;
    }

    @Override
    public List<PK> update(Collection<E> entities) {
        String primaryKeyName = getPrimaryKeyFieldName();
        String sql = UPDATE_SQL.replace(TABLE_NAME_PLACEHOLDER, getTableName())
                .replace(UPDATE_CLAUSE_PLACEHOLDER, "")
                .replace(WHERE_PLACEHOLDER, WHERE_CONSTANT)
                .replace(WHERE_CONDITION_PLACEHOLDER, primaryKeyName + "=:" + primaryKeyName)
                .trim();
        /*待完成*/
        return null;
    }

    @Override
    public PK upsert(E e) {
        return null;
    }

    @Override
    public List<PK> upsert(Collection<E> entities) {
        return null;
    }

    @Override
    public PK patch(E e) {
        return null;
    }

    @Override
    public List<PK> patch(Collection<E> entities) {
        return null;
    }

    /**==== DDL语句执行方法======*/
    /**
     * 执行DDL语句SELECT返回单个对象
     *
     * @param sql      执行sql
     * @param paramMap 查询参数
     * @return
     */
    public E selectOne(String sql, Map<String, Object> paramMap) {
        List<E> resultList = selectList(sql, paramMap);
        if (resultList.size() > 1) {
            throw new RuntimeException("异常：符合查询条件记录超过1条");
        } else if (resultList.size() < 1) {
            return null;
        }
        return resultList.get(0);
    }

    /**
     * 执行DDL语句SELECT返回List
     *
     * @param sql      执行sql
     * @param paramMap 查询参数
     * @return
     */
    public List<E> selectList(String sql, Map<String, Object> paramMap) {
        List<E> result;
        try {
            /*result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(eClass));*/
            result = jdbcTemplate.queryForList(sql, paramMap).stream().map(this::map2Bean).collect(toList());
        } catch (EmptyResultDataAccessException e) {
            //query 查询如果没有数据，jdbcTemplate会空结果异常，需要让使用者主动处理
            return new ArrayList<>();
        }
        return result;
    }

    /**
     * 执行DDL语句 insert
     *
     * @param sql      执行sql
     * @param paramMap 参数
     * @return {插入状态}
     */
    public int insert(String sql, Map<String, Object> paramMap) {
        return jdbcTemplate.update(sql, paramMap);
    }

    /**
     * 执行DDL语句 batchInsert
     *
     * @param sql    执行sql
     * @param params 参数[]
     * @return
     */
    public int[] batchInsert(String sql, Map<String, Object>[] params) {
        return jdbcTemplate.batchUpdate(sql, params);
    }

    /**
     * 执行DDL语句 delete
     *
     * @param sql      执行sql
     * @param paramMap 参数
     * @return
     */
    public int delete(String sql, Map<String, Object> paramMap) {
        return jdbcTemplate.update(sql, paramMap);
    }

    /**
     * 执行DDL语句 update
     *
     * @param sql      执行sql
     * @param paramMap 参数
     * @return
     */
    public int update(String sql, Map<String, Object> paramMap) {
        return jdbcTemplate.update(sql, paramMap);
    }

    /**
     * 执行DDL语句 batchUpdate
     *
     * @param sql    执行sql
     * @param params 参数[]
     * @return
     */
    public int[] batchUpdate(String sql, Map<String, Object>[] params) {
        return jdbcTemplate.batchUpdate(sql, params);
    }


    /**
     * 批量新增
     *
     * @param sql       sql
     * @param batchArgs new BeanPropertySqlParameterSource(Object object)[]
     * @return
     */
    public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs) {
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 批量新增
     *
     * @param sql
     * @param entityList
     */
    public int[] batchUpdate(String sql, List<E> entityList) {
        int listSize = entityList.size();
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[listSize];
        for (int i = 0; i < listSize; i++) {
            sqlParameterSources[i] = new BeanPropertySqlParameterSource(entityList.get(i));
        }
        return batchUpdate(sql, sqlParameterSources);
    }

    /** ==============  private function  ============= **/

    /**
     * 获取表名
     *
     * @return
     */
    private String getTableName() {
        return this.table == null ? this.entityType.getSimpleName() : this.table.value();
    }

    /**
     * 初始化数据表字段映射表
     */
    private void initTableField() {
        //通过注解和反射，将字段信息维护在map中 ==> key=实体类字段名 value=注解字段名
        List<Field> fields = ReflectionUtil.getAccessibleFields(this.entityType);
        fields.forEach(entityField -> {
            entityField.setAccessible(true);
            TableField tableField = entityField.getAnnotation(TableField.class);
            if (tableField == null) {
                this.entityTableFieldMap.put(entityField.getName(), entityField.getName());
            } else {
                //如果注解属性exist为true，表示该字段为数据库字段，否则不是。
                if (tableField.exist()) {
                    //如果字段上的注解属性value值为空，则取对象的字段名作为数据表字段名
                    if (StringUtils.isEmpty(tableField.value())) {
                        //key=实体类字段名 value=注解字段名
                        this.entityTableFieldMap.put(entityField.getName(), entityField.getName());
                    } else {
                        this.entityTableFieldMap.put(entityField.getName(), tableField.value());
                    }
                }
            }
        });
        //通过注解，设置主键字段
        List<Field> primaryKeyField = ReflectionUtil.getAccessibleFields(this.entityType, ID.class);
        if (!primaryKeyField.isEmpty()) {
            if (primaryKeyField.size() > 1) {
                throw new RuntimeException("多主键异常");
            }
            //将主键字段信息维护在primaryKeyFieldName中,此处为实体类字段名
            primaryKeyFieldName = primaryKeyField.get(0).getName();
        } else {
            primaryKeyFieldName = BaseEntity.ID;
//            throw new RuntimeException("未设置主键异常");
        }
    }


    /**
     * 生成SQL字段名
     *
     * @return
     */
    private String generateColumnNames() {
        return String.join(",", entityTableFieldMap.values());
    }

    /**
     * 生成SQL字段值占位符
     *
     * @return
     */
    private String generateColumnValues() {
        return ":" + String.join(",:", entityTableFieldMap.values());
    }


    /**
     * 获取SQL字段值参数值
     *
     * @param entity
     * @return
     */
    private Map<String, Object> generateParams(E entity) {
        Map<String, Object> params = new HashMap<>(16);
        entityTableFieldMap.forEach((k, v) ->
                params.put(v, ReflectionUtil.getFieldValue(entity, k))
        );
        return params;
    }

    /**
     * 获取SQL字段值参数值（批量）
     *
     * @param entities
     * @param <S>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <S extends E> Map<String, Object>[] generateParams(Collection<S> entities) {
        return entities.stream().map(this::generateParams).collect(toList()).toArray(new HashMap[entities.size()]);
    }

    /**
     * 将返回的含有数据库字段的map，转换为需要的实体类对象
     *
     * @param map 数据map
     * @return {E}
     */
    private E map2Bean(Map<String, Object> map) {
        E entity = ReflectionUtil.newInstance(entityType);
        List<Field> fields = ReflectionUtil.getAccessibleFields(this.entityType);
        fields.forEach(field -> {
            field.setAccessible(true);
            try {
                field.set(entity, map.get(entityTableFieldMap.get(field.getName())));
            } catch (IllegalAccessException e) {
                log.error("类转换异常", e);
            }
        });
        return entity;
    }

    /**
     * 获取主键字段名
     *
     * @return
     */
    private String getPrimaryKeyFieldName() {
        return entityTableFieldMap.get(primaryKeyFieldName);
    }

    /**
     * 获取主键字段值
     *
     * @param entity
     * @param <S>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <S extends E> PK getPrimaryKeyValue(S entity) {
        PK pk = (PK) ReflectionUtil.getFieldValue(entity, primaryKeyFieldName);
        if (pk == null) {
            throw new RuntimeException("主键值为空");
        }
        return pk;
    }
}
