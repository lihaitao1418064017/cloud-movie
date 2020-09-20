package org.hhy.xxl.job.executor.bean;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/9/20 下午5:43
 **/
public interface Entity<PK> {

   String ID ="id";

   PK getId();

   void setId(PK id);

}
